package application;

import javafx.fxml.FXML;

public class LoginController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void telaCadastro() {
		Main.chageScreen("cadastro");
	}
	
	@FXML 
	public void login() {
		Main.chageScreen("main");
	}

}
