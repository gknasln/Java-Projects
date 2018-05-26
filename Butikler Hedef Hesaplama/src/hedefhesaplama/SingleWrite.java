/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hedefhesaplama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;



/**
 * this class created for writing shops one by one 
 * @author gknasln
 */
public class SingleWrite {
    private int totalTarget, personalNumber, index; 
   private String completed;
   private boolean proportionOption, personalTypeOption;
public SingleWrite(int shopNumber, int shopTarget) {
  try {
    proportionOption = getProportionOption();
    personalTypeOption = getPersonalTypeOption();
    new Calculate().Calculate(shopNumber, shopTarget);
    
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget);
    }
    
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("personel yok ");
   }else{
    Center.progressBar.setString(getShopName(shopNumber));
    File folder;
    if(getCalculateMode() == 0 ){
        folder = new File( Main.path + "/Butik Mağaza Hedefleri");
    }else{
        folder = new File( Main.path + "/Tekil Müşteri Hedefleri");
    }
        folder.mkdir();
        
    index = 0;
    personalNumber = 1;
            
    // creating methods and sheets 
    Workbook wbook = new HSSFWorkbook();
    Sheet page = wbook.createSheet(getShopName(shopNumber));
    
    // creating style for head line
    CellStyle headLine = wbook.createCellStyle();
    Font font = wbook.createFont();
    font.setFontHeightInPoints((short)16);
    font.setColor(IndexedColors.DARK_BLUE.getIndex());
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    
    headLine.setFont(font);
    headLine.setAlignment(CellStyle.ALIGN_CENTER);
    headLine.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
   

    CellStyle navy = wbook.createCellStyle();
    Font navyFont = wbook.createFont();
    navyFont.setColor(IndexedColors.DARK_BLUE.getIndex());
    navyFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    
    navy.setFont(navyFont);
    navy.setAlignment(CellStyle.ALIGN_CENTER);
    navy.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    
    CellStyle bold = wbook.createCellStyle();
    Font boldFont = wbook.createFont();
    boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    bold.setFont(boldFont);
    

    CellStyle boldCenter = wbook.createCellStyle();
    boldCenter.setAlignment(CellStyle.ALIGN_CENTER);
    Font boldCenterFont = wbook.createFont();
    boldCenterFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    boldCenter.setFont(boldCenterFont);

    CellStyle red = wbook.createCellStyle();
    red.setWrapText(true);
    red.setAlignment(HorizontalAlignment.LEFT);
    red.setVerticalAlignment(VerticalAlignment.CENTER);
    
    Font redFont = wbook.createFont();
    redFont.setColor(IndexedColors.RED.getIndex());
    redFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    red.setFont(redFont);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(shopNumber) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(shopNumber) + " Tekil Müşteri Hedefleri ");
    }
    page.getRow(index).getCell(0).setCellStyle(headLine);
    index+= 2;
       
    page.createRow(index).createCell(0).setCellValue("Nu.");
    page.getRow(index).getCell(0).setCellStyle(navy);
    page.getRow(index).createCell(1).setCellValue("Personel adı");
    page.getRow(index).getCell(1).setCellStyle(navy);
    page.getRow(index).createCell(2).setCellValue("Personel hedefi");
    page.getRow(index).getCell(2).setCellStyle(navy);
    if(personalTypeOption){
        page.getRow(index).createCell(4).setCellValue("Statüsü");
        page.getRow(index).getCell(4).setCellStyle(navy);
    }
    if(proportionOption){
        page.getRow(index).createCell(5).setCellValue("Çarpanı");
        page.getRow(index).getCell(5).setCellStyle(navy);
    }
    
    DecimalFormat dc;
    if(getCalculateMode() == 0){
         dc = new DecimalFormat("###,000");
    }else{
         dc = new DecimalFormat("###");
    }
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
    
    index+= 2;

	// After this part writing progress is begining

        if( Calculate.managerNumber !=0 ){
        // loop is going to turn for every managers and going to create new line
         for(int d =0; d < Calculate.managerNumber; d++){
            page.createRow(index).createCell(0).setCellValue( personalNumber );
            page.getRow(index).getCell(0).setCellStyle(boldCenter);
            page.getRow(index).createCell(1).setCellValue( Calculate.managerName.get(d));
            page.getRow(index).getCell(1).setCellStyle(bold);
            page.getRow(index). createCell(2).setCellValue( Calculate.managerTarget.get(d) );
            page.getRow(index).getCell(2).setCellStyle(bold);
            if(personalTypeOption){
                page.getRow(index).createCell(4).setCellValue("Satış yöneticisi");
                page.getRow(index).getCell(4).setCellStyle(bold);
            }
            if(proportionOption){
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion);
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            index+= 2;
         }
        }
        
        
        Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
       if( Calculate.expertNumber !=0){ 
        // loop is going to turn for every expert and going to create new line    
       for(int d= 0; d < Calculate.expertNumber; d++){
            page.createRow(index).createCell(0).setCellValue( personalNumber );
            page.getRow(index).getCell(0).setCellStyle(boldCenter);
            page.getRow(index).createCell(1).setCellValue( Calculate.expertName.get(d) );
            page.getRow(index).getCell(1).setCellStyle(bold);
            page.getRow(index).createCell(2).setCellValue( Calculate.expertTarget.get(d) );
            page.getRow(index).getCell(2).setCellStyle(bold);
            if(personalTypeOption){
                page.getRow(index).createCell(4).setCellValue("Uzman satış danışmanı");
                page.getRow(index).getCell(4).setCellStyle(bold);
            }
            if(proportionOption){
                page.getRow(index).createCell(5).setCellValue(Main.expertProportion);
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            index += 2;
       }
       }
       
       Center.progressBar.setValue(Center.progressBar.getValue() + 40);
       
        if( Calculate.consultantNumber !=0){
        // loop is going to turn for every consultant and going to create new line
           for(int d =0; d < Calculate.consultantNumber; d++){
            page.createRow(index).createCell(0).setCellValue( personalNumber );
            page.getRow(index).getCell(0).setCellStyle(boldCenter);
            page.getRow(index).createCell(1).setCellValue( Calculate.consultantName.get(d) );
            page.getRow(index).getCell(1).setCellStyle(bold);
            page.getRow(index).createCell(2).setCellValue( Calculate.consultantTarget.get(d) );
            page.getRow(index).getCell(2).setCellStyle(bold);
            if(personalTypeOption){
                page.getRow(index).createCell(4).setCellValue("Satış danışmanı");
                page.getRow(index).getCell(4).setCellStyle(bold);
            }
            if(proportionOption){
                page.getRow(index).createCell(5).setCellValue(Main.consultantProportion);
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
           }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            index += 2;
           }
       }
        
        Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
       if( Calculate.otherNumber != 0){
        // loop is going to turn for every other personal and going to create new line
       for(int d=0; d < Calculate.otherNumber; d++){
            page.createRow(index).createCell(0).setCellValue( personalNumber );
            page.getRow(index).getCell(0).setCellStyle(boldCenter);
            page.getRow(index).createCell(1).setCellValue( Calculate.otherName.get(d) );
            page.getRow(index).getCell(1).setCellStyle(bold);
            page.getRow(index).createCell(2).setCellValue( Calculate.otherTarget.get(d) );
            page.getRow(index).getCell(2).setCellStyle(bold);
            if(personalTypeOption){
                page.getRow(index).createCell(4).setCellValue("Diğer");
                page.getRow(index).getCell(4).setCellStyle(bold);
            }
            if(proportionOption){
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d));
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Dağıtılan hedef : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;

    
    // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            redFont.setColor(IndexedColors.ORANGE.getIndex());
        }else if(completed.contains("hedefini geçeceği")){
            redFont.setColor(IndexedColors.RED.getIndex());        
        }else if(completed.contains("tamamlanamıyor")){
            redFont.setColor(IndexedColors.RED.getIndex());
        }else{
            redFont.setColor(IndexedColors.GREEN.getIndex());        
        }
            page.getRow(index).getCell(0).setCellStyle(red);
     }
   
       
  
    // this loop settings column size 
    for(int a= 0; a < index; a++){
          page.autoSizeColumn(a);
    }
    // creating file and writing
    FileOutputStream out;
    if(getCalculateMode() == 0){
       out = new FileOutputStream( folder.getAbsolutePath() + "/" + getShopName(shopNumber) + " Personel Hedefleri.xls" );
    }else{
       out = new FileOutputStream( folder.getAbsolutePath() + "/" + getShopName(shopNumber) +  " Tekil Müşteri Hedefleri.xls" );
    }
    wbook.write(out);
    out.close();   
    }
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
    } catch (IOException e) {
          System.err.println("Error in writing single shop" + e.getMessage());
    }
}// ends of single write class


    // this method getting shop name with shop number
    private String getShopName(int shopNumber){
	String str = "";
	try{
	   BufferedReader br = new BufferedReader(new FileReader(new File("Options/shop" + shopNumber + "/" + "shopName.ini")));
	   str = br.readLine();
	   br.close();
    	}catch(IOException e){
	   System.err.println("Error in getting current shop name shop number : " + shopNumber + "  " + e.getLocalizedMessage());
	}
	return str;
    }	



    /**
    * this method created for converting personal target to integer value for calculating total personal targets
    * @db the double value for convert to int
    *
    */
    private int convertToInt(String  str){
      	int i = 0;
      	try{
 	  str = str.replace(".", "");
	  str = str.replace(",", "");
	  i = Integer.parseInt(str);
	}catch(NumberFormatException ex){
	  System.err.println("Error in converToInt method in singleWrite class : " + ex.getLocalizedMessage());
	}
	return i;
    }



    private boolean getCompleteOption(){
	boolean b = false;
	try{
	   BufferedReader br = new BufferedReader(new FileReader(new File("Options/completeButtonOption.ini")));
	   b = Boolean.parseBoolean(br.readLine());
	   br.close();
	}catch(IOException e){
	   System.err.println("Error in getting complete options in single write classs  " + e.getLocalizedMessage());
	}
	return b;
    }


 
    // this method created for getting current calculating mode 
    private int getCalculateMode(){
        int i = 0;
        try {
            File file = new File("Options/calculateMode.ini");
            BufferedReader br = new BufferedReader(new FileReader(file));
            i = Integer.parseInt(br.readLine());
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error in getting calculate mode options in single write class : " + e.getLocalizedMessage());
        }
        return i;
    }



