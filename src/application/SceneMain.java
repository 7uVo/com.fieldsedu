package application;

import java.awt.Insets;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;

public class SceneMain {
	public Scene Scenemain;
	HBox root, setPath;
	VBox left, right;
	ImageView imageViewMain, imageViewTemp;
	ComboBox<String> listWorkbook, listSchool, listSemester, listGrade;
	Label workbook, school, semester, grade, problem, setname;
	TextField problemNumber, setName;
	Button addButton, exportButton;
	
	
	
	SceneMain(Page page){
		HBox root = new HBox();
		VBox left = new VBox();
		VBox right = new VBox();
		
		imageViewMain = new ImageView(page.backGround);
		
		//imageViewTemp 는 add 버튼을 눌렀을 때 초기화를 하는 형식으로 해야할 듯 또는 임시 파일을 만들어서 init함수에서 초기화 시켜주는 형식으로 고고
		imageViewTemp = new ImageView(SwingFXUtils.toFXImage(page.recentImage_Buffer, null));
		
		
		workbook = new Label("문제집");
		school = new Label("학교");
		semester = new Label("학기");
		grade = new Label("학년");
		problem = new Label("문제번호");
		
		listWorkbook = new ComboBox<String>();
		listSchool = new ComboBox<String>();
		listSemester = new ComboBox<String>();
		listGrade = new ComboBox<String>();
		
		listWorkbook.setItems(FXCollections.observableArrayList("개념원리RPM", "쎈SSEN"));
		listSchool.setItems(FXCollections.observableArrayList("중학교","고등학교"));
		listSemester.setItems(FXCollections.observableArrayList("1학기","2학기"));
		listGrade.setItems(FXCollections.observableArrayList("1학년","2학년","3학년"));
		
		problemNumber = new TextField();
		setName = new TextField();
		setname = new Label("이름 설정");
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
		
		addButton = new Button("add");
		exportButton = new Button("export");
		setPath.getChildren().add(addButton);
		
		
		left.getChildren().add(imageViewMain);
		right.getChildren().add(setPath);
		right.getChildren().add(imageViewTemp);
		right.getChildren().add(setname);
		right.getChildren().add(setName);
		right.getChildren().add(exportButton);
		
		root.getChildren().add(left);
		root.getChildren().add(right);
		
		Scenemain = new Scene(root);
		
	}
	
	void setImageViewMain(Image image){
		imageViewMain.setImage(image);
		return;
	}
	void setImageViewTemp(Image image){
		imageViewTemp.setImage(image);
		return;
	}
}
