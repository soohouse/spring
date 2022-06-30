package com.spring.myweb.util.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect //AOP를 적용시킬 클래스
@Component //빈 등록
public class LogAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

	/*
	 준비하신 로직(advice)를 어떤 시점(joint point)에서 사용하게 할 지를 정해주실 수가 있습니다. (pointCut)
	 @before, @after, @afterThrowing
	 @around는 위에 있는 세 가지 pointcut을 한번에 처리할 수 있도록 해 줍니다.
	 메서드 실행 권한을 가지고, 타겟 메서드랑 접목시켜서 사용.
	 규칙 - 반환타입은 Object, 매개변수로 메서드의 실행 시점을 결정짓는 ProceedingJoinPoint를 선언합니다.
	 ProceedingJoinPoint는 AOP의 대상이 되는 Target이나 파라미터 등을 파악할 뿐만 아니라,
	 직접 실행을 결정할 수도 있습니다.
	 
	 execution(accessModi package class method arguments)
	 */
	//접근제한자 상관없음
	//com.spring.myweb으로 시작하는 패키지, 중간에 뭐 들어가는 지는 상관없고
	//서비스 패키지 안에 있는 ~~~Service로 끝나는 클래스의 모든 메서드(매개변수 관계 없음)
	@Around("execution(* com.spring.myweb.*.service.*Service.*(..))")
	public Object aroundLog(ProceedingJoinPoint jp) {
		
		long start = System.currentTimeMillis();
		
		logger.info("실행 중인 클래스: " + jp.getTarget() );
		logger.info("실행 중인 메서드: " + jp.getSignature().toString());
		logger.info("매개값 : " + Arrays.toString(jp.getArgs()));
		
		
		
		Object result = null;
		
		try {
			result = jp.proceed(); //타겟 메서드의 실행.
		} catch (Throwable e) {

			e.printStackTrace();
		} 
		
		long end = System.currentTimeMillis();
		
		logger.info("메서드 소요 시간: " + (end-start)*0.001 + "초");	
		
		//위에 작성한 이 메서드의 실행 내용은 proxy 환경에서 돌아가는 중이기 때문에
		//proceed()의 결과를 반환해야 메서드의 정상 흐름으로 돌아갑니다.
		return result; 
	}
}
