package application;

import java.sql.Connection;
import java.sql.SQLException;

import db.ConnectionFactory;
import db.UserDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroController {
	
	@FXML
	TextField user;
	
	@FXML
	TextField email;
	
	@FXML
	TextField senha;

	public CadastroController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void telaLogin() {
		Main.chageScreen("login");
	}
	
	@FXML
	public void cadastra() {
		
		try(Connection conn = ConnectionFactory.getConnetion()) {
			User u = new User(UserDatabase.getId(), user.getText(), senha.getText(), email.getText());
			Register rg = new Register(u);
			rg.register();
			
			Popup.display();
		} catch (SQLException | NullPointerException e) {
			Popup.displayError("Não foi possivel cadastrar!");
		}
		
		
	}
}
