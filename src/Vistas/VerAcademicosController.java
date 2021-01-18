/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Pojos.Academico;
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
public class VerAcademicosController implements Initializable {

    @FXML
    private ListView<Academico> listvAcademicos;
    
    @FXML
    private Button btnActualizarAcademico;
    
    private ObservableList<Academico> academicos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listvAcademicos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        academicos = FXCollections.observableArrayList();
       CargaListaDeAcademicos();
    }
    
    private void CargaListaDeAcademicos(){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if(conn != null){
            try{
             String consulta = "SELECT * FROM academico";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 
                 Academico academico = new Academico();
                 academico.setIdAcademico(rs.getInt("idAcademico"));
                 academico.setNombre(rs.getString("nombre"));
                 academico.setNumeroPersonal(rs.getString("numeroPersonal"));
                 academico.setCorreo(rs.getString("correo"));
                 academicos.add(academico);
             }
             listvAcademicos.setItems(academicos);
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
        
        if (listvAcademicos.hasProperties()){
            btnActualizarAcademico.setDisable(false);
        }else{
            btnActualizarAcademico.setDisable(true);
        }
    }
  
}
