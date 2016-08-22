package application;

import javafx.geometry.Insets;

import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;

public class SceneMain {
	public Scene Scenemain;
	HBox root, ButtonBox, PageButtonBox;
	VBox left, right;
	FlowPane setPath;
	ImageView imageViewMain, imageViewTemp;
	ComboBox<String> listWorkbook, listSchool, listSemester, listGrade;
	Label workbook, school, semester, grade, problem, setname, nowPage, maxPage;
	TextField problemNumber, setName;
	Button addButton, exportButton, deleteButton, deleteAllButton;
	Button nextPageButton, previousPageButton;
	MenuBar menuBar;
	
	SceneMain(){
		root = new HBox();
		left = new VBox();
		right = new VBox();
		ButtonBox = new HBox();
		PageButtonBox = new HBox();
		
		
		
		
		
		
		
		
		
		imageViewMain = new ImageView(SwingFXUtils.toFXImage(Page.backGround_Buffer,  null));
		imageViewMain.setFitHeight(877);
		imageViewMain.setFitWidth(620);
		//imageViewTemp 는 add 버튼을 눌렀을 때 초기화를 하는 형식으로 해야할 듯 또는 임시 파일을 만들어서 init함수에서 초기화 시켜주는 형식으로 고고
		imageViewTemp = new ImageView(SwingFXUtils.toFXImage(Page.backGround_Buffer, null));
		imageViewTemp.setFitHeight(500);
		imageViewTemp.setFitWidth(500);
		
		
	
		
		
		
		
		workbook = new Label("문제집 :");
		workbook.setFont(new Font(15));
		school = new Label("학교 :");
		school.setFont(new Font(15));
		semester = new Label("학기 :");
		semester.setFont(new Font(15));
		grade = new Label("학년 :");
		grade.setFont(new Font(15));
		problem = new Label("문제번호 :");
		problem.setFont(new Font(15));
		
		addButton = new Button("add");
		exportButton = new Button("export");
		deleteButton = new Button("delete");
		deleteAllButton = new Button("deleteAll");
		nextPageButton = new Button("next Page");
		previousPageButton = new Button("previous Page");
		
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
		setname.setFont(new Font(15));
		setPath = new FlowPane();
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
		setPath.getChildren().add(addButton);
		
		setPath.setPadding(new Insets(50, 10, 30, 20));
		setPath.setHgap(10);
		setPath.setVgap(10);
		
		PageButtonBox.getChildren().add(previousPageButton);
		PageButtonBox.setSpacing(30);
		nowPage = new Label("1");
		Label tempLabel = new Label("/");
		maxPage = new Label("1");
		PageButtonBox.getChildren().add(nowPage);
		PageButtonBox.getChildren().add(tempLabel);
		PageButtonBox.getChildren().add(maxPage);
		PageButtonBox.getChildren().add(nextPageButton);
		
		
		left.setPadding(new Insets(30,30,30,30));
		
		left.getChildren().add(imageViewMain);
		//left.getChildren().add(PageButtonBox);
		right.getChildren().add(setPath);
		right.getChildren().add(PageButtonBox);
		right.getChildren().add(imageViewTemp);
		right.getChildren().add(setname);
		right.getChildren().add(setName);
		root.getChildren().add(left);
		root.getChildren().add(right);
		right.getChildren().add(ButtonBox);
		right.setPadding(new Insets(50,20,50,20)); 
		right.setSpacing(20);
		
		
		ButtonBox.getChildren().add(deleteButton);
		ButtonBox.getChildren().add(deleteAllButton);
		ButtonBox.getChildren().add(exportButton);
		ButtonBox.setPadding(new Insets(30, 30, 30, 20));
		ButtonBox.setSpacing(20);
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