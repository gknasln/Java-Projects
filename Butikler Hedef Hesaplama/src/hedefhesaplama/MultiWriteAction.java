/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hedefhesaplama;

/**
 *
 * @author gknas
 */
public class MultiWriteAction implements Runnable {
    private boolean[] condition;
    private int[] shopTarget;

    public MultiWriteAction(boolean[] condition, int[] shopTarget) {
        this.condition = condition;
        this.shopTarget = shopTarget;
    }
    
    
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
         new MultiWrite(condition, shopTarget);
         Center.progressBar.setString("Tamamlandı");
         Thread.sleep(700);
        Center.progressBar.setVisible(false);
        } catch (InterruptedException ex) {
            System.err.println("Errror in sleeping" + ex.getLocalizedMessage());
        }
}
    
}
