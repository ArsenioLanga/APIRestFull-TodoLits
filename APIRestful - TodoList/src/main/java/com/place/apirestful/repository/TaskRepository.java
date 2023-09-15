package com.place.apirestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.place.apirestful.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
