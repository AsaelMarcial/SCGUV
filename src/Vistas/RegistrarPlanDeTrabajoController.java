package Vistas;

import Pojos.Academia;
import Pojos.Academico;
import Pojos.ExperienciaEducativa;
import Pojos.Periodo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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

public class RegistrarPlanDeTrabajoController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAgregarActividad;
    @FXML
    private Button btnEliminarActividad;
    @FXML
    private Button btnAgregarTema;
    @FXML
    private Button btnEliminarTema;
    @FXML
    private Button btnRegistrar;
    
    @FXML
    private TextField txfNombrePlan;
    @FXML
    private TextArea txaObjetivoGeneral;
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
    
    @FXML
    private ComboBox<Periodo> cmbPeriodo;
    @FXML
    private ComboBox<Academia> cmbAcademia;
    @FXML
    private ComboBox<Academico> cmbCoordinador;
    @FXML
    private ComboBox<?> cmbExperienciaEducativa;
    
    ObservableList<Periodo> periodos;
    ObservableList<Academico> coordinadores;
    ObservableList<Academia> academias;
    ObservableList<ExperienciaEducativa> experienciasEducativas;

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

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
    }
    
}
