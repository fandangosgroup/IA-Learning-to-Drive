package application;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import ai.EvolutionAlgorithm;
import ai.NeuralNetwork;
import ai.WaveFront;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class mainController implements Initializable{

	
	@FXML
	private ImageView pista;
	
	@FXML
	private AnchorPane game;
	
	@FXML
	private TextField carNumber;
	
	@FXML
	private TextField timeGen;
	
	@FXML
	private TextField velocity;
	
	@FXML
	private TextField chanceMutation;
	
	@FXML
	private TextField severyMutation;
	
	@FXML
	private TextField chanceExtremeMutation;
	
	@FXML
	private Button startButton;
	@FXML
	private Button resetButton;
	
	private int timer = 0;
	private Sensors sensor;
	private DrawTrack track; 
	private Controller controller;
	private ArrayList<Car> car;
	private Collider collider;
	private NeuralNetwork nn;
	private WaveFront wf;
	private EvolutionAlgorithm ea;
	private Timeline tl;

	private int gambi = 0; //se tirar quebra
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	
	}
	
	public void restartGame() {
		
		this.car = ea.getGeneration(Double.parseDouble(this.chanceMutation.getText()), Double.parseDouble(this.chanceExtremeMutation.getText()), Double.parseDouble(this.severyMutation.getText()));
		tl.play();
		
	}
	
	@FXML
	public void startGame() {
		carNumber.setDisable(true);
		timeGen.setDisable(true);
		velocity.setDisable(true);
		chanceMutation.setDisable(true);
		severyMutation.setDisable(true);
		chanceExtremeMutation.setDisable(true);
		startButton.setDisable(true);
		
		ea = new EvolutionAlgorithm(Integer.parseInt(this.carNumber.getText()), 1,( Integer.parseInt(this.timeGen.getText())), game);
		this.car = ea.getGeneration(Double.parseDouble(this.chanceMutation.getText()), Double.parseDouble(this.chanceExtremeMutation.getText()), Double.parseDouble(this.severyMutation.getText()));

		//for(Car x : this.car)  {
			//System.out.println(x.getGenome().toString());
	//	}
		
		//System.out.println(game.getChildren().toString());
		this.nn = new NeuralNetwork();
		this.track = new DrawTrack(Main.usuario.getId());
		this.setTrack();
		collider = new Collider(this.track);
		wf = new WaveFront(game, track);
		controller = new Controller();
		this.sensor = new Sensors(this.track.getTrack(), this.game);
		wf.setMatrix();
		this.tl = new Timeline(
				new KeyFrame(Duration.millis(Integer.parseInt(this.velocity.getText())), e -> this.pensa())
        );
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
	}
	
	private void setTrack() {
		ImageView img = this.track.getImgTrack();
		this.game.getChildren().add(0, img);
	}
	

	void direita(Car car) {
		controller.moveRight(car);
	}

	void esquerda(Car car) {
		controller.moveLeft(car);
	}

	void cima(Car car) {
		controller.moveUp(car);
	}

	void baixo(Car car) {
		controller.moveDown(car);
	}
	//DEBUG PROVISÒRIO
	@FXML
	void pensa() {
		this.timer++;
		if(this.timer >= this.ea.getTimeGeneration()) {
			terminaGeracao();
		}
		for(int i =0; i < this.car.size(); i++) {
			if(this.car.get(i).isChampion()) {
				terminaGeracao();
			}
			ArrayList<Double> scan = new ArrayList<Double>();
			ArrayList<Double> sinapse = new ArrayList<Double>();
			if(this.car.get(i).getIsDead().equals(false)) {
				scan = sensor.scan((int)car.get(i).getImageView().getLayoutX(), (int)car.get(i).getImageView().getLayoutY());
				sinapse = this.nn.getSinapse(scan, car.get(i));
				
//				for (int j = 0; j < sinapse.size(); j++) {
//					System.out.println(sinapse.get(j));
//				}
//				System.out.println("---------");
				
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
	
	@FXML
	void usuarioDireita() {
		controller.moveRight(this.car.get(0));
		//this.wf.getCarPointer(this.car);
		//ea.artifialSelection(this.car, this.track);
		sensor.scan((int) this.car.get(0).getImageView().getLayoutX(), (int)car.get(0).getImageView().getLayoutY());
	}
	@FXML
	void usuarioEsquerda() {
		controller.moveLeft(this.car.get(0));
		//this.wf.getCarPointer(this.car);
		//ea.artifialSelection(this.car, this.track);
		sensor.scan((int) this.car.get(0).getImageView().getLayoutX(), (int)car.get(0).getImageView().getLayoutY());
		}
	@FXML
	void usuarioCima() {
		controller.moveUp(this.car.get(0));
		//this.wf.getCarPointer(this.car);
		//ea.artifialSelection(this.car, this.track);
		sensor.scan((int) this.car.get(0).getImageView().getLayoutX(), (int)car.get(0).getImageView().getLayoutY());
		}
	@FXML
	void usuarioBaixo() {
		controller.moveDown(this.car.get(0));
		//this.wf.getCarPointer(this.car);
		//ea.artifialSelection(this.car, this.track);
		sensor.scan((int) this.car.get(0).getImageView().getLayoutX(), (int)car.get(0).getImageView().getLayoutY());
		}
	@FXML
	void reset() throws Throwable {
		tl.stop();
		
		final Button restartButton = new Button( "Restart" );
		restartButton.setLayoutX(524);
		restartButton.setLayoutY(9);
		restartButton.setOnAction(e -> {
            try {
				reset();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		
		final Button IaRemoveButton = new Button( "Delete IA" );
		IaRemoveButton.setLayoutX(450);
		IaRemoveButton.setLayoutY(277);
		IaRemoveButton.setOnAction(e -> {
			File genes = new File("genes");
			File score = new File("score");
			
			genes.delete();
			score.delete();
        });
		
		final Button StopButton = new Button( "Save & Exit" );
		StopButton.setLayoutX(589);
		StopButton.setLayoutY(9);
		StopButton.setOnAction(e -> {stop();});
		final Button UpButton = new Button( "CIMA" );
		UpButton.setLayoutX(284);
		UpButton.setLayoutY(263);
		UpButton.setOnAction(e -> {usuarioCima();});
		final Button DownButton = new Button( "BAIXO" );
		DownButton.setLayoutX(282);
		DownButton.setLayoutY(313);
		DownButton.setOnAction(e -> {usuarioBaixo();});
		final Button LeftButton = new Button( "ESQUERDA" );
		LeftButton.setLayoutX(206);
		LeftButton.setLayoutY(288);
		LeftButton.setOnAction(e -> {usuarioEsquerda();});
		final Button RightButton = new Button( "DIREITA" );
		RightButton.setLayoutX(334);
		RightButton.setLayoutY(288);
		RightButton.setOnAction(e -> {usuarioDireita();});
		
		this.game.getChildren().clear();
		this.game.getChildren().add(this.track.getImgTrack());
		this.game.getChildren().add(restartButton);
		this.game.getChildren().add(StopButton);
//		this.game.getChildren().add(UpButton);
//		this.game.getChildren().add(DownButton);
//		this.game.getChildren().add(LeftButton);
//		this.game.getChildren().add(RightButton);
//		this.game.getChildren().add(IaRemoveButton);
		
		this.timer = 0;
		this.car = null;
		restartGame();
	}
	@FXML
	void stop() {
		System.exit(1);
	}
	public void terminaGeracao() {
		this.wf.getCarPointer(car);
		this.ea.artifialSelection(car, track);
		try {
			reset();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
