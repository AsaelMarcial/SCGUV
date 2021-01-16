/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author israz
 */
public class ActualizarPlanDeTrabajoController implements Initializable {

    @FXML
    private TextField txfNombrePlan;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextArea txaObjetivoGeneral;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnAgregarActividad;
    @FXML
    private Button btnEliminarActividad;
    @FXML
    private Button btnAgregarTema;
    @FXML
    private Button btnEliminarTema;
    @FXML
    private TextField txfActividad;
    @FXML
    private DatePicker datepFecha;
    @FXML
    private TextField txfOperacion;
    @FXML
    private TextField txfParcial;
    @FXML
    private TextField txfTema;
    
    @FXML
    private ComboBox<?> cmbExperienciaEducativa;
    @FXML
    private ComboBox<?> cmbPeriodo;
    @FXML
    private ComboBox<?> cmbCoordinador;
    @FXML
    private ComboBox<?> cmbAcademia;
    
    @FXML
    private TableView<?> tablevActividades;
    @FXML
    private TableColumn<?, ?> tbcActividadesActividad;
    @FXML
    private TableColumn<?, ?> tbcActividadesFecha;
    @FXML
    private TableColumn<?, ?> tbcActividadesOperacion;
    
    @FXML
    private TableView<?> tablevTemas;
    @FXML
    private TableColumn<?, ?> tbcTemasExperienciaEducativa;
    @FXML
    private TableColumn<?, ?> tbcTemasParcial;
    @FXML
    private TableColumn<?, ?> tbcTemasTema;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicAgregarActividad(ActionEvent event) {
    }

    @FXML
    private void clicEliminarActividad(ActionEvent event) {
    }

    @FXML
    private void clicAgregarTema(ActionEvent event) {
    }

    @FXML
    private void clicEliminarTema(ActionEvent event) {
    }
    
}
