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
public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String rfc;
    private int idCt;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombre, String apPaterno, String apMaterno, String rfc, int idCt) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.rfc = rfc;
        this.idCt = idCt;
    }

    public Empleado(String nombre, String apPaterno, String apMaterno, String rfc, int idCt) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.rfc = rfc;
        this.idCt = idCt;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getIdCt() {
        return idCt;
    }

    public void setIdCt(int idCt) {
        this.idCt = idCt;
    }

    @Override
    public String toString() {
        return nombre + " " + apPaterno + " " + apMaterno;
        //return (nombre.length() + apPaterno.length() + apMaterno.length()) + " " + nombre + " " + apPaterno + " " + apMaterno;
    }

    public String string(){
        return "<html>" + this.nombre + "<br>" + this.apPaterno + " " + this.apMaterno + "</html>";
    }   
}
