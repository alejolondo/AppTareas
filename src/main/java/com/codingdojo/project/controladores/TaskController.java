package com.codingdojo.project.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.project.modelos.Priority;
import com.codingdojo.project.modelos.Task;
import com.codingdojo.project.modelos.User;
import com.codingdojo.project.servicios.AppService;

@Controller
public class TaskController {

	@Autowired
	private AppService service;
	
	
	@GetMapping("/tasks/new")
	public String newTask(@ModelAttribute("task") Task task, HttpSession session, Model model) {
		
		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}else {
			
			List<Priority> allPriorities = service.Allpriorities();
			model.addAttribute("priorities", allPriorities);
			
			List<User> allUsers = service.findAllUsers();
			model.addAttribute("users", allUsers);
			return "new.jsp";
		}
	}
	
	@PostMapping("/tasks/new")
	public String saveTask(@Valid @ModelAttribute("task")Task task, 
							BindingResult result, HttpSession session) {
		

		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}
		
		if (result.hasErrors()) {
			return "new.jsp";
		}else {
			service.saveTask(task);
			return "redirect:/tasks";
		}
	}
	
	@GetMapping("/task/{id}")
	public String seeTask(@PathVariable("id")Long id, Model model, HttpSession session) {
		

		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}else {
			Task currentTask = service.findTaskById(id);
			model.addAttribute("task", currentTask);
			return "task.jsp";
		}
	}
	
	@GetMapping("/task/edit/{id}")
	public String edit(@PathVariable("id")Long id, @ModelAttribute("task")Task task, HttpSession session, Model model) {
		

		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}
		
		Task taskEdit = service.findTaskById(id);
		
		if (currentUser.getId() != taskEdit.getCreator().getId()) {
			return "redirect:/tasks";
		}
		List<Priority> allPriorities = service.Allpriorities();
		model.addAttribute("priorities", allPriorities);
		
		List<User> allUsers = service.findAllUsers();
		model.addAttribute("users", allUsers);
		
		model.addAttribute("task", taskEdit);
		
		return "edit.jsp";
	}
	
	@PutMapping("/tasks/edit")
	public String saveEdit(@Valid @ModelAttribute("task")Task task, BindingResult result, HttpSession session, Model model) {
		
		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}
		
		if (result.hasErrors()) {
			List<Priority> allPriorities = service.Allpriorities();
			model.addAttribute("priorities", allPriorities);
			
			List<User> allUsers = service.findAllUsers();
			model.addAttribute("users", allUsers);
			
			
			return "edit.jsp";
			
		} else {
			
			service.saveTask(task);
			return "redirect:/tasks";
		}
		
		
		
	}
	
	@DeleteMapping("/task/delete/{id}")
	public String delete(@PathVariable("id")Long id, HttpSession session ){
		
		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}else {
			
			service.deleteTask(id);
			return "redirect:/tasks";
		}

	}
	@DeleteMapping("/task/complete/{id}")
	public String completeTask(@PathVariable("id")Long id, HttpSession session) {
		
		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}else {
			
			service.deleteTask(id);
			return "redirect:/tasks";
		}
	}
	
	
}
