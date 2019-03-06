package ar.com.users.app.support.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.com.users.app.api.UserApi;
import ar.com.users.domain.model.User;

@Component
public class UserApiToUserConverter implements Converter<UserApi, User>
{
	@Override
	public User convert(UserApi userApi)
	{
		User user = new User();
		
		user.setId(userApi.getId());
		user.setUsername(userApi.getUsername());
		user.setFirstName(userApi.getFirstName());
		user.setLastName(userApi.getLastName());
		user.setEmail(userApi.getEmail());
		user.setGender(userApi.getGender());
		user.setPassword(userApi.getPassword());
		user.setBirthDate(userApi.getBirthDate());
		
		return user;
	}
}
