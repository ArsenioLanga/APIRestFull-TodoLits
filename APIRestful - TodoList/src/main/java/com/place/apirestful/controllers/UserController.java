package com.place.apirestful.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.place.apirestful.models.User;
import com.place.apirestful.models.User.CreateUser;
import com.place.apirestful.models.User.UpdateUser;
import com.place.apirestful.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {		
		User newObj = this.userService.findById(id);
		return ResponseEntity.ok().body(newObj);		
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> listAllUsers() {		
	   List<User> lista = new ArrayList<>();
	   lista = userService.allUsers();
		return ResponseEntity.ok().body(lista);		
	}
	
	
	@PostMapping("/create")
	@Validated(CreateUser.class)
	public ResponseEntity<Void> saveUser(@Valid @RequestBody User user) {
		this.userService.saveUser(user);
//		return ResponseEntity.status(HttpStatus.CREATED).body(users);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Validated(UpdateUser.class)
	public ResponseEntity<Void> updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
		user.setId(id);
	    this.userService.upadateUser(user);
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		this.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
