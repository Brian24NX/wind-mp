package com.iss.wind.wx.miniapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iss.wind.client.ShipmentTrackingClient;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingReq;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.common.util.log.WebLog;
import com.iss.wind.serevice.util.PDFUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 测试下载PDF Controller
 */
@Api(value = "pdf模板导出")
@Slf4j
@RestController
public class DownloadPdfController {

    /**
     * 下载PDF
     * @param response
     */
    @GetMapping("/downloadPdf")
//    @ApiOperation(value = "pdf模板导出",notes = "pdf模板导出")
//    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
//    @WebLog(description = "downloadPdf")
    public void download(HttpServletResponse response){
//        String ship = "{\n" +
//                "    \"code\": \"200\",\n" +
//                "    \"message\": \"操作成功\",\n" +
//                "    \"data\": {\n" +
//                "        \"portOfLoading\": {\n" +
//                "            \"code\": \"FRLEH\",\n" +
//                "            \"name\": \"LE HAVRE\",\n" +
//                "            \"unLocode\": \"FRLEH\"\n" +
//                "        },\n" +
//                "        \"portOfLoadingCountryCode\": \"FR\",\n" +
//                "        \"portOfDischarge\": {\n" +
//                "            \"code\": \"DEHAM\",\n" +
//                "            \"name\": \"HAMBURG\",\n" +
//                "            \"unLocode\": \"DEHAM\"\n" +
//                "        },\n" +
//                "        \"portOfDischargeCountryCode\": \"DE\",\n" +
//                "        \"voyageReference\": \"BLS1028\",\n" +
//                "        \"nbUnits\": 0,\n" +
//                "        \"routes\": [\n" +
//                "            {\n" +
//                "                \"journeyLegs\": [\n" +
//                "                    {\n" +
//                "                        \"sequenceNumber\": 1,\n" +
//                "                        \"pointFrom\": {\n" +
//                "                            \"code\": \"FRLEH\",\n" +
//                "                            \"name\": \"LE HAVRE\",\n" +
//                "                            \"unLocode\": \"FRLEH\"\n" +
//                "                        },\n" +
//                "                        \"vesselFrom\": {\n" +
//                "                            \"name\": \"RegTest Vsl No18\"\n" +
//                "                        },\n" +
//                "                        \"vesselTo\": {\n" +
//                "                            \"code\": \"CGM18\",\n" +
//                "                            \"name\": \"RegTest Vsl No18\"\n" +
//                "                        },\n" +
//                "                        \"pointTo\": {\n" +
//                "                            \"code\": \"DEHAM\",\n" +
//                "                            \"name\": \"HAMBURG\",\n" +
//                "                            \"unLocode\": \"DEHAM\"\n" +
//                "                        },\n" +
//                "                        \"poolLocationFromCode\": \"FRLEHD21\",\n" +
//                "                        \"facilityFrom\": {\n" +
//                "                            \"facilityType\": \"Shipping Terminal\",\n" +
//                "                            \"internalCode\": \"FRLEHD21\",\n" +
//                "                            \"facilityCodifications\": [\n" +
//                "                                {\n" +
//                "                                    \"codificationType\": \"SMDG\",\n" +
//                "                                    \"codification\": \"FRLEHQATL\"\n" +
//                "                                }\n" +
//                "                            ],\n" +
//                "                            \"name\": \"TERMINAL DE L'ATLANTIQUE\"\n" +
//                "                        },\n" +
//                "                        \"poolLocationToCode\": \"DEHAMDBMW\",\n" +
//                "                        \"facilityTo\": {\n" +
//                "                            \"facilityType\": \"Shipper Pool\",\n" +
//                "                            \"internalCode\": \"DEHAMDBMW\",\n" +
//                "                            \"facilityCodifications\": [],\n" +
//                "                            \"name\": \"UNITS EX BMW\"\n" +
//                "                        },\n" +
//                "                        \"collectionDate\": \"2022-02-11T14:00:00+01:00\",\n" +
//                "                        \"voyageReference\": \"BLS1028\",\n" +
//                "                        \"dischargeVoyageReference\": \"BLS1028\",\n" +
//                "                        \"deliveryDate\": \"2022-02-12T14:00:00+01:00\",\n" +
//                "                        \"shipCompCode\": \"0001\"\n" +
//                "                    }\n" +
//                "                ],\n" +
//                "                \"containers\": [\n" +
//                "                    {\n" +
//                "                        \"id\": \"BLLEVEL0219\",\n" +
//                "                        \"size\": 20,\n" +
//                "                        \"type\": \"ST\",\n" +
//                "                        \"movements\": [\n" +
//                "                            {\n" +
//                "                                \"statusOrder\": \"637803216\",\n" +
//                "                                \"status\": {\n" +
//                "                                    \"code\": \"XOF\",\n" +
//                "                                    \"name\": \"Loaded on Vessel at Port terminal\"\n" +
//                "                                },\n" +
//                "                                \"date\": \"2022-02-13T04:00:00+01:00\",\n" +
//                "                                \"poolLocation\": \"DEHAMDHAM\",\n" +
//                "                                \"facility\": {\n" +
//                "                                    \"facilityType\": \"Container Yard\",\n" +
//                "                                    \"internalCode\": \"DEHAMDHAM\",\n" +
//                "                                    \"facilityCodifications\": [],\n" +
//                "                                    \"name\": \"HAMBURG\"\n" +
//                "                                },\n" +
//                "                                \"pointLocation\": {\n" +
//                "                                    \"code\": \"DEHAM\",\n" +
//                "                                    \"name\": \"HAMBURG\",\n" +
//                "                                    \"unLocode\": \"DEHAM\"\n" +
//                "                                },\n" +
//                "                                \"voyageReference\": \"BLS1028\",\n" +
//                "                                \"vessel\": {\n" +
//                "                                    \"code\": \"CGM18\",\n" +
//                "                                    \"name\": \"RegTest Vsl No18\"\n" +
//                "                                },\n" +
//                "                                \"pointOfDischarge\": \"DEHAM\",\n" +
//                "                                \"portOfLoading\": \"FRLEH\",\n" +
//                "                                \"finalPod\": \"DEHAM\",\n" +
//                "                                \"countryCode\": \"DE\",\n" +
//                "                                \"shipCompCode\": \"0001\",\n" +
//                "                                \"voyageShipCompCode\": \"0001\"\n" +
//                "                            },\n" +
//                "                            {\n" +
//                "                                \"statusOrder\": \"637803180\",\n" +
//                "                                \"status\": {\n" +
//                "                                    \"code\": \"XRX\",\n" +
//                "                                    \"name\": \"Gate in at Port terminal\"\n" +
//                "                                },\n" +
//                "                                \"date\": \"2022-02-13T03:00:00+01:00\",\n" +
//                "                                \"poolLocation\": \"DEHAMDHAM\",\n" +
//                "                                \"facility\": {\n" +
//                "                                    \"facilityType\": \"Container Yard\",\n" +
//                "                                    \"internalCode\": \"DEHAMDHAM\",\n" +
//                "                                    \"facilityCodifications\": [],\n" +
//                "                                    \"name\": \"HAMBURG\"\n" +
//                "                                },\n" +
//                "                                \"pointLocation\": {\n" +
//                "                                    \"code\": \"DEHAM\",\n" +
//                "                                    \"name\": \"HAMBURG\",\n" +
//                "                                    \"unLocode\": \"DEHAM\"\n" +
//                "                                },\n" +
//                "                                \"voyageReference\": \"BLS1028\",\n" +
//                "                                \"vessel\": {\n" +
//                "                                    \"code\": \"CGM18\",\n" +
//                "                                    \"name\": \"RegTest Vsl No18\"\n" +
//                "                                },\n" +
//                "                                \"pointOfDischarge\": \"DEHAM\",\n" +
//                "                                \"portOfLoading\": \"FRLEH\",\n" +
//                "                                \"finalPod\": \"DEHAM\",\n" +
//                "                                \"countryCode\": \"DE\",\n" +
//                "                                \"shipCompCode\": \"0001\",\n" +
//                "                                \"voyageShipCompCode\": \"0001\"\n" +
//                "                            },\n" +
//                "                            {\n" +
//                "                                \"statusOrder\": \"637803144\",\n" +
//                "                                \"status\": {\n" +
//                "                                    \"code\": \"MOS\",\n" +
//                "                                    \"name\": \"Gate out Empty to Shipper\"\n" +
//                "                                },\n" +
//                "                                \"date\": \"2022-02-13T02:00:00+01:00\",\n" +
//                "                                \"poolLocation\": \"DEHAMDHAM\",\n" +
//                "                                \"facility\": {\n" +
//                "                                    \"facilityType\": \"Container Yard\",\n" +
//                "                                    \"internalCode\": \"DEHAMDHAM\",\n" +
//                "                                    \"facilityCodifications\": [],\n" +
//                "                                    \"name\": \"HAMBURG\"\n" +
//                "                                },\n" +
//                "                                \"pointLocation\": {\n" +
//                "                                    \"code\": \"DEHAM\",\n" +
//                "                                    \"name\": \"HAMBURG\",\n" +
//                "                                    \"unLocode\": \"DEHAM\"\n" +
//                "                                },\n" +
//                "                                \"voyageReference\": \"BLS1028\",\n" +
//                "                                \"vessel\": {\n" +
//                "                                    \"code\": \"CGM18\",\n" +
//                "                                    \"name\": \"RegTest Vsl No18\"\n" +
//                "                                },\n" +
//                "                                \"pointOfDischarge\": \"DEHAM\",\n" +
//                "                                \"portOfLoading\": \"FRLEH\",\n" +
//                "                                \"finalPod\": \"DEHAM\",\n" +
//                "                                \"countryCode\": \"DE\",\n" +
//                "                                \"shipCompCode\": \"0001\",\n" +
//                "                                \"voyageShipCompCode\": \"0001\"\n" +
//                "                            },\n" +
//                "                            {\n" +
//                "                                \"statusOrder\": \"637803108\",\n" +
//                "                                \"status\": {\n" +
//                "                                    \"code\": \"MEA\",\n" +
//                "                                    \"name\": \"Container Empty Returned\"\n" +
//                "                                },\n" +
//                "                                \"date\": \"2022-02-13T01:00:00+01:00\",\n" +
//                "                                \"poolLocation\": \"FRLEHDLEH\",\n" +
//                "                                \"facility\": {\n" +
//                "                                    \"facilityType\": \"Container Yard\",\n" +
//                "                                    \"internalCode\": \"FRLEHDLEH\",\n" +
//                "                                    \"facilityCodifications\": [],\n" +
//                "                                    \"name\": \"LE HAVRE PRINCIPAL\"\n" +
//                "                                },\n" +
//                "                                \"pointLocation\": {\n" +
//                "                                    \"code\": \"FRLEH\",\n" +
//                "                                    \"name\": \"LE HAVRE\",\n" +
//                "                                    \"unLocode\": \"FRLEH\"\n" +
//                "                                },\n" +
//                "                                \"pointOfDischarge\": \"DEHAM\",\n" +
//                "                                \"countryCode\": \"FR\",\n" +
//                "                                \"shipCompCode\": \"0001\"\n" +
//                "                            }\n" +
//                "                        ]\n" +
//                "                    }\n" +
//                "                ]\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    \"success\": true\n" +
//                "}";
//        JSONObject obj = JSON.parseObject(ship);
//        ShipmentTrackingResp shipment = JSONObject.toJavaObject(obj, ShipmentTrackingResp.class);//测试数据

        Map<String, Object> data = buildData(null);
        try(BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setCharacterEncoding(PDFUtils.ENCODING);
            String fileName = URLEncoder.encode("shipment-tracing.pdf", PDFUtils.ENCODING);
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            PDFUtils.createPDF("shipment.ftl", data, out);
        }catch (Exception e){
           log.error("异常：",e);
            throw new RuntimeException("下载PDF方法异常");
        }
    }

