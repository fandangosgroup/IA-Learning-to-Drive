package application;

import javafx.scene.image.ImageView;

public class Controller {
	
	private ImageView car;
	
	public Controller(ImageView car) {
		this.car = car;
	}
	
	public void moveUp() {
		car.setLayoutY(car.getLayoutY() - 7);
	}
	
	public void moveDown() {
		car.setLayoutY(car.getLayoutY() + 7);	
	}
	
	public void moveLeft() {
		car.setLayoutX(car.getLayoutX() - 7);
	}
	
	public void moveRight() {
		car.setLayoutX(car.getLayoutX() + 7);
	}
	
}
