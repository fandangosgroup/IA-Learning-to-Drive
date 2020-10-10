package environment;

import java.awt.Color;

public class Collider {
	
	private DrawTrack track;
	
	public Collider(DrawTrack track) {
		this.track = track;
	}
	
	public Boolean verify(Car car) {
		int x = (int) car.getImageView().getLayoutX();
		int y = (int) car.getImageView().getLayoutY();
		int instantRGB = this.track.getTrack().getRGB(x, y);
		
		Color c = new Color(instantRGB);
        int blue = c.getBlue();
        int red = c.getRed();
        int green = c.getGreen();
        
		return  (blue + red + green) < 20? true : false;
	}
}
