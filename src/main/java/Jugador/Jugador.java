package Jugador;

import es.ieslavereda.model.Color;
import es.ieslavereda.model.Tablero;
import es.ieslavereda.model.Tools;

public class Jugador {

    private String nombre;
    private Color color;

    private Tablero tablero;

    public Jugador(String nombre, Tablero t){
        this.nombre = nombre;
        this.color = Tools.randColor();
        tablero=t;
    }
    public Jugador(String nombre, Color color, Tablero t){
        this.nombre= nombre;
        this.color = color;
        tablero=t;
    }

    public Color getColor(){
        return color;
    }
    public String getNombre() {
        return nombre;
    }
}
