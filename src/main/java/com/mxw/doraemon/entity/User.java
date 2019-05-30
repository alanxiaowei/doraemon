package com.mxw.doraemon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mxw.doraemon.entity.enums.GenderEnum;
import lombok.Data;

/**
 * 用户
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2019-05-29 18:14
 */

@Data
public class User {
	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private GenderEnum gender;
	private Integer age;
	private String email;

	public User() {
		super();
	}

	public User(String name, GenderEnum gender, Integer age, String email) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}
}
