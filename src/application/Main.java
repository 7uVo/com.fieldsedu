package application;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox();
		root.setPrefWidth(1200);
		root.setPrefHeight(1500);
		root.setAlignment(Pos.CENTER_LEFT);
		root.setSpacing(20);
		
		ImageView imgView = new ImageView(new Image("@C:\\Users\\LeeTaeHyun\\Documents\\image1.jpg"));
		
		imgView.prefHeight(1000);
		imgView.prefWidth(500);
		VBox root2 = new VBox();
		HBox workbook = new HBox();
		TextField workbook_title = new TextField();
		TextField workbook_number = new TextField();
		Button button = new Button();
		button.setText("추가");
		button.setOnAction(event -> {
			
		});
		
		root.getChildren().add(imgView);
		root.setAlignment(Pos.CENTER_LEFT);
		root.getChildren().add(root2);
		workbook.getChildren().add(workbook_title);
		workbook.getChildren().add(workbook_number);
		root2.getChildren().add(workbook);
		root2.setAlignment(Pos.BASELINE_LEFT);
		root2.getChildren().add(button);
		
		
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("오답노트 생성 프로그램");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
