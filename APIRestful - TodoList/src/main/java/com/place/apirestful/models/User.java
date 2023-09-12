package com.place.apirestful.models;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_users")
public class User {

	public interface CreateUser{}
	
	public interface UpdateUser{}
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false, unique = true, updatable = false)
	@NotBlank(groups = CreateUser.class) //= NotNull + NotEmpty
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)  //somente leitura
	@Column(length = 8, nullable = false, unique = false)
	@NotBlank(groups = {CreateUser.class, UpdateUser.class})
	@Size(groups = {CreateUser.class, UpdateUser.class}, min = 4, max = 8)
	private String password;
	
	public User() {
		
	}

	public User(Long id, @NotBlank(groups = CreateUser.class) String username,
			@NotBlank(groups = { CreateUser.class, UpdateUser.class }) @Size(groups = { CreateUser.class,
					UpdateUser.class }, min = 4, max = 8) String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;	
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	

	
	
}
