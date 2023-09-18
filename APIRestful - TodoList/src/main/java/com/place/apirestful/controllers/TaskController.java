package com.place.apirestful.controllers;

import java.net.URI;
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

import com.place.apirestful.models.Task;
import com.place.apirestful.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {		
		Task newObj = this.taskService.findById(id);
		return ResponseEntity.ok().body(newObj);		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Task>> findTasksByUser(@PathVariable Long id){
		List<Task> tasksById = this.taskService.findAllById(id);
		return ResponseEntity.ok().body(tasksById);	
	}
	
	
	@PostMapping("/create")
	@Validated
	public ResponseEntity<Void> saveTask(@Valid @RequestBody Task task) {
		this.taskService.saveTask(task);
//		return ResponseEntity.status(HttpStatus.CREATED).body(users);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
//	@Validated(UpdateUser.class)
	public ResponseEntity<Void> updateTask(@Valid @RequestBody Task task, @PathVariable Long id) {
		task.setId(id);
	    this.taskService.updateTask(task);
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id){
		this.taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
