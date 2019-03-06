package ar.com.users.app.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserApiTest
{
	private UserApi userApi;
	
	@Test
	public void testInit()
	{
		this.userApi = UserApiFixture.withDefaults();
		assertNotNull(this.userApi);
		assertNotNull(this.userApi.getId());
		assertNotNull(this.userApi.getUsername());
		assertNotNull(this.userApi.getPassword());
		assertNotNull(this.userApi.getEmail());
		assertNotNull(this.userApi.getFirstName());
		assertNotNull(this.userApi.getLastName());
		assertNotNull(this.userApi.getGender());
		assertNotNull(this.userApi.getBirthDate());
		assertNotNull(this.userApi.getCreatedAt());
		assertNotNull(this.userApi.getUpdatedAt());
		assertFalse(this.userApi.equals(null));
	}
	
	@Test
	public void testSet()
	{
		this.userApi = new UserApi();
		UserApi user = UserApiFixture.withDefaults();
		this.userApi.setId(user.getId());
		this.userApi.setUsername(user.getUsername());
		this.userApi.setPassword(user.getPassword());
		this.userApi.setEmail(user.getEmail());
		this.userApi.setFirstName(user.getFirstName());
		this.userApi.setLastName(user.getLastName());
		this.userApi.setGender(user.getGender());
		this.userApi.setBirthDate(user.getBirthDate());
		this.userApi.setCreatedAt(user.getCreatedAt());
		this.userApi.setUpdatedAt(user.getUpdatedAt());
		
		assertNotNull(userApi);
		assertEquals(this.userApi.getId(), user.getId());
		assertEquals(this.userApi.getUsername(), user.getUsername());
		assertEquals(this.userApi.getPassword(), user.getPassword());
		assertEquals(this.userApi.getEmail(), user.getEmail());
		assertEquals(this.userApi.getFirstName(), user.getFirstName());
		assertEquals(this.userApi.getLastName(), user.getLastName());
		assertEquals(this.userApi.getGender(), user.getGender());
		assertEquals(this.userApi.getBirthDate(), user.getBirthDate());
		assertEquals(this.userApi.getCreatedAt(), user.getCreatedAt());
		assertEquals(this.userApi.getUpdatedAt(), user.getUpdatedAt());
	}
}
