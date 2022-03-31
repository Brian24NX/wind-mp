package com.iss.wind.serevice.export;

import cn.hutool.core.date.DateUtil;
import com.hanson.rest.SimpleResult;
import com.iss.wind.serevice.util.ContextPatUtil;
import com.iss.wind.serevice.util.DocumentHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FreemarkerService {

    @Value("${wind.fileSavePath}")
    private String windFileSavePath;

    //处理数据根据模板导出word
    public SimpleResult<String> expWord() throws UnsupportedEncodingException {;
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name","dwqqd");
        dataMap.put("time","2022-03-31");
        dataMap.put("Weather","hot");
        dataMap.put("dong","swimming");
        DocumentHandler mdoc = new DocumentHandler();
        String filePath = windFileSavePath;
        String fileName = rename("ttt.doc");
        String savePath = mdoc.createDoc(dataMap, filePath,fileName);
        if(null == savePath){
            return SimpleResult.fail("500","文件生成失败");
        }else {
            String queryPath = ContextPatUtil.getContextPat(ContextPatUtil.getRequest()) +"/file/" + savePath;
            return SimpleResult.success(queryPath);
        }
    }

    public static String rename(String fileName){
        return DateUtil.format(new Date(),"yyyyMMddHHmmsss")+"_"+fileName;
    }
}
