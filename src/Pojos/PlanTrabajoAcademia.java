
package Pojos;

/**
 *
 * @author israz
 */
public class PlanTrabajoAcademia{
    
    private int idPlanTrabajoAcademia;
    private String nombre;
    private String objetivo;
    private int idAcademia;
    private int idPeriodo;
    private int idCoordinador;

    public PlanTrabajoAcademia() {
    }

    public PlanTrabajoAcademia(int idPlanTrabajoAcademia, String nombre, String objetivo, int idAcademia, int idPeriodo, int idCoordinador) {
        this.idPlanTrabajoAcademia = idPlanTrabajoAcademia;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.idAcademia = idAcademia;
        this.idPeriodo = idPeriodo;
        this.idCoordinador = idCoordinador;
    }

    public int getIdPlanTrabajoAcademia() {
        return idPlanTrabajoAcademia;
    }

    public void setIdPlanTrabajoAcademia(int idPlanT) {
        this.idPlanTrabajoAcademia = idPlanT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public int getIdAcademia() {
        return idAcademia;
    }

    public void setIdAcademia(int idAcademia) {
        this.idAcademia = idAcademia;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
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
