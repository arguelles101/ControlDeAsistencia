/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador;

import com.ieepo.checador.components.Imagen;
import com.ieepo.checador.db.ConnectionBD;
import com.ieepo.checador.model.Empleado;
import com.ieepo.checador.model.Horario;
import com.ieepo.checador.model.HorarioEmpleado;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author varguelles
 */
public class Checador extends javax.swing.JApplet {
    private String hora,minutos,segundos,ampm, dia, mes, noDia;
    private Calendar calendario;
    
    private final int minutos_normal = 10;
    private final int minutos_retardo = 30;
    
    private DigitalPersona dp;
    
    private final int id_ct = 6155;
    
    TimerTask task;
    TimerTask taskHuellas;
    
    Boolean adminActivo = false;
    Boolean cuentaActiva = false;
    int checar;
    int estaLogin; //para saber si la pantalla esta en login
    
    ArrayList<Empleado> empleados;
    int id_empleado;
    private Boolean status; //Variable para saber si se esta utilizando el dispositivo en algun dedo
    JButton dedo;
    
    /**
     * Initializes the applet Checador
     */
    @Override
    public void init() {

        try {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
            */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
            /* Create and display the applet */
            
            java.awt.EventQueue.invokeAndWait(() -> {
                initComponents();
                setSize(new Dimension(1000, 700));
                setMinimumSize(new Dimension(800, 500));
                
                jpFondo.setVisible(false);
                
                Imagen i = new Imagen("com/ieepo/images/logo.png", (int)(jpLogo.getPreferredSize().width*0.4), jpLogoPng.getPreferredSize().height);
                jpLogoPng.add(i);
                jpLogoPng.repaint();
                
                dp = new DigitalPersona();
                dp.Iniciar();
                dp.start();
                
                
                
                hora();
                final Runnable tarea = () -> {
                    hora();
                };
                
                ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
                timer.scheduleAtFixedRate(tarea, 1, 1, TimeUnit.SECONDS);
            });
            
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
       
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpLogo = new org.jdesktop.swingx.JXPanel();
        jpLogoPng = new org.jdesktop.swingx.JXPanel();
        jpSection = new org.jdesktop.swingx.JXPanel();
        jpChecador = new org.jdesktop.swingx.JXPanel();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        lbHora = new org.jdesktop.swingx.JXLabel();
        lbDia = new org.jdesktop.swingx.JXLabel();
        lbMes = new org.jdesktop.swingx.JXLabel();
        btnAcceder = new javax.swing.JButton();
        jpFondo = new org.jdesktop.swingx.JXPanel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(400, 200));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jpLogo.setBackground(new java.awt.Color(255, 255, 255));
        jpLogo.setPreferredSize(new java.awt.Dimension(1000, 100));

        jpLogoPng.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpLogoPngLayout = new javax.swing.GroupLayout(jpLogoPng);
        jpLogoPng.setLayout(jpLogoPngLayout);
        jpLogoPngLayout.setHorizontalGroup(
            jpLogoPngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jpLogoPngLayout.setVerticalGroup(
            jpLogoPngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpLogoLayout = new javax.swing.GroupLayout(jpLogo);
        jpLogo.setLayout(jpLogoLayout);
        jpLogoLayout.setHorizontalGroup(
            jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLogoLayout.createSequentialGroup()
                .addGap(0, 500, Short.MAX_VALUE)
                .addComponent(jpLogoPng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpLogoLayout.setVerticalGroup(
            jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpLogoPng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpSection.setBackground(new java.awt.Color(255, 255, 255));
        jpSection.setPreferredSize(new java.awt.Dimension(1000, 500));

        jpChecador.setBackground(new java.awt.Color(255, 255, 255));
        jpChecador.setPreferredSize(new java.awt.Dimension(1000, 500));

        jXPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jXPanel1.setPreferredSize(new java.awt.Dimension(400, 300));

        lbHora.setForeground(new java.awt.Color(51, 153, 255));
        lbHora.setText("10:49:50");
        lbHora.setToolTipText("");
        lbHora.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N

        lbDia.setForeground(new java.awt.Color(51, 153, 255));
        lbDia.setText("MIÉRCOLES 10");
        lbDia.setToolTipText("");
        lbDia.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N

        lbMes.setForeground(new java.awt.Color(51, 153, 255));
        lbMes.setText("Septiembre");
        lbMes.setToolTipText("");
        lbMes.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        btnAcceder.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAcceder.setText("Acceder");

        javax.swing.GroupLayout jpChecadorLayout = new javax.swing.GroupLayout(jpChecador);
        jpChecador.setLayout(jpChecadorLayout);
        jpChecadorLayout.setHorizontalGroup(
            jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpChecadorLayout.createSequentialGroup()
                .addContainerGap(336, Short.MAX_VALUE)
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(264, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpChecadorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAcceder)
                .addGap(125, 125, 125))
        );
        jpChecadorLayout.setVerticalGroup(
            jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChecadorLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnAcceder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jpFondo.setPreferredSize(new java.awt.Dimension(1000, 500));

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpSectionLayout = new javax.swing.GroupLayout(jpSection);
        jpSection.setLayout(jpSectionLayout);
        jpSectionLayout.setHorizontalGroup(
            jpSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSectionLayout.createSequentialGroup()
                .addGroup(jpSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpChecador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpSectionLayout.setVerticalGroup(
            jpSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSectionLayout.createSequentialGroup()
                .addComponent(jpChecador, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void hora(){
        calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();


        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        
        
        if(ampm.equals("PM")){
            int h = calendario.get(Calendar.HOUR_OF_DAY); //-12
            hora = h>9?""+h:"0"+h;
        }else{
            hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);            
        }
        minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
        
        noDia = calendario.get(Calendar.DAY_OF_MONTH) + "";
        mes = (calendario.get(Calendar.MONTH)+1)>9?(calendario.get(Calendar.MONTH)+1)+"":"0"+(calendario.get(Calendar.MONTH)+1);
        
        
        dia = getDia(calendario.get(Calendar.DAY_OF_WEEK));
        mes = getMes(mes);
        
        lbHora.setText(hora + ":" + minutos + ":" + segundos);
        lbDia.setText(dia + " " + noDia);
        lbMes.setText("DE " + mes);
        
        //lbHoraVisitantes.setText(hora + ":" + minutos + ":" + segundos);
        //lbMesVisitantes.setText(dia + " " + noDia + " DE " + mes);
        
        validarActivo(); 
    }
    
    private void validarActivo(){
       
        Boolean activo = dp.getActivo();
        System.out.println("activo = " + activo);
        System.out.println("dp.getActivo() = " + dp.getActivo());
        if(activo && !adminActivo){
            try{
                ConnectionBD sql = new ConnectionBD();
                
                
                Connection cn = sql.conectar();
                PreparedStatement consulta;
                consulta = cn.prepareStatement("SELECT * FROM huella");
                ResultSet resultado = consulta.executeQuery();
                while(resultado.next()){
                    byte templateBuffer[] = resultado.getBytes("huella");
                    if(dp.verificarHuella(templateBuffer)){
                        mandar(resultado.getInt("idempleado"));
                        dp.clear();
                    }
                    else{
                        System.out.println("no");
                    }
                }
                dp.clear();
            } catch (SQLException ex) {
                Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }
    }
    
    private void mandar(int id_empleado){
        try {
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM horarioempleado WHERE idempleado = ?");
            consulta.setInt(1, id_empleado);
            ResultSet resultado = consulta.executeQuery();
            System.out.println(id_empleado);
            System.out.println("consulta: " + consulta);
                
            if(resultado.next()){
                int id_horario_empleado;
                Date fecha_asigna_hora;
                int id_horario;
                int id_area_e;
                
                id_horario_empleado = resultado.getInt("idhorarioempleado");
                fecha_asigna_hora = resultado.getDate("fechaasignahora");
                id_horario = resultado.getInt("idhorario");
                id_area_e = resultado.getInt("idarea");
                
                HorarioEmpleado he = new HorarioEmpleado(id_horario_empleado, id_empleado, id_ct, fecha_asigna_hora, id_horario, id_area_e);
                
                
                consulta = cn.prepareStatement("SELECT * FROM horarios WHERE idhorario = ?");
                consulta.setInt(1, id_horario);
                ResultSet resultadoH = consulta.executeQuery(); 
                if(resultadoH.next()){
                    int id_turno;
                    Time hora_entrada;
                    Time hora_salida;
                    Time hora_entrada_v;
                    Time hora_salida_v;
                    
                    id_turno = resultadoH.getInt("idturno");
                    hora_entrada = resultadoH.getTime("horaentrada");
                    hora_salida = resultadoH.getTime("horasalida");
                    hora_entrada_v = resultadoH.getTime("horaentradav");
                    hora_salida_v = resultadoH.getTime("horasalidav");
                    
                    Horario h = new Horario(id_horario, id_turno, hora_entrada, hora_salida, hora_entrada_v, hora_salida_v);
                    
                    calendario = new GregorianCalendar();
                    java.util.Date d = new java.util.Date();
                    
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String fecha = dateFormat.format(date);
                    System.out.println(dateFormat.format(date));
                    System.out.println(fecha.substring(0, 11));
                    
                    consulta = cn.prepareStatement("SELECT * FROM incidencias WHERE idctlocal = ? and idempleado = ? and fechahora like ?");
                    consulta.setInt(1, id_ct);
                    consulta.setInt(2, id_empleado);
                    consulta.setString(3, fecha.substring(0, 11)+"%");
                    ResultSet resultadoIncidencias = consulta.executeQuery();
                    int contador = 0; 
                    
                    if(hora_entrada_v == null && hora_salida_v == null){
                        System.out.println("dos veces");
                        System.out.println(consulta);
                        while(resultadoIncidencias.next()){
                            contador++;
                        }
                        
                        if(contador == 2){
                            System.out.println("Ya no puede registrarte mas de dos veces en el dia");
                            JOptionPane.showMessageDialog(null, "Ya no puedes registrarte mas en este dia","Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            if(contador == 1){
                                System.out.println("salida");
                                Time horaEComparar = new Time( Integer.parseInt(fecha.substring(11,13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)) );
                                Time horaDBComparar = h.getHora_salida();
                                
                                String tipo = compararSalida(horaEComparar, horaDBComparar);
                                PreparedStatement ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)");
                                ps.setInt(1, id_empleado);
                                ps.setString(2, fecha);
                                ps.setInt(3, id_ct);
                                ps.setString(4, "SALIDA");
                                ps.setString(5, tipo);
                                System.out.println(ps);
                                ps.executeUpdate();
                                
                                bienvenido(id_empleado, fecha, id_ct, "SALIDA", tipo);
                            }
                            else{
                                System.out.println("entrada"); 
                                Time horaEComparar = new Time( Integer.parseInt(fecha.substring(11,13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)) );
                                Time horaDBComparar = h.getHora_entrada();
                                
                                String tipo = compararEntrada(horaEComparar, horaDBComparar);
                                PreparedStatement ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)");
                                ps.setInt(1, id_empleado);
                                ps.setString(2, fecha);
                                ps.setInt(3, id_ct);
                                ps.setString(4, "ENTRADA");
                                ps.setString(5, tipo);
                                ps.executeUpdate(); 
                                
                                bienvenido(id_empleado, fecha, id_ct, "ENTRADA", tipo);
                            }
                            /////////////////////
                            
                        }    
                    }
                    else{
                        System.out.println("cuatro veces");
                        System.out.println(consulta);
                        while(resultadoIncidencias.next()){
                            contador++;
                        }
                        
                        if(contador == 4){
                            System.out.println("Ya no puede registrarte mas de dos veces en el dia");
                            JOptionPane.showMessageDialog(null, "Ya no puedes registrarte mas en este dia","Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            Time horaEComparar;
                            Time horaDBComparar;
                            String tipo;
                            PreparedStatement ps;
                            switch(contador){
                                case 0:
                                    System.out.println("entrada");
                                    horaEComparar = new Time( Integer.parseInt(fecha.substring(11,13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)) );
                                    horaDBComparar = h.getHora_entrada();

                                    tipo = compararEntrada(horaEComparar, horaDBComparar);
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)");
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "ENTRADA");
                                    ps.setString(5, tipo);
                                    ps.executeUpdate(); 
                                    bienvenido(id_empleado, fecha, id_ct, "ENTRADA", tipo);
                                    break;
                                case 1: 
                                    System.out.println("salida");
                                    horaEComparar = new Time( Integer.parseInt(fecha.substring(11,13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)) );
                                    horaDBComparar = h.getHora_salida();

                                    tipo = compararSalida(horaEComparar, horaDBComparar);
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)");
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "SALIDA");
                                    ps.setString(5, tipo);
                                    ps.executeUpdate();
                                    bienvenido(id_empleado, fecha, id_ct, "SALIDA", tipo);
                                    break;
                                case 2: 
                                    System.out.println("entrada_v");
                                    horaEComparar = new Time( Integer.parseInt(fecha.substring(11,13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)) );
                                    horaDBComparar = h.getHora_entrada_v();

                                    tipo = compararEntrada(horaEComparar, horaDBComparar);
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)");
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "ENTRADA");
                                    ps.setString(5, tipo);
                                    ps.executeUpdate();
                                    bienvenido(id_empleado, fecha, id_ct, "ENTRADA", tipo);
                                    break;
                                case 3: 
                                    System.out.println("salida_v");
                                    horaEComparar = new Time( Integer.parseInt(fecha.substring(11,13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)) );
                                    horaDBComparar = h.getHora_salida_v();

                                    tipo = compararSalida(horaEComparar, horaDBComparar);
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)");
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "SALIDA");
                                    ps.setString(5, tipo);
                                    ps.executeUpdate();
                                    bienvenido(id_empleado, fecha, id_ct, "SALIDA", tipo);
                                    break;
                            }
                        }
                    }
                }
                else{
                    System.out.println("Error");
                }
            }
            else{
                System.out.println("Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String compararEntrada(Time horaEComparar, Time horaDBComparar){
        if(horaEComparar.before(horaDBComparar)){
            return "";
        }
        
        Time t = new Time(horaEComparar.getTime() - horaDBComparar.getTime());
        if(t.getHours()!= 18 ){
            return "FALTA";
        } 
        if(t.getMinutes() < minutos_normal){
            return "";
        }
        if(t.getMinutes() < minutos_retardo){
            return "RETARDO";
        }
        return "FALTA";
    }

    private String compararSalida(Time horaEComparar, Time horaDBComparar){
        System.out.println(" E: " + horaEComparar);
        System.out.println("DB: " + horaDBComparar);
        System.out.println(horaEComparar.after(horaDBComparar));
        
        if(horaEComparar.after(horaDBComparar)){
            return "";
        }
        Time t = new Time(horaDBComparar.getTime() - horaEComparar.getTime());
        if(t.getHours() == 18  && t.getMinutes() < minutos_normal){
            return "";
        }
        return "ANTICIPADA";
    }

    private void bienvenido(int id_empleado, String fecha, int id_ct, String movimiento, String tipo){
        try{
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado = ?");
            consulta.setInt(1, id_empleado);
            ResultSet resultado = consulta.executeQuery();
            
            if(resultado.next()){
                int idEmpleado;
                String nombre;
                String apPaterno;
                String apMaterno;
                String rfc;
                int idct;
                
                idEmpleado = resultado.getInt("idempleado");
                nombre = resultado.getString("nombre").trim();
                apPaterno = resultado.getString("apPaterno").trim();
                apMaterno = resultado.getString("apMaterno").trim();
                rfc = resultado.getString("rfc").trim();
                idct = resultado.getInt("idct");
                
                
                Empleado e = new Empleado(idEmpleado, nombre, apPaterno, apMaterno, rfc, idct);
                
                
                
    
                
                Date datt = new Date();
                calendario.setTime(datt);
                
                mes = (calendario.get(Calendar.MONTH)+1)>9?(calendario.get(Calendar.MONTH)+1)+"":"0"+(calendario.get(Calendar.MONTH)+1);
                
                dia = getDia(calendario.get(Calendar.DAY_OF_WEEK));
                mes = getMes(mes);
                
                /*
                lbNombre.setText(e.string());
                lbHoraB.setText(fecha.substring(11));
                lbAviso.setText(movimiento + " " + tipo);
                lbDiaB.setText(dia + " " + noDia + " DE " + mes);
                
                if(tipo.equals("")){
                    lbHoraB.setForeground(Color.blue);
                    lbDiaB.setForeground(Color.blue);
                }
                else{
                    lbHoraB.setForeground(Color.red);
                    lbDiaB.setForeground(Color.red);
                }
                
                if(movimiento.equals("ENTRADA")){
                    lbBienvenido.setText("Bienvenido:");
                }
                if(movimiento.equals("SALIDA")){
                    lbBienvenido.setText("Adios:");
                }
                
                lbNombre.setText(e.string());
                lbHoraB.setText(fecha.substring(11));
                lbAviso.setText(movimiento + " " + tipo);
                */

                //JOptionPane.showMessageDialog(this, lbBienvenido.getText() + " " + e.string());
                
                
                int TiempoSegundos = 4;
                Timer tiempo ;
                
                tiempo = new Timer();
                task = new TimerTask() {
                    int contador=0;
                    @Override
                    public void run() {
                        try {
                            contador++;
                            if(contador == TiempoSegundos){
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ESCAPE);
                                System.out.println("se presiono");
                                task.cancel();
                            }
                            
                        } catch (AWTException ex) {
                            Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }    
                };        
                tiempo.schedule(task,0,1000);  
                
                
                String imp = "<html>" + e.toString() + "<br>" + fecha.substring(11) + "<br>" + movimiento + "<br>" + tipo + "</html>";
                JOptionPane.showOptionDialog(null, imp, movimiento, JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
                /*JOptionPane jo = new JOptionPane();
               //jo.showMessageDialog(null, imp, lbBienvenido.getText(), JOptionPane.DEFAULT_OPTION);
                int TiempoSegundos = 4;
                
                

                //JOptionPane pane = new JOptionPane(imp, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, null, null);
                JOptionPane pane = new JOptionPane(imp, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, null, null);
                

                JDialog dialog = new JDialog();
                dialog.setTitle(lbBienvenido.getText().substring(0, lbBienvenido.getText().length()-1));
                dialog.setModal(true);
                
                dialog.add(pane);
                //dialog.pack();
                //dialog.setLocationRelativeTo(null);
                dialog.setSize(300, 300); // This is bad idea, use an EmptyBorder instead
                int x = this.location().x + (this.getWidth()/2) - dialog.getWidth()/2;
                int y = this.location().y + (this.getHeight()/2) - dialog.getHeight()/2;
                dialog.setLocation(x, y);
                dialog.setVisible(true);
                dialog.set
                
                
                
                
                /*
                // ... conectar...
                Timer tiempo ;
                
                tiempo = new Timer();
                task = new TimerTask() {
                    int contador=0;
                    public void run() {
                        try {
                            contador++;
                            if(contador == TiempoSegundos){
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ESCAPE);
                                System.out.println("se presiono");
                                task.cancel();
                            }
                            
                        } catch (AWTException ex) {
                            Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }    
                };        
                tiempo.schedule(task,0,1000);  
                
                JOptionPane.showOptionDialog(null, imp, lbBienvenido.getText(), JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                */
            }
        } catch (SQLException ex) {
            Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private String getDia(int c){
        String dia_aux;
        switch(c){
            case 1: 
                dia_aux = "DOMINGO";
                break;
            case 2: 
                dia_aux = "LUNES";
                break;
            case 3: 
                dia_aux = "MARTES";
                break;
            case 4: 
                dia_aux = "MIÉRCOLES";
                break;
            case 5: 
                dia_aux = "JUEVES";
                break;
            case 6: 
                dia_aux = "VIERNES";
                break;
            case 7: 
                dia_aux = "SÁBADO";
                break;
            default:
                dia_aux = "";
        }
        return dia_aux;
    }
    
    private String getMes(String c){
        String mes_aux;
        switch(c){
            case "01":
                mes_aux = "ENRERO";
                break;
            case "02":
                mes_aux = "FEBRERO";
                break;
            case "03":
                mes_aux = "MARZO";
                break;
            case "04":
                mes_aux = "ABRIL";
                break;
            case "05":
                mes_aux = "MAYO";
                break;
            case "06":
                mes_aux = "JUNIO";
                break;
            case "07":
                mes_aux = "JULIO";
                break;
            case "08":
                mes_aux = "AGOSTO";
                break;
            case "09":
                mes_aux = "SEPTIEMBRE";
                break;
            case "10":
                mes_aux = "OCTUBRE";
                break;
            case "11":
                mes_aux = "NOVIEMBRE";
                break;
            case "12":
                mes_aux = "DICIEMBRE";
                break;
            default:
                mes_aux = "";
        }
        return mes_aux;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcceder;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jpChecador;
    private org.jdesktop.swingx.JXPanel jpFondo;
    private org.jdesktop.swingx.JXPanel jpLogo;
    private org.jdesktop.swingx.JXPanel jpLogoPng;
    private org.jdesktop.swingx.JXPanel jpSection;
    private org.jdesktop.swingx.JXLabel lbDia;
    private org.jdesktop.swingx.JXLabel lbHora;
    private org.jdesktop.swingx.JXLabel lbMes;
    // End of variables declaration//GEN-END:variables
}
