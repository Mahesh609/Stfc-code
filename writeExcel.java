package ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writeExcel {

	public void write_to_Excel(String filePath, String fileName, String sheetName, String url) throws IOException {

		// Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook MyWorkbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
	
		if (fileExtensionName.equals(".xlsx")) {

			MyWorkbook = new XSSFWorkbook(inputStream);
		}

		else if (fileExtensionName.equals(".xls")) {

			MyWorkbook = new HSSFWorkbook(inputStream);
		}
   
		Sheet sheet = MyWorkbook.getSheet(sheetName);
				
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println(rowCount);

		Row row = sheet.getRow(0);
		System.out.println(rowCount);

		Row newRow = sheet.createRow(rowCount + 1);
//Create a loop over the cell of newly created Row
		for (int j = 0; j < row.getLastCellNum(); j++) {
			// Fill data in row
			Cell cell = newRow.createCell(j);
			cell.setCellValue(url);
		}

		inputStream.close();

		FileOutputStream outputStream = new FileOutputStream(file);

		MyWorkbook.write(outputStream);

		outputStream.close();

	}

	/*
	 * public static void main(String...strings) throws IOException{
	 * 
	 * //Create an array with the data in the same order in which you expect to be
	 * filled in excel file
	 * 
	 * String[] valueToWrite = {"Mr. E","Noida"};
	 * 
	 * //Create an object of current class
	 * 
	 * WriteGuru99ExcelFile objExcelFile = new WriteGuru99ExcelFile();
	 * 
	 * //Write the file using file name, sheet name and the data to be filled
	 * 
	 * objExcelFile.writeToExcel(System.getProperty("user.dir")+
	 * "\\src\\excelExportAndFileIO","ExportExcel.xlsx","ExcelGuru99Demo",
	 * valueToWrite);
	 * 
	 * }
	 */

}
