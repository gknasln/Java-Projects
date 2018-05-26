/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hedefhesaplama;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author gknas
 */
public class Main extends JFrame{
    
    private JPanel  center;
    
    JRadioButton singleWrite, multiWrite;
    
    private int width, height;
    
    static double managerProportion, expertProportion, consultantProportion, otherProportion;
    static String path;
    public static void main(String[] args) {
        
        // setting look and feel 
        try {
           UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.err.println("Error in setting nimbus look and feel : " + e.getLocalizedMessage());
        }
//        System.out.println(carp);
        Main program = new Main();
        } 
    
    
    
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Main(){
        try {
            
            width =  Toolkit.getDefaultToolkit().getScreenSize().width;
            height = Toolkit.getDefaultToolkit().getScreenSize().height;
            
            path = getSavePath();
            managerProportion = getProportion("manager");
            expertProportion = getProportion("expert");
            consultantProportion = getProportion("consultant");
            otherProportion = getProportion("other");
            
            System.out.println(path);
            
            width = (int) (width*0.4);
            height = (int) (height*0.94);
            setSize(width, height);
            setLocationRelativeTo(null);
            setIconImage(new ImageIcon("icons/simge.png").getImage());
        
            Container con =  getContentPane();
            con.setLayout(null);
            
            MenuBar menuBar = new MenuBar();
            setJMenuBar(menuBar);
            
            
            center = new Center((int) (width*0.95), (int) (height*0.875));
            center.setLocation((int) (width*0.012), (int) (height*0.04));
            con.add(center); 
           
            
            
            JLabel direction = new JLabel("Hesaplamak İstediğiniz Mağazalar İçin Hedef Giriniz", 0);
            if(Toolkit.getDefaultToolkit().getScreenSize().width > 1750){
                direction.setFont(new Font("Arial", 1, 20));
            }else if(Toolkit.getDefaultToolkit().getScreenSize().width > 1500){
                direction.setFont(new Font("Arial", 1, 18));
            }else{
                direction.setFont(new Font("Arial", 1, 16));
            }
            direction.setSize(new Dimension(width, (int) (height*0.05)));
            direction.setLocation(0, 0);
            con.add(direction);
            
            setVisible(true);
            setDefaultCloseOperation(3);
            
            KeyStroke closeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
            Action closeAciton = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }};
            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(closeKeyStroke, "ESCAPE");
            getRootPane().getActionMap().put("ESCAPE", closeAciton);
      
            
        } catch (HeadlessException e) {
            System.err.println("Error in multi calculate panel : " + e.getLocalizedMessage());
        }
    }
    public int carp(){
        return 2;
    }
//    
//    private void setProportion(String personalType, double value){
//        try {
//            File file = new File("Options/" + personalType + "Proportion.ini");
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter br = new BufferedWriter(fw);
//            br.write(Double.toString(value));
//            br.close();
//        } catch (IOException e) {
//            System.err.println("Error in saving "+ personalType +  " proportion : " + e.getLocalizedMessage());
//        }
//    }
    /**
    *@param personalType  just fill the type off personal like "consultant", or "manager"
    *
    */
    private double getProportion(String personalType){
        Double db = 0.0;
        try { //options/pro
            File file = new File("Options/" + personalType + "Proportion.ini" );
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            db = Double.parseDouble(br.readLine());
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error in getting" + personalType + " proportion : " + e.getLocalizedMessage());
        }
        return db;
    }
    private String getSavePath(){
        String pathh = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Options/savePath.ini")));
            pathh = br.readLine();
            br.close();
        } catch (IOException e) {
            System.err.println("Error in getting save path in main class : " + e.getLocalizedMessage());
        }
        return pathh;
    }
    class MenuBar extends JMenuBar {
        
    private JCheckBoxMenuItem shopTargetMode;
    
    @SuppressWarnings({"OverridableMethodCallInConstructor", "ResultOfObjectAllocationIgnored"})
    public MenuBar() {
        
//        setFont(new Font("Arial", 1, 14));
//        JMenu file = new JMenu("Dosya");
//        add(file);

//        JMenuItem close = new JMenuItem("Çıkış");
//        file.add(close);
 
        JMenu tools = new JMenu("Araçlar");
        add(tools);

        JMenuItem settings = new JMenuItem("Ayarlar");
        tools.add(settings);

        JMenu calculateMode = new JMenu("Hesaplama Modu");
        calculateMode.setBackground(Color.red);
        add(calculateMode);

        ButtonGroup group = new ButtonGroup();

        shopTargetMode = new JCheckBoxMenuItem("Mağaza Hedefi Hesaplama");
        calculateMode.add(shopTargetMode);

        shopTargetMode.addActionListener((e) -> {
            if(shopTargetMode.isSelected()){
                setCalculateModeOption(0);
                setTitle("Hedef Hesaplama Modu");
                Center.complete.setVisible(false);
                resetTextFields();
            }else{
                setCalculateModeOption(1);
            }
        });

        JCheckBoxMenuItem costumerNumberTarget = new JCheckBoxMenuItem("Tekil Müşteri Hesaplama");
        calculateMode.add(costumerNumberTarget);

        costumerNumberTarget.addActionListener((e) -> {
            if(costumerNumberTarget.isSelected()){
                setCalculateModeOption(1);
                setTitle("Tekil Müşteri Hesaplama Modu");
                Center.complete.setVisible(true);
                resetTextFields();
                
            }else{
                setCalculateModeOption(0);
            }
        });
        
        if(getCalculateModeOption() == 0){
            shopTargetMode.setSelected(true);
            setTitle("Hedef Hesaplama Modu");
        }else{
            costumerNumberTarget.setSelected(true);
            setTitle("Tekil Müşteri Hesaplama Modu");
        }

        group.add(shopTargetMode);
        group.add(costumerNumberTarget);
        settings.addActionListener((e) -> {
            new SettingsFrame();
        });

        JMenu help = new JMenu("Yardım");
        add(help);
        
        JMenuItem item = new JMenuItem("Hakkında");
        help.add(item);
        
        item.addActionListener((e) -> {
            JOptionPane.showMessageDialog(null, "<html>Bu program Gökhan Aslan tarafından yapılmıştır <br> herhangi bir sorunda " + 
                    " gknasln@hotmail.com adresinden ulaşabilirsiniz.<html/>");
        });
        
    }
    private void resetTextFields(){
        for (int i = 0; i < 17; i++) {
            Center.textFieldShop[i].setText("Hedef giriniz...");
            Center.textFieldShop[i].setForeground(Color.gray);
            Center.textFieldShop[i].setHorizontalAlignment(2);
        }
    }
    private int getCalculateModeOption(){
        int i = 0;
        try {
            File file = new File("Options/calculateMode.ini");
            BufferedReader br = new BufferedReader(new FileReader(file));
            i = Integer.parseInt(br.readLine());
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error in getting calculate mode options : " + e.getLocalizedMessage());
        }
        return i;
    }

    private void setCalculateModeOption(int i){
        try {
            File file = new File("Options/calculateMode.ini");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(Integer.toString(i));
            bw.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error in saving calculate mode options : " + e.getLocalizedMessage());
        }
    }
    
    
}
}

