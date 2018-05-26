package hedefhesaplama;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


public class ShopSetting extends JFrame{
    private JLabel settingsFrameDirection;
    private JPanel center;
    private JButton saveShopName;
    private JTextField shopNameTextField;
    private int width, height, centerWidth, centerHeight, shopNumber, personalNumber;
    static JTextField personalNameTextField;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args){
        new ShopSetting(1);
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
        public ShopSetting(int shopNumber) {
        try{
            // creating new file object and connecting current shopSettings
//            File file = new File("Options/shop" + shopNumber + "/shopName.ini");
        
        setUndecorated(true);
        setTitle(getShopName(shopNumber));
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        // getting shopnumber from constructor for using it  other methots 
        this.shopNumber = shopNumber;
        
        // fitting width and height before give settings frames size
        width = (int) (width*0.33);
        height = (int) (height*0.78);
        
        setSize (width, height);
        setLocationRelativeTo(null);
        

        JPanel con = new MotionPanel(this);
        setContentPane(con);
        con.setLayout(null);
        con.setBackground(Color.gray);
        JButton closeButton = closeButton((int) (width*0.08), (int) (width*0.07));
//        closeButton.setSize();
        closeButton.setLocation((int) (width*0.92), 0);
        con.add(closeButton);
                
        settingsFrameDirection = new JLabel(getShopName(shopNumber) + " Mağaza Ayarları", 0);
        settingsFrameDirection.setSize((int) (width*0.97), (int) (height*0.07));
        settingsFrameDirection.setLocation(0, 0);
        settingsFrameDirection.setFont(new Font("Arial", 1, 18));
        con.add(settingsFrameDirection);
        
        JLabel shopNameDirection = new JLabel("Mağaza adı :");
        shopNameDirection.setSize(width, (int) (height*0.03));
        shopNameDirection.setLocation((int) (width*0.05), (int) (height*0.075));
        con.add(shopNameDirection);
        
        shopNameTextField = textFields(getShopName(shopNumber));
        shopNameTextField.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 1));
        shopNameTextField.setSize((int) (width*0.7), (int) (height*0.04));
        shopNameTextField.setLocation((int) (width*0.03), (int) (height*0.1));
        con.add(shopNameTextField);
        
        shopNameTextField.addActionListener((e) -> {
            setShopName(shopNumber, shopNameTextField.getText());
            setTitle(shopNameTextField.getText());
            settingsFrameDirection.setText(shopNameTextField.getText() + " Mağaza Ayarları");
            Center.TButtonShop[shopNumber].setText(shopNameTextField.getText());
            saveShopName.setEnabled(false);
        });
        
        // adding key listener  for making enable save button in right  text 
        shopNameTextField.addKeyListener( new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if(shopNameTextField.getText().equals(getShopName(shopNumber))){
                        saveShopName.setEnabled(false);
                    }else{
                        saveShopName.setEnabled(true);
                    }
                }
             
         });
         
        // adding  focus listener for making enable save button in right text
         shopNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                shopNameTextField.selectAll();
            }
             @Override
            public void focusLost(FocusEvent e) {
                if(shopNameTextField.getText().equals("")){
                    shopNameTextField.setText(getShopName(shopNumber));
                    saveShopName.setEnabled(false);
                    System.out.println("bu ");
                }
            }
        });
      
        
        
        saveShopName = new JButton("Kaydet");
        saveShopName.setSize((int) (width*0.2), (int) (height*0.04));
        saveShopName.setLocation((int) (width*0.75), (int) (height*0.1));
        saveShopName.setEnabled(false);
        saveShopName.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 0));
        con.add(saveShopName);
        
        
        saveShopName.addActionListener((e) -> {
            setShopName(shopNumber, shopNameTextField.getText());
            setTitle(shopNameTextField.getText());
            settingsFrameDirection.setText(shopNameTextField.getText() + " Mağaza Ayarları");
            Center.TButtonShop[shopNumber].setText(shopNameTextField.getText());
            saveShopName.setEnabled(false);
        });
        
        ModifiedFlowLayout layout = new ModifiedFlowLayout(FlowLayout.LEFT, 1, 0);
        center = new JPanel(layout);
        
