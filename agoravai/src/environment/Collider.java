package environment;

import java.awt.Color;

public class Collider {
	
	private DrawTrack track;
	
	public Collider(DrawTrack track) {
		this.track = track;
	}
	
	public Boolean verify(Car car) {
		boolean retval = false;
		int xDireita = (int) car.getImageView().getLayoutX() + 5;
		int yDireita = (int) car.getImageView().getLayoutY();
		int xCima = (int) car.getImageView().getLayoutX();
		int yCima = (int) car.getImageView().getLayoutY() + 5;
		int xEsquerda = (int) car.getImageView().getLayoutX() - 5;
		int yEsquerda = (int) car.getImageView().getLayoutY();
		int xBaixo = (int) car.getImageView().getLayoutX();
		int yBaixo = (int) car.getImageView().getLayoutY() + 5;
        
		if(this.rgbVerify(xDireita, yDireita, car))
			retval = true;
		if(this.rgbVerify(xCima, yCima, car))
			retval = true;
		if(this.rgbVerify(xEsquerda, yEsquerda, car))
			retval = true;
		if(this.rgbVerify(xBaixo, yBaixo, car))
			retval = true;
		
        return retval;
	}
	
	private Boolean rgbVerify(int x, int y, Car car) {
		int instantRGB = this.track.getTrack().getRGB(x, y);
		Color c = new Color(instantRGB);
        Integer blue = c.getBlue();
        Integer red = c.getRed();
        Integer green = c.getGreen();
        if((blue + red + green) < 20) {
        	car.Kill();
        }
        return (blue + red + green) < 20 ? true : false;
	}
}
