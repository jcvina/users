package ar.com.users.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.users.app.api.ApiError;
import ar.com.users.app.api.MessageApi;
import ar.com.users.app.api.UserApi;
import ar.com.users.app.support.converter.UserApiToUserConverter;
import ar.com.users.domain.exception.UserException;
import ar.com.users.domain.model.User;
import ar.com.users.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@RequestMapping(path = "/users")
@Api(value = "Service to administrate users")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserApiToUserConverter userApiToUserConverter;
	
	@ApiOperation(value = "Get all users.", response = UserApi.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Users list."), 
			@ApiResponse(code = 406, message = "Error obtaining users.", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal error.", response = ApiError.class), })
	@GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> get()
	{
		try
		{
			List<UserApi> usersApi = new ArrayList<UserApi>();
			List<User> users = userService.getAll();
			  
			for (User user : users)
			{
				usersApi.add(new UserApi(user));
			}
			
			return new ResponseEntity<List>(usersApi, HttpStatus.OK);
		}
		catch (UserException e)
		{
			return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, "", e.getMessage(), e), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@ApiOperation(value = "Get an user by username.", response = UserApi.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User."),
			@ApiResponse(code = 404, message = "Uset not found.", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal error.", response = ApiError.class), })
	@GetMapping(path = "/getByUsername/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> get(
			@ApiParam(value = "Username to search.", required = true) @PathVariable("username") final String username) throws UserException
	{
		try
		{
			User user = userService.getByUsername(username);
			UserApi userApi = new UserApi(user);
		
			return new ResponseEntity<UserApi>(userApi, HttpStatus.OK);
		}
		catch (UserException ex)
		{
			return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, "", ex.getMessage(), ex), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@ApiOperation(value = "Get an user by email.", response = UserApi.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User."), 
			@ApiResponse(code = 404, message = "User not found.", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal error.", response = ApiError.class), })
	@GetMapping(path = "getByEmail/{email:.+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getByEmail(
			@ApiParam(value = "Email to search.", required = true) @PathVariable("email") final String email) throws UserException
	{
		try
		{
			User user = userService.getByEmail(email);
			UserApi userApi = new UserApi(user);
			
			return new ResponseEntity<UserApi>(userApi, HttpStatus.OK);
		}
		catch (UserException ex)
		{
			return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, "", ex.getMessage(), ex), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@ApiOperation(value = "Creates an user.", response = UserApi.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User created."), 
			@ApiResponse(code = 406, message = "Error creating user.", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal error.", response = ApiError.class), })
	@PostMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@ApiParam(value = "User data.", required = true) @RequestBody UserApi userApi) throws Exception
	{
		try
		{
			User user = userApiToUserConverter.convert(userApi);
			user = userService.create(user);
			userApi = new UserApi(user);
			
			return new ResponseEntity<UserApi>(userApi, HttpStatus.OK);
		}
		catch (UserException ex)
		{
			return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, "", ex.getMessage(), ex), HttpStatus.NOT_ACCEPTABLE);
		}
		catch (DataIntegrityViolationException ex)
		{
			return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, "User not created.", ex.getMessage(), ex), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@ApiOperation(value = "Deletes an user.", response = MessageApi.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User deleted."), 
			@ApiResponse(code = 403, message = "Access denied.", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal error.", response = ApiError.class), })
	@DeleteMapping(path = "/delete/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteUser(@ApiParam(value = "Username.", required = true) @PathVariable("username") final String username) throws Exception
	{
		try
		{
			userService.delete(userService.getByUsername(username));
			return new ResponseEntity<MessageApi>(new MessageApi("User deleted."), HttpStatus.OK);
		}
		catch (UserException ex)
		{
			return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, "", ex.getMessage(), ex), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@ApiOperation(value = "Updates user data.", response = UserApi.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User updated."), 
			@ApiResponse(code = 403, message = "Error updating user.", response = ApiError.class),
			@ApiResponse(code = 500, message = "Internal error.", response = ApiError.class), })
	@PutMapping(path = "/update/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateUser(
			@ApiParam(value = "Username to update.", required = true) @PathVariable("username") final String username,
			@ApiParam(value = "User's data.", required = true) @RequestBody UserApi userApi) throws Exception
	{
		try
		{
			User user = userApiToUserConverter.convert(userApi);
			user = userService.update(user, username);
			userApi = new UserApi(user);
			return new ResponseEntity<UserApi>(userApi, HttpStatus.OK);
		}
		catch (UserException ex)
		{
			return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, "", ex.getMessage(), ex), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
