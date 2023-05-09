package es.ieslavereda.model.GAME;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Tablero implements Serializable {
    private final Map<Coordenada, Celda> cellsMap;
    private final IDeletePieceManager deletePieceManager;

    public Tablero(Partida partida) {

        this.cellsMap = new HashMap<>();
        Coordenada c;
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                c = new Coordenada((char) ('A' + col), row + 1);
                cellsMap.put(c, new Celda(c, this));
            }
        }
        rellenarTablero();
        deletePieceManager = new DeletePieceManager(partida);
    }

    private void rellenarTablero() {
        Pieza p;
        for (int col = 0; col < 8; col++) {
            if (col == 0 || col == 7)
                p = new TorreNegra(cellsMap.get(new Coordenada((char) ('A' + col), 1)));
            else if (col == 1 || col == 6)
                p = new CaballoNegro(cellsMap.get(new Coordenada((char) ('A' + col), 1)));
            else if (col == 2 || col == 5)
                p = new AlfilNegro(cellsMap.get(new Coordenada((char) ('A' + col), 1)));
            else if (col == 3)
                p = new ReinaNegra(cellsMap.get(new Coordenada((char) ('A' + col), 1)));
            else
                p = new ReyNegro(cellsMap.get(new Coordenada((char) ('A' + col), 1)));
        }
        for (int col = 0; col < 8; col++) {
            p = new PeonNegro(cellsMap.get(new Coordenada((char) ('A' + col), 2)));
        }
        //
        for (int col = 0; col < 8; col++) {
            if (col == 0 || col == 7)
                p = new TorreBlanca(cellsMap.get(new Coordenada((char) ('A' + col), 8)));
            else if (col == 1 || col == 6)
                p = new CaballoBlanco(cellsMap.get(new Coordenada((char) ('A' + col), 8)));
            else if (col == 2 || col == 5)
                p = new AlfilBlanco(cellsMap.get(new Coordenada((char) ('A' + col), 8)));
            else if (col == 3) p = new ReinaBlanca(cellsMap.get(new Coordenada((char) ('A' + col), 8)));
            else p = new ReyBlanco(cellsMap.get(new Coordenada((char) ('A' + col), 8)));
        }
        for (int col = 0; col < 8; col++) {
            p = new PeonBlanco(cellsMap.get(new Coordenada((char) ('A' + col), 7)));
        }
    }

    public Map<Coordenada, Celda> getCellsMap() {
        return cellsMap;
    }

    public Celda getCelda(Coordenada coordenada) {
        return cellsMap.get(coordenada);
    }

    public DeletePieceManager getManager() {
        return (DeletePieceManager) deletePieceManager;
    }

    public void resaltarTablero(Set<Coordenada> aux) {
        for (Coordenada c : aux)
            getCelda(c).resaltar();
    }

    public void resaltarTableroOne(Coordenada c){
        getCelda(c).resaltarJaque();
    }

    public void resetColors() {
        for (Celda c : cellsMap.values()) {
            c.resetColor();
        }
    }

    public List<Pieza> piezasEnemigas(Color colorAux) { //Devolver en una lista las fichas del color contrario al del parametro
        return cellsMap.values().stream()
                .map(Celda::getPiece)
                .filter((p) -> p != null && p.getColor() != colorAux)
                .collect(Collectors.toList());
    }

    public List<Pieza> piezasAliadas(Color colorAux) { //Devolver en una lista las fichas del color igual al del parametro
        return cellsMap.values().stream()
                .map(Celda::getPiece)
                .filter((p) -> p != null && p.getColor() == colorAux)
                .collect(Collectors.toList());

    }

    public boolean movesAliadosVacios(Color color) {
        List<Pieza> aliadas = piezasAliadas(color);
        Set<Coordenada> aux;

        for (Pieza p : aliadas) {
            aux = p.getComplexMoves();
            if (!aux.isEmpty()) {
                return false;
            }
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
        boolean ayuda = false;
        Optional<Pieza> aux = cellsMap.values().stream()
                .map(Celda::getPiece)
                .filter((z) -> z != null && z.getType() == p)
                .findFirst();
        Rey reyAux;
        if (aux.isPresent()) {
            reyAux = ((Rey) aux.get());
            ayuda = reyAux.estaEnJaque(reyAux.getCelda().getCoordenada());
            if (ayuda) resaltarTableroOne(reyAux.getCelda().getCoordenada());
            return ayuda;
        } else {
            return false;
        }
    }

    public boolean estaMateColor(Color c) {
        if (c == Color.WHITE) {
            return reyMate(Pieza.PieceType.WHITE_REY);
        } else {
            return reyMate(Pieza.PieceType.BLACK_REY);
        }
    }

    public boolean reyMate(Pieza.PieceType p) {
        for (Celda col : cellsMap.values()) {
            if (!col.isEmpty()) {
                if (col.getPiece().getType() == p) {
                    Rey reyAux = (Rey) col.getPiece();
                    return reyAux.mate();
                }
            }
        }
        return false;
    }

    public boolean hayTablasColor(Color c) {
        if (c == Color.WHITE) {
            return reyTablas(Pieza.PieceType.WHITE_REY);
        } else {
            return reyTablas(Pieza.PieceType.BLACK_REY);
        }
    }

    public boolean reyTablas(Pieza.PieceType p) {
        for (Celda col : cellsMap.values()) {
            if (!col.isEmpty()) {
                if (col.getPiece().getType() == p) {
                    Rey reyAux = (Rey) col.getPiece();
                    return reyAux.tablas(reyAux.getCelda().getCoordenada());
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
                return !((Torre) (piezaAux)).seHaMovido();
            }
        }
        return false;
    }

//    public void dameElRey(Color color) {
//        if (color == Color.WHITE)
//            resaltarJaque(Pieza.PieceType.WHITE_REY);
//        else
//            resaltarJaque(Pieza.PieceType.BLACK_REY);
//    }
//
//    public void resaltarJaque(Pieza.PieceType p) {
//        for (Celda c : cellsMap.values()) {
//            if (!c.isEmpty()) {
//                if (c.getPiece().getType() == p) {
//                    c.resaltarJaque();
//                }
//            }
//        }
//    }
}
