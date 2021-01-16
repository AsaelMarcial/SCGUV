package Util;

import javafx.scene.control.Alert;

public class Herramientas {
    
    public static Alert constructorDeAlertas(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(mensaje);
        
        return alert;
    }
}
