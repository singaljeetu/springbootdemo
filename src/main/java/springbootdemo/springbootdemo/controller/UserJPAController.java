package springbootdemo.springbootdemo.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

import springbootdemo.springbootdemo.dto.Post;
import springbootdemo.springbootdemo.dto.User;
import springbootdemo.springbootdemo.exception.ErrorMessage;
import springbootdemo.springbootdemo.exception.UserNotFoundException;
import springbootdemo.springbootdemo.repository.PostRepository;
import springbootdemo.springbootdemo.repository.UserRepository;
import springbootdemo.springbootdemo.service.UserService;

@RestController
@RequestMapping(path = "/user/jpa")
public class UserJPAController {
	
	@Autowired
	UserService user;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	MessageSource messageSource;
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> addUSer(@Valid @RequestBody User userDtoObj) {
		
		User dto = userRepo.save(userDtoObj);		
		URI link = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sId}").buildAndExpand(dto.getsId()).toUri();
		//return dto;
		return ResponseEntity.created(link).build();
	}
	
	@GetMapping(path = "/{id}")
	public EntityModel<User> viewUSer(@PathVariable Integer id) throws UserNotFoundException {
		Optional<User> dto =  userRepo.findById(id);
		//UserDTO dto = user.viewUSer(id);
		if(dto.isEmpty())
			throw new UserNotFoundException(new ErrorMessage("404", "not found"));
		EntityModel<User> em = EntityModel.of(dto.get());
		
		WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).getAllUser());
		
		em.add(linkToUser.withRel("all-users"));
		
		return em; 
	}
	
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteUser(@PathVariable Integer id) {
		userRepo.deleteById(id);
	}
	
	@PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public User updateUser(@PathVariable String id, @RequestBody User userDtoObj) {
		return userRepo.save(userDtoObj);
	}

	@GetMapping(path="/all")
	public Collection<User> getAllUser(){
		return userRepo.findAll();
	}
	
	@PostMapping(path="/{id}/posts", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> createUserPost(@PathVariable Integer id, @RequestBody Post post) {
		Optional<User> user = userRepo.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException(new ErrorMessage("404", "User not found with ID " + id)); 
		
		post.setUser(user.get());
		
		postRepo.save(post);
				
		URI link = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sId}").buildAndExpand(post.getId()).toUri();
		//return dto;
		return ResponseEntity.created(link).build();
	}
	
	@GetMapping(path="/{id}/posts")
	public Collection<Post> getAllPostsFromUser(@PathVariable Integer id){
		Optional<User> user = userRepo.findById(id);
		List<Post> posts = null;
		if(user.isPresent()) {
			posts = user.get().getPosts();
		}
		else
			throw new UserNotFoundException(new ErrorMessage("404", "User not found with ID " + id));
			
		return posts;
	}
}
