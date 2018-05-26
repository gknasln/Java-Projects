/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hedefhesaplama;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author gknas
 */
public class SettingsFrame extends  JFrame{
    private int width, height;
    private JTextField managerProportionTextField, expertProportionTextField,
            consultantProportionTextField, otherProportionTextField;
    private JButton saveManagerProportion, saveExpertProportion, saveConsultantProportion,
            saveOtherProportion;
public static void main(String[] args){
    try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
    }
    new SettingsFrame();
}

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public SettingsFrame() throws HeadlessException {
try {
    setTitle("Program Ayarları");
   
    setIconImage(new ImageIcon("icons/simge.png").getImage());
    
    width  = Toolkit.getDefaultToolkit().getScreenSize().width;
    height = Toolkit.getDefaultToolkit().getScreenSize().height;
    width = (int) (width*0.27);
    height = (int) (height*0.5);
    
    setSize(width, height);
    setLocationRelativeTo(null);
    
    JTabbedPane pane = new JTabbedPane();
    pane.setFont(new Font("Arial", 1, 12));
    JPanel tab1 = new personalProportionPanel(width, (int) (height*0.7));
    tab1.setLayout(null);
    
    JPanel tab2 = new savingOptionPanel(width, height);
    
    JPanel tab3 = new writingOptionPanel(width, height);
    
    pane.addTab("Varsayılan personel yüzdeleri", tab1);
    pane.addTab("Kaydetme seçenekleri", tab2);
    pane.addTab("Yazdırma Ayarları", tab3);
    add(pane);
    
    
    
    KeyStroke closeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
    Action closeAciton = new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }};
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(closeKeyStroke, "ESCAPE");
    getRootPane().getActionMap().put("ESCAPE", closeAciton);
    
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
} catch (HeadlessException e) {
}
}
    
    class personalProportionPanel extends JPanel{

        @SuppressWarnings("OverridableMethodCallInConstructor")
        public personalProportionPanel(int width, int height) {
        
    JLabel managerProportionDirection = new JLabel("Satış yöneticisi varsayılan yüzde değeri : ");
    managerProportionDirection.setSize((int) (width*0.9), (int) (height*0.05));
    managerProportionDirection.setLocation((int) (width*0.06), (int) (height*0.09));
    add(managerProportionDirection);
    
    
    
    managerProportionTextField = textField(getPersonalProportion("manager"));
    managerProportionTextField.setSize((int) (width*0.4), (int) (height*0.08));
    managerProportionTextField.setLocation((int) (width*0.06), (int) (height*0.15));
    add(managerProportionTextField);
    
    managerProportionTextField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            managerProportionTextField.selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(managerProportionTextField.getText().equals("")){
                managerProportionTextField.setText(getPersonalProportion("manager"));
                saveManagerProportion.setEnabled(false);
            }
        }
    });
    
    managerProportionTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if(managerProportionTextField.getText().equals(getPersonalProportion("manager"))){
                saveManagerProportion.setEnabled(false);
            }else{
                saveManagerProportion.setEnabled(true);
            }
        }
        
    });
    
    
    saveManagerProportion = new JButton("Kaydet");
    saveManagerProportion.setSize((int) (width*0.17), (int) (height*0.08));
    saveManagerProportion.setLocation((int) (width*0.54), (int) (height*0.15));
    saveManagerProportion.setEnabled(false);
    add(saveManagerProportion);
    
    saveManagerProportion.addActionListener((e) -> {
        setPersonalProportion(managerProportionTextField.getText(), "manager");
        Main.managerProportion = Double.parseDouble(managerProportionTextField.getText());
        saveManagerProportion.setEnabled(false);
    });
    
    JLabel expertProportionDirection = new JLabel("Uzman satış danışmanı varsayılan yüzde değeri : ");
    expertProportionDirection.setSize((int) (width*0.9), (int) (height*0.05));
    expertProportionDirection.setLocation((int) (width*0.06), (int) (height*0.27));
    add(expertProportionDirection);
    
    expertProportionTextField = textField(getPersonalProportion("expert"));
    expertProportionTextField.setSize((int) (width*0.4), (int) (height*0.08));
    expertProportionTextField.setLocation((int) (width*0.064), (int) (height*0.33));
    add(expertProportionTextField);
    
    expertProportionTextField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            expertProportionTextField.selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(expertProportionTextField.getText().equals("")){
                expertProportionTextField.setText(getPersonalProportion("expert"));
                saveExpertProportion.setEnabled(false);
            }
        }
    });
    
    expertProportionTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if(expertProportionTextField.getText().equals(getPersonalProportion("expert"))){
                saveExpertProportion.setEnabled(false);
            }else{
                saveExpertProportion.setEnabled(true);
            }
        }
        
    });
    
    
    saveExpertProportion = new JButton("Kaydet");
    saveExpertProportion.setSize((int) (width*0.17), (int) (height*0.08));
    saveExpertProportion.setLocation((int) (width*0.54), (int) (height*0.33));
    saveExpertProportion.setEnabled(false);
    add(saveExpertProportion);
    
    
    saveExpertProportion.addActionListener((e) -> {
        setPersonalProportion(expertProportionTextField.getText(), "expert");
        Main.expertProportion = Double.parseDouble(expertProportionTextField.getText());
        saveExpertProportion.setEnabled(false);
    });
    
    
    JLabel consultantProportionDirection = new JLabel("Satış danışmanı varsayılan yüzde değeri : ");
    consultantProportionDirection.setSize((int) (width*0.9), (int) (height*0.05));
    consultantProportionDirection.setLocation((int) (width*0.06), (int) (height*0.44));
    add(consultantProportionDirection);
    
    consultantProportionTextField = textField(getPersonalProportion("consultant"));
    consultantProportionTextField.setSize((int) (width*0.4), (int) (height*0.08));
    consultantProportionTextField.setLocation((int) (width*0.06), (int) (height*0.49));
    add(consultantProportionTextField);
    
    consultantProportionTextField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            consultantProportionTextField.selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(consultantProportionTextField.getText().equals("")){
                consultantProportionTextField.setText(getPersonalProportion("consultant"));
                saveConsultantProportion.setEnabled(false);
            }
        }
    });
    
    consultantProportionTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if(consultantProportionTextField.getText().equals(getPersonalProportion("consultant"))){
                saveConsultantProportion.setEnabled(false);
            }else{
                saveConsultantProportion.setEnabled(true);
            }
        }
        
    });
    
    
    saveConsultantProportion = new JButton("Kaydet");
    saveConsultantProportion.setSize((int) (width*0.17), (int) (height*0.08));
    saveConsultantProportion.setLocation((int) (width*0.54), (int) (height*0.49));
    saveConsultantProportion.setEnabled(false);
    add(saveConsultantProportion);
    
    saveConsultantProportion.addActionListener((e) -> {
        setPersonalProportion(consultantProportionTextField.getText(), "consultant");
        Main.consultantProportion = Double.parseDouble(consultantProportionTextField.getText());
        saveConsultantProportion.setEnabled(false);
    });
    
    JLabel otherProportionDirection = new JLabel("Diğer personeller için varsayılan yüzde değeri : ");
    otherProportionDirection.setSize((int) (width*0.9), (int) (height*0.05));
    otherProportionDirection.setLocation((int) (width*0.06), (int) (height*0.6));
    add(otherProportionDirection);
    
    otherProportionTextField = textField(getPersonalProportion("other"));
    otherProportionTextField.setSize((int) (width*0.4), (int) (height*0.08));
    otherProportionTextField.setLocation((int) (width*0.06), (int) (height*0.66));
    add(otherProportionTextField);
    
    otherProportionTextField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            otherProportionTextField.selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(otherProportionTextField.getText().equals("")){
                otherProportionTextField.setText(getPersonalProportion("other"));
                saveOtherProportion.setEnabled(false);
            }
        }
    });
    
    otherProportionTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if(otherProportionTextField.getText().equals(getPersonalProportion("other"))){
                saveOtherProportion.setEnabled(false);
            }else{
                saveOtherProportion.setEnabled(true);
            }
        }
        
    });
    
    
    saveOtherProportion = new JButton("Kaydet");
    saveOtherProportion.setSize((int) (width*0.17), (int) (height*0.08));
    saveOtherProportion.setLocation((int) (width*0.54), (int) (height*0.66));
    saveOtherProportion.setEnabled(false);
    add(saveOtherProportion);
    
    saveOtherProportion.addActionListener((e) -> {
        setPersonalProportion(otherProportionTextField.getText(), "other");
        Main.otherProportion = Double.parseDouble(otherProportionTextField.getText());
        saveOtherProportion.setEnabled(false);
    });
    
    
        
        
        }
        
        
        
    }
    
    
    class savingOptionPanel extends JPanel{
        private JComboBox savingOption;
        private JTextField pathTextField;
        private JButton savePathButton;
        @SuppressWarnings("OverridableMethodCallInConstructor")
        public savingOptionPanel(int width, int height) {
            setLayout(null);
            
            JLabel savingOptionsDirection = new JLabel("Hesaplanan sonuçların kaydedileceği alan");
            savingOptionsDirection.setSize((int) (width*0.9), (int) (height*0.05));
            savingOptionsDirection.setLocation((int) (width*0.1), (int) (height*0.1));
            add(savingOptionsDirection);
            
            
            String[] savingOptions = { "Masaüstü", "Belgeler", "Diğer" };
            
            savingOption = new JComboBox(savingOptions);
            savingOption.setSelectedIndex(getSavePathOption());
            savingOption.setSize((int) (width*0.6), (int) (height*0.07));
            savingOption.setLocation((int) (width*0.06), (int) (height*0.15));
            add(savingOption);
            
            savingOption.addActionListener((e) -> {
                setSavePathOption(savingOption.getSelectedIndex());
                switch(savingOption.getSelectedIndex()){
                    case 0 : 
                        setSavePath(System.getProperty("user.home") + "/Desktop");
                        Main.path = System.getProperty("user.home") + "/Desktop";
                        pathTextField.setVisible(false);
                        savePathButton.setVisible(false);
                        break;
                    case 1 : 
                        setSavePath(System.getProperty("user.home"));
                        Main.path = System.getProperty("user.home");
                        pathTextField.setVisible(false);
                        savePathButton.setVisible(false);
                        break;
                    case 2 :
                        pathTextField.setVisible(true);
                        savePathButton.setVisible(true);
                        pathTextField.setText(getSavePath());
                }
            });
            
            pathTextField = new JTextField(getSavePath());
            pathTextField.setSize((int) (width*0.7), (int) (height*0.07));
            pathTextField.setLocation((int) (width*0.04), (int) (height*0.26));
            add(pathTextField);
             
            pathTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                pathTextField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(pathTextField.getText().equals("")){
                    pathTextField.setText(getSavePath());
                }
            }
        });
    
        pathTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if(pathTextField.getText().equals(getSavePath())){
                savePathButton.setEnabled(false);
            }else{
                savePathButton.setEnabled(true);
            }
        }
        });
    
        savePathButton = new JButton("Kaydet");
        savePathButton.setSize((int) (width*0.17), (int) (height*0.07));
        savePathButton.setLocation((int) (width*0.76), (int) (height*0.26));
        savePathButton.setEnabled(false);
        add(savePathButton);
        if(getSavePathOption() != 2){
            pathTextField.setVisible(false);
            savePathButton.setVisible(false);
        }
        savePathButton.addActionListener((e) -> {
            setSavePath(pathTextField.getText());
            savePathButton.setEnabled(false);
        });
        }
    }
    
    class writingOptionPanel extends JPanel{
        private JCheckBox personalType, proportion;
        public writingOptionPanel(int width, int height) {
            try {
                setLayout(null);
                personalType = new JCheckBox("Personel statüsü bilgisi", getPersonalTypeOption());
                personalType.setFont(new Font("Arial", 0, 13));
                personalType.setSize((int) (width*0.5), (int) (height*0.07));
                personalType.setLocation((int) (width*0.1), (int) (height*0.2));
                add(personalType);
                
                personalType.addActionListener((e) -> {
                    if(personalType.isSelected()){
                        setPersonelTypeOption(true);
                    }else{
                        setPersonelTypeOption(false);
                    }
                });
                
                proportion = new JCheckBox("Personel çarpanı bilgisi", getProportionOption());
                proportion.setSize((int) (width*0.5), (int) (height*0.07));
                proportion.setSize((int) (width*0.5), (int) (height*0.07));
                proportion.setLocation((int) (width*0.1), (int) (height*0.27));
                add(proportion);
                
                proportion.addActionListener((e) -> {
                    if(proportion.isSelected()){
                        setProportionOption(true);
                    }else{
                        setProportionOption(false);
                    }
                });
            } catch (Exception e) {
                System.err.println("Error in writing options panel : " + e.getLocalizedMessage());
            }
        }
    }
    private boolean getPersonalTypeOption(){
        boolean b = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Options/personalTypeOption.ini")));
            b = Boolean.parseBoolean(br.readLine());
            br.close();
        } catch (IOException e) {
            System.err.println("Error in getting personel type option in setting frame : " + e.getLocalizedMessage());
        }
        return b;
    }
    
    private void setPersonelTypeOption(boolean b){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Options/personalTypeOption.ini")));
            bw.write(Boolean.toString(b));
            bw.close();
        } catch (IOException e) {
            System.err.println("Error in saving personel type option in settings frame  : " + e.getLocalizedMessage());
        }
    }
    private boolean getProportionOption(){
        boolean b = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Options/proportionOption.ini")));
            b = Boolean.parseBoolean(br.readLine());
            br.close();
        } catch (IOException e) {
            System.err.println("Error in getting proportion option in setting frame : " + e.getLocalizedMessage());
        }
        return b;
    }
    
    private void setProportionOption(boolean b){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Options/proportionOption.ini")));
            bw.write(Boolean.toString(b));
            bw.close();
        } catch (IOException e) {
            System.err.println("Error in saving proportion option in settings frame  : " + e.getLocalizedMessage());
        }
    }
