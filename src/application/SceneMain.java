package application;

import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;

public class SceneMain {
	public Scene Scenemain;
	HBox root, setPath, ButtonBox;
	VBox left, right;
	ImageView imageViewMain, imageViewTemp;
	ComboBox<String> listWorkbook, listSchool, listSemester, listGrade;
	Label workbook, school, semester, grade, problem, setname;
	TextField problemNumber, setName;
	Button addButton, exportButton, deleteButton, deleteAllButton;
	
	
	
	SceneMain(Page page){
		HBox root = new HBox();
		VBox left = new VBox();
		VBox right = new VBox();
		HBox buttonBox = new HBox();
		imageViewMain = new ImageView(SwingFXUtils.toFXImage(page.outputImage_Buffer,  null));
		imageViewMain.setFitHeight(877);
		imageViewMain.setFitWidth(620);
		//imageViewTemp �� add ��ư�� ������ �� �ʱ�ȭ�� �ϴ� �������� �ؾ��� �� �Ǵ� �ӽ� ������ ���� init�Լ����� �ʱ�ȭ �����ִ� �������� ���
		imageViewTemp = new ImageView(SwingFXUtils.toFXImage(page.recentImage_Buffer, null));
		imageViewTemp.setFitHeight(400);
		imageViewTemp.setFitWidth(300);
		
		
	
		
		
		
		
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
		setPath.getChildren().add(addButton);
		
		setPath.setPadding(new Insets(50, 30, 30, 20));
		setPath.setSpacing(20);
		left.setPadding(new Insets(30,30,30,30));
		
		left.getChildren().add(imageViewMain);
		right.getChildren().add(setPath);
		right.getChildren().add(imageViewTemp);
		right.getChildren().add(setname);
		right.getChildren().add(setName);
		root.getChildren().add(left);
		root.getChildren().add(right);
		right.getChildren().add(buttonBox);
		right.setPadding(new Insets(50,50,50,50)); 
		right.setSpacing(20);
		
		
		buttonBox.getChildren().add(deleteButton);
		buttonBox.getChildren().add(deleteAllButton);
		buttonBox.getChildren().add(exportButton);
		buttonBox.setPadding(new Insets(30, 30, 30, 20));
		buttonBox.setSpacing(20);
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