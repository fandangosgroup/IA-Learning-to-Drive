package ai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import environment.Car;
import environment.DrawTrack;
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
	
	public ArrayList<Car> getGeneration(double chanceMutation, double extremeChanceMutation, double severyMutation){
		ArrayList<Car> cars = new ArrayList<Car>();
		for(int i = 0; i < individualsPerGeneration; i++) {
			cars.add(i, new Car(i));
			cars.get(i).setChanceMutation(chanceMutation);
			cars.get(i).setChanceExtremeMutation(extremeChanceMutation);
			cars.get(i).setSeveryMutation(severyMutation);
			cars.get(i).mutation();
			this.game.getChildren().add(cars.get(i).getImageView());
		}
		
		return cars;	
	}
	
	public void artifialSelection(ArrayList<Car> car, DrawTrack track) {
		long min = 1000000;
		int carIndex = 0;
		for(int i = 0; i < car.size(); i++) {
			if(car.get(i).getIsDead().equals(true)) {
				car.get(i).setPonto(car.get(i).getPonto() + 3);
			}
		}
		for(int i = 0; i < car.size(); i++) {
			if(min > car.get(i).getPonto() && car.get(i).getPonto() > 0) {
				min = car.get(i).getPonto();
				carIndex = i;
			}
		}
		System.out.println("O MELHOR CARRO FOI O: " + carIndex);
		System.out.println("na area: " + car.get(carIndex).getPonto());
		System.out.println("COM PONTUACAO DE FINAL: " + min);
		System.out.println("-----------------------------------------------------");
		
		this.saveGenesState(car.get(carIndex), min);
	}
	
	public ArrayList<Car> nextGeneration(){
		
		
		return null;
	}
	//salvar os genes no banco de dados
	public void saveGenes() {
		
	}
	//salva os melhores genes
	private void saveGenesState(Car car, double fitness) {

				File genes = new File("genes");
				File score = new File("score");
				
				double oldFitness = 999999999;
				
				try {

		            if (score.exists()) {
		               
		               ObjectInputStream objInput2 = new ObjectInputStream(new FileInputStream(score));
		               oldFitness = (double) objInput2.readObject();
		               objInput2.close();
		           }
		         } catch(IOException erro1) {
		              System.out.printf("Erro: %s", erro1.getMessage());
		         } catch(ClassNotFoundException erro2) {
		              System.out.printf("Erro: %s", erro2.getMessage());
		         }
				
				System.out.println("Pontuação do arquivo: "+oldFitness);
				System.out.println("É MELHOR VOU SALVAR !!");
					try {
						
				         genes.delete();
				         score.delete();

				         genes.createNewFile();
				         score.createNewFile();

				         ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(genes));
				         objOutput.writeObject(car.getGenome());
				         objOutput.close();
				         
				         ObjectOutputStream objOutput2 = new ObjectOutputStream(new FileOutputStream(score));
				         objOutput2.writeObject(fitness);
				         objOutput2.close();

				       } catch(IOException erro) {
				           System.out.printf("Erro: %s", erro.getMessage());
				       }

		
		
		//System.exit(0);
	}
	public int getTimeGeneration() {
		return this.timeGeneration;
	}
}
