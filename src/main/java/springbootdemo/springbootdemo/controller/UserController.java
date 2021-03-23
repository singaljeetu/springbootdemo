package springbootdemo.springbootdemo.controller;

import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springbootdemo.springbootdemo.dto.UserDTO;
import springbootdemo.springbootdemo.exception.UserNotFoundException;
import springbootdemo.springbootdemo.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	UserService user;
	
	@PostMapping(path = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDTO addUSer(@RequestBody UserDTO userDtoObj) {
		
		return user.addUSer(userDtoObj);
	}
	
	@GetMapping(path = "/view/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public UserDTO viewUSer(@PathVariable String id) {
		UserDTO dto = user.viewUSer(id);
		if(dto == null)
			throw new UserNotFoundException("102","User not found with Id " + id);
		return dto; 
	}
	
	@DeleteMapping(path = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public UserDTO deleteUser(@RequestParam String id) {
		return user.deleteUser(id);
	}
	
	@PutMapping(path = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public UserDTO updateUser(@RequestParam String id, @RequestBody UserDTO userDtoObj) {
		return user.updateUser(id, userDtoObj);
	}

	@GetMapping(path="/all")
	public Collection<UserDTO> getAllUser(){
		return user.getAllUser();
	}
}
