package ar.com.users.domain.exception;

public class UserException extends Exception
{
	public static final String USER_NOT_EXIST = "User doens't exist.";
	public static final String USERNAME_TAKEN = "Username already taken.";
	public static final String EMAIL_TAKEN = "Email already taken.";
	
	public UserException(String message)
	{
		super(message);
	}
}
