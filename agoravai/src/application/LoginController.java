package application;

import db.UserDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	TextField user;
	
	@FXML
	TextField pass;

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void telaCadastro() {
		Main.chageScreen("cadastro");
	}
	
	@FXML 
	public void login() {
		System.out.println(user.getText());
		System.out.println(pass.getText());
		
		
		try {
			User u = UserDatabase.getUser(user.getText(), pass.getText());
			
			Main.usuario = u;
			
			System.out.println(u.getEmail());
			
			if(u.getEmail().length() > 2) {
				Main.chageScreen("main");
			}
		} catch (Exception e) {
			System.out.println("FUDEUD NV !");
		}
	
		
	}

}
