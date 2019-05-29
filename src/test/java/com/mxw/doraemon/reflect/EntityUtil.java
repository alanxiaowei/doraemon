package com.mxw.doraemon.reflect;

import com.mxw.doraemon.reflect.domain.EntityTAD;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体操作工具类
 * 
 * @author AlanMa
 * 
 */
public class EntityUtil {

	/**
	 * 数据库实体转换为Java对象
	 * 
	 * @param list
	 * @param tabColName
	 * @return
	 * @throws BusException
	 */
	@SuppressWarnings("rawtypes")
	public static List<EntityTAD> getEntityTAD(List entitys) {

		List<EntityTAD> entityPOJOs = new ArrayList<EntityTAD>();
		try {
			for (int i = 0; i < entitys.size(); i++) {
				List entity = (List) entitys.get(i);
				EntityTAD entityPOJO = new EntityTAD();
				Class cls = (Class) entityPOJO.getClass();
				Field[] fields = cls.getDeclaredFields();
				for (int j = 0; j < entity.size(); j++) {
					Field field = fields[j];
					field.setAccessible(true);
					field.set(entityPOJO, entity.get(j));
				}
				entityPOJOs.add(entityPOJO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityPOJOs;
	}
	
	/**
	 * @param val
	 * @return
	 */
	public static String firstToLower(String val) {
		return val.substring(0, 1).toLowerCase() + val.substring(1);
	}

}
