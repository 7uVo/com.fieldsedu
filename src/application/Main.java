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
		page = new Page(basic_path+"\\image1.jpg");
		sceneMain = new SceneMain(page);
	}
	@Override
	public void start(Stage stage) {
		
		//다른 path들은 이제 basic_path에 concatenation 할 수 있게 구현할 것
		String imagePath = "image1.jpg";
		

	    sceneMain.addButton.setOnAction(e -> {
	    	//imagePath 를 설정 -> 어디서? combobox의 인자들을 묶을 것 and 모두 체크가 되어있지 않을 경우 Exception 발생시킬 것
	    	page.setImage(basic_path+"image2.jpg");
	    	
	    });
	    	
		
		
		
	    //Scene에 들어갈 것은 SceneMain 클래스 안에 저장해놓을것
	    stage.setScene(sceneMain.Scenemain);
	    stage.setTitle("필즈수학원 오답노트 생성기");
	    stage.show();
	  }

	public static void saveToFile(Image image, String path, String name) {
		File outputFile = new File(path+"\\"+name);
		try{
			outputFile.createNewFile();
		}catch(IOException e){
			System.out.println("file make error");
			return;
		}
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bImage, "png", outputFile);
	    } catch (IOException e) {
	    	System.out.println("file index not found");
	    	throw new RuntimeException(e);
	    }
	  }
}
