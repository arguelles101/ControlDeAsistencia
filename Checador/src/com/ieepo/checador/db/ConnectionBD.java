/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author varguelles
 */
public class ConnectionBD {
     private Connection conn = null;

    public Connection conectar(){
        
        /**
         * Pruebas
         */
        String url = "jdbc:mysql://localhost:3306/controlasistencias";
        String user = "varguelles";
        String pass = "#1Qazse4";
        
        /**
         * 
         */
        /*
        String url = "jdbc:mysql://10.186.11.219:3306/controlasistencias";
        String user = "MCA";
        String pass = "12345";
        */
        
        System.out.println("Conectando...");
        try{
            conn = DriverManager.getConnection(url, user,pass);
            System.out.println("Conectado");
            return conn;
        }catch(SQLException e){
            System.out.println("mensaje");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void desconectar(){
        conn = null;
        System.out.println("Desconexion a base de datos listo...");
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
