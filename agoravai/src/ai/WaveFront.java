package ai;

import java.awt.Color;
import java.util.ArrayList;

import environment.Car;
import environment.DrawTrack;
import javafx.scene.layout.AnchorPane;

public class WaveFront {
	private DrawTrack track;
	ArrayList<Nodes> grafo = new ArrayList<Nodes>();
	private int[][] matrix = new int[500][500];
	AnchorPane game;
	
	public WaveFront(AnchorPane game, DrawTrack track) {
		this.game = game;
		this.track = track;
	}
	
	public void setMatrix() {
		this.grafo.add(new Nodes(499, 499, 1));
		this.dispersion(499, 499, game, this.grafo.get(0).getGrau(), this.grafo.get(0));	
		for(int i = 0; i < this.grafo.size(); i++) {
			ArrayList<Nodes> layer = this.grafo.get(i).getChildren();
			layer.forEach(
					node -> 
					this.dispersion(node.getX(), 
									node.getY(), game, 
									node.getGrau(), node));
		}
		
		
//		for(int i = 0; i < this.grafo.size(); i++) {
//			System.out.println("x: = " + this.grafo.get(i).getX() + " y: = "  + this.grafo.get(i).getY() +  "Grau: " + this.grafo.get(i).getGrau() + " Indice " + i);
//		}
//		
//		for (int i = 0; i < matrix.length; i++) {
//		System.out.println("");
//		for (int j = 0; j < matrix.length; j++) {
//			System.out.print(this.matrix[i][j] + "|");
//		}
//	}
//		System.exit(0);
	}
	
	public void dispersion(int x, int y, AnchorPane game, int n, Nodes node) {
		if(this.matrix[y][x] == 0 && this.pixelVeify(x, y, this.track).equals(true)) {
			this.matrix[y][x] = n;
			//this.drawHeat(x, y, game, n);
		}
		if((y - 1) > 0 && this.matrix[y - 1][x] == 0 && this.pixelVeify((y - 1), x, this.track).equals(true)) {
			this.matrix[y - 1][x] = n + 1;
			Nodes nodeChild = new Nodes(x, y - 1, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);
			//this.drawHeat(x, y - 1, game, n);
		}
		if((y - 1) > 0 && (x - 1) > 0 && this.matrix[y - 1][x - 1] == 0 && this.pixelVeify(y - 1, x - 1, this.track).equals(true)) {
			this.matrix[y - 1][x - 1] = n + 1;
			Nodes nodeChild = new Nodes(x - 1, y - 1, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);			
			//this.drawHeat(x - 1, y - 1, game, n);
		}
		if((x - 1) > 0  && this.matrix[y][x - 1] == 0 && this.pixelVeify(y, x - 1, this.track).equals(true)) {
			this.matrix[y][x - 1] = n + 1;
			Nodes nodeChild = new Nodes(x - 1, y - 1, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);
			//this.drawHeat(x - 1, y, game, n);
		}
		if((y + 1) < this.matrix.length && (x - 1) > 0  && this.matrix[y + 1][x - 1] == 0 && this.pixelVeify(y + 1, x - 1, this.track).equals(true)) {
			this.matrix[y + 1][x - 1] = n + 1;
			Nodes nodeChild = new Nodes(x - 1, y + 1, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);
			//this.drawHeat( x - 1, y + 1, game, n);
		}
		if((y + 1) < this.matrix.length && x < this.matrix.length  && this.matrix[y + 1][x] == 0 && this.pixelVeify(y + 1, x, this.track).equals(true)) {
			this.matrix[y + 1][x] = n + 1;
			Nodes nodeChild = new Nodes(x, y + 1, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);
			//this.drawHeat(x, y + 1,  game, n);
		}
		if((y + 1) < this.matrix.length && (x + 1) < this.matrix.length  && this.matrix[y + 1][x + 1] == 0 && this.pixelVeify(y + 1, x + 1, this.track).equals(true)) {
			this.matrix[y + 1][x + 1] = n + 1;
			Nodes nodeChild = new Nodes(x + 1, y + 1, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);
			//this.drawHeat(x + 1, y + 1,  game, n);
		}
		if((x + 1) < this.matrix.length && y < this.matrix.length  && this.matrix[y][x + 1] == 0 && this.pixelVeify(y, x + 1, this.track).equals(true)) {
			this.matrix[y][x + 1] = n + 1;
			Nodes nodeChild = new Nodes(x + 1, y, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);
			//this.drawHeat(x + 1, y, game, n);
		}
		if((y - 1) >= 0 && (x + 1) < this.matrix.length  && this.matrix[y - 1][x + 1] == 0 && this.pixelVeify(y - 1, x + 1, this.track).equals(true)) {
			this.matrix[y - 1][x + 1] = n + 1;
			Nodes nodeChild = new Nodes(x + 1, y - 1, n + 1);
			node.addChild(nodeChild);
			grafo.add(nodeChild);
			//this.drawHeat(x + 1, y - 1, game, n);
		}
	}
	
	public Boolean pixelVeify(int y, int x,DrawTrack track) {
		int instantRGB = track.getTrack().getRGB(y, x);
		
		Color c = new Color(instantRGB);
	    Integer blue = c.getBlue();
	    Integer red = c.getRed();
	    Integer green = c.getGreen();
	    
	    return red > 200 ? true : false;
	}
	public void getCarPointer(ArrayList<Car> car) {
		for(int i = 0; i < car.size(); i++) {
			car.get(i).setPonto(this.matrix[(int)car.get(i).getImageView().getLayoutX()][(int)car.get(i).getImageView().getLayoutY()]);
		}
	}
}