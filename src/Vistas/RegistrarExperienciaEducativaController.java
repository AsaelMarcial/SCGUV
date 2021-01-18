/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Pojos.Academia;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import BaseDeDatos.ConexionBD;
import Util.Herramientas;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author dltun
 */
public class RegistrarExperienciaEducativaController implements Initializable {

    @FXML
    private TextField txtNombreExperienciaEducativa;
    @FXML
    private TextField txtNRC;
    @FXML
    private Button btnGuardarExperienciaEducativa;
    @FXML
    private ComboBox<Academia> cbAcademia;
    
    private ObservableList<Academia> academias;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        academias = FXCollections.observableArrayList();
        
        CargaListaDeAcademias();
    }
    
    private void CargaListaDeAcademias(){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM academia";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 
                 Academia academia = new Academia();
                 academia.setIdAcademia(rs.getInt("idAcademia"));
                 academia.setNombre(rs.getString("nombre"));
                 academia.setIdCoordinador(rs.getInt("idCoordinador"));
                 academias.add(academia);
             }
             cbAcademia.setItems(academias);
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
    private void clickGuardarExperienciaEducativa() {
        
        boolean isValido = true;
        
        String nombreExperienciaEducativaAux = txtNombreExperienciaEducativa.getText();
        
        String nrcAUX = txtNRC.getText();
        
        int posAcademia = cbAcademia.getSelectionModel().getSelectedIndex();
        
        if(nombreExperienciaEducativaAux.isEmpty()){
            isValido = false;
        }
        
        if(nrcAUX.isEmpty()){
            isValido = false;
        }
        
        if(posAcademia < 0){
            isValido = false;
        }
        
        if(isValido){
            guardaExperienciaEducativa(nombreExperienciaEducativaAux, nrcAUX, academias.get(posAcademia).getIdAcademia());
        } else{
            Alert alertConexion = Herramientas.constructorDeAlertas("Campos obligatorios", "Por favor llene los campos para continuar", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    public void guardaExperienciaEducativa(String nombreExperienciaEducativa, String nrc, int idAcademia){
        
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        
        if(conn != null){
            try{
             String consulta = "INSERT INTO experienciaEducativa (nombre, nrc, idAcademia) VALUES (?, ?, ?)";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.setString(1, nombreExperienciaEducativa);
             ps.setString(2, nrc);
             ps.setInt(3, idAcademia);
             int resultado = ps.executeUpdate();
             
             if(resultado > 0){
                alertConexion = Herramientas.constructorDeAlertas("Registrado", "Experiencia educativa registrada con éxito", Alert.AlertType.INFORMATION);
                alertConexion.showAndWait();
                
                Stage stage = (Stage) txtNombreExperienciaEducativa.getScene().getWindow();
                stage.close();
                
             }else{
                alertConexion = Herramientas.constructorDeAlertas("Error en el registro", "La Experiencia Educativa no pudo ser guardada en el sistema", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
             }
             
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
        
    }
}
