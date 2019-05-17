package cn.kgc.boot.service;

import java.util.List;

import cn.kgc.boot.entity.User;

public interface UserService {

	public User getUserByCellphone(String cellphone) throws Exception;

	public boolean saveUser(User user) throws Exception;

	public List<User> getAllUserList() throws Exception;

	public User getUserById(Long userId) throws Exception;

	public boolean deleteUser(User user) throws Exception;

}
