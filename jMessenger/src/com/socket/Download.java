package com.socket;

import com.ui.Dashboard;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Download implements Runnable{
    
    public ServerSocket server;
    public Socket socket;
    public int port=13000;
    public String saveTo = "";
    public InputStream In;
    public FileOutputStream Out;
    public Dashboard ui;
    
    public Download(String saveTo, Dashboard ui){
        try {
            server = new ServerSocket(0);
            port = server.getLocalPort();
            this.saveTo = saveTo;
            this.ui = ui;
        } 
        catch (IOException ex) {
            System.out.println("Exception [Download : Download(...)]");
        }
    }

    @Override
    public void run() {
        try {
            socket = server.accept();
            System.out.println("Download : "+socket.getRemoteSocketAddress());
            
            In = socket.getInputStream();
            Out = new FileOutputStream(saveTo);
            
            byte[] buffer = new byte[1024];
            int count;
            
            while((count = In.read(buffer)) >= 0){
                Out.write(buffer, 0, count);
            }
            
            Out.flush();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            ui.messageTextArea.append("[Application > Me] : Download complete"+"     \n\t\t\t\t[" + formatter.format(date)+  "]\n");
            
            if(Out != null){ Out.close(); }
            if(In != null){ In.close(); }
            if(socket != null){ socket.close(); }
        } 
        catch (Exception ex) {
            System.out.println("Exception [Download : run(...)]");
        }
    }
}