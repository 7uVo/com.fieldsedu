package application;

import java.awt.Insets;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneMain {
	public Scene Scenemain;
	
	SceneMain(){
		HBox root = new HBox();
		root.setPrefWidth(500);
		root.setPrefHeight(500);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);
		
		VBox left = new VBox();
		left.setPrefWidth(200);
		left.setPrefHeight(200);
		
		VBox right = new VBox();
		right.setPrefWidth(200);
		right.setPrefHeight(200);
		
		root.getChildren().add(left);
		root.getChildren().add(right);
		
		
		ImageView imgViewMain = new ImageView();
		ImageView imgViewTemp = new ImageView();
		
		
	}
}
