package com.nn.api.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.nn.api.base.BaseClass;


public class ExcelUtils extends BaseClass {

	public static FileInputStream fis;
	public static FileOutputStream fio;
	public static XSSFWorkbook Workbook;
	public static XSSFSheet Sheet;
	public static XSSFRow Row;
	public static XSSFCell Cell;


	public static int getRowCount(String baseprogramExcelPath,String sheetname) throws Exception {

		//get row count from the sheet

		fis = new FileInputStream(baseprogramExcelPath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname);//get sheet by sheetname
		int RowCount = Sheet.getLastRowNum();
		Workbook.close();
		fis.close();
		return RowCount;
	}

	public static int getCellCount(String baseprogramExcelPath,String sheetname,int rownum) throws Exception {

		//get column count from the row
		fis = new FileInputStream(baseprogramExcelPath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname); //get sheet by index starts from 0
		Row = Sheet.getRow(rownum); //
		int CellCount = Row.getLastCellNum();
		Workbook.close();
		fis.close();
		return CellCount;

	}

	//get cell value
	public static String getCellData(String baseprogramExcelPath,String sheetname, int rownum, int colnum) throws Exception{

		//get cell value from the row and the column

		fis = new FileInputStream(baseprogramExcelPath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname);
		Row = Sheet.getRow(rownum);
		Cell = Row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String celldata = formatter.formatCellValue(Cell);
			return celldata ;


		} 
		catch (Exception e) {
			data = "";
		}
		//returns the cell value as a string 
		Workbook.close();
		fis.close();
		return data;

	}

	public static void setCellData(String baseprogramExcelPath,String sheetname, int rownum, int colnum, String data) throws IOException {

		fis = new FileInputStream(baseprogramExcelPath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname);
		Row = Sheet.getRow(rownum);
		Cell = Row.createCell(colnum);
		Cell.setCellValue(data);
		fio = new FileOutputStream(baseprogramExcelPath);
		Workbook.write(fio);
		Workbook.close();
		fio.close();
		fis.close();



	}



}


