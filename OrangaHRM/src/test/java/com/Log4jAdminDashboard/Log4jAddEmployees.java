package com.Log4jAdminDashboard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.LoginPage.OrangeHRMLoginPage;

public class Log4jAddEmployees extends OrangeHRMLoginPage {
	String FName;
	String MName;
	String LName;
	String EmpID;
	
//	List<String> Employeee=new ArrayList<>();
//	Employeee.set(0,"a");
	Row AddEmployeeRow;

	Cell AddEmployeeRowOfCell;

	@Test(priority = 2)
	public void AddEmployee() throws IOException, InterruptedException {

		FileInputStream PropertiesFile = new FileInputStream(
				"C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\src\\main\\java\\com\\Config\\OrangeHRMAdminPageElements.properties");
		Properties property = new Properties();
		property.load(PropertiesFile);

		By PIMProperty = By.id(property.getProperty("AddEmployeePIMProperty"));
		WebElement PIMList = driver.findElement(PIMProperty);

		Actions actions = new Actions(driver);
		actions.moveToElement(PIMList).perform();
		By AddEmployeeProperty = By.id(property.getProperty("AddEmployeePIMAddEmployee"));
		WebElement AddEmployee = driver.findElement(AddEmployeeProperty);
		AddEmployee.click();

		By firstnameProperty = By.name(property.getProperty("AddEmployeeFirstnameProperty"));
		By middlenameProperty = By.name(property.getProperty("AddEmployeeMiddlenameProperty"));
		By lastnameProperty = By.name(property.getProperty("AddEmployeeLastnameProperty"));
		By EmployeeIDProperty = By.name(property.getProperty("AddEmployeeIDProperty"));
		By SaveButtonProperty = By.id(property.getProperty("AddEmployeeSaveButtonProperty"));
/*
		WebElement firstname = driver.findElement(firstnameProperty);
		WebElement middlename = driver.findElement(middlenameProperty);
		WebElement lastname = driver.findElement(lastnameProperty);
		WebElement EmployeeID = driver.findElement(EmployeeIDProperty);
		WebElement SaveButton = driver.findElement(SaveButtonProperty);
*/
		FileInputStream ExcelTestDataFile = new FileInputStream(
				"C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\src\\main\\java\\com\\ApplicationTestDataFiles\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(ExcelTestDataFile);
		XSSFSheet AddEmployeeSheet = workbook.getSheet("AddEmployee");

		for (int RowIndex = 1; RowIndex < 6; RowIndex++) {
//			List<String> Employee=new ArrayList<>();
			String[] Employee = new String[4];
			AddEmployeeRow = AddEmployeeSheet.getRow(RowIndex);
			for (int CellIndex = 0; CellIndex < 3; CellIndex++) {
				AddEmployeeRowOfCell = AddEmployeeRow.getCell(CellIndex);
				Employee[CellIndex] = AddEmployeeRowOfCell.getStringCellValue();
//				Employee.add(CellIndex,AddEmployeeRowOfCell.getStringCellValue());
				
				if (CellIndex == 2) {	
					
					System.out.println(RowIndex + " , " + CellIndex);
					WebElement firstname = driver.findElement(firstnameProperty);
					WebElement middlename = driver.findElement(middlenameProperty);
					WebElement lastname = driver.findElement(lastnameProperty);
					WebElement EmployeeID = driver.findElement(EmployeeIDProperty);
					WebElement SaveButton = driver.findElement(SaveButtonProperty);
					
					System.out.println(Employee[0]+","+Employee[1]+","+Employee[2]);
					firstname.sendKeys(Employee[0]);
					middlename.sendKeys(Employee[1]);
					lastname.sendKeys(Employee[2]);
					EmpID = EmployeeID.getAttribute("value");

					FileOutputStream TestDataResultFile = new FileOutputStream(
							"C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\src\\main\\java\\com\\ApplicationTestResultFiles\\AddEmployeeResult.xlsx");
					AddEmployeeRowOfCell = AddEmployeeRow.createCell(CellIndex);
					AddEmployeeRowOfCell.setCellValue(EmpID);
					workbook.write(TestDataResultFile);
					SaveButton.click();
					
					PIMList = driver.findElement(PIMProperty);
					actions.moveToElement(PIMList).perform();
					AddEmployee = driver.findElement(AddEmployeeProperty);
					AddEmployee.click();				
				} else {
					System.out.println(RowIndex + " , " + CellIndex);
					Cell AddEmployeeRowOfCell = AddEmployeeRow.getCell(CellIndex);
					Employee[CellIndex]=AddEmployeeRowOfCell.getStringCellValue();
//					Employee.add(AddEmployeeRowOfCell.getStringCellValue());
				}
			}
		}
	}
}
