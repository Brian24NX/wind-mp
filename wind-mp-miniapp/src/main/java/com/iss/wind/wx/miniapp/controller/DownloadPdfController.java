package com.iss.wind.wx.miniapp.controller;

import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.common.util.log.WebLog;
import com.iss.wind.serevice.export.TrackingExportService;
import com.iss.wind.serevice.util.PDFUtils;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;
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


    /**
     * 下载PDF
     *
     * @param response
     */
    @PostMapping("/downloadPdf")
    @ApiOperation(value = "pdf模板导出", notes = "pdf模板导出")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    //@WebLog(description = "downloadPdf")
    public void download(@RequestBody ShipmentTrackingResp trackingResp, HttpServletResponse response) {
        try (BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            //ObjectMapper mapper = new ObjectMapper();
            //ShipmentTrackingResp trackingResp = mapper.readValue(tracking, ShipmentTrackingResp.class);
            //String Path1 = "D:\\Wind\\wind-mp\\wind-mp-miniapp\\src\\main\\resources\\pdfTemplate\\tracking.jrxml";
            //String Path2 = "D:\\Wind\\wind-mp\\wind-mp-miniapp\\src\\main\\resources\\pdfTemplate\\tracking.jasper";
            //JasperCompileManager.compileReportToFile(Path1, Path2);
            log.info("\n接收到来自前台的数据:{}", trackingResp);
            ClassPathResource resource = new ClassPathResource("pdfTemplate/tracking.jasper");
            FileInputStream fis = new FileInputStream(resource.getFile());
            //调用方法以获取正确的数据格式;
            HashMap<String, Object> map = exportService.getColumnHeader(trackingResp);
            ArrayList<Map<String, Object>> list = exportService.getDetails(trackingResp);
            //对模板文件进行填充形成新文件,写入到输出流;
            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, map, new JRBeanCollectionDataSource(list));
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setCharacterEncoding(PDFUtils.ENCODING);
            String fileName = URLEncoder.encode("shipment-tracking.pdf", PDFUtils.ENCODING);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("异常：", e);
            throw new RuntimeException("下载PDF方法异常");
        }
    }
}
