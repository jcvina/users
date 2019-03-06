package ar.com.users.app.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserApiFixture
{
	public static UUID id = UUID.fromString("c3a81dfa-ad7a-4b66-af51-dd51ce28f916");
	private String username = "juan.cruz";
	private String password = "123456";
	private String email = "juan.cruz@gmail.com";
	private String firstName = "Juan";
	private String lastName = "Cruz";
	private String gender = "M";
	private LocalDate birthDate = LocalDate.now();
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt = LocalDateTime.now();
	
	
	private UserApi build()
	{
		UserApi user = new UserApi(id, username, firstName, lastName, email, gender, password, birthDate, createdAt, updatedAt);
		return user;
	}
	
	public static UserApi withDefaults()
	{
		return new UserApiFixture().build();
	}
}
