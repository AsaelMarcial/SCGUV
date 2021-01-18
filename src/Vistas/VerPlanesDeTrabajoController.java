/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.PlanTrabajoAcademia;
import Util.Herramientas;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.text.View;


/**
 * FXML Controller class
 *
 * @author israz
 */
public class VerPlanesDeTrabajoController implements Initializable {

    @FXML
    private ListView<PlanTrabajoAcademia> listvPlanesDeTrabajo;
    @FXML
    private Button btnActualizarPlan;
    @FXML
    private Button btnRegistrarPlan;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnVisualizarPlan;
    @FXML
    private Button btnEliminarPlan;
    
    private ObservableList<PlanTrabajoAcademia> planes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listvPlanesDeTrabajo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       planes = FXCollections.observableArrayList();
       CargaListaDePlanes();
    }    
    
    private void CargaListaDePlanes(){
        planes.removeAll(planes);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM planAcademia";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 
                 PlanTrabajoAcademia p = new PlanTrabajoAcademia();
                 p.setIdPlanTrabajoAcademia(rs.getInt("idPlanAcademia"));
                 p.setNombre(rs.getString("nombre"));
                 p.setObjetivo(rs.getString("objetivo"));
                 p.setIdAcademia(rs.getInt("idAcademia"));
                 p.setIdPeriodo(rs.getInt("idPeriodo"));
                 p.setIdCoordinador(rs.getInt("idCoordinador"));
                 planes.add(p);
             }
             listvPlanesDeTrabajo.setItems(planes);
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
    private void clicElementoLista(MouseEvent event) {
        
        if (listvPlanesDeTrabajo.hasProperties()){
            btnVisualizarPlan.setDisable(false);
            btnActualizarPlan.setDisable(false);
            btnEliminarPlan.setDisable(false);
        }else{
            btnVisualizarPlan.setDisable(true);
            btnActualizarPlan.setDisable(true);
            btnEliminarPlan.setDisable(true);
        }
    }

    @FXML
    private void clicVisualizarPlan(ActionEvent event) {
        PlanTrabajoAcademia plan = new PlanTrabajoAcademia();
        plan = listvPlanesDeTrabajo.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizarPlanDeTrabajo.fxml"));
            Parent root = loader.load();
            
            VisualizarPlanDeTrabajoController controlador = loader.getController();
            controlador.pasarPlan(plan);
           

            Scene sceneOpcion = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(sceneOpcion);
            stage.setResizable(false);
            stage.setTitle("Ver plan de trabajo de academia");
            stage.showAndWait();
            btnVisualizarPlan.setDisable(true);
            btnActualizarPlan.setDisable(true);
            btnEliminarPlan.setDisable(true);
        
        }catch(IOException ex){
            System.out.println("Error al cargar FXML ->  "+ex.getMessage());
        }
        
    }

    @FXML
    private void clicActualizarPlan(ActionEvent event) {
        PlanTrabajoAcademia plan = new PlanTrabajoAcademia();
        plan = listvPlanesDeTrabajo.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ActualizarPlanDeTrabajo.fxml"));
             
            Parent root = loader.load();
            
            ActualizarPlanDeTrabajoController controlador = loader.getController();
            controlador.pasarPlan(plan);
           

            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Actualizar plan de trabajo de academia");
            stage.showAndWait();
            
            CargaListaDePlanes();
            btnVisualizarPlan.setDisable(true);
            btnActualizarPlan.setDisable(true);
            btnEliminarPlan.setDisable(true);
            
        }catch(IOException ex){
            System.out.println("Error al cargar FXML ->  "+ex.getMessage());
        }
    }
    
    
    @FXML
    private void clicEliminarPlan(ActionEvent event) {
        
    }
    
    @FXML
    private void clicRegistrarPlan(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrarPlanDeTrabajo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Registrar plan de trabajo de academia");
            stage.showAndWait();
            CargaListaDePlanes();
            btnVisualizarPlan.setDisable(true);
            btnActualizarPlan.setDisable(true);
            btnEliminarPlan.setDisable(true);
            
        }catch(IOException ex){
            System.out.println("Error al cargar FXML ->  "+ex.getMessage());
        }
        
    }

    @FXML
    private void clicSalir(ActionEvent event) {
    }
    
    
    
}
