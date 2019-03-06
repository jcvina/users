package ar.com.users.app.api;

import org.springframework.http.HttpStatus;

public class ApiErrorFixture
{
	public static String code = "CODE";
	public static String message = "MESSAGE";
	public static String debugMessage = "DEBUG_MESSAGE";
	
	
	private ApiError build()
	{
		return new ApiError(HttpStatus.OK, code, message, null);
	}
	
	public static ApiError withDefaults()
	{
		return new ApiErrorFixture().build();
	}
}
