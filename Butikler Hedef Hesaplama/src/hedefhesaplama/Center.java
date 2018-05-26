/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hedefhesaplama;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author gknas
 */
public class Center extends JPanel {
    
    JPanel shop1, shop2, shop3, shop4, shop5, shop6, shop7, shop8,
           shop9, shop10, shop11, shop12, shop13, shop14, shop15, 
           shop16, shop17;
    
    static JToggleButton[] TButtonShop;
    
    static JTextField[] textFieldShop;
    
    JButton setButtonShop1, setButtonShop2, setButtonShop3, setButtonShop4, 
            setButtonShop5, setButtonShop6, setButtonShop7, setButtonShop8, 
            setButtonShop9, setButtonShop10, setButtonShop11, setButtonShop12, 
            setButtonShop13, setButtonShop14, setButtonShop15, setButtonShop16,
            setButtonShop17, calculate, reset;
    
    static JToggleButton complete;
    
    JRadioButton singleWrite, multiWrite;
    
    private Color color1, color2;
    
    private int width, height, icWidth, icHeight, personalNumber, loop;
    static int openedFrames;
    private Font font;
    static JProgressBar progressBar;
   
    @SuppressWarnings({"OverridableMethodCallInConstructor", "ResultOfObjectAllocationIgnored"})
    public Center(int width, int height) {
        try {
            TButtonShop = new JToggleButton[17];
            textFieldShop = new JTextField[17];
            font = setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 1);
            openedFrames = 0;
            
            this.width = width;
            this.height = height;
            
//            setBackgro+und(Color.cyan);
            setLayout(null);
            setSize(width, height);
            
            JPanel inCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            inCenter.setSize(new Dimension((int) (width*0.99), (int) (height*0.91)));
            inCenter.setLocation(3, 3);
            
            add(inCenter);
            // ic = inCenter
            icWidth =   (int) (width*0.99);
            icHeight =  (int) (height*0.054);
            
            color1 = new Color(173, 173, 173);
            color2 = new Color(195, 195, 195);
            setBorder(BorderFactory.createLoweredBevelBorder());
          
            shop1 = panels(icWidth, icHeight);
            shop1.setBackground(color1);
            inCenter.add(shop1);
            
            
            TButtonShop[0] = new ToggleButtons(1);
            TButtonShop[0].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop1.add(TButtonShop[0]);
            
            
           
            textFieldShop[0] = new TextFields();
            textFieldShop[0].setLocation((int) (icWidth*0.42), (int) (icHeight*0.14));
            shop1.add(textFieldShop[0]);
    
            
            
            setButtonShop1 = buttons(1);
            setButtonShop1.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop1.add(setButtonShop1);
            
            
            shop2 = panels(icWidth, icHeight);
            shop2.setBackground(color2);
            inCenter.add(shop2);
            
            
            TButtonShop[1] = new ToggleButtons(2);     
            TButtonShop[1].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop2.add(TButtonShop[1]);
            
           
            textFieldShop[1] = new TextFields();
            textFieldShop[1].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop2.add(textFieldShop[1]);
    
            
            
            setButtonShop2 = buttons(2);
            setButtonShop2.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop2.add(setButtonShop2);
            
            
            shop3 = panels(icWidth, icHeight);
            shop3.setBackground(color1);
            inCenter.add(shop3);
            
            
            TButtonShop[2] = new ToggleButtons(3);     
            TButtonShop[2].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop3.add(TButtonShop[2]);
            
           
            textFieldShop[2] = new TextFields();
            textFieldShop[2].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop3.add(textFieldShop[2]);
            
            
            setButtonShop3 = buttons(3);
            setButtonShop3.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop3.add(setButtonShop3);
            
            
            shop4 = panels(icWidth, icHeight);
            shop4.setBackground(color2);
            inCenter.add(shop4);


            TButtonShop[3] = new ToggleButtons(4);     
            TButtonShop[3].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop4.add(TButtonShop[3]);


            textFieldShop[3] = new TextFields();
            textFieldShop[3].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop4.add(textFieldShop[3]);



            setButtonShop4 = buttons(4);
            setButtonShop4.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop4.add(setButtonShop4);

            shop5 = panels(icWidth, icHeight);
            shop5.setBackground(color1);
            inCenter.add(shop5);


            TButtonShop[4] = new ToggleButtons(5);     
            TButtonShop[4].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop5.add(TButtonShop[4]);


            textFieldShop[4] = new TextFields();
            textFieldShop[4].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop5.add(textFieldShop[4]);



            setButtonShop5 = buttons(5);
            setButtonShop5.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop5.add(setButtonShop5);

            shop6 = panels(icWidth, icHeight);
            shop6.setBackground(color2);
            inCenter.add(shop6);
            
            
            TButtonShop[5] = new ToggleButtons(6);     
            TButtonShop[5].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop6.add(TButtonShop[5]);


            textFieldShop[5] = new TextFields();
            textFieldShop[5].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop6.add(textFieldShop[5]);



            setButtonShop6 = buttons(6);
            setButtonShop6.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop6.add(setButtonShop6);
            

            shop7 = panels(icWidth, icHeight);
            shop7.setBackground(color1);
            inCenter.add(shop7);


            TButtonShop[6] = new ToggleButtons(7);     
            TButtonShop[6].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop7.add(TButtonShop[6]);


            textFieldShop[6] = new TextFields();
            textFieldShop[6].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop7.add(textFieldShop[6]);



            setButtonShop7 = buttons(7);
            setButtonShop7.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop7.add(setButtonShop7);

            
            shop8 = panels(icWidth, icHeight);
            shop8.setBackground(color2);
            inCenter.add(shop8);
            
            
            TButtonShop[7] = new ToggleButtons(8);     
            TButtonShop[7].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop8.add(TButtonShop[7]);


            textFieldShop[7] = new TextFields();
            textFieldShop[7].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop8.add(textFieldShop[7]);


            setButtonShop8 = buttons(8);
            setButtonShop8.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop8.add(setButtonShop8);
            
            
            shop9 = panels(icWidth, icHeight);
            shop9.setBackground(color1);
            inCenter.add(shop9);


            TButtonShop[8] = new ToggleButtons(9);     
            TButtonShop[8].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop9.add(TButtonShop[8]);


            textFieldShop[8] = new TextFields();
            textFieldShop[8].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop9.add(textFieldShop[8]);



            setButtonShop9 = buttons(9);
            setButtonShop9.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop9.add(setButtonShop9);
            
            
            shop10 = panels(icWidth, icHeight);
            shop10.setBackground(color2);
            inCenter.add(shop10);


            TButtonShop[9] = new ToggleButtons(10);     
            TButtonShop[9].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop10.add(TButtonShop[9]);


            textFieldShop[9] = new TextFields();
            textFieldShop[9].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop10.add(textFieldShop[9]);



            setButtonShop10 = buttons(10);
            setButtonShop10.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop10.add(setButtonShop10);
            
            
            shop11 = panels(icWidth, icHeight);
            shop11.setBackground(color1);
            inCenter.add(shop11);


            TButtonShop[10] = new ToggleButtons(11);     
            TButtonShop[10].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop11.add(TButtonShop[10]);


            textFieldShop[10] = new TextFields();
            textFieldShop[10].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop11.add(textFieldShop[10]);



            setButtonShop11 = buttons(11);
            setButtonShop11.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop11.add(setButtonShop11);
            
            
            shop12 = panels(icWidth, icHeight);
            shop12.setBackground(color2);
            inCenter.add(shop12);


            TButtonShop[11] = new ToggleButtons(12);     
            TButtonShop[11].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop12.add(TButtonShop[11]);


            textFieldShop[11] = new TextFields();
            textFieldShop[11].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop12.add(textFieldShop[11]);



            setButtonShop12 = buttons(12);
            setButtonShop12.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop12.add(setButtonShop12);
            
            
            shop13 = panels(icWidth, icHeight);
            shop13.setBackground(color1);
            inCenter.add(shop13);


            TButtonShop[12] = new ToggleButtons(13);     
            TButtonShop[12].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop13.add(TButtonShop[12]);


            textFieldShop[12] = new TextFields();
            textFieldShop[12].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop13.add(textFieldShop[12]);



            setButtonShop13 = buttons(13);
            setButtonShop13.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop13.add(setButtonShop13);
            

            shop14 = panels(icWidth, icHeight);
            shop14.setBackground(color2);
            inCenter.add(shop14);


            TButtonShop[13] = new ToggleButtons(14);     
            TButtonShop[13].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop14.add(TButtonShop[13]);


            textFieldShop[13] = new TextFields();
            textFieldShop[13].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop14.add(textFieldShop[13]);



            setButtonShop14 = buttons(14);
            setButtonShop14.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop14.add(setButtonShop14);
            

            shop15 = panels(icWidth, icHeight);
            shop15.setBackground(color1);
            inCenter.add(shop15);


            TButtonShop[14] = new ToggleButtons(15);     
            TButtonShop[14].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop15.add(TButtonShop[14]);

            TButtonShop[14].addActionListener((ActionEvent e) -> {
//                tButtonAction(TButtonShop[14], 16);
            });

            textFieldShop[14] = new TextFields();
            textFieldShop[14].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop15.add(textFieldShop[14]);



            setButtonShop15 = buttons(15);
            setButtonShop15.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop15.add(setButtonShop15);

            
            shop16 = panels(icWidth, icHeight);
            shop16.setBackground(color2);
            inCenter.add(shop16);


            TButtonShop[15] = new ToggleButtons(16);     
            TButtonShop[15].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1 ));
            shop16.add(TButtonShop[15]);

            TButtonShop[15].addActionListener((ActionEvent e) -> {
//                tButtonAction(TButtonShop[15], 16);
            });

            textFieldShop[15] = new TextFields();
            textFieldShop[15].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop16.add(textFieldShop[15]);



            setButtonShop16 = buttons(16);
            setButtonShop16.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop16.add(setButtonShop16);
            
            
            shop17 = panels(icWidth, icHeight);
            shop17.setBackground(color1);
            inCenter.add(shop17);


            TButtonShop[16] = new ToggleButtons(17);     
            TButtonShop[16].setLocation((int) (icWidth*0.03), (int) (icHeight*0.1));
            shop17.add(TButtonShop[16]);
            

            textFieldShop[16] = new TextFields();
            textFieldShop[16].setLocation((int) (icWidth*0.42), (int) (icHeight*0.15));
            shop17.add(textFieldShop[16]);


            setButtonShop17 = buttons(17);
            setButtonShop17.setLocation((int) (icWidth*0.81), (int) (icHeight*0.15));
            shop17.add(setButtonShop17);
            
            // adding keylistener to textfiled for passing each other with keyboard
            for (int i = 0; i < 16; i++) {
                 if(i == 0){
                    textFieldShop[i].addKeyListener(new textFieldsKeyListeners(textFieldShop[16], textFieldShop[1]));
                }else if(i >=1 && i <=15){
                    textFieldShop[i].addKeyListener(new textFieldsKeyListeners(textFieldShop[i-1], textFieldShop[i+1]));
                }else if(i == 16){
                    textFieldShop[i].addKeyListener(new textFieldsKeyListeners(textFieldShop[i-1], textFieldShop[0]));
                }
            }

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setSize(new Dimension((int) (width*0.99), (int) (height*0.09)));
        buttonPanel.setLocation(3, (int) (height*0.91));
        add(buttonPanel);
            
        int buttonPanelWidt = buttonPanel.getSize().width;
        int buttonPanelHeig = buttonPanel.getSize().height;
            
        ButtonGroup writeGroup = new ButtonGroup();
            
        singleWrite =  new JRadioButton("Tek Dosya'ya yaz");            
        singleWrite.setSize((int) (buttonPanelWidt*0.3), (int) (buttonPanelHeig*0.3));
        singleWrite.setLocation((int) (buttonPanelWidt*0.01), (int) (buttonPanelHeig*0.2));
        singleWrite.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 0));
        buttonPanel.add(singleWrite);
            
        singleWrite.addActionListener((e) -> {
           if(singleWrite.isSelected()){
                setWriteOptions(0);
            }
            });
            
        multiWrite = new JRadioButton("Ayrı dosya oluştur");
        multiWrite.setSize((int) (buttonPanelWidt*0.3), (int) (buttonPanelHeig*0.3));
        multiWrite.setLocation((int) (buttonPanelWidt*0.01), (int) (buttonPanelHeig*0.5));
        multiWrite.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 0));
        buttonPanel.add(multiWrite);
            
        multiWrite.addActionListener((e) -> {
            if(multiWrite.isSelected()){
                setWriteOptions(1);
            }
        });
            
        if(getWriteOptions() == 0){
            singleWrite.setSelected(true);
        }else{
            multiWrite.setSelected(true);
        }

        writeGroup.add(singleWrite);
        writeGroup.add(multiWrite);
            
        complete = new JToggleButton("Tamamla", getCompleteButtonOption());
        complete.setSize((int) (buttonPanelWidt*0.15), (int) (buttonPanelHeig*0.5));
        complete.setLocation((int) (buttonPanelWidt*0.369), (int) (buttonPanelHeig*0.25));
        complete.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 1));
        buttonPanel.add(complete);
        
        if(getCalculateModeOption() == 0){
            complete.setVisible(false); 
        }
        
        
        complete.addActionListener((e) -> {
            if(complete.isSelected()){
                setCompleteButtonOption(true);
            }else{
                setCompleteButtonOption(false);
            }
        });
        
        reset = resetButton();
        reset.setSize((int) (buttonPanelWidt*0.16), (int) (buttonPanelHeig*0.5));
        reset.setLocation((int) (buttonPanelWidt*0.56), (int) (buttonPanelHeig*0.25));
        reset.setFont(new Font("Arial", 1, 13));
        buttonPanel.add(reset);
        
        calculate = new JButton("Hesapla");
        calculate.setSize((int) (buttonPanelWidt*0.22), (int) (buttonPanelHeig*0.65));
        calculate.setLocation((int) (buttonPanelWidt*0.75), (int) (buttonPanelHeig*0.17));
        calculate.setFont(new Font("Arial", 1, 13));
        buttonPanel.add(calculate);
        
        calculate.addActionListener((ActionEvent e) -> {
            calculateButtonAction();
        });
           
        
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setStringPainted(true);
        progressBar.setSize((int) (buttonPanelWidt*0.99), (int) (buttonPanelHeig*0.23));
        progressBar.setLocation((int) (buttonPanelWidt*0.01), (int) (buttonPanelHeig*0.8));
        progressBar.setVisible(false);
        buttonPanel.add(progressBar);
            
        } catch (HeadlessException e) {
            System.err.println("Error in center panel : " + e.getLocalizedMessage());
        }
    }
    
    private JButton resetButton(){
        JButton button = new JButton("Sıfırla");
        button.addActionListener((e) -> {
            for (int i = 0; i < 17; i++) {
            textFieldShop[i].setText("Hedef giriniz...");
            textFieldShop[i].setForeground(Color.gray);
            textFieldShop[i].setHorizontalAlignment(2);
        }
        });
        button.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setForeground(Color.red);
                    button.setFont(new Font("Arial", 1, 14));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(Color.black);
                    button.setFont(new Font("Arial", 1, 13));
                }
        });
        return button;
    }
    
    
    private boolean getTButtonOption(int shopNumber){
        boolean b = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Options/shop" + shopNumber + "/TButtonOption.ini")));
            b = Boolean.parseBoolean(br.readLine());
            br.close();
        } catch (IOException e) {
            System.err.println("Error in getting shop" + shopNumber + " TButton options : " + e.getLocalizedMessage());
        }
        return b;
    }
    
    private void setTButtonOption(int shopNumber, boolean b){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Options/shop" + shopNumber + "/TButtonOption.ini")));
            bw.write(Boolean.toString(b));
            bw.close();
        } catch (IOException e) {
            System.err.println("Error in saving shop" + shopNumber + " TButton options : " + e.getLocalizedMessage());
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
            System.err.println("Error in getting calculate mode options in center class : " + e.getLocalizedMessage());
        }
        return i;
    }
    private void setCompleteButtonOption(boolean option){
        try {
            File file = new File("Options/completeButtonOption.ini");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(Boolean.toString(option));
            bw.close();
        } catch (IOException e) {
            System.err.println("Error in saving complete button action : " + e.getLocalizedMessage());
        }
    }
    
    private boolean getCompleteButtonOption(){
        boolean option = false;
        try {
            File file = new File("Options/completeButtonOption.ini");
            BufferedReader br = new BufferedReader(new FileReader(file));
            option = Boolean.parseBoolean(br.readLine());
        } catch (IOException e) {
            System.err.println("Error in getting complete  button option : " + e.getLocalizedMessage());
        }
        return option;
    }
    private String getShopName(int shopNumber){
        String str = null; 
        try {
            File file  = new File( "Options/shop" + shopNumber + "/shopName.ini");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            str = br.readLine();
            br.close();
        } catch (IOException e) {
            System.out.println("Error in reading shop name : " + e.getLocalizedMessage());
        }
        return str;
    }
    
    private JPanel panels(int width, int height){
        JPanel panel  = new JPanel(null);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }
    
    private Font setFont(int width, int fontType){
        Font fontt = new Font("Arial", fontType, 11);
        if(width > 1701){
            fontt = new Font("Arial", fontType, 13); 
        }else if(width > 1300 && width < 1701){
            fontt = new Font("Arial", fontType, 12);
        }else if(width > 1100 && width < 1300){
            fontt = new Font("Arial", fontType, 11);
        }else if(width > 800 && width < 1100){
            fontt = new Font("Arial", fontType, 9);
        }
        return fontt;
    }
    
    
    class ToggleButtons extends JToggleButton{
        JToggleButton button;
       
        @SuppressWarnings("OverridableMethodCallInConstructor")
    private  ToggleButtons(int shopNumber){
        try {
            personalNumber = new File("Personals/shop" + shopNumber).list().length;
            if(personalNumber == 0){// when frame is closed if there is no personal for this shop buttons willl be unable
                setEnabled(false);
            }else{
                setEnabled(true);
            }
            
            setText(getShopName(shopNumber));
            setSelected(getTButtonOption(shopNumber));
            setSize((int) (icWidth*0.34), (int) (icHeight*0.75));
            setToolTipText("Mağazanın aktiflik durumunu ayarlar..");
            setFont(font);
            
            addActionListener((ActionEvent e) -> {
                 if(isSelected()){
                    setTButtonOption(shopNumber, true);
                 }else{
                    setTButtonOption(shopNumber, false);
        }
            });
            
        } catch (HeadlessException e) {
            System.err.println("Error in centers toggle : " + e.getLocalizedMessage());
        }
    }
    }
    
    
    
    class TextFields extends JTextField{

        @SuppressWarnings("OverridableMethodCallInConstructor")
        public TextFields() {
         try {
            if(personalNumber == 0){// when frame is closed if there is no personal for this shop buttons willl be unable
                setEditable(false);
                setBackground(new Color(210, 210, 210));
            }
            setText("Hedef giriniz...");
            setSize((int) (icWidth*0.34), (int) (icHeight*0.75));
            setForeground(Color.gray);
             setFont(font);
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if(getText().equals("Hedef giriniz...")){
                        setText("");
                    }else{
                        selectAll();
                    }
                    setForeground(Color.black);
                    setHorizontalAlignment(0);
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if(getText().equals("")){
                        setText("Hedef giriniz...");
                        setHorizontalAlignment(2);
                        setForeground(Color.gray);
                    }
                }
                
        });
            
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int limit;
                if(getCalculateModeOption() == 1){
                     limit = 4;
                }else{
                     limit = 9;
                }
                if(getText().length() >= limit){
                        e.consume();
                        Toolkit.getDefaultToolkit().beep();
                }
                if((e.getKeyChar() < '0') || (e.getKeyChar() >'9')){
//                    if((e.getKeyChar() != '.') && (e.getKeyChar() != ',')){
                        e.consume();
                        Toolkit.getDefaultToolkit().beep();
                }
            }
                @Override
                public void keyReleased(KeyEvent e) {            
                if( getCalculateModeOption() == 0){
                String str = getText();
                if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                switch(str.length()){
                    case 4 :
                        setText(str.charAt(0) + "." + str.substring(1, str.length()));
                        break;
                    case 6 :
                        str = str.replace(".", "");
                        setText(str.substring(0, 2) + "." + str.substring(2, str.length()));
                        break;
                    case 7 :
                        str = str.replace(".", "");
                        setText(str.substring(0, 3) + "." + str.substring(3, str.length()));
                        break;
                    case 8 : 
                        str = str.replace(".", "");
                        setText(str.charAt(0) + "." + str.substring(1, 4) + "." + str.substring(4, str.length()));
                        break;
                    case 10 :
                        str = str.replace(".", "");
                        setText(str.substring(0, 2) + "." + str.substring(2, 5) + "." + str.substring(5, str.length()));
                        break;
                }
                }else{
                    switch(str.length()){
                        case 4 :
                            str = str.replace(".", "");
                            setText(str);
                            break;
                        case 5 : 
                            str = str.replace(".", "");
                            setText(str.charAt(0) + "." + str.substring(1, str.length()));
                            break;
                        case 6 :
                            str = str.replace(".", "");
                            setText(str.substring(0, 2) + "." + str.substring(2, str.length()));
                            break;
                         case 8 :
                            str = str.replace(".", "");
                            setText(str.substring(0, 3) + "." + str.substring(3, str.length()));
                            break;
                        case 9 : 
                            str = str.replace(".", "");
                            setText(str.charAt(0) + "." + str.substring(1, 4) + "." + str.substring(4, str.length()));
                            break;
                        default : break;
                
                    }
                }
                }
                }
            
        });
        } catch (HeadlessException e) {
            System.err.println("Error in Center textFields : " + e.getLocalizedMessage());
        }
        }
    }
    
    class textFieldsKeyListeners extends KeyAdapter{
        JTextField up, down;

        public textFieldsKeyListeners(JTextField up, JTextField down) {
            this.up = up;
            this.down = down;
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_ENTER :
                    down.requestFocusInWindow();
                    break;
                case KeyEvent.VK_DOWN :
                    down.requestFocus();
                    break;
                case KeyEvent.VK_UP :
                    up.requestFocus();
                    break;
                default :
                break;
            }
        }  
    }
    
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private JButton buttons(int shopNumber){
        JButton button = null;
        try {
          button = new JButton("Ayarlar");
          button.setSize((int) (icWidth*0.15), (int) (icHeight*0.7));
          button.setToolTipText("Mağaza personel ayarları..");
          button.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 0));
          button.addActionListener((e) -> {
                if(openedFrames < 1){
                    new ShopSetting(shopNumber);
                    openedFrames++;
                }else{
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Aynı anda birden fazla ayar penceresi açılamaz", "Diğer ayar penceresini kapatın!", 2);
                }
            });
        } catch (HeadlessException e) {
            System.err.println("Error in centers button : " + e.getLocalizedMessage());
        }
      return button;
    }
    
    
    private void setWriteOptions(int i){
        try {
            File file = new File("Options/writeOptions.ini");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Integer.toBinaryString(i));
            bw.close();
        } catch (IOException e) {
            System.err.println("Error in saving write options : " + e.getLocalizedMessage());
        }
    }
    
    private int getWriteOptions(){
        int i = 0;
        try {
            File file = new File("Options/writeOptions.ini");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            i = Integer.parseInt(br.readLine());
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error in getting write options : " + e.getLocalizedMessage());
        }
        return i;
    }
   
    private void calculateButtonAction(){
        // making visible progress bar and writing inside "starting"
        progressBar.setVisible(true);
        progressBar.setString("Başlatılıyor");
        boolean[] shop = new boolean[17];
        int[] shopTarget = new int[17];
        shop[0] = TButtonShop[0].isSelected() && !textFieldShop[0].getText().contains("Hedef giriniz...");
        if(shop[0])
            shopTarget[0] = fixBeforeGetShopTarget(textFieldShop[0].getText());
        else
            shopTarget[0] = 0;
        
        shop[1] = TButtonShop[1].isSelected() && !textFieldShop[1].getText().contains("Hedef giriniz...");
        if(shop[1])
            shopTarget[1] = fixBeforeGetShopTarget(textFieldShop[1].getText());
        else
            shopTarget[1] = 0;
        
        shop[2] = TButtonShop[2].isSelected() && !textFieldShop[2].getText().contains("Hedef giriniz...");
        if(shop[2])
            shopTarget[2] = fixBeforeGetShopTarget(textFieldShop[2].getText());
        else
            shopTarget[2] = 0;
        
        shop[3] = TButtonShop[3].isSelected() && !textFieldShop[3].getText().contains("Hedef giriniz...");
        if(shop[3])
            shopTarget[3] = fixBeforeGetShopTarget(textFieldShop[3].getText());
        else
            shopTarget[3] = 0;
        
        shop[4] = TButtonShop[4].isSelected() && !textFieldShop[4].getText().contains("Hedef giriniz...");
        if(shop[4])
            shopTarget[4] = fixBeforeGetShopTarget(textFieldShop[4].getText());
        else
            shopTarget[4] = 0;
        
        shop[5] = TButtonShop[5].isSelected() && !textFieldShop[5].getText().contains("Hedef giriniz...");
        if(shop[5])
            shopTarget[5] = fixBeforeGetShopTarget(textFieldShop[5].getText());
        else
            shopTarget[5] = 0;
        
        shop[6] = TButtonShop[6].isSelected() && !textFieldShop[6].getText().contains("Hedef giriniz...");
        if(shop[6])
            shopTarget[6] = fixBeforeGetShopTarget(textFieldShop[6].getText());
        else
            shopTarget[6] = 0;
        
        shop[7] = TButtonShop[7].isSelected() && !textFieldShop[7].getText().contains("Hedef giriniz...");
        if(shop[7])
            shopTarget[7] = fixBeforeGetShopTarget(textFieldShop[7].getText());
        else
            shopTarget[7] = 0;
        
        shop[8] = TButtonShop[8].isSelected() && !textFieldShop[8].getText().contains("Hedef giriniz...");
        if(shop[8])
            shopTarget[8] = fixBeforeGetShopTarget(textFieldShop[8].getText());
        else
            shopTarget[8] = 0;
        
        shop[9] = TButtonShop[9].isSelected() && !textFieldShop[9].getText().contains("Hedef giriniz...");
        if(shop[9])
            shopTarget[9] = fixBeforeGetShopTarget(textFieldShop[9].getText());
        else
            shopTarget[9] = 0;
        
        shop[10] = TButtonShop[10].isSelected() && !textFieldShop[10].getText().contains("Hedef giriniz...");
        if(shop[10])
            shopTarget[10] = fixBeforeGetShopTarget(textFieldShop[10].getText());
        else
            shopTarget[10] = 0;
        
        shop[11] = TButtonShop[11].isSelected() && !textFieldShop[11].getText().contains("Hedef giriniz...");
        if(shop[11])
            shopTarget[11] = fixBeforeGetShopTarget(textFieldShop[11].getText());
        else
            shopTarget[11] = 0;
        
        shop[12] = TButtonShop[12].isSelected() && !textFieldShop[12].getText().contains("Hedef giriniz...");
        if(shop[12])
            shopTarget[12] = fixBeforeGetShopTarget(textFieldShop[12].getText());
        else
            shopTarget[12] = 0;
        
        shop[13] = TButtonShop[13].isSelected() && !textFieldShop[13].getText().contains("Hedef giriniz...");
        if(shop[13])
            shopTarget[13] = fixBeforeGetShopTarget(textFieldShop[13].getText());
        else
            shopTarget[13] = 0;
        
        shop[14] = TButtonShop[14].isSelected() && !textFieldShop[14].getText().contains("Hedef giriniz...");
        if(shop[14])
            shopTarget[14] = fixBeforeGetShopTarget(textFieldShop[14].getText());
        else
            shopTarget[14] = 0;
        
        shop[15] = TButtonShop[15].isSelected() && !textFieldShop[15].getText().contains("Hedef giriniz...");
        if(shop[15])
            shopTarget[15] = fixBeforeGetShopTarget(textFieldShop[15].getText());
        else
            shopTarget[15] = 0;
        
        shop[16] = TButtonShop[16].isSelected() && !textFieldShop[16].getText().contains("Hedef giriniz...");
        if(shop[16])
            shopTarget[16] = fixBeforeGetShopTarget(textFieldShop[16].getText());
        else
            shopTarget[16] = 0;
       
        if(singleWrite.isSelected()){
            Thread thread = new Thread(new MultiWriteAction(shop, shopTarget));
            thread.start();
        }else{
            Thread thread = new Thread(new SingleWriteAciton(shop, shopTarget));
            thread.start(); 
        }
        Toolkit.getDefaultToolkit().beep();  
    }
    
    
    
    private int fixBeforeGetShopTarget(String target){
        try {
        target = target.replace(".", "");
        target = target.replace(",", "");
        } catch (Exception e) {
            System.err.println("Error in fixing entered target " + e.getLocalizedMessage());
        }
        return Integer.parseInt(target);
    }
}
