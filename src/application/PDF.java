package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

//import javafx.scene.image.Image;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;





public class PDF {
	
	//왜 for문에서 page_maxIndex 와 같게 해야하는 지는 잘 모르겠음 
	public static void toPDF(Page[] page, int page_maxIndex, String path, String fileName){
		File outputFile = new File(path + "//" + fileName + ".pdf");
		//FileOutputStream pdfFileout = null;
		try{
			outputFile.createNewFile();
			//pdfFileout = new FileOutputStream(outputFile);
		}catch(FileNotFoundException fnfe){
			System.out.println("file not found exception");
		}catch(Exception e){
			System.out.println("file make error");
		}
		PdfDocument pdfDoc = null;
		try {
			pdfDoc = new PdfDocument(new PdfWriter(outputFile.getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PageSize pageSize = new PageSize(PageSize.A4);
		Document document = new Document(pdfDoc, pageSize);
		
		for(int i = 0; i <= page_maxIndex; i++){
			PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
			Page.saveToFile(page[i], path, "temp");
			try {
				canvas.addImage(ImageDataFactory.create(path+"\\"+"temp.jpg"), pageSize, false);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		document.close();
		pdfDoc.close();
		return;
	}
}
