package ar.com.users.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import ar.com.users.domain.exception.UserException;
import ar.com.users.domain.model.User;
import ar.com.users.domain.repository.UserRepository;
import ar.com.users.domain.service.UserService;

@Service
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAll() throws UserException {
		return userRepository.findAll();
	}

	@Override
	public User getByUsername(String username) throws UserException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UserException(UserException.USER_NOT_EXIST);
		}
		return user;
	}
	
	@Override
	public User getByEmail(String email) throws UserException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException(UserException.USER_NOT_EXIST);
		}
		return user;
	}

	@Override
	public User create(User user) throws UserException {
		if (userRepository.findByUsername(user.getUsername()) != null)
		{
			throw new UserException(UserException.USERNAME_TAKEN);
		}
		if (userRepository.findByEmail(user.getEmail()) != null)
		{
			throw new UserException(UserException.EMAIL_TAKEN);
		}
		
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		
		user = userRepository.saveAndFlush(user);
		return user;
	}

	@Override
	public User update(User user, String username) throws UserException {
		User persistUser = getByUsername(username);
		
		validateUpdate(user.getUsername(), user.getEmail());
		
		if (null != user.getUsername())
		{
			persistUser.setUsername(user.getUsername());
		}
		if (null != user.getFirstName())
		{
			persistUser.setFirstName(user.getFirstName());
		}
		if (null != user.getLastName())
		{
			persistUser.setLastName(user.getLastName());
		}
		if (null != user.getEmail())
		{
			persistUser.setEmail(user.getEmail());
		}
		if (null != user.getGender())
		{
			persistUser.setGender(user.getGender());
		}
		if (null != user.getPassword())
		{
			persistUser.setPassword(user.getPassword());
		}
		if (null != user.getBirthDate())
		{
			persistUser.setBirthDate(user.getBirthDate());
		}
		
		persistUser.setUpdatedAt(LocalDateTime.now());
		
		user = userRepository.saveAndFlush(persistUser);
		return user;
	}

	@Override
	public void delete(User user) throws UserException {
		userRepository.delete(user);
	}
	
	private void validateUpdate(String username, String email) throws UserException
	{
		User user = userRepository.findByUsername(username);
		if (null != user)
		{
			throw new UserException(UserException.USERNAME_TAKEN);
		}
		
		user = userRepository.findByEmail(email);
		if (null != user)
		{
			throw new UserException(UserException.EMAIL_TAKEN);
		}
	}
}