//        con.add(center);
        JScrollPane scroll = new JScrollPane(center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setSize((int) (width*0.98), (int) (height*0.83));
        scroll.setLocation((int) (width*0.01), (int) (height*0.16));
        scroll.setBackground(new Color(220, 220, 220));
        scroll.setBorder(BorderFactory.createLoweredBevelBorder());
        con.add(scroll);
        
        centerWidth = (int) (scroll.getSize().width*0.995);
        centerHeight = scroll.getSize().height;
        createNewList(shopNumber);
        center.add(newPersonalButton());
        
        
        // this keystroke closing frame with escape button
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
        // Esc Tuşuna basıldığında pencereyi kapatan action metodu
        @Override
        public void actionPerformed(ActionEvent e) {
            personalNumber = new File("Personals/shop" + shopNumber ).list().length;
            if(personalNumber == 0){// when frame is closed if there is no personal for this shop buttons willl be unable
                Center.textFieldShop[shopNumber-1].setEditable(false);
                Center.textFieldShop[shopNumber-1].setBackground(new Color(210, 210, 210));
                Center.textFieldShop[shopNumber-1].setText("Hedef giriniz...");
                Center.TButtonShop[shopNumber-1].setEnabled(false);
            }else{
                Center.textFieldShop[shopNumber-1].setEditable(true);
                Center.textFieldShop[shopNumber-1].setBackground(Color.white);
                Center.TButtonShop[shopNumber-1].setEnabled(true);    
            }            Center.openedFrames--;
            dispose();
        }}; 
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        this.getRootPane().getActionMap().put("ESCAPE", escapeAction);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        }catch(HeadlessException e){
            System.err.println("error in shop setting frame : " + e.getLocalizedMessage());
        }
    }
        
     
        // this method setting font depending on screen size width
     private Font setFont(int width, int fontType){
        Font font = new Font("Arial", fontType, 11);
        if(width > 1701){
            font = new Font("Arial", fontType, 13); 
        }else if(width > 1300 && width < 1701){
            font = new Font("Arial", fontType, 12);
        }else if(width > 1100 && width < 1300){
            font = new Font("Arial", fontType, 11);
        }else if(width > 800 && width < 1100){
            font = new Font("Arial", fontType, 9);
        }
        return font;
    }
       
     // this method creating new personal list 
     private void createNewList(int shopNumber){
       center.removeAll();
       center.updateUI();
       File[] files = new File("Personals/shop" + shopNumber).listFiles();
       // if there is no personal files array will be null 
       if(files != null){
           // this loop  adding managers  
           for (File file : files) {
               if(getPersonalType(file) == 0 ){
                   center.add(new Personal(file));
               }
           }
           // this loop adding experts
             for (File file : files) {
                 if(getPersonalType(file) == 1){
                     center.add(new Personal(file));
                 }
             }
           // this loop adding consultants
             for(File file : files){
                 if(getPersonalType(file) == 2){
                     center.add(new Personal(file));
                 }
             }

            // this loop adding other personals
             for(File file : files){
                 if(getPersonalType(file) == 3){
                     center.add(new Personal(file));
                 }
             }
       }
   }
     
     
     // ths method getting personal type with accessing file
  private int getPersonalType(File file){
      int i = 0;
      try {
          FileReader fr = new FileReader(file);
          BufferedReader br = new BufferedReader(fr);
          i = Integer.parseInt(br.readLine());
          br.close();
      } catch (IOException | NumberFormatException e) {
          System.out.println("Error in getting personal type in shopsetting class : " + e.getLocalizedMessage());
      }
      return i;
  }
  
  
  //this  method getting personal name with accessing files name
  private String getPersonalName(File file){
      String str = file.getName().replace(".ini", "");
      return str;
  }
  
  
  
  // this method getting personal proportion with accessing file
  private double getPersonalPropotion(File file){
      double db = 0.0;
      try {
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);
         br.readLine();
         db = Double.parseDouble(br.readLine());
         br.close();
      } catch (IOException e) {
          System.out.println("Error in getting personal proportion in shopsetting class : " + e.getLocalizedMessage());
      }
      return db;
 }
  // this method getting shop name accessing shops adres its just needs shopnumber
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
  
  // this  method saving shops name 
  private void setShopName(int shopNumber, String shopName){
      try {
          File file = new File("Options/shop" + shopNumber + "/shopName.ini");
          FileWriter fw = new FileWriter(file);
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write(shopName);
          bw.close();
      } catch (IOException e) {
          System.err.println("Error in saving shop name " + e.getLocalizedMessage());
      }
  }
  
  private JButton closeButton(int width, int height){
      JButton button = new JButton("X");
      button.setSize(width, height);
      button.setFont(new Font("Arial", 1, 16));
      
      Color currentColor = button.getBackground();
      
      button.addMouseListener(new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {
              personalNumber = new File("Personals/shop" + shopNumber ).list().length;
            if(personalNumber == 0){// when frame is closed if there is no personal for this shop buttons willl be unable
                Center.textFieldShop[shopNumber-1].setEditable(false);
                Center.textFieldShop[shopNumber-1].setBackground(new Color(210, 210, 210));
                Center.textFieldShop[shopNumber-1].setText("Hedef giriniz...");
                Center.TButtonShop[shopNumber-1].setEnabled(false);
            }else{
                Center.textFieldShop[shopNumber-1].setEditable(true);
                Center.textFieldShop[shopNumber-1].setBackground(Color.white);
                Center.TButtonShop[shopNumber-1].setEnabled(true);    
            }
              Center.openedFrames--;
              dispose();
          }

          @Override
          public void mousePressed(MouseEvent e) {
              button.setBackground(new Color(140, 0, 0));
          }

          @Override
          public void mouseReleased(MouseEvent e) {
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              button.setBackground(new Color(190,0 , 0));
              button.setFont(new Font("Arial", 1, 17));
          }

          @Override
          public void mouseExited(MouseEvent e) {
              button.setBackground(currentColor);
              button.setFont(new Font("Arial", 1, 14));
          }
      });
      
      return button;
  }
  // this methods making textfileds with a keylistener
  private JTextField textFields(String str){
      JTextField text = new JTextField(str);
      text.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 0));
      text.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if(text.getText().endsWith(" ")){
                if(e.getKeyChar() == ' '){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
    });
      return text;
  }
     

  // // this method making a Button for creating new personal
   private JPanel newPersonalButton(){
       
       JPanel panel = new JPanel(new BorderLayout(0, 0));
       panel.setPreferredSize(new Dimension(centerWidth, (int) (centerHeight*0.08)));
       
       
       ImageIcon addIcon = new  ImageIcon("icons/addIcon.png");
       Image fixAddIcon = addIcon.getImage().getScaledInstance((int) (panel.getPreferredSize().height), (int) (panel.getPreferredSize().height*0.9), 4);
       addIcon.setImage(fixAddIcon);
       
       
       JButton button = new JButton("Personel Ekle", addIcon);
       button.setHorizontalTextPosition(2);
       button.setFont(setFont(Toolkit.getDefaultToolkit().getScreenSize().width, 0));
       panel.add(button, BorderLayout.CENTER);
       
       button.addActionListener((e) -> {
           center.removeAll();
           center.updateUI();
           createNewList(shopNumber);
           center.add(new NewPersonalPanel());
           personalNameTextField.requestFocusInWindow();
       });
       return panel;
   }
   
   
   
   
   // this panel represents personals
    class  Personal extends JPanel{
        private JComboBox personalTypeComboBox;
        private JTextField proportionTextField, personalNameTextField;
        private JButton  saveButton, deleteButton;
        private int panelWidth, panelHeight;
        private Color panelColor;
        
        @SuppressWarnings("OverridableMethodCallInConstructor")
        public Personal(File file){
            panelWidth = centerWidth;
            panelHeight = (int) (centerHeight*0.08);
            
            setPreferredSize(new Dimension(panelWidth, panelHeight));
            
            setLayout(new FlowLayout(FlowLayout.LEADING, (int) (panelWidth*0.02), (int) (panelHeight*0.12)));
            
         setBorder(BorderFactory.createEtchedBorder());
            switch(getPersonalType(file)){
                 case 0 : panelColor = new Color(130, 130, 130); break;
                 case 1 : panelColor = new Color(160, 160, 160); break;
                 case 2 : panelColor = new Color(190, 190, 190); break;
                 case 3 : panelColor = new Color(210, 210, 210); break;
                 default : panelColor = Color.lightGray; break;
         }
            
         setBackground(panelColor); 
         
         // this textfield showing current personal name
         personalNameTextField = textFields(getPersonalName(file));
         personalNameTextField.setFont(new Font("Arial", 1, 13));
         personalNameTextField.setPreferredSize(new Dimension((int) (panelWidth*0.3), (int) (panelHeight*0.7)));
         add(personalNameTextField);
         
         personalNameTextField.addKeyListener( new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if(personalNameTextField.getText().equals(getPersonalName(file))){
                        saveButton.setEnabled(false);
                    }else{
                        saveButton.setEnabled(true);
                    }
                }
             
         });
         
         personalNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                personalNameTextField.selectAll();
            }
             @Override
            public void focusLost(FocusEvent e) {
                if(personalNameTextField.getText().equals("")){
                    personalNameTextField.setText(getPersonalName(file));
                    saveButton.setEnabled(false);
                }
            }
        });
      
         
         // creating personal type names
         String[] personelTypes = {"Satış Yöneticisi", "Uzman Satış Danışmanı", "Satış Danışmanı", "Diğer" };
         
         // makking cbox for change current personal types
         personalTypeComboBox = new JComboBox(personelTypes);
         personalTypeComboBox.setPreferredSize(new Dimension((int) (panelWidth*0.25), (int) (panelHeight*0.7)));
         personalTypeComboBox.setSelectedIndex(getPersonalType(file));
         add(personalTypeComboBox);
