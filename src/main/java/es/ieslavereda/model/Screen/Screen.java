package es.ieslavereda.model.Screen;

import es.ieslavereda.model.GAME.Coordenada;
import es.ieslavereda.model.GAME.Mensajes;
import es.ieslavereda.model.GAME.Partida;
import es.ieslavereda.model.GAME.Tablero;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Screen {

    public static void tableroReverse(Partida partida, Tablero t) {
        Coordenada c;
        String output = "   Turno de: " + partida.jugadorPorColor(partida.getTurno()) +
                " -> " + colorize(partida.getTurno().toString().toUpperCase(), partida.getTurno().getColor())  + "\n";
        output += "   H  G  F  E  D  C  B  A\n";
        for (int i = 7; i >= 0; i--) {
            output += (i + 1) + " ";
            for (int j = 7; j >= 0; j--) {
                c = new Coordenada((char) ('A' + j), i + 1);
                output += t.getCellsMap().get(c);
            }
            output += " " + (i + 1) + "\n";
        }
        output += "   H  G  F  E  D  C  B  A\n";
        String str = t.getManager().toString();
        System.out.println(output + str);
    }

    public static void tableroDefault(Partida partida, Tablero t) {
        Coordenada c;
        String output = "   Turno de: " + partida.jugadorPorColor(partida.getTurno()) +
                " -> " + colorize(partida.getTurno().toString().toUpperCase(), partida.getTurno().getColor()) + "\n";
        output += "   A  B  C  D  E  F  G  H\n";
        for (int i = 0; i < 8; i++) {
            output += (i + 1) + " ";
            for (int j = 0; j < 8; j++) {
                c = new Coordenada((char) ('A' + j), i + 1);
                output += t.getCellsMap().get(c);
            }
            output += " " + (i + 1) + "\n";
        }
        output += "   A  B  C  D  E  F  G  H\n";
        String str = t.getManager().toString();
        System.out.println(output + str);
    }

    public static void showBienvenida(){
        Scanner sc = new Scanner(System.in);
        System.out.println(Mensajes.bienvenida());
        sc.nextLine();
    }
}
