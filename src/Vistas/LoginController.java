/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Util.Herramientas;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author israz
 */
public class LoginController implements Initializable {

    @FXML
    private Label lblErrorLogin;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private TextField txfNombreUsuario;
    @FXML
    private TextField txfContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        lblErrorLogin.setText("");
        String usuario = txfNombreUsuario.getText();
        String contraseña = txfContraseña.getText();
        
        if(validarCamposVacios(usuario, contraseña)){
            consultarCredencialesBD(usuario, contraseña);
        }
    }
    
    private boolean validarCamposVacios(String usuario, String contraseña){
        if(usuario.isEmpty()){
            lblErrorLogin.setText("Campo usuario obligatorio");
            return false;
        }
        if(contraseña.isEmpty()){
            lblErrorLogin.setText("Campo contraseña obligatorio");
            return false;
        }
        
        return true;
    }
    
     private void consultarCredencialesBD(String usuario, String contraseña){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if (conn != null){
            try{
                String consulta = "SELECT * FROM usuario WHERE username = ? AND password = ?";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setString(1, usuario);
                ps.setString(2, contraseña);
                ResultSet resultadoBD = ps.executeQuery();
                
                if(resultadoBD.next()){
                    String nombreUsuario = resultadoBD.getString("username");
                    int idRol = resultadoBD.getInt("idRol");
                    
                    alertConexion = Herramientas.constructorDeAlertas(
                            "Inicio de sesión exitoso", 
                            "Bienvenido "+nombreUsuario, 
                            Alert.AlertType.INFORMATION);   
                    alertConexion.showAndWait();
                    IniciarSesion(idRol);
                    
                    
                }else{
                    alertConexion = Herramientas.constructorDeAlertas(
                            "Usuario o contraseña incorrecto", 
                            "Ingrese los datos correctos o registre una nueva cuenta", 
                            Alert.AlertType.ERROR);   
                    alertConexion.showAndWait();
                }
                
                conn.close();
                
            }catch(SQLException ex){
            alertConexion = Herramientas.constructorDeAlertas(
                    "Error de la consulta", 
                    ex.getMessage(), 
                    Alert.AlertType.ERROR);   
            alertConexion.showAndWait();
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas(
                    "Error de conexion", 
                    "No se puede conectar con la base de datos", 
                    Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
        
    }
     
     private void IniciarSesion(int idRol){
         try{
                Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
                switch(idRol){
                    case 1:
                        Scene sceneCoordinador = new Scene(FXMLLoader.load(getClass().getResource("InicioCoordinador.fxml")));
                        stage.setScene(sceneCoordinador);
                        break;
                    case 2:
                        Scene sceneDocente = new Scene(FXMLLoader.load(getClass().getResource("InicioDocente.fxml")));
                        stage.setScene(sceneDocente);
                        break;
                    case 3:
                        Scene sceneAdministrador = new Scene(FXMLLoader.load(getClass().getResource("InicioAdministrador.fxml")));
                        stage.setScene(sceneAdministrador);
                        break;
                }
                
                stage.show();
            } catch(IOException ex){
                System.out.println("Error al cargar FXML"+ex.getMessage());
            }
     }
     
     
    
}