package com.mxw.doraemon.junit;

import com.mxw.doraemon.entity.User;
import com.mxw.doraemon.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: doraemon
 * @description: 用户测试
 * @author: AlanMa
 * @create: 2019-05-29 18:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		List<User> userList = userMapper.getByNameSQLXML("alan");
		userList.forEach(System.out::println);
	}

}
