package com.place.apirestful.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.place.apirestful.models.Task;
import com.place.apirestful.models.User;
import com.place.apirestful.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService;
	
	public Task findById(Long id) {
		Optional<Task> task = this.taskRepository.findById(id);
		return task.orElseThrow(
				()-> new RuntimeException("Tarefa não encontrada! Verifica o id: "+ id + ", Tipo " + User.class.getName())
		);
	}
	
	@Transactional
	public Task saveTask(Task task) {
		User user = this.userService.findById(task.getUser().getId());
		task.setId(null);
		task.setUser(user);
		task  = this.taskRepository.save(task);
		return task;
	}
	
	@Transactional
	public Task updateTask(Task task) {
		Task newObjct = this.findById(task.getId());
		newObjct.setDescription(task.getDescription());
		return this.taskRepository.save(newObjct);
	}
	
	public void deleteTask(Long id) {
		findById(id);
		try {
			this.taskRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possivel deletar esta tarefa porque tem entidades recionadas");
		}
	}
}
