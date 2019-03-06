package ar.com.users.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.users.domain.model.User;

public interface UserRepository extends JpaRepository<User, UUID>
{
	User findByUsername(String username);
	User findByEmail(String email);
}