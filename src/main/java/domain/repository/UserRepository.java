package domain.repository;

import java.util.List;

import domain.User;

public interface UserRepository {
	List<User> getAllUsers();
	User findUserByName(String name);
	void addUser(User user);
	void deleteUser(Long userId);
}
