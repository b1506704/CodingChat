/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui;

import com.socket.Message;
import com.socket.SocketClient;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import keeptoo.Drag;

/**
 *
 * @author TsundereMoe
 */
public class LoginRegister extends javax.swing.JFrame {

     /**
     * Creates new form LoginRegister
     */
    public SocketClient client;
    private int port=13000;
    private String serverAddr;
    private String username;
    private String password;
    public Thread clientThread;
   // public Dashboard dashBoard;
    public LoginRegister() {
        initComponents();
        serverAddr = this.txtServerIP.getText();
        
    }
    
     /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @param serverAddr the serverAddr to set
     */
    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @return the serverAddr
     */
    public String getServerAddr() {
        return serverAddr;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    //set font for an element of JComponent
    public void applyFont(javax.swing.JComponent e, String fontDir, float fontSize){
        try {
            InputStream is = LoginRegister.class.getResourceAsStream(fontDir);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            e.setFont(font.deriveFont(fontSize));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(LoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void keyBinding() {
        //must include this
        this.getRootPane().setDefaultButton(this.btnLogin);
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        //-----------------
        this.btnLogin.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                "login");
        Action login = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        this.btnLogin.getActionMap().put("login",
                login);
    }
    
    //set initSetting for the whole frame
    public static void initSetting(){
        try {
            //set background
            LoginRegister loginForm = new LoginRegister();
            loginForm.setBackground(new Color(0,0,0,0));
            loginForm.loginRegisterPanel.setBorder(null);
            loginForm.loginRegisterPanel.setkStartColor(new Color(51, 100, 51, 40));
            loginForm.loginRegisterPanel.setkEndColor(new Color(51, 100, 51, 40));
            loginForm.loginRegisterPanel.setOpaque(false);
            loginForm.txtIDPanel.setkFillBackground(false);
            loginForm.txtPasswordPanel.setkFillBackground(false);
            loginForm.txtID.setBorder(null);
            loginForm.txtID.setBackground(new Color(0,0,0,0));
            loginForm.txtPassword.setBorder(null);
            loginForm.txtPassword.setBackground(new Color(0,0,0,0));
            
            //render and change icon of a button
            loginForm.keyBinding();
            
            //apply fonts
            loginForm.applyFont(loginForm.lblTitle1,"Vanessas Valentine.otf",70f);
            loginForm.applyFont(loginForm.lblTitle2,"Vanessas Valentine.otf",70f);
            loginForm.applyFont(loginForm.lblID,"FVF Fernando 08.ttf",14f);
            loginForm.applyFont(loginForm.lblPassword,"FVF Fernando 08.ttf",14f);
            loginForm.applyFont(loginForm.txtID,"FVF Fernando 08.ttf",17f);
            loginForm.applyFont(loginForm.txtPassword,"FVF Fernando 08.ttf",17f);
            loginForm.applyFont(loginForm.btnLogin,"VL COCO.OTF",25f);
            loginForm.applyFont(loginForm.btnRegister,"VL COCO.OTF",25f);
            loginForm.applyFont(loginForm.btnHidePass,"FVF Fernando 08.ttf",8f);
            loginForm.applyFont(loginForm.btnLogout, "Vanessas Valentine.otf",40F);
            //show frame
            loginForm.setVisible(true);
            loginForm.txtID.requestFocus();
        } catch (Exception ex) {
            Logger.getLogger(LoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround = new keeptoo.KGradientPanel();
        btnLogout = new keeptoo.KButton();
        lblTitle2 = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();
        loginRegisterPanel = new keeptoo.KGradientPanel();
        lblID = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnHidePass = new keeptoo.KButton();
        btnLogin = new keeptoo.KButton();
        btnRegister = new keeptoo.KButton();
        txtIDPanel = new keeptoo.KGradientPanel();
        txtID = new javax.swing.JTextField();
        txtPasswordPanel = new keeptoo.KGradientPanel();
        txtPassword = new javax.swing.JPasswordField();
        txtServerIP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("loginRegisterForm"); // NOI18N
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        backGround.setFont(new java.awt.Font("Viner Hand ITC", 0, 11)); // NOI18N
        backGround.setkBorderRadius(100);
        backGround.setkEndColor(new java.awt.Color(255, 153, 153));
        backGround.setkStartColor(new java.awt.Color(0, 51, 102));
        backGround.setOpaque(false);

        btnLogout.setBackground(new java.awt.Color(255, 153, 153));
        btnLogout.setBorder(null);
        btnLogout.setForeground(new java.awt.Color(102, 255, 102));
        btnLogout.setText("X");
        btnLogout.setkBorderRadius(200);
        btnLogout.setkFillButton(false);
        btnLogout.setkForeGround(new java.awt.Color(255, 255, 153));
        btnLogout.setkHoverColor(new java.awt.Color(255, 0, 255));
        btnLogout.setkHoverEndColor(new java.awt.Color(204, 255, 255));
        btnLogout.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnLogout.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnLogout.setkIndicatorColor(new java.awt.Color(15, 255, 0));
        btnLogout.setkIndicatorThickness(0);
        btnLogout.setkPressedColor(new java.awt.Color(255, 153, 153));
        btnLogout.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnLogout.setkStartColor(new java.awt.Color(255, 153, 153));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblTitle2.setFont(new java.awt.Font("OCR A Extended", 0, 48)); // NOI18N
        lblTitle2.setForeground(new java.awt.Color(255, 255, 102));
        lblTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle2.setLabelFor(this);
        lblTitle2.setText(" Chat");
        lblTitle2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lblTitle1.setFont(new java.awt.Font("OCR A Extended", 0, 48)); // NOI18N
        lblTitle1.setForeground(new java.awt.Color(255, 255, 102));
        lblTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1.setLabelFor(this);
        lblTitle1.setText("Coding");
        lblTitle1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        loginRegisterPanel.setkBorderRadius(100);

        lblID.setForeground(new java.awt.Color(255, 255, 0));
        lblID.setText("ID");

        lblPassword.setForeground(new java.awt.Color(255, 255, 0));
        lblPassword.setText("Password");
        lblPassword.setToolTipText("6-14 characters");

        btnHidePass.setBorder(null);
        btnHidePass.setForeground(new java.awt.Color(102, 255, 102));
        btnHidePass.setText("Unhide");
        btnHidePass.setFont(new java.awt.Font("UD Digi Kyokasho NK-B", 0, 11)); // NOI18N
        btnHidePass.setkBorderRadius(200);
        btnHidePass.setkForeGround(new java.awt.Color(255, 255, 153));
        btnHidePass.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnHidePass.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnHidePass.setkStartColor(new java.awt.Color(255, 102, 153));
        btnHidePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHidePassActionPerformed(evt);
            }
        });

        btnLogin.setBorder(null);
        btnLogin.setForeground(new java.awt.Color(102, 255, 102));
        btnLogin.setText("Login");
        btnLogin.setFont(new java.awt.Font("UD Digi Kyokasho NK-B", 0, 11)); // NOI18N
        btnLogin.setkBorderRadius(200);
        btnLogin.setkForeGround(new java.awt.Color(255, 255, 153));
        btnLogin.setkHoverColor(new java.awt.Color(255, 0, 255));
        btnLogin.setkHoverEndColor(new java.awt.Color(204, 255, 255));
        btnLogin.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnLogin.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnLogin.setkStartColor(new java.awt.Color(255, 102, 153));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setBorder(null);
        btnRegister.setForeground(new java.awt.Color(102, 255, 102));
        btnRegister.setText("Register");
        btnRegister.setFont(new java.awt.Font("UD Digi Kyokasho NK-B", 0, 11)); // NOI18N
        btnRegister.setkBorderRadius(200);
        btnRegister.setkForeGround(new java.awt.Color(255, 255, 153));
        btnRegister.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        btnRegister.setkStartColor(new java.awt.Color(255, 102, 153));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        txtIDPanel.setkBorderRadius(100);
        txtIDPanel.setkEndColor(new java.awt.Color(255, 255, 51));
        txtIDPanel.setkStartColor(new java.awt.Color(255, 255, 255));
        txtIDPanel.setOpaque(false);

        txtID.setBackground(new java.awt.Color(204, 204, 255));
        txtID.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N

        javax.swing.GroupLayout txtIDPanelLayout = new javax.swing.GroupLayout(txtIDPanel);
        txtIDPanel.setLayout(txtIDPanelLayout);
        txtIDPanelLayout.setHorizontalGroup(
            txtIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtIDPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        txtIDPanelLayout.setVerticalGroup(
            txtIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtIDPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtPasswordPanel.setkBorderRadius(100);
        txtPasswordPanel.setkEndColor(new java.awt.Color(255, 255, 0));
        txtPasswordPanel.setkStartColor(new java.awt.Color(255, 255, 255));
        txtPasswordPanel.setOpaque(false);

        txtPassword.setBackground(new java.awt.Color(204, 204, 255));
        txtPassword.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N

        javax.swing.GroupLayout txtPasswordPanelLayout = new javax.swing.GroupLayout(txtPasswordPanel);
        txtPasswordPanel.setLayout(txtPasswordPanelLayout);
        txtPasswordPanelLayout.setHorizontalGroup(
            txtPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPasswordPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        txtPasswordPanelLayout.setVerticalGroup(
            txtPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtPasswordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout loginRegisterPanelLayout = new javax.swing.GroupLayout(loginRegisterPanel);
        loginRegisterPanel.setLayout(loginRegisterPanelLayout);
        loginRegisterPanelLayout.setHorizontalGroup(
            loginRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(loginRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                        .addComponent(lblID)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                        .addGroup(loginRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIDPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                            .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtPasswordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHidePass, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))))
        );
        loginRegisterPanelLayout.setVerticalGroup(
            loginRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIDPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPassword)
                .addGroup(loginRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnHidePass, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginRegisterPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(loginRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        javax.swing.GroupLayout backGroundLayout = new javax.swing.GroupLayout(backGround);
        backGround.setLayout(backGroundLayout);
        backGroundLayout.setHorizontalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGroundLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(loginRegisterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
            .addGroup(backGroundLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addComponent(lblTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtServerIP, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        backGroundLayout.setVerticalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGroundLayout.createSequentialGroup()
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backGroundLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(txtServerIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(loginRegisterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 26, Short.MAX_VALUE)
                .addComponent(backGround, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        new Drag(this.rootPane).onPress(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        new Drag(this.rootPane).moveWindow(evt);
        
    }//GEN-LAST:event_formMouseDragged

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        //add ID and Password to Socket and database
        if (!txtID.getText().equals("") && !txtPassword.getText().equals("")) {
                    setUsername(txtID.getText().trim());
                    setPassword(txtPassword.getText().trim());
                    //==> Socket code here
                    if(!username.isEmpty() && !password.isEmpty()){
                       try{
                             client = new SocketClient(this);
                             clientThread = new Thread(client);
                             clientThread.start();
                             client.send(new Message("signup", getUsername(), getPassword(), "SERVER"));
                        }
                       catch(Exception ex){
                           
                        }
                } 
        } else {
            JOptionPane.showMessageDialog(rootPane, "Please check input!");
          }
        
    }//GEN-LAST:event_btnRegisterActionPerformed
    public void showLoginInformation(){
        JOptionPane.showMessageDialog(rootPane, "Login failed! Please check username or password");
    
    }
    public void showRegisterInformation(){
        JOptionPane.showMessageDialog(rootPane, "Register failed!\n Please check username or password");
    
    }
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // get ID and password input to check with Socket and database
        if (!txtID.getText().equals("") && !txtPassword.getText().equals("")) {
                    setUsername(txtID.getText().trim());
                    setPassword(txtPassword.getText().trim());
                    //==> Socket code here
                    if(!username.isEmpty() && !password.isEmpty()){
                        try{
                            client = new SocketClient(this);
                            clientThread = new Thread(client);
                            clientThread.start();
                            client.send(new Message("login", username, password, "SERVER"));
                        }
                        catch(Exception ex){
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please check input!");
                }
    }//GEN-LAST:event_btnLoginActionPerformed
    int count=0;
    private void btnHidePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHidePassActionPerformed
        // unhide and hide password with count
        if (count % 2==0) {
            this.txtPassword.setEchoChar((char)0);
        } else {
            this.txtPassword.setEchoChar('*');
        }
        count++;
    }//GEN-LAST:event_btnHidePassActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        try{ 
            client.send(new Message("message", username, ".bye", "SERVER")); 
            clientThread.stop();
            System.exit(0);
        } catch (Exception e){
            
        }
        
        
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                initSetting();
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel backGround;
    private keeptoo.KButton btnHidePass;
    private keeptoo.KButton btnLogin;
    private keeptoo.KButton btnLogout;
    private keeptoo.KButton btnRegister;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblTitle2;
    private keeptoo.KGradientPanel loginRegisterPanel;
    private javax.swing.JTextField txtID;
    private keeptoo.KGradientPanel txtIDPanel;
    private javax.swing.JPasswordField txtPassword;
    private keeptoo.KGradientPanel txtPasswordPanel;
    private javax.swing.JTextField txtServerIP;
    // End of variables declaration//GEN-END:variables

   
}
