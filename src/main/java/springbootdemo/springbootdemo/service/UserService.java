package springbootdemo.springbootdemo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootdemo.springbootdemo.dao.UserDAO;
import springbootdemo.springbootdemo.dto.User;

@Service
public class UserService {
	
	@Autowired
	UserDAO user;
	
	public User addUSer(User userDtoObj) {
		return user.addUSer(userDtoObj);
	}
	
	public User viewUSer(Integer id) {
		return user.viewUSer(id);
	}
	
	public User deleteUser(Integer id) {
		return user.deleteUser(id);
	}
	
	public User updateUser(Integer id, User userDtoObj) {
		return user.updateUser(id, userDtoObj);
	}
	
	public Collection<User> getAllUser(){
		return user.getAllUser();
	}
}
