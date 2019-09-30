/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.Asignacion;
import Dominio.Traslado;
import Dominio.Usuario;
import Dominio.Vehiculo;
import Excepciones.ResiduosException;
import Negocio.FabricaNegocio;
import Negocio.IFacadeNegocio;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Galindo
 */
public class frmVehiculosTraslado extends javax.swing.JDialog {

    private IFacadeNegocio facade;
    private Asignacion asignacion;
    private List<Vehiculo> vehiculos;
    private Usuario usuario;

    private static frmVehiculosTraslado instance;

    /**
     * Creates new form frmVehiculosTraslado
     */
    private frmVehiculosTraslado(java.awt.Frame parent, boolean modal, Asignacion asignacion, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.setSize(850, 650);
        this.setLocationRelativeTo(null);
        facade = FabricaNegocio.getFachada();
        this.asignacion = asignacion;
        this.usuario = usuario;
    }

    public static frmVehiculosTraslado getInstance(java.awt.Frame parent, boolean modal, Asignacion asignacion, Usuario usuario) {
        if (instance == null) {
            instance = new frmVehiculosTraslado(parent, modal, asignacion, usuario);
        }
        instance.asignacion = asignacion;
        instance.usuario = usuario;
        instance.actualizar();
        return instance;
    }

    private void actualizar() {
        vehiculos = facade.obtenerVehiculosEmpresa(usuario.getCodigoAcceso());
        renderizarTablas();
        calendar.setSelectedDate(LocalDate.now());
        spinCosto.setValue(500);
        spinKilometros.setValue(1000);
        textArea.setText("");
    }

    private void renderizarTablas() {
        vaciarTablas();
        DefaultTableModel modeloTablaSolicitudes = (DefaultTableModel) tablaVehiculos.getModel();
        Object rowData[] = new Object[2];

        for (int i = 0; i < vehiculos.size(); i++) {
            rowData[0] = vehiculos.get(i).getMatricula();
            rowData[1] = vehiculos.get(i).getTipoVehiculo();
            modeloTablaSolicitudes.addRow(rowData);
        }
    }

    private void vaciarTablas() {
        while (tablaVehiculos.getRowCount() > 0) {
            ((DefaultTableModel) tablaVehiculos.getModel()).removeRow(0);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        spinCosto = new javax.swing.JSpinner();
        calendar = new com.github.lgooddatepicker.components.CalendarPanel();
        jLabel4 = new javax.swing.JLabel();
        spinKilometros = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        botonRegistrarTraslado = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVehiculos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(122, 235, 122));

        jLabel1.setText("Registro de Traslado concluido");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setText("Kilometros recorridos:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setText("Costo Final:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        spinCosto.setModel(new javax.swing.SpinnerNumberModel(500.0f, 0.0f, null, 100.0f));
        spinCosto.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        jLabel4.setText("Fecha de Llegada");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        spinKilometros.setModel(new javax.swing.SpinnerNumberModel(1000.0f, 1000.0f, null, 500.0f));
        spinKilometros.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        jLabel5.setText("Descripción de tratamiento:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        textArea.setColumns(10);
        textArea.setRows(20);
        textArea.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(textArea);

        botonRegistrarTraslado.setText("Registrar Traslado concluido");
        botonRegistrarTraslado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botonRegistrarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarTrasladoActionPerformed(evt);
            }
        });

        salir.setText("Cancelar");
        salir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        tablaVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Tipo de Vehiculo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVehiculos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(tablaVehiculos);

        jLabel6.setText("Vehiculos");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinKilometros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinCosto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonRegistrarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spinKilometros, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonRegistrarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addContainerGap(44, Short.MAX_VALUE))
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

    private void botonRegistrarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarTrasladoActionPerformed
        int[] selectedRows = tablaVehiculos.getSelectedRows();

        if (selectedRows.length > 0) {
            int IDAsignacion = asignacion.getIDAsignacion();
            String fechaTermino = calendar.getSelectedDate().toString();
            String tratamiento = textArea.getText();
            float costo = 0;
            float km = 0;
            try {
                spinCosto.commitEdit();
                costo = (Float) spinCosto.getValue();
                spinKilometros.commitEdit();
                km = (Float) spinKilometros.getValue();

                List<Vehiculo> vehiculosAsignados = new ArrayList<>();
                for (int selectedRow : selectedRows) {
                    vehiculosAsignados.add(vehiculos.get(selectedRow));
                }
                
                facade.registrarTraslado(IDAsignacion, fechaTermino, tratamiento, vehiculosAsignados, costo, usuario.getCodigoAcceso(), km);
                JOptionPane.showMessageDialog(this, "Traslado registrado con exito", "Registro exitoso", JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Asigne un numero valido", "Eso no es un número", JOptionPane.PLAIN_MESSAGE);
            } catch(ResiduosException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al hacer el registro", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Escoga vehiculos para registrar junto al traslado", "Vehiculos no seleccionados", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_botonRegistrarTrasladoActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrarTraslado;
    private com.github.lgooddatepicker.components.CalendarPanel calendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton salir;
    private javax.swing.JSpinner spinCosto;
    private javax.swing.JSpinner spinKilometros;
    private javax.swing.JTable tablaVehiculos;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}