package Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.data.JSONReader;
import data.BaseTest;

public class MainTest extends BaseTest{

	
	
	
	@Test(dataProvider = "loginData")
    public void loginTest(HashMap<String, String> input) throws IOException {
	
		String email = input.get("email");
        String password = input.get("password");
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        
	
	}
	@DataProvider(name = "loginData")
	public Object[][] getData() throws IOException {
	    JSONReader jsonReader = new JSONReader();
	    List<HashMap<String, String>> data = jsonReader.getJsonData(
	        System.getProperty("user.dir") + "\\src\\test\\java\\data\\Purchase.json"
	    );

	    // Dynamically create Object[][] based on JSON size
	    Object[][] dataArray = new Object[data.size()][1];
	    for (int i = 0; i < data.size(); i++) {
	        dataArray[i][0] = data.get(i);
	    }

	    return dataArray;
	}

}
