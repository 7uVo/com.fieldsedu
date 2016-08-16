package application;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
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
	Page page;
	String basic_path;
	SceneMain sceneMain;
	public static void main(String[] args) {
	    Application.launch(args);
	  }
	@Override
	public void init(){
		//basic_path 에 기본적인 path로 초기화 할 것
		basic_path = new String("C:\\Users\\LeeTaeHyun\\Documents");
		page = new Page(basic_path+"\\backGround.jpg");
		sceneMain = new SceneMain(page);
	}
	@Override
	public void start(Stage stage) {
		
		//다른 path들은 이제 basic_path에 concatenation 할 수 있게 구현할 것
		//String imagePath = "image1.jpg";
		

	    sceneMain.addButton.setOnAction(e -> {
	    	String path = new String();
	    	path = basic_path + "//" + sceneMain.listSchool.getValue() + "//" + sceneMain.listGrade.getValue() + "//" + sceneMain.listSemester.getValue() + "//" + sceneMain.listWorkbook.getValue() + "//" + sceneMain.problemNumber.getText() + ".jpg";
	    	page.setImage(path, sceneMain);
	    	sceneMain.problemNumber.clear();
	    });
	    
	    sceneMain.deleteAllButton.setOnAction(e -> {
	    	page.setAllNull(sceneMain);
	    });
	    
	    sceneMain.deleteButton.setOnAction(e -> {
	    	page.setNull(sceneMain);
	    });
	    sceneMain.exportButton.setOnAction(e -> {
	    	Page.saveToFile(page, basic_path, sceneMain.setName.getText());
	    	sceneMain.setName.clear();
	    });
		//Scene에 들어갈 것은 SceneMain 클래스 안에 저장해놓을것
	    stage.setScene(sceneMain.Scenemain);
	    stage.setTitle("필즈수학원 오답노트 생성기");
	    stage.show();
	  }
}
