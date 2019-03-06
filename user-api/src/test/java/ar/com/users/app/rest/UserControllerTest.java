package ar.com.users.app.rest;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ar.com.users.app.api.MessageApi;
import ar.com.users.app.api.UserApi;
import ar.com.users.app.api.UserApiFixture;
import ar.com.users.app.support.converter.UserApiToUserConverter;
import ar.com.users.domain.exception.UserException;
import ar.com.users.domain.model.User;
import ar.com.users.domain.model.UserFixture;
import ar.com.users.domain.repository.UserRepository;
import ar.com.users.domain.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
@EnableWebMvc
public class UserControllerTest
{
	private MockMvc mockMvc;
	ObjectMapper mapper;
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserApiToUserConverter userApiToUserConverter;

	@Mock
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	private static String user_id = "c3a81dfa-ad7a-4b66-af51-dd51ce28f916";
	
	@Before
	public void setup()
	{
		mockMvc =
		MockMvcBuilders.standaloneSetup(userController)
	            .setControllerAdvice(new Exception())
	            .build();
	    mapper = new ObjectMapper()
	                .findAndRegisterModules()
	                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
	                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
	                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

	}
	
	@Test
	public void getUsers() throws Exception
	{
		
		when(userService.getAll()).thenReturn(newArrayList(UserFixture.withDefaults()));

		MvcResult result = mockMvc.perform(get("/users/"))
				.andExpect(status().isOk())
				.andReturn();
		
		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
		Object object = mapper.readValue(response.getBytes(), List.class);
		assertTrue(object instanceof List);
		assertEquals(((List)object).size(), 1);
		
	}
	
	@Test
	public void getUsersWithError() throws Exception
	{
		when(userService.getAll()).thenThrow(new ar.com.users.domain.exception.UserException(""));

		mockMvc.perform(get("/users/"))
			.andExpect(status().isNotAcceptable());

	}

	@Test
	public void getUserByUserName() throws Exception
	{
		when(userService.getByUsername(anyString())).thenReturn(UserFixture.withDefaults());

		MvcResult result = mockMvc.perform(get("/users/getByUsername/" + UserFixture.username))
				.andExpect(status().isOk())
				.andReturn();
		
		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
		Object object = mapper.readValue(response.getBytes(), UserApi.class);
		assertTrue(object instanceof UserApi);
	}
	
	@Test
	public void getUserByUserNameWithError() throws Exception
	{
		when(userService.getByUsername(anyString())).thenThrow(new ar.com.users.domain.exception.UserException(UserException.USER_NOT_EXIST));

		mockMvc.perform(get("/users/getByUsername/juan"))
		.andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void getUserByEmail() throws Exception
	{
		when(userService.getByEmail(anyString())).thenReturn(UserFixture.withDefaults());

		MvcResult result = mockMvc.perform(get("/users/getByEmail/" + UserFixture.email))
				.andExpect(status().isOk())
				.andReturn();
		
		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
		Object object = mapper.readValue(response.getBytes(), UserApi.class);
		assertTrue(object instanceof UserApi);
	}
	
	@Test
	public void getUserByEmailWithError() throws Exception
	{
		when(userService.getByEmail(anyString())).thenThrow(new ar.com.users.domain.exception.UserException(UserException.USER_NOT_EXIST));

		mockMvc.perform(get("/users/getByEmail/" + UserFixture.email))
		.andExpect(status().isNotAcceptable());
	}

	
	@Test
	public void createUserBadRequest() throws Exception
	{
		User user = UserFixture.withDefaults();

		MvcResult result = mockMvc.perform(
				post("/users/").contentType(MediaType.APPLICATION_JSON).content("{{" + asJsonString(UserApiFixture.withDefaults())))
		.andExpect(status().isBadRequest()).andReturn();
	}
	
	@Test
	public void createUserWithError() throws Exception
	{
		User user = UserFixture.withDefaults();
		when(userApiToUserConverter.convert(UserApiFixture.withDefaults())).thenReturn(user);
		when(userService.create(anyObject())).thenThrow(new ar.com.users.domain.exception.UserException(""));
		
		mockMvc.perform(
				post("/users/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(UserApiFixture.withDefaults())))
		.andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void createUserWithError1() throws Exception
	{
		User user = UserFixture.withDefaults();
		when(userApiToUserConverter.convert(UserApiFixture.withDefaults())).thenReturn(user);
		when(userService.create(anyObject())).thenThrow(new UserException(""));
		
		mockMvc.perform(
				post("/users/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(UserApiFixture.withDefaults())))
		.andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void updateUser() throws Exception
	{
		User user = UserFixture.withDefaults();
		when(userApiToUserConverter.convert(anyObject())).thenReturn(UserFixture.withDefaults());
		when(userService.update(anyObject(), anyString())).thenReturn(user);

		MvcResult result = mockMvc.perform(
				put("/users/update/" + user.getUsername()).contentType(MediaType.APPLICATION_JSON).content(asJsonString(UserApiFixture.withDefaults())))
		.andExpect(status().isOk())
		.andReturn();
		
		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
		Object object = mapper.readValue(response.getBytes(), UserApi.class);
		assertTrue(object instanceof UserApi);
	}
	
	@Test
	public void updateUserNotFound() throws Exception
	{
		User user = UserFixture.withDefaults();
		when(userApiToUserConverter.convert(anyObject())).thenReturn(UserFixture.withDefaults());
		when(userService.update(anyObject(), anyString())).thenThrow(new ar.com.users.domain.exception.UserException(UserException.USER_NOT_EXIST));

		
		mockMvc.perform(put("/users/" + user.getUsername()).contentType(MediaType.APPLICATION_JSON).content(asJsonString( UserApiFixture.withDefaults())))
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void updateUserWithError() throws Exception
	{
		User user = UserFixture.withDefaults();
		when(userApiToUserConverter.convert(anyObject())).thenReturn(UserFixture.withDefaults());
		when(userService.update(anyObject(), anyString())).thenThrow(new ar.com.users.domain.exception.UserException(""));

		
		mockMvc.perform(put("/users/update/" + user.getUsername()).contentType(MediaType.APPLICATION_JSON).content(asJsonString( UserApiFixture.withDefaults())))
			.andExpect(status().isNotAcceptable());
		
	}

	@Test
	public void deleteUser() throws Exception
	{
		User user = UserFixture.withDefaults();

		MvcResult result = mockMvc.perform(
				delete("/users/delete/" + user.getUsername()))
		.andExpect(status().isOk())
		.andReturn();
		
		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
		Object object = mapper.readValue(response.getBytes(), MessageApi.class);
		assertTrue(object instanceof MessageApi);
	}
	
	/*
     * converts a Java object into JSON representation
     */
    public  String asJsonString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
