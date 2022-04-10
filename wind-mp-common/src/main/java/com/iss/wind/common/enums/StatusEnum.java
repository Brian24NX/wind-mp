package com.iss.wind.common.enums;

public enum StatusEnum {
    MEA("EmptyInDepotMEA", "Empty in depot"),
    MOS("EmptyDeliveredToShipper", "Emptydelivered to shipper"),
    XRX("Readytobeloaded", "Ready be loaded"),
    XOF("LoadedonboardXOF", "Loaded on board"),
    TDF("DischargedInTranshipment", "Discharged in transhipment"),
    TOF("LoadedonboardTOF", "Loaded on board"),
    IDF("Discharged", "Discharged"),
    IFC("ContainerToConsignee", "Container to consignee"),
    TAF("Containerintransitforimport", "Container in transit for import"),
    PSI("ContainerOnRailForImport", "Container on rail for import"),
    IRA("TrainArrivalForImport", "Train arrival for import"),
    IRI("ReceivedForImportTransfer", "Received for import transfer"),
    IIT("ReceivedForExportTransfer", "Received for export transfer"),
    XLR("FullLoadOnRailForExport", "Full load on rail for export"),
    TPF("Containerintransitforexport", "Container in transit for export"),
    PSE("ContainerOnRailForExport", "Container on rail for export"),
    XUR("ExportUnloadFullFromRail", "Export unload full from rail"),
    ETA_FINAL_DISCHARGE("FinalDischarge", "Arrival final port of discharge");


    private final String statusUpName;
    private final String statusLoName;

    private StatusEnum(String statusUpName, String statusLoName) {
        this.statusUpName = statusUpName;
        this.statusLoName = statusLoName;
    }

    public String getStatusUpName() {
        return statusUpName;
    }

    public String getStatusLoName() {
        return statusLoName;
    }

    public static String getUpNameByCode(String code, String name) {
        StatusEnum[] values = values();
        for (StatusEnum status : values) {
            if (status.toString().equals(code)) {
                return status.getStatusUpName();
            }
        }
        return name;
    }

    public static String getLoNameByCode(String code, String name) {
        StatusEnum[] values = values();
        for (StatusEnum status : values) {
            if (status.toString().equals(code)) {
                return status.getStatusLoName();
            }
        }
        return name;
    }

}