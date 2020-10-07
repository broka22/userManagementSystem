 package com.vastika.uise.repository;

import java.util.List;

import com.vastika.uise.model.User;

public interface UserRepository {
	
	void saveUser(User user);

	void updateUser(User user);

	void deleteUser(int id);

	List<User> getAllUser();

	User getUserById(int id);
	
	User getUserByUsername(String username);
	
	User getUserByEmail(String email);

}