//         
         personalTypeComboBox.addActionListener((ActionEvent e) -> {
             // if personal type is changed it will enable to save button, otherwise it will not.
             if(getPersonalType(file) == personalTypeComboBox.getSelectedIndex()){
                 saveButton.setEnabled(false);
             }else{
                 saveButton.setEnabled(true);
             }
             // setting personal proportion by selected index
             switch(personalTypeComboBox.getSelectedIndex()){
                 case 0 : 
                     proportionTextField.setText(""+Main.managerProportion);
                     proportionTextField.setEnabled(false);
                     break;
                 case 1 :
                     proportionTextField.setText(""+Main.expertProportion);
                     proportionTextField.setEnabled(false);
                     break;
                 case 2 : 
                     proportionTextField.setText(""+Main.consultantProportion);
                     proportionTextField.setEnabled(false);
                     break;
                 case 3 : 
                     proportionTextField.setText(""+Main.otherProportion);
                     proportionTextField.setEnabled(true);
                     break;
                 default : 
                     break;
             }
         });
//         
         proportionTextField =  new JTextField();
         proportionTextField.setFont(new Font("Arial", 1, 13));
         proportionTextField.setHorizontalAlignment(0);
         proportionTextField.setEnabled(false);
         proportionTextField.setPreferredSize(new Dimension((int) (panelWidth*0.1), (int) (panelHeight*0.7)));
         add(proportionTextField);
         switch(getPersonalType(file)){
             case 0 : proportionTextField.setText(Main.managerProportion+""); break;
             case 1 : proportionTextField.setText(Main.expertProportion+""); break;
             case 2 : proportionTextField.setText(Main.consultantProportion+""); break;
             case 3 : 
                 proportionTextField.setText(getPersonalPropotion(file)+""); 
                 proportionTextField.setEnabled(true);
                 break;
             default : break;
         }
         
         proportionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if((e.getKeyChar() < '0') || (e.getKeyChar() > '9')){
                    if(e.getKeyChar() != '.'){
                        e.consume();
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                if(proportionTextField.getText().contains(".")){
                    if(e.getKeyChar() == '.'){
                        e.consume();
                    }
                }
                if(proportionTextField.getText().length() > 3){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }

                @Override
                public void keyReleased(KeyEvent e) {
                if(proportionTextField.getText().equals(""+getPersonalPropotion(file))){
                    saveButton.setEnabled(false);
                }else{
                    saveButton.setEnabled(true);
                }
                }
            
        });
         proportionTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                proportionTextField.selectAll();
            }
             @Override
            public void focusLost(FocusEvent e) {
                if(proportionTextField.getText().equals("")){
                    proportionTextField.setText(getPersonalPropotion(file)+"");
                }
            }
        });
    
         // this panel created for  making button close each other
         JPanel buttonPanel = new JPanel(null);
         buttonPanel.setPreferredSize(new Dimension((int) (panelWidth*0.25), (int) (panelHeight*0.8)));
         buttonPanel.setBackground(panelColor); 
         add(buttonPanel);
         
         // icons for save button
         ImageIcon saveIcon = new ImageIcon("icons/saveIcon.png");
         Image fixSaveIcon = saveIcon.getImage().getScaledInstance((int) (buttonPanel.getPreferredSize().height*0.7), (int) (buttonPanel.getPreferredSize().height*0.7), 4);
         saveIcon.setImage(fixSaveIcon);
   
         // this button is saving setting if there enterd properly
         saveButton = new JButton(saveIcon);
         saveButton.setSize(new Dimension((int) (buttonPanel.getPreferredSize().width*0.33), (int) (buttonPanel.getPreferredSize().height)));
         saveButton.setLocation((int) (buttonPanel.getPreferredSize().width*0.25), 0);
         saveButton.setEnabled(false);
         buttonPanel.add(saveButton);
    
         saveButton.addActionListener((ActionEvent e) -> {
             saveButtonAction(e, file) ;
         });
         
         // icon for delete button
         ImageIcon deleteIcon = new ImageIcon("icons/deleteIcon.png");
         Image fixDeleteIcon = deleteIcon.getImage().getScaledInstance((int) (buttonPanel.getPreferredSize().height*0.7), (int) (buttonPanel.getPreferredSize().height*0.7), 4);
         deleteIcon.setImage(fixDeleteIcon);
   
         // this button created for deleting current personal information
         deleteButton = new JButton(deleteIcon);
         deleteButton.setSize(new Dimension((int) (buttonPanel.getPreferredSize().width*0.33), (int) (buttonPanel.getPreferredSize().height)));
         deleteButton.setLocation((int) (buttonPanel.getPreferredSize().width*0.6), 0);
         buttonPanel.add(deleteButton);
        
         deleteButton.addActionListener((ActionEvent e) -> {
             deleteButtonAction(file);
         });
         
        }
        
        
        private void saveButtonAction(ActionEvent e, File file){
            try {
                String name = personalNameTextField.getText();
                String prop = proportionTextField.getText();
               if(name.equals("") ){
                  JOptionPane.showMessageDialog(null, "Lütfen personel ismi belirtiniz", "Personel İsmi Girilmedi!", 2);
               }else{
                  if(prop.equals("") || prop.equals(".") || prop.equals("0") || prop.equals("0.0")){
                      JOptionPane.showMessageDialog(null, "Lütfen personel hedef oranını belirtiniz", "Personel Hedef Oranı Girilmedi!", 2);
                  }else{
                      File newFile = new File(file.getParent() + name);
                      file.renameTo(newFile);
                      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                      bw.write(Integer.toString(personalTypeComboBox.getSelectedIndex()));
                      bw.newLine();
                      bw.write(prop);
                      bw.close();
                      saveButton.setEnabled(false);
                  }
               }
            } catch (HeadlessException | IOException ex) {
                System.err.println("Error in saving current personal : " + e.getActionCommand());
            }
        }
        
        private void deleteButtonAction(File file){
            try {
                int choose = JOptionPane.showConfirmDialog(null, file.getName().replace(".ini", "") + " Personelini silmek istediğinize emin misiniz ?", 
                        "Personel Silme İşlemi", 0);
                System.out.println(choose);
                if(choose == 0){
                    if(file.delete()){
                        center.removeAll();
                        center.updateUI();
                        createNewList(shopNumber);
                        center.add(newPersonalButton());
                    }else{
                        JOptionPane.showMessageDialog(null, "Bilinmeyen bir nedenen dolayı personel silinemedi", "Hata!", 0);
                    }
                }else{
                    Toolkit.getDefaultToolkit().beep();
                }
            } catch (HeadlessException e) {
                System.err.println("Error in deleting  current personal : " + e.getLocalizedMessage());
            }
        }
        
    } // end of personal class
    
    
    
    
    
    // this class returns a jpanel for creating new personal infmations
    class NewPersonalPanel extends JPanel{
       
    private JTextField proportionTextField;
    private JComboBox personalTypeBox;
    private JButton  saveButton, cancelButton; 
    private int panelWidth, panelHeight;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public NewPersonalPanel() {
    try {
        // setting width and height
        panelWidth = centerWidth;
        panelHeight = (int) (centerHeight*0.08);
        setPreferredSize( new Dimension(panelWidth, panelHeight));
        
        setLayout(new FlowLayout(FlowLayout.LEADING, (int) (panelWidth*0.02), (int) (panelHeight*0.15)));
        setBackground(new Color(0, 128, 64));
        
        // this textfields created for entering new personals name
        personalNameTextField = new JTextField("Personel adı");
        personalNameTextField.setFont(new Font("Arial", 1, 13));
        personalNameTextField .setForeground(Color.gray);
        personalNameTextField.setPreferredSize(new Dimension((int) (panelWidth*0.3), (int) (panelHeight*0.7)));
        add(personalNameTextField);

        personalNameTextField .addKeyListener(new KeyAdapter(){
          public void KeyTyped(KeyEvent ke){
             if(personalNameTextField.getText().endsWith(" ")){
                 if(ke.getKeyChar()== ' '){
                    ke.consume();
                    Toolkit.getDefaultToolkit().beep();
                 }
             }
             if(personalNameTextField.getText().length() < 2){
                if(ke.getKeyChar() == ' '){
                    ke.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
    });

    personalNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 if(personalNameTextField.getText().equals( "Personel adı" )){
                    personalNameTextField.setText("");
                 }else{
                    personalNameTextField.selectAll();
                 }
            personalNameTextField.setForeground(Color.black);
           }
            @Override
            public void focusLost(FocusEvent e) {
                if(personalNameTextField.getText().equals("Personel adı") || personalNameTextField.getText().equals("")){
                    personalNameTextField.setForeground(Color.gray);
                    personalNameTextField.setText("Personel adı");
                }
            }
   });
   String[] personalTypes = {"Satış Yöneticisi", "Uzman Satış Danışmanı", "Satış Danışmanı", "Diğer" };
    
   // this combobox created for selecting personal type
   personalTypeBox = new JComboBox(personalTypes);
   personalTypeBox.setPreferredSize(new Dimension((int) (panelWidth*0.25), (int) (panelHeight*0.7)));
   personalTypeBox.setSelectedIndex(3);
   add(personalTypeBox);
   
   // if personal type is other proportion textfield will be able to write othervise   not
   personalTypeBox.addActionListener((ActionEvent e) -> {
     switch(personalTypeBox.getSelectedIndex()){
	case 0 : 
	  proportionTextField.setEnabled(false);
	  proportionTextField.setText(Double.toString(Main.managerProportion)); 
 	  break;
 	case 1 : 
 	  proportionTextField.setEnabled(false);
	  proportionTextField.setText(Double.toString(Main.expertProportion));
	  break;
	case 2 : 
 	  proportionTextField.setEnabled(false);
	  proportionTextField.setText(Double.toString(Main.consultantProportion));
 	  break;
 	case 3 :
	  proportionTextField.setEnabled(true);
	  proportionTextField.setText(Double.toString(Main.otherProportion));
	  break;
	
   }
   });

   // this texfield created for entering personal proportion informations
   proportionTextField = new JTextField(Double.toString(Main.otherProportion));
   proportionTextField.setFont(new Font("Arial", 1, 13));
   proportionTextField.setHorizontalAlignment(0);
   proportionTextField.setPreferredSize(new Dimension((int) (panelWidth*0.1), (int) (panelHeight*0.7)));
   add(proportionTextField);

   proportionTextField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent fe){
	if(proportionTextField.getText().equals( "Personel adı" )){
	    personalNameTextField.setText("");
	}else{
	    personalNameTextField.selectAll();
	}

	personalNameTextField.setForeground(Color.black);
       }

      @Override
      public void focusLost(FocusEvent fe){
	if(personalNameTextField.getText().equals( "Personel adı" )){
	   personalNameTextField.setForeground(Color.gray);
	}
      }
   });
   
   JPanel buttonPanel = new JPanel(null);
   buttonPanel.setBackground(new Color(0, 128, 64));
   buttonPanel.setPreferredSize(new Dimension((int) (panelWidth*0.25), (int) (panelHeight*0.8)));
   add(buttonPanel);
        
    ImageIcon saveIcon = new ImageIcon("icons/saveIcon.png");
    Image fixSaveIcon = saveIcon.getImage().getScaledInstance((int) (buttonPanel.getPreferredSize().height*0.7), (int) (buttonPanel.getPreferredSize().height*0.7), 4);
    saveIcon.setImage(fixSaveIcon);         
        
   saveButton = new JButton(saveIcon);
   saveButton.setSize(new Dimension((int) (buttonPanel.getPreferredSize().width*0.33), (int) (buttonPanel.getPreferredSize().height)));
   saveButton.setLocation((int) (buttonPanel.getPreferredSize().width*0.25), 0);
