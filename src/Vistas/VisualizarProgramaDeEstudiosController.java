/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.BibliografiaProgramaEstudio;
import Pojos.EvaluacionProgramaEstudio;
import Pojos.ProgramaEstudio;
import Util.Herramientas;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asael
 */
public class VisualizarProgramaDeEstudiosController implements Initializable {

    @FXML
    private TextField txtAreaAcademica;
    @FXML
    private TextField txtCarrera;
    @FXML
    private TextField txtCampus;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombreEE;
    @FXML
    private TextField txtModalidad;
    @FXML
    private TextField txtOportunidades;
    @FXML
    private TextField txtCreditos;
    @FXML
    private TextField txtElaboracion;
    @FXML
    private TextField txtModificacion;
    @FXML
    private TextField txtAprobacion;
    @FXML
    private TextArea txtTeoricos;
    @FXML
    private TextArea txtHeuristicos;
    @FXML
    private TextArea txtAxiologicos;
    @FXML
    private TableView<BibliografiaProgramaEstudio> tablevBibliografia;
    @FXML
    private TableColumn tbcAutor;
    @FXML
    private TableColumn tbcTitulo;
    @FXML
    private TableColumn tbcEditorial;
    @FXML
    private TableColumn tbcAnio;
    @FXML
    private TableView<EvaluacionProgramaEstudio> tablevEvaluacion;
    @FXML
    private TableColumn tbcEvidencia;
    @FXML
    private TableColumn tbcCriterio;
    @FXML
    private TableColumn tbcAmbito;
    @FXML
    private TableColumn tbcPorcentaje;
    
    public Alert alertConexion;
    private ObservableList<ProgramaEstudio> obsListPrograma;
    private ObservableList<EvaluacionProgramaEstudio> obsListEvaluacion;
    private ObservableList<BibliografiaProgramaEstudio> obsListBibliografia;
    @FXML
    private Button btCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obsListPrograma = FXCollections.observableArrayList();
        
        obsListEvaluacion = FXCollections.observableArrayList();
        this.tbcEvidencia.setCellValueFactory(new PropertyValueFactory("Evidencia"));
        this.tbcCriterio.setCellValueFactory(new PropertyValueFactory("Criterio"));
        this.tbcAmbito.setCellValueFactory(new PropertyValueFactory("Ambito"));
        this.tbcPorcentaje.setCellValueFactory(new PropertyValueFactory("Porcentaje"));
        
        obsListBibliografia = FXCollections.observableArrayList();
        this.tbcAutor.setCellValueFactory(new PropertyValueFactory("Autor"));
        this.tbcTitulo.setCellValueFactory(new PropertyValueFactory("Titulo"));
        this.tbcEditorial.setCellValueFactory(new PropertyValueFactory("Editorial"));
        this.tbcAnio.setCellValueFactory(new PropertyValueFactory("Anio"));
    }    

    public void pasarPrograma(ProgramaEstudio programaRecibido){
        obsListPrograma.add(0,programaRecibido);
        cargarDatos();
    }
    
    public void cargarDatos(){
        ProgramaEstudio programa = new ProgramaEstudio();
        programa = obsListPrograma.get(0);
        txtCodigo.setText(programa.getCodigo());
        txtCreditos.setText(String.valueOf(programa.getCreditos()));
        txtModalidad.setText(programa.getModalidad());
        txtOportunidades.setText(programa.getOportunidades());
        txtElaboracion.setText(programa.getFechaElaboracion());
        txtModificacion.setText(programa.getFechaModificacion());
        txtAprobacion.setText(programa.getFechaAprobacion());
        txtTeoricos.setText(programa.getSaberTeorico());
        txtHeuristicos.setText(programa.getSaberHeuristico());
        txtAxiologicos.setText(programa.getSaberAxiologico());
        cargarDato("SELECT nombre FROM areaAcademica WHERE idAreaAcademica ="+programa.getIdAreaAcademica(),txtAreaAcademica,"nombre");
        cargarDato("SELECT nombre FROM carrera WHERE idCarrera ="+programa.getIdCarrera(),txtCarrera,"nombre");
        cargarDato("SELECT nombre FROM campus WHERE idCampus ="+programa.getIdCampus(),txtCampus,"nombre");
        cargarDato("SELECT nombre FROM experienciaEducativa WHERE idExperienciaEducativa ="+programa.getIdExperienciaEducativa(),txtNombreEE,"nombre");
        cargarBibliografia();
        cargarEvaluacion();
    }
    
    public void cargarDato(String SQLConsulta, TextField txfDato, String campoSQL){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = SQLConsulta;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                txfDato.setText(rs.getString(campoSQL)); 
             }
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
    
    public void cargarBibliografia(){ 
        ProgramaEstudio programa = new ProgramaEstudio();
        programa = obsListPrograma.get(0);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM programaEstudioBibliografia WHERE idProgramaEstudio = "+programa.getIdProgramaEstudio();
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                BibliografiaProgramaEstudio bibliografia = new BibliografiaProgramaEstudio();
                bibliografia.setAutor(rs.getString("autor"));
                bibliografia.setTitulo(rs.getString("titulo"));
                bibliografia.setEditorial(rs.getString("editorial"));
                bibliografia.setAnio(rs.getInt("anio"));
                obsListBibliografia.add(bibliografia);
             }
             tablevBibliografia.setItems(obsListBibliografia);
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
    
    public void cargarEvaluacion(){
        ProgramaEstudio programa = new ProgramaEstudio();
        programa = obsListPrograma.get(0);
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM programaEstudioEvaluacion WHERE idProgramaEstudio = "+programa.getIdProgramaEstudio();
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                EvaluacionProgramaEstudio eval = new EvaluacionProgramaEstudio();
                eval.setEvidencia(rs.getString("evidencia"));
                eval.setCriterio(rs.getString("criterio"));
                eval.setAmbito(rs.getString("ambito"));
                eval.setPorcentaje(rs.getInt("porcentaje"));
                obsListEvaluacion.add(eval);
             }
             tablevEvaluacion.setItems(obsListEvaluacion);
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
    private void btCerrar(ActionEvent event) {
        Stage stage = (Stage) btCerrar.getScene().getWindow();
        stage.close();
    }
    
}
