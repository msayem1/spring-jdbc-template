package com.msayem.dao;

import java.util.List;
import com.msayem.entity.User;

/**
 * Interface for User DAO Implementation.
 * 
 * @author MSAYEM
 * 
 */
public interface UserDao {

	public void addUser(User user);

	public List<User> listUser();

	public User getUserById(Integer id);
	public void updateUser(User user);

	public void deleteUser(Integer id);
}