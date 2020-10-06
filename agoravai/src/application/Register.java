package application;

import db.UserDatabase;

public class Register{
	
	private User user = null;
	
	public Register (User user) {
		this.user = user;
	}
	
	public void register() {
		UserDatabase userDb = new UserDatabase();
		userDb.insert(this.user);
	}
}
