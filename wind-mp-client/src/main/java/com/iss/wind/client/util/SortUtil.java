package com.iss.wind.client.util;

import com.iss.wind.client.dto.sechedule.RoutingFinderResp;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Slf4j
public class SortUtil {

    public static final String rfs = "[\n" +
            "\t{\n" +
            "\t\t\"shippingCompany\": \"0001\",\n" +
            "\t\t\"solutionUid\": null,\n" +
            "\t\t\"solutionNo\": 1,\n" +
            "\t\t\"transitTime\": 38,\n" +
            "\t\t\"specificRoutings\": null,\n" +
            "\t\t\"routingDetails\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"pointFrom\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"SHANGHAI\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"CNSHA\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"CNSGH\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"CNSHADYAN\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"CNSGHSHENG\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"YANGSHAN DEEP WATER PORT PHASE1 TER\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50001882407\",\n" +
            "\t\t\t\t\t\"departureDateLocal\": \"2022-04-12T02:34:00+08:00\",\n" +
            "\t\t\t\t\t\"departureDateGmt\": \"2022-04-11T18:34:00Z\",\n" +
            "\t\t\t\t\t\"portCutoffDate\": \"2022-04-05T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\"portCutoffDateGmt\": \"2022-04-05T05:00:00Z\",\n" +
            "\t\t\t\t\t\"vgmCutoffDate\": \"2022-03-22T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\"vgmCutoffDateGmt\": \"2022-03-22T00:35:00Z\",\n" +
            "\t\t\t\t\t\"customsCutoffDate\": \"2022-03-22T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\"customsCutoffDateGmt\": \"2022-03-22T05:00:00Z\",\n" +
            "\t\t\t\t\t\"cutOff\": {\n" +
            "\t\t\t\t\t\t\"portCutoff\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-05T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-05T05:00:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"vgm\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-03-22T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-03-22T00:35:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"standardBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-12T17:25:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-12T09:25:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"specialBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-05T15:20:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-05T07:20:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"shippingInstructionAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-09T21:34:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-09T13:34:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"customsAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-03-22T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-03-22T05:00:00Z\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"pointTo\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"ROTTERDAM\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"NLRTM\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"NLRTM\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"NLRTMDRWG\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"NLRTMRWG\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"ROTTERDAM WORLD GATEWAY TERMINAL\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50002049627\",\n" +
            "\t\t\t\t\t\"arrivalDateLocal\": \"2022-05-12T15:00:00+02:00\",\n" +
            "\t\t\t\t\t\"arrivalDateGmt\": \"2022-05-12T13:00:00Z\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"transportation\": {\n" +
            "\t\t\t\t\t\"transportationPhase\": null,\n" +
            "\t\t\t\t\t\"meanOfTransport\": \"Vessel\",\n" +
            "\t\t\t\t\t\"vehicule\": {\n" +
            "\t\t\t\t\t\t\"vehiculeType\": \"Vessel\",\n" +
            "\t\t\t\t\t\t\"vehiculeName\": \"CMA CGM ARGENTINA\",\n" +
            "\t\t\t\t\t\t\"reference\": \"9839909\",\n" +
            "\t\t\t\t\t\t\"referenceType\": \"IMO\",\n" +
            "\t\t\t\t\t\t\"internalReference\": \"CGARG\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"voyage\": {\n" +
            "\t\t\t\t\t\t\"voyageReference\": \"0FLAXW1MA\",\n" +
            "\t\t\t\t\t\t\"service\": {\n" +
            "\t\t\t\t\t\t\t\"code\": \"FAL\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"FAL\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"legTransitTime\": 30\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"shippingCompany\": \"0001\",\n" +
            "\t\t\"solutionUid\": null,\n" +
            "\t\t\"solutionNo\": 2,\n" +
            "\t\t\"transitTime\": 35,\n" +
            "\t\t\"specificRoutings\": null,\n" +
            "\t\t\"routingDetails\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"pointFrom\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"SHANGHAI\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"CNSHA\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"CNSGH\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"CNSHADYAN\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"CNSGHSHENG\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"YANGSHAN DEEP WATER PORT PHASE1 TER\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50001989034\",\n" +
            "\t\t\t\t\t\"departureDateLocal\": \"2022-04-07T10:00:00+08:00\",\n" +
            "\t\t\t\t\t\"departureDateGmt\": \"2022-04-07T02:00:00Z\",\n" +
            "\t\t\t\t\t\"portCutoffDate\": \"2022-04-04T15:00:00+08:00\",\n" +
            "\t\t\t\t\t\"portCutoffDateGmt\": \"2022-04-04T07:00:00Z\",\n" +
            "\t\t\t\t\t\"vgmCutoffDate\": \"2022-03-21T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\"vgmCutoffDateGmt\": \"2022-03-21T00:35:00Z\",\n" +
            "\t\t\t\t\t\"customsCutoffDate\": \"2022-03-21T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\"customsCutoffDateGmt\": \"2022-03-21T05:00:00Z\",\n" +
            "\t\t\t\t\t\"cutOff\": {\n" +
            "\t\t\t\t\t\t\"portCutoff\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-04T15:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-04T07:00:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"vgm\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-03-21T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-03-21T00:35:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"standardBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-05T17:25:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-05T09:25:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"specialBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-05T15:20:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-05T07:20:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"shippingInstructionAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-04T17:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-04T09:00:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"customsAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-03-21T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-03-21T05:00:00Z\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"pointTo\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"ROTTERDAM\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"NLRTM\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"NLRTM\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"NLRTMDEMX\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"NLRTMEMX\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"ECT EUROMAX ROTTERDAM\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50001989042\",\n" +
            "\t\t\t\t\t\"arrivalDateLocal\": \"2022-05-12T11:00:00+02:00\",\n" +
            "\t\t\t\t\t\"arrivalDateGmt\": \"2022-05-12T09:00:00Z\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"transportation\": {\n" +
            "\t\t\t\t\t\"transportationPhase\": null,\n" +
            "\t\t\t\t\t\"meanOfTransport\": \"Vessel\",\n" +
            "\t\t\t\t\t\"vehicule\": {\n" +
            "\t\t\t\t\t\t\"vehiculeType\": \"Vessel\",\n" +
            "\t\t\t\t\t\t\"vehiculeName\": \"TEXAS TRIUMPH\",\n" +
            "\t\t\t\t\t\t\"reference\": \"9737503\",\n" +
            "\t\t\t\t\t\t\"referenceType\": \"IMO\",\n" +
            "\t\t\t\t\t\t\"internalReference\": \"TEXTP\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"voyage\": {\n" +
            "\t\t\t\t\t\t\"voyageReference\": \"0SC8RW1MA\",\n" +
            "\t\t\t\t\t\t\"service\": {\n" +
            "\t\t\t\t\t\t\t\"code\": \"FAL8\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"FAL8\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"legTransitTime\": 35\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"shippingCompany\": \"0001\",\n" +
            "\t\t\"solutionUid\": null,\n" +
            "\t\t\"solutionNo\": 2,\n" +
            "\t\t\"transitTime\": 35,\n" +
            "\t\t\"specificRoutings\": null,\n" +
            "\t\t\"routingDetails\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"pointFrom\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"SHANGHAI\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"CNSHA\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"CNSGH\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"CNSHADYAN\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"CNSGHSHENG\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"YANGSHAN DEEP WATER PORT PHASE1 TER\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50001989048\",\n" +
            "\t\t\t\t\t\"departureDateLocal\": \"2022-04-14T10:00:00+08:00\",\n" +
            "\t\t\t\t\t\"departureDateGmt\": \"2022-04-14T02:00:00Z\",\n" +
            "\t\t\t\t\t\"portCutoffDate\": \"2022-04-11T15:00:00+08:00\",\n" +
            "\t\t\t\t\t\"portCutoffDateGmt\": \"2022-04-11T07:00:00Z\",\n" +
            "\t\t\t\t\t\"vgmCutoffDate\": \"2022-03-28T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\"vgmCutoffDateGmt\": \"2022-03-28T00:35:00Z\",\n" +
            "\t\t\t\t\t\"customsCutoffDate\": \"2022-03-28T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\"customsCutoffDateGmt\": \"2022-03-28T05:00:00Z\",\n" +
            "\t\t\t\t\t\"cutOff\": {\n" +
            "\t\t\t\t\t\t\"portCutoff\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-11T15:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-11T07:00:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"vgm\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-03-28T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-03-28T00:35:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"standardBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-12T17:25:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-12T09:25:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"specialBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-12T15:20:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-12T07:20:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"shippingInstructionAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-11T17:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-11T09:00:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"customsAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-03-28T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-03-28T05:00:00Z\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"pointTo\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"ROTTERDAM\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"NLRTM\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"NLRTM\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"NLRTMDEMX\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"NLRTMEMX\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"ECT EUROMAX ROTTERDAM\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50001989056\",\n" +
            "\t\t\t\t\t\"arrivalDateLocal\": \"2022-05-19T11:00:00+02:00\",\n" +
            "\t\t\t\t\t\"arrivalDateGmt\": \"2022-05-19T09:00:00Z\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"transportation\": {\n" +
            "\t\t\t\t\t\"transportationPhase\": null,\n" +
            "\t\t\t\t\t\"meanOfTransport\": \"Vessel\",\n" +
            "\t\t\t\t\t\"vehicule\": {\n" +
            "\t\t\t\t\t\t\"vehiculeType\": \"Vessel\",\n" +
            "\t\t\t\t\t\t\"vehiculeName\": \"THALASSA PATRIS\",\n" +
            "\t\t\t\t\t\t\"reference\": \"9665607\",\n" +
            "\t\t\t\t\t\t\"referenceType\": \"IMO\",\n" +
            "\t\t\t\t\t\t\"internalReference\": \"THPAT\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"voyage\": {\n" +
            "\t\t\t\t\t\t\"voyageReference\": \"0SC8TW1MA\",\n" +
            "\t\t\t\t\t\t\"service\": {\n" +
            "\t\t\t\t\t\t\t\"code\": \"FAL8\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"FAL8\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"legTransitTime\": 35\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"shippingCompany\": \"0001\",\n" +
            "\t\t\"solutionUid\": null,\n" +
            "\t\t\"solutionNo\": 2,\n" +
            "\t\t\"transitTime\": 35,\n" +
            "\t\t\"specificRoutings\": null,\n" +
            "\t\t\"routingDetails\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"pointFrom\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"SHANGHAI\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"CNSHA\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"CNSGH\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"CNSHADYAN\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"CNSGHSHENG\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"YANGSHAN DEEP WATER PORT PHASE1 TER\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50001989062\",\n" +
            "\t\t\t\t\t\"departureDateLocal\": \"2022-04-21T10:00:00+08:00\",\n" +
            "\t\t\t\t\t\"departureDateGmt\": \"2022-04-21T02:00:00Z\",\n" +
            "\t\t\t\t\t\"portCutoffDate\": \"2022-04-18T15:00:00+08:00\",\n" +
            "\t\t\t\t\t\"portCutoffDateGmt\": \"2022-04-18T07:00:00Z\",\n" +
            "\t\t\t\t\t\"vgmCutoffDate\": \"2022-04-04T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\"vgmCutoffDateGmt\": \"2022-04-04T00:35:00Z\",\n" +
            "\t\t\t\t\t\"customsCutoffDate\": \"2022-04-04T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\"customsCutoffDateGmt\": \"2022-04-04T05:00:00Z\",\n" +
            "\t\t\t\t\t\"cutOff\": {\n" +
            "\t\t\t\t\t\t\"portCutoff\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-18T15:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-18T07:00:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"vgm\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-04T08:35:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-04T00:35:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"standardBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-19T17:25:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-19T09:25:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"specialBookingAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-19T15:20:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-19T07:20:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"shippingInstructionAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-18T17:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-18T09:00:00Z\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"customsAcceptance\": {\n" +
            "\t\t\t\t\t\t\t\"local\": \"2022-04-04T13:00:00+08:00\",\n" +
            "\t\t\t\t\t\t\t\"utc\": \"2022-04-04T05:00:00Z\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"pointTo\": {\n" +
            "\t\t\t\t\t\"location\": {\n" +
            "\t\t\t\t\t\t\"name\": \"ROTTERDAM\",\n" +
            "\t\t\t\t\t\t\"internalCode\": \"NLRTM\",\n" +
            "\t\t\t\t\t\t\"locationCodifications\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"codificationType\": \"UN/Locode\",\n" +
            "\t\t\t\t\t\t\t\t\"codification\": \"NLRTM\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"facility\": {\n" +
            "\t\t\t\t\t\t\t\"facilityType\": \"Shipping Terminal\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"NLRTMDEMX\",\n" +
            "\t\t\t\t\t\t\t\"facilityCodifications\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"codificationType\": \"SMDG\",\n" +
            "\t\t\t\t\t\t\t\t\t\"codification\": \"NLRTMEMX\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\"name\": \"ECT EUROMAX ROTTERDAM\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"callId\": \"50001989070\",\n" +
            "\t\t\t\t\t\"arrivalDateLocal\": \"2022-05-26T11:00:00+02:00\",\n" +
            "\t\t\t\t\t\"arrivalDateGmt\": \"2022-05-26T09:00:00Z\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"transportation\": {\n" +
            "\t\t\t\t\t\"transportationPhase\": null,\n" +
            "\t\t\t\t\t\"meanOfTransport\": \"Vessel\",\n" +
            "\t\t\t\t\t\"vehicule\": {\n" +
            "\t\t\t\t\t\t\"vehiculeType\": \"Vessel\",\n" +
            "\t\t\t\t\t\t\"vehiculeName\": \"EMC TBN 36\",\n" +
            "\t\t\t\t\t\t\"reference\": null,\n" +
            "\t\t\t\t\t\t\"referenceType\": \"IMO\",\n" +
            "\t\t\t\t\t\t\"internalReference\": \"EMC36\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"voyage\": {\n" +
            "\t\t\t\t\t\t\"voyageReference\": \"0SC8VW1MA\",\n" +
            "\t\t\t\t\t\t\"service\": {\n" +
            "\t\t\t\t\t\t\t\"code\": \"FAL8\",\n" +
            "\t\t\t\t\t\t\t\"internalCode\": \"FAL8\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"legTransitTime\": 35\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "]";


    public static void srot() throws ParseException {
//        JSONArray objArr = JSONObject.parseArray(rfs);
//        List<RoutingFinderResp> list = JSONObject.parseArray(objArr.toJSONString(), RoutingFinderResp.class);
//        listSortDeparture(list);
//        for (RoutingFinderResp r:list) {
//            log.info(r.getRoutingDetails().get(0).getPointFrom().getDepartureDateLocal()+"");
//        }
//
//        listSortTrans(list);
//        for (RoutingFinderResp r:list) {
//            log.info(r.getTransitTime()+"");
//        }
    }

    //离港日期排序-departureDateLocal
    public static void listSortDeparture(List<RoutingFinderResp> list) {
        {    //排序方法
            Collections.sort(list, new Comparator<RoutingFinderResp>() {
                @Override
                public int compare(RoutingFinderResp o1, RoutingFinderResp o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    try {
                        // format.format(o1.getTime()) 表示 date转string类型 如果是string类型就不要转换了
                        Date dt1 = transDate(o1.getDeparturedate());
                        Date dt2 = transDate(o2.getDeparturedate());
                        log.info(dt1+":"+dt2);
                        // 这是由小向大排序   如果要由小向大转换比较符号就可以
                        if (dt1.getTime() > dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() < dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }

            });
        }
    }

    //到港日期排序-arrivalDateLocal
    public static void listSortArrival(List<RoutingFinderResp> list) {
        {    //排序方法
            Collections.sort(list, new Comparator<RoutingFinderResp>() {
                @Override
                public int compare(RoutingFinderResp o1, RoutingFinderResp o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    try {
                        // format.format(o1.getTime()) 表示 date转string类型 如果是string类型就不要转换了
                        Date dt1 = transDate(o1.getArrivaldate());
                        Date dt2 = transDate(o2.getArrivaldate());
                        log.info(dt1+":"+dt2);
                        // 这是由小向大排序   如果要由小向大转换比较符号就可以
                        if (dt1.getTime() > dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() < dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }

            });
        }
    }

    //运输时间排序-transitTime
    public static void listSortTrans(List<RoutingFinderResp> list){
        Collections.sort(list, new Comparator<RoutingFinderResp>() {
            @Override
            public int compare(RoutingFinderResp o1, RoutingFinderResp o2) {
                //升序
                return o1.getTransitTime().compareTo(o2.getTransitTime());
            }
        });
    }

    public static Date transDate(String str) throws ParseException {
//        String str = "2022-02-13T11:00:00+01:00";
        String[] strArr = str.split("\\+");
        String strDate = strArr[0];
        int strZone = Integer.parseInt(strArr[1].substring(0,2));
        //1. Create a Date from String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = sdf.parse(strDate);
        //2. Test - Convert Date to Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, +strZone);
        Date newDate = calendar.getTime();
        return newDate;
    }

}
