package cn.kgc.boot.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.kgc.boot.base.controller.BaseController;
import cn.kgc.boot.entity.User;
import cn.kgc.boot.service.UserService;

@Controller("userController")
@RequestMapping("user")
public class UserController extends BaseController{
	
	@Resource(name = "userService")
	private UserService userService;
	
	// 登录
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginForm() throws Exception {
		return "user_login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(String cellphone, String password) throws Exception {
		// 判断用户是否填写内容
		if (cellphone != null && !"".equals(cellphone.trim()) && (password != null && !"".equals(password.trim()))) {
			//根据电话获取用户信息
			User user = userService.getUserByCellphone(cellphone);
			// 判断用户是否存在
			if (user != null && password.equals(user.getPassword())) {
				// 登录成功
				session.setAttribute("user",user);
				return "redirect:list";
			}
		}
		// 登录失败
		return "redirect:login";
	}
	
	// 注册
	@RequestMapping(value = "/registry", method = RequestMethod.GET)
	public String getRegistryPage() throws Exception {
		return "user_registry";
	}
	
	@RequestMapping(value = "/registry", method = RequestMethod.POST)
	public String registryUser(User user) throws Exception {
		// 检查电话号码是否重复
		if (userService.getUserByCellphone(user.getCellphone()) == null) {
			if (userService.saveUser(user)) {
				// 注册成功
				return "redirect:login";
			}
		}
		// 注册失败
		return "redirect:registry";
	}
	
	// 列表
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getUserList(ModelMap map) throws Exception {
		List<User> userList = userService.getAllUserList();
		map.put("userList", userList);
		return "user_list";
	}
	
	// 修改
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public String getUpdatePage(ModelMap map, Long userId) throws Exception {
		User user = userService.getUserById(userId);
		map.put("user", user);
		return "user_update";
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String updateUser(User user) throws Exception {
		User checkUser = userService.getUserByCellphone(user.getCellphone());
		if ((checkUser == null)||(checkUser != null && user.getUserId() == checkUser.getUserId())) {
			if (userService.saveUser(user)) {
				return "redirect:list";
			}
		}
 		return "redirect:update?userId=" + user.getUserId();
	}

	// 删除
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String deleteUser(Long userId) throws Exception {
		User user = userService.getUserById(userId);
		if (userService.deleteUser(user)) {
			return "redirect:list";
		}
		return "redirect:list";
	}
}
