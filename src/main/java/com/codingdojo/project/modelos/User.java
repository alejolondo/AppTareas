package com.codingdojo.project.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El campo de nombre es obligatorio")
	@Size(min=2, max=30, message="El nombre debe tener entre 2 y 30 caracteres")
	private String name;
	
	
	@NotEmpty(message="El campo de email es obligatorio")
	@Email(message="Ingrese un correo electronico válido")
	private String email;
	
	@NotEmpty(message="El campo de password es obligatorio")
	@Size(min=8, max=128)
	private String password;
	
	
	@Transient 
	@NotEmpty(message="El campo de confirmación es obligatorio")
	@Size(min=8, max=128)
	private String confirm;
	
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	
	@OneToMany(mappedBy = "creator" , fetch = FetchType.LAZY)
	private List<Task> myTasks;

	
	@OneToMany(mappedBy = "assigned", fetch = FetchType.LAZY)
	private List<Task> assignments;
	
	public User() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	public List<Task> getMyTasks() {
		return myTasks;
	}


	public void setMyTasks(List<Task> myTasks) {
		this.myTasks = myTasks;
	}


	public List<Task> getAssignments() {
		return assignments;
	}


	public void setAssignments(List<Task> assignments) {
		this.assignments = assignments;
	}


	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate 
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
	
}