package es.ieslavereda;

import es.ieslavereda.model.GAME.Partida;
import es.ieslavereda.model.Screen.Screen;

public class Main {
    public static void main(String[] args) {
        Screen.showBienvenida();
        Partida p;
        while ((p=Partida.medMenu())!=null) {
            if (p.getListaJugadores().isEmpty())
                System.out.println(p.inicializarPartida());
            p.jugar();
            System.out.println(p.resultadoPartida());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("¡Hasta pronto!");
        System.exit(0);
    }
}