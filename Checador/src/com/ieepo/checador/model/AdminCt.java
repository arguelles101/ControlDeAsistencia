/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.model;

/**
 *
 * @author varguelles
 */
public class AdminCt {
    private int id_admin_ct;
    private int id_empleado;
    private int id_ct;

    public AdminCt() {
    }

    public AdminCt(int id_admin_ct, int id_empleado, int id_ct) {
        this.id_admin_ct = id_admin_ct;
        this.id_empleado = id_empleado;
        this.id_ct = id_ct;
    }

    public int getId_admin_ct() {
        return id_admin_ct;
    }

    public void setId_admin_ct(int id_admin_ct) {
        this.id_admin_ct = id_admin_ct;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_ct() {
        return id_ct;
    }

    public void setId_ct(int id_ct) {
        this.id_ct = id_ct;
    }
    
    
}
