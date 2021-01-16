
package Pojos;


public class Avance {

    private int idAvance;
    private int idExperienciaEducativa;
    private int idAcademico;

    public Avance(int idAvance, int idExperienciaEducativa, int idAcademico) {
        this.idAvance = idAvance;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idAcademico = idAcademico;
    }
    
    public Avance(){
        
    }

    public int getIdAvance() {
        return idAvance;
    }

    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public int getIdAcademico() {
        return idAcademico;
    }

    public void setIdAcademico(int idAcademico) {
        this.idAcademico = idAcademico;
    }
    
    
}
