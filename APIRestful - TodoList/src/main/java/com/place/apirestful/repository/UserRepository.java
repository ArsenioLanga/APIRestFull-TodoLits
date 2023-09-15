package com.place.apirestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.place.apirestful.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	
}
