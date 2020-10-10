package application;

import environment.Car;
import javafx.scene.image.ImageView;

public class Controller {
	
	private Car car;
	
	public Controller(Car car) {
		this.car = car;
	}
	
	public void moveUp() {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutY(car.getImageView().getLayoutY() - 7);	
		}
	}
	
	public void moveDown() {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutY(car.getImageView().getLayoutY() + 7);	
		}
	}
	
	public void moveLeft() {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutX(car.getImageView().getLayoutX() - 7);
		}
	}
	
	public void moveRight() {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutX(car.getImageView().getLayoutX() + 7);
		}
	}
	
}
