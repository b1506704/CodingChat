package com.socket;

import com.ui.Dashboard;
import java.io.*;
import java.net.*;
import java.util.Date;
import com.ui.LoginRegister;
public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public LoginRegister logUI;
    public Dashboard chatUI;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public History hist;
    public SocketClient(LoginRegister loginRegister) throws IOException{
        logUI = loginRegister; 
        this.serverAddr = logUI.getServerAddr(); 
        this.port = logUI.getPort();
        socket = new Socket(InetAddress.getByName(serverAddr), port);
            
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
      //  hist = ui.hist;
    }
    
    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                //can't interact with Dashboard yet <== check
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(logUI.getUsername())){
                        chatUI.messageTextArea.append("["+msg.sender +" > Me] : " + msg.content + "\n");
                    }
                    else{
                        chatUI.messageTextArea.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(logUI.getUsername())){
                        String msgTime = (new Date()).toString();
                        
//                        try{
//                            hist.addMessage(msg, msgTime);
//                            DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
//                            table.addRow(new Object[]{msg.sender, msg.content, "Me", msgTime});
//                        }
//                        catch(Exception ex){}  
                    }
                }
                else if(msg.type.equals("login")){
                    if(msg.content.equals("TRUE")){
                        chatUI = new Dashboard(this);
                        chatUI.setUsername(logUI.getUsername());
                        chatUI.setPort(logUI.getPort());
                        chatUI.setServerAddr(logUI.getServerAddr());
                        chatUI.clientThread=logUI.clientThread;
                        chatUI.setVisible(true);
                        chatUI.toFront();
                       // chatUI.wait();
                       

                    }
                    else{
                        //show dialog
                        logUI.showLoginInformation();
                    }
                }
//                else if(msg.type.equals("test")){
//                    ui.jButton1.setEnabled(false);
//                    ui.jButton2.setEnabled(true); ui.jButton3.setEnabled(true);
//                    ui.jTextField3.setEnabled(true); ui.jPasswordField1.setEnabled(true);
//                    ui.jTextField1.setEditable(false); ui.jTextField2.setEditable(false);
//                    ui.jButton7.setEnabled(true);
//                }
                else if(msg.type.equals("newuser")){
                    //show user online
                    // login--> newuser
//                    if(!msg.content.equals(logUI.getUsername())){
//                        boolean exists = false;
//                        for(int i = 0; i < chatUI..getSize(); i++){
//                            if(ui.model.getElementAt(i).equals(msg.content)){
//                                exists = true; break;
//                            }
//                        }
//                        if(!exists){ ui.model.addElement(msg.content); }
//                    }
                }
                else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
//                        ui.jButton2.setEnabled(false); ui.jButton3.setEnabled(false);
//                        ui.jButton4.setEnabled(true); ui.jButton5.setEnabled(true);
                        //show information register dialog
                        
                    }
                    else{
                        logUI.showRegisterInformation();
                    }
                }
                else if(msg.type.equals("signout")){
                    //user signout
//                    if(msg.content.equals(ui.username)){
//                        ui.jTextArea1.append("["+ msg.sender +" > Me] : Bye\n");
//                        ui.jButton1.setEnabled(true); ui.jButton4.setEnabled(false); 
//                        ui.jTextField1.setEditable(true); ui.jTextField2.setEditable(true);
//                        
//                        for(int i = 1; i < ui.model.size(); i++){
//                            ui.model.removeElementAt(i);
//                        }
//                        
//                        ui.clientThread.stop();
//                    }
//                    else{
//                        ui.model.removeElement(msg.content);
//                        ui.jTextArea1.append("["+ msg.sender +" > All] : "+ msg.content +" has signed out\n");
//                    }
                }
                else if(msg.type.equals("upload_req")){
                    
//                    if(JOptionPane.showConfirmDialog(ui, ("Accept '"+msg.content+"' from "+msg.sender+" ?")) == 0){
//                        
//                        JFileChooser jf = new JFileChooser();
//                        jf.setSelectedFile(new File(msg.content));
//                        int returnVal = jf.showSaveDialog(ui);
//                       
//                        String saveTo = jf.getSelectedFile().getPath();
//                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
//                            Download dwn = new Download(saveTo, ui);
//                            Thread t = new Thread(dwn);
//                            t.start();
//                            //send(new Message("upload_res", (""+InetAddress.getLocalHost().getHostAddress()), (""+dwn.port), msg.sender));
//                            send(new Message("upload_res", ui.username, (""+dwn.port), msg.sender));
//                        }
//                        else{
//                            send(new Message("upload_res", ui.username, "NO", msg.sender));
//                        }
//                    }
//                    else{
//                        send(new Message("upload_res", ui.username, "NO", msg.sender));
//                    }
                }
                else if(msg.type.equals("upload_res")){
//                    if(!msg.content.equals("NO")){
//                        int port  = Integer.parseInt(msg.content);
//                        String addr = msg.sender;
//                        
//                        ui.jButton5.setEnabled(false); ui.jButton6.setEnabled(false);
//                        Upload upl = new Upload(addr, port, ui.file, ui);
//                        Thread t = new Thread(upl);
//                        t.start();
//                    }
//                    else{
//                        ui.jTextArea1.append("[SERVER > Me] : "+msg.sender+" rejected file request\n");
//                    }
//                }
//                else{
//                    ui.jTextArea1.append("[SERVER > Me] : Unknown message type\n");
//                }
            }
        }
            catch(Exception ex) {
//                keepRunning = false;
//                ui.jTextArea1.append("[Application > Me] : Connection Failure\n");
//                ui.jButton1.setEnabled(true); ui.jTextField1.setEditable(true); ui.jTextField2.setEditable(true);
//                ui.jButton4.setEnabled(false); ui.jButton5.setEnabled(false); ui.jButton5.setEnabled(false);
//                
//                for(int i = 1; i < ui.model.size(); i++){
//                    ui.model.removeElementAt(i);
//                }
//                
//                ui.clientThread.stop();
//                
//                System.out.println("Exception SocketClient run()");
//                ex.printStackTrace();
            }
        }
            
    }
    
    public void send(Message msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
            
            if(msg.type.equals("message") && !msg.content.equals(".bye")){
                String msgTime = (new Date()).toString();
                try{
                  //  hist.addMessage(msg, msgTime);               
                   // DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
                   // table.addRow(new Object[]{"Me", msg.content, msg.recipient, msgTime});
                }
                catch(Exception ex){}
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
