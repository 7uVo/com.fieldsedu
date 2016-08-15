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
		
		//imageViewTemp �� add ��ư�� ������ �� �ʱ�ȭ�� �ϴ� �������� �ؾ��� �� �Ǵ� �ӽ� ������ ���� init�Լ����� �ʱ�ȭ �����ִ� �������� ���
		imageViewTemp = new ImageView(SwingFXUtils.toFXImage(page.recentImage_Buffer, null));
		
		
		workbook = new Label("������");
		school = new Label("�б�");
		semester = new Label("�б�");
		grade = new Label("�г�");
		problem = new Label("������ȣ");
		
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
