/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador;

import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_READY;
import com.ieepo.checador.components.Imagen;
import com.ieepo.checador.db.ConnectionBD;
import com.ieepo.checador.model.AdminCt;
import com.ieepo.checador.model.Empleado;
import com.ieepo.checador.model.Horario;
import com.ieepo.checador.model.HorarioEmpleado;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
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
import java.security.*;
import java.util.StringTokenizer;

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
    Runnable tareaLogin;
    ScheduledExecutorService timerLogin;
    
    ArrayList<Empleado> admins;
    Boolean adminActivo = false;
    Boolean cuentaActiva = false;
    int checar;
    int estaLogin; //para saber si la pantalla esta en login
    
    ArrayList<Empleado> empleados;
    int id_empleado;
    private Boolean status; //Variable para saber si se esta utilizando el dispositivo en algun dedo
    JButton dedo;
    Boolean ponerAlert;
    
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
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
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
    
    /*@Override
    public void  init(){ 
        AccessController.doPrivileged((PrivilegedAction) () -> {
            try{
                // Código del método INIT
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
                
                String property = System.getProperty("java.library.path");
                StringTokenizer parser = new StringTokenizer(property, ";");
                while (parser.hasMoreTokens()) {
                    System.err.println(parser.nextToken());
                }
                
                
            }
            catch(Exception e){
                Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, e);
            }
            return null;
        });
    }*/


 


 
    

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
        jpLogin = new org.jdesktop.swingx.JXPanel();
        btnRegresar = new javax.swing.JButton();
        cmbAdmin = new javax.swing.JComboBox<>();
        jpHuellas = new org.jdesktop.swingx.JXPanel();
        jpPanelHuellas = new javax.swing.JPanel();
        lbEmpleado = new javax.swing.JLabel();
        cbEmpleados = new javax.swing.JComboBox<>();
        jpHuellasFondo = new javax.swing.JPanel();
        btn1d = new javax.swing.JButton();
        btn2d = new javax.swing.JButton();
        btn3d = new javax.swing.JButton();
        btn4d = new javax.swing.JButton();
        btn5d = new javax.swing.JButton();
        btn5i = new javax.swing.JButton();
        btn4i = new javax.swing.JButton();
        btn3i = new javax.swing.JButton();
        btn2i = new javax.swing.JButton();
        btn1i = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        btnCerrarSesion = new javax.swing.JButton();

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
        btnAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccederActionPerformed(evt);
            }
        });

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
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jpFondo.setPreferredSize(new java.awt.Dimension(1000, 500));

        jpLogin.setBackground(new java.awt.Color(255, 255, 255));
        jpLogin.setPreferredSize(new java.awt.Dimension(1000, 500));

        btnRegresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cmbAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpLoginLayout = new javax.swing.GroupLayout(jpLogin);
        jpLogin.setLayout(jpLoginLayout);
        jpLoginLayout.setHorizontalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addGap(125, 125, 125))
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(cmbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jpLoginLayout.setVerticalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnRegresar)
                .addGap(18, 18, 18)
                .addComponent(cmbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(387, Short.MAX_VALUE))
        );

        jpHuellas.setBackground(new java.awt.Color(255, 255, 255));
        jpHuellas.setPreferredSize(new java.awt.Dimension(1000, 500));

        jpPanelHuellas.setBackground(new java.awt.Color(255, 255, 255));
        jpPanelHuellas.setPreferredSize(new java.awt.Dimension(500, 400));

        lbEmpleado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbEmpleado.setText("Seleccione el empleado:");

        cbEmpleados.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbEmpleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maximiliano Perez Luna", "Pedro Luis Martinez Guzman", "Jose Manuel Eugenio Luna", "Victor Manuel Arguelles Alcazar" }));
        cbEmpleados.setPreferredSize(new java.awt.Dimension(300, 21));
        cbEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEmpleadosActionPerformed(evt);
            }
        });

        jpHuellasFondo.setBackground(new java.awt.Color(204, 204, 204));
        jpHuellasFondo.setPreferredSize(new java.awt.Dimension(375, 245));

        btn1d.setText("1d");
        btn1d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1dActionPerformed(evt);
            }
        });

        btn2d.setText("2d");
        btn2d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2dActionPerformed(evt);
            }
        });

        btn3d.setText("3d");
        btn3d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3dActionPerformed(evt);
            }
        });

        btn4d.setText("4d");
        btn4d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4dActionPerformed(evt);
            }
        });

        btn5d.setText("5d");
        btn5d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5dActionPerformed(evt);
            }
        });

        btn5i.setText("5i");
        btn5i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5iActionPerformed(evt);
            }
        });

        btn4i.setText("4i");
        btn4i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4iActionPerformed(evt);
            }
        });

        btn3i.setText("3i");
        btn3i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3iActionPerformed(evt);
            }
        });

        btn2i.setText("2i");
        btn2i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2iActionPerformed(evt);
            }
        });

        btn1i.setText("1i");
        btn1i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1iActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpHuellasFondoLayout = new javax.swing.GroupLayout(jpHuellasFondo);
        jpHuellasFondo.setLayout(jpHuellasFondoLayout);
        jpHuellasFondoLayout.setHorizontalGroup(
            jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btn1i)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHuellasFondoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn4i)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn3i)
                        .addGap(3, 3, 3)
                        .addComponent(btn2i)
                        .addGap(57, 57, 57)))
                .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(btn2d)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn3d)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn4d)
                        .addGap(16, 16, 16))
                    .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btn1d)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                .addComponent(btn5i)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn5d))
        );
        jpHuellasFondoLayout.setVerticalGroup(
            jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                        .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn2i)
                                    .addComponent(btn3i)))
                            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btn4i)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn5i))
                    .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                        .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn2d)
                                    .addComponent(btn3d)))
                            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btn4d)))
                        .addGap(15, 15, 15)
                        .addComponent(btn5d)
                        .addGap(47, 47, 47)
                        .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn1d)
                            .addComponent(btn1i))))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        btnAceptar.setBackground(new java.awt.Color(255, 153, 0));
        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jpPanelHuellasLayout = new javax.swing.GroupLayout(jpPanelHuellas);
        jpPanelHuellas.setLayout(jpPanelHuellasLayout);
        jpPanelHuellasLayout.setHorizontalGroup(
            jpPanelHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelHuellasLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jpPanelHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEmpleados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpPanelHuellasLayout.createSequentialGroup()
                        .addGroup(jpPanelHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpPanelHuellasLayout.createSequentialGroup()
                                .addComponent(lbEmpleado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre))
                            .addGroup(jpPanelHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jpHuellasFondo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAceptar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jpPanelHuellasLayout.setVerticalGroup(
            jpPanelHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelHuellasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmpleado)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addComponent(jpHuellasFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        btnCerrarSesion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCerrarSesion.setText("Cerrar sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpHuellasLayout = new javax.swing.GroupLayout(jpHuellas);
        jpHuellas.setLayout(jpHuellasLayout);
        jpHuellasLayout.setHorizontalGroup(
            jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHuellasLayout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addComponent(jpPanelHuellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(125, 125, 125))
        );
        jpHuellasLayout.setVerticalGroup(
            jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHuellasLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jpPanelHuellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(jpHuellasLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpHuellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addComponent(jpLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpHuellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed
        // TODO add your handling code here:
        
        System.out.println("entra aqui primero");
        taparTodo();
        jpFondo.setVisible(true);
        jpSection.setVisible(true);
        jpLogin.setVisible(true);
        
        ConnectionBD sql = new ConnectionBD();
        Connection cn = sql.conectar();
        PreparedStatement consulta;
        System.out.println("cn = " + cn);
        admins = new ArrayList<>();
        try {
            consulta = cn.prepareStatement("SELECT * FROM admincts WHERE idct = ?");
            consulta.setInt(1, id_ct);
            ResultSet resultado = consulta.executeQuery();
          
            while(resultado.next()){
                int id_admin_ct;
                
                id_admin_ct = resultado.getInt("idadmin");
                id_empleado = resultado.getInt("idempleado");
                
                AdminCt a = new AdminCt(id_admin_ct, id_empleado, id_ct);
                consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado = ?");
                consulta.setInt(1, a.getId_empleado());
                ResultSet resultadoEmpleados = consulta.executeQuery();
                
                while(resultadoEmpleados.next()){
                    int idEmpleado;
                    String nombre;
                    String apPaterno;
                    String apMaterno;
                    String rfc;
                    int idct;

                    idEmpleado = resultadoEmpleados.getInt("idempleado");
                    nombre = resultadoEmpleados.getString("nombre").trim();
                    apPaterno = resultadoEmpleados.getString("apPaterno").trim();
                    apMaterno = resultadoEmpleados.getString("apMaterno").trim();
                    rfc = resultadoEmpleados.getString("rfc").trim();
                    idct = resultadoEmpleados.getInt("idct");


                    Empleado e = new Empleado(idEmpleado, nombre, apPaterno, apMaterno, rfc, idct);
                    admins.add(e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbAdmin.removeAllItems();
        admins.forEach((admin) -> {
            cmbAdmin.addItem(admin.toString());
        });
        adminActivo = true;
        estaLogin = 1;
        checar = validarAdmin(0, estaLogin);
        tareaLogin = () -> {
            checar = validarAdmin(checar, estaLogin);
        };

        timerLogin = Executors.newSingleThreadScheduledExecutor();
        timerLogin.scheduleAtFixedRate(tareaLogin, 1, 1, TimeUnit.SECONDS);
    }//GEN-LAST:event_btnAccederActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        taparTodo();
        jpSection.setVisible(true);
        jpChecador.setVisible(true);
        adminActivo = false;
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void cbEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEmpleadosActionPerformed
        // TODO add your handling code here:
        if(cbEmpleados.getSelectedIndex() == -1) return;
        btn1d.setEnabled(true);
        btn2d.setEnabled(true);
        btn3d.setEnabled(true);
        btn4d.setEnabled(true);
        btn5d.setEnabled(true);
        btn1i.setEnabled(true);
        btn2i.setEnabled(true);
        btn3i.setEnabled(true);
        btn4i.setEnabled(true);
        btn5i.setEnabled(true);

        btn1d.setBackground(new Color(240, 240, 240));
        btn2d.setBackground(new Color(240, 240, 240));
        btn3d.setBackground(new Color(240, 240, 240));
        btn4d.setBackground(new Color(240, 240, 240));
        btn5d.setBackground(new Color(240, 240, 240));
        btn1i.setBackground(new Color(240, 240, 240));
        btn2i.setBackground(new Color(240, 240, 240));
        btn3i.setBackground(new Color(240, 240, 240));
        btn4i.setBackground(new Color(240, 240, 240));
        btn5i.setBackground(new Color(240, 240, 240));

        int i = cbEmpleados.getSelectedIndex();
        Empleado empleado = empleados.get(i);
        id_empleado = empleado.getIdEmpleado();

        ConnectionBD sql = new ConnectionBD();
        Connection cn = sql.conectar();
        PreparedStatement consulta;
        try {
            consulta = cn.prepareStatement("SELECT * FROM huella where idempleado = ?");
            consulta.setInt(1, id_empleado);
            ResultSet resultado = consulta.executeQuery();

            while(resultado.next()){
                String d = resultado.getString("dedomano") == null ? "" : resultado.getString("dedomano");
                switch(d){
                    case "1d":
                    btn1d.setEnabled(false);
                    break;
                    case "2d":
                    btn2d.setEnabled(false);
                    break;
                    case "3d":
                    btn3d.setEnabled(false);
                    break;
                    case "4d":
                    btn4d.setEnabled(false);
                    break;
                    case "5d":
                    btn5d.setEnabled(false);
                    break;
                    case "1i":
                    btn1i.setEnabled(false);
                    break;
                    case "2i":
                    btn2i.setEnabled(false);
                    break;
                    case "3i":
                    btn3i.setEnabled(false);
                    break;
                    case "4i":
                    btn4i.setEnabled(false);
                    break;
                    case "5i":
                    btn5i.setEnabled(false);
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cbEmpleadosActionPerformed

    private void btn1dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1dActionPerformed
        // TODO add your handling code here:
        System.out.println("status = " + status);
        if(status) return;
        ponerInfo();
        btn1d.setBackground(Color.red);
        dedo = btn1d;
        guardarHuella();
    }//GEN-LAST:event_btn1dActionPerformed

    private void btn2dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2dActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn2d.setBackground(Color.red);
        dedo = btn2d;
        guardarHuella();
    }//GEN-LAST:event_btn2dActionPerformed

    private void btn3dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3dActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn3d.setBackground(Color.red);
        dedo = btn3d;
        guardarHuella();
    }//GEN-LAST:event_btn3dActionPerformed

    private void btn4dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4dActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn4d.setBackground(Color.red);
        dedo = btn4d;
        guardarHuella();
    }//GEN-LAST:event_btn4dActionPerformed

    private void btn5dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5dActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn5d.setBackground(Color.red);
        dedo = btn5d;
        guardarHuella();
    }//GEN-LAST:event_btn5dActionPerformed

    private void btn5iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5iActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn5i.setBackground(Color.red);
        dedo = btn5i;
        guardarHuella();
    }//GEN-LAST:event_btn5iActionPerformed

    private void btn4iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4iActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn4i.setBackground(Color.red);
        dedo = btn4i;
        guardarHuella();
    }//GEN-LAST:event_btn4iActionPerformed

    private void btn3iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3iActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn3i.setBackground(Color.red);
        dedo = btn3i;
        guardarHuella();
    }//GEN-LAST:event_btn3iActionPerformed

    private void btn2iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2iActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn2i.setBackground(Color.red);
        dedo = btn2i;
        guardarHuella();
    }//GEN-LAST:event_btn2iActionPerformed

    private void btn1iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1iActionPerformed
        // TODO add your handling code here:
        if(status) return;
        ponerInfo();
        btn1i.setBackground(Color.red);
        dedo = btn1i;
        guardarHuella();
    }//GEN-LAST:event_btn1iActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        this.stop();
        super.stop();
        System.exit(0);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        cargarEmpleados(txtNombre.getText());
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        taparTodo();
        jpSection.setVisible(true);
        jpChecador.setVisible(true);
        adminActivo = false;
    }//GEN-LAST:event_btnCerrarSesionActionPerformed
    
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
        System.out.println("adminActivo = " + adminActivo);
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
    
    private void taparTodo(){
        jpChecador.setVisible(false);
        jpFondo.setVisible(false);
        jpSection.setVisible(false);
        jpHuellas.setVisible(false);
        jpLogin.setVisible(false);
        //dp.clear();
    }
    
    private int validarAdmin(int checar, int el){
        Boolean activo = dp.getActivo();
        if(el == 0) return 0;
        if(activo && adminActivo){
            try{
                ConnectionBD sql = new ConnectionBD();   
                Connection cn = sql.conectar();
                PreparedStatement consulta;
                int aux_id_empleado = admins.get(cmbAdmin.getSelectedIndex()).getIdEmpleado();
                consulta = cn.prepareStatement("SELECT * FROM huella WHERE idempleado = ?");
                consulta.setInt(1, aux_id_empleado);
                ResultSet resultado = consulta.executeQuery();
                System.out.println("aux_id_empleado = " + aux_id_empleado);
                
                while(resultado.next()){
                    byte templateBuffer[] = resultado.getBytes("huella");
                    if(dp.verificarHuella(templateBuffer)){
                        checar++;
                        cuentaActiva = true;
                        timerLogin.shutdown();
                        status = false;
                        estaLogin = 0;
                        taparTodo();
                        jpSection.setVisible(true);
                        jpFondo.setVisible(true);
                        jpHuellas.setVisible(true);
                        huellas();
                    }                    
                }
                System.out.println("checar = " + checar);
                System.out.println("activo = " + activo);
                if(checar == 0 && activo){
                    dp.setActivo(false);
                    dp.clear();
                    System.out.println("checar = " + checar);
                    JOptionPane.showOptionDialog(null, "Acceso denegado", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return checar;
    }

    private void huellas(){
        btnAceptar.setBackground(new Color(240, 60, 80));
        btnAceptar.setForeground(Color.white);

        Imagen i = new Imagen("com/ieepo/images/u123.png", jpHuellasFondo.getSize().width, jpHuellasFondo.getSize().height);
        jpHuellasFondo.add(i);
        jpHuellasFondo.repaint();
        cargarEmpleados("");
    }
    
    private void cargarEmpleados(String cadena){
        ConnectionBD sql = new ConnectionBD();
        Connection cn = sql.conectar();
        PreparedStatement consulta;
        try {
            consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idct = ? and (nombre LIKE ? or appaterno LIKE ? or apmaterno LIKE ?) ORDER BY nombre");
            consulta.setInt(1, id_ct);
            consulta.setString(2, "%" + cadena + "%");
            consulta.setString(3, "%" + cadena + "%");
            consulta.setString(4, "%" + cadena + "%");
            ResultSet resultado = consulta.executeQuery();
                        
            empleados = new ArrayList<>();
            while(resultado.next()){
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
                empleados.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
        }
        
        cbEmpleados.removeAllItems();
        
        empleados.forEach((Empleado empleado) -> {
            cbEmpleados.addItem(empleado.toString());
        });
    }
   
    private void ponerInfo(){
        int i = cbEmpleados.getSelectedIndex();
        Empleado empleado = empleados.get(i);
        id_empleado = empleado.getIdEmpleado();
        status = true;
    }
    
    private void guardarHuella(){
        Timer tiempo;
        dp.clear();
        System.out.println("aqui");
        tiempo = new Timer();
        taskHuellas = new TimerTask() {
            int contador=1;
            @Override
            public void run() {
                //contador++;
                System.out.println("aqui");
                if(dp.Reclutador.getFeaturesNeeded() == 4 && contador == 1){
                    JOptionPane.showMessageDialog(null, "Ponga su dedo en el dispositivo", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("aqui 1");
                    contador++;
                }
                if(dp.Reclutador.getFeaturesNeeded() == 3 && contador == 2){
                    JOptionPane.showMessageDialog(null, "Una vez mas por favor!", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("aqui 2");
                    contador++;
                }
                if(dp.Reclutador.getFeaturesNeeded() == 2 && contador == 3){
                    JOptionPane.showMessageDialog(null, "Otra vez por favor", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("aqui 3");
                    contador++;
                }
                if(dp.Reclutador.getFeaturesNeeded() == 1 && contador == 4){
                    JOptionPane.showMessageDialog(null, "La ultima vez, ponga su dedo en el dispositivo!!", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("aqui 4");
                    contador++;
                }
                if(dp.Reclutador.getFeaturesNeeded() == 0 && contador == 5){
                    JOptionPane.showMessageDialog(null, "Listo huella guardada", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("aqui 5");
                    contador++;
                }
                if(dp.estadoHuellas() == TEMPLATE_STATUS_READY){
                    try {
                        ByteArrayInputStream datosHuella = new ByteArrayInputStream(dp.getTemplate().serialize());
                        Integer tamanioHuella=dp.getTemplate().serialize().length;
                        
                        ConnectionBD sql = new ConnectionBD();
                        Connection cn = sql.conectar();
                        String d = dedo.getText();
                        PreparedStatement ps = cn.prepareStatement("INSERT INTO huella(idempleado, huella, dedomano) VALUES (?,?, ?)");
                        ps.setInt(1, id_empleado); ///////////////////////////////////////////////////////////////////////// id_empleado
                        ps.setBinaryStream(2, datosHuella,tamanioHuella);
                        ps.setString(3, d);

                        ps.executeUpdate();
                        
                        JButton j = dedo;
                        j.setBackground(Color.green);
                        j.setEnabled(false);
                        j.setBackground(Color.green);
                        dp.stop();
                        dp.clear();
                        dp.stop();
                        status = false; 
                        taskHuellas.cancel();
                    } catch (SQLException ex) {
                        Logger.getLogger(Checador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }    
        };        
        tiempo.schedule(taskHuellas,0,1000);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1d;
    private javax.swing.JButton btn1i;
    private javax.swing.JButton btn2d;
    private javax.swing.JButton btn2i;
    private javax.swing.JButton btn3d;
    private javax.swing.JButton btn3i;
    private javax.swing.JButton btn4d;
    private javax.swing.JButton btn4i;
    private javax.swing.JButton btn5d;
    private javax.swing.JButton btn5i;
    private javax.swing.JButton btnAcceder;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbEmpleados;
    private javax.swing.JComboBox<String> cmbAdmin;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jpChecador;
    private org.jdesktop.swingx.JXPanel jpFondo;
    private org.jdesktop.swingx.JXPanel jpHuellas;
    private javax.swing.JPanel jpHuellasFondo;
    private org.jdesktop.swingx.JXPanel jpLogin;
    private org.jdesktop.swingx.JXPanel jpLogo;
    private org.jdesktop.swingx.JXPanel jpLogoPng;
    private javax.swing.JPanel jpPanelHuellas;
    private org.jdesktop.swingx.JXPanel jpSection;
    private org.jdesktop.swingx.JXLabel lbDia;
    private javax.swing.JLabel lbEmpleado;
    private org.jdesktop.swingx.JXLabel lbHora;
    private org.jdesktop.swingx.JXLabel lbMes;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
