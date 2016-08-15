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
	BufferedImage outputImage;
	BufferedImage recentImage_Buffer;
	int i;
	
	
	Page(String path){
		setBackGroundImage(path);
		recentImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		image = new Image[6];
		image_Buffer = new BufferedImage[6];
		i = 0;
		
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
	void setImage(String path){
		int index;
		for(index = 0; index < 6; index++) if(image[index]==null) break;
		
		try{
			recentImage_Buffer = ImageIO.read(new File(path));
			image_Buffer[index] = deepCopy_BufferedImage(recentImage_Buffer);
		}catch(IOException i){
			System.out.println("image_Buffer open Error! index is : " + index);
			return;
		}
		image[index] = SwingFXUtils.toFXImage(image_Buffer[index], null);
	}
	
	Image getImage(int index){
		return image[index];
	}
	
	void setAllNull(){
		for(int i=0;i<6;i++){
			image[i]=null;
			image_Buffer[i]=null;
		}
	}
	//가장 끝에 있는 이미지를 없애줌
	void setNull(){
		int index;
		for(index = 5; index>=0; index--){
			if(image[i]!=null) break;
		}
		image[index]=null;
		image_Buffer[index]=null;
	}
	/* 
	 * 이 함수를 index를 넣어가지고 그 index에 해당하는 구역에 가서 오버레이 시킬 수 있도록 할 것!(case문 구현해서 좌표 설정)
	 */
	static void overlayImage(Page page){
		
		int w = (int)page.backGround.getWidth();
		int h = (int)page.backGround.getHeight();
		page.outputImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = page.outputImage.getGraphics();
		
		//좌표 제대로 설정할 것
		for(int i = 0; i < 6; i++){
			if(page.image[i]!=null){
				switch(i){
				case 0:
					g.drawImage(page.image_Buffer[i], 0, 0, null);
					return;
				case 1:
					g.drawImage(page.image_Buffer[i], 0, 0, null);
					return;
				case 2:
					g.drawImage(page.image_Buffer[i], 0, 0, null);
					return;
				case 3:
					g.drawImage(page.image_Buffer[i], 0, 0, null);
					return;
				case 4:
					g.drawImage(page.image_Buffer[i], 0, 0, null);
					return;
				case 5:
					g.drawImage(page.image_Buffer[i], 0, 0, null);
					return;
				}
			}
		}
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
