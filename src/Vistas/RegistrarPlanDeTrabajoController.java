package Vistas;

import BaseDeDatos.ConexionBD;
import Pojos.Academia;
import Pojos.Academico;
import Pojos.ActividadPlanTrabajoAcademia;
import Pojos.ExperienciaEducativa;
import Pojos.Periodo;
import Pojos.PlanTrabajoAcademia;
import Pojos.TemaPlanTrabajoAcademia;
import Util.Herramientas;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TextField txfTema;
    @FXML
    private TextField txfCoordinador;
    
    @FXML
    private TableView<ActividadPlanTrabajoAcademia> tablevActividades;
    ObservableList<ActividadPlanTrabajoAcademia> actividades;
    
    @FXML
    private TableColumn tbcActividadesActividad;
    @FXML
    private TableColumn tbcActividadesFecha;
    @FXML
    private TableColumn tbcActividadesOperacion;
    
    @FXML
    private TableView<TemaPlanTrabajoAcademia> tablevTemas;
    ObservableList<TemaPlanTrabajoAcademia> temas;
    
    @FXML
    private TableColumn tbcTemasExperienciaEducativa;
    @FXML
    private TableColumn tbcTemasParcial;
    @FXML
    private TableColumn tbcTemasTema;
    
    @FXML
    private ComboBox<String> cmbParcial;
    ObservableList<String> parciales;
    
    @FXML
    private ComboBox<Periodo> cmbPeriodo;
    ObservableList<Periodo> periodos;
    
    @FXML
    private ComboBox<Academia> cmbAcademia;
    ObservableList<Academia> academias;
    
    @FXML
    private ComboBox<ExperienciaEducativa> cmbExperienciaEducativa;
    ObservableList<ExperienciaEducativa> experienciasEducativas;
    
    ObservableList<Academico> coordinadores;
    private int idPlanRegistrado;
    
    Alert alertConexion;
    @FXML
    private Button btnRegistrar;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        datepFecha.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate hoy = LocalDate.now();
            setDisable(empty || date.compareTo(hoy) < 0 );
            }
        });
        datepFecha.setEditable(false);
        
        
        parciales = FXCollections.observableArrayList();
        periodos = FXCollections.observableArrayList();
        academias = FXCollections.observableArrayList();
        coordinadores = FXCollections.observableArrayList();
        experienciasEducativas = FXCollections.observableArrayList();
        
        actividades = FXCollections.observableArrayList();
        this.tbcActividadesActividad.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbcActividadesFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.tbcActividadesOperacion.setCellValueFactory(new PropertyValueFactory("operacion"));
        
        temas = FXCollections.observableArrayList();
        this.tbcTemasTema.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbcTemasParcial.setCellValueFactory(new PropertyValueFactory("parcial"));
        this.tbcTemasExperienciaEducativa.setCellValueFactory(new PropertyValueFactory("nombreExperienciaEducativa"));
        
        cargarComboBoxPeriodo();
        cargarComboBoxAcademia();
        
        parciales.add("Primer parcial");
        parciales.add("Segundo parcial");
        cmbParcial.setItems(parciales);
        
        
        cmbAcademia.valueProperty().addListener(new ChangeListener<Academia>(){
            @Override
            public void changed(ObservableValue<? extends Academia> observable, Academia oldValue, Academia newValue) {
                txfCoordinador.setText("");
                if(newValue != null){
                cargarCoordinador(newValue.getIdCoordinador());
                cargarComboBoxExperienciaEducativa(newValue.getIdAcademia());
                cmbExperienciaEducativa.setDisable(false);
                }
            }
        });
        
        cmbExperienciaEducativa.valueProperty().addListener(new ChangeListener<ExperienciaEducativa>(){
            @Override
            public void changed(ObservableValue<? extends ExperienciaEducativa> observable, ExperienciaEducativa oldValue, ExperienciaEducativa newValue) {
                if(newValue != null){
                    btnAgregarTema.setDisable(false);
                }
            }
        });
        
        cmbParcial.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnAgregarTema.setDisable(false);
            }
            
        });
    }
    
    private void cargarComboBoxPeriodo(){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM periodo";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Periodo p = new Periodo();
                 p.setIdPeriodo(rs.getInt("idPeriodo"));
                 p.setNombre(rs.getString("nombre"));
                 periodos.add(p);
             }
             cmbPeriodo.setItems(periodos);
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
    
    private void cargarComboBoxAcademia(){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM academia";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Academia a = new Academia();
                 a.setIdAcademia(rs.getInt("idAcademia"));
                 a.setNombre(rs.getString("nombre"));
                 a.setIdCoordinador(rs.getInt("idCoordinador"));
                 academias.add(a);
             }
             cmbAcademia.setItems(academias);
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
    
    private void cargarComboBoxExperienciaEducativa(int idAcademia){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        experienciasEducativas.clear();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM experienciaEducativa WHERE idAcademia = "+idAcademia;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 ExperienciaEducativa e = new ExperienciaEducativa();
                 e.setIdExperienciaEduactiva(rs.getInt("idExperienciaEducativa"));
                 e.setNombreExperienciaEducativa(rs.getString("nombre"));
                 e.setNrcExperienciaEducativa(rs.getString("nrc"));
                 e.setIdAcademia(idAcademia);
                 experienciasEducativas.add(e);
             }
             cmbExperienciaEducativa.setItems(experienciasEducativas);
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
    
    private void cargarCoordinador(int idCoordinador){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "SELECT * FROM academico WHERE idAcademico = "+idCoordinador;
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 txfCoordinador.setText(rs.getString("nombre"));         
             }
            conn.close();
            
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta: "+ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    
    
    @FXML
    private void clicAgregarActividad(ActionEvent event) {
        ActividadPlanTrabajoAcademia actividad = new ActividadPlanTrabajoAcademia();
        String nombre = txfActividad.getText();
        String operacion = txfOperacion.getText();
        
        if(datepFecha.getValue() != null){
            LocalDate ldFecha = datepFecha.getValue();

            String fecha = ldFecha.toString();
            if( !(nombre.isEmpty() || operacion.isEmpty()) ){
                actividad.setNombre(nombre);
                actividad.setFecha(fecha);
                actividad.setOperacion(operacion);
        
                actividades.add(actividad);
                tablevActividades.setItems(actividades);
                btnAgregarActividad.setDisable(true);
                return;
            }
        }
            alertConexion = Herramientas.constructorDeAlertas("Campos incorrectos", 
                    "Existen campos faltantes o son incorrectos para agregar un nuevo tema a la tabla", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
            
    }
    
    
    @FXML
    private void clicAgregarTema(ActionEvent event) {
        TemaPlanTrabajoAcademia tema = new TemaPlanTrabajoAcademia();
        ExperienciaEducativa ee = new ExperienciaEducativa();
        ee = cmbExperienciaEducativa.getSelectionModel().getSelectedItem();
        String nombre = txfTema.getText();
        if(ee != null){
            String parcialSeleccionado = cmbParcial.getSelectionModel().getSelectedItem();
            if(!(parcialSeleccionado.isEmpty())){
                if(!nombre.isEmpty()){
                    if(parcialSeleccionado == "Primer parcial")
                        tema.setParcial(1);
                    else
                        tema.setParcial(2);
                    tema.setNombre(nombre);
                    tema.setIdExperienciaEducativa(ee.getIdExperienciaEduactiva());
                    tema.setNombreExperienciaEducativa(ee.getNombreExperienciaEducativa());
                    temas.add(tema);
                    tablevTemas.setItems(temas);
                    btnAgregarTema.setDisable(true);
                    return;
                }
                
            }
        }
        alertConexion = Herramientas.constructorDeAlertas("Campos incorrectos", 
                    "Existen campos faltantes o son incorrectos para agregar una nueva actividad a la tabla", Alert.AlertType.ERROR);
        alertConexion.showAndWait();
        }
    
    @FXML
    private void clicEliminarActividad(ActionEvent event) {
        actividades.remove(tablevActividades.getSelectionModel().getSelectedItem());
        tablevActividades.setItems(actividades);
        btnEliminarTema.setDisable(true);
    }

    @FXML
    private void clicEliminarTema(ActionEvent event) {
        temas.remove(tablevTemas.getSelectionModel().getSelectedItem());
        tablevTemas.setItems(temas);
        btnEliminarTema.setDisable(true);
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
        String nombre = txfNombrePlan.getText();
        String objetivo = txaObjetivoGeneral.getText();
       
       if(validarSeleccionElementosPlan() && validarCamposPlan(nombre, objetivo)){
           if(!actividades.isEmpty() && !temas.isEmpty()){
               
               Academia academia = new Academia();
                academia = cmbAcademia.getSelectionModel().getSelectedItem();
                int idAcademia = academia.getIdAcademia();
                int idCoordinador = academia.getIdCoordinador();
           
                Periodo periodo = new Periodo();
                periodo = cmbPeriodo.getSelectionModel().getSelectedItem();
                int idPeriodo = periodo.getIdPeriodo();
           
                
                registrarPlan(nombre, objetivo, idAcademia, idCoordinador, idPeriodo);

                ObservableList<ActividadPlanTrabajoAcademia> iterarActividades = actividades;
                for (ActividadPlanTrabajoAcademia actividadIndice : iterarActividades){

                    registrarActividades(actividadIndice.getNombre(), actividadIndice.getFecha(), actividadIndice.getOperacion());
                }
                
                ObservableList<TemaPlanTrabajoAcademia> iterarTemas = temas;
                for (TemaPlanTrabajoAcademia temaIndice : iterarTemas){

                    registrarTemas(temaIndice.getNombre(), temaIndice.getParcial(), temaIndice.getIdExperienciaEducativa());
                }
                
                concluirRegistro();
                
                return;
           }else{
                alertConexion = Herramientas.constructorDeAlertas("Tablas vacías", 
                    "Debe agregar Actividades y Temas al Plan de curso antes de registrarlo", Alert.AlertType.ERROR);
                alertConexion.showAndWait();
           } 
       }else{
            alertConexion = Herramientas.constructorDeAlertas("Campos incorrectos", 
                    "Existen campos faltantes o son incorrectos para registrar el plan de curso", Alert.AlertType.ERROR);
             alertConexion.showAndWait();
       }
           
    }
    
    private boolean validarSeleccionElementosPlan(){
        if(cmbPeriodo.getSelectionModel().getSelectedItem() == null)
            return false;
        
        if(cmbAcademia.getSelectionModel().getSelectedItem() == null)
            return false;

        return true;
    }
    
    private boolean validarCamposPlan(String nombre, String objetivo){
       if(nombre.isEmpty())
           return false;
       
       if(objetivo.isEmpty())
           return false;
        
        
        return true;
    }
    
    private void registrarPlan(String nombre, String objetivo, int idAcademia, int idPeriodo, int idCoordinador){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "INSERT INTO planAcademia (nombre, objetivo, idAcademia, idPeriodo, idCoordinador) VALUES (?, ?, ?, ?, ?)";
             PreparedStatement ps = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
             ps.setString(1, nombre);
             ps.setString(2, objetivo);
             ps.setInt(3, idAcademia);
             ps.setInt(4, idPeriodo);
             ps.setInt(5, idCoordinador);
             
             int affectedRows = ps.executeUpdate();
             if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
             }
             
             try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                idPlanRegistrado = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
             }
             
             ps.close();

             conn.close();
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta"+ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
        
    }
    
    private void registrarActividades(String nombre, String fecha, String operacion){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "INSERT INTO planAcademiaActividad (nombre, fecha, operacion, idPlanAcademia) VALUES (?, ?, ?, ?)";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.setString(1, nombre);
             ps.setString(2, fecha);
             ps.setString(3, operacion);
             ps.setInt(4, idPlanRegistrado);
             
             ps.executeUpdate();
             ps.close();
             conn.close();
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta"+ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    private void registrarTemas(String nombre, int parcial, int idExperienciaEducativa){
        Connection conn = ConexionBD.iniciarConexionMySQL();
        if(conn != null){
            try{
             String consulta = "INSERT INTO planAcademiaTema(nombre, parcial, idExperienciaEducativa, idPlanAcademia) VALUES (?, ?, ?, ?)";
             PreparedStatement ps = conn.prepareStatement(consulta);
             ps.setString(1, nombre);
             ps.setInt(2, parcial);
             ps.setInt(3, idExperienciaEducativa);
             ps.setInt(4, idPlanRegistrado);
             
             ps.executeUpdate();
             ps.close();
             conn.close();
            }catch(SQLException ex){
                alertConexion = Herramientas.constructorDeAlertas("Error de consulta", "La consulta a la base de datos no es correcta"+ex.getMessage(), Alert.AlertType.ERROR);
                alertConexion.showAndWait();   
            }
        }else{
            alertConexion = Herramientas.constructorDeAlertas("Error de conexion", "No se puede conectar a la base de datos", Alert.AlertType.ERROR);
            alertConexion.showAndWait();
        }
    }
    
    private void concluirRegistro(){
        alertConexion = Herramientas.constructorDeAlertas("Registro exitoso", 
                    "Se ha concluido con el registro del plan de trabajo de academia", Alert.AlertType.INFORMATION);
        alertConexion.showAndWait();
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicElementoActividades(MouseEvent event) {
        if (tablevActividades.hasProperties()){
            btnEliminarActividad.setDisable(false);
        }else{
            btnEliminarActividad.setDisable(true);
        }
    }

    @FXML
    private void clicElementoTemas(MouseEvent event) {
        if (tablevTemas.hasProperties()){
            btnEliminarTema.setDisable(false);
        }else{
            btnEliminarTema.setDisable(true);
        }
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void seleccionaFecha(Event event) {
        btnAgregarActividad.setDisable(false);
    }

    @FXML
    private void activarBotonActividad(KeyEvent event) {
        btnAgregarActividad.setDisable(false);
    }

    private void activarBotonActividad(Event event) {
        btnAgregarActividad.setDisable(false);
    }

    @FXML
    private void activarBotonTema(Event event) {
        btnAgregarTema.setDisable(false);
    }

    
}