private int getSavePathOption(){
   int i = 0;
    try {
        BufferedReader br = new BufferedReader(new FileReader(new File("Options/savePathOption.ini")));
        i = Integer.parseInt(br.readLine());
        br.close();
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error in getting save path options : " + e.getLocalizedMessage());
    }
    return i;
}    

private void setSavePathOption(int index){
    try {
        BufferedWriter bw = new  BufferedWriter(new FileWriter(new File("Options/savePathOption.ini")));
        bw.write(Integer.toString(index));
        bw.close();
    } catch (IOException e) {
        System.err.println("error in saving save path index : " + e.getLocalizedMessage());
    }
}
    
private String getSavePath(){
    String path = null;
    try {
        BufferedReader br = new BufferedReader(new FileReader(new File("Options/savePath.ini")));
        path =  br.readLine();
        br.close();
        if(path == null){
            path = System.getProperty("user.home") + "/desktop";
        }
    } catch (IOException e) {
        System.err.println("Error in getting save path" + e.getLocalizedMessage());
    }
    return path;
}

private void setSavePath(String path){
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Options/savePath.ini")));
        bw.write(path);
        bw.close();
    } catch (IOException e) {
        System.err.println("Error in writing save path" + e.getLocalizedMessage());
    }
}
    
    
private String getPersonalProportion(String personalType){
    String proportion = "0.0";
    try {
        File file = new  File("Options/"+ personalType + "Proportion.ini");
        BufferedReader br = new BufferedReader(new FileReader(file));
        proportion = br.readLine();
        br.close();
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error in getting personal proportion :  " + e.getLocalizedMessage());
    }
    return proportion;
}

private void setPersonalProportion(String value, String personalType){
    try {
        File file = new  File("Options/"+ personalType + "Proportion.ini");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(value);
        bw.close();
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error in saving personal proportion :  " + e.getLocalizedMessage());
    }
}
private JTextField textField(String str){
    JTextField text = new JTextField(str);
    text.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if((e.getKeyChar() < '0') || (e.getKeyChar() > '9')){
                if((e.getKeyChar() != '.') && (e.getKeyChar() != ',')){
                e.consume();
                Toolkit.getDefaultToolkit().beep();
                }
            }
            if(text.getText().length() > 4){
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
            text.setText(text.getText().replace(",", "."));
            
            if(text.getText().contains(".") || text.getText().contains(",")){
                if(e.getKeyChar() == '.'|| e.getKeyChar() == ','){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                    
            }
        }
    });
    return text;
}
    
}
