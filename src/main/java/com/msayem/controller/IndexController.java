package com.msayem.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.msayem.entity.User;
import com.msayem.dao.UserDao;

/**
 * Handles requests for the application index page.
 * 
 * @author MSAYEM
 * 
 */
@Controller
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)      
	public String indexController(Map<String, Object> map) {

		map.put("user", new User());
		map.put("userList", userDao.listUser());

		logger.info("spring-jdbc-template: Loading index.jsp page...");
		
		return "index";
	}

	@RequestMapping(value = "/add-edit", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("user") User user, BindingResult result) {

		if(user.getUserid() == 0) {
			
			userDao.addUser(user);
		}
		else {
			
			userDao.updateUser(user);
		}

		return "redirect:/";
	}
    
	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Map<String, Object> map){

		map.put("user", userDao.getUserById(id));
		map.put("userList", userDao.listUser());
        
		return "index";
	}

	@RequestMapping("/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {

		userDao.deleteUser(userId);

		return "redirect:/";
	}
}