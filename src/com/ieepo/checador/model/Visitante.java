/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.model;

import java.sql.Time;

/**
 *
 * @author varguelles
 */
public class Visitante {

    private int id_visita;
    private int id_empresa;
    private int id_area;
    private int id_ct;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String empresa;
    private String motivo;
    private String fecha;
    private Time hora_salida;

    public Visitante() {
    }

    public Visitante(int id_visita, int id_empresa, int id_area, int id_ct, String nombre, String primer_apellido, String segundo_apellido, String empresa, String motivo, String fecha, Time hora_salida) {
        this.id_visita = id_visita;
        this.id_empresa = id_empresa;
        this.id_area = id_area;
        this.id_ct = id_ct;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.empresa = empresa;
        this.motivo = motivo;
        this.fecha = fecha;
        this.hora_salida = hora_salida;
    }

    public int getId_visita() {
        return id_visita;
    }

    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public int getId_ct() {
        return id_ct;
    }

    public void setId_ct(int id_ct) {
        this.id_ct = id_ct;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    @Override
    public String toString() {
        return nombre + " " + primer_apellido + " " + segundo_apellido;
    }

}
