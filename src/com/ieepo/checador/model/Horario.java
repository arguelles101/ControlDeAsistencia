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
public class Horario {

    private int id_horario;
    private int id_turno;
    private Time hora_entrada;
    private Time hora_salida;
    private Time hora_entrada_v;
    private Time hora_salida_v;

    public Horario() {
    }

    public Horario(int id_horario, int id_turno, Time hora_entrada, Time hora_salida, Time hora_entrada_v, Time hora_salida_v) {
        this.id_horario = id_horario;
        this.id_turno = id_turno;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.hora_entrada_v = hora_entrada_v;
        this.hora_salida_v = hora_salida_v;
    }

    public Horario(int id_turno, Time hora_entrada, Time hora_salida, Time hora_entrada_v, Time hora_salida_v) {
        this.id_turno = id_turno;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.hora_entrada_v = hora_entrada_v;
        this.hora_salida_v = hora_salida_v;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public Time getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Time hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Time getHora_entrada_v() {
        return hora_entrada_v;
    }

    public void setHora_entrada_v(Time hora_entrada_v) {
        this.hora_entrada_v = hora_entrada_v;
    }

    public Time getHora_salida_v() {
        return hora_salida_v;
    }

    public void setHora_salida_v(Time hora_salida_v) {
        this.hora_salida_v = hora_salida_v;
    }
}
