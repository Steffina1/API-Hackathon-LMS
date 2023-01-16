package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelreader {
	public String sheetname;
	public String testid;
	public Excelreader(String testid, String sheetname) {
		this.testid=testid;
		this.sheetname= sheetname;
		
	}
	
	public String readdatafromexcel() {
		
		File file = new File("C:\\Users\\amitf\\eclipse-workspace\\HackathonforAPI\\Backupforapihackathon\\src\\test\\resources\\Testdata\\ApihackathonLMS.xlsx");
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		XSSFWorkbook book = null;
		try {
			book = new XSSFWorkbook(fs);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		XSSFSheet sheet = book.getSheet(this.sheetname);
		int rowcount = sheet.getLastRowNum();
		 for (int rownum =1; rownum<=rowcount; rownum++) {
			
			 DataFormatter fmt = new DataFormatter();
			 Row excelrow = sheet.getRow(rownum);
			 
			if(this.testid.equals(fmt.formatCellValue(excelrow.getCell(0))))
			{
			 return fmt.formatCellValue(excelrow.getCell(1)) ;
			
		 }
		}
		 return"";
		}
}