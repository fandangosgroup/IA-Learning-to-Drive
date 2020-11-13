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
	
	public ArrayList<Car> getGeneration(){
		ArrayList<Car> cars = new ArrayList<Car>();
		for(int i = 0; i < individualsPerGeneration; i++) {
			cars.add(new Car(i));
			this.game.getChildren().add(cars.get(i).getImageView());
		}
		
		return cars;	
	}
	
	public void artifialSelection(ArrayList<Car> car, DrawTrack track) {
		
		ArrayList<Double> pontoFinal = new ArrayList<Double>();
		Double min = 100000d;
		int carIndex = 0;
		for(int i = 0; i < car.size(); i++) {
			if(car.get(i).getIsDead()) {
				car.get(i).setPonto(car.get(i).getPonto() + 15);
			}
				Double dab = Math.sqrt(Math.pow(499 - car.get(i).getImageView().getLayoutX(), 2) + Math.pow(499 - car.get(i).getImageView().getLayoutY(), 2));
				//System.out.println("distacia euclidiana: " + dab);
				//System.out.println("ponto final: " + dab * car.get(i).getPonto());
				if(min > (dab * car.get(i).getPonto())) {
					min = dab * car.get(i).getPonto();
					carIndex = i;
				}
			
		}
		System.out.println("O MELHOR CARRO FOI O: " + carIndex);
		System.out.println("COM PONTUACAO DE : " + min);
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
		if(oldFitness > fitness) {
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
		}
		
		//System.exit(0);
	}
	public int getTimeGeneration() {
		return this.timeGeneration;
	}
}
