package environment;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Car{
	
	private ArrayList<Integer> genome =  new ArrayList<Integer>();
	//private Color color;
	private boolean isDead;
	private int chanceMutation;
	private int severyMutation;
	private int chanceExtremeMutation;
	private ImageView car;
	
	public Car() {
		this.isDead = false;
		this.setImageView();
		this.setRandomGenome();
		for(Integer i :this.genome) {
			System.out.println("gernoma: " + i.toString());
		}
		//this.setPresetGenes();
	}
	
	public void mutation() {
		for(int i = 0; i < this.genome.size(); i++) {
			if(this.chanceMutation >= (int)(Math.random() * 100)) {
				this.genome.set(i, this.genome.get(i) * this.severyMutation);
				if(this.chanceExtremeMutation >= (int)(Math.random() * 100)) {
					this.genome.set(i, this.extremeMutation(this.genome.get(i)));
				}
			}
			
		}
		
	}
	
	private int extremeMutation(int genes) {
		return genes * -1;
	}
	
	public void Kill() {
		this.isDead = true;
	}
	
	public Boolean getIsDead() {
		return this.isDead;
	}
	
	private void setImageView(){
		 File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\car.png");
         Image img = new Image(file.toURI().toString());
         car = new ImageView(img);
         car.setFitHeight(5);
         car.setFitWidth(5);
         car.setLayoutX(10);
         car.setLayoutY(40);
         car.setId("1");
	}
	private void setRandomGenome() {
		for(int i = 0; i < this.genome.size(); i++) {
			this.genome.add((int) (1000d - Math.random() * 1000d) );
		}
	}
	
	private void setPresetGenes() {
		//load no arquivo de genes
	}
	
	public ImageView getImageView() {
		return this.car;
	}
}
