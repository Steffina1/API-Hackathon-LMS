package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	Properties prop;
	public ConfigReader() throws IOException {
		try {
			
FileInputStream propfile = new FileInputStream("C:\\Users\\amitf\\eclipse-workspace\\HackathonforAPI\\Backupforapihackathon\\Configss\\config.properties");
         prop = new Properties();  
        prop.load(propfile);
		} catch (FileNotFoundException e) {
			   e.printStackTrace();
			   System.out.println("Exception is "+e.getMessage());
		}
		
		
	}
	
	public String baseurl() {
		String url = prop.getProperty("baseurl");
		return url;
	}
	
	public String contenttypejson() {
		String json = prop.getProperty("content-type");
		return json;
	}
	public int successcode() {
		int code = Integer.parseInt(prop.getProperty("successstatuscode"));
		return code;
	}
	
	public String successsl() {
		String line = prop.getProperty("successstatusline");
		return line;
	}
	
	public int notallowedsc() {
		int code = Integer.parseInt(prop.getProperty("methodnotallowedsc"));
		return code;
	}
	
	public String unsupportedmediasl() {
		String line = prop.getProperty("unsupportedmediasl");
		return line;
	}
	
	public String methodnotallowedsl() {
		String line = prop.getProperty("methodnotallowedsl");
		return line;
	}
	
	public int unsupportedmediasc() {
		int code = Integer.parseInt(prop.getProperty("unsupportedmediasc"));
		return code;
	}
	
	public int notfoundsc() {
		int code = Integer.parseInt(prop.getProperty("notfoundstatuscode"));
		return code;
	}
	
	public String notfoundsl() {
		String line = prop.getProperty("notfoundsl");
		return line;
	}
	public int badrequestsc() {
		int code = Integer.parseInt(prop.getProperty("badrequestsc"));
		return code;
	}
	public String badrequestsl() {
		String line = prop.getProperty("badrequestsl");
		return line;
	}
	
	public String endpointforallbatches() {
		String line = prop.getProperty("endpiintforallbatches");
		return line;
	}
	public String endpointforallprogs() {
		String line = prop.getProperty("endpiintforallprograms");
		return line;
	}
	public String endpointforbatchbyname() {
		String line = prop.getProperty("endpiintgetbatchbyname");
		return line;
	}
	public String errorcodegetbatchbyname() {
		String line = prop.getProperty("errorcodegetbatchbyname");
		return line;
	}
	public String errormsggetbatchbyname() {
		String line = prop.getProperty("errormessagegetbatchbyname");
		return line;
	}
	
	
	
	

}


