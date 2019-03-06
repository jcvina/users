package ar.com.users.app.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ApiErrorTest
{
	private ApiError apiError;
	
	@Test
	public void testInit()
	{
		apiError = ApiErrorFixture.withDefaults();
		assertNotNull(apiError);
		assertNotNull(apiError.getStatus());
		assertNotNull(apiError.getCode());
		assertNotNull(apiError.getMessage());
		assertFalse(apiError.equals(null));
		assertTrue(apiError.getCode().equals(ApiErrorFixture.code));
		assertTrue(apiError.getMessage().equals(ApiErrorFixture.message));
		assertTrue(apiError.getStatus().equals(HttpStatus.OK));
		assertNotNull(apiError.toString());
	}

	@Test
	public void testSet()
	{
		apiError = new ApiError(HttpStatus.OK);
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setCode(ApiErrorFixture.code);
		apiError.setMessage(ApiErrorFixture.message);
		apiError.setDebugMessage(ApiErrorFixture.debugMessage);
		apiError.setTimestamp(LocalDateTime.now());
		assertNotNull(apiError);
		assertEquals(apiError.getStatus(), HttpStatus.BAD_REQUEST);
		assertEquals(apiError.getCode(), ApiErrorFixture.code);
		assertEquals(apiError.getMessage(), ApiErrorFixture.message);
		assertEquals(apiError.getDebugMessage(), ApiErrorFixture.debugMessage);
		assertNotNull(apiError.getTimestamp());
		
		apiError = new ApiError(HttpStatus.OK, ApiErrorFixture.code, ApiErrorFixture.message);
	}
}
