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
	public static void main(String[] args) {
	    Application.launch(args);
	  }
	
	@Override
	public void start(Stage stage) {
		System.out.println("start");
		String Path = "//Users//LTH//Desktop";
		String imagePath = "//1.jpg";
		Image image;
		BufferedImage temp;
		try{
			temp = ImageIO.read(new File(Path + imagePath));
		}catch(IOException e){
			System.out.println("IO Exception occured");
			System.out.println("check file");
			return;
			}
		image = SwingFXUtils.toFXImage(temp, null);
	    ImageView imageView = new ImageView(image);

	    Button saveBtn = new Button("Save Image");
	    saveBtn.setOnAction(e -> saveToFile(image));

	    VBox root = new VBox(10, imageView, saveBtn);
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.setTitle("");
	    stage.show();
	  }

	public static void saveToFile(Image image) {
		File outputFile = new File("C:\\JavaFX");
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bImage, "png", outputFile);
	    } catch (IOException e) {
	    	System.out.println("file index not found");
	    	throw new RuntimeException(e);
	    }
	  }
}
