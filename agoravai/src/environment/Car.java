package environment;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Car{
	
	private int genesNumber = 160;
	private ArrayList<Integer> genome =  new ArrayList<Integer>();
	//private Color color;
	private boolean isDead;
	private int chanceMutation;
	private int severyMutation;
	private int chanceExtremeMutation;
	private ImageView car;
	
	public Car(int i) {
		this.isDead = false;
		this.setImageView(i);
		this.setRandomGenome();
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
	
	private void setImageView(Integer i){
		 File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\car.png");
         Image img = new Image(file.toURI().toString());
         car = new ImageView(img);
         car.setFitHeight(5);
         car.setFitWidth(5);
         car.setLayoutX(30);
         car.setLayoutY(35);
         car.setId(i.toString());
	}
	
	private void setRandomGenome() {
		for(int i = 0; i < this.genesNumber; i++) {
			this.genome.add((int) (1000d - Math.random() * 1000d) );
		}
		//System.out.println(this.genome);
	}
	
	private void setPresetGenes() {
		//load no arquivo de genes
	}
	
	public ImageView getImageView() {
		return this.car;
	}
	
	public ArrayList<Integer> getGenome(){
		return this.genome;
	}
	
}
