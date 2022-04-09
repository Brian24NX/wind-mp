package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.sechedule.RoutingFinderPostReq;
import com.iss.wind.client.dto.sechedule.RoutingFinderResp;

import java.util.*;
import java.util.stream.Collectors;

import com.iss.wind.client.util.ServiceNameMap;
import com.iss.wind.client.util.SortUtil;
import com.iss.wind.client.util.rest.RestTemplateLogInterceptor;
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
        String url = digitalApiUrl + "/vesseloperation/route/v2/routings?placeOfLoading={placeOfLoading}&placeOfDischarge={placeOfDischarge}";
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
        return response.getBody();
    }

    //方案list和是否直达
    public Map listRoutings(List<RoutingFinderResp> list,String placeOfLoading,String placeOfDischarge,String departureDate,String arrivalDate,String searchRange){
        Map ret = new HashMap();
        if(CollectionUtils.isEmpty(list)){
            return ret;
        }
        //遍历获取方案种类及是否直达
        int cncCount = 0;
        int anlCount = 0;
        int aplCount = 0;
        List<Map> solutionNos = new ArrayList<>();
        for (RoutingFinderResp r : list){
            List<RoutingFinderResp.RoutingDetail> routingDetails = r.getRoutingDetails();
            Set keys = getKeys(solutionNos);
            if(!keys.contains(r.getSolutionNo())){
                Map m = new HashMap();
                m.put(r.getSolutionNo(),routingDetails.get(0).getTransportation().getVoyage().getService().getCode());
                solutionNos.add(m);
            }
            if(r.getShippingCompany() == "0011" ){
                cncCount ++;
            }
            if(r.getShippingCompany() == "0002" ){
                anlCount ++;
            }
            if(r.getShippingCompany() == "0015" ){
                aplCount ++;
            }
            r.setDirectFlag(1 == routingDetails.size()? true:false);
            //起运港
            r.setPointfrom(routingDetails.get(0).getPointFrom().getLocation().getName());
            //起运时间
            r.setDeparturedate(routingDetails.get(0).getPointFrom().getDepartureDateLocal());
            //目的港
            r.setPointto(1 == routingDetails.size()? routingDetails.get(0).getPointTo().getLocation().getName():routingDetails.get(routingDetails.size()-1).getPointTo().getLocation().getName());
            //到达时间
            r.setArrivaldate(1 == routingDetails.size()? routingDetails.get(0).getPointTo().getArrivalDateLocal():routingDetails.get(routingDetails.size()-1).getPointTo().getArrivalDateLocal());
            //转成次数
            r.setTranshipment(routingDetails.size()-1);
            //第一艘船名
            r.setShipname(routingDetails.get(0).getTransportation().getVehicule().getVehiculeName());
            //多个服务用
            r.setService(getServices(routingDetails));
        }
        //设置原顺序
        setRoutingOrder(list);
        //最早离港
        getEarlyDepartureList(list);
        //最早到港
        getEarlyArrivalList(list);
        //是否再调一次【//默认只有0001，不需要再调一次；若有0002或0011或0015 则需再调一次接口】
        ret.put("againReq",getAgainReq(list));
        ret.put("solutionNos",solutionNos);
        ret.put("routings",list);
        //给前端做缓存
        ret.put("placeOfLoading",placeOfLoading);
        ret.put("placeOfDischarge",placeOfDischarge);
        ret.put("departureDate",departureDate);
        ret.put("arrivalDate",arrivalDate);
        ret.put("searchRange",searchRange);
        ret.put("cnc",cncCount);//0011 - CNC
        ret.put("anl",anlCount);//0002 - ANL
        ret.put("apl",aplCount);//0015 - APL

        return ret;
    }


    //根据：方案->直达->排序->最早到达 的顺序
    public List<RoutingFinderResp> routingSort(RoutingFinderPostReq routingFinderPostReq){
        //先根据方案筛选出list
        List<RoutingFinderResp> needSolutionList = CollectionUtils.isEmpty(routingFinderPostReq.getSortSolutionNos())? routingFinderPostReq.getRoutings():getNeedSolutionList(routingFinderPostReq);
        //再获取直达过滤后的list
        List<RoutingFinderResp> needDirectList = routingFinderPostReq.isNeedDirectFlag()? getNeedDirectList(needSolutionList): needSolutionList;
        //再进行排序（升序）
        if(1 ==  routingFinderPostReq.getSortDateType()){
            //排序离港日期
            SortUtil.listSortDeparture(needDirectList);
        }else if(2 ==  routingFinderPostReq.getSortDateType()){
            //排序到港日期
            SortUtil.listSortArrival(needDirectList);
        }else {
            //排序运输时间
            SortUtil.listSortTrans(needDirectList);
        }
        //找出最早标识之前要跟据排好的顺序设置一下
        setRoutingOrder(needDirectList);
        //再是否找出最早标识
        List<RoutingFinderResp> retList = routingFinderPostReq.isNeedEarlyFlag()? getNeedEarlyList(needDirectList,routingFinderPostReq.getSortDateType()): needDirectList;
        return retList;
    }

    //获取需要排序的方案list
    public List<RoutingFinderResp> getNeedSolutionList(RoutingFinderPostReq routingFinderPostReq){
        List<RoutingFinderResp> needSolutionList = new ArrayList<>();
        List<Integer> sortSolutionNos = routingFinderPostReq.getSortSolutionNos();
        List<RoutingFinderResp> routings = routingFinderPostReq.getRoutings();
        for (RoutingFinderResp r :routings) {
            if(sortSolutionNos.contains(r.getSolutionNo())){
                needSolutionList.add(r);
            }
        }
        return needSolutionList;
    }

    //获取需要排序的直达list
    public List<RoutingFinderResp> getNeedDirectList(List<RoutingFinderResp> needSolutionList){
        List<RoutingFinderResp> needDirectList = new ArrayList<>();
        for (RoutingFinderResp r :needSolutionList) {
            if(r.isDirectFlag()){
                needDirectList.add(r);
            }
        }
        return needDirectList;
    }

    //顺序设置
    public List<RoutingFinderResp> setRoutingOrder(List<RoutingFinderResp> needDirectList){
        for (int i = 0; i < needDirectList.size() ;i++) {
            needDirectList.get(i).setOrder(i+1);
        }
        return needDirectList;
    }

    //为list找出最早到港标识
    public List<RoutingFinderResp> getNeedEarlyList(List<RoutingFinderResp> needDirectList,int sortDateType){
        //若是已经经历 到港日期排序，则第一个就是最早到港
        if (2 == sortDateType){
            needDirectList.get(0).setEarlyFlag(true);
            return needDirectList;
        }
        //其他的类型，再次进行到港日期排序后设置
        List<RoutingFinderResp> sortArriList = needDirectList;
        SortUtil.listSortArrival(sortArriList);
        //设置最早标识
        needDirectList.get(sortArriList.get(0).getOrder()).setEarlyFlag(true);
        return needDirectList;
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
                serv += ServiceNameMap.map.get(voyage.getService().getCode()) + "/";
            }
        }
        return serv.substring(0,serv.length()-1);
    }

    //最早离港
    public void getEarlyDepartureList(List<RoutingFinderResp> needList){
        List<RoutingFinderResp> sortArriList = needList;
        SortUtil.listSortDeparture(sortArriList);
        //设置最早标识
        needList.get(sortArriList.get(0).getOrder()).setDepartureDateFlag(true);
    }

    //最早到港
    public void getEarlyArrivalList(List<RoutingFinderResp> needList){
        List<RoutingFinderResp> sortArriList = needList;
        SortUtil.listSortArrival(sortArriList);
        //设置最早标识
        needList.get(sortArriList.get(0).getOrder()).setArrivalDateFlag(true);
    }

    public boolean getAgainReq(List<RoutingFinderResp> list){
        for (RoutingFinderResp r : list){
            if(r.getShippingCompany() == "0002" || r.getShippingCompany() == "0011" || r.getShippingCompany() == "0015"){
                return true;
            }
        }
        return false;
    }
}
