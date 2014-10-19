package com.dstevens.users;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import static com.dstevens.collections.Maps.map;

@Repository
public class UserDao {

	private static final Map<UserIdentifier, User> users = map();
	
	public Optional<User> loadUser(UserIdentifier userIdentifier) {
		return users.containsKey(userIdentifier) ? Optional.of(users.get(userIdentifier)) : Optional.empty();
	}
	
	public void saveUser(User user) {
		users.put(user.getId(), user);
	}
	
	public void deleteUser(User user) {
		users.remove(user.getId());
	}
	
}
