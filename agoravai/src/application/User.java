package application;

import db.UserDatabase;

public class User {
	private Integer id;
	private String login;
	private String password;
	private String email;
	
	public User(Integer id, String login, String password, String email) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public void deleteUser() {
		UserDatabase userDb = new UserDatabase();
		userDb.delete(id);
	}
	
	public String getLogin() {
		return login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
