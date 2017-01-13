package com.alanma.doraemon.utils.property;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProperty {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("propertyconfig/applicationContext.xml");

		String value = CustomPropertyConfigurer.getProperty("");

		System.out.println(value);
	}
}
