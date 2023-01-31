package es.ieslavereda.model;

public class Movimientos {
    public static void moveTo(Pieza p, Coordenada nuevaPosicion) {
        Tablero t = p.getCelda().getTablero();
        Celda celda = p.getCelda();

        if (t.getCelda(nuevaPosicion).isEmpty()) {
            t.getCelda(celda.getCoordenada()).setPieza(null);
            p.celda = t.getCelda(nuevaPosicion);
            t.getCelda(nuevaPosicion).setPieza(p);
        } else {
            t.getCelda(celda.getCoordenada()).setPieza(null);
            t.getManager().addPiece(t.getCelda(nuevaPosicion).getPiece());
            p.celda = t.getCelda(nuevaPosicion);
            t.getCelda(nuevaPosicion).getPiece().celda = null;
            t.getCelda(nuevaPosicion).setPieza(p);
        }
        t.resetColors();
    }

    public static void moveSinMatar(Pieza p, Coordenada nuevaPosicion) {
        Tablero t = p.getCelda().getTablero();
        Celda celda = p.getCelda();

        if (t.getCelda(nuevaPosicion).isEmpty()) {
            t.getCelda(celda.getCoordenada()).setPieza(null);
            p.celda = t.getCelda(nuevaPosicion);
            t.getCelda(nuevaPosicion).setPieza(p);
        } else if (t.getCelda(nuevaPosicion).getPiece().getColor() != p.getColor()) {
            t.getCelda(celda.getCoordenada()).setPieza(null);
            p.celda = t.getCelda(nuevaPosicion);
            t.getCelda(nuevaPosicion).getPiece().celda = null;
            t.getCelda(nuevaPosicion).setPieza(p);
        }
    }

    public static void enrocarseCorto(Pieza p) {
        Tablero t = p.getCelda().getTablero();
        Coordenada origen = p.getCelda().getCoordenada();
        Coordenada coordenadaTorre;
        Pieza torreCorta = t.getCelda(new Coordenada('h', origen.getRow())).getPiece();
        coordenadaTorre = torreCorta.getCelda().getCoordenada();
        Movimientos.moveTo(p, origen.casillaRight().casillaRight());
        Movimientos.moveTo(torreCorta, coordenadaTorre.casillaLeft().casillaLeft());
    }

    public static void enrocarseLargo(Pieza p) {
        Tablero t = p.getCelda().getTablero();
        Coordenada origen = p.getCelda().getCoordenada();
        Coordenada coordenadaTorre;
        Pieza torreLarga = t.getCelda(new Coordenada('a', origen.getRow())).getPiece();
        coordenadaTorre = torreLarga.getCelda().getCoordenada();
        Movimientos.moveTo(p, origen.casillaLeft().casillaLeft());
        Movimientos.moveTo(torreLarga, coordenadaTorre.casillaRight().casillaRight().casillaRight());
    }
}
