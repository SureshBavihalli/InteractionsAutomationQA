package com.pojoclass;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.HashMap;
import java.util.Iterator;



public class ExcelUtils {
    static  int rowCount=0;
   
    public static final String SAMPLE_XLSX_FILE_PATH = "./convertcsv.xlsx";

    public static void readExcel(Root res) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        System.out.println("**** Here we go! ****************");
        // Retrieving the number of sheets in the Workbook
        
      

        // You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("Sheet Name :  " + sheet.getSheetName());
        }

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

       //Use hash map to read rows as key value pair
        //read one row at a time and add in hashmap along with column names
        HashMap<String, String> excelData = new HashMap<String, String>();
        // Use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");

           
        	int colCOunt =  sheet.getRow(0).getLastCellNum();
        	System.out.println("Number of rows in a sheet: "+sheet.getLastRowNum());
        	System.out.println("Number of columns in a sheet:  "+colCOunt);
        	for (int i = 1; i <= sheet.getLastRowNum(); i++) {
        		
        		for (int k = 0; k < colCOunt; k++) {
        		if(sheet.getRow(i).getCell(k) != null  ) {
     
        			excelData.put(sheet.getRow(0).getCell(k).toString(), 
        				sheet.getRow(i).getCell(k).toString());
        		}
        		
        	}

        		int flag = 0;
        		String[][] values = new String[100][50];
			    //we got row data now, lets check this data with our response. for witch index it match!
        		for (int resIndex = 0; resIndex < res.getList().size(); resIndex++) {
        			
            		//If to check index in respose..used two params to check and also printing in loop.
            		if(excelData.get("main/temp").equals(String.valueOf(res.getList().get(resIndex).getMain().getTemp()))&&
            				excelData.get("wind/speed").equals(String.valueOf(res.getList().get(resIndex).getWind().getSpeed()))	) {
							
            			
            			    //Print in console
            				System.out.println("Expected      |       Actual");
							System.out.println("--------------------------------");
							System.out.println(excelData.get("main/temp")+"            "+res.getList().get(resIndex).getMain().getTemp());
							System.out.println(excelData.get("clouds/all")+"                 "+res.getList().get(resIndex).getClouds().getAll());
							System.out.println(excelData.get("wind/speed")+"              "+res.getList().get(resIndex).getWind().getSpeed());
							System.out.println(excelData.get("weather/0/id")+"              "+res.getList().get(resIndex).getWeather().get(0).getId());
							System.out.println(excelData.get("weather/0/main")+"             "+res.getList().get(resIndex).getWeather().get(0).getMain());
						 
							
							//Write to excel - Results.xlsx located in project folder!
							WriteOutput.write(excelData.get("main/temp"), Double.toString(res.getList().get(resIndex).getMain().getTemp()));   
							WriteOutput.write(excelData.get("clouds/all"), Double.toString(res.getList().get(resIndex).getClouds().getAll()));
							WriteOutput.write(excelData.get("wind/speed"), Double.toString(res.getList().get(resIndex).getWind().getSpeed()));
							WriteOutput.write(excelData.get("weather/0/id"), Double.toString(res.getList().get(resIndex).getWeather().get(0).getId()));
							WriteOutput.write(excelData.get("weather/0/main"), res.getList().get(resIndex).getWeather().get(0).getMain());
							 
						 
							flag = 1;
            			
            		}

           }
        		
        		//If not match!
        		if (flag == 0) {
					System.out.println("No data match for main/tem : "+excelData.get("main/temp"));
				   
        		}
            		
        		
            	
        		//excelData.clear();//for next iteration
        		 
                flag =0; //for next iteration
	
			}
        	
        // Closing the workbook
       // workbook.close();
    }

	
}