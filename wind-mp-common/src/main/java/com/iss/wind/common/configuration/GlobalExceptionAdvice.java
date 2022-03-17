package com.iss.wind.common.configuration;


import static com.hanson.rest.enmus.ErrorCodeEnum.BAD_REQUEST;
import static com.hanson.rest.enmus.ErrorCodeEnum.FAIL;
import static com.hanson.rest.enmus.ErrorCodeEnum.METHOD_NOT_ALLOWED;
import static com.hanson.rest.enmus.ErrorCodeEnum.NOT_FOUND;

import com.hanson.rest.BizException;
import com.hanson.rest.SimpleResult;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Hanson
 * @date 2021/7/2  13:53
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public SimpleResult<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error(e.getMessage(), e);
		return new SimpleResult<>(BAD_REQUEST);
	}

	/**
	 * 404 - Resource Not Found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public SimpleResult<Void> handleHttpMessageNotFoundException(NoHandlerFoundException e) {
		log.error(e.getMessage(), e);
		return new SimpleResult<>(NOT_FOUND);
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public SimpleResult<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return new SimpleResult<>(METHOD_NOT_ALLOWED);
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public SimpleResult<Void> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return new SimpleResult<>(FAIL.getCode(),e.getMessage());
	}

	/**
	 * 服务异常
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(BizException.class)
	public SimpleResult<Void> ServiceException(BizException e) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String requestURI = request.getRequestURI();
		log.error("call {} failed , cause :{}",requestURI,e.getErrorMessage());
		return SimpleResult.fail(e.getErrorMessage());
	}
}
