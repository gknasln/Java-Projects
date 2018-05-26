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
 *
 * @author gknas
 */
public class MultiWrite {
   private int totalTarget, personalNumber, index, grandTotal, totalShopTarget, calculatedShop, totalPersonals; 
   private String completed;
   private boolean personalTypeOption, proportionOption;
   DecimalFormat dc;
public MultiWrite(boolean[] shopCondition, int[] shopTarget) {
  try {
    index = 0;
    grandTotal = 0;
    totalShopTarget = 0;
    calculatedShop = 0;
    totalPersonals = 0;
    personalTypeOption = getPersonalTypeOption();
    proportionOption = getProportionOption();
    File folder;
    if(getCalculateMode() == 0 ){
        folder = new File( Main.path + "/Butik Mağaza Hedefleri");
    }else{
        folder = new File( Main.path + "/Tekil Müşteri Hedefleri");
    }
        folder.mkdir();
        
    if(getCalculateMode() == 0){
         dc = new DecimalFormat("###,000");
    }else{
         dc = new DecimalFormat("###");
    }
            
    // creating methods and sheets 
    Workbook wbook = new HSSFWorkbook();
    Sheet page = wbook.createSheet("Hedefler");
    
    // creating style for head line
    CellStyle headLine = wbook.createCellStyle();
    Font font = wbook.createFont();
    font.setFontHeightInPoints((short)16);
    font.setColor(IndexedColors.DARK_BLUE.getIndex());
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    
    headLine.setFont(font);
    headLine.setAlignment(CellStyle.ALIGN_CENTER);
    headLine.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
   

    CellStyle yellow = wbook.createCellStyle();
    yellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    yellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
    
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

    CellStyle orange = wbook.createCellStyle();
    orange.setWrapText(true);
    orange.setAlignment(HorizontalAlignment.LEFT);
    orange.setVerticalAlignment(VerticalAlignment.CENTER);    
    Font orangeFont = wbook.createFont();
    orangeFont.setColor(IndexedColors.ORANGE.getIndex());
    orangeFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    orange.setFont(orangeFont);

    CellStyle green = wbook.createCellStyle();
    green.setWrapText(true);
    green.setAlignment(HorizontalAlignment.LEFT);
    green.setVerticalAlignment(VerticalAlignment.CENTER);    
    Font greenFont = wbook.createFont();
    greenFont.setColor(IndexedColors.GREEN.getIndex());
    greenFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    green.setFont(greenFont);
        
    
//   -------------------------------------------Writing Shop 1 ----------------------------------------------------     
if(shopCondition[0]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(1));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(1, shopTarget[0]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[0]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(1) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(1) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[0]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


    // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

   Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[0];
   totalTarget = 0;
   calculatedShop ++;
    }
}



//   -------------------------------------------Writing Shop 2----------------------------------------------------     
if(shopCondition[1]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(2));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(2, shopTarget[1]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("İkinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[1]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(2) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(2) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[1]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[1];
   totalTarget = 0;
   calculatedShop ++;
   }
}



//   -------------------------------------------Writing Shop 3  ----------------------------------------------------     
if(shopCondition[2]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(3));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(3, shopTarget[2]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[2]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(3) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(3) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[2]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   }
   
   
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[2];
   totalTarget = 0;
   calculatedShop ++;
}


//   -------------------------------------------Writing Shop 4 ----------------------------------------------------     
if(shopCondition[3]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(4));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(4, shopTarget[3]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[3]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(4) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(4) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[3]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;



    // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[3];
   totalTarget = 0;
   calculatedShop ++;
   }
}


//   -------------------------------------------Writing Shop 5 ----------------------------------------------------     
if(shopCondition[4]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(5));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(5, shopTarget[4]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[4]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(5) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(5) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[4]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


    // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }


    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
    
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[4];
   totalTarget = 0;
   calculatedShop ++;
   }
}



//   -------------------------------------------Writing Shop  6----------------------------------------------------     
if(shopCondition[5]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(6));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(6, shopTarget[5]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[5]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(6) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(6) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[5]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;

    
       // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }
    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[5];
   totalTarget = 0;
   calculatedShop ++;
   }
}




