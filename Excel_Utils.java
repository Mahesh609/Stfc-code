package Utils;

import java.util.ArrayList;

public class Excel_Utils {
    
static Excel_Reader reader;
    
    public static  ArrayList<Object[]> getDataFromexcel() {
        ArrayList<Object[]> myData=new ArrayList<Object[]>();
        try {
        
            reader=new Excel_Reader("C:\\Users\\j416\\eclipse-workspace\\Practice\\Excel\\example.xlsx");
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int rowNum = 2; rowNum <= reader.getRowCount("Sheet1"); rowNum++) {
        	String PageURL = reader.getCellData("Sheet1", "PageURL", rowNum);
        	//String Title = reader.getCellData("Sheet1", "Title", rowNum);

    
//        	String User_Name = reader.getCellData("Sheet1", "User_Name", rowNum);
//        	String Password= reader.getCellData("Sheet1","Password", rowNum);
//        	String Titles = reader.getCellData("Sheet1", "Titles", rowNum);
//        	String First_Name = reader.getCellData("Sheet1", "First_Name", rowNum);
//        	String Middle_Name = reader.getCellData("Sheet1", "Middle_Name", rowNum);
//        	String Marital_Status = reader.getCellData("Sheet1", "Marital_Status", rowNum);
//        	String Place_Of_Birth = reader.getCellData("Sheet1", "Place_Of_Birth", rowNum);
//        	String Father_Name = reader.getCellData("Sheet1", "Father_Name", rowNum);
//        	String Mother_Name = reader.getCellData("Sheet1", "Mother_Name", rowNum);
       	  
            Object ob[]= {PageURL};
            myData.add(ob);
        }
        return myData;

    }




}
