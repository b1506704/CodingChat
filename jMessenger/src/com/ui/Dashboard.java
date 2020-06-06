/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import keeptoo.Drag;
import keeptoo.KButton;
import keeptoo.KGradientPanel;
/**
 *
 * @author TsundereMoe
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     *
     */
    public Dashboard() {
        initComponents();
    }
    public static ArrayList<JComponent> jComponent = new ArrayList<>();
    public static ArrayList<KButton> kComponent = new ArrayList<>();
    public void keyBinding() {
        //must include this
        this.getRootPane().setDefaultButton(this.btnSend);
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        //-----------------
        this.btnSend.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                "sendMessage");
        Action sendMessage = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(txtReply.getText()) && !"Please type in here....".equals(txtReply.getText())) {
                    messageTextArea.append(txtReply.getText() + "\n");
                    txtReply.setText("");
                    txtReply.requestFocus();
                }

            }
        };
        this.btnSend.getActionMap().put("sendMessage",
                sendMessage);
    }

    //set font for a certain control of JComponent
    public void applyFont(JComponent e, String fontDir, float fontSize) {
        try {
            InputStream is = LoginRegister.class.getResourceAsStream(fontDir);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            e.setFont(font.deriveFont(fontSize));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(LoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //apply Layout, a rounded panel and button with text

    /**
     *
     * @param motherEle đây là parentPanel để setLayout
     * @param sonEle đây là panel nằm trong motherEle và chứa button
     * grandChilEle
     * @param grandChildEle đây là Kbutton đẻ set thuộc tính
     * @param grandChildStartColor  
     * @param grandChildEndColor
     * @param grandChildForeground
     * @param grandChildHoverColor
     * @param grandChildHoverEndColor
     * @param grandChildHoverStartColor
     * @param grandChildHoverForeground
     * @param grandChildPressedColor
     * @param isAppendGrandChild
     * @param appendString
     * @param isAllowTab
     */
    //chua xai duoc ScrollPanel
    public void applyModernView(KGradientPanel motherEle, KGradientPanel sonEle, KButton grandChildEle, Color grandChildStartColor, Color grandChildEndColor, Color grandChildForeground, Color grandChildHoverColor, Color grandChildHoverEndColor, Color grandChildHoverStartColor, Color grandChildHoverForeground, Color grandChildPressedColor, boolean isAppendGrandChild, boolean isAllowTab, String appendString) {
            motherEle.setLayout(new BoxLayout(motherEle,BoxLayout.Y_AXIS ));
            motherEle.setOpaque(false);
            motherEle.setkFillBackground(false);
            motherEle.setBackground(new Color(0,0,0,0));
            motherEle.setBorder(null);
            //motherEle.setMaximumSize(new Dimension(225, 380));
            sonEle = new KGradientPanel();
            sonEle.setBackground(new Color(0, 0, 0, 0));
            sonEle.setkStartColor(Color.WHITE);
            sonEle.setkEndColor(Color.PINK);
            sonEle.setkFillBackground(false);
            sonEle.setkBorderRadius(50);
            sonEle.setMinimumSize(new Dimension(169, 50));
            sonEle.setMaximumSize(new Dimension(169, 50));
            sonEle.setPreferredSize(new Dimension(169, 50));
            sonEle.setOpaque(false);
            grandChildEle = new KButton();
            grandChildEle.setkBorderRadius(50);
            grandChildEle.setkFillButton(true);
            grandChildEle.setkEndColor(grandChildEndColor);
            grandChildEle.setkForeGround(grandChildForeground);
            grandChildEle.setkHoverColor(grandChildHoverColor);
            grandChildEle.setkHoverEndColor(grandChildHoverEndColor);
            grandChildEle.setkHoverStartColor(grandChildHoverStartColor);
            grandChildEle.setkHoverForeGround(grandChildHoverForeground);
            grandChildEle.setkStartColor(grandChildStartColor);
            grandChildEle.setkPressedColor(grandChildPressedColor);
            
            grandChildEle.setBorder(null);
            grandChildEle.setOpaque(true);
            grandChildEle.setkAllowTab(isAllowTab);
            
            if (isAppendGrandChild) {
                
             motherEle.add(sonEle);
             //friendListScrollPane.add(sonEle);
             sonEle.add(grandChildEle);
             grandChildEle.setText(appendString);

             motherEle.revalidate();
             motherEle.repaint();
            }
             
              
    }
    
    public static void initSetting() {
        try {
            Dashboard dashBoard = new Dashboard();
            //add JComponent and KComponent to a list for handle later
            jComponent.add(dashBoard.lblChangeFont);
            jComponent.add(dashBoard.lblChangeTheme);
            jComponent.add(dashBoard.lblCodeSnippet);
            jComponent.add(dashBoard.lblIsTyping);
            jComponent.add(dashBoard.lblUserName);
            jComponent.add(dashBoard.messageTextArea);
            kComponent.add(dashBoard.btnActiveIntell);
            kComponent.add(dashBoard.btnAddFile);
            kComponent.add(dashBoard.btnAddFriend);
            kComponent.add(dashBoard.btnBrowseFont);
            kComponent.add(dashBoard.btnConversation);
            kComponent.add(dashBoard.btnLogout);
            kComponent.add(dashBoard.btnReset);
            kComponent.add(dashBoard.btnSend);
            kComponent.add(dashBoard.btnShowIntell);
            //---------------------------------------------------------
            
            //set background
            dashBoard.setBackground(new Color(0, 0, 0, 0));
            dashBoard.backGround.setBackground(new Color(0, 0, 0, 0));
            dashBoard.conversation.setBackground(new Color(0,0,0,0));
            dashBoard.messageScrollPane.getViewport().setOpaque(false);
            dashBoard.friendList.setBackground(new Color(0, 0, 0, 0));
            dashBoard.messageTextArea.setBackground(new Color(0, 0, 0, 0));
            dashBoard.txtReply.setBackground(new Color(0, 0, 0, 0));
            dashBoard.replyPane.setBackground(new Color(0, 0, 0, 0));
            dashBoard.userPane.setBackground(new Color(0, 0, 0, 0));
            
            //apply font
            dashBoard.applyFont(dashBoard.btnSend, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.btnLogout, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.txtReply, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.btnConversation, "FVF Fernando 08.ttf", 9f);
            dashBoard.applyFont(dashBoard.btnReset, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.btnBrowseFont, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.btnActiveIntell, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.btnAddFile, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.btnShowIntell, "SVN-Hole Hearted.ttf", 14f);
            dashBoard.applyFont(dashBoard.messageTextArea, "SVN-Hole Hearted.ttf", 25f);
            dashBoard.applyFont(dashBoard.lblChangeTheme, "FVF Fernando 08.ttf", 9f);
            dashBoard.applyFont(dashBoard.lblChangeFont, "FVF Fernando 08.ttf", 9f);
            dashBoard.applyFont(dashBoard.lblCodeSnippet, "FVF Fernando 08.ttf", 9f);
            dashBoard.applyFont(dashBoard.lblUserName, "SVN-Hole Hearted.ttf", 15f);
            dashBoard.applyFont(dashBoard.lblIsTyping, "SVN-Hole Hearted.ttf", 15f);
            //set key binding for send button
            dashBoard.keyBinding();
            
            //show the whole frame
            dashBoard.setVisible(true);

        } catch (Exception ex) {
            
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
        conversation = new keeptoo.KGradientPanel();
        messageScrollPane = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        btnSend = new keeptoo.KButton();
        btnConversation = new keeptoo.KButton();
        replyPane = new keeptoo.KGradientPanel();
        txtReply = new javax.swing.JTextField();
        btnAddFriend = new keeptoo.KButton();
        userPane = new keeptoo.KGradientPanel();
        lblUserName = new javax.swing.JLabel();
        btnReset = new keeptoo.KButton();
        btnLogout = new keeptoo.KButton();
        changeThemePanel = new keeptoo.KGradientPanel();
        lblChangeTheme = new javax.swing.JLabel();
        btnSetRedBackground = new keeptoo.KButton();
        btnSetGreenBackground = new keeptoo.KButton();
        btnSetPinkBackground = new keeptoo.KButton();
        btnSetWhiteBackground = new keeptoo.KButton();
        btnSetTansparentBackground = new keeptoo.KButton();
        friendList = new keeptoo.KGradientPanel();
        lblIsTyping = new javax.swing.JLabel();
        changeFontPanel = new keeptoo.KGradientPanel();
        lblChangeFont = new javax.swing.JLabel();
        btnSetRedFont = new keeptoo.KButton();
        btnSetGreenFont = new keeptoo.KButton();
        btnSetPinkFont = new keeptoo.KButton();
        btnSetWhiteFont = new keeptoo.KButton();
        btnSetTansparentFont = new keeptoo.KButton();
        btnBrowseFont = new keeptoo.KButton();
        codeSnippetPanel = new keeptoo.KGradientPanel();
        lblCodeSnippet = new javax.swing.JLabel();
        btnShowIntell = new keeptoo.KButton();
        btnActiveIntell = new keeptoo.KButton();
        btnAddFile = new keeptoo.KButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("dashBoard"); // NOI18N
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

        backGround.setkBorderRadius(100);
        backGround.setkEndColor(new java.awt.Color(255, 153, 153));
        backGround.setkStartColor(new java.awt.Color(51, 255, 255));
        backGround.setOpaque(false);

        conversation.setBackground(new java.awt.Color(255, 0, 255));
        conversation.setkBorderRadius(30);
        conversation.setkEndColor(new java.awt.Color(153, 0, 153));
        conversation.setkFillBackground(false);
        conversation.setkStartColor(new java.awt.Color(0, 0, 0));
        conversation.setkTransparentControls(false);
        conversation.setOpaque(false);

        messageScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        messageScrollPane.setBorder(null);
        messageScrollPane.setForeground(new java.awt.Color(255, 255, 255));
        messageScrollPane.setFocusable(false);
        messageScrollPane.setOpaque(false);

        messageTextArea.setEditable(false);
        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        messageTextArea.setCaretColor(new java.awt.Color(204, 255, 204));
        messageTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        messageTextArea.setSelectionColor(new java.awt.Color(255, 255, 255));
        messageScrollPane.setViewportView(messageTextArea);

        javax.swing.GroupLayout conversationLayout = new javax.swing.GroupLayout(conversation);
        conversation.setLayout(conversationLayout);
        conversationLayout.setHorizontalGroup(
            conversationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conversationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageScrollPane)
                .addContainerGap())
        );
        conversationLayout.setVerticalGroup(
            conversationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conversationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSend.setText("Send");
        btnSend.setkBorderRadius(100);
        btnSend.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSend.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSend.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnSend.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSend.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSend.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnConversation.setText("Conversation 1");
        btnConversation.setkAllowTab(true);
        btnConversation.setkBorderRadius(25);
        btnConversation.setkForeGround(new java.awt.Color(102, 255, 0));
        btnConversation.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnConversation.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnConversation.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnConversation.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnConversation.setkStartColor(new java.awt.Color(0, 0, 0));
        btnConversation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConversationActionPerformed(evt);
            }
        });

        replyPane.setkBorderRadius(100);
        replyPane.setkEndColor(new java.awt.Color(153, 0, 153));
        replyPane.setkFillBackground(false);
        replyPane.setkStartColor(new java.awt.Color(0, 0, 0));
        replyPane.setOpaque(false);

        txtReply.setText("Please type in here....");
        txtReply.setBorder(null);
        txtReply.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtReply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtReplyMouseClicked(evt);
            }
        });
        txtReply.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReplyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReplyKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout replyPaneLayout = new javax.swing.GroupLayout(replyPane);
        replyPane.setLayout(replyPaneLayout);
        replyPaneLayout.setHorizontalGroup(
            replyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(replyPaneLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtReply, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        replyPaneLayout.setVerticalGroup(
            replyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, replyPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtReply, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnAddFriend.setText("+");
        btnAddFriend.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnAddFriend.setkBorderRadius(100);
        btnAddFriend.setkForeGround(new java.awt.Color(102, 255, 0));
        btnAddFriend.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnAddFriend.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnAddFriend.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnAddFriend.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnAddFriend.setkStartColor(new java.awt.Color(0, 0, 0));
        btnAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFriendActionPerformed(evt);
            }
        });

        userPane.setkBorderRadius(100);
        userPane.setkEndColor(new java.awt.Color(255, 255, 255));
        userPane.setkFillBackground(false);
        userPane.setkStartColor(new java.awt.Color(255, 255, 255));
        userPane.setOpaque(false);

        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserName.setText("userDio");

        btnReset.setText("Reset");
        btnReset.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnReset.setkBorderRadius(100);
        btnReset.setkForeGround(new java.awt.Color(102, 255, 0));
        btnReset.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnReset.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnReset.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnReset.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnReset.setkStartColor(new java.awt.Color(0, 0, 0));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnLogout.setkBorderRadius(100);
        btnLogout.setkForeGround(new java.awt.Color(102, 255, 0));
        btnLogout.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnLogout.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnLogout.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnLogout.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnLogout.setkStartColor(new java.awt.Color(0, 0, 0));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userPaneLayout = new javax.swing.GroupLayout(userPane);
        userPane.setLayout(userPaneLayout);
        userPaneLayout.setHorizontalGroup(
            userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPaneLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPaneLayout.createSequentialGroup()
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPaneLayout.createSequentialGroup()
                        .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        userPaneLayout.setVerticalGroup(
            userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        changeThemePanel.setkBorderRadius(100);
        changeThemePanel.setkEndColor(new java.awt.Color(255, 255, 255));
        changeThemePanel.setkFillBackground(false);
        changeThemePanel.setkStartColor(new java.awt.Color(255, 255, 255));
        changeThemePanel.setOpaque(false);

        lblChangeTheme.setText("Set your theme here");

        btnSetRedBackground.setkBackGroundColor(java.awt.Color.red);
        btnSetRedBackground.setkBorderRadius(100);
        btnSetRedBackground.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetRedBackground.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetRedBackground.setkHoverStartColor(java.awt.Color.red);
        btnSetRedBackground.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetRedBackground.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetRedBackground.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetRedBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetRedBackgroundActionPerformed(evt);
            }
        });

        btnSetGreenBackground.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetGreenBackground.setkBorderRadius(100);
        btnSetGreenBackground.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetGreenBackground.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetGreenBackground.setkHoverStartColor(java.awt.Color.green);
        btnSetGreenBackground.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetGreenBackground.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetGreenBackground.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetGreenBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetGreenBackgroundActionPerformed(evt);
            }
        });

        btnSetPinkBackground.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetPinkBackground.setkBorderRadius(100);
        btnSetPinkBackground.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetPinkBackground.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetPinkBackground.setkHoverStartColor(java.awt.Color.pink);
        btnSetPinkBackground.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetPinkBackground.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetPinkBackground.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetPinkBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetPinkBackgroundActionPerformed(evt);
            }
        });

        btnSetWhiteBackground.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetWhiteBackground.setkBorderRadius(100);
        btnSetWhiteBackground.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetWhiteBackground.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetWhiteBackground.setkHoverStartColor(java.awt.Color.white);
        btnSetWhiteBackground.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetWhiteBackground.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetWhiteBackground.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetWhiteBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetWhiteBackgroundActionPerformed(evt);
            }
        });

        btnSetTansparentBackground.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetTansparentBackground.setkBorderRadius(100);
        btnSetTansparentBackground.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetTansparentBackground.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetTansparentBackground.setkHoverStartColor(new java.awt.Color(236, 255, 254));
        btnSetTansparentBackground.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetTansparentBackground.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetTansparentBackground.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetTansparentBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetTansparentBackgroundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeThemePanelLayout = new javax.swing.GroupLayout(changeThemePanel);
        changeThemePanel.setLayout(changeThemePanelLayout);
        changeThemePanelLayout.setHorizontalGroup(
            changeThemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeThemePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblChangeTheme)
                .addGap(102, 102, 102))
            .addGroup(changeThemePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btnSetRedBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSetGreenBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnSetPinkBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSetWhiteBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSetTansparentBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        changeThemePanelLayout.setVerticalGroup(
            changeThemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeThemePanelLayout.createSequentialGroup()
                .addComponent(lblChangeTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changeThemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeThemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSetGreenBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSetPinkBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSetWhiteBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSetTansparentBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSetRedBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        friendList.setBackground(new java.awt.Color(255, 0, 255));
        friendList.setkBorderRadius(30);
        friendList.setkEndColor(new java.awt.Color(0, 204, 204));
        friendList.setkFillBackground(false);
        friendList.setkGradientFocus(450);
        friendList.setkStartColor(new java.awt.Color(153, 255, 255));
        friendList.setOpaque(false);
        friendList.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout friendListLayout = new javax.swing.GroupLayout(friendList);
        friendList.setLayout(friendListLayout);
        friendListLayout.setHorizontalGroup(
            friendListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        friendListLayout.setVerticalGroup(
            friendListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );

        lblIsTyping.setText("  ");

        changeFontPanel.setkBorderRadius(100);
        changeFontPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        changeFontPanel.setkFillBackground(false);
        changeFontPanel.setkStartColor(new java.awt.Color(255, 255, 255));
        changeFontPanel.setOpaque(false);

        lblChangeFont.setText("Set your font style here");
        lblChangeFont.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnSetRedFont.setkBackGroundColor(java.awt.Color.red);
        btnSetRedFont.setkBorderRadius(100);
        btnSetRedFont.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetRedFont.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetRedFont.setkHoverStartColor(java.awt.Color.red);
        btnSetRedFont.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetRedFont.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetRedFont.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetRedFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetRedFontActionPerformed(evt);
            }
        });

        btnSetGreenFont.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetGreenFont.setkBorderRadius(100);
        btnSetGreenFont.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetGreenFont.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetGreenFont.setkHoverStartColor(java.awt.Color.green);
        btnSetGreenFont.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetGreenFont.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetGreenFont.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetGreenFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetGreenFontActionPerformed(evt);
            }
        });

        btnSetPinkFont.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetPinkFont.setkBorderRadius(100);
        btnSetPinkFont.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetPinkFont.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetPinkFont.setkHoverStartColor(java.awt.Color.pink);
        btnSetPinkFont.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetPinkFont.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetPinkFont.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetPinkFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetPinkFontActionPerformed(evt);
            }
        });

        btnSetWhiteFont.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetWhiteFont.setkBorderRadius(100);
        btnSetWhiteFont.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetWhiteFont.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetWhiteFont.setkHoverStartColor(java.awt.Color.white);
        btnSetWhiteFont.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetWhiteFont.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetWhiteFont.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetWhiteFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetWhiteFontActionPerformed(evt);
            }
        });

        btnSetTansparentFont.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnSetTansparentFont.setkBorderRadius(100);
        btnSetTansparentFont.setkForeGround(new java.awt.Color(102, 255, 0));
        btnSetTansparentFont.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnSetTansparentFont.setkHoverStartColor(new java.awt.Color(236, 255, 254));
        btnSetTansparentFont.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnSetTansparentFont.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnSetTansparentFont.setkStartColor(new java.awt.Color(0, 0, 0));
        btnSetTansparentFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetTansparentFontActionPerformed(evt);
            }
        });

        btnBrowseFont.setText("Browse");
        btnBrowseFont.setkBorderRadius(100);
        btnBrowseFont.setkForeGround(new java.awt.Color(102, 255, 0));
        btnBrowseFont.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnBrowseFont.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnBrowseFont.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnBrowseFont.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnBrowseFont.setkStartColor(new java.awt.Color(0, 0, 0));
        btnBrowseFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseFontActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeFontPanelLayout = new javax.swing.GroupLayout(changeFontPanel);
        changeFontPanel.setLayout(changeFontPanelLayout);
        changeFontPanelLayout.setHorizontalGroup(
            changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeFontPanelLayout.createSequentialGroup()
                .addGroup(changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeFontPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblChangeFont)
                        .addGap(38, 38, 38))
                    .addGroup(changeFontPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnSetRedFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetGreenFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetPinkFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetWhiteFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetTansparentFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)))
                .addComponent(btnBrowseFont, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        changeFontPanelLayout.setVerticalGroup(
            changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeFontPanelLayout.createSequentialGroup()
                .addGroup(changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(changeFontPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnBrowseFont, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeFontPanelLayout.createSequentialGroup()
                        .addComponent(lblChangeFont, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSetGreenFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSetPinkFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSetWhiteFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSetTansparentFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSetRedFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        codeSnippetPanel.setkBorderRadius(100);
        codeSnippetPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        codeSnippetPanel.setkFillBackground(false);
        codeSnippetPanel.setkStartColor(new java.awt.Color(255, 255, 255));
        codeSnippetPanel.setOpaque(false);

        lblCodeSnippet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodeSnippet.setText("Java Intellisense");

        btnShowIntell.setText("Show");
        btnShowIntell.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnShowIntell.setkBorderRadius(100);
        btnShowIntell.setkForeGround(new java.awt.Color(102, 255, 0));
        btnShowIntell.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnShowIntell.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnShowIntell.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnShowIntell.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnShowIntell.setkStartColor(new java.awt.Color(0, 0, 0));
        btnShowIntell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowIntellActionPerformed(evt);
            }
        });

        btnActiveIntell.setText("Active");
        btnActiveIntell.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnActiveIntell.setkBorderRadius(100);
        btnActiveIntell.setkForeGround(new java.awt.Color(102, 255, 0));
        btnActiveIntell.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnActiveIntell.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnActiveIntell.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnActiveIntell.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnActiveIntell.setkStartColor(new java.awt.Color(0, 0, 0));
        btnActiveIntell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveIntellActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout codeSnippetPanelLayout = new javax.swing.GroupLayout(codeSnippetPanel);
        codeSnippetPanel.setLayout(codeSnippetPanelLayout);
        codeSnippetPanelLayout.setHorizontalGroup(
            codeSnippetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, codeSnippetPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnActiveIntell, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnShowIntell, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, codeSnippetPanelLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(lblCodeSnippet, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        codeSnippetPanelLayout.setVerticalGroup(
            codeSnippetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codeSnippetPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCodeSnippet, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(codeSnippetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowIntell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActiveIntell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnAddFile.setText("+");
        btnAddFile.setkBorderRadius(100);
        btnAddFile.setkForeGround(new java.awt.Color(102, 255, 0));
        btnAddFile.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnAddFile.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnAddFile.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnAddFile.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnAddFile.setkStartColor(new java.awt.Color(0, 0, 0));
        btnAddFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backGroundLayout = new javax.swing.GroupLayout(backGround);
        backGround.setLayout(backGroundLayout);
        backGroundLayout.setHorizontalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGroundLayout.createSequentialGroup()
                        .addComponent(replyPane, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConversation, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(conversation, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addComponent(changeThemePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(changeFontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(codeSnippetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userPane, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(btnAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(friendList, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(backGroundLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(lblIsTyping, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backGroundLayout.setVerticalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(changeThemePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(changeFontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(codeSnippetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(userPane, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConversation, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(friendList, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addComponent(conversation, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(replyPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIsTyping, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
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

//------------------------------BUTTON CLICK EVENTS------------------------------------------------//
    
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnConversationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConversationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConversationActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        String replyMsg = this.txtReply.getText();
        if (!"".equals(replyMsg) && !"Please type in here....".equals(replyMsg)) {
            
            
            this.messageTextArea.append("User DIO1: " + replyMsg + "\n");
            this.txtReply.setText("");
            this.txtReply.requestFocus();
        }


    }//GEN-LAST:event_btnSendActionPerformed

    private void txtReplyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReplyMouseClicked
        // TODO add your handling code here:
        if ("Please type in here....".equals(this.txtReply.getText())) {
            this.txtReply.setText("");
        }

    }//GEN-LAST:event_txtReplyMouseClicked
    //remove this later
    int testUserID = 0;
    private void btnAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFriendActionPerformed
        // TODO add your handling code here:
        // replace userDIO with username from SOCKET       
        this.applyModernView(this.friendList, userPane, btnAddFriend, Color.red, Color.pink, Color.black, Color.green, Color.white, Color.pink, Color.black, Color.pink, true, false, "userDIo " + this.testUserID);
        this.testUserID++;
       // this.friendListScrollPane.revalidate();
        //this.friendListScrollPane.repaint();

    }//GEN-LAST:event_btnAddFriendActionPerformed

    private void btnSetGreenBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetGreenBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.GREEN);
        this.backGround.setkEndColor(Color.GREEN);
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetGreenBackgroundActionPerformed

    private void btnSetRedBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetRedBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.RED);
        this.backGround.setkEndColor(Color.RED);
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetRedBackgroundActionPerformed

    private void btnSetPinkBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetPinkBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.PINK);
        this.backGround.setkEndColor(Color.PINK);
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetPinkBackgroundActionPerformed

    private void btnSetTansparentBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetTansparentBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(new Color(0, 0, 0, 30));
        this.backGround.setkEndColor(new Color(0, 0, 0, 30));
        this.backGround.setkFillBackground(true);
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetTansparentBackgroundActionPerformed

    private void btnSetWhiteBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetWhiteBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.WHITE);
        this.backGround.setkEndColor(Color.WHITE);
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetWhiteBackgroundActionPerformed

//-----------------------------------END OF CHANGE THEME METHODS-------------------//
    
//-----------------------------------KEY LISTENER EVENT----------------------------//
    private void txtReplyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReplyKeyPressed
        // TODO add your handling code here:
        this.lblIsTyping.setText("userDIO is typing...");
    }//GEN-LAST:event_txtReplyKeyPressed

    private void txtReplyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReplyKeyReleased
        // TODO add your handling code here:
        this.lblIsTyping.setText("");
        
    }//GEN-LAST:event_txtReplyKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        initSetting();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSetRedFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetRedFontActionPerformed
        // TODO add your handling code here:
        jComponent.forEach((jComponentI) -> {
            jComponentI.setForeground(Color.RED);
        });
        kComponent.forEach((kButtonI) -> {
            kButtonI.setkForeGround(Color.RED);
        });
    }//GEN-LAST:event_btnSetRedFontActionPerformed

    private void btnSetGreenFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetGreenFontActionPerformed
        // TODO add your handling code here:
        jComponent.forEach((jComponentI) -> {
            jComponentI.setForeground(Color.GREEN);
        });
        kComponent.forEach((kButtonI) -> {
            kButtonI.setkForeGround(Color.GREEN);
        });
    }//GEN-LAST:event_btnSetGreenFontActionPerformed

    private void btnSetPinkFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetPinkFontActionPerformed
        // TODO add your handling code here:
        jComponent.forEach((jComponentI) -> {
            jComponentI.setForeground(Color.PINK);
        });
        kComponent.forEach((kButtonI) -> {
            kButtonI.setkForeGround(Color.PINK);
        });
    }//GEN-LAST:event_btnSetPinkFontActionPerformed

    private void btnSetWhiteFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetWhiteFontActionPerformed
        // TODO add your handling code here:
        jComponent.forEach((jComponentI) -> {
            jComponentI.setForeground(Color.WHITE);
        });
        kComponent.forEach((kButtonI) -> {
            kButtonI.setkForeGround(Color.WHITE);
        });
    }//GEN-LAST:event_btnSetWhiteFontActionPerformed

    private void btnSetTansparentFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetTansparentFontActionPerformed
        // TODO add your handling code here:
        jComponent.forEach((jComponentI) -> {
            jComponentI.setForeground(new Color(15, 15, 15, 40));
        });
        kComponent.forEach((kButtonI) -> {
            kButtonI.setkForeGround(new Color(15, 15, 15, 40));
        });
    }//GEN-LAST:event_btnSetTansparentFontActionPerformed

    private void btnBrowseFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseFontActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_btnBrowseFontActionPerformed

    private void btnShowIntellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowIntellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnShowIntellActionPerformed
    private void btnAddFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFileActionPerformed
    
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddFileActionPerformed

    private void btnActiveIntellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveIntellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActiveIntellActionPerformed

    
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private keeptoo.KButton btnActiveIntell;
    private keeptoo.KButton btnAddFile;
    private keeptoo.KButton btnAddFriend;
    private keeptoo.KButton btnBrowseFont;
    private keeptoo.KButton btnConversation;
    private keeptoo.KButton btnLogout;
    private keeptoo.KButton btnReset;
    private keeptoo.KButton btnSend;
    private keeptoo.KButton btnSetGreenBackground;
    private keeptoo.KButton btnSetGreenFont;
    private keeptoo.KButton btnSetPinkBackground;
    private keeptoo.KButton btnSetPinkFont;
    private keeptoo.KButton btnSetRedBackground;
    private keeptoo.KButton btnSetRedFont;
    private keeptoo.KButton btnSetTansparentBackground;
    private keeptoo.KButton btnSetTansparentFont;
    private keeptoo.KButton btnSetWhiteBackground;
    private keeptoo.KButton btnSetWhiteFont;
    private keeptoo.KButton btnShowIntell;
    private keeptoo.KGradientPanel changeFontPanel;
    private keeptoo.KGradientPanel changeThemePanel;
    private keeptoo.KGradientPanel codeSnippetPanel;
    private keeptoo.KGradientPanel conversation;
    private keeptoo.KGradientPanel friendList;
    private javax.swing.JLabel lblChangeFont;
    private javax.swing.JLabel lblChangeTheme;
    private javax.swing.JLabel lblCodeSnippet;
    private javax.swing.JLabel lblIsTyping;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JScrollPane messageScrollPane;
    private javax.swing.JTextArea messageTextArea;
    private keeptoo.KGradientPanel replyPane;
    private javax.swing.JTextField txtReply;
    private keeptoo.KGradientPanel userPane;
    // End of variables declaration//GEN-END:variables

}