//    private String getPersonalPercent(Double proportion){
//        String percent = "";
//        try {
//            if(proportion == 0 || proportion == 0.0 || proportion == 0.00){
//                percent = "Yüzde belirtilmemiş!";
//            }else if(proportion == 1 || proportion == 1.0 || proportion == 1.00){
//                percent = "%0";
//            }else{
//                // transfering double data to string for check
//                String[] check = Double.toString(proportion).replace(".", ".#.").split(".#.");
//                if(check.length >=  1){
//                    if(check[0].equals("0")){ //if proportion is begining with 0. it will be negative percent 
//                            if(check[1].length() == 1){ // second number is just 1 digits like 1.2 it will be -%20
//                            percent = "-%" + check[1] + "0";
//                        }else{ // if second number is 2 digits like 1.25 it will be -%25
//                            percent = "-%" + check[1];
//                        } 
//                    }else if(check[0].equals("1")){ // if proportion is begining with 1. it will be positive percent
//                         if(check[1].length() == 1){ // second number is just 1 digits like 1.2 it will be %20
//                            percent = "%" + check[1] + "0";
//                        }else{ // if second number is 2 digits like 1.25 it will be %25
//                            percent = "%" + check[1];
//                        }
//                    }
//                }else{
//                    if(check[0].length() == 1){
//                        percent =  "+%" + check[0] + "00" ;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Error in getting personal percent  in single Write Class : " + e.getLocalizedMessage());
//        }
//        return percent;
//    }
    
    private boolean getPersonalTypeOption(){
        boolean b = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Options/personalTypeOption.ini")));
            b = Boolean.parseBoolean(br.readLine());
            br.close();
        } catch (IOException e) {
            System.err.println("Error in getting personel type option in singleWrite class : " + e.getLocalizedMessage());
        }
        return b;
    }
    private boolean getProportionOption(){
        boolean b = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Options/proportionOption.ini")));
            b = Boolean.parseBoolean(br.readLine());
            br.close();
        } catch (IOException e) {
            System.err.println("Error in getting proportion option in singleWrite class : " + e.getLocalizedMessage());
        }
        return b;
    }
 
}



    

