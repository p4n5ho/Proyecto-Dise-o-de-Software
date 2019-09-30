/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.ElementoQuimico;
import Dominio.Residuo;
import Dominio.Traslado;
import Excepciones.ResiduosException;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Alejandro Galindo
 */
public class frmTablas extends javax.swing.JDialog {

    private IFacadeNegocio facade = FabricaNegocio.getFachada();
    private List<Residuo> listaResiduos;
    private List<ElementoQuimico> listaElementos;
    private List<Traslado> listaTraslado;

    private static frmTablas instance;

    private frmTablas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(800, 600);
        this.setLocationRelativeTo(parent);
        actualizar();
    }

    public static frmTablas getInstance(java.awt.Frame parent, boolean modal) {
        if (instance == null) {
            instance = new frmTablas(parent, modal);
        }
        instance.actualizar();
        return instance;
    }

    private void actualizar() {
        try {
            listaResiduos = facade.obtenerListaResiduos();
            listaElementos = facade.obtenerListaElementosQuimicos();
            listaTraslado = facade.obtenerListaTraslados();
            actualizarColumnas();
        } catch (ResiduosException residuosException) {
            throw residuosException;
        }

        renderizarTablas();
    }

    private void renderizarTablas() {
        vaciarTablas();

        DefaultTableModel modeloTablaResiduos = (DefaultTableModel) tablaResiduos.getModel();
        Object rowDataResiduos[] = new Object[4];

        for (int i = 0; i < listaResiduos.size(); i++) {
            rowDataResiduos[0] = listaResiduos.get(i).getIDResiduo();
            rowDataResiduos[1] = listaResiduos.get(i).getNombreResiduo();
            rowDataResiduos[2] = listaResiduos.get(i).getElementosQuimicos().toString();
            rowDataResiduos[3] = listaResiduos.get(i).getEmpresasProductoras().toString();
            modeloTablaResiduos.addRow(rowDataResiduos);
        }

        DefaultTableModel modeloTablaElementos = (DefaultTableModel) tablaElementos.getModel();
        Object rowDataElementos[] = new Object[4];

        for (int i = 0; i < listaElementos.size(); i++) {
            rowDataElementos[0] = listaElementos.get(i).getIDElemento();
            rowDataElementos[1] = listaElementos.get(i).getNombreElemento();
            rowDataElementos[2] = listaElementos.get(i).getSimbolo();
            rowDataElementos[3] = listaElementos.get(i).getnAtomico();
            modeloTablaElementos.addRow(rowDataElementos);
        }
    }

    private void vaciarTablas() {
        while (tablaResiduos.getRowCount() > 0) {
            ((DefaultTableModel) tablaResiduos.getModel()).removeRow(0);
        }

        while (tablaElementos.getRowCount() > 0) {
            ((DefaultTableModel) tablaElementos.getModel()).removeRow(0);
        }
    }
    
    private void actualizarColumnas() {
        scrollResiduos.getViewport().setBackground(Color.WHITE);
        tablaResiduos.getTableHeader().setReorderingAllowed(false);
        tablaResiduos.getTableHeader().setResizingAllowed(true);
        
        scrollElementos.getViewport().setBackground(Color.WHITE);
        tablaElementos.getTableHeader().setReorderingAllowed(false);
        tablaElementos.getTableHeader().setResizingAllowed(true);

        Font f = new Font("Seoge UI", Font.BOLD, 15);
        tablaResiduos.getTableHeader().setFont(f);
        tablaElementos.getTableHeader().setFont(f);
        
        tablaResiduos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < tablaResiduos.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tablaResiduos.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            for (int r = 0; r < tablaResiduos.getRowCount(); r++) {
                renderer = tablaResiduos.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(tablaResiduos, tablaResiduos.getValueAt(r, i),
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
        botonRegresar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        scrollResiduos = new javax.swing.JScrollPane();
        tablaResiduos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        scrollElementos = new javax.swing.JScrollPane();
        tablaElementos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        botonRegresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonRegresar.setText("Regresar al menu principal");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tablaResiduos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tablaResiduos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Composicion de Elementos Quimicos", "Empresas que lo producen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResiduos.setRowHeight(32);
        tablaResiduos.setRowMargin(2);
        tablaResiduos.setSelectionBackground(new java.awt.Color(122, 235, 122));
        scrollResiduos.setViewportView(tablaResiduos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollResiduos, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollResiduos, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Residuos", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tablaElementos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tablaElementos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo de Elemento Quimico", "Nombre", "Simbolo", "#Atómico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaElementos.setRowHeight(32);
        tablaElementos.setRowMargin(2);
        tablaElementos.setSelectionBackground(new java.awt.Color(122, 235, 122));
        scrollElementos.setViewportView(tablaElementos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollElementos, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollElementos, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Elementos Quimicos", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonRegresar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane scrollElementos;
    private javax.swing.JScrollPane scrollResiduos;
    private javax.swing.JTable tablaElementos;
    private javax.swing.JTable tablaResiduos;
    // End of variables declaration//GEN-END:variables
}
