package es.ieslavereda.model.Jugador;

import es.ieslavereda.model.Timer.Timer;
import es.ieslavereda.model.GAME.Color;
import es.ieslavereda.model.GAME.Tablero;

import java.io.Serializable;
import java.util.Comparator;

public class Jugador implements Serializable {

    public static Comparator<Jugador> SORT_BY_COLOR = Comparator.comparing(o -> o.getColor().toString());
    private String nombre;
    private Color color;
    private Timer reloj;
    private Tablero tablero;

    public Jugador(String nombre, Color color, Tablero t, Timer r){
        this.nombre= nombre;
        this.color = color;
        tablero=t;
        reloj = r;
    }

    public Timer getReloj() {
        return reloj;
    }

    public Color getColor(){
        return color;
    }
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
