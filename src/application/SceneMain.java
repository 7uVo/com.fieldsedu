package application;

import java.awt.Insets;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneMain {
	public Scene Scenemain;
	HBox root, setPath;
	VBox left, right;
	ImageView imageViewMain, imageViewTemp;
	ComboBox<String> listWorkbook, listSchool, listSemester, listGrade;
	Label workbook, school, semester, grade, problem;
	TextField problemNumber;
	Button addButton, exportButton;
	SceneMain(){
		
		//should check size of these box and view
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
		
		//check pixel
		imageViewMain = new ImageView();
		imageViewMain.setFitHeight(200);
		imageViewMain.setFitWidth(200);
		
		
		//check pixel
		imageViewTemp = new ImageView();
		imageViewTemp.setFitHeight(200);
		imageViewTemp.setFitWidth(200);
		
		
		//ComboBox<String> listWorkbook, listSchool, listSemester, listGrade;
		//Label workbook, school, semester, grade;
		//TextField problemNumber;
		//Button addButton, exportButton;
		
		workbook = new Label("문제집");
		school = new Label("학교");
		semester = new Label("학기");
		grade = new Label("학년");
		problem = new Label("문제번호");
		
		listWorkbook.setItems(FXCollections.observableArrayList("개념원리RPM", "쎈SSEN"));
		listSchool.setItems(FXCollections.observableArrayList("중학교", "고등학교"));
		listSemester.setItems(FXCollections.observableArrayList("1학기", "2학기"));
		listGrade.setItems(FXCollections.observableArrayList("1학년", "2학년", "3학년"));
		problemNumber = new TextField();
		
		setPath = new HBox();
		setPath.getChildren().add(school);
		setPath.getChildren().add(listSchool);
		setPath.getChildren().add(grade);
		setPath.getChildren().add(listGrade);
		setPath.getChildren().add(semester);
		setPath.getChildren().add(listSemester);
		setPath.getChildren().add(workbook);
		setPath.getChildren().add(listWorkbook);
		setPath.getChildren().add(problem);
		setPath.getChildren().add(problemNumber);
		
		
		
		
		root.getChildren().add(left);
		root.getChildren().add(right);
		left.getChildren().add(imageViewMain);
		right.getChildren().add(setPath);
		right.getChildren().add(imageViewTemp);
		
	}
	void setImageViewMain(Image image){
		imageViewMain.setImage(image);
		return;
	}
	void setImageViewTemp(Image image){
		imageViewTemp.setImage(image);
	}
}