//   saveButton.setEnabled(false);
   buttonPanel.add(saveButton);
   
   saveButton.addActionListener((ActionEvent e) -> {
       saveButtonAciton();
   });

   ImageIcon cancelIcon = new ImageIcon("icons/cancelIcon.png");
   Image fixCancelIcon = cancelIcon.getImage().getScaledInstance((int) (buttonPanel.getPreferredSize().height*0.7), (int) (buttonPanel.getPreferredSize().height*0.7), 4);
   cancelIcon.setImage(fixCancelIcon);
    
   cancelButton = new JButton(cancelIcon);
   cancelButton.setSize(new Dimension((int) (buttonPanel.getPreferredSize().width*0.33), (int) (buttonPanel.getPreferredSize().height)));
   cancelButton.setLocation((int) (buttonPanel.getPreferredSize().width*0.6), 0);
   buttonPanel.add(cancelButton);
   
   cancelButton.addActionListener((ActionEvent e) -> {
       cancelButtonAciton(e);
   });
    }catch (Exception e) {
        System.err.println("Error in creating new personal panel : " + e.getLocalizedMessage());
    }
    personalNameTextField.requestFocus(true);
    }
    
    
    
    private void saveButtonAciton(){
         String name = personalNameTextField.getText();
         int type = personalTypeBox.getSelectedIndex();
         String prop = proportionTextField.getText();
         
         // if name isn' t given this message will shop up
        if(name.equals("") || name.equals("Personel adı")){
            JOptionPane.showMessageDialog(null, "Yeni personel için bir isim girmelisiniz.", "İsim girilmedi!", 2);
        }else {// if name is gived else block will work
          if(prop.equals("") || prop.equals("0.0") || prop.equals("0") || prop.equals(".")){
            JOptionPane.showMessageDialog(null, "Yeni personel için hedef yüzdesi girmelisiniz", "Hedef Yüzdesi Girilmesi!", 2);
          }else{
              try {
                  File file = new File("Personals/shop"+ shopNumber + "/" + name + ".ini" );
                  System.out.println(file.exists());
                  if(file.createNewFile()){
                      FileWriter fw = new FileWriter(file);
                      BufferedWriter br = new BufferedWriter(fw);
                      br.write(Integer.toString(type));
                      br.newLine();
                      br.write(proportionTextField.getText());
                      br.close();
                      center.removeAll();
                      center.updateUI();
                      createNewList(shopNumber);
                      center.add(newPersonalButton());
                  }else{
                      JOptionPane.showMessageDialog(null, "Bilinmeyen bir nedenle dosya oluşturulamadı.", "Dosya Oluşturulamadı", 0);
                  }
              } catch (IOException e) {
                  System.err.println("Error in saving new personal");
              }
          }  
        }
    }
    private void cancelButtonAciton(ActionEvent e){
        center.removeAll();
        center.updateUI();
        createNewList(shopNumber);
        center.add(newPersonalButton());
        Toolkit.getDefaultToolkit().beep();
    }
} // end new personal class

} // end of shopSetting class
