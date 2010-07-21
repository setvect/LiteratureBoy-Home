package com.setvect.literatureboy.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.setvect.common.db.HibernateUtil;
import com.setvect.common.log.LogPrinter;

/**
 * @version $Id$
 */
@Aspect
public class DBTransitionAspect {

	/**
	 * ��Ʈ�ѷ����� ���� �޼ҵ� ȣ��� Ʈ����� ����
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
