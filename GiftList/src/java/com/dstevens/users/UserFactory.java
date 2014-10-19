package com.dstevens.users;

import org.springframework.stereotype.Service;

@Service
public class UserFactory {

	public User createUser(String email) {
		return new User(email);
	}
	
}