//   -------------------------------------------Writing Shop 7 ----------------------------------------------------     
if(shopCondition[6]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(7));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(7, shopTarget[6]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[6]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(7) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(7) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[6]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


    // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }
    
    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[6];
   totalTarget = 0;
   calculatedShop ++;
   }
}



//   -------------------------------------------Writing Shop 8 ----------------------------------------------------     
if(shopCondition[7]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(8));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(8, shopTarget[7]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[7]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(8) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(8) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[7]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


       // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }
    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[7];
   totalTarget = 0;
   calculatedShop ++;
   }
}




//   -------------------------------------------Writing Shop  9----------------------------------------------------     
if(shopCondition[8]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(9));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(9, shopTarget[8]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[8]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(9) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(9) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[8]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


       // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }
    
    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[8];
   totalTarget = 0;
   calculatedShop ++;
   }
}




//   -------------------------------------------Writing Shop  10 ----------------------------------------------------     
if(shopCondition[9]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(10));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(10, shopTarget[9]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[9]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(10) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(10) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[9]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


     // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[9];
   totalTarget = 0;
   calculatedShop ++;
   }
}


//   -------------------------------------------Writing Shop  11 ----------------------------------------------------     
if(shopCondition[10]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(11));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(11, shopTarget[10]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[10]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(11) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(11) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[10]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;

    
  
        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[10];
   totalTarget = 0;
   calculatedShop ++;
   }
}




//   -------------------------------------------Writing Shop 12 ----------------------------------------------------     
if(shopCondition[11]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(12));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(12, shopTarget[11]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[11]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(12) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(12) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[11]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


    
        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[11];
   totalTarget = 0;
   calculatedShop ++;
   }
}



//   -------------------------------------------Writing Shop  13----------------------------------------------------     
if(shopCondition[12]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(13));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(13, shopTarget[12]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[12]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(13) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(13) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[12]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[12];
   totalTarget = 0;
   calculatedShop ++;
   }
}



//   -------------------------------------------Writing Shop  14----------------------------------------------------     
if(shopCondition[13]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(14));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(14, shopTarget[13]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[13]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(14) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(14) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[13]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;

        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

   
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[13];
   totalTarget = 0;
   calculatedShop ++;
   }
}



//   -------------------------------------------Writing Shop  15----------------------------------------------------     
if(shopCondition[14]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(15));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(15, shopTarget[14]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[14]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(15) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(15) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[14]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[14];
   totalTarget = 0;
   calculatedShop ++;
   }
}



//   -------------------------------------------Writing Shop 16 ----------------------------------------------------     
if(shopCondition[15]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(16));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(16, shopTarget[15]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[15]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(16) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(16) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[15]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;

    
        // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }

    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index += 3;
   page.createRow(index).createCell(0).setCellStyle(yellow);
   page.addMergedRegion(new CellRangeAddress(index, index+2, 0, 25));
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[15];
   totalTarget = 0;
   calculatedShop ++;
   }
}




