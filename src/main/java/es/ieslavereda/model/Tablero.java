package es.ieslavereda.model;

import Jugador.Jugador;
import es.ieslavereda.model.TAD.ListaCoord;
import es.ieslavereda.model.TAD.ListaPieza;
import es.ieslavereda.model.TAD.NodoCoord;
import es.ieslavereda.model.TAD.NodoPieza;

import javax.management.relation.RelationService;

public class Tablero {

    private Partida partida;
    private final Celda[][] vectorCeldas;
    private IDeletePieceManager deletePieceManager;


    public Tablero(Partida partida) {

        this.vectorCeldas = new Celda[8][8];
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                vectorCeldas[row][col] = new Celda(new Coordenada((char) ('A' + col), row + 1), this);
            }
        }
        this.partida = partida;
        rellenarTablero();

        deletePieceManager = new DeletePieceManager();
    }


    private void rellenarTablero() {
        Pieza p;
        for (int col = 0; col < 8; col++) {
            if (col == 0 || col == 7)
                p = new TorreNegra(vectorCeldas[0][col]);
            else if (col == 1 || col == 6)
                p = new CaballoNegro(vectorCeldas[0][col]);
            else if (col == 2 || col == 5)
                p = new AlfilNegro(vectorCeldas[0][col]);
            else if (col == 3) p = new ReinaNegra(vectorCeldas[0][col]);
            else p = new ReyNegro(vectorCeldas[0][col]);
        }
        for (int col = 0; col < 8; col++) {
            p = new PeonNegro(vectorCeldas[1][col]);
        }
        //
        for (int col = 0; col < 8; col++) {
            if (col == 0 || col == 7)
                p = new TorreBlanca(vectorCeldas[7][col]);
            else if (col == 1 || col == 6)
                p = new CaballoBlanco(vectorCeldas[7][col]);
            else if (col == 2 || col == 5)
                p = new AlfilBlanco(vectorCeldas[7][col]);
            else if (col == 3) p = new ReinaBlanca(vectorCeldas[7][col]);
            else p = new ReyBlanco(vectorCeldas[7][col]);
        }
        for (int col = 0; col < 8; col++) {
            p = new PeonBlanco(vectorCeldas[6][col]);
        }

    }

    public Celda getCelda(Coordenada c) {
        if ((c.getRow() < 1 || c.getRow() > 8) || (c.getCol() < 'A' || c.getCol() > 'H')) return null;
        else return vectorCeldas[c.getRow() - 1][c.getCol() - 'A'];
    }
    public DeletePieceManager getManager(){
        return (DeletePieceManager) deletePieceManager;
    }

    public void resaltarTablero(ListaCoord aux) {
        NodoCoord nodAux = aux.getCabeza();
        while (nodAux != null) {
            getCelda(nodAux.getCoordenada()).resaltar();
            nodAux = nodAux.getNext();
        }
    }

    public void resetColors() {
        for (Celda[] row : vectorCeldas) {
            for (Celda c : row)
                c.resetColor();
        }
    }

    public ListaPieza piezasEnemigas(Color colorAux) { //Devolver en un array las fichas del color contrario al del parametro

        ListaPieza vectorPiezas = new ListaPieza();

        for (Celda[] row : vectorCeldas) {
            for (Celda col : row) {
                if (col.getPiece() != null && col.getPiece().getColor() != colorAux) {
                    vectorPiezas.add(col.getPiece());
                }
            }
        }
        return vectorPiezas;
    }

    public ListaPieza piezasAliadas(Color colorAux) { //Devolver en un array las fichas del color igual al del parametro

        ListaPieza vectorPiezas = new ListaPieza();

        for (Celda[] row : vectorCeldas) {
            for (Celda col : row) {
                if (col.getPiece() != null && col.getPiece().getColor() == colorAux) {
                    vectorPiezas.add(col.getPiece());
                }
            }
        }
        return vectorPiezas;
    }

    public boolean movesAliadosVacios(Color color) {
        ListaPieza aliadas = piezasAliadas(color);
        ListaCoord aux;
        NodoPieza nodoPiezaAux = aliadas.getCabeza();

        while (nodoPiezaAux != null) {
            aux = nodoPiezaAux.getPieza().getComplexMoves();
            if (!aux.isEmpty()) {
                return false;
            }
            nodoPiezaAux = nodoPiezaAux.getNext();
        }
        return true;
    }



    public boolean estaJaqueColor(Color c) {
        if (c == Color.WHITE) {
            return reyJaque(Pieza.PieceType.WHITE_REY);
        } else {
            return reyJaque(Pieza.PieceType.BLACK_REY);
        }
    }

    public boolean reyJaque(Pieza.PieceType p) {
        for (Celda[] row : vectorCeldas) {
            for (Celda col : row) {
                if (!col.isEmpty()) {
                    if (col.getPiece().getType() == p) {
                        Rey reyAux = (Rey) col.getPiece();
                        return reyAux.estaEnJaque(reyAux.getCelda().getCoordenada());
                    }
                }
            }
        }
        return false;
    }

    public boolean estaMateColor(Color c) {
        if (c == Color.WHITE) {
            return reyMate(Pieza.PieceType.WHITE_REY);
        } else {
            return reyMate(Pieza.PieceType.BLACK_REY);
        }
    }

    public boolean reyMate(Pieza.PieceType p) {
        for (Celda[] row : vectorCeldas) {
            for (Celda col : row) {
                if (!col.isEmpty()) {
                    if (col.getPiece().getType() == p) {
                        Rey reyAux = (Rey) col.getPiece();
                        return reyAux.mate();
                    }
                }
            }
        }
        return false;
    }

    public boolean hayTablasColor(Color c){
        if (c == Color.WHITE) {
            return reyTablas(Pieza.PieceType.WHITE_REY);
        } else {
            return reyTablas(Pieza.PieceType.BLACK_REY);
        }
    }

    public boolean reyTablas(Pieza.PieceType p){
        for (Celda[] row : vectorCeldas) {
            for (Celda col : row) {
                if (!col.isEmpty()) {
                    if (col.getPiece().getType() == p) {
                        Rey reyAux = (Rey) col.getPiece();
                        return reyAux.tablas(reyAux.getCelda().getCoordenada());
                    }
                }
            }
        }
        return false;
    }

    public boolean getTorreCorta(Color color, Coordenada coordenada) {
        Coordenada coordAux = coordenada.casillaRight().casillaRight().casillaRight();
        Pieza piezaAux = getCelda(coordAux).getPiece();

        if (piezaAux instanceof Torre) {
            if (piezaAux.getColor() == color) {
                return !((Torre) (piezaAux)).seHaMovido();
            }
        }
        return false;
    }

    public boolean getTorreLarga(Color color, Coordenada coordenada) {
        Coordenada coordAux = coordenada.casillaLeft().casillaLeft().casillaLeft().casillaLeft();
        Pieza piezaAux = getCelda(coordAux).getPiece();

        if (piezaAux instanceof Torre) {
            if (piezaAux.getColor() == color) {
                if (!((Torre) (piezaAux)).seHaMovido()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void dameElRey(Color color){
        if (color==Color.WHITE)
            resaltarJaque(Pieza.PieceType.WHITE_REY);
        else
            resaltarJaque(Pieza.PieceType.BLACK_REY);
    }

    public void resaltarJaque(Pieza.PieceType p){
        for (Celda[] row:vectorCeldas) {
            for (Celda c:row) {
                if (!c.isEmpty()){
                    if (c.getPiece().getType() == p){
                        c.resaltarJaque();
                    }
                }
            }
        }
    }

    public String direccionTablero(Color c){
        if (c == Color.WHITE)
            return toString();
        else{
            return tableroReverse();
        }
    }

    public String tableroReverse(){
        String output = "   H  G  F  E  D  C  B  A\n";
        for (int i = 7; i >= 0; i--) {
            output += (i + 1) + " ";
            for (int j = 7; j >= 0; j--) {
                output += vectorCeldas[i][j];
            }
            output += " " + (i + 1) + "\n";
        }
        output += "   H  G  F  E  D  C  B  A\n";
        String str = deletePieceManager.toString();
        return output + str;
    }

    @Override
    public String toString() {
        String output = "   A  B  C  D  E  F  G  H\n";
        for (int i = 0; i < 8; i++) {
            output += (i + 1) + " ";
            for (int j = 0; j < 8; j++) {
                output += vectorCeldas[i][j];
            }
            output += " " + (i + 1) + "\n";
        }
        output += "   A  B  C  D  E  F  G  H\n";
        String str = deletePieceManager.toString();

        return output + str;
    }
}
