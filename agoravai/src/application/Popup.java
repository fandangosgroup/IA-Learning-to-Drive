package application;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Popup {
	
	private static String a;
	private static String b;
    
	public static void display() {
		Stage popupwindow = new Stage();
		      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Usuario Cadastrado com sucesso!");
		      
		      
		Label label1 = new Label("Usuario Cadastrado com sucesso!");
		      
		     
		Button button1 = new Button("OK");
		     
		     
		button1.setOnAction(e -> {
			// Coloque aqui o código a ser executado ao fechar o sistema.
			
			
			popupwindow.close();
			Main.chageScreen("login");
			
			
		});
		
		VBox layout = new VBox(10);
		     
		      
		layout.getChildren().addAll(label1, button1);
		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene1 = new Scene(layout, 300, 250);
		      
		popupwindow.setScene(scene1);
		      
		popupwindow.showAndWait();
	}
	
	public static void displayError(String expt) {
		Stage popupwindow = new Stage();
		      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Não foi possivel cadastrar o usuario!");
		      
		      
		Label label1 = new Label(expt);
		System.out.println(expt);
		      
		     
		Button button1 = new Button("OK");
		     
		     
		button1.setOnAction(e -> {
			// Coloque aqui o código a ser executado ao fechar o sistema.
			

			popupwindow.close();
			
		});
		
		VBox layout = new VBox(10);
		     
		      
		layout.getChildren().addAll(label1, button1);
		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene1 = new Scene(layout, 300, 250);
		      
		popupwindow.setScene(scene1);
		      
		popupwindow.showAndWait();
	}

	public static String getA() {
		// TODO Auto-generated method stub
		return a;
	}

	public static String getB() {
		// TODO Auto-generated method stub
		return b;
	}
}
