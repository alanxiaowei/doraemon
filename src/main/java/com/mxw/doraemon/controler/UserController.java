package com.mxw.doraemon.controler;

/**
 * @program: doraemon
 * @description: 用户测试
 * @author: AlanMa
 * @create: 2019-05-29 19:08
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxw.doraemon.entity.User;
import com.mxw.doraemon.entity.enums.GenderEnum;
import com.mxw.doraemon.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.controller
 * @email yangyang@dalaoyang.cn
 * @date 2018/7/20
 */
@RestController
public class UserController {

	@Autowired
	private UserMapper userDao;

	/**
	 * 查询用户列表
	 * http://localhost:8726/getUserList?name=alan
	 * @return
	 */
	@GetMapping("getUserList")
	public List<User> getUserList(String name){
		return userDao.getUserListByName(name);
	}


	/**
	 * 根据名称查询用户
	 * http://localhost:8726/getUserListByName?userName=Jone
	 * @param userName
	 * @return
	 */
	@GetMapping("getUserListByName")
	public List<User> getUserListByName(String userName)
	{
		Map map = new HashMap();
		map.put("name", userName);
		return userDao.selectByMap(map);
	}

	/**
	 * 根据名称查询用户
	 * http://localhost:8726/getUserListByNameSQL?userName=Jone
	 * @param userName
	 * @return
	 */
	@GetMapping("getUserListByNameSQL")
	public List<User> getUserListByNameSQL(String userName)
	{
		return userDao.getByNameSQL(userName);
	}


	/**
	 * 保存用户
	 * http://localhost:8726/saveUser?name=alan
	 * @param name
	 * @return
	 */
	@GetMapping("saveUser")
	public String saveUser(String name)
	{
		User user = new User(name,GenderEnum.MALE,30,"alanxiaowei@hotmail.com");
		Integer index = userDao.insert(user);
		if(index>0){
			return "新增用户成功。";
		}else{
			return "新增用户失败。";
		}
	}

	/**
	 * 修改用户
	 * http://localhost:8726/updateUser?name=alan&age=32
	 * @param id
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	@GetMapping("updateUser")
	public String updateUser(String name,Integer age)
	{
		QueryWrapper wrapper=new QueryWrapper<User>();
		wrapper.eq("name",name);
		User user= userDao.selectOne(wrapper);
		user.setAge(age);
		Integer index = userDao.updateById(user);
		if(index>0){
			return "修改用户成功，影响行数"+index+"行。";
		}else{
			return "修改用户失败，影响行数"+index+"行。";
		}
	}

	/**
	 * 根据Id查询User
	 * http://localhost:8726/getUserById?userId=1
	 * @param userId
	 * @return
	 */
	@GetMapping("getUserById")
	public User getUserById(Integer userId)
	{
		return userDao.selectById(userId);
	}

	/**
	 * http://localhost:8726/getUserListByPage?pageNumber=0&pageSize=2
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping("getUserListByPage")
	public List<User> getUserListByPage(Integer pageNumber,Integer pageSize)
	{
		Page<User> page =new Page<>(pageNumber,pageSize);
		return userDao.selectPage(page,null).getRecords();
	}


}


