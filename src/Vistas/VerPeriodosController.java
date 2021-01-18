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
    
    private ObservableList<Periodo> periodos;
    @FXML
    private Button btnRegistrarPeriodo;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnEliminarPeriodo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listvPeriodos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        periodos = FXCollections.observableArrayList();
       CargaListaDePeriodos();
    }    
    
    private void CargaListaDePeriodos(){
        periodos.removeAll(periodos);
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
            btnEliminarPeriodo.setDisable(false);
        }else{
            btnEliminarPeriodo.setDisable(true);
        }
    }

    @FXML
    private void clickEliminarPeriodo(ActionEvent event) {
        Periodo periodo = new Periodo();
        periodo = listvPeriodos.getSelectionModel().getSelectedItem();
        int idPeriodo = periodo.getIdPeriodo();
        
        eliminarPeriodo(idPeriodo);
        CargaListaDePeriodos();
        btnEliminarPeriodo.setDisable(true);
    }

    private void eliminarPeriodo(int idPeriodo){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "DELETE FROM periodo WHERE idPeriodo = "+idPeriodo;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.executeUpdate();
             ps.close();
             conn.close();
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta eliminarPeriodo", "La consulta a la base de datos no es correcta"+ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    @FXML
    private void clickSalir(ActionEvent event) {
        try{
                Stage stage = (Stage) btnSalir.getScene().getWindow();
                        Scene sceneAdministrador = new Scene(FXMLLoader.load(getClass().getResource("InicioAdministrador.fxml")));
                        stage.setScene(sceneAdministrador);
                
                stage.show();
            } catch(IOException ex){
                System.out.println("Error al cargar FXML"+ex.getMessage());
            }
        
    }

    @FXML
    private void clickRegistrarPeriodo(ActionEvent event) {
        
        try{
                
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrarPeriodo.fxml"));
            Parent root = loader.load();
            
            
            Scene sceneRegistroPeriodo = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(sceneRegistroPeriodo);
            stage.showAndWait();
            
            CargaListaDePeriodos();
                
            } catch(IOException ex){
                System.out.println("Error al cargar FXML"+ex.getMessage());
            }
        
    }
}
