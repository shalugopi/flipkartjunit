package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public  class MobilePurchase {
	static  WebDriver driver ;
static String name;

	@BeforeClass
	public static void   beforeClass() {
		System.out.println("Before class");
			System.setProperty("webdriver.chrome.driver", "D:\\java code\\Flipkart.junit\\lib\\chromedriver.exe");
		 driver=new ChromeDriver();
driver.get("https://www.flipkart.com/");
driver.manage().window().maximize();
	}
@AfterClass
	public static  void afterClass() throws InterruptedException {
	System.out.println("After Class");
	Thread.sleep(3000);
	 driver.quit();
}
static long startime;
@Before
public void beforeTest() {
	System.out.println("Before test");
	long starttime=System.currentTimeMillis();
}
	@After
	public void afterTest()  {
		System.out.println("After test");
long endtime=System.currentTimeMillis();
System.out.println(endtime-startime);
	}
	@Test
	public void method1() {
		WebElement clsbtn= driver.findElement(By.xpath("//button[text()='✕']"));
		clsbtn.click();
	}
	
	@Test
	public void method2(){
		
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("realme");	
		
	}
	@Test
	public void method3() throws IOException {
		 
		WebElement searchclk=driver.findElement(By.xpath("//button[@type='submit']"));
		searchclk.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		WebElement excelname=driver.findElement(By.xpath("(//div[contains(text(),'realme')])[1]"));
String name=excelname.getText();
		File f=new File("D:\\java code\\Flipkart.junit\\file\\realme.xlsx");
		FileInputStream f1=new FileInputStream(f);
		XSSFWorkbook w =new XSSFWorkbook(f1);
		XSSFSheet s=w.getSheet("mobile");
		s.getRow(0).createCell(1).setCellValue(name);
		 FileOutputStream fs=new FileOutputStream(f);
		 w.write(fs);
		 fs.close();
		
	}
	@Test
	public void method4() throws IOException {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement realmeclick=driver.findElement(By.xpath("(//div[contains(text(),'realme')])[1]"));
		realmeclick.click();
		Set<String> allwin=((WebDriver) driver).getWindowHandles();
		List<String> l=new ArrayList<String>();
			l.addAll(allwin);
			driver.switchTo().window(l.get(1));
			File f2=new File("D:\\\\java code\\\\Flipkart.junit\\\\file\\\\realme.xlsx");

			FileInputStream f3=new FileInputStream(f2);
			Workbook w =new XSSFWorkbook(f3);
			Sheet s1=w.getSheet("mobile");
			String data=s1.getRow(0).getCell(1).getStringCellValue();
	
	Assert.assertEquals(name,data);
	}
@Test
public void method5() throws IOException {
	TakesScreenshot t=(TakesScreenshot)driver;
	File source=t.getScreenshotAs(OutputType.FILE);
	File target=new File("D:\\java code\\Flipkart.junit\\screenshot\\flipkart.png");
	FileUtils.copyFile(source, target);
	WebElement down=driver.findElement(By.xpath("//div[text()='Highlights']"));
	JavascriptExecutor j=(JavascriptExecutor)driver;
	j.executeScript("arguments[0].scrollIntoView(true)",down);
	File source1=t.getScreenshotAs(OutputType.FILE);
	File target1=new File("D:\\java code\\Flipkart.junit\\screenshot\\flipkart2.png");
	FileUtils.copyFile(source1, target1);
}
		
	}
	
	
	
	
	
	
	
		
	

