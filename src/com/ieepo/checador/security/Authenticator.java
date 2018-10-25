/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.security;

import java.awt.HeadlessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;

/**
 *
 * @author varguelles
 */
public class Authenticator {

    private static final Logger log = LoggerFactory.getLogger(Authenticator.class);

    private static final String AUTHENTICATION_QUERY = "SELECT pass FROM sis_usuarios WHERE usuario=?";

    //private static final String SALTED_AUTHENTICATION_QUERY = "SELECT password, salt FROM users WHERE username = ?";
    //private static final String ROLES_QUERY = "SELECT g.name FROM groups g,users u WHERE g.id = u.group_id AND u.username=?";
    //private static final String PERMISIONS_QUERY = "SELECT p.permission FROM perms p,groups g WHERE p.group_id=g.id AND g.name=?";
    /**
     * * Autenticates a user
     *
     *
     * @param username
     * @param pass
     * @return
     */
    public Subject authenticate(String username, String pass) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, pass);
        
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory();
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        
        
        
        SecurityUtils.setSecurityManager(securityManager);
               
        Subject currentUser = SecurityUtils.getSubject();
        
        System.out.println("currentUser = " + currentUser);

        //Authenticate the subject by passing 
        //the user name and password token 
        //into the login method 
        //currentUser.login(token);
        try {
            currentUser.login(token);
        } catch  ( UnknownAccountException uae ) { 
            System.out.println("uae = " + uae);
        } catch  ( LockedAccountException lae ) {
            System.out.println("lae = " + lae);
        } catch  ( ExcessiveAttemptsException eae ) {
            System.out.println("eae = " + eae);
        } catch ( AuthenticationException ae ) {
            System.out.println("ae = " + ae);
        }
        
        return currentUser ;

}
}
