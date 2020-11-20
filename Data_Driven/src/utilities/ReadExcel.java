package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadExcel {
	
	public String[][] readExcel(String filePath,String fileName,String sheetName) throws IOException{
	    File file =    new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook excelWorkbook = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    	excelWorkbook = new XSSFWorkbook(inputStream);
	    }
	    else if(fileExtensionName.equals(".xls")){
	        excelWorkbook = new HSSFWorkbook(inputStream);

	    }
	    Sheet excelSheet = excelWorkbook.getSheet(sheetName);
	    int rowCount = excelSheet.getLastRowNum()-excelSheet.getFirstRowNum();
	    int colCount = excelSheet.getRow(0).getLastCellNum();
	    
	    String data[][] = new String [rowCount+1][colCount];

	    for (int i = 0; i < rowCount+1; i++) {

	        Row row = excelSheet.getRow(i);

	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            data[i][j] = (row.getCell(j).getStringCellValue());
	        }
	    } 
	    return data;
	}
}
