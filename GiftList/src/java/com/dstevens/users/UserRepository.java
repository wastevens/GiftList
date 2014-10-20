package com.dstevens.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private final UserDao userDao;
	private final UserFactory userFactory;

	@Autowired
	public UserRepository(UserDao userDao, UserFactory userFactory) {
		this.userDao = userDao;
		this.userFactory = userFactory;
	}
	
	public Optional<User> findUser(UserIdentifier userIdentifier) {
		return userDao.loadUser(userIdentifier);
	}
	
	public boolean isUser(UserIdentifier userIdentifier) {
		return userDao.userExists(userIdentifier);
	}
	
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}
	
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}
	
	public User createUser(String email) {
		return saveUser(userFactory.createUser(email));
	}
	
	
	
}
