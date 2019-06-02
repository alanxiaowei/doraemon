package com.mxw.doraemon.entity;

import com.mxw.doraemon.common.BaseEntity;
import com.mxw.doraemon.entity.enums.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户
 *
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2019-05-29 18:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {

	private static final long serialVersionUID = 125341736548392818L;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 性别
	 */
	private GenderEnum gender;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 邮箱
	 */
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
