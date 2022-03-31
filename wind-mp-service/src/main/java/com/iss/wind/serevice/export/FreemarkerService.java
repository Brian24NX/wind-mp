package com.iss.wind.serevice.export;

import cn.hutool.core.date.DateUtil;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.hanson.rest.SimpleResult;
import com.iss.wind.serevice.util.ContextPatUtil;
import com.iss.wind.serevice.util.DocumentHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Slf4j
@Service
public class FreemarkerService {

    @Value("${wind.fileSavePath}")
    private String windFileSavePath;

    /**
     * 处理数据根据模板导出word
     */
    public SimpleResult<List<String>> expWord() throws UnsupportedEncodingException {
        List<String> list = new ArrayList<>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name","dwqqd");
        dataMap.put("time","2022-03-31");
        dataMap.put("Weather","hot");
        dataMap.put("dong","swimming");
        DocumentHandler mdoc = new DocumentHandler();
        String filePath = windFileSavePath;
        String fileName = rename("ttt.docx");
        String wordName = mdoc.createDoc(dataMap, filePath,fileName);
        String pdfName = wordTurnPdf(windFileSavePath,fileName);
        if(null == wordName || null == pdfName){
            return SimpleResult.fail("500","文件生成失败");
        }else {
            String queryWordPath = ContextPatUtil.getContextPat(ContextPatUtil.getRequest()) +"/file/" + wordName;
            String queryPdfPath = ContextPatUtil.getContextPat(ContextPatUtil.getRequest()) +"/file/" + pdfName;
            list.add(queryWordPath);
            list.add(queryPdfPath);
            return SimpleResult.success(list);
        }
    }

    public String wordTurnPdf(String windFileSavePath , String fileName){
        Map<String, Object> map=new HashMap<>();
        try {
            //截取文件前缀
            String caselsh=fileName.substring(0,fileName.lastIndexOf("."));
            //需要转换的word文件
            File inputWord = new File(windFileSavePath+fileName);
            //转换后生成的pdf文件
            File outputFile = new File(windFileSavePath+caselsh+".pdf");
            InputStream docxInputStream = new FileInputStream(inputWord);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            outputStream.close();
            return caselsh + ".pdf";
        } catch (Exception e) {
            log.error("转成pdf异常：",e);
        }
        return null;
    }

    public static String rename(String fileName){
        return DateUtil.format(new Date(),"yyyyMMddHHmmsss")+"_"+fileName;
    }
}
