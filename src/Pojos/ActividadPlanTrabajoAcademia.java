package Pojos;

public class ActividadPlanTrabajoAcademia {
    
    private int idActividad;
    private String nombre;
    private String operacion;
    private String fecha;
    private int idPlanAcademia;

    public ActividadPlanTrabajoAcademia() {
    }

    public ActividadPlanTrabajoAcademia(int idActividad, String nombre, String operacion, String fecha, int idPlanAcademia) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.operacion = operacion;
        this.fecha = fecha;
        this.idPlanAcademia = idPlanAcademia;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdPlanAcademia() {
        return idPlanAcademia;
    }

    public void setIdPlanAcademia(int idPlanAcademia) {
        this.idPlanAcademia = idPlanAcademia;
    }

    

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
