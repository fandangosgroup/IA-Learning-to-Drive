package application;

import environment.Car;
import javafx.scene.image.ImageView;

public class Controller {
	
	
	public void moveUp(Car car) {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutY(car.getImageView().getLayoutY() - 7);	
		}
	}
	
	public void moveDown(Car car) {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutY(car.getImageView().getLayoutY() + 7);	
		}
	}
	
	public void moveLeft(Car car) {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutX(car.getImageView().getLayoutX() - 7);
		}
	}
	
	public void moveRight(Car car) {
		if(!car.getIsDead()) {
			car.getImageView().setLayoutX(car.getImageView().getLayoutX() + 7);
		}
	}
	
}
