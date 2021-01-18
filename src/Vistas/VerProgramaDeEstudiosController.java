/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.ProgramaEstudio;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asael
 */
public class VerProgramaDeEstudiosController implements Initializable {

    @FXML
    private ListView<ProgramaEstudio> listvProgramaDeEstudios;
    @FXML
    private Button btnVisualizarPrograma;
    @FXML
    private Button btnActualizarPrograma;
    @FXML
    private Button btnEliminarPrograma;
    @FXML
    private Button btnRegistrarPrograma;
    @FXML
    private Button btnSalir;

    private ObservableList<ProgramaEstudio> programas;
    @FXML
    private Button btnActualizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listvProgramaDeEstudios.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        programas = FXCollections.observableArrayList();
        cargarListaDeProgramas();
    }

    private void cargarListaDeProgramas() {
        Connection conn = ConexionBD.iniciarConexionMySQL();
        Alert alertConexion;
        if (conn != null) {
            try {
                String consulta = "SELECT * FROM programaEstudio";
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ProgramaEstudio p = new ProgramaEstudio();
                    p.setIdProgramaEstudio(rs.getInt("idProgramaEstudio"));
                    p.setIdExperienciaEducativa(rs.getInt("idExperienciaEducativa"));
                    p.setIdCarrera(rs.getInt("idCarrera"));
                    p.setIdCampus(rs.getInt("idCampus"));
                    p.setIdAreaAcademica(rs.getInt("idAreaAcademica"));
                    p.setCodigo(rs.getString("codigo"));
                    p.setCreditos(rs.getInt("creditos"));
                    p.setModalidad(rs.getString("modalidad"));
                    p.setOportunidades(rs.getString("oportunidades"));
                    p.setFechaElaboracion(rs.getString("fechaElaboracion"));
                    p.setFechaModificacion(rs.getString("fechaModificacion"));
                    p.setFechaAprobacion(rs.getString("fechaAprobacion"));
                    p.setPerfil(rs.getString("perfil"));
                    p.setSaberTeorico(rs.getString("saberTeorico"));
                    p.setSaberHeuristico(rs.getString("saberHeuristico"));
                    p.setSaberAxiologico(rs.getString("saberAxiologico"));
                    p.setAcreditacion(rs.getString("acreditacion"));
                    programas.add(p);
                }
                listvProgramaDeEstudios.setItems(programas);
                conn.close();
            } catch (SQLException ex) {
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
            }
        } else {
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }

    @FXML
    private void clicElementoLista(MouseEvent event) {
        if (listvProgramaDeEstudios.hasProperties()) {
            btnVisualizarPrograma.setDisable(false);
            btnActualizarPrograma.setDisable(false);
            btnEliminarPrograma.setDisable(false);
        } else {
            btnVisualizarPrograma.setDisable(true);
            btnActualizarPrograma.setDisable(true);
            btnEliminarPrograma.setDisable(true);
        }
    }

    @FXML
    private void clicVisualizarPrograma(ActionEvent event) {
        ProgramaEstudio programa = new ProgramaEstudio();
        programa = listvProgramaDeEstudios.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("visualizarProgramaDeEstudios.fxml"));
            Parent root = loader.load();
            VisualizarProgramaDeEstudiosController controlador = loader.getController();
            controlador.pasarPrograma(programa);

            Scene sceneOpcion = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(sceneOpcion);
            stage.setResizable(false);
            stage.setTitle("Ver programa de estudios");
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println("Error al cargar FXML ->  " + ex.getMessage());
        }

    }

    @FXML
    private void clicActualizarPrograma(ActionEvent event) {
    }

    @FXML
    private void clicEliminarPrograma(ActionEvent event) {
    }

    @FXML
    private void clicRegistrarPrograma(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrarProgramaDeEstudios.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Registrar programa de estudios");
            stage.showAndWait();

        } catch (IOException ex) {
            System.out.println("Error al cargar FXML ->  " + ex.getMessage());
        }
    }

    @FXML
    private void clicSalir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicActualizar(ActionEvent event) {
        programas.clear();
        cargarListaDeProgramas();
    }

}
