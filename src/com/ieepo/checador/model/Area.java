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
public class Area {

    private int id_area;
    private int id_ct;
    private String nombre_area;
    private String nombre_responsable;
    private int activo;

    public Area() {
    }

    public Area(int id_area, int id_ct, String nombre_area, String nombre_responsable, int activo) {
        this.id_area = id_area;
        this.id_ct = id_ct;
        this.nombre_area = nombre_area;
        this.nombre_responsable = nombre_responsable;
        this.activo = activo;
    }

    public Area(int id_area, int id_ct, String nombre_area, String nombre_responsable) {
        this.id_area = id_area;
        this.id_ct = id_ct;
        this.nombre_area = nombre_area;
        this.nombre_responsable = nombre_responsable;
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

    public String getNombre_area() {
        return nombre_area;
    }

    public void setNombre_area(String nombre_area) {
        this.nombre_area = nombre_area;
    }

    public String getNombre_responsable() {
        return nombre_responsable;
    }

    public void setNombre_responsable(String nombre_responsable) {
        this.nombre_responsable = nombre_responsable;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Area{" + "id_area=" + id_area + ", id_ct=" + id_ct + ", nombre_area=" + nombre_area + ", nombre_responsable=" + nombre_responsable + '}';
    }
}
