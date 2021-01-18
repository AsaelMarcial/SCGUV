/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Util.Herramientas;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dltun
 */
public class RegistrarPeriodoController implements Initializable {

    @FXML
    private Button btnGuardarPeriodo;
    @FXML
    private TextField txtFechaInicio;
    @FXML
    private TextField txtNombrePeriodo;
    @FXML
    private TextField txtFechaFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickGuardarPeriodo(ActionEvent event) {
        boolean isValido = true;
        
        String nombrePeriodoAux = txtNombrePeriodo.getText();
        
        String fechaInicioAux = txtFechaInicio.getText();
        
        String fechaFinAux = txtFechaFin.getText();
        
        if(nombrePeriodoAux.isEmpty()){
            isValido = false;
        }
        
        if(fechaInicioAux.isEmpty()){
            isValido = false;
        }
        
        if(fechaFinAux.isEmpty()){
            isValido = false;
        }
        
        if(isValido){
            guardaExperienciaEducativa(nombrePeriodoAux, fechaInicioAux, fechaFinAux);
        } else{
            Alert alertConexion = Herramientas.constructorDeAlertas("Campos obligatorios", "Por favor llene los campos para continuar", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    private void guardaExperienciaEducativa(String nombrePeriodo, String fechaInicio, String fechaFin){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        
        if(conn != null){
            try{
             String consulta = "INSERT INTO periodo (nombre, fechaInicio, fechaFin) VALUES (?, ?, ?)";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.setString(1, nombrePeriodo);
             ps.setString(2, fechaInicio);
             ps.setString(3, fechaFin);
             int resultado = ps.executeUpdate();
             
             if(resultado > 0){
                alertConexion = Herramientas.constructorDeAlertas("Registrado", "Periodo registrado con éxito", Alert.AlertType.INFORMATION);
                alertConexion.showAndWait();
                
                Stage stage = (Stage) txtNombrePeriodo.getScene().getWindow();
                stage.close();
                
             }else{
                alertConexion = Herramientas.constructorDeAlertas("Error en el registro", "El periodo no pudo ser guardado en el sistema", Alert.AlertType.ERROR);
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
