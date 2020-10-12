package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import ai.NeuralNetwork;
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
	private ArrayList<Car> car;
	private Collider collider;
	private NeuralNetwork nn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.car = new ArrayList<Car>();
		for(int i = 0; i < 1000; i++) {
			this.car.add(new Car(i));
			game.getChildren().add(car.get(i).getImageView());
		}
		this.nn = new NeuralNetwork();
		this.track = new DrawTrack(UserDatabase.getId() - 1);
		this.setTrack();
		collider = new Collider(this.track);
		
		controller = new Controller();
		this.sesor = new Sensors(this.track.getTrack(), this.game);
	
	}
	
	private void setTrack() {
		ImageView img = this.track.getImgTrack();
		this.game.getChildren().add(0, img);
	}
	
	@FXML
	void direita(Car car) {
		controller.moveRight(car);
		if(collider.verify(car)) {
			car.Kill();
		}
	
	}
	@FXML
	void esquerda(Car car) {
		controller.moveLeft(car);
		if(collider.verify(car)) {
			car.Kill();
		}
	}
	@FXML
	void cima(Car car) {
		controller.moveUp(car);
		if(collider.verify(car)) {
			car.Kill();
		}
	}
	@FXML
	void baixo(Car car) {
		controller.moveDown(car);
		if(collider.verify(car)) {
			car.Kill();
		}
	}
	//DEBUG PROVISÒRIO
	@FXML
	void pensa() {
		for(int i =0; i < this.car.size(); i++) {
			ArrayList<Double> scan = sesor.scan((int)car.get(i).getImageView().getLayoutX(), (int)car.get(i).getImageView().getLayoutY());
			ArrayList<Double> sinapse = this.nn.getSinapse(scan, car.get(i));
			
			if(sinapse.get(0) > sinapse.get(1) && sinapse.get(0) > sinapse.get(2) && sinapse.get(0) > sinapse.get(3)) {
				this.cima(this.car.get(i));
			}
			if(sinapse.get(1) > sinapse.get(0) && sinapse.get(1) > sinapse.get(2) && sinapse.get(1) > sinapse.get(3)) {
				this.direita(this.car.get(i));
			}
			if(sinapse.get(2) > sinapse.get(0) && sinapse.get(2) > sinapse.get(1) && sinapse.get(2) > sinapse.get(3)) {
				this.esquerda(this.car.get(i));
			}
			if(sinapse.get(3) > sinapse.get(0) && sinapse.get(3) > sinapse.get(1) && sinapse.get(3) > sinapse.get(2)) {
				this.baixo(this.car.get(i));
			}
		}
	}
}
