package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.UserDatabase;
import environment.Car;
import environment.Collider;
import environment.DrawTrack;
import environment.Sensors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class mainController implements Initializable{

	
	@FXML
	private ImageView pista;
	
	@FXML
	private AnchorPane game;
	
	private Sensors sesor;
	private DrawTrack track; 
	private Controller controller;
	private Car car;
	private Collider collider;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.car = new Car();
		this.track = new DrawTrack(17);
		this.setTrack();
		collider = new Collider(this.track);
		game.getChildren().add(car.getImageView());
		controller = new Controller(this.car);
		this.sesor = new Sensors(this.track.getTrack(), this.game);
	
	}
	
	private void setTrack() {
		ImageView img = this.track.getImgTrack();
		this.game.getChildren().add(0, img);
	}
	@FXML
	void direita() {
		controller.moveRight();
		if(collider.verify(this.car)) {
			this.car.Kill();
		}
		
		ArrayList<Integer> result = this.sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
	}
	@FXML
	void esquerda() {
		controller.moveLeft();
		if(collider.verify(this.car)) {
			this.car.Kill();
		}
		ArrayList<Integer> result = sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
	}
	@FXML
	void cima() {
		controller.moveUp();
		if(collider.verify(this.car)) {
			this.car.Kill();
		}
		ArrayList<Integer> result = sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
	}
	@FXML
	void baixo() {
		controller.moveDown();
		if(collider.verify(this.car)) {
			this.car.Kill();
		}
		ArrayList<Integer> result = sesor.scan((int)car.getImageView().getLayoutX(), (int)car.getImageView().getLayoutY());
	}
	
}
