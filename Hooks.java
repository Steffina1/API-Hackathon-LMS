
public class Hooks {

	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;

	import io.cucumber.java.After;
	import io.cucumber.java.Before;
	import io.cucumber.java.Scenario;
	import utilities.Helper;

	public class Hooks {
	 @Before
	    public static void setUp() {
	     
	     //need to write for base url
	    }
	 
	    @After
	    public static void tearDown(Scenario scenario) {
	 
	        if(scenario.isFailed()) {
	            final byte[] screenshot = ((TakesScreenshot) Helper.getDriver()).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png", scenario.getName()); 
	        }   
	         
	      
	    }

	}
}
