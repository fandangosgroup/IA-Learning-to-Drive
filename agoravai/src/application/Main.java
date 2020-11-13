package application;
	
import db.UserDatabase;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			User user = new User(UserDatabase.getId(),"testesegundaPista","1530","testandoPista@hotmail.com");
			Register rg = new Register(user);
			rg.register();
			AnchorPane root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root,600,600);
			
//			final Button restartButton = new Button( "Restart 2" );
//			
//			root.getChildren().add(restartButton);
//			
//		    restartButton.setOnAction( __ ->
//		    {
//		      System.out.println( "Restarting app!" );
//		      primaryStage.close();
//		      Platform.runLater( () -> new Main().start( new Stage() ) );
//		    } );
			
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
