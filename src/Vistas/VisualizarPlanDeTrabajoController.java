/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.ActividadPlanTrabajoAcademia;
import Pojos.PlanTrabajoAcademia;
import Pojos.TemaPlanTrabajoAcademia;
import Util.Herramientas;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author israz
 */
public class VisualizarPlanDeTrabajoController implements Initializable {

    @FXML
    private TextField txfPeriodo;
    @FXML
    private TextField txfCoordinador;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txfNombrePlan;
    @FXML
    private TextField txfAcademia;
    @FXML
    private TextArea txfObjetivoGeneral;
    
    @FXML
    private TableView<ActividadPlanTrabajoAcademia> tablevActividades;
    @FXML
    private TableColumn tbcActividadesActividad;
    @FXML
    private TableColumn tbcActividadesFecha;
    @FXML
    private TableColumn tbcActividadesOperacion;
    
    @FXML
    private TableView<TemaPlanTrabajoAcademia> tablevTemas;
    @FXML
    private TableColumn tbcTemasExperienciaEducativa;
    @FXML
    private TableColumn tbcTemasParcial;
    @FXML
    private TableColumn tbcTemasTema;
    
    public  Alert alertConexion;
    
    private ObservableList<PlanTrabajoAcademia> planObservable;
    
    private ObservableList<TemaPlanTrabajoAcademia> temas;
    private ObservableList<ActividadPlanTrabajoAcademia> actividades;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planObservable = FXCollections.observableArrayList();
        tbcActividadesOperacion.setResizable(true);

        actividades = FXCollections.observableArrayList();
        this.tbcActividadesActividad.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbcActividadesFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.tbcActividadesOperacion.setCellValueFactory(new PropertyValueFactory("operacion"));
        
        temas = FXCollections.observableArrayList();
        this.tbcTemasTema.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbcTemasParcial.setCellValueFactory(new PropertyValueFactory("parcial"));
        this.tbcTemasExperienciaEducativa.setCellValueFactory(new PropertyValueFactory("nombreExperienciaEducativa"));
        
    }    

    public void pasarPlan(PlanTrabajoAcademia planRecibido){
        planObservable.add(0, planRecibido);
        cargarDatos();
    }

    private void cargarDatos(){
        PlanTrabajoAcademia p = new PlanTrabajoAcademia();
        p = planObservable.get(0);
        txfNombrePlan.setText(p.getNombre());
        txfObjetivoGeneral.setText(p.getObjetivo());
        cargarDato("SELECT nombre FROM periodo WHERE idPeriodo = "+p.getIdPeriodo(), txfPeriodo, "nombre");
        cargarDato("SELECT nombre FROM academia WHERE idAcademia = "+p.getIdAcademia(), txfAcademia, "nombre");
        cargarDato("SELECT nombre FROM academico WHERE idAcademico = "+p.getIdCoordinador(), txfCoordinador, "nombre");
        cargarActividades();
        cargarTemas();
    }
    
    
    private void cargarDato(String SQLConsulta, TextField txfDato, String campoSQL){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = SQLConsulta;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                txfDato.setText(rs.getString(campoSQL)); 
             }
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
    
    private void cargarActividades(){
        PlanTrabajoAcademia p = new PlanTrabajoAcademia();
        p = planObservable.get(0);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM planAcademiaActividad WHERE idPlanAcademia = "+p.getIdPlanTrabajoAcademia();
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                ActividadPlanTrabajoAcademia a = new ActividadPlanTrabajoAcademia();
                a.setNombre(rs.getString("nombre"));
                a.setFecha(rs.getString("fecha"));
                a.setOperacion(rs.getString("operacion"));
                actividades.add(a);
             }
             tablevActividades.setItems(actividades);
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
    
    private void cargarTemas(){
        PlanTrabajoAcademia p = new PlanTrabajoAcademia();
        p = planObservable.get(0);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "SELECT planAcademiaTema.nombre, parcial, experienciaEducativa.nombre "
                     + "FROM planAcademiaTema "
                     + "INNER JOIN experienciaEducativa "
                     + "ON planAcademiaTema.idExperienciaEducativa = experienciaEducativa.idExperienciaEducativa "
                     + "WHERE idPlanAcademia = "+p.getIdPlanTrabajoAcademia();
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                TemaPlanTrabajoAcademia t = new TemaPlanTrabajoAcademia();
                t.setNombre(rs.getString("planAcademiaTema.nombre"));
                t.setParcial(rs.getInt("parcial"));
                t.setNombreExperienciaEducativa(rs.getString("experienciaEducativa.nombre"));
                temas.add(t);
             }
             tablevTemas.setItems(temas);
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
    
    @FXML
    private void clicRegresar(ActionEvent event) {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }

}
