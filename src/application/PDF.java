package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Files;

//import javafx.scene.image.Image;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
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

	public static void toPDF(Page[] page, int page_maxIndex, String path, String fileName){
		File outputFile = new File(path + "//" + fileName + ".pdf");
		//FileOutputStream pdfFileout = null;
		if(outputFile.exists() && !outputFile.isDirectory()){
			File tempFile = new File(path + "//" + "temp" + ".pdf");
			outputFile.renameTo(tempFile);
			System.out.println("outputFile : " + outputFile.getPath());
			
			PdfDocument pdfDoc = null;
			PdfDocument origPdf = null;
			Document document = null;
			try{
				pdfDoc = new PdfDocument(new PdfWriter(outputFile.getPath()));
				origPdf = new PdfDocument(new PdfReader(tempFile.getPath()));
			}catch(Exception e){}
			origPdf.copyPagesTo(1, origPdf.getNumberOfPages(), pdfDoc);
			PageSize pageSize = new PageSize(PageSize.A4);
			document = new Document(pdfDoc);
			
			for(int i = 0; i <= page_maxIndex; i++){
				PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
				Page.saveToFile(page[i], path, "temp");
				try {
					canvas.addImage(ImageDataFactory.create(path+"\\"+"temp.jpg"), pageSize, false);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			origPdf.close();
			pdfDoc.close();
			document.close();
			
			try{
				Files.delete(tempFile.toPath());
				if(tempFile.delete()) System.out.println("deleteSuccess");
				else System.out.println("delete not Success");
			}catch(Exception e){}
			
		}
		else{
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
		}
		
		return;
	}
}
