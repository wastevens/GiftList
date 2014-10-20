package com.dstevens.users;

import org.springframework.stereotype.Service;

@Service
public class UserIdentifierService {

	private static int currentId = 0;
	
	public UserIdentifier nextId() {
		if (currentId >= 5) {
			throw new IllegalStateException("Cannot exceed id 5");
		}
		return new UserIdentifier(++currentId);
	}
	
}
