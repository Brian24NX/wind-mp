package com.iss.wind.serevice.export;

import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.common.enums.StatusEnum;
import com.iss.wind.serevice.util.DateForPDFUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class TrackingExportService {

    private int currentDate;

    public HashMap<String, Object> getColumnHeader(ShipmentTrackingResp trackingResp) throws Exception {
        Date date = new Date();
        HashMap<String, Object> map = new HashMap<>();
        ShipmentTrackingResp.Container container = trackingResp.getRoutes().get(0).getContainers().get(0);
        List<ShipmentTrackingResp.Movement> movements = container.getMovements();
        List<ShipmentTrackingResp.JourneyLeg> journeyLegs = trackingResp.getRoutes().get(0).getJourneyLegs();
        InputStream fis = this.getClass().getResourceAsStream("/pdfTemplate/wind.png");
        ShipmentTrackingResp.Status status = movements.get(movements.size() - currentDate).getStatus();
        this.currentDate = 0;
        map.put("Logo", fis);
        if (journeyLegs != null && journeyLegs.size() != 0) {
            map.put("etaLastPortDate", journeyLegs.get(0).getCollectionDate());
        } else {
            map.put("etaLastPortDate", "");
        }
        String statusLoName = StatusEnum.getLoNameByCode(status.getCode(), status.getName());
        map.put("movementNewStatusName", statusLoName);
        map.put("curreDate", DateForPDFUtil.currentDate(date));
        map.put("footerDate", DateForPDFUtil.footerDate(date));
        map.put("containerId", container.getId());
        map.put("portOfLoadingName", trackingResp.getPortOfLoading().getName() + "(" + trackingResp.getPortOfLoadingCountryCode() + ")");
        map.put("customNum", "N/A");
        map.put("portOfDischargeName", trackingResp.getPortOfDischarge().getName() + "(" + trackingResp.getPortOfDischargeCountryCode() + ")");
        map.put("containerSize", Integer.toString(container.getSize()));
        map.put("containerType", container.getType());
        return map;
    }

    public ArrayList<Map<String, Object>> getDetails(ShipmentTrackingResp trackingResp) {
        List<ShipmentTrackingResp.Movement> movements = trackingResp.getRoutes().get(0).getContainers().get(0).getMovements();
        ArrayList<Map<String, Object>> Lists = new ArrayList<>();

        for (int i = 0; i < movements.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            ShipmentTrackingResp.Movement movement = movements.get(movements.size() - 1 - i);
            String movementDate = DateForPDFUtil.transDate(movement.getDate());
            ShipmentTrackingResp.Status status = movement.getStatus();
            String pointLocationName = movement.getPointLocation().getName();
            String movementVesselName = "";
            if (movement.getVessel() != null) {
                movementVesselName = movement.getVessel().getName();
            }
            if (DateForPDFUtil.isPastDate(movement.getDate())) {
                currentDate++;
            }
            String statusUpname = StatusEnum.getUpNameByCode(status.getCode(), status.getName());
            String movementVoyageReference = movement.getVoyageReference();
            map.put("movementDate", movementDate);
            map.put("movementStatusName", statusUpname);
            map.put("pointLocationName", pointLocationName);
            map.put("movementVesselName", movementVesselName);
            map.put("movementVoyageReference", movementVoyageReference);
            Lists.add(map);
        }
        int pastDate = currentDate;
        for (Map<String, Object> map : Lists) {
            if (pastDate > 1) {
                map.put("FlagDate", "1");
                pastDate--;
            } else if (pastDate == 1) {
                map.put("FlagDate", "2");

                pastDate--;
            } else {
                map.put("FlagDate", "3");
            }
        }
        return Lists;
    }
}