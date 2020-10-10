package application;
	
import db.UserDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//User user = new User(UserDatabase.getId(),"testesegundaPista","1530","testandoPista@hotmail.com");
			//Register rg = new Register(user);
			//rg.register();
			AnchorPane root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
