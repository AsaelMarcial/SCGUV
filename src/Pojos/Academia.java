
package Pojos;

public class Academia {
    
    private int idAcademia;
    private String nombre;
    private int idCoordinador;
    
    public Academia(){
        
    }
    public Academia(int idAcademia, String nombre, int idCoordinador) {
        this.idAcademia = idAcademia;
        this.nombre = nombre;
        this.idCoordinador = idCoordinador;
    }

    public int getIdAcademia() {
        return idAcademia;
    }

    public void setIdAcademia(int idAcademia) {
        this.idAcademia = idAcademia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
