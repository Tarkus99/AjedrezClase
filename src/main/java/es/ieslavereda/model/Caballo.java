package es.ieslavereda.model;

import es.ieslavereda.model.TAD.ListaCoord;

public abstract class Caballo extends Pieza {


    public Caballo(PieceType pieceType, Celda celda) {
        super(pieceType, celda);
    }

    @Override
    public void moveTo(Coordenada nuevaPosición){
        Movimientos.moveTo(this, nuevaPosición);
    }

    @Override
    public ListaCoord getNextKills() {
        coordenadasMovimientos.clear();
        Coordenada posicion = getCelda().getCoordenada();

        Coordenada c;
        //Up
        c = posicion.casillaUp().casillaUp().casillaLeft();
        verificarCasillas(c);
        c = posicion.casillaUp().casillaUp().casillaRight();
        verificarCasillas(c);
        //Down
        c = posicion.casillaDown().casillaDown().casillaLeft();
        verificarCasillas(c);
        c = posicion.casillaDown().casillaDown().casillaRight();
        verificarCasillas(c);
        //Left
        c = posicion.casillaLeft().casillaLeft().casillaDown();
        verificarCasillas(c);
        c = posicion.casillaLeft().casillaLeft().casillaUp();
        verificarCasillas(c);
        //Right
        c = posicion.casillaRight().casillaRight().casillaUp();
        verificarCasillas(c);
        c = posicion.casillaRight().casillaRight().casillaDown();
        verificarCasillas(c);

        return coordenadasMovimientos;
    }

    @Override
    public ListaCoord getComplexMoves() {
        coordenadasMovimientos.clear();
        Coordenada posicion = getCelda().getCoordenada();

        Coordenada c;
        //Up
        c = posicion.casillaUp().casillaUp().casillaLeft();
        verificarComplexCasillas(c);
        c = posicion.casillaUp().casillaUp().casillaRight();
        verificarComplexCasillas(c);
        //Down
        c = posicion.casillaDown().casillaDown().casillaLeft();
        verificarComplexCasillas(c);
        c = posicion.casillaDown().casillaDown().casillaRight();
        verificarComplexCasillas(c);
        //Left
        c = posicion.casillaLeft().casillaLeft().casillaDown();
        verificarComplexCasillas(c);
        c = posicion.casillaLeft().casillaLeft().casillaUp();
        verificarComplexCasillas(c);
        //Right
        c = posicion.casillaRight().casillaRight().casillaUp();
        verificarComplexCasillas(c);
        c = posicion.casillaRight().casillaRight().casillaDown();
        verificarComplexCasillas(c);

        return coordenadasMovimientos;
    }
        private void verificarCasillas(Coordenada c) {
        Tablero t = getCelda().getTablero();

        if (t.getCelda(c) != null) {
            if (t.getCelda(c).isEmpty()) {
                coordenadasMovimientos.add(c);
            } else {
                if (t.getCelda(c).getPiece().getColor() != this.getColor())
                    coordenadasMovimientos.add(c);
            }
        }
    }

    private void verificarComplexCasillas(Coordenada c) {
        Tablero t = getCelda().getTablero();
        Pieza provisional;
        Celda celda = this.celda;

        if (t.getCelda(c) != null) {
            if (t.getCelda(c).isEmpty()) {
                Movimientos.moveSinMatar(this, c);
                if (!t.estaJaqueColor(this.getColor())) {
                    coordenadasMovimientos.add(c);
                }
                deshacerMove(celda.getCoordenada(), c, null);
            } else {
                provisional = t.getCelda(c).getPiece();
                if (t.getCelda(c).getPiece().getColor() != this.getColor()) {
                    Movimientos.moveSinMatar(this, c);
                    if (!t.estaJaqueColor(this.getColor())) {
                        coordenadasMovimientos.add(c);
                    }
                    deshacerMove(celda.getCoordenada(), c, provisional);
                }

            }
        }
    }
}

