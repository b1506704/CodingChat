package com.socket;

import com.ui.Dashboard;
import java.io.*;
import java.net.*;
import com.ui.LoginRegister;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public LoginRegister logUI;
    public Dashboard chatUI;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public SimpleDateFormat formatter;  
    public Date date;
    public SocketClient(LoginRegister loginRegister) throws IOException{
        logUI = loginRegister; 
        this.serverAddr = logUI.getServerAddr(); 
        this.port = logUI.getPort();
        socket = new Socket(InetAddress.getByName(serverAddr), port);
            
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
    }
    
    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(logUI.getUsername())){
                        formatter = new SimpleDateFormat("HH:mm:ss");
                        date = new Date();
                        chatUI.messageTextArea.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content +"\n\t\t\t\t[" + formatter.format(date)+ "]\n" );
                    }
                    else{
                        formatter = new SimpleDateFormat("HH:mm:ss");
                        date = new Date();
                        chatUI.messageTextArea.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content +"\n\t\t\t\t[" + formatter.format(date)+  "]\n");
                    }
                }
                else if(msg.type.equals("login")){
                    //found user in database
                    if(msg.content.equals("TRUE")){
                        chatUI = new Dashboard(this);
                        chatUI.setUsername(logUI.getUsername());
                        chatUI.setPort(logUI.getPort());
                        chatUI.setServerAddr(logUI.getServerAddr());
                        chatUI.clientThread=logUI.clientThread;
                        chatUI.setVisible(true);
                        chatUI.toFront();
                    }
                    else{
                        //show error dialog
                        logUI.showLoginInformation();
                    }
                }
                else if(msg.type.equals("newuser")){
                    //show user online
                    chatUI.model.removeElement(msg.content);
                    chatUI.model.addElement(msg.content);
                }
                else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
                        JOptionPane.showMessageDialog(chatUI, "Register successfully");
                    }
                    else{
                        //show error register dialog
                        logUI.showRegisterInformation();
                    }
                }
                else if(msg.type.equals("signout")){
                    //user signout
                    if(msg.content.equals(logUI.getUsername())){
                        chatUI.model.removeElement(msg.content);
                        logUI.clientThread.stop();
                    }
                    else{
                        formatter = new SimpleDateFormat("HH:mm:ss");
                        date = new Date();
                        chatUI.model.removeElement(msg.content);
                        chatUI.messageTextArea.append("["+ msg.sender +" > All] : "+ msg.content +" has signed out" + "\n\t\t\t\t[" + formatter.format(date)+  "]\n");
                    }
                }
                else if(msg.type.equals("upload_req")){
                    
                    if(JOptionPane.showConfirmDialog(chatUI, ("Accept '"+msg.content+"' from "+msg.sender+" ?")) == 0){
                        
                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(msg.content));
                        int returnVal = jf.showSaveDialog(chatUI);
                       
                        String saveTo = jf.getSelectedFile().getPath();
                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
                            Download dwn = new Download(saveTo, chatUI);
                            Thread t = new Thread(dwn);
                            t.start();
                            send(new Message("upload_res", logUI.getUsername(), (""+dwn.port), msg.sender));
                        }
                        else{
                            send(new Message("upload_res", logUI.getUsername(), "NO", msg.sender));
                        }
                    }
                    else{
                        send(new Message("upload_res", logUI.getUsername(), "NO", msg.sender));
                    }
                }
                else if(msg.type.equals("upload_res")){
                    if(!msg.content.equals("NO")){
                        int port  = Integer.parseInt(msg.content);
                        String addr = msg.sender;
                        
                        Upload upl = new Upload(addr, port, chatUI.file, chatUI);
                        Thread t = new Thread(upl);
                        t.start();
                    }
                    else{
                        chatUI.messageTextArea.append("[SERVER > Me] : "+msg.sender+" rejected file request\n");
                    }
                }
                else{
                    chatUI.messageTextArea.append("[SERVER > Me] : Unknown message type\n");
                }
            
        }
            catch(IOException | ClassNotFoundException ex) {
                
            }
        }
            
    }
    
    public void send(Message msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
