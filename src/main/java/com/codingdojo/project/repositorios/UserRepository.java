package com.codingdojo.project.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.project.modelos.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
