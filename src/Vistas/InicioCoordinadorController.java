
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

/**
 * FXML Controller class
 *
 * @author israz
 */
public class InicioCoordinadorController implements Initializable {

    @FXML
    private Button btnVerAvancesProgramaticos;
    @FXML
    private Button btnVerPlanesDeTrabajo;
    @FXML
    private Button btnVerMinutas;
    @FXML
    private Button btnVerProgramasDeEstudios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clicVerAvancesProgramáticos(ActionEvent event) {
        abrirOpciónCliqueada("VerAvances.fxml");
    }

    @FXML
    private void clicVerPlanesDeTrabajo(ActionEvent event) {
        abrirOpciónCliqueada("VerPlanesDeTrabajo.fxml");
    }

    @FXML
    private void clicVerMinutasDeReunionAcademica(ActionEvent event) {
        abrirOpciónCliqueada("VerMinutas.fxml");
    }

    @FXML
    private void clicVerProgramasDeEstudios(ActionEvent event) throws IOException {
        abrirOpciónCliqueada("VerProgramaDeEstudios.fxml");
    }
    
    private void abrirOpciónCliqueada(String nombreFXML){
        try{
        Stage stage = (Stage) btnVerAvancesProgramaticos.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(nombreFXML)));
        stage.setScene(scene);
        
        }catch(IOException ex){
            System.out.println("Error al cargar FXML"+ex.getMessage() + ex.getCause());

        }
    }
}
