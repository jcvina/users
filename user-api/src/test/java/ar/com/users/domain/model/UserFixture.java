package ar.com.users.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserFixture
{
	public static UUID id = UUID.fromString("c3a81dfa-ad7a-4b66-af51-dd51ce28f916");
	public static String username = "juan.cruz";
	public static String password = "123456";
	public static String email = "juan.cruz@gmail.com";
	public static String firstName = "Juan";
	public static String lastName = "Cruz";
	public static String gender = "M";
	public static LocalDate birthDate = LocalDate.now();
	public static LocalDateTime createdAt = LocalDateTime.now();
	public static LocalDateTime updatedAt = LocalDateTime.now();

	private User build()
	{
		User user = new User(id, username, firstName, lastName, email, gender, password, birthDate, createdAt, updatedAt);
		return user;
	}

	public static User withDefaults()
	{
		return new UserFixture().build();
	}
}