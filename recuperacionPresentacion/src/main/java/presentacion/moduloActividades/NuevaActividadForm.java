/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.moduloActividades;

import DTOs.ActividadCreacionDTO;
import DTOs.EventoDTO;
import DTOs.LugarDTO;
import DTOs.OrganizadorDTO;
import control.CoordinadorAplicacion;
import control.CoordinadorNegocio;
import exception.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Formulario para agregar una actividad en el módulo de actividades.
 *
 * @author Alejandra García 252444
 */
public class NuevaActividadForm extends javax.swing.JFrame {
    
    private List<EventoDTO> listaEventos;
    private List<LugarDTO> listaLugaresFiltrados;
    private List<OrganizadorDTO> listaOrganizadoresFiltrados;
    private EventoDTO eventoSeleccionado;
    private LugarDTO lugarSeleccionado;
    private OrganizadorDTO organizadorSeleccionado;

    private DefaultListModel<String> modeloListaEventos;
    private DefaultListModel<String> modeloListaLugares;
    private DefaultListModel<String> modeloListaOrganizadores;

    /**
     * Creates new form NuevaActividadForm
     */
    public NuevaActividadForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Nueva Actividad");

        // Inicializar listas
        listaEventos = new ArrayList<>();
        listaLugaresFiltrados = new ArrayList<>();
        listaOrganizadoresFiltrados = new ArrayList<>();

        // Inicializar modelos de listas
        modeloListaEventos = new DefaultListModel<>();
        modeloListaLugares = new DefaultListModel<>();
        modeloListaOrganizadores = new DefaultListModel<>();

        jList.setModel(modeloListaEventos);
        jList2.setModel(modeloListaLugares);
        jList3.setModel(modeloListaOrganizadores);

        // Cargar datos iniciales
        cargarEventos();

