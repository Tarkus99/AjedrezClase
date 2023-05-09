package es.ieslavereda.model.GAME;

/**
 * Clase para instanciar un Caballo negro.
 */
public final class CaballoNegro extends Caballo{

    public CaballoNegro(Celda celda){
        super(PieceType.BLACK_CABALLO,celda);
    }
}
