/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.security;

import com.ieepo.checador.db.ConnectionBD;
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 *
 * @author varguelles
 */
public class MyDataSource extends BoneCPDataSource {
    
      private static final long serialVersionUID = -2248923051363839327L;
      private ConnectionBD cdb;
      
    /** Data source class for retrieving authentication information **/
    public MyDataSource() {
	super();
        cdb = new ConnectionBD();
	this.setDriverClass("com.mysql.jdbc.Driver");
	this.setJdbcUrl(cdb.getUrl());
	this.setUsername(cdb.getUser());
	this.setPassword(cdb.getPass());
	this.setDefaultAutoCommit(true);
    }

    /**
     * @param config
     */
    public MyDataSource(BoneCPConfig config) {
	super(config);
    }

}
