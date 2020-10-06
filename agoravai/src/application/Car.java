package application;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Car{
	
	private ArrayList<Integer> genome;
	private Color color; //n faço ideia
	private boolean isDead;
	private int chanceMutation;
	private int severyMutation;
	private int chanceExtremeMutation;
	private ImageView car;
	
	public Car() {
		this.isDead = false;
		this.setImageView();
		this.setRandomGenome();
		this.setPresetGenes();
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
	
	private void setImageView(){
		//load na imageview
	}
	private void setRandomGenome() {
		for(int i = 0; i < this.genome.size(); i++) {
			this.genome.add((int) (1000d - Math.random() * 1000d) );
		}
	}
	
	private void setPresetGenes() {
		//load no arquivo de genes
	}
}
