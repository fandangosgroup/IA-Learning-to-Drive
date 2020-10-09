package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class mainController implements Initializable{

	
	@FXML
	private ImageView pista;
	
	@FXML
	private AnchorPane game;
	
	private Sensors sesor;
	private BufferedImage track; 
	private Controller controller;
	private Car car;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.car = new Car();
		game.getChildren().add(car.getImageView());
		controller = new Controller(car.getImageView());
		//provisório
		this.setTrack();
		this.sesor = new Sensors(this.track, this.game);
		
	
	}
	
	private void setTrack() {
		try {
            this.track = ImageIO.read(new File("C:\\Users\\bruno\\Desktop\\pista.png"));
        } catch (IOException ex) {
            
        }
	}
	@FXML
	void direita() {
		controller.moveRight();
		ArrayList<Integer> result = this.sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
		
		
		
		
	}
	@FXML
	void esquerda() {
		controller.moveLeft();
		ArrayList<Integer> result = sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
	}
	@FXML
	void cima() {
		controller.moveUp();
		ArrayList<Integer> result = sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
	}
	@FXML
	void baixo() {
		controller.moveDown();
		ArrayList<Integer> result = sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
	}
	
}
