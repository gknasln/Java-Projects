/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hedefhesaplama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class  Calculate{
    
  static int managerNumber, expertNumber, consultantNumber, otherNumber;
  static ArrayList<String> managerName = new ArrayList<>();
  static ArrayList<String> managerTarget = new ArrayList<>();
  static ArrayList<String> expertName = new ArrayList<>();
  static ArrayList<String> expertTarget = new ArrayList<>();
  static ArrayList<String> consultantName = new ArrayList<>();
  static ArrayList<String> consultantTarget = new ArrayList<>();
  static ArrayList<String> otherName = new ArrayList<>();
  static ArrayList<String> otherTarget = new ArrayList<>();
  static ArrayList<Double> otherProportions = new ArrayList<>();
  
  private double totalProportion;
  int average;

  public int Calculate(int shopNumber, int shopTarget){
   try{
    File file = new File("Personals/shop" + shopNumber);
    File[] files = file.listFiles();
    
    
    managerName.clear();
    managerTarget.clear();
    expertName.clear();
    expertTarget.clear();
    consultantName.clear();
    consultantTarget.clear();
    otherName.clear();
    otherTarget.clear();
    otherProportions.clear();
    managerNumber = 0; expertNumber = 0; consultantNumber = 0; otherNumber = 0;
    
    // with this for each loop, getting personal numbers
    for(File xfile : files){
  	switch(getPersonalType(xfile)){
	   case 0 : // if this personal is a manager
		totalProportion += Main.managerProportion;
                managerName.add(xfile.getName().replace(".ini", ""));
                managerNumber++;
		break;
 	   case 1 : // if this personal is a expert
		totalProportion += Main.expertProportion; 
                expertName.add(xfile.getName().replace(".ini", ""));
                expertNumber++;
		break;
	   case 2 : // if this personal is consultant
		totalProportion += Main.consultantProportion;
                consultantName.add(xfile.getName().replace(".ini", ""));
                consultantNumber++;
		break;
	   case 3 : 
		totalProportion += getPersonalProportion(xfile);
                otherName.add(xfile.getName().replace(".ini", ""));
                otherProportions.add(getPersonalProportion(xfile));
                otherNumber++;
		break;
	   default : System.err.println("Error : Uknown personal type! \"" + file.getName().replace(".ini", "") + "\"");
	}
    }
    DecimalFormat dc;
    if(getCalculateMode() == 0){
       dc = new DecimalFormat("###,000");
    }else{
       dc = new DecimalFormat("###");
    }
     average = (int) (shopTarget/totalProportion);
     
       for (int i = 0; i < managerNumber; i++) {
           managerTarget.add(dc.format(average*Main.managerProportion));
        }

        for (int i = 0; i < expertNumber; i++) {
            expertTarget.add(dc.format(average*Main.expertProportion));
        }

       for (int i = 0; i < consultantNumber; i++) {
           consultantTarget.add(dc.format(average*Main.consultantProportion));
       }
       
       for (int i = 0; i < otherNumber; i++) {
           otherTarget.add(dc.format(average*Main.otherProportion ));
       }
       
       
   }catch(NumberFormatException exp){
	System.err.println("Error in calculating  shop" + shopNumber + " : " + exp.getLocalizedMessage());
   }
      return average;
  }
  



  private int getPersonalType(File file){
    int i = 0;
    try{
	BufferedReader br = new BufferedReader(new FileReader(file));
	i = Integer.parseInt(br.readLine());
	br.close();
    }catch(IOException ex){
	System.err.println("Error in getting personel type in calculate class : " + ex.getLocalizedMessage());
    }
    return i;
}

  private double getPersonalProportion(File file){
    double proportion = Main.otherProportion;
    try{
	BufferedReader br = new BufferedReader(new FileReader(file));
	br.readLine();
	proportion = Double.parseDouble(br.readLine());
       	br.close();
    }catch(IOException ex){
	System.err.println("Error in getting personal proportion in calculate class : " + ex.getLocalizedMessage());
    }
	return proportion;
   }
  
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



}