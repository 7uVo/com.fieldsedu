package application;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Page {
	static Image backGround;
	static BufferedImage backGround_Buffer;
	static BufferedImage checkBoxImage_Buffer;
	static BufferedImage recentImage_Buffer;
	
	Image[] image;
	BufferedImage[] image_Buffer;
	BufferedImage outputImage_Buffer;
	Image outputImage;
	boolean isFullPage = false, isLeftFull = false;
	static int posx, posy = 350;
	static final int problemSize = 650;
	int temp;
	int x = posx, y[] = {posy, 150};
	int checkSpace = 140;
	
	Page() throws IOException{
		//setBackGroundImage(path);
		if(backGround_Buffer == null || checkBoxImage_Buffer == null){
			try{	
				backGround_Buffer = ImageIO.read(getClass().getResourceAsStream("/resources/backGround.jpg"));
				checkBoxImage_Buffer = ImageIO.read(getClass().getResourceAsStream("/resources/checkbox.png"));
			}catch(IOException i){
				System.out.println("file open error");
			}			
			backGround = SwingFXUtils.toFXImage(backGround_Buffer, null);
			checkBoxImage_Buffer = Page.scale(checkBoxImage_Buffer, 258, 60, (double)258/checkBoxImage_Buffer.getWidth());
		}
		//set recentImage_Buffer to temp_image(search next time);
		posx = (int)Page.backGround.getWidth()/14;
		if(recentImage_Buffer == null) {
			recentImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
			double scale = problemSize/(double)recentImage_Buffer.getWidth();
			recentImage_Buffer = Page.scale(recentImage_Buffer, problemSize, (int)(recentImage_Buffer.getHeight() * scale), scale);
		}
		outputImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		System.out.println("Height is : " + backGround.getHeight() + "Width is : " + backGround.getWidth());
		image = new Image[40];
		image_Buffer = new BufferedImage[40];
		isFullPage = isLeftFull = false;
	}
	
	void setoutputImage_fromBuffer(){
		this.outputImage = SwingFXUtils.toFXImage(outputImage_Buffer, null);
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
	
	/*
	 * create new BufferedImage by existed one//deepcopy
	 * */
	static BufferedImage deepCopy_BufferedImage(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	Image getImage(int index){
		return image[index];
	}
	
	/*
	 * set BufferedImage object and Image object by their index not initialized null;
	 * so initialize them to ImageIO to path
	 * */
	boolean setImage(String path, SceneMain sceneMain){
		int index;
		for(index = 0; index < 40; index++) if(image[index]==null) break;
		System.out.println("setImage index = " + index);
		
		try{
			recentImage_Buffer = ImageIO.read(new File(path));
			double scale = problemSize/(double)recentImage_Buffer.getWidth();
			recentImage_Buffer = Page.scale(recentImage_Buffer, problemSize, (int)(recentImage_Buffer.getHeight() * scale), scale);
			if(index == 40 || isFullPage == true || y[1] + Page.recentImage_Buffer.getHeight() + Page.checkBoxImage_Buffer.getHeight() + 10 + checkSpace > Page.backGround.getHeight()){
				return false;
			}
			image_Buffer[index] = deepCopy_BufferedImage(recentImage_Buffer);
		}catch(IOException i){
			System.out.println("image_Buffer open Error! index is : " + index);
			System.out.println("path is : " + path);
			return true;
		}
		image[index] = SwingFXUtils.toFXImage(image_Buffer[index], null);
		sceneMain.imageViewTemp.setImage(SwingFXUtils.toFXImage(recentImage_Buffer,  null));
		
		overlayImage(sceneMain);
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
		return true;
	}
	
	boolean setImageByFalse(SceneMain sceneMain){
		int index;
		for(index = 0; index < 40; index++) if(image[index]==null) break;
		System.out.println("setImagebyFalse index = " + index);
		if(index == 40){
			System.out.println("image array is full");
			return false;
		}
		if(y[1] + Page.recentImage_Buffer.getHeight() + Page.checkBoxImage_Buffer.getHeight() + 10 + checkSpace > Page.backGround.getHeight()){
			System.out.println("setImageByFalse return false");
			System.out.println(y[1]);
			System.out.println(Page.recentImage_Buffer.getHeight());
			System.out.println(Page.checkBoxImage_Buffer.getHeight());
			
			return false;
		}
		image_Buffer[index] = deepCopy_BufferedImage(recentImage_Buffer);
		image[index] = SwingFXUtils.toFXImage(image_Buffer[index], null);
		sceneMain.imageViewTemp.setImage(SwingFXUtils.toFXImage(recentImage_Buffer,  null));
		
		overlayImage(sceneMain);
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
		return true;
	}
	
	void setAllNull(SceneMain sceneMain){
		for(int i=0;i<40;i++){
			image[i]=null;
			image_Buffer[i]=null;
		}
		System.out.println("setAllNull");
		outputImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		overlayImageAll(sceneMain);
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
	}
	/*set Null like stack
	 * and after set to null call overlay func, it is overlay first to end
	 * 
	 * */

	void setNull(SceneMain sceneMain){
		int index;
		for(index = 39; index>=0; index--){
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
		overlayImageAll(sceneMain);
		
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
	}
	/*
	 * overlayImage is a function which get graphics of each BufferedImage object and draw to outputImage_Buffer 
	 * clear to backGround Image and overlay one by one
	 * */
	void overlayImage(SceneMain sceneMain){
		System.out.println("Width is : "+this.outputImage_Buffer.getWidth()+"Height is : " + this.outputImage_Buffer.getHeight());
		Graphics g = this.outputImage_Buffer.getGraphics();
		
		int index = 0;
		for(;this.image_Buffer[index]==null;index++);
		
		
		temp = Page.checkBoxImage_Buffer.getHeight() + 10;
		if(isLeftFull == false && y[0] + recentImage_Buffer.getHeight() + temp + checkSpace < Page.backGround.getHeight()){
			g.drawImage(Page.checkBoxImage_Buffer, posx, y[0], null);
			y[0]+=temp;
			g.drawImage(recentImage_Buffer, posx, y[0], null);
			y[0]+=recentImage_Buffer.getHeight()+30;
		}
		else{
			isLeftFull = true;
			x = posx + (int)Page.backGround.getWidth()/2;
			if(y[1] + recentImage_Buffer.getHeight() + temp + checkSpace > Page.backGround.getHeight()){
				this.isFullPage = true;
				return ;
			}
			g.drawImage(Page.checkBoxImage_Buffer, x, y[1], null);
			y[1]+=temp;
			g.drawImage(recentImage_Buffer, x, y[1], null);
			y[1]+=recentImage_Buffer.getHeight()+30;
		}
		
		
	}
	void overlayImageAll(SceneMain sceneMain){
		System.out.println("Width is : "+this.outputImage_Buffer.getWidth()+"Height is : " + this.outputImage_Buffer.getHeight());
		Graphics g = this.outputImage_Buffer.getGraphics();
		
		int index = 0;
		for(;index < 40 && this.image_Buffer[index]==null;index++);
		//if(index == 40) return;
		this.isFullPage = false;
		this.isLeftFull = false;
		temp = Page.checkBoxImage_Buffer.getHeight() + 10;
		x = posx;
		y[0] = posy;
		y[1] = 150;
		
		for(int i = 0; i < 40; i++){
			if(this.image[i]!=null){
				if(index == 0 && y[0] + this.image_Buffer[i].getHeight() + temp + checkSpace < Page.backGround.getHeight()){
					g.drawImage(Page.checkBoxImage_Buffer, x, y[0], null);
					y[0]+=temp;
					g.drawImage(this.image_Buffer[i], x, y[0], null);
					y[0] += this.image_Buffer[i].getHeight() + 30;
				}
				else{
					index=1;
					this.isLeftFull = true;
					x = posx+(int)Page.backGround.getWidth()/2;
					if(y[1] + this.image_Buffer[i].getHeight() + temp + checkSpace > Page.backGround.getHeight()){
						this.isFullPage = true;
						return;
					}
					g.drawImage(Page.checkBoxImage_Buffer, x, y[1], null);
					y[1]+=temp;
					g.drawImage(this.image_Buffer[i],  x,  y[1],  null);
					y[1] += this.image_Buffer[i].getHeight() + 30;
				}
				
			}
		}
		
		
	}
	/*
	 * export page.outputImage_Buffer to path(basic path) + name(set filename); 
	 * */
	public static void saveToFile(Page page, String path, String name) {
		File outputFile = new File(path + "\\" + name + ".jpg");
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
	    } catch (NullPointerException npe){
	    	System.out.println("null pointer exception occured");
	    	
	    }
	  }
	/*
	 * sbi 는 크기 조정을 해야하는 BufferedImage object
	 * dest_Width, dest_Height is destination of each length
	 * sclaeRate is how much multiply to sbi
	 * */
	public static BufferedImage scale (BufferedImage sbi, int dest_Width, int dest_Height, double scaleRate){
		BufferedImage dbi = null;
		if(sbi != null){
			dbi = new BufferedImage(dest_Width, dest_Height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(scaleRate, scaleRate);
			g.drawRenderedImage(sbi,  at);
			}
		return dbi;
	}
}