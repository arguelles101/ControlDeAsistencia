/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author varguelles
 */
public class ConnectionBD {

    private Connection conn = null;
    
    /**
     * Pruebas
     */
    private String url = "jdbc:mysql://localhost:3306/controlasistencias";
    private String user = "varguelles";
    private String pass = "#1Qazse4";
     
    /*
     * Produccion
     * 
     */
    /*
    private String url = "jdbc:mysql://10.186.11.219:3306/controlasistencias";
    private String user = "MCA";
    private String pass = "12345";
    */
    
    public Connection conectar() {

        System.out.println("Conectando...");
        try {
            //        Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = null;
        System.out.println("Desconexion a base de datos listo...");
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