//   -------------------------------------------Writing Shop 17 ----------------------------------------------------     
if(shopCondition[16]){
    // setting progressbar with shopname while shops writing 
    Center.progressBar.setString(getShopName(17));
    // calling calculate method  for shop 1 and giving target by arrays first index
    new Calculate().Calculate(17, shopTarget[16]);
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
        
//    after calculate method called personal numbers will be reset  if there is no personal this message will show up, else actions  gonna work 
   if(Calculate.managerNumber == 0 && Calculate.expertNumber == 0 && Calculate.consultantNumber == 0 && Calculate.otherNumber == 0){
       System.out.println("Birinci mağazanın personeli yok ");
   }else{
       // writing actions starting 
    personalNumber = 1;
    if(getCompleteOption() && getCalculateMode() == 1){
        completed = new Complete().completed(shopTarget[16]);
    }
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 7));
    if(getCalculateMode() == 0){
        page.createRow(index).createCell(0).setCellValue( getShopName(17) + " Personel Hedefleri");
    }else{  
        page.createRow(index).createCell(0).setCellValue( getShopName(17) + " Tekil Müşteri Hedefleri ");
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
                page.getRow(index).createCell(5).setCellValue( Main.managerProportion );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
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
            totalPersonals++;
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
            totalPersonals++;
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
                page.getRow(index).createCell(5).setCellValue( Calculate.otherProportions.get(d) );
                page.getRow(index).getCell(5).setCellStyle(boldCenter);
            }
	    totalTarget += convertToInt(page.getRow(index).getCell(2).getStringCellValue());
            personalNumber++;
            totalPersonals++;
            index+= 2;
       }
       }
    
    index += 2;
    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Mağaza Hedefi : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue(dc.format(shopTarget[16]));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 2;

    
    page.addMergedRegion(new CellRangeAddress(index, index, 0, 1));
    page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan : ");
    page.getRow(index).getCell(0).setCellStyle(bold);
    page.getRow(index).createCell(2).setCellValue( dc.format(totalTarget));
    page.getRow(index).getCell(2).setCellStyle(bold);
    index += 3;


    // if program is on costumer number calculating mode and complete mode is on this information will show up
    if(getCalculateMode() == 1 && getCompleteOption()){
        page.addMergedRegion(new CellRangeAddress(index, index+1, 0, 6));
        page.createRow(index).createCell(0).setCellValue( completed );
        
        if(completed.contains("Hedef tam")){
            page.getRow(index).getCell(0).setCellStyle(orange);
        }else if(completed.contains("hedefini geçeceği")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else if(completed.contains("tamamlanamıyor")){
            page.getRow(index).getCell(0).setCellStyle(red);
        }else{        
            page.getRow(index).getCell(0).setCellStyle(green);
        }
     }
    
    Center.progressBar.setValue(Center.progressBar.getValue() + 40);
   index+=3;
   grandTotal += totalTarget;
   totalShopTarget += shopTarget[16];
   calculatedShop ++;
   }
}

   page.addMergedRegion(new CellRangeAddress(index, index, 0, 2));
   page.createRow(index).createCell(0).setCellValue("Hesaplanan mağaza sayısı : ");
   page.getRow(index).getCell(0).setCellStyle(bold);
   page.getRow(index).createCell(3).setCellValue(calculatedShop);
   page.getRow(index).getCell(3).setCellStyle(bold);
   
   index += 2;
   
   page.addMergedRegion(new CellRangeAddress(index, index, 0, 2));
   page.createRow(index).createCell(0).setCellValue("Mağazalara verilen toplam hedef  : ");
   page.getRow(index).getCell(0).setCellStyle(bold);
   page.getRow(index).createCell(3).setCellValue(dc.format(totalShopTarget));
   page.getRow(index).getCell(3).setCellStyle(bold);
   
   index += 2;
   
   page.addMergedRegion(new CellRangeAddress(index, index, 0, 2));
   page.createRow(index).createCell(0).setCellValue("Personellere dağıtılan toplam hedef  : ");
   page.getRow(index).getCell(0).setCellStyle(bold);
   page.getRow(index).createCell(3).setCellValue(dc.format(grandTotal));
   page.getRow(index).getCell(3).setCellStyle(bold);
   
   index += 2;
   
   page.addMergedRegion(new CellRangeAddress(index, index, 0, 2));
   page.createRow(index).createCell(0).setCellValue("Toplam personel sayısı : ");
   page.getRow(index).getCell(0).setCellStyle(bold);
   page.getRow(index).createCell(3).setCellValue(totalPersonals);
   page.getRow(index).getCell(3).setCellStyle(bold);
   
   
   
   
    // this loop settings column size 
    for(int a= 0; a < index; a++){
          page.autoSizeColumn(a);
    }
    
    
    // creating file and writing all documents 
    FileOutputStream out;
    if(getCalculateMode() == 0){
       out = new FileOutputStream( folder.getAbsolutePath() + "/" + " Butik Mağazalar Personel Hedefleri.xls" );
    }else{
       out = new FileOutputStream( folder.getAbsolutePath() + "/" + "Butik Mağazalar Tekil Müşteri Hedefleri.xls" );
    }
    wbook.write(out);
    out.close();
    
    
    } catch (IOException e) {
          System.err.println("Error in writing multiple shop" + e.getLocalizedMessage());
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

//
//
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
            System.err.println("Error in getting personel type option in multiwrite class : " + e.getLocalizedMessage());
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
            System.err.println("Error in getting proportion option in multiwrite class : " + e.getLocalizedMessage());
        }
        return b;
    }
}


