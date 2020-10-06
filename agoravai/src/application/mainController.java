package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class mainController implements Initializable{

	
	@FXML
	private ImageView pista;
	
	private Controller c;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	c = new Controller(pista);
		
	}
	@FXML
	void direita() {
		c.moveRight();
	}
	@FXML
	void esquerda() {
		c.moveLeft();
	}
	@FXML
	void cima() {
		c.moveUp();
	}
	@FXML
	void baixo() {
		c.moveDown();
	}
	
}
