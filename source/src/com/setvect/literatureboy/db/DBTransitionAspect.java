package com.setvect.literatureboy.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.setvect.common.db.HibernateUtil;
import com.setvect.common.log.LogPrinter;

@Aspect
public class DBTransitionAspect {

	/**
	 * 컨트롤러에서 서비스 메소드 호출시 트랜잭션 관리
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 * @version $Id$
	 */
	@Around("execution(public * com.setvect.literatureboy.web..*Controller.*Transcation(..)) ")
	public Object beforeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		Object val = null;
		try {
			HibernateUtil.beginTransaction();
			val = joinPoint.proceed();
			HibernateUtil.commitTransaction();
		} catch (Throwable e) {
			LogPrinter.error(e.getMessage(), e);
			HibernateUtil.rollbackTransaction();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}

		return val;
	}
}
