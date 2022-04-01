package com.iss.wind.serevice.util;

import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Locale;

/**
 * PDF生成工具类
 */
@Slf4j
public class PDFUtils {

    /**
     * resource资源路径获取
     */
    private static final URL URL = Thread.currentThread().getContextClassLoader().getResource("");

    /**
     * 默认编码格式 UTF-8
     */
    public static final String ENCODING = "UTF-8";

    /**
     * 模板路径
     */
    private static final String TEMPLATE_PATH = URL.getPath().concat("pdfTemplate").concat(File.separator);

    /**
     * 字体路径
     */
    private static final String FONT_PATH = URL.getPath().concat("pdfTemplate").concat(File.separator)
            .concat("font").concat(File.separator);

    /**
     * 字体名 宋体
     */
    private static final String FONT_NAME = "simsun.ttc";

    private PDFUtils(){
        throw new IllegalStateException("不允许创建PDFUtils实例");
    }

    /**
     * 生成PDF
     * @param templateFileName 模板文件名
     * @param data 要填充的数据（键-值）（通常是一个Map<String, Object>,
     *             或者是一个JavaBean，如果是JavaBean，那么属性名将作为键）
     *             注意：键值对中的值不能为 null
     * @param out 输出流
     */
    public static void createPDF(String templateFileName, Object data, OutputStream out){
        //创建一个Freemarker示例
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        ITextRenderer renderer = new ITextRenderer();
        log.info("URL={"+URL+"},TEMPLATE_PATH={"+TEMPLATE_PATH+"},templateFileName={"+templateFileName+"}");
        try {
//            //设置模板文件加载路径
//            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
//            //设置css中的字体样式（默认宋体）
////            renderer.getFontResolver().addFont(FONT_PATH.concat(FONT_NAME), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            //设置模板的编码格式
            cfg.setEncoding(Locale.CHINA, ENCODING);
            //获取模板文件
            cfg.setClassForTemplateLoading(PDFUtils.class, "/pdfTemplate");
            Template template = cfg.getTemplate(templateFileName, ENCODING);
            StringWriter writer = new StringWriter();
            //将数据输出到html中
            template.process(data, writer);
            writer.flush();
            String html = writer.toString();
            //将html代码传入渲染器中
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(out, false);
            renderer.finishPDF();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成PDF出现异常");
        }
    }
}
