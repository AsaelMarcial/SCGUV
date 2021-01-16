package Pojos;

public class ExperienciaEducativa {
    
    private int idExperienciaEduactiva;
    private String nombreExperienciaEducativa;
    private String nrcExperienciaEducativa;
    private int idAcademia;

    public ExperienciaEducativa(int idExperienciaEduactiva, String nombreExperienciaEducativa, String nrcExperienciaEducativa, int idAcademia) {
        this.idExperienciaEduactiva = idExperienciaEduactiva;
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
        this.nrcExperienciaEducativa = nrcExperienciaEducativa;
        this.idAcademia = idAcademia;
    }

    public ExperienciaEducativa() {
    }

    public int getIdExperienciaEduactiva() {
        return idExperienciaEduactiva;
    }

    public void setIdExperienciaEduactiva(int idExperienciaEduactiva) {
        this.idExperienciaEduactiva = idExperienciaEduactiva;
    }

    public String getNombreExperienciaEducativa() {
        return nombreExperienciaEducativa;
    }

    public void setNombreExperienciaEducativa(String nombreExperienciaEducativa) {
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
    }

    public String getNrcExperienciaEducativa() {
        return nrcExperienciaEducativa;
    }

    public void setNrcExperienciaEducativa(String nrcExperienciaEducativa) {
        this.nrcExperienciaEducativa = nrcExperienciaEducativa;
    }

    public int getIdAcademia() {
        return idAcademia;
    }

    public void setIdAcademia(int idAcademia) {
        this.idAcademia = idAcademia;
    }

    @Override
    public String toString() {
        return nombreExperienciaEducativa;
    }
    
    
}
