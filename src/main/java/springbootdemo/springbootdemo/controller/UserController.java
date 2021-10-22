package springbootdemo.springbootdemo.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Locale;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import springbootdemo.springbootdemo.dto.User;
import springbootdemo.springbootdemo.exception.ErrorMessage;
import springbootdemo.springbootdemo.exception.UserNotFoundException;
import springbootdemo.springbootdemo.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	UserService user;
	
	@Autowired
	MessageSource messageSource;
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> addUSer(@Valid @RequestBody User userDtoObj) {
		
		User dto = user.addUSer(userDtoObj);		
		URI link = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sId}").buildAndExpand(dto.getsId()).toUri();
		//return dto;
		return ResponseEntity.created(link).build();
	}
	
	@GetMapping(path = "/{id}")
	public EntityModel<User> viewUSer(@PathVariable Integer id) throws UserNotFoundException {
		User dto = user.viewUSer(id);
		if(id.equals("7"))
			throw new RuntimeException("Bad Request");
		if(dto == null)
			throw new UserNotFoundException(new ErrorMessage("404", "not found"));
		EntityModel<User> em = EntityModel.of(dto);
		
		WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).getAllUser());
		
		em.add(linkToUser.withRel("all-users"));
		
		return em; 
	}
	
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public User deleteUser(@PathVariable Integer id) {
		return user.deleteUser(id);
	}
	
	@PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public User updateUser(@PathVariable Integer id, @RequestBody User userDtoObj) {
		return user.updateUser(id, userDtoObj);
	}

	@GetMapping(path="/all")
	public Collection<User> getAllUser(){
		return user.getAllUser();
	}
	
	@GetMapping(path="/hello-internationalized")
	public String getInternationalized(){
		return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
	}
	
}
