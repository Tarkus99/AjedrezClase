package es.ieslavereda.model;

import es.ieslavereda.model.TAD.ListaCoord;

public abstract class Peon extends Pieza {

    public Peon(Pieza.PieceType p, Celda c) {
        super(p, c);
    }

   /* @Override
    public void moveVerificar(Coordenada nuevaPosicion){
        super.moveVerificar(nuevaPosicion);
        if(getCelda().getCoordenada().getRow()==1||getCelda().getCoordenada().getRow()==8){
            transform();
        }
    }*/

    public abstract void transform();
   /* public abstract ListaCoord getNextMoves();*/
    public abstract ListaCoord getComplexMoves();
    public abstract ListaCoord getNextKills();
}
