package application;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import javafx.application.Platform;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	Page[] page;
	String basic_path;
	SceneMain sceneMain;
	boolean isDEBUG = false, isLeeTaeHyun = true;
	int page_index, page_maxIndex;
	File jarPath;
	public static void main(String[] args) {
	    Application.launch(args);
	  }
	@Override
	public void init(){
		page = new Page[1000];
		page_index = 0;
		page_maxIndex = 0;
	}
	public void init_setFile_Dir(){
		try{
			page[page_index] = new Page();
		}catch(IOException i){
			if(isDEBUG){
				Stage dialog = new Stage(StageStyle.UTILITY);
				dialog.initModality(Modality.WINDOW_MODAL);
				dialog.initOwner(sceneMain.Scenemain.getWindow());
				dialog.setTitle("ERROR");
				dialog.setScene(new Scene(new VBox(new Label("Page class init error"))));
				dialog.setResizable(false);;
				dialog.show();	
			}
			
			System.out.println("file io error");
		}
		if(isLeeTaeHyun){
			jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();
			//jarPath.getParentFile().getAbsolutePath();
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("기본 경로 선택");;
			File selectedFile = directoryChooser.showDialog(new Stage());
			basic_path = selectedFile.getPath();	
			
		}
		else{
			basic_path = new String("G:\\com.fieldsedu");
		}
		sceneMain = new SceneMain();
	}
	
	void addButtonFunction(){
		String path = new String();
    	String temp = "//";
    	if(sceneMain.middleSchoolRadioButton.isSelected()){
    		path = basic_path + temp + sceneMain.middleSchoolRadioButton.getUserData() + temp + sceneMain.listGrade.getValue() + temp + sceneMain.listSemester.getValue() + temp + sceneMain.listMiddleSchoolWorkbook.getValue() + temp + sceneMain.problemNumber.getText() + ".jpg";
    	}
    	else if(sceneMain.highSchoolRadioButton.isSelected()){
    		path = basic_path + temp + sceneMain.highSchoolRadioButton.getUserData() + temp + sceneMain.listHighSchoolSubject.getValue() + temp + sceneMain.listHighSchoolWorkbook.getValue() + temp + sceneMain.problemNumber.getText() + ".jpg";
    	}
    	if(!page[page_index].setImage(path, sceneMain)){
    		nextPageButtonFunction();
    		page[page_index].setImageByFalse(sceneMain);
    	}
    	
	}
	void previousPageButtonFunction(){
		page_index--;
    	if(page_index < 0) page_index = 0;
    	sceneMain.nowPage.setText(String.valueOf(page_index+1));
    	sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(page[page_index].outputImage_Buffer, null));
    	sceneMain.imageViewTemp.setImage(SwingFXUtils.toFXImage(page[page_index].outputImage_Buffer, null));
	}
	void nextPageButtonFunction(){
		page_index++;
    	if(page_index >= 100) page_index = 99;
    	page_maxIndex = Math.max(page_maxIndex, page_index);
    	sceneMain.maxPage.setText(String.valueOf(page_maxIndex+1));
    	sceneMain.nowPage.setText(String.valueOf(page_index+1));
    	if(page[page_index] == null) {
    		try{
    			page[page_index] = new Page();
    		}catch(IOException ioe){
    			System.out.println("IOException at create new page object");
    		}
    	}
    	sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(page[page_index].outputImage_Buffer, null));
    	sceneMain.imageViewTemp.setImage(SwingFXUtils.toFXImage(Page.recentImage_Buffer, null));
	}
	@Override
	public void start(Stage stage) {
		init_setFile_Dir();
		sceneMain.addButton.setOnAction(e -> {
	    	addButtonFunction();
	    });
	    
	    sceneMain.deleteAllButton.setOnAction(e -> {
	    	page[page_index].setAllNull(sceneMain);
	    });
	    
	    sceneMain.deleteButton.setOnAction(e -> {
	    	page[page_index].setNull(sceneMain);
	    });
	    sceneMain.exportButton.setOnAction(e -> {
	    	//Page.saveToFile(page[page_index], basic_path, sceneMain.setName.getText());
	    	PDF.toPDF(page, page_maxIndex, basic_path, sceneMain.setName.getText());
	    	sceneMain.setName.clear();
	    });
	    sceneMain.nextPageButton.setOnAction(e -> {
	    	nextPageButtonFunction();
	    });
	    sceneMain.previousPageButton.setOnAction(e -> {
	    	previousPageButtonFunction();
	    });
	    //sceneMain.listSchool.property
	    sceneMain.deleteLastPageButton.setOnAction(e -> {
	    	//page_maxIndex;
	    	if(page_index == page_maxIndex && page_index>0){
	    		previousPageButtonFunction();
	    	}
	    	if(page_maxIndex == 0){
	    		page[0].setAllNull(sceneMain);
	    	}
	    	else{
	        	page[page_maxIndex] = null;
	        	page_maxIndex--;
	        	sceneMain.maxPage.setText(String.valueOf(page_maxIndex+1));
	        	sceneMain.nowPage.setText(String.valueOf(page_index+1));
	        	if(page[page_index] == null) {
	        		try{
	        			page[page_index] = new Page();
	        		}catch(IOException ioe){
	        			System.out.println("IOException at create new page object");
	        		}
	        	}
	        	sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(page[page_index].outputImage_Buffer, null));
	        	sceneMain.imageViewTemp.setImage(SwingFXUtils.toFXImage(Page.recentImage_Buffer, null));
	    	}

	    });
	    
	    sceneMain.problemNumber.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    	@Override
	    	public void handle(KeyEvent keyEvent){
	    		if(keyEvent.getCode() == KeyCode.ENTER){
	    			addButtonFunction();
	    			sceneMain.problemNumber.clear();
	    		}
	    	}
	    });
	    
	    sceneMain.schoolGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
	    	@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if(sceneMain.middleSchoolRadioButton.isSelected()) {
					sceneMain.setPath.getChildren().add(1, sceneMain.setPathMiddleSchool);
					sceneMain.setPath.getChildren().remove(2);
				}
				//sceneMain.setPathMiddleSchool;
				if(sceneMain.highSchoolRadioButton.isSelected()) {
					sceneMain.setPath.getChildren().add(1, sceneMain.setPathHighSchool);
					sceneMain.setPath.getChildren().remove(2);
				}
			}
	    });
	    
	    
	    
	    stage.setScene(sceneMain.Scenemain);
	    System.out.println("setScene");
	    stage.setTitle("필즈 수학학원 오답노트 생성기");
	    stage.show();
	  }
}