package application;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Page {
	Image backGround;
	Image[] image;
	BufferedImage backGround_Buffer;
	BufferedImage[] image_Buffer;
	BufferedImage outputImage_Buffer;
	BufferedImage recentImage_Buffer;

	
	
	Page(String path){
		setBackGroundImage(path);
		//set recentImage_Buffer to temp_image(search next time);
		recentImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		outputImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		System.out.println("Height is : " + backGround.getHeight() + "Width is : " + backGround.getWidth());
		image = new Image[6];
		image_Buffer = new BufferedImage[6];
	}
	
	void setBackGroundImage(String path){
		try{
			backGround_Buffer = ImageIO.read(new File(path));			
		}catch(IOException i){
			System.out.println("backGround_Buffer open Error!");
			System.out.println("path is : " + path);
			return;
		}
		backGround = SwingFXUtils.toFXImage(backGround_Buffer, null);
		
	}
	static BufferedImage deepCopy_BufferedImage(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	void setImage(String path, SceneMain sceneMain){
		int index;
		for(index = 0; index < 6; index++) if(image[index]==null) break;
		System.out.println("setImage index = " + index);
		if(index == 6){
			System.out.println("image array is full");
			return;
		}
		try{
			recentImage_Buffer = ImageIO.read(new File(path));
			image_Buffer[index] = deepCopy_BufferedImage(recentImage_Buffer);
		}catch(IOException i){
			System.out.println("image_Buffer open Error! index is : " + index);
			System.out.println("path is : " + path);
			return;
		}
		image[index] = SwingFXUtils.toFXImage(image_Buffer[index], null);
		sceneMain.imageViewTemp.setImage(SwingFXUtils.toFXImage(recentImage_Buffer,  null));
		overlayImage(this, sceneMain);
		
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
	}
	
	Image getImage(int index){
		return image[index];
	}
	
	void setAllNull(SceneMain sceneMain){
		for(int i=0;i<6;i++){
			image[i]=null;
			image_Buffer[i]=null;
		}
		System.out.println("setAllNull");
		outputImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		overlayImage(this, sceneMain);
		
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
	}
	//가장 끝에 있는 이미지를 없애줌
	void setNull(SceneMain sceneMain){
		int index;
		for(index = 5; index>=0; index--){
			if(image[index]!=null) break;
		}
		if(index < 0){
			System.out.println("imageArray is empty");
			return;
		}
		System.out.println("setNull index = " + index);
		image[index]=null;
		image_Buffer[index]=null;
		outputImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		overlayImage(this, sceneMain);
		
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
	}
	/* 
	 * 이 함수를 index를 넣어가지고 그 index에 해당하는 구역에 가서 오버레이 시킬 수 있도록 할 것!(case문 구현해서 좌표 설정)
	 */
	static void overlayImage(Page page, SceneMain sceneMain){
		
		//int w = (int)page.backGround.getWidth();
		//int h = (int)page.backGround.getHeight();
		//page.outputImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		System.out.println("Width is : "+page.outputImage_Buffer.getWidth()+"Height is : " + page.outputImage_Buffer.getHeight());
		Graphics g = page.outputImage_Buffer.getGraphics();
		
		//좌표 제대로 설정할 것
		for(int i = 0; i < 6; i++){
			if(page.image[i]!=null){
				System.out.println("overlayImage index = " + i);
				switch(i){
				case 0:
					g.drawImage(page.image_Buffer[i], 70, 658, null);
					break;
				case 1:
					g.drawImage(page.image_Buffer[i], 70, 1096, null);
					break;
				case 2:
					g.drawImage(page.image_Buffer[i], 70, 1535, null);
					break;
				case 3:
					g.drawImage(page.image_Buffer[i], 700, 658, null);
					break;
				case 4:
					g.drawImage(page.image_Buffer[i], 700, 1096, null);
					break;
				case 5:
					g.drawImage(page.image_Buffer[i], 700, 1535, null);
					break;
				}
			}
		}
	}
	public static void saveToFile(Page page, String path, String name) {
		File outputFile = new File(path+"\\"+name+".jpg");
		try{
			outputFile.createNewFile();
		}catch(IOException e){
			System.out.println("file make error");
			return;
		}
		try {
			ImageIO.write(page.outputImage_Buffer, "jpg", outputFile);
	    } catch (IOException e) {
	    	System.out.println("file index not found");
	    	throw new RuntimeException(e);
	    }
	  }
}