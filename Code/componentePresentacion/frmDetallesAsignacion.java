/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.DetallesSolicitud;
import Dominio.Solicitud;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Alejandro Galindo
 */
public class frmDetallesAsignacion extends javax.swing.JDialog {
    
    private Solicitud solicitud;
    private List<DetallesSolicitud> detalles;
    private List<DetallesSolicitud> detallesNoTerminados = new ArrayList<>();
    
    private IFacadeNegocio facade;
    
    private static frmDetallesAsignacion instance;
    
    /**
     * Creates new form frmDetallesAsignacion
     */
    private frmDetallesAsignacion(java.awt.Frame parent, boolean modal, Solicitud solicitud) {
        super(parent, modal);
        initComponents();
        this.setTitle("Asignación de traslados");
        this.setLocationRelativeTo(parent);
        this.facade = FabricaNegocio.getFachada();
    }
    
    public static frmDetallesAsignacion getInstance(java.awt.Frame parent, boolean modal, Solicitud solicitud){
        if(instance == null){
            instance = new frmDetallesAsignacion(parent, modal, solicitud);
        }
        instance.solicitud = solicitud;
        instance.actualizar();
        return instance;
    }
    
    private void actualizar(){
        this.labelProductor.setText("Solicitud de: " + facade.obtenerEmpresaProductora(solicitud.getRFCEmpresa()).getNombreProductora());
        this.labelFecha.setText("Fecha solicitada: " + solicitud.getFecha());
        detalles = solicitud.getDetalles();
        for (DetallesSolicitud detalle : detalles) {
            if(detalle.getCantidad() != 0){
                detallesNoTerminados.add(detalle);
            }
        }
        
        
        renderizarTablas();
    }
    
    private void renderizarTablas() {
        vaciarTablas();
        DefaultTableModel modeloTablaSolicitudes = (DefaultTableModel) tablaDetalles.getModel();
        Object rowData[] = new Object[3];

        for (int i = 0; i < detallesNoTerminados.size(); i++) {
            rowData[0] = facade.obtenerResiduo(detallesNoTerminados.get(i).getIDResiduo());
            rowData[1] = detallesNoTerminados.get(i).getCantidad();
            rowData[2] = detallesNoTerminados.get(i).getMedida();
            modeloTablaSolicitudes.addRow(rowData);
        }
    }

    private void vaciarTablas() {
        while (tablaDetalles.getRowCount() > 0) {
            ((DefaultTableModel) tablaDetalles.getModel()).removeRow(0);
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
        jPanel2 = new javax.swing.JPanel();
        labelProductor = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        salir = new javax.swing.JButton();
        asignarTransporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(122, 235, 122));

        labelProductor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelProductor.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelProductor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelProductor, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelFecha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tablaDetalles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Residuo", "Cantidad", "Medida"
            }
        ));
        tablaDetalles.setRowHeight(32);
        tablaDetalles.setRowMargin(2);
        tablaDetalles.setSelectionBackground(new java.awt.Color(122, 235, 122));
        scroll.setViewportView(tablaDetalles);

        salir.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        salir.setText("Cancelar");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        asignarTransporte.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        asignarTransporte.setText("Asignar Transporte");
        asignarTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignarTransporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(asignarTransporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(asignarTransporte))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

    private void asignarTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignarTransporteActionPerformed
        int selectedRow = tablaDetalles.getSelectedRow();
        
        if(selectedRow >= 0){
            frmAsignarEmpresas empresas = frmAsignarEmpresas.getInstance(null, true, detalles.get(selectedRow));
            empresas.setVisible(true);
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun residuo", "Residuo sin seleccionar", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_asignarTransporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton asignarTransporte;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelProductor;
    private javax.swing.JButton salir;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaDetalles;
    // End of variables declaration//GEN-END:variables
}
