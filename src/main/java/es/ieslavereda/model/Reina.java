package es.ieslavereda.model;

import es.ieslavereda.model.TAD.ListaCoord;

public abstract class Reina extends Pieza {

    public Reina(Pieza.PieceType p, Celda c) {
        super(p, c);
    }

    @Override
    public void moveTo(Coordenada nuevaPosición){
        Movimientos.moveTo(this, nuevaPosición);
    }

    @Override
    public ListaCoord getNextKills() {
        return new ListaCoord(Torre.getNextKillsAsTorre(this)).addAll(Alfil.getNextKillsAsAlfil(this));
    }

    @Override
    public ListaCoord getComplexMoves() {
        coordenadasMovimientos.clear();
        coordenadasMovimientos = coordenadasMovimientos.addAll(Torre.getComplexMovesAsTorre(this));
        coordenadasMovimientos = coordenadasMovimientos.addAll(Alfil.getComplexMovesAsAlfil(this));
        return coordenadasMovimientos;
    }

}
