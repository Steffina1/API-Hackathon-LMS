package com.lmshackathon.utilities;

public class Excelreader {
	
	import java.io.FileInputStream;
	import java.io.FileOutputStream;

	import org.apache.poi.sl.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;



	public class ExcelUtils {
		 
		public static FileInputStream fis;
		public static FileOutputStream fio;
		public static XSSFWorkbook workbook;
		public static XSSFSheet sheet;
		public static XSSFRow row;
		public static XSSFCell cell;
		
		
		public static int getRowCount(String excelpath,String sheetname) throws Exception {
		
			//get row count from the sheet
			
			fis = new FileInputStream(excelpath);
			workbook = new XSSFWorkbook(fis);
			sheet = Workbook.getSheet(sheetname);//get sheet by sheetname
			int rowcount = Sheet.getLastRowNum();
			workbook.close();
			fis.close();
			return rowcount;
		}
		
		public static int getCellCount(String excelpath,String sheetname,int rownum) throws Exception {
			
			//get column count from the row
			fis = new FileInputStream(excelpath);
			workbook = new xssfWorkbook(fis);
			sheet = workbook.getSheet(sheetname); //get sheet by index starts from 0
			row = Sheet.getRow(rownum); //
			int cellcount = Row.getLastCellNum();
			workbook.close();
			fis.close();
			return cellcount;
			
		}
		
		//get cell value
		public static String getCellData(String excelpath,String sheetname, int rownum, int colnum) throws Exception{
			
			//get cell value from the row and the column
			
			fis = new FileInputStream(BaseExcelPath);
			workbook = new xssfWorkbook(fis);
			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);
			//String data;
			//try {
				DataFormatter formatter = new DataFormatter();
				String celldata = formatter.formatCellValue(Cell);
				return celldata ;
				
				
			//} 
			//catch (Exception e) {
				//data = "";
			//}
			//returns the cell value as a string 
			//Workbook.close();
			//fis.close();
			//return data;
			
		}
		
				



	}


}
