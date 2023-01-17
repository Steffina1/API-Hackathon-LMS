package com.nn.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFile {

	private Properties properties;
	private final String propertyFilePath= "./src/test/resources/configuration/configFile.properties";
	

	public ReadConfigFile() {

		File filePath = new File(propertyFilePath);
		//open the file in read mode 
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e)
			{
				System.out.println("Exception is"+e.getMessage());
			}
		}	

		//add method to read variable and the values
		public String getApplicationURL() {

			String baseurl=properties.getProperty("baseurl");
			return baseurl;
		}

		public String getProgramPath(){
			String programendpoint = properties.getProperty("programendpoint");
			return programendpoint;
		}

		public String getBatchesPath() {
			String batchesendpoint = properties.getProperty("batchesendpoint");
			return batchesendpoint;
		}

		public String getprogramExcelPath(){
			String programExcelPath = properties.getProperty("programexcelpath");
			return programExcelPath;
	
		}
		public String getbatchesExcelPath(){
			String batchesexcelpath = properties.getProperty("batchesexcelpath");
			return batchesexcelpath;
	
		}

		public String excelFilePath() {
			String batchesexcelpath = properties.getProperty("batchesexcelpath");
			return batchesexcelpath;
		}
		public int badrequestStatusCode() {
			int badrequestStatusCode = Integer.parseInt(properties.getProperty("badrequestStatusCode"));
			return badrequestStatusCode;
		}
		
}
