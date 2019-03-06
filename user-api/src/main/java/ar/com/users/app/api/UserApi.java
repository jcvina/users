package ar.com.users.app.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.users.domain.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonRootName(value = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "User data")
public class UserApi
{	
	@JsonProperty
	@ApiModelProperty(notes = "id", required = true)
	private UUID id;
	
	@JsonProperty
	@ApiModelProperty(notes = "Username", required = true)
	private String username;
	
	@JsonProperty
	@ApiModelProperty(notes = "User's first name", required = true)
	private String firstName;
	
	@JsonProperty
	@ApiModelProperty(notes = "User's last name", required = true)
	private String lastName;
	
	@JsonProperty
	@ApiModelProperty(notes = "User's email", required = true)
	private String email;
	
	@JsonProperty
	@ApiModelProperty(notes = "User's gender", required = false)
	private String gender;
	
	@JsonProperty
	@ApiModelProperty(notes = "User's password", required = true)
	private String password;
	
	@JsonProperty
	@ApiModelProperty(notes = "User's birth date", required = false)
	private LocalDate birthDate;
	
	@JsonProperty
	@ApiModelProperty(notes = "User created date", required = false)
	private LocalDateTime createdAt;
	
	@JsonProperty
	@ApiModelProperty(notes = "User updated", required = false)
	private LocalDateTime updatedAt;
	
	
	public UserApi()
	{
		
	}

	public UserApi(UUID id, String username, String firstName, String lastName, String email, String gender, 
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
	
	public UserApi(User user)
	{
		id = user.getId();
		username = user.getUsername();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		gender = user.getGender();
		password = user.getPassword();
		birthDate = user.getBirthDate();
		createdAt = user.getCreatedAt();
		updatedAt = user.getUpdatedAt();
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
