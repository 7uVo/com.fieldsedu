package application;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//엑셀파일에서 string을 얻어옴
//원하는 부분만 가지고 올 수 있을 듯
public class XlsxRead {
	public static String xlsxRead(String path) {
		XSSFRow row;
		XSSFCell cell;
		String str = null;
		try {
			FileInputStream inputStream = new FileInputStream("C:\\Users\\LeeTaeHyun\\Documents\\file.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			//sheet수 취득
			int sheetCn = workbook.getNumberOfSheets();
			System.out.println("sheet수 : " + sheetCn);
			
			for(int cn = 0; cn < sheetCn; cn++){
				System.out.println("취득하는 sheet 이름 : " + workbook.getSheetName(cn));
				System.out.println(workbook.getSheetName(cn) + " sheet 데이터 취득 시작");
				
				//0번째 sheet 정보 취득
				XSSFSheet sheet = workbook.getSheetAt(cn);
				
				//취득된 sheet에서 rows수 취득
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println(workbook.getSheetName(cn) + " sheet의 row수 : " + rows);
				
				//취득된 row에서 취득대상 cell수 취득
				int cells = sheet.getRow(cn).getPhysicalNumberOfCells(); //
				System.out.println(workbook.getSheetName(cn) + " sheet의 row에 취득대상 cell수 : " + cells);
				
				for (int r = 0; r < rows; r++) {
					row = sheet.getRow(r); // row 가져오기
					if (row != null) {
						for (int c = 0; c < cells; c++) {
							cell = row.getCell(c);
							if (cell != null) {
								String value = null;
								switch (cell.getCellType()) {
								case XSSFCell.CELL_TYPE_FORMULA:
									value = cell.getCellFormula();
									break;
								case XSSFCell.CELL_TYPE_NUMERIC:
									value = "" + cell.getNumericCellValue();
									break;
								case XSSFCell.CELL_TYPE_STRING:
									value = "" + cell.getStringCellValue();
									break;
								case XSSFCell.CELL_TYPE_BLANK:
									value = "[null 아닌 공백]";
									break;
								case XSSFCell.CELL_TYPE_ERROR:
									value = "" + cell.getErrorCellValue();
									break;
								default:
								}
								System.out.print(value + "\t");
							} else {
								System.out.print("[null]\t");
							}
						} // for(c) 문
						System.out.print("\n");
					}
				} // for(r) 문
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}