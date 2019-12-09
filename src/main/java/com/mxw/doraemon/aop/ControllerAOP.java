package com.mxw.doraemon.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @program: digitalbank
 * @description: 控制器切面校验
 * @author: AlanMa
 * @create: 2019-12-06 11:24
 */
@Aspect
@Component
public class ControllerAOP {


	@Pointcut("execution(public * com.mxw.doraemon.controler..*.*(..))")
	public void pointcut() {
	}


	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("我是前置通知!!!");
		//获取目标方法的参数信息
		Object[] obj = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		//代理的是哪一个方法
		System.out.println("方法：" + signature.getName());
		//AOP代理类的名字
		System.out.println("方法所在包:" + signature.getDeclaringTypeName());
		//AOP代理类的类（class）信息
		signature.getDeclaringType();
		MethodSignature methodSignature = (MethodSignature) signature;
		String[] strings = methodSignature.getParameterNames();
		System.out.println("参数名：" + Arrays.toString(strings));
		System.out.println("参数值ARGS : " + Arrays.toString(joinPoint.getArgs()));
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = attributes.getRequest();
		// 记录下请求内容
		System.out.println("请求URL : " + req.getRequestURL().toString());
		System.out.println("HTTP_METHOD : " + req.getMethod());
		System.out.println("IP : " + req.getRemoteAddr());
		System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

	}


	@AfterReturning(returning = "object", pointcut = "pointcut()")
	public void doAfterReturning(Object object) {

	}


	@AfterThrowing("pointcut()")
	public void exp(JoinPoint jp) {

	}


	@After("pointcut()")
	public void after(JoinPoint jp) {

	}


	@Around("pointcut()")
	public Object arround(ProceedingJoinPoint pjp) {
		try {
			Object o = pjp.proceed();
			return o;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}
