package com.place.apirestful.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.place.apirestful.models.User;
import com.place.apirestful.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User saveUser(User user) {
		user.setId(null);
	  return this.userRepository.save(user);
	}
	
	@Transactional
	public User upadateUser(User user) {
		User newObj = findById(user.getId());
		newObj.setPassword(user.getPassword());
	  return this.userRepository.save(newObj);
	}
	
	public void deleteUser(Long id) {
		findById(id);
		try {
			this.userRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException(" Não pode deletar este user porque tem tarefas relacionadas");
		}
	}
	
	public List<User> allUsers(){
		List<User> lista = new ArrayList<>();
		lista = this.userRepository.findAll();
		return lista;
	}
	
	public User findById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		return user.orElseThrow(
			()-> new RuntimeException(
				"Usuario não encontrado! Verifica o id: "+ id + ", Tipo " + User.class.getName()
			)
		);
	}
	
}
