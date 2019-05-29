package com.mxw.doraemon.controler;

import com.mxw.doraemon.DoraemonApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class ApiController {


	 final Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.info("enter init ~~~~~~~~~~~~~~");
	}

	@GetMapping("/hello")
	public String getSequenceOrders() {

		return "Hello World~";
	}


}
