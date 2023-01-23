package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties property;

	public ReadConfig(){  //constructor to read config file from the path

		File filePath = new File("./src/test/resources/Configs/config.properties");

		//open the file in read mode 
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			property = new Properties();
			property.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is"+e.getMessage());
		}	

	}
	//add method to read variable and the values
	public String getApplicationURL() {

		String url=property.getProperty("url");
		return url;
	}

	public String getProgramPath(){
		String postpath = property.getProperty("programpath");
		return postpath;
	}

	public String getBatchPath() {
		String batchpath = property.getProperty("batchspath")
	}

	public String getExcelPath(){
		String excelpath = property.getProperty("excelpath");
		return postpath;
	}

}
