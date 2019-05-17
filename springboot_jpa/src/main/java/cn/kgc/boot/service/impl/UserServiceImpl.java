package cn.kgc.boot.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.kgc.boot.dao.UserDao;
import cn.kgc.boot.entity.User;
import cn.kgc.boot.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Resource(name = "userDao")
	private UserDao userDao;
	
	public User getUserByCellphone(String cellphone) throws Exception{
		User user =userDao.findUserByCellphone(cellphone);
		return user;
	}

	public boolean saveUser(User user) throws Exception{
		user = userDao.save(user);
		if (user.getUserId() != null) {
			return true;
		}
		return false;
	}

	public List<User> getAllUserList() throws Exception {
		return userDao.findAll();
	}

	public User getUserById(Long userId) throws Exception {
		User user = userDao.getOne(userId);
		return user;
	}

	public boolean deleteUser(User user) throws Exception {
		try {
			userDao.delete(user);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

}
