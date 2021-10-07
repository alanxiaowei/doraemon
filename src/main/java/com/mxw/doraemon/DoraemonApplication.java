package com.mxw.doraemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DoraemonApplication {

	static final Logger logger = LoggerFactory.getLogger(DoraemonApplication.class);

	public static void main(String[] args) {
		logger.info("start {} {}...", DoraemonApplication.class.getSimpleName(),
				DoraemonApplication.class.getPackage().getImplementationVersion());
		SpringApplication.run(DoraemonApplication.class, args);
	}




}
