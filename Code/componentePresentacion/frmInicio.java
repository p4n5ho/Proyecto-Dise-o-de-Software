/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.Usuario;
import Excepciones.ResiduosException;
import Negocio.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class frmInicio extends javax.swing.JFrame {

    private IFacadeNegocio facade;

    private static frmInicio instance;

    private Usuario usuario;

    private frmInicio() {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(1200, 720);
        this.facade = FabricaNegocio.getFachada();
    }

    public static frmInicio getInstance(Usuario usuario) {
        if (instance == null) {
            instance = new frmInicio();
        }
        instance.establecerAcciones(usuario);
        return instance;
    }

    private void establecerAcciones(Usuario usuario) {
        this.usuario = usuario;
        switch (this.usuario.getTipo()) {
            case 1:
                this.botonRegistrarResiduo.setEnabled(false);
                this.botonSolicitarTraslado.setEnabled(false);
                this.botonAsignarTraslado.setEnabled(true);
                this.botonRegistrarTraslado.setEnabled(false);
                break;
            case 2:
                this.botonRegistrarResiduo.setEnabled(true);
                this.botonSolicitarTraslado.setEnabled(true);
                this.botonAsignarTraslado.setEnabled(false);
                this.botonRegistrarTraslado.setEnabled(false);
                break;
            case 3:
                this.botonRegistrarResiduo.setEnabled(false);
                this.botonSolicitarTraslado.setEnabled(false);
                this.botonAsignarTraslado.setEnabled(false);
                this.botonRegistrarTraslado.setEnabled(true);
                break;
            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonSolicitarTraslado = new javax.swing.JButton();
        botonRegistrarResiduo = new javax.swing.JButton();
        tablasBoton = new javax.swing.JButton();
        botonAsignarTraslado = new javax.swing.JButton();
        botonRegistrarTraslado = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cerrarSesion = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Residuos Peligrosos");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        botonSolicitarTraslado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonSolicitarTraslado.setText("Solicitar Traslado");
        botonSolicitarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSolicitarTrasladoActionPerformed(evt);
            }
        });

        botonRegistrarResiduo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonRegistrarResiduo.setText("Registrar Residuo");
        botonRegistrarResiduo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarResiduoActionPerformed(evt);
            }
        });

        tablasBoton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tablasBoton.setText("Ver Catálogo de Residuos y Elementos Químicos");
        tablasBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablasBotonActionPerformed(evt);
            }
        });

        botonAsignarTraslado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonAsignarTraslado.setText("Ver Solicitudes de Traslado");
        botonAsignarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAsignarTrasladoActionPerformed(evt);
            }
        });

        botonRegistrarTraslado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonRegistrarTraslado.setText("Registrar Traslado");
        botonRegistrarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarTrasladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonRegistrarResiduo, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSolicitarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAsignarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonRegistrarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tablasBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAsignarTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonSolicitarTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addComponent(botonRegistrarResiduo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonRegistrarTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablasBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(122, 235, 122));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sistema de Residuos Peligrosos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Inicio");

        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(cerrarSesion);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Acerca De");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarResiduoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarResiduoActionPerformed
        frmRegistroResiduo registroResiduo = null;
        try {
            registroResiduo = frmRegistroResiduo.getInstance(this, true, this.usuario);
            registroResiduo.setVisible(true);
        } catch (ResiduosException e) {
            JOptionPane.showMessageDialog(this, "No se pueden obtener los elementos quimicos", "Problemas de BD", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonRegistrarResiduoActionPerformed

    private void botonSolicitarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSolicitarTrasladoActionPerformed
        frmSolicitudTraslado registroTraslado = null;
        try {
            registroTraslado = frmSolicitudTraslado.getInstance(this, true, this.usuario);
            registroTraslado.setVisible(true);
        } catch (ResiduosException e) {
            JOptionPane.showMessageDialog(this, "No se pueden obtener los residuos de la empresa", "Problemas de BD", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonSolicitarTrasladoActionPerformed

    private void tablasBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablasBotonActionPerformed
        frmTablas tablas = null;
        try {
            tablas = frmTablas.getInstance(this, true);
            tablas.setVisible(true);
        } catch (ResiduosException residuosException) {
            JOptionPane.showMessageDialog(this, residuosException.getMessage(), "Error de tablas", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tablasBotonActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
        frmInicioSesion frmsesion = frmInicioSesion.getInstance();
        frmsesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarSesionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(NORMAL);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void botonAsignarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAsignarTrasladoActionPerformed
        frmAsignacion asignacion = null;
        try {
            asignacion = frmAsignacion.getInstance(this, true);
            asignacion.setVisible(true);
        } catch (ResiduosException e) {
            JOptionPane.showMessageDialog(this, "No se pueden obtener las solicitudes no atendidas", "Problemas de BD", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_botonAsignarTrasladoActionPerformed

    private void botonRegistrarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarTrasladoActionPerformed
        frmRegistroTraslados rTraslados = null;
        try{
            rTraslados = frmRegistroTraslados.getInstance(this, rootPaneCheckingEnabled, usuario);
            rTraslados.setVisible(true);
        }catch(ResiduosException e){
            JOptionPane.showMessageDialog(this, "No se pueden obtener los traslados", "Problemas de BD", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_botonRegistrarTrasladoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAsignarTraslado;
    private javax.swing.JButton botonRegistrarResiduo;
    private javax.swing.JButton botonRegistrarTraslado;
    private javax.swing.JButton botonSolicitarTraslado;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton tablasBoton;
    // End of variables declaration//GEN-END:variables
}
