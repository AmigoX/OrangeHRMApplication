package com.ExcelAdminDashboard;

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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.LoginPage.OrangeHRMLoginPage;

public class ExcelAddEmployees extends OrangeHRMLoginPage{
	String FName;
	String MName;
	String LName;
	String EmpID;
	String[] Employee=new String[4];
	Row AddEmployeeRow;
	
	Cell AddEmployeeRowOfCell;
	@Test(priority=2)
	public void AddEmployee() throws IOException {
			
		By PIMProperty=By.id("menu_pim_viewPimModule");
		WebElement PIMList=driver.findElement(PIMProperty);
		
		Actions actions=new Actions(driver);
		actions.moveToElement(PIMList).perform();
		By AddEmployeeProperty=By.id("menu_pim_addEmployee");
		WebElement AddEmployee=driver.findElement(AddEmployeeProperty);
		AddEmployee.click();
			
		By firstnameProperty=By.name("firstName");
		By middlenameProperty=By.name("middleName");
		By lastnameProperty=By.name("lastName");
		By EmployeeIDProperty=By.name("employeeId");
		By SaveButtonProperty=By.id("btnSave");
		
		WebElement firstname=driver.findElement(firstnameProperty);
		WebElement middlename=driver.findElement(middlenameProperty);
		WebElement lastname=driver.findElement(lastnameProperty);
		WebElement EmployeeID=driver.findElement(EmployeeIDProperty);
		WebElement SaveButton=driver.findElement(SaveButtonProperty);
		
		FileInputStream ExcelTestDataFile=new FileInputStream("C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\src\\main\\java\\com\\ApplicationTestDataFiles\\TestData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook=new XSSFWorkbook(ExcelTestDataFile);
		XSSFSheet AddEmployeeSheet=workbook.getSheet("AddEmployee");
		
		for(int RowIndex=1;RowIndex<6;RowIndex++) {
			AddEmployeeRow=AddEmployeeSheet.getRow(RowIndex);
			for(int CellIndex=0;CellIndex<3;CellIndex++) {
				AddEmployeeRowOfCell=AddEmployeeRow.getCell(CellIndex);
				Employee[CellIndex]=AddEmployeeRowOfCell.getStringCellValue();
				
				if(CellIndex==2) {
					System.out.println(RowIndex+" , "+CellIndex);
					firstname.sendKeys(Employee[0]);
					middlename.sendKeys(Employee[1]);
					lastname.sendKeys(Employee[2]);
					EmpID=EmployeeID.getAttribute("value");
					
					FileOutputStream TestDataResultFile=new FileOutputStream("C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\src\\main\\java\\com\\ApplicationTestResultFiles\\AddEmployeeResult.xlsx");
					AddEmployeeRowOfCell=AddEmployeeRow.createCell(CellIndex);
					AddEmployeeRowOfCell.setCellValue(EmpID);
					workbook.write(TestDataResultFile);
					SaveButton.click();
					
				}
				else {
					System.out.println(RowIndex+" , "+CellIndex);
					Cell AddEmployeeRowOfCell=AddEmployeeRow.getCell(CellIndex);
					Employee[CellIndex]=AddEmployeeRowOfCell.getStringCellValue();
				}
			}
		}
		
		
	}
}