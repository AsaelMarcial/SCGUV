/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Pojos.Periodo;
import BaseDeDatos.ConexionBD;
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
 * @author dltun
 */
public class VerPeriodosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ListView<Periodo> listvPeriodos;
    
    @FXML
    private Button btnActualizarPeriodo;
    
    private ObservableList<Periodo> periodos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listvPeriodos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        periodos = FXCollections.observableArrayList();
       CargaListaDePeriodos();
    }    
    
    private void CargaListaDePeriodos(){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM periodo";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 
                 Periodo periodo = new Periodo();
                 periodo.setIdPeriodo(rs.getInt("idPeriodo"));
                 periodo.setNombre(rs.getString("nombre"));
                 periodo.setFechaInicio(rs.getString("fechaInicio"));
                 periodo.setFechaFin(rs.getString("fechaFin"));
                 periodos.add(periodo);
             }
             listvPeriodos.setItems(periodos);
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
        
        if (listvPeriodos.hasProperties()){
            btnActualizarPeriodo.setDisable(false);
        }else{
            btnActualizarPeriodo.setDisable(true);
        }
    }
    
    /*
    @FXML
    private void clicActualizarPeriodo(ActionEvent event) {
        Periodo periodo = new Periodo();
        periodo = listvPeriodos.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ActualizarPeriodo.fxml"));
             
            Parent root = loader.load();
            
            VisualizarPlanDeTrabajoController controlador = loader.getController();
            controlador.pasarPlan(periodo);
           

            Scene sceneOpcion = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(sceneOpcion);
            stage.setResizable(false);
            stage.setTitle("Actualizar periodo");
            stage.showAndWait();
        
        }catch(IOException ex){
            System.out.println("Error al cargar FXML ->  "+ex.getMessage());
        }
    }
    */
}