        // Configurar listeners para las búsquedas
        configurarListeners();
    }
    
    private void configurarListeners() {
        // Evento para búsqueda
        campoEvento.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarEventos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarEventos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarEventos();
            }
        });

        // Lugar para búsqueda
        campoCorreo6.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarLugares();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarLugares();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarLugares();
            }
        });

        // Organizador para búsqueda
        campoCorreo7.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarOrganizadores();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarOrganizadores();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarOrganizadores();
            }
        });

        // Selección de evento
        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jList.getSelectedIndex() != -1) {
                int index = jList.getSelectedIndex();
                eventoSeleccionado = listaEventos.get(index);
                campoEvento.setText(eventoSeleccionado.getTitulo());
            }
        });

        // Selección de lugar
        jList2.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jList2.getSelectedIndex() != -1) {
                int index = jList2.getSelectedIndex();
                lugarSeleccionado = listaLugaresFiltrados.get(index);
                campoCorreo6.setText(lugarSeleccionado.getNombre());
            }
        });

        // Selección de organizador
        jList3.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jList3.getSelectedIndex() != -1) {
                int index = jList3.getSelectedIndex();
                organizadorSeleccionado = listaOrganizadoresFiltrados.get(index);
                campoCorreo7.setText(organizadorSeleccionado.getNombre());
            }
        });
    }

    private void cargarEventos() {
        try {
            listaEventos = CoordinadorNegocio.getInstancia().consultarTodosEventos();
            modeloListaEventos.clear();
            for (EventoDTO evento : listaEventos) {
                modeloListaEventos.addElement(evento.getTitulo());
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar eventos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarEventos() {
        String filtro = campoEvento.getText().toLowerCase();
        modeloListaEventos.clear();
        for (EventoDTO evento : listaEventos) {
            if (evento.getTitulo().toLowerCase().contains(filtro)) {
                modeloListaEventos.addElement(evento.getTitulo());
            }
        }
    }

    private void filtrarLugares() {
        String filtro = campoCorreo6.getText().toLowerCase();
        modeloListaLugares.clear();
        listaLugaresFiltrados.clear();

        try {
            List<LugarDTO> lugares = CoordinadorNegocio.getInstancia().buscarLugaresPorNombre(filtro);

            for (LugarDTO lugar : lugares) {
                modeloListaLugares.addElement(lugar.getNombre() + " - Capacidad: " + lugar.getCapacidad());
                listaLugaresFiltrados.add(lugar);
            }
        } catch (NegocioException ex) {
            
        }
    }

    private void filtrarOrganizadores() {
        String filtro = campoCorreo7.getText().toLowerCase();
        modeloListaOrganizadores.clear();
        listaOrganizadoresFiltrados.clear();

        try {
            List<OrganizadorDTO> organizadores = CoordinadorNegocio.getInstancia().consultarTodosOrganizadores();

            for (OrganizadorDTO organizador : organizadores) {
                if (organizador.getNombre().toLowerCase().contains(filtro)) {
                    modeloListaOrganizadores.addElement(organizador.getNombre());
                    listaOrganizadoresFiltrados.add(organizador);
                }
            }
        } catch (NegocioException ex) {
            
        }
    }

    private boolean validarCampos() {
        if (campoNombreActividad.getText().trim().isEmpty()) {
            mostrarError("El nombre de la actividad es obligatorio");
            return false;
        }

        if (campoTipo.getText().trim().isEmpty()) {
            mostrarError("El tipo de actividad es obligatorio");
            return false;
        }

        if (eventoSeleccionado == null) {
            mostrarError("Debe seleccionar un evento");
            return false;
        }

        if (lugarSeleccionado == null) {
            mostrarError("Debe seleccionar un lugar");
            return false;
        }

        if (organizadorSeleccionado == null) {
            mostrarError("Debe seleccionar un organizador");
            return false;
        }

        try {
            Integer.parseInt(campoCorreo5.getText());
        } catch (NumberFormatException e) {
            mostrarError("La capacidad debe ser un número entero");
            return false;
        }

        try {
            Integer.parseInt(campoDuracion.getText());
        } catch (NumberFormatException e) {
            mostrarError("La duración debe ser un número entero de minutos");
            return false;
        }

        if (dateTimePicker.getDateTimeStrict() == null) {
            mostrarError("Debe seleccionar una fecha y hora");
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEvento = new javax.swing.JLabel();
        campoEvento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        btnVolver = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblNombreActividad = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        campoNombreActividad = new javax.swing.JTextField();
        campoTipo = new javax.swing.JTextField();
        campoDuracion = new javax.swing.JTextField();
        dateTimePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        lblApellidoMaterno2 = new javax.swing.JLabel();
        campoCorreo5 = new javax.swing.JTextField();
        lblCapacidad = new javax.swing.JLabel();
        campoCorreo6 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        lblApellidoMaterno4 = new javax.swing.JLabel();
        campoCorreo7 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(217, 217, 217));

        lblEvento.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblEvento.setForeground(new java.awt.Color(0, 0, 0));
        lblEvento.setText("Evento:");

        campoEvento.setBackground(new java.awt.Color(255, 255, 255));
        campoEvento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoEvento.setForeground(new java.awt.Color(0, 0, 0));

        jList.setBackground(new java.awt.Color(255, 255, 255));
        jList.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jList);

        btnVolver.setBackground(new java.awt.Color(0, 0, 0));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Cancelar");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnExportar.setBackground(new java.awt.Color(0, 0, 0));
        btnExportar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Guardar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Nueva Actividad");

        lblNombreActividad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombreActividad.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreActividad.setText("Nombre de la actividad:");

        lblTipo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(0, 0, 0));
        lblTipo.setText("Tipo:");

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDuracion.setForeground(new java.awt.Color(0, 0, 0));
        lblDuracion.setText("Duración:");

        lblFechaHora.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFechaHora.setForeground(new java.awt.Color(0, 0, 0));
        lblFechaHora.setText("Fecha y hora:");

        campoNombreActividad.setBackground(new java.awt.Color(255, 255, 255));
        campoNombreActividad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNombreActividad.setForeground(new java.awt.Color(0, 0, 0));

        campoTipo.setBackground(new java.awt.Color(255, 255, 255));
        campoTipo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTipo.setForeground(new java.awt.Color(0, 0, 0));

        campoDuracion.setBackground(new java.awt.Color(255, 255, 255));
        campoDuracion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDuracion.setForeground(new java.awt.Color(0, 0, 0));

        lblApellidoMaterno2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblApellidoMaterno2.setForeground(new java.awt.Color(0, 0, 0));
        lblApellidoMaterno2.setText("Seleccionar lugar:");

        campoCorreo5.setBackground(new java.awt.Color(255, 255, 255));
        campoCorreo5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCorreo5.setForeground(new java.awt.Color(0, 0, 0));

        lblCapacidad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCapacidad.setForeground(new java.awt.Color(0, 0, 0));
        lblCapacidad.setText("Capacidad:");

        campoCorreo6.setBackground(new java.awt.Color(255, 255, 255));
        campoCorreo6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCorreo6.setForeground(new java.awt.Color(0, 0, 0));

        jList2.setBackground(new java.awt.Color(255, 255, 255));
        jList2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jList2.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(jList2);

        lblApellidoMaterno4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblApellidoMaterno4.setForeground(new java.awt.Color(0, 0, 0));
        lblApellidoMaterno4.setText("Seleccionar organizador:");

        campoCorreo7.setBackground(new java.awt.Color(255, 255, 255));
        campoCorreo7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCorreo7.setForeground(new java.awt.Color(0, 0, 0));
        campoCorreo7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCorreo7ActionPerformed(evt);
            }
        });

        jList3.setBackground(new java.awt.Color(255, 255, 255));
        jList3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jList3.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(jList3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoCorreo7, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoMaterno4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(556, 556, 556))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoCorreo5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(campoDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(67, 67, 67)
                                                .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(campoNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(35, 35, 35)
                                                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(lblApellidoMaterno2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(campoCorreo6, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(288, 288, 288)
                                        .addComponent(dateTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitulo)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEvento)
                    .addComponent(campoEvento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreActividad)
                    .addComponent(campoNombreActividad))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(campoTipo)
                    .addComponent(lblDuracion)
                    .addComponent(campoDuracion)
                    .addComponent(lblFechaHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidoMaterno2)
                    .addComponent(lblCapacidad)
                    .addComponent(campoCorreo5, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(campoCorreo6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lblApellidoMaterno4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCorreo7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de cancelar? Se perderán los datos ingresados",
                "Confirmar cancelación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            CoordinadorAplicacion.getInstancia().mostrarModuloActividades();
            this.dispose();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        if (!validarCampos()) {
            return;
        }

        try {
            // Crear DTO con los datos de la actividad
            ActividadCreacionDTO nuevaActividad = new ActividadCreacionDTO();
            nuevaActividad.setNombre(campoNombreActividad.getText().trim());
            nuevaActividad.setTipo(campoTipo.getText().trim());
            nuevaActividad.setCapacidad(Integer.parseInt(campoCorreo5.getText().trim()));
            nuevaActividad.setDuracion(Integer.parseInt(campoDuracion.getText().trim()));
            nuevaActividad.setFechaHoraInicio(dateTimePicker.getDateTimeStrict());

            // En lugar de IDs, usar los objetos completos o propiedades naturales
            nuevaActividad.setNombreEvento(eventoSeleccionado.getTitulo());
            nuevaActividad.setNombreLugar(lugarSeleccionado.getNombre());
            nuevaActividad.setNombreOrganizador(organizadorSeleccionado.getNombre());

            // Verificar si hay conflictos de horario
            boolean hayConflicto = CoordinadorNegocio.getInstancia()
                    .verificarConflictosHorario(nuevaActividad, lugarSeleccionado.getNombre());

            if (hayConflicto) {
                int confirmacion = JOptionPane.showConfirmDialog(this,
                        "Existe un conflicto de horario con otra actividad en el mismo lugar.\n"
                        + "¿Desea continuar de todos modos?",
                        "Conflicto de horario", JOptionPane.YES_NO_OPTION);

                if (confirmacion != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            // Registrar la actividad
            CoordinadorNegocio.getInstancia().registrarActividad(nuevaActividad);

            JOptionPane.showMessageDialog(this,
                    "Actividad registrada correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Volver al módulo de actividades
            CoordinadorAplicacion.getInstancia().mostrarModuloActividades();
            this.dispose();

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar la actividad: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en los datos numéricos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void campoCorreo7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCorreo7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCorreo7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JTextField campoCorreo5;
    private javax.swing.JTextField campoCorreo6;
    private javax.swing.JTextField campoCorreo7;
    private javax.swing.JTextField campoDuracion;
    private javax.swing.JTextField campoEvento;
    private javax.swing.JTextField campoNombreActividad;
    private javax.swing.JTextField campoTipo;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker;
    private javax.swing.JList<String> jList;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblApellidoMaterno2;
    private javax.swing.JLabel lblApellidoMaterno4;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEvento;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblNombreActividad;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
