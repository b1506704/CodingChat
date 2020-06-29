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
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;
import keeptoo.Drag;
import keeptoo.KButton;
/**
 *
 * @author TsundereMoe
 */
public final class Dashboard extends javax.swing.JFrame {

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the serverAddr
     */
    public String getServerAddr() {
        return serverAddr;
    }

    /**
     * @param serverAddr the serverAddr to set
     */
    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
        this.lblUserName.setText("[" + username + "]");
    }
    public static ArrayList<JComponent> jComponent = new ArrayList<>();
    public static ArrayList<KButton> kComponent = new ArrayList<>();
    public ArrayList<String> keywords;
    public DefaultListModel model;
    public File file;
    public String fileStr;
    public SocketClient client;
    private int port;
    private String serverAddr;
    private String username;
    private static final String COMMIT_ACTION = "commit";
    AutoComplete autoComplete;
    public Thread clientThread;
     /**
     * Creates new form Dashboard
     *
     * @param client
     */
    public Dashboard(SocketClient client) {
        initComponents();
            //initial component
            jComponent.add(this.lblChangeFont);
            jComponent.add(this.lblChangeTheme);
            jComponent.add(this.lblCodeSnippet);
            jComponent.add(this.lblIsTyping);
            jComponent.add(this.lblUserName);
            jComponent.add(this.messageTextArea);
            jComponent.add(this.friendList);
            jComponent.add(this.friendListScrollPane.getViewport());
            jComponent.add(this.txtReply);
            jComponent.add(this.lblAppTime);
            jComponent.add(this.txtInsertCode);
            kComponent.add(this.btnActiveIntell);
            kComponent.add(this.btnAddFile);
            kComponent.add(this.btnShowMenu);
            kComponent.add(this.btnLogout);
            kComponent.add(this.btnShowConnection);
            kComponent.add(this.btnSend);
            kComponent.add(this.btnAddCode);
            //set model for friendList
            model.addElement("All");
            friendList.setSelectedIndex(0);
            //set graphic
            this.setBackground(new Color(0, 0, 0, 0));
            this.backGround.setBackground(new Color(0, 0, 0, 0));
            this.menuPanel.setkStartColor(new Color (0,0,0,0));
            this.menuPanel.setkEndColor(new Color(0,0,0,0));
            this.conversation.setBackground(new Color(0, 0, 0, 0));
            this.messageScrollPane.getViewport().setOpaque(false);
            this.messageTextArea.setBackground(new Color(0, 0, 0, 0));
            this.txtInsertCode.setBackground(new Color(0,0,0,0));
            this.txtReply.setBackground(new Color(0, 0, 0, 0));
            this.replyPane.setBackground(new Color(0, 0, 0, 0));
            this.userPane.setBackground(new Color(0, 0, 0, 0));
            this.friendList.setBackground(new Color(0,0,0,0));
            this.friendList.setOpaque(false);
            this.friendList.setSelectionBackground(this.backGround.kStartColor.darker());
            this.friendListScrollPane.getViewport().setOpaque(false);
            
            
            //apply font
            
            this.applyFont(this.btnSend, "SVN-Hole Hearted.ttf", 14f);
            this.applyFont(this.btnLogout, "SVN-Hole Hearted.ttf", 14f);
            this.applyFont(this.txtReply, "SVN-Hole Hearted.ttf", 14f);
            this.applyFont(this.btnShowConnection, "SVN-Hole Hearted.ttf", 14f);
            this.applyFont(this.btnActiveIntell, "SVN-Hole Hearted.ttf", 14f);
            this.applyFont(this.btnAddFile, "SVN-Hole Hearted.ttf", 14f);
            this.applyFont(this.btnAddCode, "SVN-Hole Hearted.ttf", 14f);
            this.applyFont(this.messageTextArea, "SVN-Hole Hearted.ttf", 25f);
            this.applyFont(this.lblChangeTheme, "FVF Fernando 08.ttf", 9f);
            this.applyFont(this.lblChangeFont, "FVF Fernando 08.ttf", 9f);
            this.applyFont(this.lblCodeSnippet, "FVF Fernando 08.ttf", 9f);
            this.applyFont(this.lblUserName, "SVN-Hole Hearted.ttf", 15f);
            this.applyFont(this.lblIsTyping, "SVN-Hole Hearted.ttf", 15f);
            this.applyFont(this.friendList,"SVN-Hole Hearted.ttf", 15f);
            this.applyFont(this.lblAppTime,"SVN-Hole Hearted.ttf", 15f);
            this.applyFont(this.txtInsertCode,"FVF Fernando 08.ttf", 9f);
            //keybinding
            this.keyBinding();
            //Java Intellsense
            
            keywords = new ArrayList<>(100);
            keywords.add("system.out.println()");
            keywords.add("for (int i=0; i<=n ; i++) { }");
            keywords.add("while (true) {}");
            keywords.add("if (true) {}");
            keywords.add("try {} catch (Exception ex) {}");
             //socket
            this.client=client;
            //
            //set time
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Date date = new Date();
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss dd/MM/YYYY");
                    String time = timeFormat.format(date);
                    lblAppTime.setText(time);
                }
            };
            Timer timer = new Timer(1000, actionListener);
            timer.setInitialDelay(0);
            timer.start();
    
           
            
    }
    public Dashboard() {}
    
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
        replyPane = new keeptoo.KGradientPanel();
        txtReply = new javax.swing.JTextField();
        btnShowMenu = new keeptoo.KButton();
        friendListPanel = new keeptoo.KGradientPanel();
        friendListScrollPane = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        lblIsTyping = new javax.swing.JLabel();
        btnAddFile = new keeptoo.KButton();
        menuPanel = new keeptoo.KGradientPanel();
        changeThemePanel = new keeptoo.KGradientPanel();
        lblChangeTheme = new javax.swing.JLabel();
        btnSetRedBackground = new keeptoo.KButton();
        btnSetGreenBackground = new keeptoo.KButton();
        btnSetPinkBackground = new keeptoo.KButton();
        btnSetWhiteBackground = new keeptoo.KButton();
        btnSetTansparentBackground = new keeptoo.KButton();
        changeFontPanel = new keeptoo.KGradientPanel();
        lblChangeFont = new javax.swing.JLabel();
        btnSetRedFont = new keeptoo.KButton();
        btnSetGreenFont = new keeptoo.KButton();
        btnSetPinkFont = new keeptoo.KButton();
        btnSetWhiteFont = new keeptoo.KButton();
        btnSetTansparentFont = new keeptoo.KButton();
        codeSnippetPanel = new keeptoo.KGradientPanel();
        lblCodeSnippet = new javax.swing.JLabel();
        btnAddCode = new keeptoo.KButton();
        btnActiveIntell = new keeptoo.KButton();
        txtInsertCode = new javax.swing.JTextField();
        userPane = new keeptoo.KGradientPanel();
        lblUserName = new javax.swing.JLabel();
        btnShowConnection = new keeptoo.KButton();
        btnLogout = new keeptoo.KButton();
        lblAppTime = new javax.swing.JLabel();

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

        btnShowMenu.setText("︽");
        btnShowMenu.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnShowMenu.setkBorderRadius(100);
        btnShowMenu.setkForeGround(new java.awt.Color(102, 255, 0));
        btnShowMenu.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnShowMenu.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnShowMenu.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnShowMenu.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnShowMenu.setkStartColor(new java.awt.Color(0, 0, 0));
        btnShowMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMenuActionPerformed(evt);
            }
        });

        friendListPanel.setBackground(new java.awt.Color(255, 0, 255));
        friendListPanel.setkBorderRadius(30);
        friendListPanel.setkEndColor(new java.awt.Color(0, 204, 204));
        friendListPanel.setkFillBackground(false);
        friendListPanel.setkGradientFocus(450);
        friendListPanel.setkStartColor(new java.awt.Color(153, 255, 255));
        friendListPanel.setkTransparentControls(false);
        friendListPanel.setOpaque(false);
        friendListPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        friendListScrollPane.setOpaque(false);

        friendList.setModel(model = new DefaultListModel());
        friendList.setOpaque(false);
        friendListScrollPane.setViewportView(friendList);

        javax.swing.GroupLayout friendListPanelLayout = new javax.swing.GroupLayout(friendListPanel);
        friendListPanel.setLayout(friendListPanelLayout);
        friendListPanelLayout.setHorizontalGroup(
            friendListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );
        friendListPanelLayout.setVerticalGroup(
            friendListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblIsTyping.setText("  ");

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

        menuPanel.setkFillBackground(false);
        menuPanel.setOpaque(false);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout changeFontPanelLayout = new javax.swing.GroupLayout(changeFontPanel);
        changeFontPanel.setLayout(changeFontPanelLayout);
        changeFontPanelLayout.setHorizontalGroup(
            changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeFontPanelLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeFontPanelLayout.createSequentialGroup()
                        .addComponent(lblChangeFont)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeFontPanelLayout.createSequentialGroup()
                        .addComponent(btnSetRedFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSetGreenFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSetPinkFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSetWhiteFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSetTansparentFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        changeFontPanelLayout.setVerticalGroup(
            changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeFontPanelLayout.createSequentialGroup()
                .addComponent(lblChangeFont, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changeFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSetGreenFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetPinkFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetWhiteFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetTansparentFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetRedFont, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        codeSnippetPanel.setkBorderRadius(100);
        codeSnippetPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        codeSnippetPanel.setkFillBackground(false);
        codeSnippetPanel.setkStartColor(new java.awt.Color(255, 255, 255));
        codeSnippetPanel.setOpaque(false);

        lblCodeSnippet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodeSnippet.setText("Java Intellisense");

        btnAddCode.setText("Insert");
        btnAddCode.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnAddCode.setkBorderRadius(100);
        btnAddCode.setkForeGround(new java.awt.Color(102, 255, 0));
        btnAddCode.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnAddCode.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnAddCode.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnAddCode.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnAddCode.setkStartColor(new java.awt.Color(0, 0, 0));
        btnAddCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCodeActionPerformed(evt);
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

        txtInsertCode.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout codeSnippetPanelLayout = new javax.swing.GroupLayout(codeSnippetPanel);
        codeSnippetPanel.setLayout(codeSnippetPanelLayout);
        codeSnippetPanelLayout.setHorizontalGroup(
            codeSnippetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, codeSnippetPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnActiveIntell, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtInsertCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddCode, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnAddCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActiveIntell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInsertCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        userPane.setkBorderRadius(100);
        userPane.setkEndColor(new java.awt.Color(255, 255, 255));
        userPane.setkFillBackground(false);
        userPane.setkStartColor(new java.awt.Color(255, 255, 255));
        userPane.setOpaque(false);

        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserName.setText("userDio");

        btnShowConnection.setText("Info");
        btnShowConnection.setkBackGroundColor(new java.awt.Color(51, 51, 51));
        btnShowConnection.setkBorderRadius(100);
        btnShowConnection.setkForeGround(new java.awt.Color(102, 255, 0));
        btnShowConnection.setkHoverForeGround(new java.awt.Color(51, 0, 51));
        btnShowConnection.setkHoverStartColor(new java.awt.Color(204, 255, 204));
        btnShowConnection.setkPressedColor(new java.awt.Color(0, 51, 51));
        btnShowConnection.setkSelectedColor(new java.awt.Color(51, 255, 204));
        btnShowConnection.setkStartColor(new java.awt.Color(0, 0, 0));
        btnShowConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowConnectionActionPerformed(evt);
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
                .addGap(16, 16, 16)
                .addGroup(userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPaneLayout.createSequentialGroup()
                        .addComponent(btnShowConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        userPaneLayout.setVerticalGroup(
            userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changeThemePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(changeFontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(codeSnippetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userPane, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(changeThemePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(changeFontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(codeSnippetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(userPane, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblAppTime.setText("26/5 2 : AM");

        javax.swing.GroupLayout backGroundLayout = new javax.swing.GroupLayout(backGround);
        backGround.setLayout(backGroundLayout);
        backGroundLayout.setHorizontalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(conversation, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGroundLayout.createSequentialGroup()
                                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(backGroundLayout.createSequentialGroup()
                                        .addGap(166, 166, 166)
                                        .addComponent(lblIsTyping, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblAppTime))
                                    .addComponent(replyPane, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(friendListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addComponent(btnShowMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        backGroundLayout.setVerticalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnShowMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(friendListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(backGroundLayout.createSequentialGroup()
                        .addComponent(conversation, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(replyPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAppTime)
                    .addGroup(backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIsTyping, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(backGround, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        try{ 
            client.send(new Message("messagxc e", getUsername(), ".bye", "SERVER")); 
            this.dispose();
            
        }  catch (Exception ex) { 
                 
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        String target = this.friendList.getSelectedValue();
        String replyMsg = this.txtReply.getText();
        if (!"".equals(replyMsg) && !"Please type in here....".equals(replyMsg)) {
            client.send(new Message("message", this.getUsername(), replyMsg, target));
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
    int count = 0;
    private void btnShowMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMenuActionPerformed
        // TODO add your handling code here:
        if (count%2==0) {
            this.menuPanel.setVisible(false);
        } else {
            this.menuPanel.setVisible(true);
        }
        
        count++;
    }//GEN-LAST:event_btnShowMenuActionPerformed

    private void btnSetGreenBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetGreenBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.GREEN);
        this.backGround.setkEndColor(Color.GREEN);
        this.friendList.setSelectionBackground(this.backGround.kStartColor.darker());
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetGreenBackgroundActionPerformed

    private void btnSetRedBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetRedBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.RED);
        this.backGround.setkEndColor(Color.RED);
        this.friendList.setSelectionBackground(this.backGround.kStartColor.darker());
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetRedBackgroundActionPerformed

    private void btnSetPinkBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetPinkBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.PINK);
        this.backGround.setkEndColor(Color.PINK);
        this.friendList.setSelectionBackground(this.backGround.kStartColor.darker());
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetPinkBackgroundActionPerformed

    private void btnSetTansparentBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetTansparentBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(new Color(0, 0, 0, 100));
        this.backGround.setkEndColor(new Color(0, 0, 0, 100));
        this.backGround.setkFillBackground(true);
        this.friendList.setSelectionBackground(this.backGround.kStartColor.darker());
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetTansparentBackgroundActionPerformed

    private void btnSetWhiteBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetWhiteBackgroundActionPerformed
        // TODO add your handling code here:
        this.backGround.setkStartColor(Color.WHITE);
        this.backGround.setkEndColor(Color.WHITE);
        this.friendList.setSelectionBackground(this.backGround.kStartColor.darker());
        this.backGround.repaint();
    }//GEN-LAST:event_btnSetWhiteBackgroundActionPerformed

//-----------------------------------END OF CHANGE THEME METHODS-------------------//
//-----------------------------------KEY LISTENER EVENT----------------------------//
    
    private void txtReplyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReplyKeyPressed
        // TODO add your handling code here:
                this.lblIsTyping.setText("you are typing...");
    }//GEN-LAST:event_txtReplyKeyPressed

    private void txtReplyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReplyKeyReleased
        // TODO add your handling code here:
            this.lblIsTyping.setText("");
    }//GEN-LAST:event_txtReplyKeyReleased

    private void btnShowConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowConnectionActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this,"Username: " +  this.getUsername() + " at port " + this.getPort() + " at " + this.getServerAddr() + ".");
    }//GEN-LAST:event_btnShowConnectionActionPerformed

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
    
    private void btnAddCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCodeActionPerformed
        // TODO add your handling code here:
        if (!this.txtInsertCode.getText().equals("")) {
            String input = this.txtInsertCode.getText();
            keywords.add(input);
            JOptionPane.showMessageDialog(this, "Added " + input);
            autoComplete = new AutoComplete(this.txtReply, keywords);
        } else {
            JOptionPane.showMessageDialog(this, "Please check input!");
        }
    }//GEN-LAST:event_btnAddCodeActionPerformed
    private void btnAddFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFileActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "Select File");
        file = fileChooser.getSelectedFile();
        if(file != null){
            if(!file.getName().isEmpty()){
               this.fileStr=file.getPath();
               client.send(new Message("upload_req", this.getUsername(), file.getName(), friendList.getSelectedValue()));
            }
        }
    }//GEN-LAST:event_btnAddFileActionPerformed

    private void btnActiveIntellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveIntellActionPerformed
        // TODO add your handling code here:
        this.txtReply.setFocusTraversalKeysEnabled(false);
        autoComplete = new AutoComplete(this.txtReply, keywords);
        this.txtReply.getDocument().addDocumentListener(autoComplete);
        this.txtReply.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
        this.txtReply.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
        String listOfCode="";
        String concat="";
        for (int i = 0; i < keywords.size(); i++) {
            concat+= listOfCode.concat(keywords.get(i) + "\n");
        }
        JOptionPane.showMessageDialog(this, "Use tab key to auto-complete code snippet.\n List of code:\n" + concat);
        
        this.btnActiveIntell.setEnabled(false);
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
                new Dashboard().setVisible(true);
              //  initSetting();

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel backGround;
    private keeptoo.KButton btnActiveIntell;
    private keeptoo.KButton btnAddCode;
    private keeptoo.KButton btnAddFile;
    private keeptoo.KButton btnLogout;
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
    private keeptoo.KButton btnShowConnection;
    private keeptoo.KButton btnShowMenu;
    private keeptoo.KGradientPanel changeFontPanel;
    private keeptoo.KGradientPanel changeThemePanel;
    private keeptoo.KGradientPanel codeSnippetPanel;
    private keeptoo.KGradientPanel conversation;
    public javax.swing.JList<String> friendList;
    private keeptoo.KGradientPanel friendListPanel;
    private javax.swing.JScrollPane friendListScrollPane;
    public javax.swing.JLabel lblAppTime;
    private javax.swing.JLabel lblChangeFont;
    private javax.swing.JLabel lblChangeTheme;
    private javax.swing.JLabel lblCodeSnippet;
    private javax.swing.JLabel lblIsTyping;
    private javax.swing.JLabel lblUserName;
    private keeptoo.KGradientPanel menuPanel;
    private javax.swing.JScrollPane messageScrollPane;
    public javax.swing.JTextArea messageTextArea;
    private keeptoo.KGradientPanel replyPane;
    private javax.swing.JTextField txtInsertCode;
    private javax.swing.JTextField txtReply;
    private keeptoo.KGradientPanel userPane;
    // End of variables declaration//GEN-END:variables

}
