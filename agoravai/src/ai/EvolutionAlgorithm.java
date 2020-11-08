package ai;

import java.util.ArrayList;

import environment.Car;
import javafx.scene.layout.AnchorPane;

public class EvolutionAlgorithm {
	private int numberOfSelected;
	private int timeGeneration;
	private AnchorPane game;
	private int individualsPerGeneration;
	public EvolutionAlgorithm(int individualsPerGeneration, int numberOfSelected, int timeGeneration,  AnchorPane game) {
		this.numberOfSelected = numberOfSelected;
		this.timeGeneration = timeGeneration;
		this.individualsPerGeneration = individualsPerGeneration;
		this.game = game;
	}	
	
	public ArrayList<Car> getGeneration(){
		ArrayList<Car> cars = new ArrayList<Car>();
		for(int i = 0; i < individualsPerGeneration; i++) {
			cars.add(new Car(i));
			this.game.getChildren().add(cars.get(i).getImageView());
		}
		
		return cars;	
	}
	
	public void artifialSelection() {
		//distancia euclidiana
		//dab = sqrt(pow(xb-xa, 2) + pow(yb-ya, 2))
		
	}
	
	public ArrayList<Car> nextGeneration(){
		
		
		return null;
	}
	//salvar os genes no banco de dados
	public void saveGenes() {
		
	}
	//salva os melhores genes
	private void saveGenesState() {
		
	}
}
