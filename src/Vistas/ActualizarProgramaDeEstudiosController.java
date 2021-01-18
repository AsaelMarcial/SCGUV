/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.Academia;
import Pojos.BibliografiaProgramaEstudio;
import Pojos.Campus;
import Pojos.Carrera;
import Pojos.EvaluacionProgramaEstudio;
import Pojos.ExperienciaEducativa;
import Pojos.ProgramaEstudio;
import Util.Herramientas;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asael
 */
public class ActualizarProgramaDeEstudiosController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtAcreditacion;
    @FXML
    private TextField txtModalidad;
    @FXML
    private TextField txtOportunidades;
    @FXML
    private TextField txtCreditos;
    @FXML
    private TextArea txtTeoricos;
    @FXML
    private TextArea txtHeuristicos;
    @FXML
    private TextArea txtAxiologicos;
    @FXML
    private TableView<BibliografiaProgramaEstudio> tablevBibliografia;
    ObservableList<BibliografiaProgramaEstudio> obsListBibliografia;
    @FXML
    private TableColumn tbcAutor;
    @FXML
    private TableColumn tbcTitulo;
    @FXML
    private TableColumn tbcEditorial;
    @FXML
    private TableColumn tbcAnio;
    @FXML
    private TableView<EvaluacionProgramaEstudio> tablevEvaluacion;
    ObservableList<EvaluacionProgramaEstudio> obsListEval;
    @FXML
    private TableColumn tbcEvidencia;
    @FXML
    private TableColumn tbcCriterio;
    @FXML
    private TableColumn tbcAmbito;
    @FXML
    private TableColumn tbcPorcentaje;
    @FXML
    private Button btCancelar;
    @FXML
    private ComboBox<Academia> combAcademia;
    ObservableList<Academia> obsListAcademia;
    @FXML
    private ComboBox<Carrera> combCarrera;
    ObservableList<Carrera> obsListCarrera;
    @FXML
    private ComboBox<Campus> combCampus;
    ObservableList<Campus> obsListCampus;
    @FXML
    private ComboBox<ExperienciaEducativa> combExperiencia;
    ObservableList<ExperienciaEducativa> obsListEE;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtAnio;
    @FXML
    private Button btnAddBibliografia;
    @FXML
    private DatePicker dateModificacion;
    @FXML
    private DatePicker dateAprobacion;
    @FXML
    private Button btnAddEval;
    @FXML
    private TextField txtPorcentaje;
    @FXML
    private TextField txtAmbito;
    @FXML
    private TextField txtCriterio;
    @FXML
    private TextField txtEvidencia;
    @FXML
    private Button btnBorrarBibliografia;
    @FXML
    private Button btnBorrarEval;
    @FXML
    private TextArea txtPerfil;
    @FXML
    private Button btnActualizar;
    Alert alertConexion;
    private int idProgramaRegistrado;

    ObservableList<ProgramaEstudio> obsListPrograma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateModificacion.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate hoy = LocalDate.now();
                setDisable(empty || date.compareTo(hoy) < 0);
            }
        });
        dateModificacion.setEditable(false);

        dateAprobacion.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate hoy = LocalDate.now();
                setDisable(empty || date.compareTo(hoy) < 0);
            }
        });
        dateAprobacion.setEditable(false);

        obsListAcademia = FXCollections.observableArrayList();
        obsListCarrera = FXCollections.observableArrayList();
        obsListCampus = FXCollections.observableArrayList();
        obsListEE = FXCollections.observableArrayList();

        obsListBibliografia = FXCollections.observableArrayList();
        this.tbcAutor.setCellValueFactory(new PropertyValueFactory("autor"));
        this.tbcTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        this.tbcEditorial.setCellValueFactory(new PropertyValueFactory("editorial"));
        this.tbcAnio.setCellValueFactory(new PropertyValueFactory("anio"));

        obsListEval = FXCollections.observableArrayList();
        this.tbcEvidencia.setCellValueFactory(new PropertyValueFactory("evidencia"));
        this.tbcCriterio.setCellValueFactory(new PropertyValueFactory("criterio"));
        this.tbcAmbito.setCellValueFactory(new PropertyValueFactory("ambito"));
        this.tbcPorcentaje.setCellValueFactory(new PropertyValueFactory("porcentaje"));

    }

    public void pasarPrograma(ProgramaEstudio programa) {
        obsListPrograma.add(0, programa);
        txtPerfil.setText(programa.getPerfil());
        txtCodigo.setText(programa.getCodigo());
        txtAcreditacion.setText(programa.getAcreditacion());
        txtCreditos.setText(String.valueOf(programa.getCreditos()));
        txtModalidad.setText(programa.getModalidad());
        txtOportunidades.setText(programa.getOportunidades());
        cargarComboBoxAcademia();
        cargarComboBoxCarrera();
        cargarComboBoxCampus();
        cargarComboBoxExperiencia();
        cargarBibliografia();
        cargarEvaluacion();

    }

    private void cargarComboBoxAcademia() {
        ProgramaEstudio programaRecibido = new ProgramaEstudio();
        programaRecibido = obsListPrograma.get(0);
        int idAcademia = programaRecibido.getIdAreaAcademica();
        Academia academiaRecibida = new Academia();
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM academia";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Academia a = new Academia();
                    a.setIdAcademia(rs.getInt("idAcademia"));
                    a.setNombre(rs.getString("nombre"));
                    a.setIdCoordinador(rs.getInt("idCoordinador"));
                    obsListAcademia.add(a);

                    if (rs.getInt("idAcademia") == idAcademia) {
                        academiaRecibida.setIdAcademia(idAcademia);
                        academiaRecibida.setNombre(rs.getString("nombre"));
                    }
                }
                combAcademia.setItems(obsListAcademia);
                combAcademia.valueProperty().setValue(academiaRecibida);
                conn.close();

            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void cargarComboBoxCarrera() {
        ProgramaEstudio programaRecibido = new ProgramaEstudio();
        programaRecibido = obsListPrograma.get(0);
        int idCarrera = programaRecibido.getIdCarrera();
        Carrera carreraRecibida = new Carrera();
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM carrera";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Carrera carr = new Carrera();
                    carr.setIdCarrera(rs.getInt("idcarrera"));
                    carr.setNombre(rs.getString("nombre"));
                    carr.setIdFacultad(rs.getInt("idFacultad"));
                    obsListCarrera.add(carr);
                    if (rs.getInt("idCarrera") == idCarrera) {
                        carreraRecibida.setIdCarrera(idCarrera);
                        carreraRecibida.setNombre(rs.getString("nombre"));
                        carreraRecibida.setIdFacultad(rs.getInt("idFacultad"));
                    }
                }
                combCarrera.setItems(obsListCarrera);
                combCarrera.valueProperty().setValue(carreraRecibida);
                conn.close();

            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void cargarComboBoxCampus() {
        ProgramaEstudio programaRecibido = new ProgramaEstudio();
        programaRecibido = obsListPrograma.get(0);
        int idCampus = programaRecibido.getIdCampus();
        Campus campusRecibido = new Campus();
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM campus";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Campus camp = new Campus();
                    camp.setIdCampus(rs.getInt("idCampus"));
                    camp.setNombre(rs.getString("nombre"));
                    obsListCampus.add(camp);
                    if (rs.getInt("idCampus") == idCampus) {
                        campusRecibido.setIdCampus(idCampus);
                        campusRecibido.setNombre(rs.getString("nombre"));
                    }
                }
                combCampus.setItems(obsListCampus);
                combCampus.valueProperty().setValue(campusRecibido);
                conn.close();

            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void cargarComboBoxExperiencia() {
        ProgramaEstudio programaRecibido = new ProgramaEstudio();
        programaRecibido = obsListPrograma.get(0);
        int idEE = programaRecibido.getIdExperienciaEducativa();
        ExperienciaEducativa eeRecibida = new ExperienciaEducativa();
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM experienciaEducativa";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ExperienciaEducativa e = new ExperienciaEducativa();
                    e.setIdExperienciaEducativa(rs.getInt("idExperienciaEducativa"));
                    e.setNombreExperienciaEducativa(rs.getString("nombre"));
                    e.setNrcExperienciaEducativa(rs.getString("nrc"));
                    e.setIdAcademia(rs.getInt("idAcademia"));
                    obsListEE.add(e);
                    if (rs.getInt("idExperienciaEducativa") == idEE) {
                        eeRecibida.setIdExperienciaEducativa(idEE);
                        eeRecibida.setNombreExperienciaEducativa("nombre");
                        eeRecibida.setNrcExperienciaEducativa("nrc");
                        eeRecibida.setIdAcademia(rs.getInt("idAcademia"));
                    }
                }
                combExperiencia.setItems(obsListEE);
                combExperiencia.valueProperty().setValue(eeRecibida);
                conn.close();
            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }


    private void cargarBibliografia() {
        ProgramaEstudio programa = new ProgramaEstudio();
        programa = obsListPrograma.get(0);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM programaEstudioBibliografia WHERE idProgramaEstudio = " + programa.getIdProgramaEstudio();
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BibliografiaProgramaEstudio bibliografia = new BibliografiaProgramaEstudio();
                    bibliografia.setAutor(rs.getString("autor"));
                    bibliografia.setTitulo(rs.getString("titulo"));
                    bibliografia.setEditorial(rs.getString("editorial"));
                    bibliografia.setAnio(rs.getInt("anio"));
                    obsListBibliografia.add(bibliografia);
                }
                tablevBibliografia.setItems(obsListBibliografia);
                conn.close();

            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void cargarEvaluacion() {
        ProgramaEstudio programa = new ProgramaEstudio();
        programa = obsListPrograma.get(0);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM programaEstudioEvaluacion WHERE idProgramaEstudio = " + programa.getIdProgramaEstudio();
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    EvaluacionProgramaEstudio eval = new EvaluacionProgramaEstudio();
                    eval.setEvidencia(rs.getString("evidencia"));
                    eval.setCriterio(rs.getString("criterio"));
                    eval.setAmbito(rs.getString("ambito"));
                    eval.setPorcentaje(rs.getInt("porcentaje"));
                    obsListEval.add(eval);
                }
                tablevEvaluacion.setItems(obsListEval);
                conn.close();

            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    @FXML
    private void clicAddBibliografia(ActionEvent event) {
        BibliografiaProgramaEstudio bibliografia = new BibliografiaProgramaEstudio();
        String autor = txtAutor.getText();
        String titulo = txtTitulo.getText();
        String editorial = txtEditorial.getText();
        int anio = parseInt(txtAnio.getText());

        if (!(autor.isEmpty() || titulo.isEmpty() || editorial.isEmpty())) {
            bibliografia.setAutor(autor);
            bibliografia.setTitulo(titulo);
            bibliografia.setEditorial(editorial);
            bibliografia.setAnio(anio);
            obsListBibliografia.add(bibliografia);
            txtAutor.setText("");
            txtTitulo.setText("");
            txtEditorial.setText("");
            txtAnio.setText("");
            tablevBibliografia.setItems(obsListBibliografia);
            return;
        }
        alertConexion = Herramientas.constructorDeAlertas("Campos incorrectos",
                "Existen campos faltantes o son incorrectos para agregar una nueva bibliografía a la tabla", Alert.AlertType.ERROR);

        alertConexion.showAndWait();
    }

    @FXML
    private void clicAddEval(ActionEvent event) {
        EvaluacionProgramaEstudio eval = new EvaluacionProgramaEstudio();
        String evidencia = txtEvidencia.getText();
        String criterio = txtCriterio.getText();
        String ambito = txtAmbito.getText();
        int porcentaje = parseInt(txtPorcentaje.getText());

        if (!(evidencia.isEmpty() || criterio.isEmpty() || ambito.isEmpty())) {
            eval.setEvidencia(evidencia);
            eval.setCriterio(criterio);
            eval.setAmbito(ambito);
            eval.setPorcentaje(porcentaje);
            obsListEval.add(eval);
            txtEvidencia.setText("");
            txtCriterio.setText("");
            txtAmbito.setText("");
            txtPorcentaje.setText("");
            tablevEvaluacion.setItems(obsListEval);
            return;
        }
        alertConexion = Herramientas.constructorDeAlertas("Campos incorrectos",
                "Existen campos faltantes o son incorrectos para agregar una nueva evaluación a la tabla", Alert.AlertType.ERROR);

        alertConexion.showAndWait();
    }

    @FXML
    private void clicBorrarBibliografia(ActionEvent event) {
        obsListBibliografia.remove(tablevBibliografia.getSelectionModel().getSelectedItem());
        tablevBibliografia.setItems(obsListBibliografia);
    }

    @FXML
    private void clicBorrarEval(ActionEvent event) {
        obsListEval.remove(tablevEvaluacion.getSelectionModel().getSelectedItem());
        tablevEvaluacion.setItems(obsListEval);
    }

    @FXML
    private void clicElementoBibliografia(MouseEvent event) {
        if (tablevBibliografia.hasProperties()) {
            btnBorrarBibliografia.setDisable(false);
        } else {
            btnBorrarBibliografia.setDisable(true);
        }
    }

    @FXML
    private void clicElementoEval(MouseEvent event) {
        if (tablevEvaluacion.hasProperties()) {
            btnBorrarEval.setDisable(false);
        } else {
            btnBorrarEval.setDisable(true);
        }
    }

    @FXML
    private void activarBtnBibliografia(KeyEvent event) {
        btnAddBibliografia.setDisable(false);
    }

    @FXML
    private void activarBtnEval(KeyEvent event) {
        btnAddEval.setDisable(false);
    }

    @FXML
    private void clicActualizar(ActionEvent event) {
        ProgramaEstudio programaRecibido = new ProgramaEstudio();
        programaRecibido = obsListPrograma.get(0);
        int id = programaRecibido.getIdProgramaEstudio();
        idProgramaRegistrado = id;
        String codigo = txtCodigo.getText();
        String creditos = txtCreditos.getText();
        String modalidad = txtModalidad.getText();
        String oportunidades = txtOportunidades.getText();
        String perfil = txtPerfil.getText();
        String acreditacion = txtAcreditacion.getText();
        String saberTeorico = txtTeoricos.getText();
        String saberHeuristico = txtHeuristicos.getText();
        String saberAxiologico = txtAxiologicos.getText();

        if (validarSeleccionElementos() && validarCampos(codigo, creditos, modalidad, oportunidades, perfil, acreditacion)) {
            if (!obsListBibliografia.isEmpty() && !obsListEval.isEmpty() && dateModificacion.getValue() != null && dateAprobacion.getValue() != null) {

                LocalDate idFechaMod = dateModificacion.getValue();
                LocalDate idFechaElab = dateAprobacion.getValue();
                String fechaElab = programaRecibido.getFechaElaboracion();
                String fechaMod = idFechaMod.toString();
                String fechaAprob = idFechaMod.toString();

                ExperienciaEducativa exp = new ExperienciaEducativa();
                exp = combExperiencia.getSelectionModel().getSelectedItem();
                int idExperiencia = exp.getIdExperienciaEducativa();

                Academia academia = new Academia();
                academia = combAcademia.getSelectionModel().getSelectedItem();
                int idAcademia = academia.getIdAcademia();

                Carrera carrera = new Carrera();
                carrera = combCarrera.getSelectionModel().getSelectedItem();
                int idCarrera = carrera.getIdCarrera();

                Campus campus = new Campus();
                campus = combCampus.getSelectionModel().getSelectedItem();
                int idCampus = campus.getIdCampus();

                actualizarPrograma(id, codigo, creditos, modalidad, oportunidades, perfil, acreditacion, fechaElab, fechaMod, fechaAprob, idExperiencia, idAcademia, idCarrera, idCampus, saberTeorico, saberHeuristico, saberAxiologico);

                eliminarBibliografias();
                for (BibliografiaProgramaEstudio bibliografiaIndice : obsListBibliografia) {
                    registrarBibliografia(bibliografiaIndice.getAutor(), bibliografiaIndice.getTitulo(), bibliografiaIndice.getEditorial(), bibliografiaIndice.getAnio());
                }

                eliminarEvaluaciones();
                for (EvaluacionProgramaEstudio evalIndice : obsListEval) {
                    registrarTemas(evalIndice.getEvidencia(), evalIndice.getCriterio(), evalIndice.getAmbito(), evalIndice.getPorcentaje());
                }

                concluirActualizacion();

                return;
            } else {
                alertConexion = Herramientas.constructorDeAlertas("Tablas vacías",
                        "Agregar información a la tabla!", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Campos incorrectos",
                    "Existen campos faltantes o son incorrectos para registrar el plan de curso", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    @FXML
    private void btCancelar(ActionEvent event) {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    private boolean validarSeleccionElementos() {
        if (combAcademia.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        if (combCarrera.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        if (combCampus.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        return true;
    }

    private boolean validarCampos(String codigo, String creditos, String modalidad, String oportunidades, String perfil, String acreditacion) {
        if (codigo.isEmpty()) {
            return false;
        }
        if (creditos.isEmpty()) {
            return false;
        }
        if (modalidad.isEmpty()) {
            return false;
        }
        if (oportunidades.isEmpty()) {
            return false;
        }
        if (perfil.isEmpty()) {
            return false;
        }
        if (acreditacion.isEmpty()) {
            return false;
        }
        return true;
    }

    private void actualizarPrograma(int id, String codigo, String creditos, String modalidad, String oportunidades, String perfil, String acreditacion, String fechaElab, String fechaMod, String fechaAprob, int idExperiencia, int idAcademia, int idCarrera, int idCampus, String saberTeorico, String saberHeuristico, String saberAxiologico) {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "UPDATE programaEstudio SET idCarrera=?, idCampus=?, idAreaAcademica=?, codigo=?, creditos=?, modalidad=?, oportunidades=?, fechaElaboracion=?, fechaModificacion=?, fechaAprobacion=?, perfil=?, saberTeorico=?, saberHeuristico=?, saberAxiologico=?, acreditacion=? WHERE idProgramaEstudio = " + id;
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setInt(1, idCarrera);
                ps.setInt(2, idCampus);
                ps.setInt(3, idAcademia);
                ps.setString(4, codigo);
                ps.setInt(5, parseInt(creditos));
                ps.setString(6, modalidad);
                ps.setString(7, oportunidades);
                ps.setString(8, fechaElab);
                ps.setString(9, fechaMod);
                ps.setString(10, fechaAprob);
                ps.setString(11, perfil);
                ps.setString(12, saberTeorico);
                ps.setString(13, saberHeuristico);
                ps.setString(14, saberAxiologico);
                ps.setString(15, acreditacion);

                ps.executeUpdate();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta" + ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }

    }

    private void eliminarBibliografias() {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "DELETE FROM programaEstudioBibliografia WHERE idProgramaEstudio = " + idProgramaRegistrado;
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.executeUpdate();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta eliminarBibliografia", "La consulta a la base de datos no es correcta" + ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void eliminarEvaluaciones() {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "DELETE FROM programaEstudioEvaluacion WHERE idProgramaEstudio = " + idProgramaRegistrado;
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.executeUpdate();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta eliminarEvaluacion", "La consulta a la base de datos no es correcta" + ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void registrarBibliografia(String autor, String titulo, String editorial, int anio) {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "INSERT INTO programaEstudioBibliografia (autor, titulo, editorial, anio, idProgramaEstudio) VALUES (?, ?, ?, ?,?)";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setString(1, autor);
                ps.setString(2, titulo);
                ps.setString(3, editorial);
                ps.setInt(4, anio);
                ps.setInt(5, idProgramaRegistrado);

                ps.executeUpdate();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta" + ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void registrarTemas(String evidencia, String criterio, String ambito, int porcentaje) {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "INSERT INTO programaEstudioEvaluacion (evidencia, criterio, ambito, porcentaje, idProgramaEstudio) VALUES (?, ?, ?, ?,?)";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setString(1, evidencia);
                ps.setString(2, criterio);
                ps.setString(3, ambito);
                ps.setInt(4, porcentaje);
                ps.setInt(5, idProgramaRegistrado);

                ps.executeUpdate();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta" + ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    private void concluirActualizacion() {
        alertConexion = Herramientas.constructorDeAlertas("Registro exitoso",
                "Se ha concluido con exito la actualizacion de del programa de estudios!", Alert.AlertType.INFORMATION);
        alertConexion.showAndWait();
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

}
