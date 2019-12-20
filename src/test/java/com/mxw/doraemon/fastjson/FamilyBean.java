package com.mxw.doraemon.fastjson;

import com.mxw.doraemon.redisson.User;

import java.util.List;

/**
 * @program: doraemon
 * @description: 家庭对象
 * @author: AlanMa
 * @create: 2019-12-13 11:46
 */
public class FamilyBean {
	private String familyName;
	private List<User> members;
	private List<FamilyBean> subFamily;

	public FamilyBean() {
	}

	public FamilyBean(String familyName, List<User> members, List<FamilyBean> subFamily) {
		this.familyName = familyName;
		this.members = members;
		this.subFamily = subFamily;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<FamilyBean> getSubFamily() {
		return subFamily;
	}

	public void setSubFamily(List<FamilyBean> subFamily) {
		this.subFamily = subFamily;
	}

	@Override
	public String toString() {
		return "FamilyBean{" + "familyName='" + familyName + '\'' + ", members=" + members + ", subFamily=" + subFamily + '}';
	}
}
