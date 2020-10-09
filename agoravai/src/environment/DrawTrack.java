package environment;


import java.awt.image.BufferedImage;

import db.UserDatabase;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class DrawTrack {
	private BufferedImage track;
	private ImageView imgTrack;
	
	public DrawTrack(Integer userId) {
		track = UserDatabase.getImageTrack(userId);
		this.setImageView();
	}
	
	public BufferedImage getTrack() {
		return track;
	}

	public ImageView getImgTrack() {
		return imgTrack;
	}

	private void setImageView() {
		this.imgTrack = this.convertToFxImage(this.track);
	}
	
	private ImageView convertToFxImage(BufferedImage image) {
	    WritableImage wr = null;
	    if (image != null) {
	        wr = new WritableImage(image.getWidth(), image.getHeight());
	        PixelWriter pw = wr.getPixelWriter();
	        for (int x = 0; x < image.getWidth(); x++) {
	            for (int y = 0; y < image.getHeight(); y++) {
	                pw.setArgb(x, y, image.getRGB(x, y));
	            }
	        }
	    }
	    return new ImageView(wr);
	}
}
