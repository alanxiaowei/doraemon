package com.alanma.doraemon.utils.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alanma.doraemon.utils.list.ListUtil;
import com.alanma.doraemon.utils.reflect.domain.EntityTAD;

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

}
