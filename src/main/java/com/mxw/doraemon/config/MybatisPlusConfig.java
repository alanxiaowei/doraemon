package com.mxw.doraemon.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @program: doraemon
 * @description: Mybatis-Plus Config
 * @author: AlanMa
 * @create: 2019-05-29 19:02
 */
public class MybatisPlusConfig {
	@Bean
	public PaginationInterceptor paginationInterceptor(){
		PaginationInterceptor page = new PaginationInterceptor();
		//设置方言类型
		page.setDialectType("mysql");
		return page;
	}

}
