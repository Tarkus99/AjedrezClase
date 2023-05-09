package es.ieslavereda.model.GAME;

import java.util.HashSet;
import java.util.Set;

public abstract class Reina extends Pieza {

    public Reina(Pieza.PieceType p, Celda c) {
        super(p, c);
    }

    @Override
    public void moveTo(Coordenada nuevaPosición){
        Movimientos.moveTo(this, nuevaPosición);
    }

    @Override
    public Set<Coordenada> getNextKills() {
        Set<Coordenada> reina = new HashSet<>( Torre.getNextKillsAsTorre(this));
        reina.addAll(Alfil.getNextKillsAsAlfil(this));
        return reina;
    }

    @Override
    public Set<Coordenada> getComplexMoves() {
        coordenadasMovimientos.clear();
        coordenadasMovimientos = Torre.getComplexMovesAsTorre(this);
        coordenadasMovimientos.addAll(Alfil.getComplexMovesAsAlfil(this));
        return coordenadasMovimientos;
    }
}
