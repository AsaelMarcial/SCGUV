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
import Util.Herramientas;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asael
 */
public class RegistrarProgramaDeEstudiosController implements Initializable {

    private int idProgramaEstudio;
    @FXML
    private TextField txtCodigo;
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
    private DatePicker dateElaboracion;
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

    public Alert alertConexion;
    @FXML
    private Button btnBorrarBibliografia;
    @FXML
    private Button btnBorrarEval;
    @FXML
    private Button btnRegistrar;
    @FXML
    private TextField txtAcreditacion;
    @FXML
    private TextArea txtPerfil;
    @FXML
    private Button btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateElaboracion.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate hoy = LocalDate.now();
                setDisable(empty || date.compareTo(hoy) < 0);
            }
        });
        dateModificacion.setEditable(false);

        dateElaboracion.setDayCellFactory(picker -> new DateCell() {
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

        cargarComboBoxAcademia();
        cargarComboBoxCarrera();
        cargarComboBoxCampus();
        cargarComboBoxExperiencia();


    }

    private void cargarComboBoxAcademia() {
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
                }
                combAcademia.setItems(obsListAcademia);
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
                }
                combCarrera.setItems(obsListCarrera);
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
                }
                combCampus.setItems(obsListCampus);
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
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM experienciaEducativa";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ExperienciaEducativa e = new ExperienciaEducativa();
                    e.setIdExperienciaEduactiva(rs.getInt("idExperienciaEducativa"));
                    e.setNombreExperienciaEducativa(rs.getString("nombre"));
                    e.setNrcExperienciaEducativa(rs.getString("nrc"));
                    e.setIdAcademia(rs.getInt("idAcademia"));
                    obsListEE.add(e);
                }
                combExperiencia.setItems(obsListEE);
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
    private void clicRegistrar(ActionEvent event) {
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
            if (!obsListBibliografia.isEmpty() && !obsListEval.isEmpty()
                    && dateElaboracion.getValue() != null && dateModificacion.getValue() != null
                    && dateAprobacion.getValue() != null) {

                LocalDate idFechaElab = dateElaboracion.getValue();
                LocalDate idFechaMod = dateModificacion.getValue();
                LocalDate idFechaAprob = dateAprobacion.getValue();

                String fechaElab = idFechaElab.toString();
                String fechaMod = idFechaMod.toString();
                String fechaAprob = idFechaAprob.toString();

                ExperienciaEducativa exp = new ExperienciaEducativa();
                exp = combExperiencia.getSelectionModel().getSelectedItem();
                int idExperiencia = exp.getIdExperienciaEduactiva();

                Academia academia = new Academia();
                academia = combAcademia.getSelectionModel().getSelectedItem();
                int idAcademia = academia.getIdAcademia();

                Carrera carrera = new Carrera();
                carrera = combCarrera.getSelectionModel().getSelectedItem();
                int idCarrera = carrera.getIdCarrera();

                Campus campus = new Campus();
                campus = combCampus.getSelectionModel().getSelectedItem();
                int idCampus = campus.getIdCampus();

                registrarPrograma(codigo, creditos, modalidad, oportunidades, perfil, acreditacion, fechaElab, fechaMod, fechaAprob, idExperiencia, idAcademia, idCarrera, idCampus, saberTeorico, saberHeuristico, saberAxiologico);

                ObservableList<BibliografiaProgramaEstudio> iterarBibliografia = obsListBibliografia;
                for (BibliografiaProgramaEstudio bibliografiaIndice : iterarBibliografia) {

                    registrarBibliografia(bibliografiaIndice.getAutor(), bibliografiaIndice.getTitulo(), bibliografiaIndice.getEditorial(), bibliografiaIndice.getAnio());
                }

                ObservableList<EvaluacionProgramaEstudio> iterarEvaluacion = obsListEval;
                for (EvaluacionProgramaEstudio evalIndice : iterarEvaluacion) {

                    registrarEvaluaciones(evalIndice.getEvidencia(), evalIndice.getCriterio(), evalIndice.getAmbito(), evalIndice.getPorcentaje());
                }

                concluirRegistro();

                return;
            } else {
                alertConexion = Herramientas.constructorDeAlertas("Tablas vacías",
                        "Debe agregar Actividades y Temas al Plan de curso antes de registrarlo", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Campos incorrectos",
                    "Existen campos faltantes o son incorrectos para registrar el plan de curso", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
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

    private void registrarPrograma(String codigo, String creditos, String modalidad, String oportunidades, String perfil, String acreditacion, String fechaElab, String fechaMod, String fechaAprob, int idExperiencia, int idAcademia, int idCarrera, int idCampus, String saberTeorico, String saberHeuristico, String saberAxiologico) {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "INSERT INTO programaEstudio (idExperienciaEducativa, idCarrera, idCampus, idAreaAcademica, codigo, creditos, modalidad, oportunidades, fechaElaboracion, fechaModificacion, fechaAprobacion, perfil, saberTeorico, saberHeuristico, saberAxiologico, acreditacion) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, idExperiencia);
                ps.setInt(2, idCarrera);
                ps.setInt(3, idCampus);
                ps.setInt(4, idAcademia);
                ps.setString(5, codigo);
                ps.setInt(6, parseInt(creditos));
                ps.setString(7, modalidad);
                ps.setString(8, oportunidades);
                ps.setString(9, fechaElab);
                ps.setString(10, fechaMod);
                ps.setString(11, fechaAprob);
                ps.setString(12, perfil);
                ps.setString(13, saberTeorico);
                ps.setString(14, saberHeuristico);
                ps.setString(15, saberAxiologico);
                ps.setString(16, acreditacion);

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idProgramaEstudio = (int) generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

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
                ps.setInt(5,idProgramaEstudio);

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

    private void registrarEvaluaciones(String evidencia, String criterio, String ambito, int porcentaje) {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if (conn != null) {
            try {
                String consulta = "INSERT INTO programaEstudioEvaluacion (evidencia, criterio, ambito, porcentaje, idProgramaEstudio) VALUES (?, ?, ?, ?,?)";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setString(1, evidencia);
                ps.setString(2, criterio);
                ps.setString(3, ambito);
                ps.setInt(4, porcentaje);
                ps.setInt(5,idProgramaEstudio);

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

    private void concluirRegistro() {
        alertConexion = Herramientas.constructorDeAlertas("Registro exitoso",
                "Se ha concluido con el registro del plan de trabajo de academia", Alert.AlertType.INFORMATION);
        alertConexion.showAndWait();
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btCancelar(ActionEvent event) {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}
