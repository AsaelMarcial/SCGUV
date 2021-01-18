/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Pojos.ExperienciaEducativa;
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
public class VerExperienciasEducativasController implements Initializable {

    @FXML
    private ListView<ExperienciaEducativa> listvExperienciasEducativas;
    
    private Button btnActualizarExperienciaEducativa;
    
    private ObservableList<ExperienciaEducativa> experienciasEducativas;
    @FXML
    private Button btnRegistrarExperienciaEducativa;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnEliminarExperienciaEducativa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listvExperienciasEducativas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        experienciasEducativas = FXCollections.observableArrayList();
        CargaListaDeExperienciasEducativas();
    }
    
    private void CargaListaDeExperienciasEducativas(){
        experienciasEducativas.removeAll(experienciasEducativas);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM experienciaEducativa";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 
                 ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                 experienciaEducativa.setIdExperienciaEducativa(rs.getInt("idExperienciaEducativa"));
                 experienciaEducativa.setNombreExperienciaEducativa(rs.getString("nombre"));
                 experienciaEducativa.setNrcExperienciaEducativa(rs.getString("nrc"));
                 experienciaEducativa.setIdAcademia(rs.getInt("idAcademia"));
                 experienciasEducativas.add(experienciaEducativa);
             }
             listvExperienciasEducativas.setItems(experienciasEducativas);
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
        
        if (listvExperienciasEducativas.hasProperties()){
            btnEliminarExperienciaEducativa.setDisable(false);
        }else{
            btnEliminarExperienciaEducativa.setDisable(true);
        }
    }

    @FXML
    private void clickRegistrarExperienciaEducativa(ActionEvent event) {
        try{
                
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrarExperienciaEducativa.fxml"));
            Parent root = loader.load();
            
            
            Scene sceneRegistroExperienciaEducativa = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(sceneRegistroExperienciaEducativa);
            stage.showAndWait();
            
            CargaListaDeExperienciasEducativas();
                
            } catch(IOException ex){
                System.out.println("Error al cargar FXML"+ex.getMessage());
            }
    }

    @FXML
    private void clickEliminarExperienciaEducativa(ActionEvent event) {
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa = listvExperienciasEducativas.getSelectionModel().getSelectedItem();
        int idExperienciaEducativa = experienciaEducativa.getIdExperienciaEducativa();
        
        eliminarExperienciaEducativa(idExperienciaEducativa);
        CargaListaDeExperienciasEducativas();
        btnEliminarExperienciaEducativa.setDisable(true);
    }
  
     private void eliminarExperienciaEducativa(int idExperienciaEducativa){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "DELETE FROM experienciaEducativa WHERE idExperienciaEducativa = "+idExperienciaEducativa;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.executeUpdate();
             ps.close();
             conn.close();
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta eliminarExperienciaEducativa", "La consulta a la base de datos no es correcta"+ex.getMessage(), Alert.AlertType.ERROR);
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
}
