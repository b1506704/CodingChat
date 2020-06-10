/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TsundereMoe
 */
public class DatabaseSQL {
    
    public Connection connection;
    public DatabaseSQL(){
    
    
    }
    public void Connect(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://DESKTOP-P2O083N\\SQLEXPRESS:1433;databaseName=CodingChat;user=sa;password=sa2014";
            connection= DriverManager.getConnection(url);
      
    
        }
         catch (Exception ex){
    
        }
    
    }
    public void Disconnect() throws SQLException{
        connection.close();
    }
    public boolean CheckLogin(String username, String password){
        try {
            this.Connect();
            Statement st = connection.createStatement();
            String url = "select * from ChatUser where ChatUser.username='"+username+"' and ChatUser.password='"+password+"'";
            ResultSet rs = st.executeQuery(url);
            while (rs.next()){
                if (rs.getString("username").equals(username)) {
                    return true;
                } else {
                    return false;
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    public boolean CheckUser(String username){
        try {
            this.Connect();
            Statement st = connection.createStatement();
            String url = "select * from ChatUser where ChatUser.username='"+username+"'";
            ResultSet rs = st.executeQuery(url);
            while (rs.next()){
                if (rs.getString("username").equals(username)) {
                    return true;
                } else {
                    return false;
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public void addUser(String username, String password) {
        try {
            this.Connect();
            Statement st = connection.createStatement();
            String url = "insert into ChatUser values ('"+username+"','"+password+"')";
            st.execute(url);
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
