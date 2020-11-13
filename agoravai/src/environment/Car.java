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
	
	private int genesNumber = 128;
	private ArrayList<Double> genome =  new ArrayList<Double>();
	//private Color color;
	private boolean isDead;
	private int chanceMutation = 90;
	private Double severyMutation = 0.09d;
	private int chanceExtremeMutation = 1;
	private ImageView car;
	private int ponto;
	
	public Car(int i) {
		this.isDead = false;
		this.setImageView(i);
		
		File genes = new File("genes");
		
		if(genes.exists()) {
			this.setPresetGenes();
			this.mutation();
		}else {
			this.setRandomGenome();
		}
		
	}
	
	public void mutation() {
		for(int i = 0; i < this.genome.size(); i++) {
			if(this.chanceMutation >= (int)(Math.random() * 100)) {
				this.genome.set(i, this.genome.get(i) * this.severyMutation);
//				if(this.chanceExtremeMutation >= (int)(Math.random() * 100)) {
//					this.genome.set(i, this.extremeMutation(this.genome.get(i)));
//				}
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
         car.setFitHeight(5);
         car.setFitWidth(5);
         car.setLayoutX(150);
         car.setLayoutY(35);
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
	public void setPonto(int ponto) {
		this.ponto = ponto;
	}
	public int getPonto() {
		return this.ponto;
	}
	
}
