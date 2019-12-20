package com.mxw.doraemon.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mxw.doraemon.redisson.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: doraemon
 * @description: 测试TypeReference
 * @author: AlanMa
 * @create: 2019-12-13 11:01
 */
public class TestTypeReference {
	public static void main(String[] args) {

		String jsonStr = "{\"familyName\":\"LaoMa's Family\",\"members\":[{\"address\":\"Shenyang\",\"age\":0,\"name\":\"MaDC\"},{\"address\":\"Shenyang\",\"age\":0,\"name\":\"WangYM\"}],\"subFamily\":[{\"familyName\":\"MXY's Family\",\"members\":[{\"address\":\"Shenyang\",\"age\":0,\"name\":\"MaXY\"}],\"subFamily\":[{\"familyName\":\"HH's Family\",\"members\":[{\"address\":\"Shenyang\",\"age\":0,\"name\":\"Zhangyj\"}]}]},{\"familyName\":\"Alan's Family\",\"members\":[{\"address\":\"Tianjin\",\"age\":0,\"name\":\"Alan\"},{\"address\":\"Tianjin\",\"age\":0,\"name\":\"Lemon\"}]}]}";
		FamilyBean familyBean = execute(null, new TypeReference<FamilyBean>() {
		}, jsonStr);
		System.out.println(familyBean);

		FamilyBean familyBean2 = execute(FamilyBean.class, null, jsonStr);
		System.out.println(familyBean2);
	}

	static <T> T execute(Class<T> clazz, TypeReference<T> ref, String jsonStr) {
		return clazz == null ? JSONObject.parseObject(jsonStr, ref) : JSONObject.parseObject(jsonStr, clazz);
	}

	static void testTypeReferenceParse() {
		System.out.println("test fastjson");

		User alan = new User("Alan", "Tianjin");
		User lemon = new User("Lemon", "Tianjin");
		User mdc = new User("MaDC", "Shenyang");
		User wym = new User("WangYM", "Shenyang");
		User mxy = new User("MaXY", "Shenyang");
		User zyj = new User("Zhangyj", "Shenyang");

		FamilyBean grandsonFamily = new FamilyBean();
		List<User> hhMembers = new ArrayList<>();
		hhMembers.add(zyj);
		grandsonFamily.setFamilyName("HH's Family");
		grandsonFamily.setMembers(hhMembers);

		FamilyBean daughterFamily = new FamilyBean();
		List<User> mxyMembers = new ArrayList<>();
		mxyMembers.add(mxy);
		daughterFamily.setFamilyName("MXY's Family");
		daughterFamily.setMembers(mxyMembers);
		List<FamilyBean> subFamilies = new ArrayList<>();
		subFamilies.add(grandsonFamily);
		daughterFamily.setSubFamily(subFamilies);

		FamilyBean sonFamily = new FamilyBean();
		List<User> sonMembers = new ArrayList<>();
		sonMembers.add(alan);
		sonMembers.add(lemon);
		sonFamily.setFamilyName("Alan's Family");
		sonFamily.setMembers(sonMembers);

		FamilyBean parentFamily = new FamilyBean();
		List<User> members = new ArrayList<>();
		members.add(mdc);
		members.add(wym);
		parentFamily.setFamilyName("LaoMa's Family");
		parentFamily.setMembers(members);
		List<FamilyBean> subFamilies1 = new ArrayList<>();
		subFamilies1.add(daughterFamily);
		subFamilies1.add(sonFamily);
		parentFamily.setSubFamily(subFamilies1);

		String familyStr = JSONObject.toJSONString(parentFamily);
		System.out.println(familyStr + "\n");

		FamilyBean familyBean = JSONObject.parseObject(familyStr, new TypeReference<FamilyBean>() {
		});
		System.out.println(familyBean + "\n");

		FamilyBean familyBean2 = JSONObject.parseObject(familyStr, FamilyBean.class);
		System.out.println(familyBean2 + "\n");
	}


}
