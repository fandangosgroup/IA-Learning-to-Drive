package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class mainController implements Initializable{
	
	@FXML
	private Button butao;
	
	@FXML
	private ImageView pista;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	}
	@FXML
	void apertei() {
		pista.setLayoutX(pista.getLayoutX() + 40);
	}
	@FXML
	void apertei2() {
		pista.setLayoutX(pista.getLayoutX() - 40);
	}
}
