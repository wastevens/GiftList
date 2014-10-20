package com.dstevens.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

	private final UserIdentifierService service;

	@Autowired
	public UserFactory(UserIdentifierService service) {
		this.service = service;
	}
	
	public User createUser(String email) {
		return new User(service.nextId(), email);
	}
	
}
