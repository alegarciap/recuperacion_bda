/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.moduloActividades;

import DTOs.ActividadDTO;
import DTOs.InscripcionDTO;
import control.CoordinadorAplicacion;
import control.CoordinadorNegocio;
import exception.NegocioException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Formulario para  del módulo de actividades.
 *
 * @author Alejandra García 252444
 */
public class ParticipantesActividadForm extends javax.swing.JFrame {
    
    public static ActividadDTO actividadSeleccionada;
    private List<InscripcionDTO> inscripciones;

    /**
     * Creates new form ParticipantesActividadForm
     */
    public ParticipantesActividadForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Gestionar Participantes");

        if (actividadSeleccionada != null) {
            cargarDatosActividad();
            cargarParticipantes();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error: No se especificó una actividad para gestionar participantes",
                    "Error", JOptionPane.ERROR_MESSAGE);
            volverAModuloActividades();
        }
    }
    
    private void cargarDatosActividad() {
        // Mostrar información básica de la actividad
        campoActividad.setText(actividadSeleccionada.getNombre() + " - "
                + actividadSeleccionada.getFechaHoraInicio().toString());
    }

    private void cargarParticipantes() {
        try {
            // Obtener inscripciones por nombre de actividad y fecha
            inscripciones = CoordinadorNegocio.getInstancia().consultarInscripcionesPorActividad(
                    actividadSeleccionada.getNombre(),
                    actividadSeleccionada.getFechaHoraInicio().toString());

            // Configurar modelo de tabla
            DefaultTableModel modelo = new DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Nombre", "Correo", "Número Control", "Carrera", "Departamento",
                        "Institución", "Asistencias", "Marcar Asistencia"
                    }
            ) {
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.Integer.class, java.lang.Boolean.class
                };

                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, true
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };

            tblParticipantes.setModel(modelo);

            // Llenar tabla con datos
            for (InscripcionDTO inscripcion : inscripciones) {
                boolean asistio = "ASISTIO".equals(inscripcion.getEstadoAsistencia());

                modelo.addRow(new Object[]{
                    inscripcion.getNombreParticipante(),
                    inscripcion.getCorreoParticipante(),
                    "", // Número control (no disponible en InscripcionDTO)
                    "", // Carrera (no disponible en InscripcionDTO)
                    "", // Departamento (no disponible en InscripcionDTO)
                    "", // Institución (no disponible en InscripcionDTO)
                    0, // Cantidad de asistencias (no disponible en InscripcionDTO)
                    asistio
                });
            }

            // Agregar listener para detectar cambios en las casillas de verificación
            tblParticipantes.getModel().addTableModelListener(e -> {
                if (e.getColumn() == 7) { // Columna de "Marcar Asistencia"
                    int fila = e.getFirstRow();
                    boolean asistio = (boolean) tblParticipantes.getValueAt(fila, 7);
                    marcarAsistencia(fila, asistio);
                }
            });

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar participantes: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void marcarAsistencia(int fila, boolean asistio) {
        try {
            InscripcionDTO inscripcion = inscripciones.get(fila);
            CoordinadorNegocio.getInstancia().registrarAsistencia(inscripcion, asistio);

            // Actualizar la vista (opcional)
            JOptionPane.showMessageDialog(this,
                    "Asistencia actualizada correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar asistencia: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

            // Revertir el cambio en la interfaz
            tblParticipantes.setValueAt(!asistio, fila, 7);
        }
    }

    private void volverAModuloActividades() {
        CoordinadorAplicacion.getInstancia().mostrarModuloActividades();
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblActividad = new javax.swing.JLabel();
        campoActividad = new javax.swing.JTextField();
        btnVolver = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblParticipantes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(217, 217, 217));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Gestionar Participantes");

        lblActividad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblActividad.setForeground(new java.awt.Color(0, 0, 0));
        lblActividad.setText("Actividad: ");

        campoActividad.setEditable(false);
        campoActividad.setBackground(new java.awt.Color(217, 217, 217));
        campoActividad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoActividad.setForeground(new java.awt.Color(0, 0, 0));

        btnVolver.setBackground(new java.awt.Color(0, 0, 0));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnExportar.setBackground(new java.awt.Color(0, 0, 0));
        btnExportar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Exportar PDF");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        tblParticipantes.setBackground(new java.awt.Color(255, 255, 255));
        tblParticipantes.setForeground(new java.awt.Color(0, 0, 0));
        tblParticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Correo", "Número Contr", "Carrera", "Departamento", "Institución", "Asistencias", "Marcar Asistencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblParticipantes.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblParticipantes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 376, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblTitulo)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActividad)
                    .addComponent(campoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volverAModuloActividades();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JTextField campoActividad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblParticipantes;
    // End of variables declaration//GEN-END:variables
}
