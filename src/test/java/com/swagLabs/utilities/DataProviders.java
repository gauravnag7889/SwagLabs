package com.swagLabs.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider for login data
	
	@DataProvider(name="LoginTestData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\DataDriven.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("LoginDataSheet");	
		int totalcols=xlutil.getCellCount("LoginDataSheet",1);
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("LoginDataSheet",i, j);  //1,0
			}
		}
	return logindata;//returning two dimension array
				
	}
}