package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class canciones implements Serializable {
    private ArrayList<cancion> listaDistribucion;

    public canciones() {
        listaDistribucion = new ArrayList<>();
        listaDistribucion.add(new cancion("Nothing compares to you", "Sinead O connor"));
        listaDistribucion.add(new cancion("Still Loving You", "Scorpions"));
        listaDistribucion.add(new cancion("The Scientist", "Coldplay"));
        listaDistribucion.add(new cancion("A Sky Full Of Stars", "Coldplay"));
        listaDistribucion.add(new cancion("Scorpions Lady", "Scorpions"));
        listaDistribucion.add(new cancion("Nothing else matters", "Metallica"));
        listaDistribucion.add(new cancion("Me paro cuando suena", "Orquesta de las nubes"));
        listaDistribucion.add(new cancion("Bohemian Rhapsody", "Queen"));
        listaDistribucion.add(new cancion("Imagine", "John Lennon"));
        listaDistribucion.add(new cancion("Another One Bites the Dust", "Queen"));
    }

    public ArrayList<cancion> getListaDistribucion() { return listaDistribucion; }

    public cancion cancionAzar() {
        int i = (int) (Math.random() * listaDistribucion.size());
        return listaDistribucion.get(i);
    }

    public ArrayList<cancion> getCancionesGrupo(String grupo) {
        ArrayList<cancion> canciones = new ArrayList<>();
        for (cancion c : listaDistribucion) {
            if (c.getGrupo().equalsIgnoreCase(grupo)) {
                canciones.add(c);
            }
        }
        return canciones;
    }

    public ArrayList<cancion> getCancionesTitulo(String titulo) {
        ArrayList<cancion> canciones = new ArrayList<>();
        for (cancion c : listaDistribucion) {
            if (c.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                canciones.add(c);
            }
        }
        return canciones;
    }
}
