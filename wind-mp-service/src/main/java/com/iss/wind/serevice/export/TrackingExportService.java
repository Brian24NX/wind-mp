package com.iss.wind.serevice.export;

import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.serevice.util.DateForPDFUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class TrackingExportService {

    public HashMap<String, Object> getColumnHeader(ShipmentTrackingResp trackingResp) throws Exception{
        Date date = new Date();
        HashMap<String, Object> map = new HashMap<>();
        ShipmentTrackingResp.Container container = trackingResp.getRoutes().get(0).getContainers().get(0);
        List<ShipmentTrackingResp.Movement> movements = container.getMovements();
        List<ShipmentTrackingResp.JourneyLeg> journeyLegs = trackingResp.getRoutes().get(0).getJourneyLegs();
        ClassPathResource resource = new ClassPathResource("pdfTemplate/wind.png");
        InputStream fis = new FileInputStream(resource.getFile());
        map.put("Logo",fis);
        if (journeyLegs != null && journeyLegs.size() != 0) {
            map.put("etaLastPortDate", journeyLegs.get(0).getCollectionDate());
        } else {
            map.put("etaLastPortDate", "");
        }
        map.put("curreDate", DateForPDFUtil.currentDate(date));
        map.put("footerDate", DateForPDFUtil.footerDate(date));
        map.put("containerId", container.getId());
        map.put("movementNewStatusName", movements.get(0).getStatus().getName());
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
        int pastDate = 0;
        for (int i = 0; i < movements.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            ShipmentTrackingResp.Movement movement = movements.get(movements.size() - 1 - i);
            String movementDate = DateForPDFUtil.transDate(movement.getDate());
            String movementStatusName = movement.getStatus().getName();
            String pointLocationName = movement.getPointLocation().getName();
            String movementVesselName = "";
            if (movement.getVessel() != null) {
                movementVesselName = movement.getVessel().getName();
            }
            if (DateForPDFUtil.isPastDate(movement.getDate())) {
                pastDate++;
            }
            String movementVoyageReference = movement.getVoyageReference();
            map.put("movementDate", movementDate);
            map.put("movementStatusName", movementStatusName);
            map.put("pointLocationName", pointLocationName);
            map.put("movementVesselName", movementVesselName);
            map.put("movementVoyageReference", movementVoyageReference);
            Lists.add(map);
        }
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
