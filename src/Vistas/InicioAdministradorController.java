package Vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioAdministradorController implements Initializable {

    @FXML
    private Button btnVerAcademicos;
    @FXML
    private Button btnVerExperienciasEducativas;
    @FXML
    private Button btnVerPeriodos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void clicVerAcademicos(ActionEvent event) {
        try{
                
            Stage stage = (Stage) btnVerAcademicos.getScene().getWindow();            
            Scene sceneAdministrador = new Scene(FXMLLoader.load(getClass().getResource("VerAcademicos.fxml")));
            stage.setScene(sceneAdministrador);  
            stage.show();
            } catch(IOException ex){
                System.out.println("Error al cargar FXML"+ex.getMessage());
            }
    }

    @FXML
    private void clicVerExperienciasEducativas(ActionEvent event) {
        try{
                
            Stage stage = (Stage) btnVerExperienciasEducativas.getScene().getWindow();            
            Scene sceneAdministrador = new Scene(FXMLLoader.load(getClass().getResource("VerExperienciasEducativas.fxml")));
            stage.setScene(sceneAdministrador);  
            stage.show();
            } catch(IOException ex){
                System.out.println("Error al cargar FXML"+ex.getMessage());
            }
    }

    @FXML
    private void clicVerPeriodos(ActionEvent event) {
        try{
                
            Stage stage = (Stage) btnVerPeriodos.getScene().getWindow();            
            Scene sceneAdministrador = new Scene(FXMLLoader.load(getClass().getResource("VerPeriodos.fxml")));
            stage.setScene(sceneAdministrador);  
            stage.show();
            } catch(IOException ex){
                System.out.println("Error al cargar FXML"+ex.getMessage());
            }
    }
}
