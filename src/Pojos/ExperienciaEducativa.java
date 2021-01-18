package Pojos;

public class ExperienciaEducativa {
    
    private int idExperienciaEducativa;
    private String nombreExperienciaEducativa;
    private String nrcExperienciaEducativa;
    private int idAcademia;


    public ExperienciaEducativa() {
    }


    public ExperienciaEducativa(int idExperienciaEducativa, String nombreExperienciaEducativa, String nrcExperienciaEducativa, int idAcademia) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
        this.nrcExperienciaEducativa = nrcExperienciaEducativa;
        this.idAcademia = idAcademia;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
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
