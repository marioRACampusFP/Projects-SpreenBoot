package modelo;

import java.io.Serializable;

public class cancion implements Serializable {
    private String titulo;
    private String grupo;

    public cancion(String titulo, String grupo) {
        this.titulo = titulo;
        this.grupo = grupo;
    }

    public String getTitulo() { return titulo; }
    public String getGrupo() { return grupo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    @Override
    public String toString() {
        return titulo + " - " + grupo;
    }
}
