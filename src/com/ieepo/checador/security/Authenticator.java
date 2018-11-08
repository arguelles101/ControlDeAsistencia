/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.security;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author varguelles
 */
public class Authenticator {
private static final Logger log = LoggerFactory.getLogger(Authenticator.class);  
    

    private static final String AUTHENTICATION_QUERY = "SELECT pass FROM sis_usuarios WHERE usuario=?";

    private static final String SALTED_AUTHENTICATION_QUERY = "SELECT password, salt FROM users WHERE username = ?";
    private static final String ROLES_QUERY = "SELECT rol FROM sis_usuarios_roles WHERE usuario=?";
    private static final String PERMISIONS_QUERY = "SELECT permiso FROM sis_roles_permisos WHERE rol=?";


    /**
     * * Autenticates a user *
     * @param username
     * @param pass
     * @return 
     */
    public Subject authenticate(String username, String pass) {
        
        //Md5Hash md5u = new Md5Hash(username);
        //String passw= pass;
        Subject currentUser = null;
        try {
            
            Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
            org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

            
            // init shiro - place this e.g. in the constructor
            // SecurityUtils.setSecurityManager(securityManager);
            // the key "jdbcRealm" must be the same in the shiro.ini file.

            JdbcRealm realm = (JdbcRealm)((IniSecurityManagerFactory) factory)
                    .getBeans().get("jdbcRealm");
            System.out.println("realm = " + realm);
   

            /*Md5CredentialsMatcher md5 = new Md5CredentialsMatcher();
            md5.setStoredCredentialsHexEncoded(true);
            realm.setCredentialsMatcher(md5);
            
            DefaultHashService hashService = new DefaultHashService();
            hashService.setHashIterations(500000);
            hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
            hashService.setGeneratePublicSalt(true);

            realm.setCredentialsMatcher((CredentialsMatcher) hashService);
            */
            //realm.setCredentialsMatcher(Sha256Hash.);
            realm.setAuthenticationQuery(AUTHENTICATION_QUERY);
            realm.setUserRolesQuery(ROLES_QUERY);
            realm.setPermissionsQuery(PERMISIONS_QUERY);
            realm.setSaltStyle(JdbcRealm.SaltStyle.NO_SALT);
            realm.setPermissionsLookupEnabled(true);
            SecurityUtils.setSecurityManager(securityManager);
            currentUser = SecurityUtils.getSubject();
            System.out.println("username = " + username);
            System.out.println("pass = " + pass);
            UsernamePasswordToken token = new UsernamePasswordToken(username, pass);
            
            System.out.println("token = " + token);
 
            // return info;
            try {
                currentUser.login(token);
                System.out.println("User [" + currentUser.getPrincipal().toString() + "] logged in successfully.");
         
            // save current username in the session, so we have access to our User model
                Session session = currentUser.getSession();  
                session.setAttribute("username", username);
              
            } catch (UnknownAccountException uae) {
                System.out.println("uae = " + uae);
//log.info("There is no user with username of " + token.getPrincipal());  
                JOptionPane.showMessageDialog(null,"There is no user with username of \" "
                        + token.getPrincipal() + "\"");
                //System.out.println("There is no user with username of "
                    //    + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("ice = " + ice);
                  JOptionPane.showMessageDialog(null,"Password for account " + token.getPrincipal()
                 + " was incorrect!");
//                System.out.println("Password for account " + token.getPrincipal()
//                        + " was incorrect!");
            } catch (LockedAccountException lae) {
                JOptionPane.showMessageDialog(null,"The account for username " + token.getPrincipal()
                        + " is locked.  "
                        + "Please contact your administrator to unlock it.");
//                System.out.println("The account for username " + token.getPrincipal()
//                        + " is locked.  "
//                        + "Please contact your administrator to unlock it.");
            } catch (ExcessiveAttemptsException aee) {
                JOptionPane.showMessageDialog(null,aee);                
//System.out.println("eae : " + eae);
            } catch (AuthenticationException ae) {
                JOptionPane.showMessageDialog(null,ae);  
                //System.out.println("ae : " + ae);
            }
        } catch (HeadlessException ex) {
            System.out.println("ex = " + ex);
        } catch (InvalidSessionException e){
            System.out.println("e = " + e);
        }
        return currentUser;

    }
    
}
