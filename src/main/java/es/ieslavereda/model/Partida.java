package es.ieslavereda.model;

import Jugador.Jugador;
import es.ieslavereda.model.TAD.ListaCoord;

public class Partida {

    private String nombre;
    private Jugador[] listaJugadores;
    private Tablero tablero;

    public Partida(String nombre) {
        tablero = new Tablero(this);
        this.nombre = nombre;
        listaJugadores = new Jugador[2];
        listaJugadores[0] = new Jugador(Tools.getText("Introduce el nombre de jugador 1."), tablero);
        listaJugadores[1] =
                new Jugador(Tools.getText("Introduce el nombre de jugador 2."), listaJugadores[0].getColor().next(), tablero);
    }

    public Tablero getTablero() {
        return tablero;
    }
    public Jugador next(Jugador j) {
        if (listaJugadores[0].getNombre().equals(j.getNombre())){
            return listaJugadores[1];
        }else{
            return listaJugadores[0];
        }
    }

    public Jugador devolverQuienJuegaConBlancas() {
        if (listaJugadores[0].getColor() == Color.WHITE) {
            return listaJugadores[0];
        } else {
            return listaJugadores[1];
        }
    }

    public void jugar(){
        Tablero t = getTablero();
        boolean mate;
        boolean tablas;

        Jugador j = devolverQuienJuegaConBlancas();

        System.out.println(j.getNombre() + " juega con blancas.");
        System.out.println(next(j).getNombre() + " juega con negras.");

        do {
            turnoUser(j, j.getColor());
            j = next(j);
            mate = getTablero().estaMateColor(j.getColor());
            tablas = getTablero().hayTablasColor(j.getColor());
        }while (!mate && !tablas);

        if (mate) {
            if (j.getColor() == Color.BLACK) {
                System.out.println("Ganan blancas por jaque mate.");
            } else {
                System.out.println("Ganan negras por jaque mate.");
            }
        }else{
            System.out.println("Empate por tablas");
        }
    }

    public void turnoUser(Jugador jugador, Color color) {
        Tablero t = getTablero();
        Coordenada coordenada;
        Pieza piezaAux;
        boolean piezaSeleccionadaNoPuedeMoverse;
        boolean movimientosPiezaContieneCoordenada;
        ListaCoord complexMoves;
        System.out.println(t.direccionTablero(color));
        do {
            do {
                coordenada = Tools.selectPieza("Turno de " + jugador.getNombre(), t, color);
                piezaAux = t.getCelda(coordenada).getPiece();
                complexMoves = piezaAux.getComplexMoves();
                piezaSeleccionadaNoPuedeMoverse = complexMoves.isEmpty();
                if (piezaSeleccionadaNoPuedeMoverse) System.out.println("Esa pieza no la puedes mover en este momento.");
            } while (piezaSeleccionadaNoPuedeMoverse);

            t.resaltarTablero(complexMoves);
            System.out.println(t.direccionTablero(color));
            coordenada = Tools.selectMove("¿A dónde quieres mover?.", t);
            movimientosPiezaContieneCoordenada = complexMoves.contiene(coordenada);

            if (!movimientosPiezaContieneCoordenada) {
                System.out.println(piezaAux.decirNombre() + " no puede moverse a esa casilla");
                t.resetColors();
            }
        } while (!movimientosPiezaContieneCoordenada);

        piezaAux.moveTo(coordenada);
        if (t.estaJaqueColor(jugador.getColor().next())){
            t.dameElRey(jugador.getColor().next());
        }
        System.out.println(t.direccionTablero(color));
    }
}
