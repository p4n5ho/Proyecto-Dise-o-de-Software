/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.DetallesSolicitud;
import Dominio.ElementoQuimico;
import Dominio.EmpresaProductora;
import Dominio.Residuo;
import Dominio.Usuario;
import Excepciones.ResiduosException;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
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
public class frmSolicitudTraslado extends javax.swing.JDialog {

    private IFacadeNegocio facade;
    private Usuario usuario;
    private List<Residuo> listaResiduosEmpresa;
    private List<Object[]> listaResiduosTraslados;

    private static frmSolicitudTraslado instance;

    private frmSolicitudTraslado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(800, 650);

        this.setTitle("Solicitud de traslado");
        this.setLocationRelativeTo(null);
        this.facade = FabricaNegocio.getFachada();
    }

    public static frmSolicitudTraslado getInstance(java.awt.Frame parent, boolean modal, Usuario usuario) {
        if (instance == null) {
            instance = new frmSolicitudTraslado(parent, modal);
        }
        instance.usuario = usuario;
        instance.actualizar();
        return instance;
    }

    private void actualizar() {
        EmpresaProductora empresa = new EmpresaProductora(usuario.getCodigoAcceso(), usuario.getPassword(), usuario.getTipo(), usuario.getNombre());
        listaResiduosEmpresa = new ArrayList<>(facade.obtenerListaResiduosEmpresa(empresa));
        listaResiduosTraslados = new ArrayList<>();
        calendarioFechaLlegada.setSelectedDate(LocalDate.now());
        renderizarTablas();
        actualizarColumnas();
    }

    private void renderizarTablas() {
        vaciarTablas();
        DefaultTableModel modeloTablaResiduosEmpresa = (DefaultTableModel) tablaResiduosEmpresa.getModel();
        Object rowData[] = new Object[3];

        for (int i = 0; i < listaResiduosEmpresa.size(); i++) {
            rowData[0] = listaResiduosEmpresa.get(i).getIDResiduo();
            rowData[1] = listaResiduosEmpresa.get(i).getNombreResiduo();
            rowData[2] = listaResiduosEmpresa.get(i).getElementosQuimicos();
            modeloTablaResiduosEmpresa.addRow(rowData);
        }

        DefaultTableModel modeloTablaResiduosTrasladar = (DefaultTableModel) tablaResiduosTrasladar.getModel();
        Object rowDataTrasladar[] = new Object[5];

        for (int i = 0; i < listaResiduosTraslados.size(); i++) {
            rowDataTrasladar[0] = listaResiduosTraslados.get(i)[0].toString();
            rowDataTrasladar[1] = listaResiduosTraslados.get(i)[1].toString();
            rowDataTrasladar[2] = listaResiduosTraslados.get(i)[2];
            rowDataTrasladar[3] = listaResiduosTraslados.get(i)[3].toString();
            rowDataTrasladar[4] = listaResiduosTraslados.get(i)[4].toString();
            modeloTablaResiduosTrasladar.addRow(rowDataTrasladar);
        }
    }

    private void vaciarTablas() {
        while (tablaResiduosEmpresa.getRowCount() > 0) {
            ((DefaultTableModel) tablaResiduosEmpresa.getModel()).removeRow(0);
        }

        while (tablaResiduosTrasladar.getRowCount() > 0) {
            ((DefaultTableModel) tablaResiduosTrasladar.getModel()).removeRow(0);
        }
    }

    private void establecerResiduosTraslados() {
        listaResiduosTraslados = new ArrayList<>();

        for (int i = 0; i < tablaResiduosTrasladar.getRowCount(); i++) {
            Object[] datos = new Object[5];
            datos[0] = tablaResiduosTrasladar.getValueAt(i, 0);
            datos[1] = tablaResiduosTrasladar.getValueAt(i, 1);
            datos[2] = tablaResiduosTrasladar.getValueAt(i, 2);
            datos[3] = tablaResiduosTrasladar.getValueAt(i, 3);
            datos[4] = tablaResiduosTrasladar.getValueAt(i, 4);

            listaResiduosTraslados.add(i, datos);
        }
    }
    
    private void actualizarColumnas() {
        scrollResiduos.getViewport().setBackground(Color.WHITE);
        tablaResiduosEmpresa.getTableHeader().setReorderingAllowed(false);
        tablaResiduosEmpresa.getTableHeader().setResizingAllowed(true);
        
        scrollResiduosTrasladar.getViewport().setBackground(Color.WHITE);
        tablaResiduosTrasladar.getTableHeader().setReorderingAllowed(false);
        tablaResiduosTrasladar.getTableHeader().setResizingAllowed(true);

        Font f = new Font("Seoge UI", Font.BOLD, 12);
        tablaResiduosEmpresa.getTableHeader().setFont(f);
        tablaResiduosTrasladar.getTableHeader().setFont(f);
        
        tablaResiduosEmpresa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < tablaResiduosEmpresa.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tablaResiduosEmpresa.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            for (int r = 0; r < tablaResiduosEmpresa.getRowCount(); r++) {
                renderer = tablaResiduosEmpresa.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(tablaResiduosEmpresa, tablaResiduosEmpresa.getValueAt(r, i),
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        scrollResiduos = new javax.swing.JScrollPane();
        tablaResiduosEmpresa = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        scrollResiduosTrasladar = new javax.swing.JScrollPane();
        tablaResiduosTrasladar = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        calendarioFechaLlegada = new com.github.lgooddatepicker.components.CalendarPanel();
        solicitarTraslado = new javax.swing.JButton();
        cancelarSolicitud = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        spinnerCantidad = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        comboMedida = new javax.swing.JComboBox<>();
        agregarResiduoTraslado = new javax.swing.JButton();
        eliminarResiduoTraslado = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 650));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablaResiduosEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tablaResiduosEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Compuesto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResiduosEmpresa.setRowHeight(32);
        tablaResiduosEmpresa.setRowMargin(2);
        tablaResiduosEmpresa.setSelectionBackground(new java.awt.Color(122, 235, 122));
        scrollResiduos.setViewportView(tablaResiduosEmpresa);

        jLabel2.setText("Residuos");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tablaResiduosTrasladar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tablaResiduosTrasladar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Compuesto", "Cantidad", "Tipo"
            }
        ));
        tablaResiduosTrasladar.setRowHeight(32);
        tablaResiduosTrasladar.setRowMargin(2);
        tablaResiduosTrasladar.setSelectionBackground(new java.awt.Color(122, 235, 122));
        scrollResiduosTrasladar.setViewportView(tablaResiduosTrasladar);

        jLabel3.setText("Residuos a trasladar");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de llegada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        calendarioFechaLlegada.setBackground(new java.awt.Color(255, 255, 255));
        calendarioFechaLlegada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calendarioFechaLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calendarioFechaLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        solicitarTraslado.setText("Solicitar Traslado");
        solicitarTraslado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        solicitarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitarTrasladoActionPerformed(evt);
            }
        });

        cancelarSolicitud.setText("Cancelar");
        cancelarSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cancelarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarSolicitudActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asigna Residuos a la Solicitud", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel6.setText("Cantidad:");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        spinnerCantidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel7.setText("Medida:");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        comboMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KG", "LT" }));
        comboMedida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        agregarResiduoTraslado.setText("Agregar Residuo a Trasladar");
        agregarResiduoTraslado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agregarResiduoTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarResiduoTrasladoActionPerformed(evt);
            }
        });

        eliminarResiduoTraslado.setText("Remover Residuo a trasladar");
        eliminarResiduoTraslado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eliminarResiduoTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarResiduoTrasladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(agregarResiduoTraslado)
                .addGap(18, 18, 18)
                .addComponent(eliminarResiduoTraslado)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eliminarResiduoTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(agregarResiduoTraslado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(comboMedida))))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(122, 235, 122));

        jLabel1.setText("Solicitud de Traslado");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addComponent(solicitarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollResiduos, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(scrollResiduosTrasladar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())))))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollResiduos, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(scrollResiduosTrasladar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(solicitarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancelarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solicitarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitarTrasladoActionPerformed
        String mensajeFinal = "";
        establecerResiduosTraslados();
        
        if(listaResiduosTraslados.isEmpty()){
            mensajeFinal += "Asigne al menos un residuo a la solicitud de traslado";
        }
        
        //Checa que no sea una fecha pasada //NO JUZGAR EL CODIGO HECHO CON AMOR
        GregorianCalendar fechaFinal = null;
        if (calendarioFechaLlegada.getSelectedDate() != null) {
            String fecha = calendarioFechaLlegada.getSelectedDate().toString();
            String fechaA[] = fecha.split("-");
            fechaFinal = new GregorianCalendar(Integer.valueOf(fechaA[0]), Integer.valueOf(fechaA[1]), Integer.valueOf(fechaA[2]));
            GregorianCalendar fechaHoy = new GregorianCalendar(TimeZone.getTimeZone("GMT-7"));
            fechaHoy.set(GregorianCalendar.MONTH, fechaHoy.get(GregorianCalendar.MONTH)+1);
            if(fechaFinal.before(fechaHoy)){
                mensajeFinal += "La fecha que ha escogido es de hoy o de dias pasados";
            }
        } else {
            mensajeFinal += "No ha seleccionado una fecha.\n";
        }
        
        if(mensajeFinal.equalsIgnoreCase("")){
            try {
                List<DetallesSolicitud> detalles = new ArrayList<>();
                
                for (int i = 0; i < listaResiduosTraslados.size(); i++) {
                    String IDResiduo = listaResiduosTraslados.get(i)[0].toString();
                    Float cantidad = Float.valueOf(listaResiduosTraslados.get(i)[3].toString());
                    String medida = listaResiduosTraslados.get(i)[4].toString();
                    detalles.add(new DetallesSolicitud(0, IDResiduo, cantidad, medida));
                }
                String fecha = calendarioFechaLlegada.getSelectedDate().toString();
                
                facade.agregarSolicitudTraslado(fecha, usuario.getCodigoAcceso(), detalles);
                JOptionPane.showMessageDialog(this, "Solicitud registrada con exito", "Solicitud realizada", JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Eso no es un número", "Error al hacer la solicitud", JOptionPane.ERROR_MESSAGE);
            } catch (ResiduosException residuosException) {
                JOptionPane.showMessageDialog(this, residuosException.getMessage(), "Error al hacer la solicitud", JOptionPane.ERROR_MESSAGE);
                this.actualizar();
            }           
        }else{
            JOptionPane.showMessageDialog(this, mensajeFinal, "Error al hacer la solicitud", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_solicitarTrasladoActionPerformed

    private void cancelarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarSolicitudActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelarSolicitudActionPerformed

    private void agregarResiduoTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarResiduoTrasladoActionPerformed
        double cantidad = 0;
        try {
            spinnerCantidad.commitEdit();
            cantidad = (Double) spinnerCantidad.getValue();
            if (tablaResiduosEmpresa.getRowCount() > 0) {
                int filaSeleccionada = tablaResiduosEmpresa.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    if (cantidad > 0) {
                        Residuo residuoAgregar = listaResiduosEmpresa.get(filaSeleccionada);
                        Object data[] = new Object[5];
                        data[0] = residuoAgregar.getIDResiduo();
                        data[1] = residuoAgregar.getNombreResiduo();
                        data[2] = residuoAgregar.getElementosQuimicos();
                        data[3] = cantidad;
                        data[4] = comboMedida.getSelectedItem().toString();
                        listaResiduosTraslados.add(data);
                        listaResiduosEmpresa.remove(filaSeleccionada);
                        renderizarTablas();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Seleccione una cantidad distinta de cero del selector de cantidad");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un elemento de la lista de residuos que produce su empresa");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay residuos en la tabla de residuos");
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Solo se aceptan números en el selector de cantidad");
        }
    }//GEN-LAST:event_agregarResiduoTrasladoActionPerformed

    private void eliminarResiduoTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarResiduoTrasladoActionPerformed
        if (tablaResiduosTrasladar.getRowCount() > 0) {
            int filaSeleccionada = tablaResiduosTrasladar.getSelectedRow();
            if (filaSeleccionada >= 0) {
                if (!listaResiduosTraslados.isEmpty()) {
                    Object data[] = new Object[5];
                    data[0] = listaResiduosTraslados.get(filaSeleccionada)[0];
                    data[1] = listaResiduosTraslados.get(filaSeleccionada)[1];
                    data[2] = listaResiduosTraslados.get(filaSeleccionada)[2];
                    data[3] = listaResiduosTraslados.get(filaSeleccionada)[3];
                    data[4] = listaResiduosTraslados.get(filaSeleccionada)[4];
                    listaResiduosEmpresa.add(new Residuo((String) data[0], (String) data[1], (ArrayList<ElementoQuimico>) data[2]));
                    listaResiduosTraslados.remove(filaSeleccionada);
                    renderizarTablas();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un elemento de la lista de residuos que produce su empresa");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay residuos en la tabla de residuos");
        }
    }//GEN-LAST:event_eliminarResiduoTrasladoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarResiduoTraslado;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.github.lgooddatepicker.components.CalendarPanel calendarioFechaLlegada;
    private javax.swing.JButton cancelarSolicitud;
    private javax.swing.JComboBox<String> comboMedida;
    private javax.swing.JButton eliminarResiduoTraslado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane scrollResiduos;
    private javax.swing.JScrollPane scrollResiduosTrasladar;
    private javax.swing.JButton solicitarTraslado;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JTable tablaResiduosEmpresa;
    private javax.swing.JTable tablaResiduosTrasladar;
    // End of variables declaration//GEN-END:variables
}
