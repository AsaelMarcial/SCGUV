package Pojos;

public class TemaPlanTrabajoAcademia {
    
    private int idTema;
    private String nombre;
    private int parcial;
    private int idExperienciaEducativa;
    private String nombreExperienciaEducativa;
    private int idPlanAcademia;

    public TemaPlanTrabajoAcademia() {
    }

    public TemaPlanTrabajoAcademia(int idTema, String nombre, int parcial, int idExperienciaEducativa, String nombreExperienciEducativa, int idPlanAcademia) {
        this.idTema = idTema;
        this.nombre = nombre;
        this.parcial = parcial;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
        this.idPlanAcademia = idPlanAcademia;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getParcial() {
        return parcial;
    }

    public void setParcial(int parcial) {
        this.parcial = parcial;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public String getNombreExperienciaEducativa() {
        return nombreExperienciaEducativa;
    }

    public void setNombreExperienciaEducativa(String nombreExperienciaEducativa) {
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
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
