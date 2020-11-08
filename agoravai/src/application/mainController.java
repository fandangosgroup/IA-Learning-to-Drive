package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import ai.EvolutionAlgorithm;
import ai.HeatDispersion;
import ai.NeuralNetwork;
import db.UserDatabase;
import environment.Car;
import environment.Collider;
import environment.DrawTrack;
import environment.Sensors;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class mainController implements Initializable{

	
	@FXML
	private ImageView pista;
	
	@FXML
	private AnchorPane game;
	
	private Sensors sensor;
	private DrawTrack track; 
	private Controller controller;
	private ArrayList<Car> car;
	private Collider collider;
	private NeuralNetwork nn;
	private HeatDispersion hd;
	private EvolutionAlgorithm ea;
	private Timeline tl;
	private int carnumber = 1000;

	private int gambi = 0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ea = new EvolutionAlgorithm(carnumber, 7, 100, game);
		this.car = ea.getGeneration();
		
		
		//System.out.println(game.getChildren().toString());
		this.nn = new NeuralNetwork();
		this.track = new DrawTrack(UserDatabase.getId() - 1);
		this.setTrack();
		collider = new Collider(this.track);
		
		controller = new Controller();
		this.sensor = new Sensors(this.track.getTrack(), this.game);
		hd = new HeatDispersion(this.track, this.game);
		this.hd.setPointerMatrix(track, game);	
		
		this.tl = new Timeline(
		new KeyFrame(Duration.millis(100), e -> this.pensa())
        );
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
	
	}
	
	private void setTrack() {
		ImageView img = this.track.getImgTrack();
		this.game.getChildren().add(0, img);
	}
	
	@FXML
	void direita(Car car) {
		controller.moveRight(car);
	}
	@FXML
	void esquerda(Car car) {
		controller.moveLeft(car);
	}
	@FXML
	void cima(Car car) {
		controller.moveUp(car);
	}
	@FXML
	void baixo(Car car) {
		controller.moveDown(car);
	}
	//DEBUG PROVISÒRIO
	@FXML
	void pensa() {
		
		for(int i =0; i < this.car.size(); i++) {
			ArrayList<Double> scan = new ArrayList<Double>();
			ArrayList<Double> sinapse = new ArrayList<Double>();
			if(this.car.get(i).getIsDead().equals(false)) {
				scan = sensor.scan((int)car.get(i).getImageView().getLayoutX(), (int)car.get(i).getImageView().getLayoutY());
				sinapse = this.nn.getSinapse(scan, car.get(i));
			
				if(sinapse.get(0) > sinapse.get(1) && sinapse.get(0) > sinapse.get(2) && sinapse.get(0) > sinapse.get(3)) {
					if(!this.collider.verify(this.car.get(i)))
						this.cima(this.car.get(i));	
				}
				if(sinapse.get(1) > sinapse.get(0) && sinapse.get(1) > sinapse.get(2) && sinapse.get(1) > sinapse.get(3)) {
					if(!this.collider.verify(this.car.get(i)))
					this.direita(this.car.get(i));
				}
				if(sinapse.get(2) > sinapse.get(0) && sinapse.get(2) > sinapse.get(1) && sinapse.get(2) > sinapse.get(3)) {

					if(!this.collider.verify(this.car.get(i)))
					this.esquerda(this.car.get(i));
				}
				if(sinapse.get(3) > sinapse.get(0) && sinapse.get(3) > sinapse.get(1) && sinapse.get(3) > sinapse.get(2)) {
					if(!this.collider.verify(this.car.get(i)))
					this.baixo(this.car.get(i));
				}
			
			}

		}
	}
}
