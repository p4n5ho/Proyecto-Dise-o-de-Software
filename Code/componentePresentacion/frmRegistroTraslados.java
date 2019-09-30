/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.Asignacion;
import Dominio.Usuario;
import Negocio.FabricaNegocio;
import Negocio.IFacadeNegocio;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
public class frmRegistroTraslados extends javax.swing.JDialog {

    
    private IFacadeNegocio facade;
    private List<Asignacion> asignaciones;
    private Usuario usuario;
    
    private static frmRegistroTraslados instance;
    
    /**
     * Creates new form frmTrasladosAsignados
     */
    private frmRegistroTraslados(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.facade = FabricaNegocio.getFachada();
        this.usuario = usuario;
    }
    
    public static frmRegistroTraslados getInstance(java.awt.Frame parent, boolean modal, Usuario usuario){
        if(instance == null){
            instance = new frmRegistroTraslados(parent, modal, usuario);
        }
        instance.usuario = usuario;
        instance.actualizar();
        return instance;
    }
    
    private void actualizar(){
        asignaciones = facade.obtenerAsignacionesEmpresa(usuario.getCodigoAcceso());
        renderizarTablas();
        actualizarColumnas();
    }
    
    private void renderizarTablas() {
        vaciarTablas();
        DefaultTableModel modeloTablaSolicitudes = (DefaultTableModel) tablaAsignacion.getModel();
        Object rowData[] = new Object[5];

        for (int i = 0; i < asignaciones.size(); i++) {
            rowData[0] = facade.obtenerSolicitud(String.valueOf(asignaciones.get(i).getIDSolicitud())).getFecha();
            rowData[1] = facade.obtenerResiduo(asignaciones.get(i).getIDResiduo()).getNombreResiduo();
            rowData[2] = asignaciones.get(i).getCantidadReparto();
            rowData[3] = asignaciones.get(i).getMedida();
            rowData[4] = facade.obtenerEmpresaProductora(facade.obtenerSolicitud(String.valueOf(asignaciones.get(i).getIDSolicitud())).getRFCEmpresa());
            modeloTablaSolicitudes.addRow(rowData);
        }
    }

    private void vaciarTablas() {
        while (tablaAsignacion.getRowCount() > 0) {
            ((DefaultTableModel) tablaAsignacion.getModel()).removeRow(0);
        }
    }
    
    private void actualizarColumnas() {
        scroll.getViewport().setBackground(Color.WHITE);
        tablaAsignacion.getTableHeader().setReorderingAllowed(false);
        tablaAsignacion.getTableHeader().setResizingAllowed(true);

        Font f = new Font("Seoge UI", Font.BOLD, 15);
        tablaAsignacion.getTableHeader().setFont(f);
        
        tablaAsignacion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < tablaAsignacion.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tablaAsignacion.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            for (int r = 0; r < tablaAsignacion.getRowCount(); r++) {
                renderer = tablaAsignacion.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(tablaAsignacion, tablaAsignacion.getValueAt(r, i),
                        false, false, r, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            col.setPreferredWidth(width + 20);
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
        scroll = new javax.swing.JScrollPane();
        tablaAsignacion = new javax.swing.JTable();
        salir = new javax.swing.JButton();
        registrarTraslado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(500, 400));
        setMinimumSize(new java.awt.Dimension(500, 400));
        setPreferredSize(new java.awt.Dimension(500, 400));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(122, 235, 122));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Traslados Asignados");

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

        tablaAsignacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaAsignacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha Solicitada", "Residuo", "Cantidad", "Medida", "Productor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAsignacion.setRowHeight(32);
        tablaAsignacion.setRowMargin(2);
        tablaAsignacion.setSelectionBackground(new java.awt.Color(122, 235, 122));
        scroll.setViewportView(tablaAsignacion);

        salir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salir.setText("Cancelar");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        registrarTraslado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registrarTraslado.setText("Registrar Traslado");
        registrarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarTrasladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(registrarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
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

    private void registrarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarTrasladoActionPerformed
        int selectedRow = tablaAsignacion.getSelectedRow();
        
        if(selectedRow >= 0){
            frmVehiculosTraslado vehiculos = frmVehiculosTraslado.getInstance(null, true, asignaciones.get(selectedRow), this.usuario);
            vehiculos.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Selecciona un traslado", "No ha seleccionado un traslado", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_registrarTrasladoActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton registrarTraslado;
    private javax.swing.JButton salir;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaAsignacion;
    // End of variables declaration//GEN-END:variables
}
