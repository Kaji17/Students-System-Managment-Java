package com.pevir.StudentManagment;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private double x =0;
	private double y =0;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("VIews/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Resources/Styles/LoginStyle.css").toExternalForm());
			primaryStage.setScene(scene);
			
			
			//Permet de faire bouger la fenetre et d'éviter de la redimensionner 
			root.setOnMousePressed((MouseEvent event)->{
				x = event.getSceneX();
				y= event.getSceneY();
			});
			
			root.setOnMouseDragged((MouseEvent event)->{
				primaryStage.setX(event.getScreenX() - x);
				primaryStage.setY(event.getScreenY() - y);
			});
			
			//Permet de faire disparatraire la barre du haut
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
