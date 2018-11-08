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
public class Incidencia {

    private int id_incidencia;
    private int id_empleado;
    private Date fecha_hora;
    private int id_ct_local;
    private String movimiento;
    private String tipo;

    public Incidencia(int id_incidencia, int id_empleado, Date fecha_hora, int id_ct_local, String movimiento, String tipo) {
        this.id_incidencia = id_incidencia;
        this.id_empleado = id_empleado;
        this.fecha_hora = fecha_hora;
        this.id_ct_local = id_ct_local;
        this.movimiento = movimiento;
        this.tipo = tipo;
    }

    public Incidencia() {
    }

    public Incidencia(int id_empleado, Date fecha_hora, int id_ct_local, String movimiento, String tipo) {
        this.id_empleado = id_empleado;
        this.fecha_hora = fecha_hora;
        this.id_ct_local = id_ct_local;
        this.movimiento = movimiento;
        this.tipo = tipo;
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

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getId_ct_local() {
        return id_ct_local;
    }

    public void setId_ct_local(int id_ct_local) {
        this.id_ct_local = id_ct_local;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "id_incidencia=" + id_incidencia + ", id_empleado=" + id_empleado + ", fecha_hora=" + fecha_hora + ", id_ct_local=" + id_ct_local + ", movimiento=" + movimiento + ", tipo=" + tipo + '}';
    }

}
