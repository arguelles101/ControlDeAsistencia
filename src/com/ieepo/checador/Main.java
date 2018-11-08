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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
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

    public void main() {
        try {

            Map<Integer, String[]> a = new HashMap<>();
            a.put(1, new String[2]);
            a.get(1)[0] = "01-01";
            a.get(1)[1] = "01-15";
            a.put(2, new String[2]);
            a.get(2)[0] = "01-16";
            a.get(2)[1] = "01-31";
            a.put(3, new String[2]);
            a.get(3)[0] = "02-01";
            a.get(3)[1] = "02-15";
            a.put(4, new String[2]);
            a.get(4)[0] = "02-16";
            a.get(4)[1] = "02-28";
            a.put(5, new String[2]);
            a.get(5)[0] = "03-01";
            a.get(5)[1] = "03-16";
            a.put(6, new String[2]);
            a.get(6)[0] = "03-16";
            a.get(6)[1] = "03-31";
            a.put(7, new String[2]);
            a.get(7)[0] = "04-01";
            a.get(7)[1] = "04-15";
            a.put(8, new String[2]);
            a.get(8)[0] = "04-16";
            a.get(8)[1] = "04-30";
            a.put(9, new String[2]);
            a.get(9)[0] = "05-01";
            a.get(9)[1] = "05-16";
            a.put(10, new String[2]);
            a.get(10)[0] = "05-16";
            a.get(10)[1] = "05-31";

            a.put(11, new String[2]);
            a.get(11)[0] = "06-01";
            a.get(11)[1] = "06-15";
            a.put(12, new String[2]);
            a.get(12)[0] = "06-16";
            a.get(12)[1] = "06-30";
            a.put(13, new String[2]);
            a.get(13)[0] = "07-01";
            a.get(13)[1] = "07-16";
            a.put(14, new String[2]);
            a.get(14)[0] = "07-16";
            a.get(14)[1] = "07-31";

            a.put(15, new String[2]);
            a.get(15)[0] = "08-01";
            a.get(15)[1] = "08-15";
            a.put(16, new String[2]);
            a.get(16)[0] = "08-16";
            a.get(16)[1] = "08-31";
            a.put(17, new String[2]);
            a.get(17)[0] = "09-01";
            a.get(17)[1] = "09-16";
            a.put(18, new String[2]);
            a.get(18)[0] = "09-16";
            a.get(18)[1] = "09-30";

            a.put(19, new String[2]);
            a.get(19)[0] = "10-01";
            a.get(19)[1] = "10-15";
            a.put(20, new String[2]);
            a.get(20)[0] = "10-16";
            a.get(20)[1] = "10-31";
            a.put(21, new String[2]);
            a.get(21)[0] = "11-01";
            a.get(21)[1] = "11-16";
            a.put(22, new String[2]);
            a.get(22)[0] = "11-16";
            a.get(22)[1] = "11-30";

            a.put(23, new String[2]);
            a.get(23)[0] = "12-01";
            a.get(23)[1] = "12-15";
            a.put(24, new String[2]);
            a.get(24)[0] = "12-16";
            a.get(24)[1] = "12-31";

            int mes = new GregorianCalendar().get(Calendar.MONTH) + 1;
            int dia = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
            System.out.println("mes = " + mes);
            System.out.println("dia = " + dia);
            int quincenaActual;

            if (dia > 15) {
                quincenaActual = mes * 2;
            } else {
                quincenaActual = mes * 2 - 1;
            }

            int quincena = 0;
            do {
                for (int i = 1; i <= quincenaActual; i++) {
                    System.out.println(i + ".- Quincena " + i);

                }
                Scanner sc = new Scanner(System.in);
                System.out.println("Ingrese la quincena: ");
                quincena = sc.nextInt();
            } while (quincena<1 || quincena>quincenaActual);

            //a.put(1, new HashMap<>(a));
            System.out.println("quincena = " + quincena);

            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            //String inicio = "2018-11-01";
            //String fin = "2018-11-15";

            String inicio = new GregorianCalendar().get(Calendar.YEAR) + "-" + a.get(quincena)[0];
            String fin = new GregorianCalendar().get(Calendar.YEAR) + "-" + a.get(quincena)[1];
            //String inicio = 2017 + "-" + a.get(quincena)[0];
            //String fin = 2017+ "-" + a.get(quincena)[1];

            System.out.println("inicio = " + inicio);
            System.out.println("fin = " + fin);

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
                    if (!incidencia.getTipo().equals("")) {

                        if (incidencia.getTipo().equals(RETARDO)) {

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
/*
class NestedHashmap {
    private Map<String, Map<String, Map<String, String>>> map;

    public NestedHashmap() { 
        map = new HashMap<String, HashMap<String, HashMap<String, String>>>;
    }

    public put(String date, String title, String link, String value){
        if(map.get(date) == null) { 
            map.put(date, new HashMap<String, HashMap<String, String>>;
        }
        if(map.get(date).get(title) == null){
            map.get(date).put(new HashMap<String, String>);
        }
        map.get(date).get(title).put(link, value);
    }

    public get(String date, String title, String link) {
        // ...mostly analogous to put...
    }
}

 */
