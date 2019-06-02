package com.mxw.doraemon.controler;

/**
 * @program: doraemon
 * @description: 用户测试
 * @author: AlanMa
 * @create: 2019-05-29 19:08
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxw.doraemon.entity.User;
import com.mxw.doraemon.entity.enums.GenderEnum;
import com.mxw.doraemon.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;


	/**
	 * 创建用户
	 * http://localhost:8726/user/createUser?name=alan&gender=MALE&age=18&email=alanxiaowei@hotmail.com
	 *
	 * @param name
	 * @return
	 */
	@GetMapping("/createUser")
	public String createUser(String name,String gender,Integer age,String email) {
		User user = new User(name,GenderEnum.valueOf(gender),age,email);

		Integer index = userMapper.insert(user);
		if (index > 0) {
			return "已创建用户:"+user.toString();
		} else {
			return "创建用户失败";
		}
	}

	/**
	 * 根据编号查询用户信息
	 * http://localhost:8726/user/getUserById?userId=1
	 *
	 * @param userId
	 * @return
	 */
	@GetMapping("/getUserById")
	public User getUserById(Integer userId) {
		return userMapper.selectById(userId);
	}

	/**
	 * 根据名称查询用户信息（Mybatis方式执行XML配置文件中SQL）
	 * http://localhost:8726/user/geUserByName1?name=alan
	 *
	 * @return
	 */
	@GetMapping("/geUserByName1")
	public List<User> getUserByNameXML(String name) {
		return userMapper.getByNameSQLXML(name);
	}


	/**
	 * 根据名称查询用户信息（MyBatisPlus Map条件方式）
	 * http://localhost:8726/user/geUserByName2?name=alan
	 *
	 * @param userName
	 * @return
	 */
	@GetMapping("/geUserByName2")
	public List<User> getUserByNameMap(String name) {
		Map map = new HashMap();
		map.put("name", name);
		return userMapper.selectByMap(map);
	}

	/**
	 * 根据名称查询用户信息（MyBatisPlus方式执行mapper注解中SQL）
	 * http://localhost:8726/user/geUserByName3?name=alan
	 *
	 * @param userName
	 * @return
	 */
	@GetMapping("/geUserByName3")
	public List<User> getUserListByNameSQL(String name) {
		return userMapper.getByNameSQL(name);
	}

	/**
	 * 分页查询用户列表
	 * http://localhost:8726/user/getList?pageNumber=1&pageSize=2
	 *
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/getList")
	public List<User> getList(Integer pageNumber, Integer pageSize) {
		IPage<User> page = new Page<>(pageNumber, pageSize);
		return userMapper.selectPage(page,null).getRecords();
	}

	/**
	 * 修改用户
	 * http://localhost:8726/user/updateUser?name=alan&age=32
	 *
	 * @param id
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	@GetMapping("/updateUser")
	public String updateUser(String name, Integer age) {
		QueryWrapper wrapper = new QueryWrapper<User>();
		wrapper.eq("name", name);
		User user = userMapper.selectOne(wrapper);
		user.setAge(age);
		Integer index = userMapper.updateById(user);
		if (index > 0) {
			return "已修改用户信息：" + user.toString();
		} else {
			return "修改用户失败";
		}
	}

	/**
	 * 删除用户
	 * http://localhost:8726/user/deleteUser?name=alan
	 *
	 * @param id
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	@GetMapping("/deleteUser")
	public String deleteUser(String name) {
		QueryWrapper wrapper = new QueryWrapper<User>();
		wrapper.eq("name", name);
		User user = userMapper.selectOne(wrapper);
		Integer index = userMapper.deleteById(user.getId());
		if (index > 0) {
			return "已删除用户：" + user.toString();
		} else {
			return "删除用户失败";
		}
	}
}


