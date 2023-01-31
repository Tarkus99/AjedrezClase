package es.ieslavereda.model;

import es.ieslavereda.model.TAD.ListaCoord;
import es.ieslavereda.model.TAD.ListaPieza;
import es.ieslavereda.model.TAD.NodoPieza;

import java.util.Scanner;

public class Tools {
    public static ListaCoord allMovements(ListaPieza vectorPiezas) {  //Devuelve todas las posiciones donde puedan moverse las piezas enemigas sin repetición
        Coordenada nodoCoordAux;
        ListaCoord listaMovesCadaPieza;
        ListaCoord allMovements = new ListaCoord();
        NodoPieza nodoPiezaAux = vectorPiezas.getCabeza();
        while (nodoPiezaAux != null) {
            listaMovesCadaPieza = nodoPiezaAux.getPieza().getNextKills();
            while ((nodoCoordAux = listaMovesCadaPieza.remove(0)) != null) {
                allMovements.add(nodoCoordAux);
            }
            nodoPiezaAux = nodoPiezaAux.getNext();
        }
        return allMovements;
    }

    public static String getText(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.println(text);
        return sc.nextLine();
    }

    /*public static void seleccionarPiezaTablero(Tablero t, String text) {
        char col;
        int row;
        Coordenada coordenada;
        Pieza p;
        do {
            do {
                System.out.println(t);
                String str = getText(text);
                while (!Character.isLetter(str.charAt(0)) || !Character.isDigit(str.charAt(1))) {
                    str = getText("No existe esa casilla. Prueba otra vez. \n" + text);
                }
                col = str.charAt(0);
                row = Character.getNumericValue(str.charAt(1));
                coordenada = new Coordenada(col, row);
            } while (t.getCelda(coordenada) == null || t.getCelda(coordenada).isEmpty());

            p = t.getCelda(coordenada).getPiece();
        } while (p.getComplexMoves().isEmpty());
        t.resaltarTablero(p.getComplexMoves());

        do {
            String str = getText("¿A dónde quieres moverla?");
            while (!Character.isLetter(str.charAt(0)) || !Character.isDigit(str.charAt(1))) {
                str = getText("No existe esa casilla. Prueba otra vez" + "¿A dónde quieres moverla?");
            }
            col = str.charAt(0);
            row = Character.getNumericValue(str.charAt(1));
            coordenada = new Coordenada(col, row);
        } while (!p.getComplexMoves().contiene(coordenada));

        Movimientos.moveTo(p, coordenada);
    }*/

    public static Coordenada selectPieza(String text, Tablero t, Color color) {
        Coordenada c;
       do {
           do {
               c = entradaUser(text);
           } while (t.getCelda(c) == null || t.getCelda(c).isEmpty());
       }while (t.getCelda(c).getPiece().getColor()!=color);
        return c;
    }

    public static Coordenada selectMove(String text, Tablero t) {
        Coordenada c;
        do {
            c = entradaUser(text);
        } while (t.getCelda(c) == null);
        return c;
    }

    public static Coordenada entradaUser(String text){
        char col;
        int row;
        String str = Tools.getText(text);
        while (str.length()!=2 || !Character.isLetter(str.charAt(0)) || !Character.isDigit(str.charAt(1))) {
            str = Tools.getText("Formato no válido. Prueba otra vez. \n" + text);
        }
        col = str.charAt(0);
        row = Character.getNumericValue(str.charAt(1));
        return new Coordenada(col, row);
    }

    public static Color randColor(){
        int rand;
        rand = (int)(Math.random()*2);
        if (rand==1) return Color.WHITE;
        else return Color.BLACK;
    }
}
