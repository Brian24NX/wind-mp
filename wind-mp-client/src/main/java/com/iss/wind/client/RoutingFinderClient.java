package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.sechedule.RoutingFinderPostReq;
import com.iss.wind.client.dto.sechedule.RoutingFinderResp;

import java.util.*;
import java.util.stream.Collectors;

import com.iss.wind.client.util.ServiceNameMap;
import com.iss.wind.client.util.SortUtil;
import com.iss.wind.client.util.StrUtils;
import com.iss.wind.client.util.rest.BusinessException;
import com.iss.wind.client.util.rest.RestTemplateLogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hanson
 * @date 2022/3/3  16:04
 * Schedule API
 */
@Slf4j
@Component
public class RoutingFinderClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;
    /**
     * API 获取航线信息
     * https://api-cockpit.cma-cgm.com/explore/catalog/vesseloperation/api/vesseloperation.route.v2/swagger
     * Port to port Schedule
     * List of solutions and schedules will be returned with conditions:
     *
     * Loading/Discharge/Transhipment
     * Loading and Discharge are mandatory
     * All codes must reefer to CMA CGM referential location API
     * Shipping company parameters
     * When parameter shipping company is set:
     * Commercial solutions for this shipcomp are returned in priority.
     * Commercial solutions for the other carriers (CMA CGM, CNC, ANL) will be returned if not commercial routes are defined on the shipping company set.
     * When parameter shipping company isn't set:
     * Commercial solutions are returned in priority
     * Solutions for all carriers (CMA CGM, CNC, ANL) are returned
     * To get Solutions for APL (US Military and US governmental) you must set to shipping company to "0015"
     * Departure date / arrival date / search range
     * Search range parameters is link with departure date OR arrival date
     * By default the planned schedule for each solutions will be proposed for the next 3 weeks from the specified departure date.
     * Ordering solutions by
     * Transit Time Balanced by number of transhipments
     * Departure date
     * Routing with statistics (parameters useRoutingStatistics)
     * If you select place of loading and/or place of discharge linked with no routable solutions, the preferred routing maritime solutions linked with the place will be proposed
     * By default this setup is activated.
     *
     * 解决方案清单和时间表将在有条件的情况下返回:
     *
     * 装货/卸货/转运
     * 装货和卸货是强制性的
     * 所有代码必须参考CMA CGM参考位置API
     * 航运公司参数
     * 设置船务公司参数时:
     * 该船公司的商业解决方案优先返回。
     * 其他承运人(CMA CGM, CNC, ANL)的商业解决方案将被退回，如果没有在航运公司的设置上定义商业航线。
     * 当没有设置船公司参数时:
     * 商业解决方案优先返回
     * 所有运营商(CMA CGM, CNC, ANL)的解决方案都已返回
     * 要获得APL(美国军方和政府)的解决方案，您必须将船运公司设置为“0015”。
     * 出发日期/到达日期/搜索范围
     * 搜索范围参数与出发日期或到达日期链接
     * 默认情况下，每个解决方案的计划时间表将在指定出发日期后的三周内提出。
     * 点解决方案,
     * 转运时间按转运数量平衡
     * 离职日期
     * 带有统计信息的路由(参数useRoutingStatistics)
     * 如果您选择的装货地点和/或卸货地点没有链接的路由解决方案，将提出与该地点链接的首选路由海上解决方案
     * 默认情况下，此设置是激活的。
     * @return
     */
    public List<RoutingFinderResp> routings(String placeOfLoading,String placeOfDischarge,String[] specificRoutings,String shippingCompany,String departureDate,String arrivalDate,String searchRange){
        String url = digitalApiUrl + "/vesseloperation/route/v2/routings?placeOfLoading={placeOfLoading}&placeOfDischarge={placeOfDischarge}&shippingCompany={shippingCompany}&departureDate={departureDate}&arrivalDate={arrivalDate}&searchRange={searchRange}";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken("rf:be");
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("placeOfLoading",placeOfLoading);
        paramMap.put("placeOfDischarge", placeOfDischarge);
        paramMap.put("specificRoutings", specificRoutings);
        paramMap.put("shippingCompany", shippingCompany);
        paramMap.put("departureDate", departureDate);
        paramMap.put("arrivalDate", arrivalDate);
        paramMap.put("searchRange", searchRange);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", "rf:be");
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<List<RoutingFinderResp>> responseType = new ParameterizedTypeReference<List<RoutingFinderResp>>() {};
        restTemplate.getInterceptors().add(new RestTemplateLogInterceptor());
        ResponseEntity<List<RoutingFinderResp>> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,paramMap);
        restTemplate.getInterceptors().clear();
        if (null != response && (response.getStatusCodeValue() == 200 || response.getStatusCodeValue() == 206 )){
            return response.getBody();
        }
        return null;
    }

    //方案list和是否直达
    public Map listRoutings(List<RoutingFinderResp> list,String placeOfLoading,String placeOfDischarge,String departureDate,String arrivalDate,String searchRange){
        try {
            Map ret = new HashMap();
            if (CollectionUtils.isEmpty(list)) {
                return ret;
            }
            //遍历获取方案种类及是否直达
            int cncCount = 0;
            int anlCount = 0;
            int aplCount = 0;
            int cmaCount = 0;
            List<String> cncList = new ArrayList<>();
            List<String> anlList = new ArrayList<>();
            List<String> aplList = new ArrayList<>();
            List<String> cmaList = new ArrayList<>();
            Map solutionServices = new HashMap();
            for (RoutingFinderResp r : list) {
                List<RoutingFinderResp.RoutingDetail> routingDetails = r.getRoutingDetails();
                //多个服务用
                r.setService(getServices(routingDetails));
                if ("0011".equals(r.getShippingCompany())) {
                    if(!StrUtils.isBlank(r.getService()) && !cncList.contains(r.getService())){
                        cncList.add(r.getService());
                    }
                    cncCount++;
                }
                if ("0002".equals(r.getShippingCompany())) {
                    if(!StrUtils.isBlank(r.getService()) && !anlList.contains(r.getService())){
                        anlList.add(r.getService());
                    }
                    anlCount++;
                }
                if ("0015".equals(r.getShippingCompany())) {
                    if(!StrUtils.isBlank(r.getService()) && !aplList.contains(r.getService())){
                        aplList.add(r.getService());
                    }
                    aplCount++;
                }
                if ("0001".equals(r.getShippingCompany())) {
                    if(!StrUtils.isBlank(r.getService()) && !cmaList.contains(r.getService())){
                        cmaList.add(r.getService());
                    }
                    cmaCount++;
                }
                r.setDirectFlag(1 == routingDetails.size() ? true : false);
                //起运港
                r.setPointfrom(routingDetails.get(0).getPointFrom().getLocation().getName());
                //起运时间
                r.setDeparturedate(routingDetails.get(0).getPointFrom().getDepartureDateLocal());
                //目的港
                r.setPointto(1 == routingDetails.size() ? routingDetails.get(0).getPointTo().getLocation().getName() : routingDetails.get(routingDetails.size() - 1).getPointTo().getLocation().getName());
                //到达时间
                r.setArrivaldate(1 == routingDetails.size() ? routingDetails.get(0).getPointTo().getArrivalDateLocal() : routingDetails.get(routingDetails.size() - 1).getPointTo().getArrivalDateLocal());
                //转成次数
                r.setTranshipment(routingDetails.size() - 1);
                //第一艘船名
                RoutingFinderResp.Vehicule veh = routingDetails.get(0).getTransportation().getVehicule();
                r.setShipname(null == veh?"":veh.getVehiculeName());
                //开始运输工具
                r.setStartMeanOfTransport(routingDetails.get(0).getTransportation().getMeanOfTransport());
                //结束运输工具
                r.setEndMeanOfTransport(routingDetails.get(routingDetails.size() - 1).getTransportation().getMeanOfTransport());
            }
            //是否再调一次【//默认只有0001，不需要再调一次；若有0002或0011或0015 则需再调一次接口】
            ret.put("againReq", getAgainReq(list));
            solutionServices.put("cnc",cncList);
            solutionServices.put("anl",anlList);
            solutionServices.put("apl",aplList);
            solutionServices.put("cma",cmaList);
            ret.put("solutionServices", solutionServices);
            ret.put("routings", list);
            //给前端做缓存
            ret.put("placeOfLoading", placeOfLoading);
            ret.put("placeOfDischarge", placeOfDischarge);
            ret.put("departureDate", departureDate);
            ret.put("arrivalDate", arrivalDate);
            ret.put("searchRange", searchRange);
            ret.put("cnc", cncList.size());//0011 - CNC
            ret.put("anl", anlList.size());//0002 - ANL
            ret.put("apl", aplList.size());//0015 - APL
            ret.put("cma", cmaList.size());//0001 - CMA
            return ret;
        }catch (Exception e){
            log.error("listRoutings异常：",e);
            throw new BusinessException("请求异常或超时!");
        }

    }

    //根据：方案->最早到达(【含排序找标签】服务名过滤后最早list)->排序->直达
    public List<RoutingFinderResp> routingSort(RoutingFinderPostReq routingFinderPostReq){
        //先根据方案筛选出list
        List<String> services = routingFinderPostReq.getSortSolutionServices();//前端传入的排序服务
        if(CollectionUtils.isEmpty(services)){
            //没有选择方案不排序
            return new ArrayList<>();
        }
        List<RoutingFinderResp> needSolutionList = getNeedSolutionList(routingFinderPostReq);
        //排序（升序）-给每个ervice加最快离港-到港标签
        List<RoutingFinderResp> sortServicesList = getSortServicesList(needSolutionList,routingFinderPostReq);
        //再根据服务 找出最早标识
        List<RoutingFinderResp> earlyList = routingFinderPostReq.isNeedEarlyFlag()? getNeedEarlyList(services,sortServicesList): sortServicesList;
        //总的排序
        sortData(earlyList,routingFinderPostReq.getSortDateType());
        //再获取直达过滤后的list
        List<RoutingFinderResp> retList = routingFinderPostReq.isNeedDirectFlag()? getNeedDirectList(earlyList): earlyList;
        return retList;
    }

    //获取需要排序的方案list
    public List<RoutingFinderResp> getNeedSolutionList(RoutingFinderPostReq routingFinderPostReq){
        List<RoutingFinderResp> needSolutionList = new ArrayList<>();
        List<String> services = routingFinderPostReq.getSortSolutionServices();
        List<RoutingFinderResp> routings = routingFinderPostReq.getRoutings();
        for (RoutingFinderResp r :routings) {
            if(services.contains(r.getService())){
                needSolutionList.add(r);
            }
        }
        return needSolutionList;
    }

    //获取需要排序的直达list
    public List<RoutingFinderResp> getNeedDirectList(List<RoutingFinderResp> earlyList){
        List<RoutingFinderResp> retList = new ArrayList<>();
        for (RoutingFinderResp r :earlyList) {
            if(r.isDirectFlag()){
                retList.add(r);
            }
        }
        return retList;
    }

    //顺序设置
    public List<RoutingFinderResp> setRoutingOrder(List<RoutingFinderResp> needDirectList){
        for (int i = 0; i < needDirectList.size() ;i++) {
            needDirectList.get(i).setOrder(i);
        }
        return needDirectList;
    }

    //根据服务code 为list找出最早到港标识
    public List<RoutingFinderResp> getNeedEarlyList(List<String> services,List<RoutingFinderResp> sortServicesList){
        //获取哪些服务
        services.stream().forEach(serv -> log.info("get-all-services::"+serv));
        //再获取serList进行排序
        List<RoutingFinderResp> earlyList = new ArrayList();
        for (int i = 0; i < services.size(); i++) {
            String service = services.get(i);
            List<RoutingFinderResp> serEarlyList = new ArrayList();
            for (RoutingFinderResp r : sortServicesList) {
                if(service.equals(r.getService())){
                    serEarlyList.add(r);
                }
            }
            //排序到港，找到第一个
            for (RoutingFinderResp r : serEarlyList) {
                if(r.isArrivalDateFlag()){
                    earlyList.add(r);
                }
            }
        }
        return earlyList;
    }

    public Set getKeys(List<Map> solutionNos){
        Set<Integer> keys = new HashSet<>();
        for (Map map :solutionNos) {
            keys.addAll(map.keySet());
        }
        return keys;
    }

    //根据routingDetai下面的tran 获取server Code
    public String getServices(List<RoutingFinderResp.RoutingDetail> routingDetails){
        String serv = "";
        for (RoutingFinderResp.RoutingDetail rd : routingDetails) {
            RoutingFinderResp.Voyage voyage = rd.getTransportation().getVoyage();
            if(null != voyage) {
                String serviceCode = voyage.getService().getCode();
                Object serNameObj = ServiceNameMap.map.get(serviceCode);
                String serName = null == serNameObj ? serviceCode : serNameObj.toString();
                if(!StrUtils.isBlank(serName)) {
                    serv += serName + "/";
                }
            }
        }
        return StrUtils.isBlank(serv)?"":serv.substring(0,serv.length()-1);
    }

    public boolean getAgainReq(List<RoutingFinderResp> list){
        for (RoutingFinderResp r : list){
            if("0002".equals(r.getShippingCompany()) || "0011".equals(r.getShippingCompany())  || "0015".equals(r.getShippingCompany())){
                return true;
            }
        }
        return false;
    }

    public List<RoutingFinderResp> getSortServicesList(List<RoutingFinderResp> needSolutionList,RoutingFinderPostReq routingFinderPostReq){
        List<RoutingFinderResp> sortServicesList = new ArrayList<>();
        List<String> services = routingFinderPostReq.getSortSolutionServices();
        int sortDateType = routingFinderPostReq.getSortDateType();
        List<RoutingFinderResp> routings = needSolutionList;
        for (int i = 0; i < services.size(); i++) {
            String service = services.get(i);
            List<RoutingFinderResp> servList = new ArrayList<>();
            for (RoutingFinderResp r :routings) {
                if(service.equals(r.getService())){
                    servList.add(r);
                }
            }
            //最早离港标签
            SortUtil.listSortDeparture(servList);
            servList.get(0).setDepartureDateFlag(true);
            //最早到港标签
            SortUtil.listSortArrival(servList);
            servList.get(0).setArrivalDateFlag(true);
            //放入总list
            sortServicesList.addAll(servList);
        }
        return sortServicesList;
    }

    public void sortData(List<RoutingFinderResp> earlyList,int sortDateType){
        if(1 == sortDateType){
            //排序离港日期
            SortUtil.listSortDeparture(earlyList);
        }else if(2 == sortDateType){
            //排序到港日期
            SortUtil.listSortArrival(earlyList);
        }else {
            //排序运输时间
            SortUtil.listSortTrans(earlyList);
        }
    }

}
