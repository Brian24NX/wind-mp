package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.dto.pdf.PdfReq;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.common.util.log.WebLog;
import com.iss.wind.serevice.export.TrackingExportService;
import com.iss.wind.wx.miniapp.util.MailUtils;
import com.iss.wind.wx.miniapp.util.SendMail;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
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
    @Resource
    private MailUtils mailUtils;

    private static final String PDFPATH = "C:\\deploy\\PDF_Repository";

    /**
     * 下载PDF
     *
     *
     */
    @PostMapping("/downloadPdf")
    @ApiOperation(value = "pdf模板导出", notes = "pdf模板导出")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "downloadPdf")
    public SimpleResult<String> download(@RequestBody ShipmentTrackingResp trackingResp) {
        try {
            //String Path1 = "D:\\Wind\\wind-mp\\wind-mp-miniapp\\src\\main\\resources\\pdfTemplate\\tracking.jrxml";
            //String Path2 = "D:\\Wind\\wind-mp\\wind-mp-miniapp\\src\\main\\resources\\pdfTemplate\\tracking.jasper";
            //JasperCompileManager.compileReportToFile(Path1, Path2);
            log.info("\n接收到来自前台的数据:{}", trackingResp);
            InputStream fis = this.getClass().getResourceAsStream("/pdfTemplate/tracking.jasper");
            String fileName = UUID.randomUUID().toString().replace("-", "")+"-Tracking.pdf";
            String filePath = PDFPATH+File.separator+fileName;
            //调用方法以获取正确的数据格式;
            ArrayList<Map<String, Object>> list = exportService.getDetails(trackingResp);
            HashMap<String, Object> map = exportService.getColumnHeader(trackingResp);
            //对模板文件进行填充形成新文件,写入到输出流;
            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, map, new JRBeanCollectionDataSource(list));
            JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
            log.info("\nPdf文件地址:{}",filePath);
            return SimpleResult.success(fileName);
        } catch (Exception e) {
            log.error("异常:", e);
            throw new RuntimeException("下载PDF方法异常");
        }
    }

//    @GetMapping("/emailPdf")
//    @ApiOperation(value = "邮箱PDF", notes = "邮箱PDF")
//    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
//    @WebLog(description = "emailPdf")
//    public SimpleResult<String> emailPdf(@ApiParam(name = "receiveMailAccount" ,value = "收件人" ,required = true) @RequestParam String receiveMailAccount,
//                                         @ApiParam(name = "path" ,value = "文件访问地址" ,required = true) @RequestParam String path) {
//        SendMail.senEmail(receiveMailAccount, path);
//        return SimpleResult.success("success");
//    }

    @PostMapping("/emailPdf")
    @ApiOperation(value = "邮箱PDF", notes = "邮箱PDF")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "emailPdf")
    public SimpleResult<String> emailPdf1(@RequestBody PdfReq pdfReq) {
        String subject = "Container Detail_"+pdfReq.getShipmentRef();
        String content = "<p>尊敬用户：<br>\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;感谢您使用本服务，查看航线PDF文件，请点击附件。</p>";
        mailUtils.sendMail(subject,content,pdfReq.getReceiveMailAccount(),pdfReq.getPath());
        return SimpleResult.success("success");
    }

}