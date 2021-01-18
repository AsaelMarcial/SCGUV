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
public class ProgramaEstudio {
    private int idProgramaEstudio;
    private int idExperienciaEducativa;
    private int idCarrera;
    private int idCampus;
    private int idAreaAcademica;
    private String codigo;
    private int creditos;
    private String modalidad;
    private String oportunidades; 
    private String fechaElaboracion;
    private String fechaModificacion;
    private String fechaAprobacion;
    private String perfil;
    private String saberTeorico;
    private String saberHeuristico;
    private String saberAxiologico; 
    private String acreditacion;

    public ProgramaEstudio() {
    }

    public ProgramaEstudio(int idProgramaEstudio, int idExperienciaEducativa, int idCarrera, int idCampus, int idAreaAcademica, String codigo, int creditos, String modalidad, String oportunidades, String fechaElaboracion, String fechaModificacion, String fechaAprobacion, String perfil, String saberTeorico, String saberHeuristico, String saberAxiologico, String acreditacion) {
        this.idProgramaEstudio = idProgramaEstudio;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idCarrera = idCarrera;
        this.idCampus = idCampus;
        this.idAreaAcademica = idAreaAcademica;
        this.codigo = codigo;
        this.creditos = creditos;
        this.modalidad = modalidad;
        this.oportunidades = oportunidades;
        this.fechaElaboracion = fechaElaboracion;
        this.fechaModificacion = fechaModificacion;
        this.fechaAprobacion = fechaAprobacion;
        this.perfil = perfil;
        this.saberTeorico = saberTeorico;
        this.saberHeuristico = saberHeuristico;
        this.saberAxiologico = saberAxiologico;
        this.acreditacion = acreditacion;
    }

    public int getIdProgramaEstudio() {
        return idProgramaEstudio;
    }

    public void setIdProgramaEstudio(int idProgramaEstudio) {
        this.idProgramaEstudio = idProgramaEstudio;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(int idCampus) {
        this.idCampus = idCampus;
    }

    public int getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(int idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(String oportunidades) {
        this.oportunidades = oportunidades;
    }

    public String getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(String fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(String fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSaberTeorico() {
        return saberTeorico;
    }

    public void setSaberTeorico(String saberTeorico) {
        this.saberTeorico = saberTeorico;
    }

    public String getSaberHeuristico() {
        return saberHeuristico;
    }

    public void setSaberHeuristico(String saberHeuristico) {
        this.saberHeuristico = saberHeuristico;
    }

    public String getSaberAxiologico() {
        return saberAxiologico;
    }

    public void setSaberAxiologico(String saberAxiologico) {
        this.saberAxiologico = saberAxiologico;
    }

    public String getAcreditacion() {
        return acreditacion;
    }

    public void setAcreditacion(String acreditacion) {
        this.acreditacion = acreditacion;
    }

    @Override
    public String toString() {
        return modalidad;
    }
    
    
}
