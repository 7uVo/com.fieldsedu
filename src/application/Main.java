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
		//basic_path �� �⺻���� path�� �ʱ�ȭ �� ��
		basic_path = new String("C:\\Users\\LeeTaeHyun\\Documents");
		page = new Page(basic_path+"\\image1.jpg");
		sceneMain = new SceneMain(page);
	}
	@Override
	public void start(Stage stage) {
		
		//�ٸ� path���� ���� basic_path�� concatenation �� �� �ְ� ������ ��
		String imagePath = "image1.jpg";
		

	    sceneMain.addButton.setOnAction(e -> {
	    	//imagePath �� ���� -> ���? combobox�� ���ڵ��� ���� �� and ��� üũ�� �Ǿ����� ���� ��� Exception �߻���ų ��
	    	page.setImage(basic_path+"image2.jpg");
	    	
	    });
	    	
		
		
		
	    //Scene�� �� ���� SceneMain Ŭ���� �ȿ� �����س�����
	    stage.setScene(sceneMain.Scenemain);
	    stage.setTitle("������п� �����Ʈ ������");
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
