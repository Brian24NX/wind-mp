package com.iss.wind.wx.miniapp.controller;

import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.common.util.log.WebLog;
import com.iss.wind.serevice.export.TrackingExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 测试下载PDF Controller
 */
@Api(value = "pdf模板导出")
@Slf4j
@RestController
public class DownloadPdfController {

    @Autowired
    private TrackingExportService exportService;

    private static final String PDFPATH = "D:\\deploy\\PDF_Repository";

    /**
     * 下载PDF
     *
     *
     */
    @PostMapping("/downloadPdf")
    @ApiOperation(value = "pdf模板导出", notes = "pdf模板导出")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    //@WebLog(description = "downloadPdf")
    public String download(@RequestBody ShipmentTrackingResp trackingResp) {
        try {
            //String Path1 = "D:\\Wind\\wind-mp\\wind-mp-miniapp\\src\\main\\resources\\pdfTemplate\\tracking.jrxml";
            //String Path2 = "D:\\Wind\\wind-mp\\wind-mp-miniapp\\src\\main\\resources\\pdfTemplate\\tracking.jasper";
            //JasperCompileManager.compileReportToFile(Path1, Path2);
            log.info("\n接收到来自前台的数据:{}", trackingResp);
            InputStream fis = this.getClass().getResourceAsStream("/pdfTemplate/tracking.jasper");
            String filePath = PDFPATH+File.separator+UUID.randomUUID().toString().replace("-","")+"-Tracking.pdf";
            //调用方法以获取正确的数据格式;
            ArrayList<Map<String, Object>> list = exportService.getDetails(trackingResp);
            HashMap<String, Object> map = exportService.getColumnHeader(trackingResp);
            //对模板文件进行填充形成新文件,写入到输出流;
            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, map, new JRBeanCollectionDataSource(list));
            JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
            log.info("\nPdf文件地址:{}",filePath);
            return filePath;
        } catch (Exception e) {
            log.error("异常:", e);
            throw new RuntimeException("下载PDF方法异常");
        }
    }
}