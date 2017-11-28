package domain.repository;

import java.util.List;

import domain.User;

public interface UserRepository {
	List<User> getAllUsers();
	void addUser(User user);
	void deleteUser(Long userId);
}
