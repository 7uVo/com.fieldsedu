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
	VBox left, right,setPath;
	FlowPane setPathMiddleSchool, setPathHighSchool;
	ImageView imageViewMain, imageViewTemp;
	ComboBox<String> listMiddleSchoolWorkbook, listSchool, listSemester, listGrade, listHighSchoolSubject, listHighSchoolWorkbook;
	Label workbookMiddleSchool,workbookHighSchool, school, semester, grade, problem,problemSHOULDDELETE, setname, nowPage, maxPage, tempLabel, subject;
	TextField setName, problemNumber;
	Button addButton, exportButton, deleteButton, deleteAllButton;
	Button nextPageButton, previousPageButton; 
	MenuBar menuBar;
	RadioButton middleSchoolRadioButton, highSchoolRadioButton;
	ToggleGroup schoolGroup;
	
	
	void setComboBox(){
		listMiddleSchoolWorkbook = new ComboBox<String>();
		listSchool = new ComboBox<String>();
		listSemester = new ComboBox<String>();
		listGrade = new ComboBox<String>();
		listHighSchoolSubject = new ComboBox<String>();
		listHighSchoolWorkbook = new ComboBox<String>();
		
		listMiddleSchoolWorkbook.setItems(FXCollections.observableArrayList("�������RPM", "��SSEN"));
		listSchool.setItems(FXCollections.observableArrayList("���б�","����б�"));
		listSemester.setItems(FXCollections.observableArrayList("1�б�","2�б�"));
		listGrade.setItems(FXCollections.observableArrayList("1�г�","2�г�","3�г�"));
		listHighSchoolSubject.setItems(FXCollections.observableArrayList("����1","����2","������1","������2","Ȯ�������","���Ͽͺ���"));
		listHighSchoolWorkbook.setItems(FXCollections.observableArrayList("�������RPM", "��SSEN", "����", "��ǰ"));
	}
	void setLabel(){
		workbookMiddleSchool = new Label("������ :");
		workbookMiddleSchool.setFont(new Font(15));
		workbookHighSchool = new Label("������ :");
		workbookHighSchool.setFont(new Font(15));
		school = new Label("�б� :");
		school.setFont(new Font(15));
		semester = new Label("�б� :");
		semester.setFont(new Font(15));
		grade = new Label("�г� :");
		grade.setFont(new Font(15));
		problem = new Label("������ȣ :");
		problem.setFont(new Font(15));
		nowPage = new Label("1");
		tempLabel = new Label("/");
		maxPage = new Label("1");
		setname = new Label("�̸� ����");
		setname.setFont(new Font(15));
		subject = new Label("���� :");
		subject.setFont(new Font(15));
	}
	void setButton(){
		schoolGroup = new ToggleGroup();
		middleSchoolRadioButton = new RadioButton("���б�");
		middleSchoolRadioButton.setUserData("���б�");
		middleSchoolRadioButton.setToggleGroup(schoolGroup);
		middleSchoolRadioButton.setSelected(true);
		highSchoolRadioButton = new RadioButton("����б�");
		highSchoolRadioButton.setUserData("����б�");
		highSchoolRadioButton.setToggleGroup(schoolGroup);
		addButton = new Button("add");
		exportButton = new Button("export");
		deleteButton = new Button("delete");
		deleteAllButton = new Button("deleteAll");
		nextPageButton = new Button("next Page");
		previousPageButton = new Button("previous Page");
		
	}
	void setGap(){
		
	}
	void setBox(){
		root = new HBox();
		left = new VBox();
		right = new VBox();
		ButtonBox = new HBox();
		PageButtonBox = new HBox();
		setPath = new VBox();
		setPathHighSchool = new FlowPane();
		setPathMiddleSchool = new FlowPane();
		
	}
	void setImageView(){
		imageViewMain = new ImageView(SwingFXUtils.toFXImage(Page.backGround_Buffer,  null));
		imageViewMain.setFitHeight(877);
		imageViewMain.setFitWidth(620);
		//imageViewTemp �� add ��ư�� ������ �� �ʱ�ȭ�� �ϴ� �������� �ؾ��� �� �Ǵ� �ӽ� ������ ���� init�Լ����� �ʱ�ȭ �����ִ� �������� ���
		imageViewTemp = new ImageView(SwingFXUtils.toFXImage(Page.backGround_Buffer, null));
		imageViewTemp.setFitHeight(500);
		imageViewTemp.setFitWidth(500);
	}
	void setTextField(){
		
		
		problemNumber = new TextField();
		setName = new TextField();
		
	}
	SceneMain(){
		setBox();
		setButton();
		setLabel();
		setComboBox();
		setTextField();
		setImageView();
		root.getChildren().add(left);
		root.getChildren().add(right);
		left.getChildren().add(imageViewMain);
		right.getChildren().add(setPath);
		right.getChildren().add(PageButtonBox);
		right.getChildren().add(imageViewTemp);
		right.getChildren().add(setname);
		right.getChildren().add(setName);
		right.getChildren().add(ButtonBox);
		

		setPath.getChildren().add(new HBox(school, new VBox(middleSchoolRadioButton, highSchoolRadioButton)));
		setPath.getChildren().add(setPathMiddleSchool);
		setPath.getChildren().add(new HBox(problem, problemNumber, addButton));
		
		setPath.setSpacing(10);
	
		
		
		setPathHighSchool.getChildren().add(subject);
		setPathHighSchool.getChildren().add(listHighSchoolSubject);
		setPathHighSchool.getChildren().add(workbookHighSchool);
		setPathHighSchool.getChildren().add(listHighSchoolWorkbook);
		setPathHighSchool.setHgap(15);
		setPathHighSchool.setVgap(15);
		//setPathHighSchool.getChildren().add(problemHighSchool);
		//setPathHighSchool.getChildren().add(highSchoolProblemNumber);
		setPathMiddleSchool.getChildren().add(grade);
		setPathMiddleSchool.getChildren().add(listGrade);
		setPathMiddleSchool.getChildren().add(semester);
		setPathMiddleSchool.getChildren().add(listSemester);
		setPathMiddleSchool.getChildren().add(workbookMiddleSchool);
		setPathMiddleSchool.getChildren().add(listMiddleSchoolWorkbook);
		setPathMiddleSchool.setHgap(15);
		setPathMiddleSchool.setVgap(15);
		
		
		
		
		//setPathMiddleSchool.getChildren().add(school);
		//setPath.getChildren().add(listSchool);
		
		//setPathMiddleSchool.getChildren().add(problem);
		//setPathMiddleSchool.getChildren().add(middleSchoolProblemNumber);
		//setPathMiddleSchool.getChildren().add(addButton);
		//setPathMiddleSchool.setPadding(new Insets(50, 10, 30, 20));
		PageButtonBox.getChildren().add(previousPageButton);
		PageButtonBox.getChildren().add(nowPage);
		PageButtonBox.getChildren().add(tempLabel);
		PageButtonBox.getChildren().add(maxPage);
		PageButtonBox.getChildren().add(nextPageButton);
		PageButtonBox.setSpacing(30);
		
		
		
		
		left.setPadding(new Insets(30,30,30,30));
		
		
//		right.setPadding(new Insets(50,20,50,20)); 
		right.setSpacing(20);
		right.setPadding(new Insets(30,30,30,30));
		
		ButtonBox.getChildren().add(deleteButton);
		ButtonBox.getChildren().add(deleteAllButton);
		ButtonBox.getChildren().add(exportButton);
//		ButtonBox.setPadding(new Insets(30, 30, 30, 20));
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