/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author asael
 */
public class AcademicoProgramaEstudio {
    private int idProgramaEstudio;
    private int idAcademico;

    public AcademicoProgramaEstudio() {
    }

    public AcademicoProgramaEstudio(int idProgramaEstudio, int idAcademico) {
        this.idProgramaEstudio = idProgramaEstudio;
        this.idAcademico = idAcademico;
    }

    public int getIdProgramaEstudio() {
        return idProgramaEstudio;
    }

    public void setIdProgramaEstudio(int idProgramaEstudio) {
        this.idProgramaEstudio = idProgramaEstudio;
    }

    public int getIdAcademico() {
        return idAcademico;
    }

    public void setIdAcademico(int idAcademico) {
        this.idAcademico = idAcademico;
    }

    @Override
    public String toString() {
        return "AcademicoProgramaEstudio{" + "idProgramaEstudio=" + idProgramaEstudio + ", idAcademico=" + idAcademico + '}';
    }
    
    
}
