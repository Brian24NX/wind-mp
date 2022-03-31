package com.iss.wind.serevice.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ContextPatUtil {
	
	public static String getContextPat(HttpServletRequest request){
		String contextPat = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort()// 端口号
				+ request.getContextPath();// 项目名
		return contextPat;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes.getRequest();
	}

}
