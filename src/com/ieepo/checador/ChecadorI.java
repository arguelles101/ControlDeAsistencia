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
import com.ieepo.checador.model.Ct;
import com.ieepo.checador.model.Empleado;
import com.ieepo.checador.model.Horario;
import com.ieepo.checador.model.HorarioEmpleado;
import com.ieepo.checador.model.Permiso;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author varguelles
 */
public class ChecadorI extends javax.swing.JApplet {

    private final String TITULO = "Control de asistencia";
    private String hora, minutos, segundos, ampm, dia, mes, noDia, anio;
    private Calendar calendario;

    private final int minutos_normal = 10;
    private final int minutos_retardo = 30;

    private DigitalPersona dp;

    //private int id_ct = 6155;///////////////////////////////
    private int id_ct = 6155;
    //private final int id_ct = 6139;//6167;//0;//6142;///////////////////////////////
    //private final int id_ct = 6171;///////////////////////////////
    ArrayList<Ct> cts;
    Ct ct;

    TimerTask taskHuellas;
    Runnable tareaLogin;
    ScheduledExecutorService timerLogin;

    ArrayList<Empleado> admins;
    Boolean adminActivo = false;
    Boolean cuentaActiva = false;
    Boolean estaVisibleAnteriorMente = false; // para saber si esta visible la parte del nombre del empleado cuando se registra
    final int duracionSegundoAMostrar = 1; //Se muestra un segundo los mensajes
    int estaVisibleAnteriorMenteII = duracionSegundoAMostrar; // para saber si esta visible la parte del nombre del empleado cuando se registra
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
            java.util.logging.Logger.getLogger(ChecadorI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(() -> {
                initComponents();

                setSize(new Dimension(600, 500));

                Preferences preferences = Preferences.userNodeForPackage(ChecadorI.class);

                /*if(true){
                    try {
                        preferences.clear();
                    } catch (BackingStoreException ex) {
                        Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }*/
                
                lbTitulo.setText(TITULO);
                lbCt.setText("");
                Imagen iii = new Imagen("com/ieepo/checador/images/logo.png", (int) (jpLogo.getPreferredSize().width * 0.5), jpLogoPng.getPreferredSize().height);
                jpLogoPng.add(iii);
                jpLogoPng.repaint();
                
                if ( preferences.getInt("id_ct", -1) == -1) {
                    taparTodo();
                    jpSection.setVisible(true);
                    jpFondo.setVisible(true);
                    jpSeleccionarCts.setVisible(true);
                    cargarCts("");
                } else {
                    id_ct = preferences.getInt("id_ct", -1);
                    inicio();
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmMenu = new javax.swing.JPopupMenu();
        Acceder = new javax.swing.JMenuItem();
        jpLogo = new org.jdesktop.swingx.JXPanel();
        jpLogoPng = new org.jdesktop.swingx.JXPanel();
        lbTitulo = new org.jdesktop.swingx.JXLabel();
        lbCt = new org.jdesktop.swingx.JXLabel();
        jpSection = new org.jdesktop.swingx.JXPanel();
        jpChecador = new org.jdesktop.swingx.JXPanel();
        lbHora = new org.jdesktop.swingx.JXLabel();
        lbDiaMes = new org.jdesktop.swingx.JXLabel();
        lbEmpTot = new org.jdesktop.swingx.JXLabel();
        lbEmpPr = new org.jdesktop.swingx.JXLabel();
        lbEmpRet = new org.jdesktop.swingx.JXLabel();
        lbEmpRet1 = new org.jdesktop.swingx.JXLabel();
        lbEmpleadosPresentes = new org.jdesktop.swingx.JXLabel();
        lbEmpleadosTotales = new org.jdesktop.swingx.JXLabel();
        lbEmpleadosRetardo = new org.jdesktop.swingx.JXLabel();
        lbEmpleadosAusentes = new org.jdesktop.swingx.JXLabel();
        lbEmpRet3 = new org.jdesktop.swingx.JXLabel();
        lbEmpleadosFaltantes = new org.jdesktop.swingx.JXLabel();
        jpBienvenido = new org.jdesktop.swingx.JXPanel();
        lbBienvenido = new org.jdesktop.swingx.JXLabel();
        lbEmpleado = new org.jdesktop.swingx.JXLabel();
        lbTipo = new org.jdesktop.swingx.JXLabel();
        lbMenu = new org.jdesktop.swingx.JXLabel();
        jpFondo = new org.jdesktop.swingx.JXPanel();
        jpLogin = new org.jdesktop.swingx.JXPanel();
        cmbAdmin = new javax.swing.JComboBox<>();
        lbAdmin = new org.jdesktop.swingx.JXLabel();
        lbRegresar = new org.jdesktop.swingx.JXLabel();
        jpHuellas = new org.jdesktop.swingx.JXPanel();
        lbSelEmp = new org.jdesktop.swingx.JXLabel();
        txtEmpleado = new javax.swing.JTextField();
        cmbEmpleados = new javax.swing.JComboBox<>();
        jpHuellasFondo = new org.jdesktop.swingx.JXPanel();
        btn2d = new javax.swing.JButton();
        btn1d = new javax.swing.JButton();
        btn3d = new javax.swing.JButton();
        btn4d = new javax.swing.JButton();
        btn5d = new javax.swing.JButton();
        btn1i = new javax.swing.JButton();
        btn2i = new javax.swing.JButton();
        btn5i = new javax.swing.JButton();
        btn4i = new javax.swing.JButton();
        btn3i = new javax.swing.JButton();
        lbCerrarSesion = new org.jdesktop.swingx.JXLabel();
        btnCancelarGuardarHuella = new javax.swing.JButton();
        jpSeleccionarCts = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        cmbCts = new javax.swing.JComboBox<>();
        btnSeleccion = new javax.swing.JButton();
        txtCt = new javax.swing.JTextField();

        Acceder.setText("Acceder");
        Acceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccederActionPerformed(evt);
            }
        });
        pmMenu.add(Acceder);

        setPreferredSize(new java.awt.Dimension(600, 500));

        jpLogo.setBackground(new java.awt.Color(255, 255, 255));
        jpLogo.setPreferredSize(new java.awt.Dimension(600, 100));

        jpLogoPng.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpLogoPngLayout = new javax.swing.GroupLayout(jpLogoPng);
        jpLogoPng.setLayout(jpLogoPngLayout);
        jpLogoPngLayout.setHorizontalGroup(
            jpLogoPngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jpLogoPngLayout.setVerticalGroup(
            jpLogoPngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        lbTitulo.setForeground(new java.awt.Color(51, 102, 255));
        lbTitulo.setText("Titulo");
        lbTitulo.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N

        lbCt.setForeground(new java.awt.Color(51, 102, 255));
        lbCt.setText("Titulo");
        lbCt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        javax.swing.GroupLayout jpLogoLayout = new javax.swing.GroupLayout(jpLogo);
        jpLogo.setLayout(jpLogoLayout);
        jpLogoLayout.setHorizontalGroup(
            jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(jpLogoPng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpLogoLayout.setVerticalGroup(
            jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpLogoPng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpLogoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpSection.setBackground(new java.awt.Color(255, 255, 255));
        jpSection.setPreferredSize(new java.awt.Dimension(600, 400));

        jpChecador.setBackground(new java.awt.Color(255, 255, 255));
        jpChecador.setPreferredSize(new java.awt.Dimension(600, 400));

        lbHora.setForeground(new java.awt.Color(51, 153, 255));
        lbHora.setText("10:30 AM");
        lbHora.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N

        lbDiaMes.setForeground(new java.awt.Color(51, 153, 255));
        lbDiaMes.setText("Jueves 11 de Octubre de 2018");
        lbDiaMes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        lbEmpTot.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpTot.setText("Empleados totales:");
        lbEmpTot.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbEmpPr.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpPr.setText("Empleados presentes:");
        lbEmpPr.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbEmpRet.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpRet.setText("Empleados con retardo:");
        lbEmpRet.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbEmpRet1.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpRet1.setText("Empleados con falta:");
        lbEmpRet1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbEmpleadosPresentes.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpleadosPresentes.setText("41");
        lbEmpleadosPresentes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        lbEmpleadosTotales.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpleadosTotales.setText("41");
        lbEmpleadosTotales.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        lbEmpleadosRetardo.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpleadosRetardo.setText("41");
        lbEmpleadosRetardo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        lbEmpleadosAusentes.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpleadosAusentes.setText("41");
        lbEmpleadosAusentes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        lbEmpRet3.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpRet3.setText("Empleados faltantes");
        lbEmpRet3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbEmpleadosFaltantes.setForeground(new java.awt.Color(51, 153, 255));
        lbEmpleadosFaltantes.setText("41");
        lbEmpleadosFaltantes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jpBienvenido.setBackground(new java.awt.Color(255, 255, 255));

        lbBienvenido.setForeground(new java.awt.Color(0, 102, 255));
        lbBienvenido.setText("Bienvenido:");
        lbBienvenido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lbEmpleado.setForeground(new java.awt.Color(0, 102, 255));
        lbEmpleado.setText("Victor Manuel Argüelles Alcázar");
        lbEmpleado.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N

        lbTipo.setForeground(new java.awt.Color(0, 102, 255));
        lbTipo.setText("#######");
        lbTipo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jpBienvenidoLayout = new javax.swing.GroupLayout(jpBienvenido);
        jpBienvenido.setLayout(jpBienvenidoLayout);
        jpBienvenidoLayout.setHorizontalGroup(
            jpBienvenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBienvenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jpBienvenidoLayout.setVerticalGroup(
            jpBienvenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBienvenidoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jpBienvenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        lbMenu.setForeground(new java.awt.Color(51, 153, 255));
        lbMenu.setText("Menu▼");
        lbMenu.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpChecadorLayout = new javax.swing.GroupLayout(jpChecador);
        jpChecador.setLayout(jpChecadorLayout);
        jpChecadorLayout.setHorizontalGroup(
            jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChecadorLayout.createSequentialGroup()
                .addComponent(jpBienvenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(lbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jpChecadorLayout.createSequentialGroup()
                .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpChecadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbEmpPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmpleadosPresentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpleadosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbEmpRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpRet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmpleadosRetardo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpleadosAusentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbEmpRet3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEmpleadosFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpChecadorLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
            .addGroup(jpChecadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDiaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpChecadorLayout.setVerticalGroup(
            jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpChecadorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBienvenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(lbDiaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpChecadorLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEmpRet3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpleadosFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpChecadorLayout.createSequentialGroup()
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEmpTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpleadosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEmpPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpleadosPresentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpChecadorLayout.createSequentialGroup()
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEmpRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpleadosRetardo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpChecadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEmpRet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmpleadosAusentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        jpFondo.setPreferredSize(new java.awt.Dimension(600, 400));

        jpLogin.setBackground(new java.awt.Color(255, 255, 255));
        jpLogin.setPreferredSize(new java.awt.Dimension(600, 400));

        cmbAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbAdmin.setForeground(new java.awt.Color(51, 153, 255));
        lbAdmin.setText("Seleccione al administrador:");
        lbAdmin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbRegresar.setForeground(new java.awt.Color(51, 153, 255));
        lbRegresar.setText("Regresar");
        lbRegresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegresarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpLoginLayout = new javax.swing.GroupLayout(jpLogin);
        jpLogin.setLayout(jpLoginLayout);
        jpLoginLayout.setHorizontalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jpLoginLayout.setVerticalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jpHuellas.setBackground(new java.awt.Color(255, 255, 255));
        jpHuellas.setPreferredSize(new java.awt.Dimension(600, 400));

        lbSelEmp.setForeground(new java.awt.Color(51, 153, 255));
        lbSelEmp.setText("Selecione el empleado:");

        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyReleased(evt);
            }
        });

        cmbEmpleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEmpleados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpleadosItemStateChanged(evt);
            }
        });

        jpHuellasFondo.setBackground(new java.awt.Color(255, 255, 255));
        jpHuellasFondo.setPreferredSize(new java.awt.Dimension(400, 235));

        btn2d.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn2d.setText("2d");
        btn2d.setBorder(null);
        btn2d.setBorderPainted(false);
        btn2d.setContentAreaFilled(false);
        btn2d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2dActionPerformed(evt);
            }
        });

        btn1d.setBackground(new java.awt.Color(255, 255, 255));
        btn1d.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn1d.setText("1d");
        btn1d.setBorder(null);
        btn1d.setBorderPainted(false);
        btn1d.setContentAreaFilled(false);
        btn1d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1dActionPerformed(evt);
            }
        });

        btn3d.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn3d.setText("3d");
        btn3d.setBorder(null);
        btn3d.setBorderPainted(false);
        btn3d.setContentAreaFilled(false);
        btn3d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3dActionPerformed(evt);
            }
        });

        btn4d.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn4d.setText("4d");
        btn4d.setBorder(null);
        btn4d.setBorderPainted(false);
        btn4d.setContentAreaFilled(false);
        btn4d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4dActionPerformed(evt);
            }
        });

        btn5d.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn5d.setText("5d");
        btn5d.setBorder(null);
        btn5d.setBorderPainted(false);
        btn5d.setContentAreaFilled(false);
        btn5d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5dActionPerformed(evt);
            }
        });

        btn1i.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn1i.setText("1i");
        btn1i.setBorder(null);
        btn1i.setBorderPainted(false);
        btn1i.setContentAreaFilled(false);
        btn1i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1iActionPerformed(evt);
            }
        });

        btn2i.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn2i.setText("2i");
        btn2i.setBorder(null);
        btn2i.setBorderPainted(false);
        btn2i.setContentAreaFilled(false);
        btn2i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2iActionPerformed(evt);
            }
        });

        btn5i.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn5i.setText("5i");
        btn5i.setBorder(null);
        btn5i.setBorderPainted(false);
        btn5i.setContentAreaFilled(false);
        btn5i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5iActionPerformed(evt);
            }
        });

        btn4i.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn4i.setText("4i");
        btn4i.setBorder(null);
        btn4i.setBorderPainted(false);
        btn4i.setContentAreaFilled(false);
        btn4i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4iActionPerformed(evt);
            }
        });

        btn3i.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn3i.setText("3i");
        btn3i.setBorder(null);
        btn3i.setBorderPainted(false);
        btn3i.setContentAreaFilled(false);
        btn3i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3iActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpHuellasFondoLayout = new javax.swing.GroupLayout(jpHuellasFondo);
        jpHuellasFondo.setLayout(jpHuellasFondoLayout);
        jpHuellasFondoLayout.setHorizontalGroup(
            jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btn4i)
                .addGap(35, 35, 35)
                .addComponent(btn3i)
                .addGap(31, 31, 31)
                .addComponent(btn2i)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn2d)
                .addGap(28, 28, 28)
                .addComponent(btn3d)
                .addGap(29, 29, 29)
                .addComponent(btn4d)
                .addGap(37, 37, 37))
            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btn5i)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn5d)
                .addGap(10, 10, 10))
            .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(btn1i)
                .addGap(24, 24, 24)
                .addComponent(btn1d)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jpHuellasFondoLayout.setVerticalGroup(
            jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHuellasFondoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHuellasFondoLayout.createSequentialGroup()
                            .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn4d)
                                .addComponent(btn4i))
                            .addGap(18, 18, 18)
                            .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn5d)
                                .addComponent(btn5i))
                            .addGap(7, 7, 7))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHuellasFondoLayout.createSequentialGroup()
                            .addComponent(btn2d)
                            .addGap(46, 46, 46))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHuellasFondoLayout.createSequentialGroup()
                            .addComponent(btn3d)
                            .addGap(56, 56, 56)))
                    .addComponent(btn3i)
                    .addGroup(jpHuellasFondoLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btn2i)))
                .addGap(45, 45, 45)
                .addGroup(jpHuellasFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1d)
                    .addComponent(btn1i))
                .addGap(106, 106, 106))
        );

        lbCerrarSesion.setForeground(new java.awt.Color(51, 153, 255));
        lbCerrarSesion.setText("Cerrar sesion");
        lbCerrarSesion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCerrarSesionMouseClicked(evt);
            }
        });

        btnCancelarGuardarHuella.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        btnCancelarGuardarHuella.setText("Cancelar");
        btnCancelarGuardarHuella.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelarGuardarHuella.setBorderPainted(false);
        btnCancelarGuardarHuella.setContentAreaFilled(false);
        btnCancelarGuardarHuella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarGuardarHuellaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpHuellasLayout = new javax.swing.GroupLayout(jpHuellas);
        jpHuellas.setLayout(jpHuellasLayout);
        jpHuellasLayout.setHorizontalGroup(
            jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHuellasLayout.createSequentialGroup()
                .addGroup(jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpHuellasLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpHuellasLayout.createSequentialGroup()
                                .addComponent(lbSelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmpleado))
                            .addComponent(cmbEmpleados, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpHuellasFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarGuardarHuella, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpHuellasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jpHuellasLayout.setVerticalGroup(
            jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHuellasLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHuellasLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jpHuellasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jpHuellasFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHuellasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelarGuardarHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );

        jpSeleccionarCts.setBackground(new java.awt.Color(255, 255, 255));
        jpSeleccionarCts.setPreferredSize(new java.awt.Dimension(600, 400));

        jXLabel1.setText("Seleccionar ct:");

        cmbCts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSeleccion.setText("Seleccionar");
        btnSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionActionPerformed(evt);
            }
        });

        txtCt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jpSeleccionarCtsLayout = new javax.swing.GroupLayout(jpSeleccionarCts);
        jpSeleccionarCts.setLayout(jpSeleccionarCtsLayout);
        jpSeleccionarCtsLayout.setHorizontalGroup(
            jpSeleccionarCtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSeleccionarCtsLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jpSeleccionarCtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSeleccion)
                    .addGroup(jpSeleccionarCtsLayout.createSequentialGroup()
                        .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpSeleccionarCtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCt)
                            .addComponent(cmbCts, 0, 330, Short.MAX_VALUE))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jpSeleccionarCtsLayout.setVerticalGroup(
            jpSeleccionarCtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSeleccionarCtsLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(txtCt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpSeleccionarCtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnSeleccion)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpHuellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpSeleccionarCts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addComponent(jpLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpHuellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpSeleccionarCts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jpChecador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void lbMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMenuMouseClicked
        // TODO add your handling code here:
        pmMenu.show(this, evt.getX(), evt.getY());
        pmMenu.setLocation(evt.getLocationOnScreen().x - lbMenu.getWidth(), evt.getLocationOnScreen().y);
    }//GEN-LAST:event_lbMenuMouseClicked

    private void AccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccederActionPerformed
        // TODO add your handling code here:
        taparTodo();
        jpFondo.setVisible(true);
        jpSection.setVisible(true);
        jpLogin.setVisible(true);

        ConnectionBD sql = new ConnectionBD();
        Connection cn = sql.conectar();
        PreparedStatement consulta;

        admins = new ArrayList<>();
        try {
            consulta = cn.prepareStatement("SELECT * FROM admincts WHERE idct = ?");
            consulta.setInt(1, id_ct);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                int id_admin_ct;

                id_admin_ct = resultado.getInt("idadmin");
                id_empleado = resultado.getInt("idempleado");

                AdminCt a = new AdminCt(id_admin_ct, id_empleado, id_ct);
                consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado = ?");
                consulta.setInt(1, a.getId_empleado());
                ResultSet resultadoEmpleados = consulta.executeQuery();

                while (resultadoEmpleados.next()) {
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
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
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
    }//GEN-LAST:event_AccederActionPerformed

    private void txtEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            return;
        }
        cargarEmpleados(txtEmpleado.getText().trim());
    }//GEN-LAST:event_txtEmpleadoKeyReleased

    private void cmbEmpleadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpleadosItemStateChanged
        // TODO add your handling code here:
        if (status) {
            return;
        }
        if (cmbEmpleados.getSelectedIndex() == -1) {
            return;
        }
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

        int i = cmbEmpleados.getSelectedIndex();
        Empleado empleado = empleados.get(i);
        id_empleado = empleado.getIdEmpleado();

        ConnectionBD sql = new ConnectionBD();
        Connection cn = sql.conectar();
        PreparedStatement consulta;
        try {
            consulta = cn.prepareStatement("SELECT * FROM huella where idempleado = ?");
            consulta.setInt(1, id_empleado);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                String d = resultado.getString("dedomano") == null ? "" : resultado.getString("dedomano");
                switch (d) {
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
    }//GEN-LAST:event_cmbEmpleadosItemStateChanged

    private void btn2dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2dActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn2d.setBackground(Color.red);
        dedo = btn2d;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn2d.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn2dActionPerformed

    private void btn1dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1dActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn1d.setBackground(Color.red);
        dedo = btn1d;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn1d.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn1dActionPerformed

    private void btn3dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3dActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn3d.setBackground(Color.red);
        dedo = btn3d;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn3d.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn3dActionPerformed

    private void btn4dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4dActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn4d.setBackground(Color.red);
        dedo = btn4d;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn4d.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn4dActionPerformed

    private void btn5dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5dActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn5d.setBackground(Color.red);
        dedo = btn5d;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn5d.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn5dActionPerformed

    private void btn1iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1iActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn1i.setBackground(Color.red);
        dedo = btn1i;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn1i.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn1iActionPerformed

    private void btn2iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2iActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn2i.setBackground(Color.red);
        dedo = btn2i;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn2i.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn2iActionPerformed

    private void btn5iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5iActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn5i.setBackground(Color.red);
        dedo = btn5i;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn5i.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn5iActionPerformed

    private void btn4iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4iActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn4i.setBackground(Color.red);
        dedo = btn4i;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn4i.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn4iActionPerformed

    private void btn3iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3iActionPerformed
        // TODO add your handling code here:
        if (status) {
            return;
        }
        ponerInfo();
        btn3i.setBackground(Color.red);
        dedo = btn3i;
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btn3i.setFont(fuente);
        guardarHuella();
    }//GEN-LAST:event_btn3iActionPerformed

    private void lbCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCerrarSesionMouseClicked
        // TODO add your handling code here:
        if (status) {
            return;
        }
        taparTodo();
        jpSection.setVisible(true);
        jpChecador.setVisible(true);
        adminActivo = false;
        dp.clear();
    }//GEN-LAST:event_lbCerrarSesionMouseClicked

    private void lbRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegresarMouseClicked
        // TODO add your handling code here:
        taparTodo();
        jpSection.setVisible(true);
        jpChecador.setVisible(true);
        adminActivo = false;
        dp.clear();
    }//GEN-LAST:event_lbRegresarMouseClicked

    private void btnCancelarGuardarHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarGuardarHuellaActionPerformed
        // TODO add your handling code here:
        JButton j = dedo;
        Font fuente = new Font("Arial", Font.PLAIN, 14);
        j.setFont(fuente);
        dp.stop();
        dp.clear();
        dp.stop();
        btnCancelarGuardarHuella.setVisible(false);
        status = false;
        cmbEmpleados.setEnabled(!status);
        txtEmpleado.setEnabled(!status);
        taskHuellas.cancel();
    }//GEN-LAST:event_btnCancelarGuardarHuellaActionPerformed

    private void btnSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionActionPerformed

        id_ct = cts.get(cmbCts.getSelectedIndex()).getId_ct();
        taparTodo();
        jpSection.setVisible(true);
        jpChecador.setVisible(true);
        jpSeleccionarCts.setVisible(false);

        System.out.println("id_ct = " + id_ct);
        Preferences preferences = Preferences.userNodeForPackage(ChecadorI.class);
        
        preferences.putBoolean("id_ct", true);
        preferences.putInt("id_ct", id_ct);
        inicio();

    }//GEN-LAST:event_btnSeleccionActionPerformed

    private void txtCtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCtKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == 10){
            return;
        }
        cargarCts(txtCt.getText().trim());
    }//GEN-LAST:event_txtCtKeyReleased

    private void inicio() {
        ConnectionBD sql = new ConnectionBD();
        Connection cn = sql.conectar();
        try {
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM cts WHERE idct = ?");
            consulta.setInt(1, id_ct);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                String clave;
                String domicilio;

                clave = resultado.getString("clave");
                domicilio = resultado.getString("domicilio");

                ct = new Ct(id_ct, clave, domicilio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sql.desconectar();
        }
        
        lbCt.setText(ct.getDomicilio());

        dp = new DigitalPersona();
        dp.Iniciar();
        dp.start();

        empleados();

        hora();
        final Runnable tarea = () -> {
            hora();
        };

        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(tarea, 1, 1, TimeUnit.SECONDS);
    }

    private void empleados() {
        empleadosTotales();
        empleadosPresentes();
        empleadosRetardos();
        empleadosAusentes();
        empleadosFaltantes();
    }

    private int empleadosTotales() {
        try {
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado in (select idempleado from horarioempleado WHERE idct = ?)");
            consulta.setInt(1, id_ct);
            ResultSet resultado = consulta.executeQuery();
            int contador = 0;
            while (resultado.next()) {
                contador++;
            }
            lbEmpleadosTotales.setText(contador + "");
            return contador;
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void empleadosPresentes() {
        try {
            calendario = new GregorianCalendar();
            java.util.Date d = new java.util.Date();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);

            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM (SELECT * FROM incidencias WHERE idctlocal = ? and fechahora like ? and movimiento = 'ENTRADA'"
                    + "	GROUP BY idempleado) as t where t.tipo = '' OR tipo = 'PERMISO'");
            consulta.setInt(1, id_ct);
            consulta.setString(2, fecha.substring(0, 11) + "%");
            ResultSet resultado = consulta.executeQuery();
            int contador = 0;
            while (resultado.next()) {
                contador++;
            }
            lbEmpleadosPresentes.setText(contador + "");
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void empleadosRetardos() {
        try {
            calendario = new GregorianCalendar();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);

            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM incidencias WHERE idctlocal = ? and fechahora like ? and movimiento = 'ENTRADA' and (tipo = 'RETARDO' OR tipo = 'PERMISO RETARDO') GROUP BY idempleado ORDER BY idincidencia, tipo");
            consulta.setInt(1, id_ct);
            consulta.setString(2, fecha.substring(0, 11) + "%");

            ResultSet resultado = consulta.executeQuery();
            int contador = 0;
            while (resultado.next()) {
                contador++;
            }
            lbEmpleadosRetardo.setText(contador + "");
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void empleadosAusentes() {
        try {
            calendario = new GregorianCalendar();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);

            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM incidencias WHERE idctlocal = ? and fechahora like ? and movimiento = 'ENTRADA' and tipo = 'FALTA' GROUP BY idempleado ORDER BY idincidencia, tipo");
            consulta.setInt(1, id_ct);
            consulta.setString(2, fecha.substring(0, 11) + "%");
            ResultSet resultado = consulta.executeQuery();
            int contador = 0;
            while (resultado.next()) {
                contador++;
            }
            lbEmpleadosAusentes.setText(contador + "");
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void empleadosFaltantes() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);

            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM incidencias WHERE idctlocal = ? and fechahora like ? and movimiento = 'ENTRADA' GROUP BY idempleado ORDER BY idincidencia");
            consulta.setInt(1, id_ct);
            consulta.setString(2, fecha.substring(0, 11) + "%");
            ResultSet resultado = consulta.executeQuery();
            int contador = 0;
            while (resultado.next()) {
                contador++;
            }
            lbEmpleadosFaltantes.setText((empleadosTotales() - contador) + "");
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void hora() {
        calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY); //-12
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);

        noDia = calendario.get(Calendar.DAY_OF_MONTH) + "";
        mes = (calendario.get(Calendar.MONTH) + 1) > 9 ? (calendario.get(Calendar.MONTH) + 1) + "" : "0" + (calendario.get(Calendar.MONTH) + 1);
        anio = calendario.get(Calendar.YEAR) + "";

        dia = getDia(calendario.get(Calendar.DAY_OF_WEEK));
        mes = getMes(mes);

        dia = dia.toLowerCase();
        mes = mes.toLowerCase();
        dia = Character.toUpperCase(dia.charAt(0)) + dia.substring(1);
        mes = Character.toUpperCase(mes.charAt(0)) + mes.substring(1);

        lbDiaMes.setText(dia + " " + noDia + " de " + mes + " de " + anio);
        //lbHora.setText(hora + ":" + minutos + " " + ampm);
        lbHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);

        if (lbBienvenido.isVisible() && !estaVisibleAnteriorMente && estaVisibleAnteriorMenteII == duracionSegundoAMostrar) {
            lbBienvenido.setVisible(false);
            lbEmpleado.setVisible(false);
            lbTipo.setVisible(false);
            estaVisibleAnteriorMente = true;
            estaVisibleAnteriorMenteII = 0;
        }
        estaVisibleAnteriorMenteII++;
        validarActivo();
    }

    private String getDia(int c) {
        String dia_aux;
        switch (c) {
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

    private String getMes(String c) {
        String mes_aux;
        switch (c) {
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

    private void validarActivo() {
        Boolean activo = dp.getActivo();
        if (activo && !adminActivo) {
            try {
                ConnectionBD sql = new ConnectionBD();

                Connection cn = sql.conectar();
                PreparedStatement consulta;
                //consulta = cn.prepareStatement("SELECT * FROM huella");
                consulta = cn.prepareStatement("SELECT * FROM huella WHERE idempleado in (select idempleado from horarioempleado WHERE idct = ?)");
                consulta.setInt(1, id_ct);
                System.out.println("consulta = " + consulta);
                ResultSet resultado = consulta.executeQuery();
                Boolean si = false;
                while (resultado.next()) {
                    byte templateBuffer[] = resultado.getBytes("huella");
                    if (dp.verificarHuella(templateBuffer)) {
                        mandar(resultado.getInt("idempleado"));
                        si = true;
                        dp.clear();
                    }
                }
                dp.clear();
                if (!si) {
                    //JOptionPane.showMessageDialog(null, "Error, huella no encontrada");
                    lbBienvenido.setText("");
                    lbEmpleado.setText("Error, huella no encontrada");
                    lbBienvenido.setVisible(true);
                    lbEmpleado.setVisible(true);
                    estaVisibleAnteriorMente = false;
                    estaVisibleAnteriorMenteII = 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }
    }

    private void mandar(int id_empleado) {
        try {
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;

            consulta = cn.prepareStatement("SELECT * FROM horarioempleado WHERE idempleado = ?");
            consulta.setInt(1, id_empleado);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
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
                consulta.setInt(1, he.getId_horario());
                ResultSet resultadoH = consulta.executeQuery();
                if (resultadoH.next()) {
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

                    consulta = cn.prepareStatement("SELECT * FROM incidencias WHERE idctlocal = ? and idempleado = ? and fechahora like ?");
                    consulta.setInt(1, id_ct);
                    consulta.setInt(2, id_empleado);
                    consulta.setString(3, fecha.substring(0, 11) + "%");
                    ResultSet resultadoIncidencias = consulta.executeQuery();
                    int contador = 0;

                    if (hora_entrada_v == null && hora_salida_v == null) {
                        System.out.println("dos veces");
                        System.out.println(consulta);
                        while (resultadoIncidencias.next()) {
                            contador++;
                        }

                        if (contador == 2) {
                            System.out.println("Ya no puede registrarte mas de dos veces en el dia");
                            JOptionPane.showMessageDialog(null, "Ya no puedes registrarte mas en este dia", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (contador == 1) {
                                System.out.println("salida");
                                Time horaEComparar = new Time(Integer.parseInt(fecha.substring(11, 13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)));
                                Time horaDBComparar = h.getHora_salida();

                                String datos[] = compararSalida(horaEComparar, horaDBComparar, id_empleado);
                                String tipo = datos[1] != null ? datos[1] + " " + datos[0] : datos[0];

                                PreparedStatement ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                                ps.setInt(1, id_empleado);
                                ps.setString(2, fecha);
                                ps.setInt(3, id_ct);
                                ps.setString(4, "SALIDA");
                                ps.setString(5, tipo);
                                ps.executeUpdate();

                                resultado = ps.getGeneratedKeys();
                                int id_incidencia = 0;
                                if (resultado.next()) {
                                    id_incidencia = resultado.getInt(1);
                                }
                                if (datos[1] != null) {
                                    cambiarPermisos(id_empleado, horaEComparar);
                                    int id_permiso = Integer.parseInt(datos[2]);
                                    ps = cn.prepareStatement("UPDATE permisos SET idincidencia = ? WHERE idpermiso = ?");
                                    ps.setInt(1, id_incidencia);
                                    ps.setInt(2, id_permiso);
                                    ps.executeUpdate();
                                }

                                bienvenido(id_empleado, fecha, id_ct, "SALIDA", tipo);
                            } else {
                                System.out.println("entrada");
                                Time horaEComparar = new Time(Integer.parseInt(fecha.substring(11, 13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)));
                                Time horaDBComparar = h.getHora_entrada();

                                String datos[] = compararEntrada(horaEComparar, horaDBComparar, id_empleado);
                                String tipo = datos[1] != null ? datos[1] + " " + datos[0] : datos[0];

                                PreparedStatement ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                                ps.setInt(1, id_empleado);
                                ps.setString(2, fecha);
                                ps.setInt(3, id_ct);
                                ps.setString(4, "ENTRADA");
                                ps.setString(5, tipo);
                                ps.executeUpdate();

                                resultado = ps.getGeneratedKeys();
                                int id_incidencia = 0;
                                if (resultado.next()) {
                                    id_incidencia = resultado.getInt(1);
                                }
                                if (datos[1] != null) {
                                    cambiarPermisos(id_empleado, horaEComparar);
                                    int id_permiso = Integer.parseInt(datos[2]);
                                    ps = cn.prepareStatement("UPDATE permisos SET idincidencia = ? WHERE idpermiso = ?");
                                    ps.setInt(1, id_incidencia);
                                    ps.setInt(2, id_permiso);
                                    ps.executeUpdate();
                                }

                                bienvenido(id_empleado, fecha, id_ct, "ENTRADA", tipo);
                                empleados();
                            }
                            /////////////////////

                        }
                    } else {
                        System.out.println("cuatro veces");
                        System.out.println(consulta);
                        while (resultadoIncidencias.next()) {
                            contador++;
                        }

                        if (contador == 4) {
                            System.out.println("Ya no puede registrarte mas de dos veces en el dia");
                            JOptionPane.showMessageDialog(null, "Ya no puedes registrarte mas en este dia", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Time horaEComparar;
                            Time horaDBComparar;
                            String datos[];
                            String tipo;
                            int id_incidencia;
                            PreparedStatement ps;
                            switch (contador) {
                                case 0:
                                    System.out.println("entrada");
                                    horaEComparar = new Time(Integer.parseInt(fecha.substring(11, 13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)));
                                    horaDBComparar = h.getHora_entrada();

                                    datos = compararEntrada(horaEComparar, horaDBComparar, id_empleado);
                                    //tipo = datos[0].equals("") ? datos[1] : datos[0];
                                    tipo = datos[1] != null ? datos[1] + " " + datos[0] : datos[0];
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "ENTRADA");
                                    ps.setString(5, tipo);
                                    ps.execute();

                                    resultado = ps.getGeneratedKeys();
                                    id_incidencia = 0;
                                    if (resultado.next()) {
                                        id_incidencia = resultado.getInt(1);
                                    }
                                    if (datos[1] != null) {
                                        cambiarPermisos(id_empleado, horaEComparar);
                                        int id_permiso = Integer.parseInt(datos[2]);
                                        ps = cn.prepareStatement("UPDATE permisos SET idincidencia = ? WHERE idpermiso = ?");
                                        ps.setInt(1, id_incidencia);
                                        ps.setInt(2, id_permiso);
                                        ps.executeUpdate();
                                    }

                                    bienvenido(id_empleado, fecha, id_ct, "ENTRADA", tipo);
                                    empleados();
                                    break;
                                case 1:
                                    System.out.println("salida");
                                    horaEComparar = new Time(Integer.parseInt(fecha.substring(11, 13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)));
                                    horaDBComparar = h.getHora_salida();

                                    datos = compararSalida(horaEComparar, horaDBComparar, id_empleado);
                                    tipo = datos[1] != null ? datos[1] + " " + datos[0] : datos[0];
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "SALIDA");
                                    ps.setString(5, tipo);
                                    ps.executeUpdate();

                                    resultado = ps.getGeneratedKeys();
                                    id_incidencia = 0;
                                    if (resultado.next()) {
                                        id_incidencia = resultado.getInt(1);
                                    }
                                    if (datos[1] != null) {
                                        cambiarPermisos(id_empleado, horaEComparar);
                                        int id_permiso = Integer.parseInt(datos[2]);
                                        ps = cn.prepareStatement("UPDATE permisos SET idincidencia = ? WHERE idpermiso = ?");
                                        ps.setInt(1, id_incidencia);
                                        ps.setInt(2, id_permiso);
                                        ps.executeUpdate();
                                    }
                                    bienvenido(id_empleado, fecha, id_ct, "SALIDA", tipo);
                                    break;
                                case 2:
                                    System.out.println("entrada_v");
                                    horaEComparar = new Time(Integer.parseInt(fecha.substring(11, 13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)));
                                    horaDBComparar = h.getHora_entrada_v();

                                    //datos = compararEntrada(horaEComparar, horaDBComparar, id_empleado);
                                    //tipo = datos[0];
                                    //tipo = datos[0].equals("") ? datos[1] : datos[0];
                                    //ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)");
                                    datos = compararEntrada(horaEComparar, horaDBComparar, id_empleado);
                                    tipo = datos[1] != null ? datos[1] + " " + datos[0] : datos[0];
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "ENTRADA");
                                    ps.setString(5, tipo);
                                    ps.executeUpdate();

                                    resultado = ps.getGeneratedKeys();
                                    id_incidencia = 0;
                                    if (resultado.next()) {
                                        id_incidencia = resultado.getInt(1);
                                    }
                                    if (datos[1] != null) {
                                        cambiarPermisos(id_empleado, horaEComparar);
                                        int id_permiso = Integer.parseInt(datos[2]);
                                        ps = cn.prepareStatement("UPDATE permisos SET idincidencia = ? WHERE idpermiso = ?");
                                        ps.setInt(1, id_incidencia);
                                        ps.setInt(2, id_permiso);
                                        ps.executeUpdate();
                                    }

                                    bienvenido(id_empleado, fecha, id_ct, "ENTRADA", tipo);
                                    break;
                                case 3:
                                    System.out.println("salida_v");
                                    horaEComparar = new Time(Integer.parseInt(fecha.substring(11, 13)), Integer.parseInt(fecha.substring(14, 16)), Integer.parseInt(fecha.substring(17, 19)));
                                    horaDBComparar = h.getHora_salida_v();

                                    datos = compararSalida(horaEComparar, horaDBComparar, id_empleado);
                                    tipo = datos[1] != null ? datos[1] + " " + datos[0] : datos[0];
                                    ps = cn.prepareStatement("INSERT INTO incidencias(idempleado, fechahora, idctlocal, movimiento, tipo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                                    ps.setInt(1, id_empleado);
                                    ps.setString(2, fecha);
                                    ps.setInt(3, id_ct);
                                    ps.setString(4, "SALIDA");
                                    ps.setString(5, tipo);
                                    ps.executeUpdate();

                                    resultado = ps.getGeneratedKeys();
                                    id_incidencia = 0;
                                    if (resultado.next()) {
                                        id_incidencia = resultado.getInt(1);
                                    }
                                    if (datos[1] != null) {
                                        cambiarPermisos(id_empleado, horaEComparar);
                                        int id_permiso = Integer.parseInt(datos[2]);
                                        ps = cn.prepareStatement("UPDATE permisos SET idincidencia = ? WHERE idpermiso = ?");
                                        ps.setInt(1, id_incidencia);
                                        ps.setInt(2, id_permiso);
                                        ps.executeUpdate();
                                    }

                                    bienvenido(id_empleado, fecha, id_ct, "SALIDA", tipo);
                                    break;
                            }
                        }
                    }
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Error");
            }
        } catch (SQLException ex) {
            System.out.println("ex = " + ex);
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String[] compararEntrada(Time horaEComparar, Time horaDBComparar, int id_empleado) {
        String datos[] = new String[3];
        String d = "";
        Permiso p = null;
        try {
            Time horaAux = new Time(horaEComparar.getHours(), horaEComparar.getMinutes() - minutos_retardo, horaEComparar.getSeconds());
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            ResultSet resultado;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            consulta = cn.prepareStatement("SELECT * FROM permisos WHERE idempleado = ? and fechainicio <= ? and fechareinicio >= ? and horainicio <= ? and horareinicio >= ? and idincidencia IS NULL");
            consulta.setInt(1, id_empleado);
            consulta.setString(2, fecha);
            consulta.setString(3, fecha);
            consulta.setTime(4, horaAux);
            consulta.setTime(5, horaAux);
            System.out.println("consulta = " + consulta);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                int id_permiso;
                int id_incidencia;
                Time hora_inicio;
                Time hora_reinicio;
                Date fecha_inicio;
                Date fecha_reinicio;

                id_permiso = resultado.getInt("idpermiso");
                id_incidencia = resultado.getInt("idincidencia");
                hora_inicio = resultado.getTime("horainicio");
                hora_reinicio = resultado.getTime("horareinicio");
                fecha_inicio = resultado.getDate("fechainicio");
                fecha_reinicio = resultado.getDate("fechareinicio");
                p = new Permiso(id_permiso, id_incidencia, id_empleado, hora_inicio, hora_reinicio, fecha_inicio, fecha_reinicio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("p = " + p);
        if (p != null) {
            horaDBComparar = p.getHora_reinicio();
            datos[1] = "PERMISO";
            datos[2] = String.valueOf(p.getId_permiso());
        }
        if (horaEComparar.before(horaDBComparar)) {
            d = "";
            datos[0] = d;
            return datos;
        }

        Time t = new Time(horaEComparar.getTime() - horaDBComparar.getTime());
        if (t.getHours() != 18) {
            d = "FALTA";
            datos[0] = d;
            return datos;
        }
        if (t.getMinutes() < minutos_normal) {
            d = "";
            datos[0] = d;
            return datos;
        }
        if (t.getMinutes() < minutos_retardo) {
            d = "RETARDO";
            datos[0] = d;
            return datos;
        }
        d = "FALTA";
        datos[0] = d;
        return datos;
    }

    private String[] compararSalida(Time horaEComparar, Time horaDBComparar, int id_empleado) {
        String datos[] = new String[3];
        String d = "";
        Permiso p = null;

        try {
            Time horaAux = new Time(horaEComparar.getHours(), horaEComparar.getMinutes() + minutos_normal, horaEComparar.getSeconds());
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            ResultSet resultado;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            consulta = cn.prepareStatement("SELECT * FROM permisos WHERE idempleado = ? and fechainicio <= ? and fechareinicio >= ? and horainicio <= ? and horareinicio >= ? and idincidencia IS NULL");
            consulta.setInt(1, id_empleado);
            consulta.setString(2, fecha);
            consulta.setString(3, fecha);
            consulta.setTime(4, horaAux);
            consulta.setTime(5, horaAux);
            System.out.println("consulta = " + consulta);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                int id_permiso;
                int id_incidencia;
                Time hora_inicio;
                Time hora_reinicio;
                Date fecha_inicio;
                Date fecha_reinicio;

                id_permiso = resultado.getInt("idpermiso");
                id_incidencia = resultado.getInt("idincidencia");
                hora_inicio = resultado.getTime("horainicio");
                hora_reinicio = resultado.getTime("horareinicio");
                fecha_inicio = resultado.getDate("fechainicio");
                fecha_reinicio = resultado.getDate("fechareinicio");
                p = new Permiso(id_permiso, id_incidencia, id_empleado, hora_inicio, hora_reinicio, fecha_inicio, fecha_reinicio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("p = " + p);
        if (p != null) {
            horaDBComparar = p.getHora_inicio();
            datos[1] = "PERMISO";
            datos[2] = String.valueOf(p.getId_permiso());
        }

        if (horaEComparar.after(horaDBComparar)) {
            d = "";
            datos[0] = d;
            return datos;
        }
        Time t = new Time(horaDBComparar.getTime() - horaEComparar.getTime());
        if (t.getHours() == 18 && t.getMinutes() < minutos_normal) {
            d = "";
            datos[0] = d;
            return datos;
        }
        d = "ANTICIPADA";
        datos[0] = d;
        return datos;
    }

    private void bienvenido(int id_empleado, String fecha, int id_ct, String movimiento, String tipo) {
        try {
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado = ?");
            consulta.setInt(1, id_empleado);
            ResultSet resultado = consulta.executeQuery();

            if (resultado.next()) {
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

                if (movimiento.equals("ENTRADA")) {
                    lbBienvenido.setText("Bienvenido:");
                }
                if (movimiento.equals("SALIDA")) {
                    lbBienvenido.setText("Adios:");
                }
                lbEmpleado.setText("<html><p>" + e.toString() + "</p></html>");
                lbTipo.setText(tipo);

                lbBienvenido.setVisible(true);
                lbEmpleado.setVisible(true);
                lbTipo.setVisible(true);
                estaVisibleAnteriorMente = false;
                estaVisibleAnteriorMenteII = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int validarAdmin(int checar, int el) {
        Boolean activo = dp.getActivo();
        if (el == 0) {
            return 0;
        }
        if (activo && adminActivo) {
            try {
                ConnectionBD sql = new ConnectionBD();
                Connection cn = sql.conectar();
                PreparedStatement consulta;
                int aux_id_empleado = admins.get(cmbAdmin.getSelectedIndex()).getIdEmpleado();
                consulta = cn.prepareStatement("SELECT * FROM huella WHERE idempleado = ?");
                consulta.setInt(1, aux_id_empleado);
                ResultSet resultado = consulta.executeQuery();
                while (resultado.next()) {
                    byte templateBuffer[] = resultado.getBytes("huella");
                    if (dp.verificarHuella(templateBuffer)) {
                        checar++;
                        cuentaActiva = true;
                        timerLogin.shutdown();
                        //status = false;
                        estaLogin = 0;
                        taparTodo();
                        jpSection.setVisible(true);
                        jpFondo.setVisible(true);
                        jpHuellas.setVisible(true);
                        huellas();
                        txtEmpleado.setText("");
                    }
                }
                if (checar == 0 && activo) {
                    dp.setActivo(false);
                    dp.clear();
                    JOptionPane.showOptionDialog(null, "Acceso denegado", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                }

            } catch (SQLException ex) {
                Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return checar;
    }

    private void taparTodo() {
        jpChecador.setVisible(false);
        jpFondo.setVisible(false);
        jpSection.setVisible(false);
        jpHuellas.setVisible(false);
        jpLogin.setVisible(false);
    }

    private void huellas() {
        status = false;
        cargarEmpleados("");
        //Imagen i = new Imagen("com/ieepo/images/logo.png", 400, 232);
        Imagen i = new Imagen("com/ieepo/checador/images/u123.png", jpHuellasFondo.getSize().width, jpHuellasFondo.getSize().height);
        jpHuellasFondo.add(i);
        jpHuellasFondo.repaint();
        btnCancelarGuardarHuella.setVisible(false);
    }

    private void cargarEmpleados(String cadena) {
        try {
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            //consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idct = ? and (nombre LIKE ? or appaterno LIKE ? or apmaterno LIKE ?) ORDER BY nombre");
            consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idempleado in (SELECT idempleado FROM horarioempleado WHERE idct = ?) AND (nombre LIKE ? OR appaterno LIKE ? OR apmaterno LIKE ?) ORDER BY nombre");
            consulta.setInt(1, id_ct);
            consulta.setString(2, "%" + cadena + "%");
            consulta.setString(3, "%" + cadena + "%");
            consulta.setString(4, "%" + cadena + "%");
            ResultSet resultado = consulta.executeQuery();
            System.out.println("consulta = " + consulta);
            empleados = new ArrayList<>();
            while (resultado.next()) {
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
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }

        cmbEmpleados.removeAllItems();
        empleados.forEach((empleado) -> {
            cmbEmpleados.addItem(empleado.toString());
        });
    }

    private void ponerInfo() {
        int i = cmbEmpleados.getSelectedIndex();
        Empleado empleado = empleados.get(i);
        id_empleado = empleado.getIdEmpleado();
        status = true;
        cmbEmpleados.setEnabled(!status);
        txtEmpleado.setEnabled(!status);
        btnCancelarGuardarHuella.setVisible(true);
    }

    private void guardarHuella() {
        Timer tiempo;
        dp.clear();
        tiempo = new Timer();
        taskHuellas = new TimerTask() {
            int contador = 1;

            @Override
            public void run() {
                //contador++;
                if (dp.Reclutador.getFeaturesNeeded() == 4 && contador == 1) {
                    JOptionPane.showMessageDialog(null, "Ponga su dedo en el dispositivo", "", JOptionPane.INFORMATION_MESSAGE);
                    contador++;
                }
                if (dp.Reclutador.getFeaturesNeeded() == 3 && contador == 2) {

                    JOptionPane.showMessageDialog(null, "Una vez mas por favor!", "", JOptionPane.INFORMATION_MESSAGE);
                    contador++;
                }
                if (dp.Reclutador.getFeaturesNeeded() == 2 && contador == 3) {
                    JOptionPane.showMessageDialog(null, "Otra vez por favor", "", JOptionPane.INFORMATION_MESSAGE);
                    contador++;
                }
                if (dp.Reclutador.getFeaturesNeeded() == 1 && contador == 4) {
                    JOptionPane.showMessageDialog(null, "La ultima vez, ponga su dedo en el dispositivo!!", "", JOptionPane.INFORMATION_MESSAGE);
                    contador++;
                }
                if (dp.Reclutador.getFeaturesNeeded() == 0 && contador == 5) {
                    JOptionPane.showMessageDialog(null, "Listo huella guardada", "", JOptionPane.INFORMATION_MESSAGE);
                    contador++;
                }
                if (dp.estadoHuellas() == TEMPLATE_STATUS_READY) {
                    try {
                        ByteArrayInputStream datosHuella = new ByteArrayInputStream(dp.getTemplate().serialize());
                        Integer tamanioHuella = dp.getTemplate().serialize().length;

                        ConnectionBD sql = new ConnectionBD();
                        Connection cn = sql.conectar();
                        String d = dedo.getText();
                        PreparedStatement ps = cn.prepareStatement("INSERT INTO huella(idempleado, huella, dedomano) VALUES (?,?, ?)");
                        ps.setInt(1, id_empleado); ///////////////////////////////////////////////////////////////////////// id_empleado
                        ps.setBinaryStream(2, datosHuella, tamanioHuella);
                        ps.setString(3, d);

                        ps.executeUpdate();

                        JButton j = dedo;
                        j.setBackground(Color.green);
                        j.setEnabled(false);
                        j.setBackground(Color.green);
                        Font fuente = new Font("Arial", Font.PLAIN, 14);
                        j.setFont(fuente);
                        dp.stop();
                        dp.clear();
                        dp.stop();
                        btnCancelarGuardarHuella.setVisible(false);
                        status = false;
                        cmbEmpleados.setEnabled(!status);
                        txtEmpleado.setEnabled(!status);
                        taskHuellas.cancel();
                    } catch (SQLException ex) {
                        Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        tiempo.schedule(taskHuellas, 0, 1000);
    }

    private void cambiarPermisos(int id_em, Time horaAux) {
        System.out.println("llllllllllllllllllllllega");
        Permiso p = null;
        try {
            ConnectionBD sql = new ConnectionBD();
            Connection cn = sql.conectar();
            PreparedStatement consulta;
            ResultSet resultado;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            consulta = cn.prepareStatement("SELECT * FROM permisos WHERE idempleado = ? and fechainicio <= ? and fechareinicio >= ? and horainicio <= ? and horareinicio >= ? and idincidencia IS NULL");
            consulta.setInt(1, id_em);
            consulta.setString(2, fecha);
            consulta.setString(3, fecha);
            consulta.setTime(4, horaAux);
            consulta.setTime(5, horaAux);
            System.out.println("consulta = " + consulta);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                int id_permiso;
                int id_incidencia;
                Time hora_inicio;
                Time hora_reinicio;
                Date fecha_inicio;
                Date fecha_reinicio;
                String autorizo;
                String numdoc;
                String nota;
                String tipo_permiso;

                id_permiso = resultado.getInt("idpermiso");
                id_incidencia = resultado.getInt("idincidencia");
                hora_inicio = resultado.getTime("horainicio");
                hora_reinicio = resultado.getTime("horareinicio");
                fecha_inicio = resultado.getDate("fechainicio");
                fecha_reinicio = resultado.getDate("fechareinicio");
                autorizo = resultado.getString("autorizo");
                numdoc = resultado.getString("numdoc");
                nota = resultado.getString("nota");
                tipo_permiso = resultado.getString("tipodpermiso");
                p = new Permiso(id_permiso, id_incidencia, id_em, hora_inicio, hora_reinicio, fecha_inicio, fecha_reinicio, autorizo, numdoc, nota, tipo_permiso);
            }
            System.out.println("p = " + p);
            if (p != null) {
                date = p.getFecha_inicio();
                Date date1 = p.getFecha_reinicio();
                String date1i = date1.toString();
                Date aux = date;
                fecha = dateFormat.format(aux);

                while (!fecha.equals(date1i)) {
                    aux = new Date(aux.getYear(), aux.getMonth(), aux.getDate() + 1);
                    fecha = dateFormat.format(aux);
                    System.out.println("fecha = " + fecha);
                    PreparedStatement ps = cn.prepareStatement("INSERT INTO permisos(idempleado, horainicio, horareinicio, fechainicio, fechareinicio, autorizo, numdoc, nota, tipodpermiso) VALUES (?,?,?,?,?,?,?,?,?)");
                    ps.setInt(1, id_em);
                    ps.setTime(2, p.getHora_inicio());
                    ps.setTime(3, p.getHora_reinicio());
                    ps.setString(4, fecha);
                    ps.setString(5, fecha);
                    ps.setString(6, p.getAutorizo());
                    ps.setString(7, p.getNumdoc());
                    ps.setString(8, p.getNota());
                    ps.setString(9, p.getTipo_permiso());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCts(String cadena){
        ConnectionBD sql = new ConnectionBD();
        Connection cn = sql.conectar();
        cts = new ArrayList<>();
        try {
            PreparedStatement consulta;
            consulta = cn.prepareStatement("SELECT * FROM cts WHERE domicilio LIKE ? ORDER BY domicilio");
            consulta.setString(1, "%" + cadena + "%");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int aux;
                String clave;
                String domicilio;

                aux = resultado.getInt("idct");
                clave = resultado.getString("clave");
                domicilio = resultado.getString("domicilio");

                ct = new Ct(aux, clave, domicilio);
                cts.add(ct);
            }
            cmbCts.removeAllItems();
            cts.forEach((ct1) -> {
                cmbCts.addItem(ct1.toString());
            });
        } catch (SQLException ex) {
            Logger.getLogger(ChecadorI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sql.desconectar();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Acceder;
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
    private javax.swing.JButton btnCancelarGuardarHuella;
    private javax.swing.JButton btnSeleccion;
    private javax.swing.JComboBox<String> cmbAdmin;
    private javax.swing.JComboBox<String> cmbCts;
    private javax.swing.JComboBox<String> cmbEmpleados;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jpBienvenido;
    private org.jdesktop.swingx.JXPanel jpChecador;
    private org.jdesktop.swingx.JXPanel jpFondo;
    private org.jdesktop.swingx.JXPanel jpHuellas;
    private org.jdesktop.swingx.JXPanel jpHuellasFondo;
    private org.jdesktop.swingx.JXPanel jpLogin;
    private org.jdesktop.swingx.JXPanel jpLogo;
    private org.jdesktop.swingx.JXPanel jpLogoPng;
    private org.jdesktop.swingx.JXPanel jpSection;
    private org.jdesktop.swingx.JXPanel jpSeleccionarCts;
    private org.jdesktop.swingx.JXLabel lbAdmin;
    private org.jdesktop.swingx.JXLabel lbBienvenido;
    private org.jdesktop.swingx.JXLabel lbCerrarSesion;
    private org.jdesktop.swingx.JXLabel lbCt;
    private org.jdesktop.swingx.JXLabel lbDiaMes;
    private org.jdesktop.swingx.JXLabel lbEmpPr;
    private org.jdesktop.swingx.JXLabel lbEmpRet;
    private org.jdesktop.swingx.JXLabel lbEmpRet1;
    private org.jdesktop.swingx.JXLabel lbEmpRet3;
    private org.jdesktop.swingx.JXLabel lbEmpTot;
    private org.jdesktop.swingx.JXLabel lbEmpleado;
    private org.jdesktop.swingx.JXLabel lbEmpleadosAusentes;
    private org.jdesktop.swingx.JXLabel lbEmpleadosFaltantes;
    private org.jdesktop.swingx.JXLabel lbEmpleadosPresentes;
    private org.jdesktop.swingx.JXLabel lbEmpleadosRetardo;
    private org.jdesktop.swingx.JXLabel lbEmpleadosTotales;
    private org.jdesktop.swingx.JXLabel lbHora;
    private org.jdesktop.swingx.JXLabel lbMenu;
    private org.jdesktop.swingx.JXLabel lbRegresar;
    private org.jdesktop.swingx.JXLabel lbSelEmp;
    private org.jdesktop.swingx.JXLabel lbTipo;
    private org.jdesktop.swingx.JXLabel lbTitulo;
    private javax.swing.JPopupMenu pmMenu;
    private javax.swing.JTextField txtCt;
    private javax.swing.JTextField txtEmpleado;
    // End of variables declaration//GEN-END:variables

}
