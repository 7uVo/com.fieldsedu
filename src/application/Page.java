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
	
	Image[] image;
	BufferedImage[] image_Buffer;
	BufferedImage outputImage_Buffer;
	Image outputImage;
	BufferedImage recentImage_Buffer;
	boolean isSetBasicImage = false;
	static int posx, posy = 350;
	static int problemSize = 650;
	
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
			checkBoxImage_Buffer = Page.scale(checkBoxImage_Buffer, 202, 42, (double)42/checkBoxImage_Buffer.getHeight());
		}
		//set recentImage_Buffer to temp_image(search next time);
		posx = (int)Page.backGround.getWidth()/14;
		recentImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		outputImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		System.out.println("Height is : " + backGround.getHeight() + "Width is : " + backGround.getWidth());
		image = new Image[40];
		image_Buffer = new BufferedImage[40];
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
	void setImage(String path, SceneMain sceneMain){
		int index;
		for(index = 0; index < 40; index++) if(image[index]==null) break;
		System.out.println("setImage index = " + index);
		if(index == 40){
			System.out.println("image array is full");
			return;
		}
		try{
			recentImage_Buffer = ImageIO.read(new File(path));
			double scale = problemSize/(double)recentImage_Buffer.getWidth();
			recentImage_Buffer = Page.scale(recentImage_Buffer, problemSize, (int)(recentImage_Buffer.getHeight() * scale), scale);
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
	
	
	
	void setAllNull(SceneMain sceneMain){
		for(int i=0;i<40;i++){
			image[i]=null;
			image_Buffer[i]=null;
		}
		System.out.println("setAllNull");
		outputImage_Buffer = deepCopy_BufferedImage(backGround_Buffer);
		overlayImage(this, sceneMain);
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
		overlayImage(this, sceneMain);
		
		sceneMain.imageViewMain.setImage(SwingFXUtils.toFXImage(outputImage_Buffer,  null));
	}
	
	
	
	/*
	 * overlayImage is a function which get graphics of each BufferedImage object and draw to outputImage_Buffer 
	 * clear to backGround Image and overlay one by one
	 * */
	static void overlayImage(Page page, SceneMain sceneMain){
		System.out.println("Width is : "+page.outputImage_Buffer.getWidth()+"Height is : " + page.outputImage_Buffer.getHeight());
		Graphics g = page.outputImage_Buffer.getGraphics();
		
		int x = posx, y[] = {posy, 150}, index = 0;
		int temp = Page.checkBoxImage_Buffer.getHeight() + 10;
		for(int i = 0; i < 40; i++){
			if(page.image[i]!=null){
				if(index == 0 && y[0] + page.image_Buffer[i].getHeight() + temp < Page.backGround.getHeight()){
					g.drawImage(Page.checkBoxImage_Buffer, x, y[0], null);
					y[0]+=temp;
					g.drawImage(page.image_Buffer[i], x, y[0], null);
					y[0] += page.image_Buffer[i].getHeight() + 30;
				}
				else{
					index=1;
					x = posx+(int)Page.backGround.getWidth()/2;
					if(y[1] + page.image_Buffer[i].getHeight() + temp > Page.backGround.getHeight()) return;
					g.drawImage(Page.checkBoxImage_Buffer, x, y[1], null);
					y[1]+=temp;
					g.drawImage(page.image_Buffer[i],  x,  y[1],  null);
					y[1] += page.image_Buffer[i].getHeight() + 30;
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