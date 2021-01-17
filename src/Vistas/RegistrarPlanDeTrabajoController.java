package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.Academia;
import Pojos.Academico;
import Pojos.ExperienciaEducativa;
import Pojos.Periodo;
import Util.Herramientas;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private TextField txfParcial;
    @FXML
    private TextField txfTema;
    @FXML
    private TextField txfCoordinador;
    
    @FXML
    private TableView<?> tablevActividades;
    @FXML
    private TableColumn tbcActividadesActividad;
    @FXML
    private TableColumn tbcActividadesFecha;
    @FXML
    private TableColumn tbcActividadesOperacion;
    
    @FXML
    private TableView<?> tablevTemas;
    @FXML
    private TableColumn tbcTemasExperienciaEducativa;
    @FXML
    private TableColumn tbcTemasParcial;
    @FXML
    private TableColumn tbcTemasTema;
    
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
        periodos = FXCollections.observableArrayList();
        academias = FXCollections.observableArrayList();
        coordinadores = FXCollections.observableArrayList();
        experienciasEducativas = FXCollections.observableArrayList();
        
        cargarComboBoxPeriodo();
        cargarComboBoxAcademia();
        
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
    }

    @FXML
    private void clicEliminarActividad(ActionEvent event) {
    }

    @FXML
    private void clicAgregarTema(ActionEvent event) {
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
    
}
