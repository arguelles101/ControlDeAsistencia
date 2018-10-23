/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.model;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author varguelles
 */
public class Permiso {
    private int id_permiso;
    private int id_incidencia;
    private int id_empleado;
    private Time hora_inicio;
    private Time hora_reinicio;
    private Date fecha_inicio;
    private Date fecha_reinicio;
    private String autorizo;
    private String numdoc;
    private String nota;
    private String tipo_permiso;

    public Permiso() {
    }

    public Permiso(int id_permiso, int id_incidencia, int id_empleado, Time hora_inicio, Time hora_reinicio, Date fecha_inicio, Date fecha_reinicio) {
        this.id_permiso = id_permiso;
        this.id_incidencia = id_incidencia;
        this.id_empleado = id_empleado;
        this.hora_inicio = hora_inicio;
        this.hora_reinicio = hora_reinicio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_reinicio = fecha_reinicio;
    }

    public Permiso(int id_incidencia, int id_empleado, Time hora_inicio, Time hora_reinicio, Date fecha_inicio, Date fecha_reinicio) {
        this.id_incidencia = id_incidencia;
        this.id_empleado = id_empleado;
        this.hora_inicio = hora_inicio;
        this.hora_reinicio = hora_reinicio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_reinicio = fecha_reinicio;
    }

    public Permiso(int id_permiso, int id_incidencia, int id_empleado, Time hora_inicio, Time hora_reinicio, Date fecha_inicio, Date fecha_reinicio, String autorizo, String numdoc, String nota, String tipo_permiso) {
        this.id_permiso = id_permiso;
        this.id_incidencia = id_incidencia;
        this.id_empleado = id_empleado;
        this.hora_inicio = hora_inicio;
        this.hora_reinicio = hora_reinicio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_reinicio = fecha_reinicio;
        this.autorizo = autorizo;
        this.numdoc = numdoc;
        this.nota = nota;
        this.tipo_permiso = tipo_permiso;
    }

    public Permiso(int id_incidencia, int id_empleado, Time hora_inicio, Time hora_reinicio, Date fecha_inicio, Date fecha_reinicio, String autorizo, String numdoc, String nota, String tipo_permiso) {
        this.id_incidencia = id_incidencia;
        this.id_empleado = id_empleado;
        this.hora_inicio = hora_inicio;
        this.hora_reinicio = hora_reinicio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_reinicio = fecha_reinicio;
        this.autorizo = autorizo;
        this.numdoc = numdoc;
        this.nota = nota;
        this.tipo_permiso = tipo_permiso;
    }
    
    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public int getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(int id_incidencia) {
        this.id_incidencia = id_incidencia;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_reinicio() {
        return hora_reinicio;
    }

    public void setHora_reinicio(Time hora_reinicio) {
        this.hora_reinicio = hora_reinicio;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_reinicio() {
        return fecha_reinicio;
    }

    public void setFecha_reinicio(Date fecha_reinicio) {
        this.fecha_reinicio = fecha_reinicio;
    }

    public String getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTipo_permiso() {
        return tipo_permiso;
    }

    public void setTipo_permiso(String tipo_permiso) {
        this.tipo_permiso = tipo_permiso;
    }

    @Override
    public String toString() {
        return "Permiso{" + "id_permiso=" + id_permiso + ", id_incidencia=" + id_incidencia + ", id_empleado=" + id_empleado + ", hora_inicio=" + hora_inicio + ", hora_reinicio=" + hora_reinicio + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_reinicio + '}';
    }   
}
