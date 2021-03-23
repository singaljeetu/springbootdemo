package springbootdemo.springbootdemo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootdemo.springbootdemo.dao.UserDAO;
import springbootdemo.springbootdemo.dto.UserDTO;

@Service
public class UserService {
	
	@Autowired
	UserDAO user;
	
	public UserDTO addUSer(UserDTO userDtoObj) {
		return user.addUSer(userDtoObj);
	}
	
	public UserDTO viewUSer(String id) {
		return user.viewUSer(id);
	}
	
	public UserDTO deleteUser(String id) {
		return user.deleteUser(id);
	}
	
	public UserDTO updateUser(String id, UserDTO userDtoObj) {
		return user.updateUser(id, userDtoObj);
	}
	
	public Collection<UserDTO> getAllUser(){
		return user.getAllUser();
	}
}
