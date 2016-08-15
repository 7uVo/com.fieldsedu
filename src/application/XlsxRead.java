//package application;
//import java.io.FileInputStream;
//
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
////�������Ͽ��� string�� ����
////���ϴ� �κи� ������ �� �� ���� ��
//public class XlsxRead {
//	public static String xlsxRead(String path) {
//		XSSFRow row;
//		XSSFCell cell;
//		String str = null;
//		try {
//			FileInputStream inputStream = new FileInputStream("C:\\Users\\LeeTaeHyun\\Documents\\file.xlsx");
//			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//
//			//sheet�� ���
//			int sheetCn = workbook.getNumberOfSheets();
//			System.out.println("sheet�� : " + sheetCn);
//			
//			for(int cn = 0; cn < sheetCn; cn++){
//				System.out.println("����ϴ� sheet �̸� : " + workbook.getSheetName(cn));
//				System.out.println(workbook.getSheetName(cn) + " sheet ������ ��� ����");
//				
//				//0��° sheet ���� ���
//				XSSFSheet sheet = workbook.getSheetAt(cn);
//				
//				//���� sheet���� rows�� ���
//				int rows = sheet.getPhysicalNumberOfRows();
//				System.out.println(workbook.getSheetName(cn) + " sheet�� row�� : " + rows);
//				
//				//���� row���� ����� cell�� ���
//				int cells = sheet.getRow(cn).getPhysicalNumberOfCells(); //
//				System.out.println(workbook.getSheetName(cn) + " sheet�� row�� ����� cell�� : " + cells);
//				
//				for (int r = 0; r < rows; r++) {
//					row = sheet.getRow(r); // row ��������
//					if (row != null) {
//						for (int c = 0; c < cells; c++) {
//							cell = row.getCell(c);
//							if (cell != null) {
//								String value = null;
//								switch (cell.getCellType()) {
//								case XSSFCell.CELL_TYPE_FORMULA:
//									value = cell.getCellFormula();
//									break;
//								case XSSFCell.CELL_TYPE_NUMERIC:
//									value = "" + cell.getNumericCellValue();
//									break;
//								case XSSFCell.CELL_TYPE_STRING:
//									value = "" + cell.getStringCellValue();
//									break;
//								case XSSFCell.CELL_TYPE_BLANK:
//									value = "[null �ƴ� ����]";
//									break;
//								case XSSFCell.CELL_TYPE_ERROR:
//									value = "" + cell.getErrorCellValue();
//									break;
//								default:
//								}
//								System.out.print(value + "\t");
//							} else {
//								System.out.print("[null]\t");
//							}
//						} // for(c) ��
//						System.out.print("\n");
//					}
//				} // for(r) ��
//			}
//			workbook.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return str;
//	}
//}