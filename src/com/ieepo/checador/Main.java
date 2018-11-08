/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador;

import com.ieepo.checador.db.ConnectionBD;
import com.ieepo.checador.model.Empleado;
import com.ieepo.checador.model.Incidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author varguelles
 */
public class Main {

    private final int id_ct = 6155;
    private final String SALIDA = "SALIDA";
    private final String ENTRADA = "ENTRADA";
    private final String PERMISO = "PERMISO";
    private final String FALTA = "FALTA";
    private final String RETARDO = "RETARDO";
    private final String ANTICIPADA = "ANTICIPADA";

    public static void main(String[] args) {
        Main main = new Main();
        main.main();
    }
    
    public void main(){
        try {
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            String inicio = "2018-11-01";
            String fin = "2018-11-15";

            PreparedStatement consulta;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String cadena = "SELECT * FROM incidencias WHERE idctlocal = " + id_ct + " AND (";
            int count = 0;
            while (!inicio.equals(fin)) {
                if (count == 0) {
                    cadena += "fechahora LIKE '" + inicio + "%' ";
                } else {
                    cadena += "OR fechahora LIKE '" + inicio + "%' ";
                }
                Date aux = new Date(Integer.parseInt(inicio.substring(0, 4)) - 1900, Integer.parseInt(inicio.substring(5, 7)) - 1, Integer.parseInt(inicio.substring(8, 10)) + 1);
                inicio = dateFormat.format(aux);
                count++;
            }
            cadena += ")";
            consulta = cn.prepareStatement(cadena + " AND idempleado IN (SELECT idempleado FROM empleados WHERE idct = ? ORDER BY nombre DESC) ORDER BY idempleado DESC, fechahora");
            consulta.setInt(1, id_ct);
            ResultSet resultado = consulta.executeQuery();
            ArrayList<Incidencia> incidencias = new ArrayList<>();
            while (resultado.next()) {
                int id_incidencia;
                int id_empleado;
                Date fecha_hora;
                int id_ct_local;
                String movimiento;
                String tipo;

                id_incidencia = resultado.getInt("idincidencia");
                id_empleado = resultado.getInt("idempleado");
                fecha_hora = (Date) resultado.getObject("fechahora");
                id_ct_local = resultado.getInt("idctlocal");
                movimiento = resultado.getString("movimiento");
                tipo = resultado.getString("tipo");

                Incidencia i = new Incidencia(id_incidencia, id_empleado, fecha_hora, id_ct_local, movimiento, tipo);
                incidencias.add(i);
            }

            if (incidencias.size() > 0) {
                Empleado empleado = null;
                int id_empleado = incidencias.get(0).getId_empleado();
                consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado = ?");
                consulta.setInt(1, id_empleado);
                resultado = consulta.executeQuery();
                if (resultado.next()) {
                    String nombre;
                    String apPaterno;
                    String apMaterno;
                    String rfc;

                    nombre = resultado.getString("nombre");
                    apPaterno = resultado.getString("appaterno");
                    apMaterno = resultado.getString("apmaterno");
                    rfc = resultado.getString("rfc");

                    empleado = new Empleado(id_empleado, nombre, apPaterno, apMaterno, rfc, id_ct);
                }
                id_empleado = 0;
                for (Incidencia incidencia : incidencias) {
                    if (id_empleado != incidencia.getId_empleado()) {
                        id_empleado = incidencia.getId_empleado();
                        consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado = ?");
                        consulta.setInt(1, id_empleado);
                        resultado = consulta.executeQuery();
                        if (resultado.next()) {
                            String nombre;
                            String apPaterno;
                            String apMaterno;
                            String rfc;

                            nombre = resultado.getString("nombre");
                            apPaterno = resultado.getString("appaterno");
                            apMaterno = resultado.getString("apmaterno");
                            rfc = resultado.getString("rfc");

                            empleado = new Empleado(id_empleado, nombre, apPaterno, apMaterno, rfc, id_ct);
                            System.out.println("");
                            System.out.println("");
                            System.out.println(empleado);
                        }
                    }
                    System.out.println(incidencia.getFecha_hora().toString().substring(0, 11));
                    if(!incidencia.getTipo().equals("")){
                        
                        if(incidencia.getTipo().equals(RETARDO)){
                            
                        }
                        
                        System.out.println(incidencia.getTipo());
                    }
                    
                    id_empleado = incidencia.getId_empleado();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
