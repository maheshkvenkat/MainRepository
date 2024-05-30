package traning;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGKT {
	
	String baseUrl="https://www.hyrtutorials.com/p/basic-controls.html";
	WebDriver driver;
	
	@BeforeMethod
	@Parameters("Browser")
	public void OpenBrowser(String browsername)
	{
		switch(browsername)
		{
		case "Chrome":
			System.setProperty("webdriver.chrome.driver","C:\\PracticeSelenium\\TestArtifact\\drivers\\chromedriver.exe");
		    driver=new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "Edge": 
			System.setProperty("webdriver.gecko.driver","C:\\PracticeSelenium\\TestArtifact\\drivers\\msedgedriver.exe");
		    driver=new EdgeDriver();
			driver.manage().window().maximize();
			break;
		}
		
		
	}
	
	@Test(dataProvider="getdata")
	public void UserRegistor(String fname,String lname,String emailid,String pwd) throws IOException
	{
		//System.setProperty("webdriver.chrome.driver","C:\\PracticeSelenium\\TestArtifact\\drivers\\chromedriver.exe");
		//WebDriver driver=new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement Firstname=driver.findElement(By.id("firstName"));
		WebElement LastName=driver.findElement(By.id("lastName"));
		WebElement Emailid=driver.findElement(By.xpath("//input[@id='email']"));
		WebElement Pasword=driver.findElement(By.xpath("//input[@id='password']"));
		WebElement registoerbutton=driver.findElement(By.cssSelector("button[id=registerbtn]"));
		Firstname.sendKeys(fname);
		LastName.sendKeys(lname);
		Emailid.sendKeys(emailid);
		Pasword.sendKeys(pwd);
		registoerbutton.click();
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));
		TakesScreenshot takeSS=(TakesScreenshot)driver;
		File srcfile=takeSS.getScreenshotAs(OutputType.FILE);
		File destfile=new File("C:\\PracticeSelenium\\TestArtifact\\Evidences\\result"+UUID.randomUUID()+".png");
		FileUtils.copyFile(srcfile, destfile);
		//driver.findElement(By.cssSelector("div>button[id=clearbtn]")).click();
		
		
	}
	
	@AfterMethod
	public void CloseBrowser()
	{
		driver.close();
	}
	
	@DataProvider
	public Object[][] getdata() throws InvalidFormatException, IOException
	{
		File fileobj=new File("C:\\PracticeSelenium\\TestArtifact\\TestData\\testdata.xlsx");
		XSSFWorkbook wbook=new XSSFWorkbook(fileobj);
		XSSFSheet sheet=wbook.getSheet("login");
		int NoOfRows=sheet.getPhysicalNumberOfRows();
		int noofcells=sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] obj=new Object[NoOfRows][noofcells];
		for(int i=0;i<NoOfRows;i++)
		{
			for( int j=0;j<noofcells;j++)
			{
				obj[i][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		
		return obj;
		
		
		
		
		
	}

}
