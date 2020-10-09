package application;

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
	private Boolean mostraSensor = true;
	
	public Sensors(BufferedImage t, AnchorPane game){
		this.track = t;
		this.game = game;
	}
	
	public ArrayList<Integer> scan(int x, int y){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		this.positionX = x;
		this.positionY = y;
		this.game.getChildren().remove(3, this.game.getChildren().size());
		ret.add(this.up());
		ret.add(this.down());
		ret.add(this.right());
		ret.add(this.left());
		ret.add(this.upRight());
		ret.add(this.upLeft());
		ret.add(this.downRight());
		ret.add(this.downLeft());
		
		return ret;
	}
	
	public int up() {
		
		int pixNumber = 0;
		
		for (int y = this.positionY; y > 0 ; y --){
            if(this.verificaRGB(this.positionX, y)){
                return pixNumber/10;
            }
            this.drawSensor(this.positionX, y);
            pixNumber++;
            }
        return 999; 
    }
	
	public int down() {
		int pixNumber = 0;

		for (int y = this.positionY; y < this.track.getHeight() ; y ++){
            if(this.verificaRGB(this.positionX, y)){
                return pixNumber/10;
            }
            this.drawSensor(this.positionX, y);
            pixNumber++;
            }
		
        return 999; 
	}
	
	public int right() {
		int pixNumber = 0;
		
		for (int x = this.positionX; x < this.track.getHeight() ; x ++){
            if(this.verificaRGB(x, this.positionY)){
                return pixNumber/10;
            }
            this.drawSensor(x, this.positionY);
            pixNumber++;
            }
		
        return 999; 
	}
	
	public int left() {
		int pixNumber = 0;
		
		for (int x = this.positionX; x > 0 ; x--){
            if(this.verificaRGB(x, this.positionY)){
                return pixNumber/10;
            }
            this.drawSensor(x, this.positionY);
            pixNumber++;
            }
        return 999; 
	}
	
	public int upRight() {
		int pixNumber = 0;
		int y = this.positionY;
		for (int x = this.positionX; x < this.track.getWidth(); x++) {
			if(this.verificaRGB(x, y) && y > 0) {
				return pixNumber/10;
			}
			this.drawSensor(x, y);
			pixNumber++;
			y--;
		}		
		return 999;
	}
	
	public int upLeft() {
		int pixNumber = 0;
		int y = this.positionY;
		for (int x = this.positionX; x > 0; x--) {
			if(this.verificaRGB(x, y) && y > 0) {
				return pixNumber/10;
			}
			this.drawSensor(x, y);
			pixNumber++;
			y--;
		}		
		return 999;
	}
	
	public int downRight() {
		int pixNumber = 0;
		int x = this.positionX;
		for (int y = this.positionY; y < this.track.getHeight(); y++) {
			if(this.verificaRGB(x, y)) {
				return pixNumber/10;
			}
			this.drawSensor(x, y);
			pixNumber++;
			x++;
		}
		
		return 999;
	}
	
	public int downLeft() {
		int pixNumber = 0;
		int x = this.positionX;
		for (int y = this.positionY; y < this.track.getHeight(); y++) {
			if(this.verificaRGB(x, y)) {
				return pixNumber/10;
			}
			this.drawSensor(x, y);
			pixNumber++;
			x--;
		}
		
		return 999;
	}
	
	public void drawSensor(int x, int y) {
		
		if(this.mostraSensor.equals(true)) {
			File file = new File("C:\\Users\\bruno\\Desktop\\sensor.png");
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
        
		return tot < 20? true : false;
	}
	
}
