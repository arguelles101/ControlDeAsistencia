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
public class Ct {
    private int id_ct;
    private String clave;
    private String domicilio;

    public Ct() {
    }

    public Ct(int id_ct, String clave, String domicilio) {
        this.id_ct = id_ct;
        this.clave = clave;
        this.domicilio = domicilio;
    }

    public Ct(String clave, String domicilio) {
        this.clave = clave;
        this.domicilio = domicilio;
    }

    public int getId_ct() {
        return id_ct;
    }

    public void setId_ct(int id_ct) {
        this.id_ct = id_ct;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return domicilio;
    }
}
