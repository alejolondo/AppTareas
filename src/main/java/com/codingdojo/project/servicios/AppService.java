package com.codingdojo.project.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.project.modelos.LoginUser;
import com.codingdojo.project.modelos.Priority;
import com.codingdojo.project.modelos.Task;
import com.codingdojo.project.modelos.User;
import com.codingdojo.project.repositorios.PriorityRepository;
import com.codingdojo.project.repositorios.TaskRepository;
import com.codingdojo.project.repositorios.UserRepository;

@Service
public class AppService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired TaskRepository repoTask;
	
	@Autowired PriorityRepository repoPriority;
	

	//Funcion que registra un usuario
	public User register(User nuevoUsuario, BindingResult result) {
		
		if (!nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm())) {
			result.rejectValue("password", "Matches","Las contraseñas no coinciden");
		}
		
		//Revisar si el correo ya existe
		String nuevoEmail = nuevoUsuario.getEmail();
		if(repository.findByEmail(nuevoEmail).isPresent()) {
			result.rejectValue("email", "unique", "El correo electronico ya exixte");
		}
		
		if(result.hasErrors()) {
			return null;
		}else {
			//Encriptamos la contraseña
			String contraEncriptada = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contraEncriptada);
			return repository.save(nuevoUsuario);
		}
	}
	
	//funcion inicio de sesion
	public User login(LoginUser nuevoLogin, BindingResult result) {
		
		//buscamos por correo electronico
		Optional<User> posibleUsuario = repository.findByEmail(nuevoLogin.getEmail());
		
		if (!posibleUsuario.isPresent()) {
			result.rejectValue("email", "unique", "Correo No registrado");
			return null;
		}
			User userLogin = posibleUsuario.get(); //Este es el usuario que me regresa mi base de datos
			if (!BCrypt.checkpw(nuevoLogin.getPassword(), userLogin.getPassword())) {
				result.rejectValue("password", "Matches", "Contraseña invalida");
			}
			if(result.hasErrors()) {
				return null;
		}else {
			return userLogin;
		}
		
		
	}
	
	//Funcion listar todos los usuarios
	public List<User> findAllUsers(){
		return repository.findAll();
	}

	
	
	//funcion de listar todas las tareas
	public List<Task> findAllTasks(){
		return repoTask.findAll();
	}
	
	
	//Funcion de encontrar tarea por Id
	public Task findTaskById(Long id) {
		return repoTask.findById(id).orElse(null);
	}
	
	//funcion de guardar una tarea
	public Task saveTask(Task task) {
		return repoTask.save(task);
	}
	
	
	//funcion listar todas las prioridades 
	public List<Priority> Allpriorities(){
		return repoPriority.findAll();
	}
	
	public void deleteTask(Long id) {
		repoTask.deleteById(id);
	}
	
	
	

}
