/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.model;

import java.util.Date;

/**
 *
 * @author varguelles
 */
public class HorarioEmpleado {
    private int id_horario_empleado;
    private int id_empleado;
    private int id_ct;
    private Date fecha_asigna_hora;
    private int id_horario;
    private int id_area;
    private int activo;
    private int excepcion;

    public HorarioEmpleado() {
    }

    public HorarioEmpleado(int id_horario_empleado, int id_empleado, int id_ct, Date fecha_asigna_hora, int id_horario, int id_area) {
        this.id_horario_empleado = id_horario_empleado;
        this.id_empleado = id_empleado;
        this.id_ct = id_ct;
        this.fecha_asigna_hora = fecha_asigna_hora;
        this.id_horario = id_horario;
        this.id_area = id_area;
    }

    public HorarioEmpleado(int id_empleado, int id_ct, Date fecha_asigna_hora, int id_horario, int id_area) {
        this.id_empleado = id_empleado;
        this.id_ct = id_ct;
        this.fecha_asigna_hora = fecha_asigna_hora;
        this.id_horario = id_horario;
        this.id_area = id_area;
    }

    public HorarioEmpleado(int id_horario_empleado, int id_empleado, int id_ct, Date fecha_asigna_hora, int id_horario, int id_area, int activo) {
        this.id_horario_empleado = id_horario_empleado;
        this.id_empleado = id_empleado;
        this.id_ct = id_ct;
        this.fecha_asigna_hora = fecha_asigna_hora;
        this.id_horario = id_horario;
        this.id_area = id_area;
        this.activo = activo;
    }

    public int getId_horario_empleado() {
        return id_horario_empleado;
    }

    public void setId_horario_empleado(int id_horario_empleado) {
        this.id_horario_empleado = id_horario_empleado;
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

    public Date getFecha_asigna_hora() {
        return fecha_asigna_hora;
    }

    public void setFecha_asigna_hora(Date fecha_asigna_hora) {
        this.fecha_asigna_hora = fecha_asigna_hora;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(int excepcion) {
        this.excepcion = excepcion;
    }
    
    @Override
    public String toString() {
        return "HorarioEmpleado{" + "id_horario_empleado=" + id_horario_empleado + ", id_empleado=" + id_empleado + ", id_ct=" + id_ct + ", fecha_asigna_hora=" + fecha_asigna_hora + ", id_horario=" + id_horario + ", id_area=" + id_area + ", activo=" + activo + ", excepcion=" + excepcion + '}';
    }
}
