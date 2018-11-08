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
public class Usuario {

    private String usuario;
    private String nombre;
    private String pass;
    private int habilitado;
    private int id_ct;
    private int id_empleado;

    public Usuario() {
    }

    public Usuario(String usuario, String nombre, String pass, int habilitado, int id_ct, int id_empleado) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.pass = pass;
        this.habilitado = habilitado;
        this.id_ct = id_ct;
        this.id_empleado = id_empleado;
    }

    public Usuario(String nombre, String pass, int habilitado, int id_ct, int id_empleado) {
        this.nombre = nombre;
        this.pass = pass;
        this.habilitado = habilitado;
        this.id_ct = id_ct;
        this.id_empleado = id_empleado;
    }

    public Usuario(String usuario, String nombre, String pass, int id_ct, int id_empleado) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.pass = pass;
        this.id_ct = id_ct;
        this.id_empleado = id_empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public int getId_ct() {
        return id_ct;
    }

    public void setId_ct(int id_ct) {
        this.id_ct = id_ct;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", nombre=" + nombre + ", pass=" + pass + '}';
    }

}
