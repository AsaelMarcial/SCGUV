/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.Academia;
import Pojos.Academico;
import Pojos.ActividadPlanTrabajoAcademia;
import Pojos.ExperienciaEducativa;
import Pojos.Periodo;
import Pojos.PlanTrabajoAcademia;
import Pojos.TemaPlanTrabajoAcademia;
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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author israz
 */
public class ActualizarPlanDeTrabajoController implements Initializable {

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
    private Button btnActualizar;
    
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
    ObservableList<PlanTrabajoAcademia> planObservable;
    
    private int idPlanRegistrado;
    
    Alert alertConexion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planObservable = FXCollections.observableArrayList();
        parciales = FXCollections.observableArrayList();
        periodos = FXCollections.observableArrayList();
        academias = FXCollections.observableArrayList();
        coordinadores = FXCollections.observableArrayList();
        experienciasEducativas = FXCollections.observableArrayList();
        
        cmbAcademia.valueProperty().addListener(new ChangeListener<Academia>(){
            @Override
            public void changed(ObservableValue<? extends Academia> observable, Academia oldValue, Academia newValue) {
                txfCoordinador.setText("");
                if(newValue != null){
                cargarCoordinador(newValue.getIdCoordinador());
                cargarComboBoxExperienciaEducativa(newValue.getIdAcademia());
                cmbExperienciaEducativa.setDisable(false);
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
    }
    
    public void pasarPlan(PlanTrabajoAcademia planRecibido){
        planObservable.add(0, planRecibido);
        cargarComboBoxPeriodo();
        cargarComboBoxAcademia();
        cargarComboBoxExperienciaEducativa(-1);
        cargarCoordinador(-1);
        
    }
    
   private void cargarComboBoxPeriodo(){
       
       //Obtiene ID del periodo en el PlanDeCurso seleccionado y recibido
        PlanTrabajoAcademia planRecibido = new PlanTrabajoAcademia();
        planRecibido = planObservable.get(0);
        int idPeriodoPlanRecibido = planRecibido.getIdPeriodo();
        Periodo periodoRecibido = new Periodo();
        
        Connection conn = ConexionBD.iniciarConexionMySQL();
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
                 
                 //Encuentra en la consulta el periodo recibido 
                 if(rs.getInt("idPeriodo") == idPeriodoPlanRecibido){
                     periodoRecibido.setIdPeriodo(idPeriodoPlanRecibido);
                     periodoRecibido.setNombre(rs.getString("nombre"));
                 }
             }
             cmbPeriodo.setItems(periodos);
             
             //Muestra el periodo recibido en el combo-box del FXML
             cmbPeriodo.valueProperty().setValue(periodoRecibido);
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
       
        //Obtiene ID de la academia en el PlanDeCurso seleccionado y recibido
        PlanTrabajoAcademia planRecibido = new PlanTrabajoAcademia();
        planRecibido = planObservable.get(0);
        int idAcademiaPlanRecibido = planRecibido.getIdAcademia();
        Academia academiaRecibida = new Academia();
        
        Connection conn = ConexionBD.iniciarConexionMySQL();
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
                 
                 if(rs.getInt("idAcademia") == idAcademiaPlanRecibido){
                     academiaRecibida.setIdAcademia(idAcademiaPlanRecibido);
                     academiaRecibida.setNombre(rs.getString("nombre"));
                 }
             }
             
             cmbAcademia.setItems(academias);
             cmbAcademia.valueProperty().setValue(academiaRecibida);
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
       
       PlanTrabajoAcademia planRecibido = new PlanTrabajoAcademia();
        planRecibido = planObservable.get(0);
        
        //Si proviene pasarPlan(), sino proviene de listener de cambio en combobox academia.
        if(idAcademia == -1){
           idAcademia = planRecibido.getIdAcademia();
       }
        
        Connection conn = ConexionBD.iniciarConexionMySQL();
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
       
        PlanTrabajoAcademia planRecibido = new PlanTrabajoAcademia();
        planRecibido = planObservable.get(0);
        
        //Si proviene pasarPlan(), sino proviene de listener de cambio en combobox academia.
        if(idCoordinador == -1){
          idCoordinador = planRecibido.getIdCoordinador();
        }
       
        Connection conn = ConexionBD.iniciarConexionMySQL();
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
   
   private void mostrarPlanDeCurso(){
       PlanTrabajoAcademia p = new PlanTrabajoAcademia();
       p = planObservable.get(0);
       txfNombrePlan.setText(p.getNombre());
       txaObjetivoGeneral.setText(p.getObjetivo());
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
    private void clicElementoActividades(MouseEvent event) {
    }

    @FXML
    private void clicElementoTemas(MouseEvent event) {
    }

    @FXML
    private void activarBotonActividad(KeyEvent event) {
    }

    @FXML
    private void seleccionaFecha(Event event) {
    }

    @FXML
    private void activarBotonTema(KeyEvent event) {
    }

    @FXML
    private void clicActualizar(ActionEvent event) {
    }
    
}
