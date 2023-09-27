package com.ExcelLoginPage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.BaseTest.DriverSetup;
import com.BaseTest.FilesSetup;
import com.Utility.Logs;

public class ExcelOrangeHRMLoginPage extends DriverSetup{
	String[] Credential=new String[3];
	
	XSSFWorkbook workbook;
	XSSFSheet TestDataSheet;
	XSSFSheet TestDataResultSheet;
	Row NewRow;
	
	String ActualResult;
	String ExpectedResult="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/auth/validateCredentials";
	
	@Test(priority=0)
	public void Login() throws IOException {
		FileInputStream ExcelTestDataFile=new FileInputStream("C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\src\\main\\java\\com\\ApplicationTestDataFiles\\TestData.xlsx");
		workbook=new XSSFWorkbook(ExcelTestDataFile);
		TestDataSheet=workbook.getSheet("LoginCredentials");

		for(int rowIndex=1;rowIndex<8;rowIndex++) {
			NewRow=TestDataSheet.getRow(rowIndex);
			for(int cellIndex=0;cellIndex<2;cellIndex++) {
				Cell UserNameRowOfCell=NewRow.getCell(cellIndex);
				Credential[cellIndex]=UserNameRowOfCell.getStringCellValue();
			}
			
//		Identification of the UserName 		
			By UsernameProperty=By.id("txtUsername");
			WebElement Username=driver.findElement(UsernameProperty);
			Username.sendKeys(Credential[0]);
		
//		Identification of the Password
			By PasswordProperty=By.name("txtPassword");
			WebElement Password=driver.findElement(PasswordProperty);
			Password.sendKeys(Credential[1]);
		
//		Identification of the Login Button
			By LoginButtonProperty=By.className("button");
			WebElement LoginButton=driver.findElement(LoginButtonProperty);
			LoginButton.click();
			
//			-----------------------Validation of the Dashboard-----------------------
			ActualResult=driver.getCurrentUrl();
//			Store Result of TestData after validation of Actual and Expected Result
			FileOutputStream ResultFile=new FileOutputStream("C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\src\\main\\java\\com\\ApplicationTestResultFiles\\TestDataResult.xlsx");
			Cell ResultRowOfCell=NewRow.createCell(2);

			if(ActualResult.contains(ExpectedResult)) {
				ResultRowOfCell.setCellValue("Failed");
				workbook.write(ResultFile);
				System.out.println("******Invalid Login credentials******");
			}
			else {
				ResultRowOfCell.setCellValue("Passed");
				workbook.write(ResultFile);
				System.out.println("******Successfully Login into OrangeHRM apllication******");
			}	
		}
		workbook.close();
	}
	
	@Test(enabled=true,priority=1)
	public void LogOut() throws IOException {
		By WelcomeDashboardProperty=By.id("welcome");
		WebElement welcome=driver.findElement(WelcomeDashboardProperty);
		welcome.click();
		
		By LogoutProperty=By.linkText("Logout");
		WebElement Logout=driver.findElement(LogoutProperty);
		Logout.click();
		
		System.out.println("******Successfully Logout the OrangeHRM application******");
	}

}
