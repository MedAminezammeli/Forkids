/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forkids.utils;

import com.forkids.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author amine
 */
public class Authentification {
    private Connection cnx;
    public static Authentification instance;
    public static User USer;
    
    public Authentification()
    {
    cnx=DataSource.getInstance().getConnection();
    USer=null;
    }
    
    public static Authentification getSession()
    {if(instance==null)
    instance=new Authentification();
    return instance;
    }
     public boolean connected(){
        return USer!=null;
    }
    public void logout(){
        USer=null;
    }
     public User getUser(){
        if (connected()) return USer;
        return null;
    }
    
    public User login(String email,String password){
              
             String req = "SELECT * FROM user WHERE email = ? and password = ? ";
             
             try{
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setString(1, email);
                    ps.setString(2, password);
                    
                    ResultSet r = ps.executeQuery();

                    if(r.next()){
                        USer= new User(r.getInt(1), r.getString(2), r.getString(6), r.getString(10)
                                ,r.getString(17));  
                        USer.setRole(r.getString(12));
                        
                    }
                    
                         
                }
                catch(SQLException ex){
                    
                }
             if(USer!=null)
             {
                 return USer;
             }
             return null;
        }
    }
