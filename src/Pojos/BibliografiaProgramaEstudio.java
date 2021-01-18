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
public class BibliografiaProgramaEstudio {
    private int idProgramaBibliografia;
    private String autor;
    private String titulo;
    private String editorial;
    private int anio;
    private int idProgramaEstudio;

    public BibliografiaProgramaEstudio() {
    }

    public BibliografiaProgramaEstudio(int idProgramaBibliografia, String autor, String titulo, String editorial, int anio, int idProgramaEstudio) {
        this.idProgramaBibliografia = idProgramaBibliografia;
        this.autor = autor;
        this.titulo = titulo;
        this.editorial = editorial;
        this.anio = anio;
        this.idProgramaEstudio = idProgramaEstudio;
    }

    public int getIdProgramaBibliografia() {
        return idProgramaBibliografia;
    }

    public void setIdProgramaBibliografia(int idProgramaBibliografia) {
        this.idProgramaBibliografia = idProgramaBibliografia;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getIdProgramaEstudio() {
        return idProgramaEstudio;
    }

    public void setIdProgramaEstudio(int idProgramaEstudio) {
        this.idProgramaEstudio = idProgramaEstudio;
    }

    @Override
    public String toString() {
        return "BibliografiaProgramaEstudio{" + "titulo=" + titulo + '}';
    }
    
    
}