    /**
     * 生成数据
     * @return
     */
    private Map<String, Object> buildData(ShipmentTrackingResp shipment){
        Map<String, Object> map = new HashMap<>();
//        ShipmentTrackingResp.Container container = shipment.getRoutes().get(0).getContainers().get(0);
//        List<ShipmentTrackingResp.Movement> movements = container.getMovements();
//        map.put("currDate", new Date());
//        map.put("containerId", container.getId());
//        map.put("movementNewStatusName", movements.get(0).getStatus().getName());
//        map.put("etaLastPortDate", movements.get(0).getDate());
//        map.put("portOfLoadingName", shipment.getPortOfLoading().getName());
//        map.put("custonmNum", "N/A");
//        map.put("portOfDischargeName", shipment.getPortOfDischarge().getName());
//        map.put("containerSize", container.getSize());
//        map.put("containerType", container.getType());
//        List<Map<String, Object>> detailList = new ArrayList<>();
//        for (int i = movements.size()-1; i >= 0 ; i--){
//            ShipmentTrackingResp.Movement movement= movements.get(i);
//            Map<String, Object> mapMove = new HashMap<>();
//            mapMove.put("movementDate", movement.getDate());
//            mapMove.put("movementStatusName", movement.getStatus().getName());
//            mapMove.put("pointLocationName", movement.getPointLocation().getName());
//            mapMove.put("movementVesselName", movement.getVessel().getName());
//            mapMove.put("movementVoyageReference", movement.getVoyageReference());
//            detailList.add(mapMove);
//        }
//        map.put("detailList", detailList);

        map.put("currDate", "2022-3-24");
        map.put("containerId", "WEWCWV123132");
        map.put("movementNewStatusName", "WDWCC");
        map.put("etaLastPortDate", "2022-3-23");
        map.put("portOfLoadingName", "SQWDC");
        map.put("custonmNum", "N/A");
        map.put("portOfDischargeName", "DXDQD");
        map.put("containerSize", "20");
        map.put("containerType", "wwqd");
        List<Map<String, Object>> detailList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("movementDate", "2020-11-14");
        map1.put("movementStatusName", "wqe");
        map1.put("pointLocationName", "w2sw");
        map1.put("movementVesselName", "w2sw");
        map1.put("movementVoyageReference", "w2sw");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("movementDate", "2020-11-13");
        map2.put("movementStatusName", "weqew");
        map2.put("pointLocationName", "ccde");
        map2.put("movementVesselName", "ccde");
        map2.put("movementVoyageReference", "ccde");
        detailList.add(map1);
        detailList.add(map2);
        map.put("detailList", detailList);
        return map;
    }
}
