package springbootdemo.springbootdemo.dao;
import springbootdemo.springbootdemo.dto.*;
import springbootdemo.springbootdemo.exception.UserNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	HashMap<String, UserDTO> hmUser = new HashMap<String, UserDTO>();
	
	public UserDTO addUSer(UserDTO user) {
		String id = "" + (hmUser.size() + 1);
		user.setsId(id);
		 hmUser.put(id, user);
		 return user;
	}
	
	public UserDTO viewUSer(String id) {
		System.out.println(hmUser);
		return hmUser.get(id);
	}
	
	public UserDTO deleteUser(String id) {
		return hmUser.remove(id);
	}
	
	public UserDTO updateUser(String id, UserDTO user) {
		return hmUser.put(""+id, user);
	}
	
	public Collection<UserDTO> getAllUser(){
		return hmUser.values();
	}
}
