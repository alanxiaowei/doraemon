package com.mxw.doraemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxw.doraemon.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: doraemon
 * @description: 用户
 * @author: AlanMa
 * @create: 2019-05-29 18:16
 */
public interface UserMapper extends BaseMapper<User> {

	List<User> getByNameSQLXML(@Param("name") String name);

	@Select("select * from user where name ='${name}'")
	List<User> getByNameSQL(@Param("name") String name);

}
