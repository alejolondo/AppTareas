package com.codingdojo.project.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.project.modelos.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	

}
