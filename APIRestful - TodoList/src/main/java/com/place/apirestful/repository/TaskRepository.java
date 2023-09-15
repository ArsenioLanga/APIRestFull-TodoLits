package com.place.apirestful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.place.apirestful.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUser_id(Long id);
	
//	@Query(value = "SELECT task FROM Task task WHERE task.user.id = :")
//	List<Task> findByUser_id(@Param("id") Long id);
//	
//	@Query(value="select * from tb_tasks  where user_id = :id", nativeQuery = true)
//	List<Task> qualquerNome(@Param("id") Long id);
}
