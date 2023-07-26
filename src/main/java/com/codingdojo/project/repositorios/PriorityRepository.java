package com.codingdojo.project.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.project.modelos.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
