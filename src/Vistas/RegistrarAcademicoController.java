/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import BaseDeDatos.ConexionBD;
import Pojos.Academia;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dltun
 */
public class RegistrarAcademicoController implements Initializable {

    @FXML
    private TextField txtNombreAcademico;
    @FXML
    private TextField txtNumeroPersonal;
    @FXML
    private TextField txtCorreoAcademico;
    @FXML
    private Button btnGuardarAcademico;

    @FXML
    private ComboBox<Academia> cbAcademia;
    
    private int idAcademicoRegistrado;
    
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
    private void clickGuardarAcademico(ActionEvent event) {
        boolean isValido = true;
        
        String nombreAcademicoAux = txtNombreAcademico.getText();
        
        String numeroPersonalAux = txtNumeroPersonal.getText();
        
        String correoAux = txtCorreoAcademico.getText();
        
        int posAcademia = cbAcademia.getSelectionModel().getSelectedIndex();
        
        if(nombreAcademicoAux.isEmpty()){
            isValido = false;
        }
        
        if(numeroPersonalAux.isEmpty()){
            isValido = false;
        }
        
        if(correoAux.isEmpty()){
            isValido = false;
        }
        
        if(posAcademia < 0){
            isValido = false;
        }
        
        if(isValido){
            guardaAcademico(nombreAcademicoAux, numeroPersonalAux, correoAux, academias.get(posAcademia).getIdAcademia());
        } else{
            Alert alertConexion = Herramientas.constructorDeAlertas("Campos obligatorios", "Por favor llene los campos para continuar", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    private void guardaAcademico(String nombreAcademico, String numeroPersonal, String correoElectronico, int idAcademia){
        
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        
        if(conn != null){
            try{
             String consulta = "INSERT INTO academico (nombre, numeroPersonal, correo) VALUES (?, ?, ?)";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.setString(1, nombreAcademico);
             ps.setString(2, numeroPersonal);
             ps.setString(3, correoElectronico);
             int resultado = ps.executeUpdate();
             
             if(resultado > 0){
                 
                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
             
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idAcademicoRegistrado = (int) generatedKeys.getLong(1);
                        guardaAcademiaAcademico(idAcademicoRegistrado, idAcademia);
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
             
             ps.close();
                 
                alertConexion = Herramientas.constructorDeAlertas("Registrado", "Academico registrado con éxito", Alert.AlertType.INFORMATION);
                alertConexion.showAndWait();
                
                Stage stage = (Stage) txtNombreAcademico.getScene().getWindow();
                stage.close();
                
             }else{
                alertConexion = Herramientas.constructorDeAlertas("Error en el registro", "El academico no pudo ser guardado en el sistema", Alert.AlertType.ERROR);
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
    
    private void guardaAcademiaAcademico(int idAcademico, int idAcademia){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        
        if(conn != null){
            try{
             String consulta = "INSERT INTO academiaAcademico (idAcademia, idAcademico, idAcademia) VALUES (?, ?)";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.setInt(1, idAcademia);
             ps.setInt(2, idAcademico);
             int resultado = ps.executeUpdate();
             
             if(resultado > 0){
                alertConexion = Herramientas.constructorDeAlertas("Registrado", "", Alert.AlertType.INFORMATION);
                alertConexion.showAndWait();
                
             }else{
                alertConexion = Herramientas.constructorDeAlertas("Error en el registro", "", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
             }
             
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
}
