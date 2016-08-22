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
		//imageViewTemp �� add ��ư�� ������ �� �ʱ�ȭ�� �ϴ� �������� �ؾ��� �� �Ǵ� �ӽ� ������ ���� init�Լ����� �ʱ�ȭ �����ִ� �������� ���
		imageViewTemp = new ImageView(SwingFXUtils.toFXImage(Page.backGround_Buffer, null));
		imageViewTemp.setFitHeight(500);
		imageViewTemp.setFitWidth(500);
		
		
	
		
		
		
		
		workbook = new Label("������ :");
		workbook.setFont(new Font(15));
		school = new Label("�б� :");
		school.setFont(new Font(15));
		semester = new Label("�б� :");
		semester.setFont(new Font(15));
		grade = new Label("�г� :");
		grade.setFont(new Font(15));
		problem = new Label("������ȣ :");
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
		
		listWorkbook.setItems(FXCollections.observableArrayList("�������RPM", "��SSEN"));
		listSchool.setItems(FXCollections.observableArrayList("���б�","����б�"));
		listSemester.setItems(FXCollections.observableArrayList("1�б�","2�б�"));
		listGrade.setItems(FXCollections.observableArrayList("1�г�","2�г�","3�г�"));
		
		problemNumber = new TextField();
		
		setName = new TextField();
		setname = new Label("�̸� ����");
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