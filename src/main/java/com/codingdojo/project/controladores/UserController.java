package com.codingdojo.project.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.project.modelos.LoginUser;
import com.codingdojo.project.modelos.Task;
import com.codingdojo.project.modelos.User;
import com.codingdojo.project.servicios.AppService;

@Controller
public class UserController {

	
	@Autowired
	private AppService service;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario")User nuevoUsuario,
			@ModelAttribute("nuevoLogin")LoginUser nuevoLogin) {
		
		
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("nuevoUsuario")User nuevoUsuario,
							BindingResult result, Model model, HttpSession session) {
		service.register(nuevoUsuario, result);
		if(result.hasErrors()) {
			model.addAttribute("nuevoLogin", new LoginUser());
			return "index.jsp";
		}else {
			session.setAttribute("userSession", nuevoUsuario);
			return "redirect:/tasks";
		}
		
	}
	
	@GetMapping("tasks")
	public String dashboard(HttpSession session, Model model) {
		
		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		} 
		
		List<Task> allTasks = service.findAllTasks();
		model.addAttribute("tasks", allTasks);
		

		
		return "dashboard.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("nuevoLogin")LoginUser nuevoLogin, BindingResult result,
						Model model, HttpSession session) {
		
		User user = service.login(nuevoLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("nuevoUsuario", new User());
			return "index.jsp";
		}else {
		session.setAttribute("userSession", user);
		return "redirect:/tasks";
		}
		
	}
	
	@GetMapping("/logout")
	 public String logout(HttpSession session) {
	  session.removeAttribute("userSession");
	  return "redirect:/";
	 }
}