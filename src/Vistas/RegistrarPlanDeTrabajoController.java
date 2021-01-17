package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.Academia;
import Pojos.Academico;
import Pojos.ActividadPlanTrabajoAcademia;
import Pojos.ExperienciaEducativa;
import Pojos.Periodo;
import Pojos.TemaPlanTrabajoAcademia;
import Util.Herramientas;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

public class RegistrarPlanDeTrabajoController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAgregarActividad;
    @FXML
    private Button btnEliminarActividad;
    @FXML
    private Button btnAgregarTema;
    @FXML
    private Button btnEliminarTema;
    @FXML
    private Button btnRegistrar;
    
    @FXML
    private TextField txfNombrePlan;
    @FXML
    private TextArea txaObjetivoGeneral;
    @FXML
    private TextField txfActividad;
    @FXML
    private DatePicker datepFecha;
    @FXML
    private TextField txfOperacion;
    @FXML
    private TextField txfTema;
    @FXML
    private TextField txfCoordinador;
    
    @FXML
    private TableView<ActividadPlanTrabajoAcademia> tablevActividades;
    ObservableList<ActividadPlanTrabajoAcademia> actividades;
    
    @FXML
    private TableColumn tbcActividadesActividad;
    @FXML
    private TableColumn tbcActividadesFecha;
    @FXML
    private TableColumn tbcActividadesOperacion;
    
    @FXML
    private TableView<TemaPlanTrabajoAcademia> tablevTemas;
    ObservableList<TemaPlanTrabajoAcademia> temas;
    
    @FXML
    private TableColumn tbcTemasExperienciaEducativa;
    @FXML
    private TableColumn tbcTemasParcial;
    @FXML
    private TableColumn tbcTemasTema;
    
    @FXML
    private ComboBox<String> cmbParcial;
    ObservableList<String> parciales;
    
    @FXML
    private ComboBox<Periodo> cmbPeriodo;
    ObservableList<Periodo> periodos;
    
    @FXML
    private ComboBox<Academia> cmbAcademia;
    ObservableList<Academia> academias;
    
    @FXML
    private ComboBox<ExperienciaEducativa> cmbExperienciaEducativa;
    ObservableList<ExperienciaEducativa> experienciasEducativas;
    
    ObservableList<Academico> coordinadores;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        datepFecha.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate hoy = LocalDate.now();
            setDisable(empty || date.compareTo(hoy) < 0 );
        }
    });
        datepFecha.setEditable(false);
        
        parciales = FXCollections.observableArrayList();
        periodos = FXCollections.observableArrayList();
        academias = FXCollections.observableArrayList();
        coordinadores = FXCollections.observableArrayList();
        experienciasEducativas = FXCollections.observableArrayList();
        
        actividades = FXCollections.observableArrayList();
        this.tbcActividadesActividad.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbcActividadesFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.tbcActividadesOperacion.setCellValueFactory(new PropertyValueFactory("operacion"));
        
        temas = FXCollections.observableArrayList();
        this.tbcTemasTema.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbcTemasParcial.setCellValueFactory(new PropertyValueFactory("parcial"));
        this.tbcTemasExperienciaEducativa.setCellValueFactory(new PropertyValueFactory("nombreExperienciaEducativa"));
        
        cargarComboBoxPeriodo();
        cargarComboBoxAcademia();
        
        parciales.add("Primer parcial");
        parciales.add("Segundo parcial");
        cmbParcial.setItems(parciales);
        
        cmbAcademia.valueProperty().addListener(new ChangeListener<Academia>(){
            @Override
            public void changed(ObservableValue<? extends Academia> observable, Academia oldValue, Academia newValue) {
                txfCoordinador.setText("");
                if(newValue != null){
                cargarCoordinador(newValue.getIdCoordinador());
                cargarComboBoxExperienciaEducativa(newValue.getIdAcademia());
                }
            }
        });
        
        cmbExperienciaEducativa.valueProperty().addListener(new ChangeListener<ExperienciaEducativa>(){
            @Override
            public void changed(ObservableValue<? extends ExperienciaEducativa> observable, ExperienciaEducativa oldValue, ExperienciaEducativa newValue) {
                if(newValue != null){
                    btnAgregarTema.setDisable(false);
                }
            }
        });
        
        cmbParcial.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnAgregarTema.setDisable(false);
            }
            
        });
    }
    
    private void cargarComboBoxPeriodo(){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM periodo";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Periodo p = new Periodo();
                 p.setIdPeriodo(rs.getInt("idPeriodo"));
                 p.setNombre(rs.getString("nombre"));
                 periodos.add(p);
             }
             cmbPeriodo.setItems(periodos);
             conn.close();
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    private void cargarComboBoxAcademia(){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM academia";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Academia a = new Academia();
                 a.setIdAcademia(rs.getInt("idAcademia"));
                 a.setNombre(rs.getString("nombre"));
                 a.setIdCoordinador(rs.getInt("idCoordinador"));
                 academias.add(a);
             }
             cmbAcademia.setItems(academias);
             conn.close();
             
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    private void cargarComboBoxExperienciaEducativa(int idAcademia){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        experienciasEducativas.clear();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM experienciaEducativa WHERE idAcademia = "+idAcademia;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 ExperienciaEducativa e = new ExperienciaEducativa();
                 e.setIdExperienciaEduactiva(rs.getInt("idExperienciaEducativa"));
                 e.setNombreExperienciaEducativa(rs.getString("nombre"));
                 e.setNrcExperienciaEducativa(rs.getString("nrc"));
                 e.setIdAcademia(idAcademia);
                 experienciasEducativas.add(e);
             }
             cmbExperienciaEducativa.setItems(experienciasEducativas);
             conn.close();
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    private void cargarCoordinador(int idCoordinador){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM academico WHERE idAcademico = "+idCoordinador;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 txfCoordinador.setText(rs.getString("nombre"));         
             }
            conn.close();
            
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta: "+ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    @FXML
    private void clicAgregarActividad(ActionEvent event) {
        ActividadPlanTrabajoAcademia actividad = new ActividadPlanTrabajoAcademia();
        LocalDate fecha = datepFecha.getValue();
        
        actividad.setNombre(txfActividad.getText());
        actividad.setFecha(fecha.toString());
        actividad.setOperacion(txfOperacion.getText());
        
        actividades.add(actividad);
        tablevActividades.setItems(actividades);
        btnAgregarActividad.setDisable(true);
    }
    
    @FXML
    private void clicAgregarTema(ActionEvent event) {
        TemaPlanTrabajoAcademia tema = new TemaPlanTrabajoAcademia();
        ExperienciaEducativa ee = new ExperienciaEducativa();
        ee = cmbExperienciaEducativa.getSelectionModel().getSelectedItem();
        
        String parcialSeleccionado = cmbParcial.getSelectionModel().getSelectedItem();
        if(parcialSeleccionado == "Primer parcial"){
             tema.setParcial(1);
        }else{
             tema.setParcial(2);
        }
        tema.setNombre(txfTema.getText());
        tema.setIdExperienciaEducativa(ee.getIdExperienciaEduactiva());
        tema.setNombreExperienciaEducativa(ee.getNombreExperienciaEducativa());
        temas.add(tema);
        tablevTemas.setItems(temas);
        btnAgregarTema.setDisable(true);
    }
    

    @FXML
    private void escrituraCampoOperacion(InputMethodEvent event) {
    }

    @FXML
    private void clicEliminarActividad(ActionEvent event) {
    }

    

    @FXML
    private void clicEliminarTema(ActionEvent event) {
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
    }

    @FXML
    private void escrituraCampoAcitividad(KeyEvent event) {
         btnAgregarActividad.setDisable(false);
    }

    
    
}
