package hedefhesaplama;


public class SingleWriteAciton implements Runnable{
    private boolean[] condition;
    private int[] shopTarget;

    public SingleWriteAciton(boolean[] condition, int[] shopTarget) {
        this.condition = condition;
        this.shopTarget = shopTarget;
    }
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    @Override
   public void run() {
      try {
       Center.progressBar.setValue(0);
       Center.progressBar.setMaximum(0);
     int progressBarValue = 0;
        for (int i = 0; i < 17; i++) {
            if(condition[i]){
                progressBarValue += 240;
            }
        }
     Center.progressBar.setMaximum(progressBarValue);
        
     // actions for shop1
     if(condition[0]){
     	new SingleWrite(1, shopTarget[0]);
     }
     // actions for shop2
     if(condition[1]){
     	new SingleWrite(2, shopTarget[1]);
     }
    // actions for shop3
     if(condition[2]){
     	new SingleWrite(3, shopTarget[2]);
     }
     // actions for shop1
     if(condition[3]){
     	new SingleWrite(4, shopTarget[3]);
     }
     // actions for shop1
     if(condition[4]){
     	new SingleWrite(5, shopTarget[4]);
     }
     // actions for shop1
     if(condition[5]){
     	new SingleWrite(6, shopTarget[5]);
     }
     // actions for shop1
     if(condition[6]){
     	new SingleWrite(7, shopTarget[6]);
     }
     // actions for shop1
     if(condition[7]){
     	new SingleWrite(8, shopTarget[7]);
     }
     // actions for shop1
     if(condition[8]){
     	new SingleWrite(9, shopTarget[8]);
     }
     // actions for shop1
     if(condition[9]){
     	new SingleWrite(10, shopTarget[9]);
     }
     // actions for shop1
     if(condition[10]){
     	new SingleWrite(11, shopTarget[10]);
     }
     // actions for shop1
     if(condition[11]){
     	new SingleWrite(12, shopTarget[11]);
     }
     // actions for shop1
     if(condition[12]){
     	new SingleWrite(13, shopTarget[12]);
     }
     // actions for shop1
     if(condition[13]){
     	new SingleWrite(14, shopTarget[13]);
     }
     // actions for shop1
     if(condition[14]){
     	new SingleWrite(15, shopTarget[14]);
     }
     // actions for shop1
     if(condition[15]){
     	new SingleWrite(16, shopTarget[15]);
     }
     // actions for shop1
     if(condition[16]){
     	new SingleWrite(17, shopTarget[16]);
     }
        Center.progressBar.setString("Tamamlandı");
        Thread.sleep(700);
        Center.progressBar.setVisible(false);
        } catch (InterruptedException ex) {
            System.err.println("Errror in sleeping" + ex.getLocalizedMessage());
        }
 }
}



    

