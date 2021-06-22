package ar.com.users.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }), @UniqueConstraint(columnNames = { "email" }) })
public class User
{
	@Id
	@Column(name = "id", columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@Column(name = "username", length = 30, nullable = false)
	private String username;
	
	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "gender", length = 1, nullable = false)
	private String gender;
	
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "birth_date", nullable = true, columnDefinition = "DATE")
	private LocalDate birthDate;
	
	@Column(name = "created_at", nullable = true, columnDefinition = "DATETIME")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = true, columnDefinition = "DATETIME")
	private LocalDateTime updatedAt;
	
	
	public User()
	{
		
	}
	


	public User(UUID id, String username, String firstName, String lastName, String email, String gender, 
			String password, LocalDate birthDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.birthDate = birthDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
