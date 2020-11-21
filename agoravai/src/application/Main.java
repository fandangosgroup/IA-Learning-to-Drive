package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	public static Stage stage;
	public static Scene telaHome;
	public static Scene telaLogin;
	public static Scene telaCadastro;
	public static User usuario;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		stage = primaryStage;
		
		//User user = new User(UserDatabase.getId(),"testesegundaPista","1530","testandoPista@hotmail.com");
		
		
		 
		primaryStage.setTitle("IALD - IA Learning to Drive :D");
		
		Parent fxmlHome = FXMLLoader.load(getClass().getResource("main.fxml"));
		telaHome = new Scene(fxmlHome, 1280, 720);
		
		Parent fxmlLogin = FXMLLoader.load(getClass().getResource("login.fxml"));
		fxmlLogin.setFocusTraversable(true);
		telaLogin = new Scene(fxmlLogin, 600, 700);
		
		Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
		fxmlCadastro.setFocusTraversable(true);
		telaCadastro = new Scene(fxmlCadastro, 600, 700);
		
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();

                // Coloque aqui o código a ser executado ao fechar o sistema.

                primaryStage.close();
                Platform.exit();
                System.exit(0);
            }
        });
		
		primaryStage.setScene(telaLogin);
		primaryStage.show();
	}

	public static void chageScreen(String name) {
		switch (name) {
			case "main":
				stage.setScene(telaHome);
				break;
			case "login":
				stage.setScene(telaLogin);
				break;
			case "cadastro":
				stage.setScene(telaCadastro);
				break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}