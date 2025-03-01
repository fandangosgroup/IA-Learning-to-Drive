package environment;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Sensors {
	
	private BufferedImage track;
	private int positionX;
	private int positionY;
	private AnchorPane game;
	private Boolean mostraSensor = false;
	private Integer sensorNormalRange = 400;
	private Integer sensorDiagonalRange = 400;
	private int flagChegada = 0;
	
	public Sensors(BufferedImage t, AnchorPane game){
		this.track = t;
		this.game = game;
	}
	
	public ArrayList<Double> scan(int x, int y){
		ArrayList<Double> ret = new ArrayList<Double>();
		this.positionX = x;
		this.positionY = y;
		this.flagChegada = 0;
		ret.add(this.up());
		ret.add(this.down());
		ret.add(this.right());
		ret.add(this.left());
		ret.add(this.upRight());
		ret.add(this.upLeft());
		ret.add(this.downRight());
		ret.add(this.downLeft());
		
		for(int i = 0; i < 8; i++) {
			ret.set(i, ret.get(i) * 10);
		}
		
		return ret;
	}
	
	public double up() {
		
		int pixNumber = 0;
		
		for (int y = this.positionY; y > this.positionY-this.sensorNormalRange && y > 0 ; y --){
            if(this.verificaRGB(this.positionX, y)){
            	if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
            	return pixNumber;
            }
            this.drawSensor(this.positionX, y);
            pixNumber++;
            }
        return 0; 
    }
	
	public double down() {
		int pixNumber = 0;

		for (int y = this.positionY; y < this.positionY + this.sensorNormalRange ; y ++){
            if(this.verificaRGB(this.positionX, y)){
            	if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
            	return pixNumber;
            }
            this.drawSensor(this.positionX, y);
            pixNumber++;
            }
		
        return 0; 
	}
	
	public double right() {
		int pixNumber = 0;
		
		for (int x = this.positionX; x < this.positionX + this.sensorNormalRange ; x ++){
            if(this.verificaRGB(x, this.positionY)){
            	if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
            	return pixNumber;
            }
            this.drawSensor(x, this.positionY);
            pixNumber++;
            }
		
        return 0; 
	}
	
	public double left() {
		int pixNumber = 0;
		
		for (int x = this.positionX; x > this.positionX - this.sensorNormalRange ; x--){
            if(this.verificaRGB(x, this.positionY)){
            	if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
            	return pixNumber;
            }
            this.drawSensor(x, this.positionY);
            pixNumber++;
            }
        return 0; 
	}
	
	public double upRight() {
		int pixNumber = 0;
		int y = this.positionY;
		for (int x = this.positionX; x < this.positionX + this.sensorDiagonalRange; x++) {
			if(this.verificaRGB(x, y) && y > 0) {
				if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
				return pixNumber;
			}
			this.drawSensor(x, y);
			pixNumber++;
			y--;
		}		
		return 0;
	}
	
	public double upLeft() {
		int pixNumber = 0;
		int y = this.positionY;
		for (int x = this.positionX; x > this.positionX - this.sensorDiagonalRange; x--) {
			if(this.verificaRGB(x, y) && y > 0) {
				if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
				return pixNumber;
			}
			this.drawSensor(x, y);
			pixNumber++;
			y--;
		}		
		return 0;
	}
	
	public double downRight() {
		int pixNumber = 0;
		int x = this.positionX;
		for (int y = this.positionY; y < this.positionY + this.sensorDiagonalRange; y++) {
			if(this.verificaRGB(x, y)) {
				if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
				return pixNumber;
			}
			this.drawSensor(x, y);
			pixNumber++;
			x++;
		}
		
		return 0;
	}
	
	public double downLeft() {
		int pixNumber = 0;
		int x = this.positionX;
		for (int y = this.positionY; y < this.positionY + this.sensorDiagonalRange; y++) {
			if(this.verificaRGB(x, y)) {
				if(this.flagChegada > 0) {
					return pixNumber + this.flagChegada;
				}
				return pixNumber;
			}
			this.drawSensor(x, y);
			pixNumber++;
			x--;
		}
		
		return 0;
	}
	
	public void drawSensor(int x, int y) {
		//this.game.getChildren().remove(5, this.game.getChildren().size());
		
		if(this.mostraSensor.equals(true)) {
			File file = new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\sensor.png");
	        Image img = new javafx.scene.image.Image(file.toURI().toString());
	        ImageView imgv = new ImageView(img);
	        imgv.setLayoutX(x);
	        imgv.setLayoutY(y);
	        this.game.getChildren().add(imgv);	
		}
		
	}
	
	public Boolean verificaRGB(int x, int y) {
		
		Integer instantRGB = 0;
		Integer blue;
		Integer red;
		Integer green;
		
		instantRGB =  this.track.getRGB(x, y);
        Color c = new Color(instantRGB);
        blue = c.getBlue();
        red = c.getRed();
        green = c.getGreen();
        Integer tot = blue + red + green;
        if(red > 200 && (blue + green) < 100 ) {
        	this.flagChegada = 999;
        	return true;
        }
        
		return tot < 20? true : false;
	}
	
}
