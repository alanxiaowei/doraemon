package com.mxw.doraemon.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: doraemon
 * @description: Mybatis-Plus Config
 * @author: AlanMa
 * @create: 2019-05-29 19:02
 */

@EnableTransactionManagement
@Configuration
@MapperScan("com.mxw.doraemon.mapper")
public class MybatisPlusConfig {
	/**
	 * 分页拦截器
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
