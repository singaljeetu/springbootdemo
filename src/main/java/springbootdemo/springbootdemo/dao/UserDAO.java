package springbootdemo.springbootdemo.dao;
import springbootdemo.springbootdemo.dto.*;
import springbootdemo.springbootdemo.exception.UserNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	HashMap<Integer, User> hmUser = new HashMap<Integer, User>();
	
	public User addUSer(User user) {
		Integer id = hmUser.size() + 1;
		user.setsId(id);
		 hmUser.put(id, user);
		 return user;
	}
	
	public User viewUSer(Integer id) {
		System.out.println(hmUser);
		return hmUser.get(id);
	}
	
	public User deleteUser(Integer id) {
		return hmUser.remove(id);
	}
	
	public User updateUser(Integer id, User user) {
		return hmUser.put(id, user);
	}
	
	public Collection<User> getAllUser(){
		return hmUser.values();
	}
}
