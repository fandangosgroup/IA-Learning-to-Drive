package ai;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import environment.Car;
//Projetado para rodar com a imagem 500/500 caso mudar a dimensão da imagem deve alterar o tamanho da matriz para os numeros correspondentes
import environment.DrawTrack;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HeatDispersion {
	private DrawTrack track;	
	private int[][] pointerMatrix = new int[500][500];
	private	ArrayList<Integer> x = new ArrayList<Integer>();
	private	ArrayList<Integer> y = new ArrayList<Integer>();
	private ArrayList<Integer> n = new ArrayList<Integer>();	
	
	public HeatDispersion(DrawTrack track, AnchorPane game) {
		this.matrixPrepair();
		this.track = track;

	}
	
	private void matrixPrepair() {
		for (int i = 0; i < this.pointerMatrix.length; i++) {
			for (int j = 0; j < this.pointerMatrix.length; j++) {
				this.pointerMatrix[i][j] = 0;
			}
		}
	}
	public void setPointerMatrix(DrawTrack track, AnchorPane game) {
		int n = 2;
		this.pintaPixel(499, 499, game, n);
		n++;
		for (int i = 0; i < this.x.size(); i++) {
			if(this.x.get(i) > 0 && this.y.get(i) > 0) {
				n = this.n.get(i) + 1;
				this.pintaPixel(this.x.get(i), this.y.get(i), game, n);
				this.x.remove(i);
				this.y.remove(i);
			}
		}

//		for (int i = 0; i < pointerMatrix.length; i++) {
//			System.out.println("");
//			for (int j = 0; j < pointerMatrix.length; j++) {
//				System.out.print(this.pointerMatrix[i][j] + "|");
//			}
//		}
	}
	
	public void pintaPixel(int x, int y, AnchorPane game, int n) {
		
		if(this.pointerMatrix[y][x] != (n - 1) && this.pointerMatrix[y][x] == 0 && this.pixelVeify(x, y, this.track).equals(true)) {
			this.pointerMatrix[y][x] = n;
			this.x.add(x);
			this.y.add(y);
			this.n.add(n);
			//this.drawHeat(x, y, game, n);
		}
		if((y - 1) > 0 && this.pointerMatrix[y - 1][x] == 0 && this.pixelVeify((y - 1), x, this.track).equals(true)) {
			this.pointerMatrix[y - 1][x] = n;
			this.x.add(x);
			this.y.add((y - 1));
			this.n.add(n);
			//this.drawHeat(x, y - 1, game, n);
		}
		if((y - 1) > 0 && (x - 1) > 0 && this.pointerMatrix[y - 1][x - 1] == 0 && this.pixelVeify(y - 1, x - 1, this.track).equals(true))
			this.pointerMatrix[y - 1][x - 1] = n;
			this.x.add((x - 1));
			this.y.add((y - 1));
			this.n.add(n);
			//this.drawHeat(x - 1, y - 1, game, n);
		if((x - 1) > 0  && this.pointerMatrix[y][x - 1] == 0 && this.pixelVeify(y, x - 1, this.track).equals(true)) {
			this.pointerMatrix[y][x - 1] = n;
			this.x.add(x - 1);
			this.y.add(y);
			this.n.add(n);
			//this.drawHeat(x - 1, y, game, n);
		}
		if((y + 1) < this.pointerMatrix.length && (x - 1) > 0  && this.pointerMatrix[y + 1][x - 1] == 0 && this.pixelVeify(y + 1, x - 1, this.track).equals(true)) {
			this.pointerMatrix[y + 1][x - 1] = n;
			this.x.add((x - 1));
			this.y.add((y + 1));
			this.n.add(n);
			//this.drawHeat( x - 1, y + 1, game, n);
		}
		if((y + 1) < this.pointerMatrix.length && x < this.pointerMatrix.length  && this.pointerMatrix[y + 1][x] == 0 && this.pixelVeify(y + 1, x, this.track).equals(true)) {
			this.pointerMatrix[y + 1][x] = n;
			this.x.add(x);
			this.y.add((y + 1));
			this.n.add(n);
			//this.drawHeat(x, y + 1,  game, n);
		}
		if((y + 1) < this.pointerMatrix.length && (x + 1) < this.pointerMatrix.length  && this.pointerMatrix[y + 1][x + 1] == 0 && this.pixelVeify(y + 1, x + 1, this.track).equals(true)) {
			this.pointerMatrix[y + 1][x + 1] = n;
			this.x.add((x + 1));
			this.y.add((y + 1));
			this.n.add(n);
			//this.drawHeat(x + 1, y + 1,  game, n);
		}
		if((x + 1) < this.pointerMatrix.length && y < this.pointerMatrix.length  && this.pointerMatrix[y][x + 1] == 0 && this.pixelVeify(y, x + 1, this.track).equals(true)) {
			this.pointerMatrix[y][x + 1] = n;
			this.x.add((x + 1));
			this.y.add(y);
			this.n.add(n);
			//this.drawHeat(x + 1, y, game, n);
		}
		if((y - 1) >= 0 && (x + 1) < this.pointerMatrix.length  && this.pointerMatrix[y - 1][x + 1] == 0 && this.pixelVeify(y - 1, x + 1, this.track).equals(true)) {
			this.pointerMatrix[y + 1][x + 1] = n;
			this.x.add((x + 1));
			this.y.add((y + 1));
			this.n.add(n);
			//this.drawHeat(x + 1, y - 1, game, n);
		}
	}
	
	public Boolean pixelVeify(int y, int x,DrawTrack track) {
		int instantRGB = track.getTrack().getRGB(y, x);
		
		Color c = new Color(instantRGB);
        Integer blue = c.getBlue();
        Integer red = c.getRed();
        Integer green = c.getGreen();
        
        return (blue + red + green) > 20 ? true : false;
	}
	
	public void drawHeat(int y, int x, AnchorPane game, int n) {
		//this.game.getChildren().remove(3, this.game.getChildren().size());
			if(n <= 10) {
			File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\sensor.png");
	        Image img = new javafx.scene.image.Image(file.toURI().toString());
	        ImageView imgv = new ImageView(img);
	        imgv.setLayoutX(x);
	        imgv.setLayoutY(y);
	        game.getChildren().add(imgv);
	        }
			if(n <= 13 && n > 10) {
				File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\red.png");
		        Image img = new javafx.scene.image.Image(file.toURI().toString());
		        ImageView imgv = new ImageView(img);
		        imgv.setLayoutX(x);
		        imgv.setLayoutY(y);
		        game.getChildren().add(imgv);
		        }
			if(n == 14) {
				File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\laranja.png");
		        Image img = new javafx.scene.image.Image(file.toURI().toString());
		        ImageView imgv = new ImageView(img);
		        imgv.setLayoutX(x);
		        imgv.setLayoutY(y);
		        game.getChildren().add(imgv);
		        }
			if(n == 15) {
				File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\azul.png");
		        Image img = new javafx.scene.image.Image(file.toURI().toString());
		        ImageView imgv = new ImageView(img);
		        imgv.setLayoutX(x);
		        imgv.setLayoutY(y);
		        game.getChildren().add(imgv);
		        }
			if(n >= 16) {
				File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\outroAzul.png");
		        Image img = new javafx.scene.image.Image(file.toURI().toString());
		        ImageView imgv = new ImageView(img);
		        imgv.setLayoutX(x);
		        imgv.setLayoutY(y);
		        game.getChildren().add(imgv);
		        }
		}
	public void getCarPointer(ArrayList<Car> car) {
		for(int i = 0; i < car.size(); i++) {
			car.get(i).setPonto(this.pointerMatrix[(int)car.get(i).getImageView().getLayoutX()][(int)car.get(i).getImageView().getLayoutY()]);
		}
	}
}
