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
public class EvaluacionProgramaEstudio {
    private int idProgramaEvaluacion;
    private String evidencia;
    private String criterio;
    private String ambito;
    private int porcentaje;
    private int idProgramaEstudio;

    public EvaluacionProgramaEstudio() {
    }

    public EvaluacionProgramaEstudio(int idProgramaEvaluacion, String evidencia, String criterio, String ambito, int porcentaje, int idProgramaEstudio) {
        this.idProgramaEvaluacion = idProgramaEvaluacion;
        this.evidencia = evidencia;
        this.criterio = criterio;
        this.ambito = ambito;
        this.porcentaje = porcentaje;
        this.idProgramaEstudio = idProgramaEstudio;
    }

    public int getIdProgramaEvaluacion() {
        return idProgramaEvaluacion;
    }

    public void setIdProgramaEvaluacion(int idProgramaEvaluacion) {
        this.idProgramaEvaluacion = idProgramaEvaluacion;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int procentaje) {
        this.porcentaje = procentaje;
    }

    public int getIdProgramaEstudio() {
        return idProgramaEstudio;
    }

    public void setIdProgramaEstudio(int idProgramaEstudio) {
        this.idProgramaEstudio = idProgramaEstudio;
    }

    @Override
    public String toString() {
        return "EvaluacionProgramaEstudio{" + "idProgramaEvaluacion=" + idProgramaEvaluacion + ", idProgramaEstudio=" + idProgramaEstudio + '}';
    }
    
    
}
