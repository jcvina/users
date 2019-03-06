package ar.com.users.domain.service;

import java.util.List;
import java.util.UUID;

import ar.com.users.domain.exception.UserException;
import ar.com.users.domain.model.User;

public interface UserService
{
	List<User> getAll() throws UserException;
	User getByUsername(String username) throws UserException;
	User getByEmail(String email) throws UserException;
	User create(User user) throws UserException;
	User update(User user, String username) throws UserException;
	void delete(User user) throws UserException;
}
