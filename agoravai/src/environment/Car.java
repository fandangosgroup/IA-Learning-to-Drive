package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Car{
	
	//Valores sagrados!
	//98d
	//0.99d
	//1d
	private int genesNumber = 180;
	private ArrayList<Double> genome =  new ArrayList<Double>();
	private boolean isDead;
	private double chanceMutation; 
	private double severyMutation; 
	private double chanceExtremeMutation;
	private ImageView car;
	private long ponto;
	private boolean keepBestCar = true;
	private boolean isChampion = false;
	
	public Car(int i) {
		this.isDead = false;
		this.setImageView(i);
		
		File genes = new File("genes");
		
		if(genes.exists()) {
			this.setPresetGenes();
//			if(i > 0) {
//				this.mutation();
//			}
//				
		}else{
			this.setRandomGenome();
			//this.mutation();
		}
	}
	
	public void mutation() {
		for(int i = 0; i < this.genome.size(); i++) {
			if(this.chanceMutation <= (int)(Math.random() * 100)) {
				this.genome.set(i, this.genome.get(i) * this.severyMutation);
				if(this.chanceExtremeMutation <= (int)(Math.random() * 100)) {
					this.genome.set(i, this.extremeMutation(this.genome.get(i)));
				}
			}
			
		}
		
	}
	
	private double extremeMutation(double genes) {
		return genes * -1;
	}
	
	public void Kill() {
		this.isDead = true;
	}
	
	public Boolean getIsDead() {
		return this.isDead;
	}
	
	private void setImageView(Integer i){
		 File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\car.png");
         Image img = new Image(file.toURI().toString());
         car = new ImageView(img);

        //car.setVisible(false);	 

         car.setFitHeight(5);
         car.setFitWidth(5);
         car.setLayoutX(50);
         car.setLayoutY(50);
         car.setId(i.toString());
	}
	
	private void setRandomGenome() {
		for(int i = 0; i < this.genesNumber; i++) {
			this.genome.add((1000d - Math.random() * 1000d) );
		}
		//System.out.println(this.genome);
	}
	
	private void setPresetGenes() {
		File genes = new File("genes");
		try {
            if (genes.exists()) {
               ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(genes));
               this.genome = (ArrayList<Double>) objInput.readObject();
               objInput.close();

           }
         } catch(IOException erro1) {
              System.out.printf("Erro: %s", erro1.getMessage());
         } catch(ClassNotFoundException erro2) {
              System.out.printf("Erro: %s", erro2.getMessage());
         }
	}
	
	public ImageView getImageView() {
		return this.car;
	}
	
	public ArrayList<Double> getGenome(){
		return this.genome;
	}
	public void setPonto(long matrix) {
		this.ponto = matrix;
	}
	public long getPonto() {
		return this.ponto;
	}
	public void champion() {
		this.isChampion  = true;
	}
	public boolean isChampion() {
		return this.isChampion;
	}
	
	public double getChanceMutation() {
		return chanceMutation;
	}

	public void setChanceMutation(Double chanceMutation) {
		//System.out.println(chanceMutation);
		this.chanceMutation = chanceMutation;
	}

	public double getSeveryMutation() {
		return severyMutation;
	}

	public void setSeveryMutation(Double severyMutation) {
		//System.out.println(severyMutation);
		this.severyMutation = severyMutation;
	}

	public double getChanceExtremeMutation() {
		return chanceExtremeMutation;
	}

	public void setChanceExtremeMutation(Double chanceExtremeMutation) {
		//System.out.println(chanceExtremeMutation);
		this.chanceExtremeMutation = chanceExtremeMutation;
	}
	
	
}
