package traning;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTraning {
	
	String listurl="https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html";
	String alerturl="https://www.hyrtutorials.com/p/alertsdemo.html";
	String windowurl="https://www.hyrtutorials.com/p/window-handles-practice.html";
	String ActionUrl="https://www.flipkart.com/";
	String baseUrl="https://www.hyrtutorials.com/p/basic-controls.html";
	WebDriver driver;
	
	public void dropdownlistselection()
	{
		System.setProperty("webdriver.chrome.driver","C:\\PracticeSelenium\\TestArtifact\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(listurl);
		WebElement course=driver.findElement(By.id("course"));
		WebElement ide=driver.findElement(By.id("ide"));
		Select dropdownlist=new Select(course);
		List<WebElement> elements=dropdownlist.getOptions();
		System.out.println("Options is Cource Drop downlist are : ");
		for(WebElement option:elements)
		{
			System.out.println(option.getText());
		}
		
		dropdownlist.selectByIndex(2);
		dropdownlist.selectByIndex(1);
		System.out.println("Selected option from  Cource Drop downlist is : "+dropdownlist.getFirstSelectedOption().getText());
		
		Select idedropdownlist=new Select(ide);
		List<WebElement> ideelements=idedropdownlist.getOptions();
		System.out.println("Options is IDE Multi option selection list are : ");
		for(WebElement option:ideelements)
		{
			System.out.println(option.getText());
		}
		
		idedropdownlist.selectByIndex(1);
		idedropdownlist.selectByIndex(2);
		
		List<WebElement> selecteditems=idedropdownlist.getAllSelectedOptions();
		System.out.println("Selected options from IDE list are ");
		for(WebElement element:selecteditems)
		{
			System.out.println(element.getText());
		}
		
		System.out.println("Selected option from  Cource Drop downlist is : "+idedropdownlist.getFirstSelectedOption().getText());
	}
	
	
	public void alertpratice() throws InterruptedException
	{
		
		System.setProperty("webdriver.chrome.driver","C:\\PracticeSelenium\\SeleniumKT\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(alerturl);
		WebElement AlertBox=driver.findElement(By.id("alertBox"));
		WebElement ConfirmBox=driver.findElement(By.id("confirmBox"));
		WebElement PromptBox=driver.findElement(By.id("promptBox"));
		
		AlertBox.click();
		driver.switchTo().alert().accept();
		Thread.sleep(5);
		
		ConfirmBox.click();
		driver.switchTo().alert().dismiss();
		Thread.sleep(5);
		
		PromptBox.click();
		driver.switchTo().alert().sendKeys("Sample");
		driver.switchTo().alert().accept();
		Thread.sleep(5);
		
	}

	public void windowhandle()
	{
		System.setProperty("webdriver.chrome.driver","C:\\PracticeSelenium\\SeleniumKT\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(windowurl);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		WebElement NewTabButton=driver.findElement(By.id("newTabBtn"));
		WebElement NewWindowButton=driver.findElement(By.id("newWindowBtn"));
		NewTabButton.click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		NewWindowButton.click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		String parentwindow=driver.getWindowHandle();
		System.out.println("Currently opened window Title name is"+driver.switchTo().window(parentwindow).getTitle());
		Set<String> allwindows=driver.getWindowHandles();
		System.out.println("Currently All opened window Title names are ");
		for(String win:allwindows)
		{
			System.out.println(driver.switchTo().window(win).getTitle());
		}
		
	}
	
	
	public void ActionsKT()
	{
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\PracticeSelenium\\SeleniumKT\\drivers\\chromedriver.exe");
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(ActionUrl);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Actions action=new Actions(driver);
			WebElement parentElement=driver.findElement(By.xpath("//span[text()='Electronics']"));
			
			action.moveToElement(parentElement).perform();
			WebElement childElement=driver.findElement(By.xpath("//a[text()='Laptop Accessories']"));
			
			action.moveToElement(childElement).perform();
			WebElement subchildElement= driver.findElement(By.xpath("//a[text()='UPS']")); 
			
			action.moveToElement(subchildElement).click().perform();
			
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='UPS1']")));
			
		}catch(Exception e)
		{
			System.out.println("Exception occured is "+e.getMessage());
		}
		
		
		//System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='UPS1']"))).toString());
		
		
	}
	
	
	public void UserRegistor() throws IOException
	{
		//this.baseUrl="";
		System.setProperty("webdriver.chrome.driver","C:\\PracticeSelenium\\SeleniumKT\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement Firstname=driver.findElement(By.id("firstName"));
		WebElement LastName=driver.findElement(By.id("lastName"));
		WebElement Emailid=driver.findElement(By.xpath("//input[@id='email']"));
		WebElement Pasword=driver.findElement(By.xpath("//input[@id='password']"));
		WebElement registoerbutton=driver.findElement(By.cssSelector("button[id=registerbtn]"));
		Firstname.sendKeys("Fnmae");
		LastName.sendKeys("lname");
		Emailid.sendKeys("emailid");
		Pasword.sendKeys("pwd");
		registoerbutton.click();
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));
		TakesScreenshot takeSS=(TakesScreenshot)driver;
		File srcfile=takeSS.getScreenshotAs(OutputType.FILE);
		File destfile=new File("C:\\PracticeSelenium\\SeleniumKT\\Evidences\\result"+UUID.randomUUID()+".png");
		FileUtils.copyFile(srcfile, destfile);
		//driver.findElement(By.cssSelector("div>button[id=clearbtn]")).click();
		driver.close();
		
	}

	
	public void collectionsKT()
	{
        
		ArrayList<String> alist=new ArrayList();
		alist.add("Str1");
		alist.add("Str2");
		alist.add("Str3");
		
		for(int i=0;i<alist.size();i++)
		{
			
		}
		
		
		HashSet<String> hset=new HashSet();
		hset.add("Str1");
		hset.add("Str2");
		hset.add("Str3");
		
		HashMap<String,String> hmap=new HashMap();
		hmap.put("Fname", "Venkat");
		hmap.put("Lname", "Mahehs");
		hmap.put("Email", "Venkat@gmail.com");
		
		
		
	}
	
	public void mainwindowopen() throws InterruptedException
	{
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/basic-controls.html");
		Thread.sleep(10);
	}

	
	public void CheckBrokerlinks() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\PracticeSelenium\\TestArtifact\\drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/basic-controls.html");
		String mainwindowhandle=driver.getWindowHandle();
		String mainwindowurl=driver.switchTo().window(mainwindowhandle).getCurrentUrl();
		List<WebElement> linkelements=driver.findElements(By.tagName("a"));
		//driver.close();
		int NoOfLinks=linkelements.size();
		System.out.println("Number of Links matched are :"+NoOfLinks);
		//Iterator<WebElement> itr=linkelements.iterator();
		for(WebElement element:linkelements) 
		{
			String Url=element.getAttribute("href");
			System.out.println("Url configured at link is "+Url);
			if(Url.isEmpty() || Url==null)
			{
				System.out.println("Link is  broker link");
			}else
			{
				driver.get(Url);
				String windowhandle=driver.getWindowHandle();
				String title=driver.switchTo().window(windowhandle).getTitle();
				if(!title.isEmpty())
				{
					System.out.println("Link is not broker link");
					
				}
				
			}
			Thread.sleep(5);
			driver.manage().window().maximize();
			driver.get("https://www.hyrtutorials.com/p/basic-controls.html");
			Thread.sleep(10);
			
		}
		
		
			
		
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		SeleniumTraning obj=new SeleniumTraning();
		obj.CheckBrokerlinks();
		
		
	}


}
