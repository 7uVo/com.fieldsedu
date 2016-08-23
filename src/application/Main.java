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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.application.Platform;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Main extends Application {
	Page[] page;
	String basic_path;
	SceneMain sceneMain;
	int page_index, page_maxIndex;
	String pathInfo = new String("/resources/pathInfo.txt");
	
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
		}catch(IOException i){System.out.println("file io error");}
		
		
		sceneMain = new SceneMain();			
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("기본 경로 설정");;
		File selectedFile = directoryChooser.showDialog(new Stage());
		
		basic_path = selectedFile.getPath();	
	}
	
	
//	boolean isSetPath(){
//		 InputStream is = getClass().getResourceAsStream(pathInfo);
//		 InputStreamReader isr = new InputStreamReader(is);
//		 BufferedReader br = new BufferedReader(isr);
//		 try {
//			if((basic_path = br.readLine()) == null) return false;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		 return true;
//	}
//	
//	void setPath(String path){
//		System.out.println("SetPath");
//		
////		PrintWriter writer=null;
////		try {
////			writer = new PrintWriter(new File(this.getClass().getResource(pathInfo).getPath()));
////		} catch (FileNotFoundException e1) {
////			// TODO Auto-generated catch block
////			System.out.println("File not found");
////			e1.printStackTrace();
////		}
//		
//		BufferedWriter writer = null;
//		try{
//			//File pathInfoFile = new File(this.getClass().getResource(pathInfo));
//			writer = new BufferedWriter(new FileWriter(this.getClass().getResource(pathInfo)));
//			writer.write(path);
//		}catch(Exception e){}
//		 finally{
//			try{
//				writer.close();
//			}catch(Exception e){}
//		}
//		System.out.println("SetPath end");
//		
//	}
	
	
	
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
	
	
	void addButtonFunction(){
		String path = new String();
    	String temp = new String("\\");
    	path = basic_path + temp + sceneMain.listSchool.getValue() + temp + sceneMain.listGrade.getValue() + temp + sceneMain.listSemester.getValue() + temp + sceneMain.listWorkbook.getValue() + temp + sceneMain.problemNumber.getText() + ".jpg";
    	if(page[page_index].setImage(path, sceneMain) == false){
    		nextPageButtonFunction();
    		page[page_index].setImageByFalse(sceneMain);
    	}
    	sceneMain.problemNumber.clear();
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
	    	page_index--;
	    	if(page_index < 0) page_index = 0;
	    	sceneMain.nowPage.setText(String.valueOf(page_index+1));
	    	sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(page[page_index].outputImage_Buffer, null));
	    	sceneMain.imageViewTemp.setImage(SwingFXUtils.toFXImage(page[page_index].outputImage_Buffer, null));
	    });
	    //sceneMain.listSchool.property
	    
	    sceneMain.problemNumber.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    	@Override
	    	public void handle(KeyEvent keyEvent){
	    		if(keyEvent.getCode() == KeyCode.ENTER){
	    			addButtonFunction();
	    		}
	    	}
	    });
	    
	    
	    stage.setScene(sceneMain.Scenemain);
	    System.out.println("setScene");
	    stage.setTitle("필즈수학원 오답노트 생성기");
	    stage.show();
	  }
}