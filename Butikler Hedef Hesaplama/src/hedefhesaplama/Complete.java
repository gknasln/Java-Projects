package hedefhesaplama;

import java.util.ArrayList;
public class Complete{

  private String completed;

  private  int managers, experts, consultants, others, totalTarget;

  private ArrayList<Integer> managerTarget = new ArrayList<>();
  private ArrayList<Integer> expertTarget = new ArrayList<>();
  private ArrayList<Integer> consultantTarget = new ArrayList<>();
  private ArrayList<Integer> otherTarget = new ArrayList<>();

  private ArrayList<String> managerName = new ArrayList<>();
  private ArrayList<String> expertName = new ArrayList<>();
  private ArrayList<String> consultantName = new ArrayList<>(); 
  private ArrayList<String> otherName = new ArrayList<>();


 public String completed(int shopTarget){
     try {
   managers = Calculate.managerNumber;
   experts = Calculate.expertNumber;
   consultants = Calculate.consultantNumber;
   others = Calculate.otherNumber;
	
     for (int i = 0; i < Calculate.managerTarget.size(); i++) {
       managerTarget.add(Integer.parseInt(Calculate.managerTarget.get(i)));
     }
   
     for (int i = 0; i < Calculate.expertTarget.size(); i++) {
       expertTarget.add(Integer.parseInt(Calculate.expertTarget.get(i)));
     }
   
     for (int i = 0; i < Calculate.consultantTarget.size(); i++) {
       consultantTarget.add(Integer.parseInt(Calculate.consultantTarget.get(i)));
     }
   
     for (int i = 0; i < Calculate.otherTarget.size(); i++) {
       otherTarget.add(Integer.parseInt(Calculate.otherTarget.get(i)));
     }

   managerName = Calculate.managerName;
   expertName = Calculate.expertName;
   consultantName = Calculate.consultantName;
   otherName = Calculate.otherName;

   totalTarget = 0;

   for(int i = 0; i<managers; i++){
      totalTarget += managerTarget.get(i);
   }

   for(int i = 0; i < experts; i++){
      totalTarget += expertTarget.get(i);
   }

   for(int i = 0; i < consultants; i++){
      totalTarget += consultantTarget.get(i);
   }

   for(int i = 0; i < others; i++){
      totalTarget += otherTarget.get(i);
   }

   switch(shopTarget - totalTarget){
    
   case 0: 
      completed = "Hedef tam dağıtılmış.";
      break;
   // if remaining target is 1 this case block will work  
   case 1: 
      if( managers == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
        completed = "Kalan 1 hedef, " + managerName.get(0) + " personelinin hedefine eklendi.";
      }
      else if( experts == 1 ){
 	expertTarget.set(0, expertTarget.get(0)+1);
	completed = "Kalan 1 hedef, " + expertName.get(0) + " personelinin hedefine eklendi.";
      }
      else if( consultants == 1){
        consultantTarget.set(0, consultantTarget.get(0)+1);
        completed = "Kalan 1 hedef, " + consultantName.get(0) + " personelinin hedefine eklendi.";
      }
      else if( others == 1){
	otherTarget.set(0, otherTarget.get(0)+1);
 	completed = "Kalan 1 hedef, " + otherName.get(0) + " personelinin hedefine eklendi.";
      }
      else{
	completed = "Mağaza hedefini geçeceği için hedef tamamlanamadı.";
      }
   break;

    // if remaining target is 2 case block will work 
   case 2:
      if( managers == 2 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	managerTarget.set(1, managerTarget.get(1)+1);
	completed = "Kalan 2 hedef, " + managerName.get(0) + " ve " + managerName.get(1) + " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && experts == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	expertTarget.set(0, expertTarget.get(0)+1);
	completed = "Kalan 2 hedef, " + managerName.get(0) + " ve " + expertName.get(0) + " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && consultants == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
    	consultantTarget.set(0, consultantTarget.get(0)+1);
	completed = "Kalan 2 hedef, " + managerName.get(0) + " ve " + consultantName.get(0) + " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && others == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 2 hedef, " + managerName.get(0) + " ve " + otherName.get(0) + " personellerinin hedeflerine eklendi";
      }
      else if( experts == 2 ){
	expertTarget.set(0, expertTarget.get(0)+1);
 	expertTarget.set(1, expertTarget.get(1)+1);
	completed = "Kalan 2 hedef, " + expertName.get(0) + " ve " + expertName.get(1) + " personellerinin hedeflerine eklendi";
      }
      else if( experts == 1 && consultants == 1 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	completed = "Kalan 2 hedef, " + expertName.get(0) + " ve " + consultantName.get(0) + " personellerinin hedeflerine eklendi";
      }
      else if( experts == 1 && consultants == 1 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1); 
	completed = "Kalan 2 hedef, " + expertName.get(0) + " ve " + consultantName.get(0) + " personellerinin hedeflerine eklendi";
      }
      else if( experts == 1 && others == 1){
	expertTarget.set(0, expertTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 2 hedef, " + expertName.get(0) + " ve " + otherName.get(0) + " personellerinin hedeflerine eklendi";
      }
      else if( consultants == 2 ){
 	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	completed = "Kalan 2 hedef, " + consultantName.get(0) + " ve " + consultantName.get(1) + " personellerinin hedeflerine eklendi";
      }
      else if( consultants == 1 && others == 1 ){
	consultantTarget.set(0, consultantTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 2 hedef, " + consultantName.get(0) + " ve " + otherName.get(0) + " personellerinin hedeflerine eklendi";
      }
      else if( others == 2 ){
	otherTarget.set(0, otherTarget.get(0)+1);
	otherTarget.set(1, otherTarget.get(1)+1);
	completed = "Kalan 2 hedef, " + otherName.get(0) + " ve " + otherName.get(1) + " hedefleri +1 eklendi.";
      }else{
	completed = "Mağaza hedefini geçeceği için hedef tamamlanamadı.";
      }
   break;

   case 3: 
      if( managers == 3 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	managerTarget.set(1, managerTarget.get(1)+1);
	managerTarget.set(2, managerTarget.get(2)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + managerName.get(1) + " ve " + managerName.get(2) + " personellerinin hedeflerine eklendi";
      }
      else if( managers == 2 && experts == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	managerTarget.set(1, managerTarget.get(1)+1); 
  	expertTarget.set(0, expertTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + managerName.get(1) + " ve " + expertName.get(0) +
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 2 && consultants == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	managerTarget.set(1, managerTarget.get(1)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + managerName.get(1) + " ve " + consultantName.get(0) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 2 && others == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	managerTarget.set(1, managerTarget.get(1)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + managerName.get(1) + " ve " + otherName.get(0) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && experts == 2 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + expertName.get(0) + " ve " + expertName.get(1) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && experts == 1 && consultants == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + expertTarget.get(0) + " ve " + consultantTarget.get(0) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && experts == 1  && others == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	expertTarget.set(0, expertTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + expertName.get(0) + " ve " + otherName.get(0) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && consultants == 2 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + consultantName.get(0) + " ve " + consultantName.get(1) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && consultants == 1 && others == 1 ){
	managerTarget.set(0, managerTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + consultantName.get(0) + " ve " + otherName.get(0) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( managers == 1 && others == 2 ){
   	managerTarget.set(0, managerTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	otherTarget.set(1, otherTarget.get(1)+1);
	completed = "Kalan 3 hedef, " + managerName.get(0) + ", " + otherName.get(0) + " ve " + otherName.get(1) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( experts == 3 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	expertTarget.set(2, expertTarget.get(2)+1);
	completed = "Kalan 3 hedef, " + expertName.get(0) + ", " + expertName.get(1) + " ve " + expertName.get(2) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( experts == 2 && consultants == 1 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + expertName.get(0) + ", " + expertName.get(1) + " ve " + consultantName.get(0) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( experts == 1 && consultants == 2 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
        completed = "Kalan 3 hedef, " + expertName.get(0) + ", " + consultantName.get(0) + " ve " + consultantName.get(1) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( experts == 1 && consultants == 1 && others == 1 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 3 hedef, " + expertName.get(0) + ", " + consultantName.get(0) + " ve " + otherName.get(0) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( experts == 1 && others == 2 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	otherTarget.set(1, otherTarget.get(1)+1);
	completed = "Kalan 3 hedef, " + expertName.get(0) + ", " + otherName.get(0) + " ve " + otherName.get(1) + 
                " personellerinin hedeflerine eklendi";
      }
      else if( consultants == 3 ){
	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	consultantTarget.set(2, consultantTarget.get(2)+1);
        completed = "Kalan 3 hedef, " + consultantName.get(0) + ", " + consultantName.get(1) + " ve " + consultantName.get(2) + 
                " personellerinin hedeflerine eklendi";
       }
      else if( consultants == 2 && others == 1 ){
          consultantTarget.set(0, consultantTarget.get(0)+1);
          consultantTarget.set(1, consultantTarget.get(1)+1);
          otherTarget.set(0, otherTarget.get(0)+1);
          completed = "Kalan 3 hedef, " + consultantName.get(0) + ", " + consultantName.get(1) + " ve " + otherName.get(0) + 
                  " personellerinin hedeflerine eklendi";
      }
      else if( others == 3 ){
          otherTarget.set(0, otherTarget.get(0)+1);
          otherTarget.set(1, otherTarget.get(1)+1);
          otherTarget.set(2, otherTarget.get(2)+1);
          completed = "Kalan 3 hedef, " + otherName.get(0) + ", " + otherName.get(1) + " ve " + otherName.get(2) + 
                  " personellerinin hedeflerine eklendi";
      }else{
	completed = "Mağaza hedefini geçeceği için hedef tamamlanamadı.";
      }
    break;

   case 4: 
       
    if( managers == 4 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        managerTarget.set(2, managerTarget.get(2)+1);
        managerTarget.set(3, managerTarget.get(3)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + managerName.get(2) + " ve " + managerName.get(3) + 
                " personellerinin hedeflerine eklendi";
    }
    else if( managers == 3 && experts == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        managerTarget.set(2, managerTarget.get(2)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + managerName.get(2) + " ve " + expertName.get(0) + 
                " personellerinin hedeflerine eklendi";
    }
    else if( managers == 3 && consultants == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        managerTarget.set(2, managerTarget.get(2)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + managerName.get(2) + " ve " + consultantName.get(0) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 3 && others == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        managerTarget.set(2, managerTarget.get(2)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + managerName.get(2) + " ve " + otherName.get(0) + 
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 2 && experts == 2 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        expertTarget.set(1, expertTarget.get(1)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + expertName.get(0) + " ve " + expertName.get(1) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 2 && experts == 1 && consultants == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + expertName.get(0) + " ve " +  consultantName.get(0) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 2 && experts == 1 && others == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + expertName.get(0) + " ve " + otherName.get(0) + 
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 2 && consultants == 2 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        consultantTarget.set(1, consultantTarget.get(1)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + consultantName.get(0) + " ve " + consultantName.get(1) + 
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 2 && consultants == 1 && others == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        completed = "Kalan 4 hedef, " +  managerName.get(0) + ", " + managerName.get(1) + ", " + consultantName.get(0) + " ve " + otherName.get(0) +
                " personellerinin hedeflerine eklendi.";
    } 
    else if( managers == 2 && others == 2 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        managerTarget.set(1, managerTarget.get(1)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        otherTarget.set(1, otherTarget.get(1)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + managerName.get(1) + ", " + otherName.get(0) + " ve " + otherName.get(1) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && experts == 3 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        expertTarget.set(1, expertTarget.get(1)+1);
        expertTarget.set(2, expertTarget.get(2)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + expertName.get(0) + ", " + expertName.get(1) + " ve " + expertName.get(2) + 
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && experts == 2 && consultants == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        expertTarget.set(1, expertTarget.get(1)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + expertName.get(0) + ", " + expertName.get(1) + " ve " + consultantName.get(0) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && experts == 2 && others == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        expertTarget.set(1, expertTarget.get(1)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + expertName.get(0) + ", " + expertName.get(1) + " ve " + otherName.get(0) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && experts == 1 && consultants == 1 && others == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        expertTarget.set(0, expertTarget.get(0)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + expertName.get(0) + ", " + consultantName.get(0) + " ve " + otherName.get(0) + 
               " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && consultants == 3 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        consultantTarget.set(1, consultantTarget.get(1)+1);
        consultantTarget.set(2, consultantTarget.get(2)+1);
        completed = managerName.get(0) + ", " + consultantName.get(0) + ", " + consultantName.get(1) + " ve " + consultantName.get(2) + 
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && consultants == 2 && others == 1 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        consultantTarget.set(1, consultantTarget.get(1)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + consultantName.get(0) + ", " + consultantName.get(1) + " ve " + otherName.get(0) + 
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && consultants == 1 && others == 2 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        consultantTarget.set(0, consultantTarget.get(0)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        otherTarget.set(1, otherTarget.get(1)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + consultantName.get(0) + ", " + otherName.get(0) + " ve " + otherName.get(1) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( managers == 1 && others == 3 ){
        managerTarget.set(0, managerTarget.get(0)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        otherTarget.set(1, otherTarget.get(1)+1);
        otherTarget.set(2, otherTarget.get(2)+1);
        completed = "Kalan 4 hedef, " + managerName.get(0) + ", " + otherName.get(0) + ", " + otherName.get(1) + " ve " + otherName.get(2) + 
                " personellerinin hedeflerine eklendi.";
    }
    else if( experts == 4 ){
        expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	expertTarget.set(2, expertTarget.get(2)+1);
	expertTarget.set(3, expertTarget.get(3)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + expertName.get(1) + ", " + expertName.get(2) + " ve " + expertName.get(3) +
		" personellerinin hedeflerine eklendi.";
    }
    else if( experts == 3 && consultants == 1 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	expertTarget.set(2, expertTarget.get(2)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + expertName.get(1) + ", " + expertName.get(2) + " ve " + consultantName.get(0) + 
		" personellerinin hedeflerine eklendi.";
    }
    else if( experts == 3 &&  others == 1 ){
 	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	expertTarget.set(2, expertTarget.get(2)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + expertName.get(1) + ", " + expertName.get(2) + " ve " + otherName.get(0) + 
		" personellerinin hedeflerine eklendi.";	

    }
    else if( experts == 2 && consultants == 2 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + expertName.get(1) + ", " + consultantName.get(0) + " ve " + consultantName.get(1) +
		" personellerinin hedeflerine eklendi.";
    }
    else if( experts == 2 && consultants == 1 && others == 1 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + expertName.get(1) + ", " + consultantName.get(0) + " ve " + otherName.get(0) + 
		" personellerinin hedeflerine eklendi.";
    }
    else if( experts == 2 && others == 2 ){
	expertTarget.set(0, expertTarget.get(0)+1);
	expertTarget.set(1, expertTarget.get(1)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	otherTarget.set(1, otherTarget.get(1)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + expertName.get(1) + ", " + otherName.get(0) + " ve " + otherName.get(1) + 
		" personellerinin hedeflerine eklendi.";

    }
    else if( experts == 1 && consultants == 3 ){
   	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	consultantTarget.set(2, consultantTarget.get(2)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + consultantName.get(0) + ", " + consultantName.get(1) + " ve " + consultantName.get(2) +
		" personellerinin hedeflerine eklendi.";
    }
    else if( experts == 1 && consultants == 2 && others == 1 ){
  	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + consultantName.get(0) + ", " + consultantName.get(1) + " ve " + otherName.get(0) + 
		" personellerinin hedeflerine eklendi.";
    }
    else if( experts == 1 && consultants == 1 && others == 2 ){
  	expertTarget.set(0, expertTarget.get(0)+1);
	consultantTarget.set(0, consultantTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	otherTarget.set(1, otherTarget.get(1)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + consultantName.get(0) + ", " + otherName.get(0) + " ve " + otherName.get(1) + 
		" personellerinin hedeflerine eklendi.";
    }
    else if( experts == 1 && others == 3 ){
  	expertTarget.set(0, expertTarget.get(0)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	otherTarget.set(1, otherTarget.get(1)+1);
	otherTarget.set(2, otherTarget.get(2)+1);
	completed = "Kalan 4 hedef, " + expertName.get(0) + ", " + otherName.get(0) + ", " + otherName.get(1) + " ve " + otherName.get(2) + 
		" personellerinin hedeflerine eklendi.";
    }
    else if( consultants == 4 ){
   	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	consultantTarget.set(2, consultantTarget.get(2)+1);
	consultantTarget.set(3, consultantTarget.get(3)+1);
	completed = "Kalan 4 hedef, " + consultantName.get(0) + ", " + consultantName.get(1) + ", " + consultantName.get(2) + " ve " + consultantName.get(3) + 
	 	" personellerinin hedeflerine eklendi.";
    }
    else if( consultants == 3 && others == 1 ){
    	consultantTarget.set(0, consultantTarget.get(0)+1);
	consultantTarget.set(1, consultantTarget.get(1)+1);
	consultantTarget.set(2, consultantTarget.get(2)+1);
	otherTarget.set(0, otherTarget.get(0)+1);
	completed = "Kalan 4 hedef, " + consultantName.get(0) + ", " + consultantName.get(1) + ", " + consultantName.get(2) + " ve " + otherName.get(0) + 
		" personellerinin hedeflerine eklendi.";
    }
    else if( consultants == 2 && others == 2 ){
       consultantTarget.set(0, consultantTarget.get(0)+1);
       consultantTarget.set(1, consultantTarget.get(1)+1);
       otherTarget.set(0, otherTarget.get(0)+1);
       otherTarget.set(1, otherTarget.get(1)+1);
       completed = "Kalan 4 hedef, " + consultantName.get(0) + ", " + consultantName.get(1) + otherName.get(0) + " ve " + otherName.get(1) +
               " personellerinin hedeflerine eklendi.";
    }
    else if( consultants == 1 && others == 3 ){
        consultantTarget.set(0, consultantTarget.get(0)+1);
        otherTarget.set(0, otherTarget.get(0)+1);
        otherTarget.set(1, otherTarget.get(1)+1);
        otherTarget.set(2, otherTarget.get(2)+1);
        completed = "Kalan 4 hedef, " + consultantName.get(0) + ", " + otherName.get(0) + ", " + otherName.get(1) + " ve " + otherName.get(2) +
                " personellerinin hedeflerine eklendi.";
    }
    else if( others == 4 ){
	otherTarget.set(0, otherTarget.get(0));
	otherTarget.set(1, otherTarget.get(1));
	otherTarget.set(2, otherTarget.get(2));
	otherTarget.set(3, otherTarget.get(3));
        completed = "Kalan 4 hedef, " + otherName.get(0) + ", " + otherName.get(1) + ", " + otherName.get(2) + " ve " + otherName.get(3) +  
                " personellerinin hedeflerine eklendi.";
    }else{
	completed = "Mağaza hedefini geçeceği için hedef tamamlanamadı.";
    }
   break;
  
//   case 5:
//   break;
   default :
       completed = "Hedef tamamlanamıyor";
       break;
       
   }
   Calculate.managerTarget.clear();
   Calculate.expertTarget.clear();
   Calculate.consultantTarget.clear();
   Calculate.otherTarget.clear();
       for (int i = 0; i < managerTarget.size(); i++) {
           Calculate.managerTarget.add(Integer.toString(managerTarget.get(i)));
       }
       
       for (int i = 0; i < expertTarget.size(); i++) {
           Calculate.expertTarget.add(Integer.toString(expertTarget.get(i)));
       }
       for (int i = 0; i < consultantTarget.size(); i++) {
           Calculate.consultantTarget.add(Integer.toString(consultantTarget.get(i)));
       }
       for (int i = 0; i < otherTarget.size(); i++) {
           Calculate.otherTarget.add(Integer.toString(otherTarget.get(i)));
       }
       
       
     } catch (NumberFormatException e) {
         System.err.println("Error in completing costumer number target : " + e.getLocalizedMessage() );
     }
     return completed;
 }
}

