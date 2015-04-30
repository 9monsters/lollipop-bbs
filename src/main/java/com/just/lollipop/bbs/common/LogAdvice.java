package com.just.lollipop.bbs.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAdvice {
	
	/** 对业务层方法进行AfterReturning增强处理进行日志记录  */
	@AfterReturning(pointcut="execution(* com.forum.service.impl.*.*.*(..))", returning="target")
	public void logger(JoinPoint jp, Object target){
		Logger logger = Logger.getLogger(LogAdvice.class);
		
		logger.info("Method Signature: " + jp.getSignature().getName());
		logger.info("Method Args: " + jp.getArgs());
		logger.info("Target: " + jp.getTarget());
		
		if (target != null){
			logger.info("Method return: " + target);
		}else{
			logger.info("Method return: void");
		}
	}
	
}
