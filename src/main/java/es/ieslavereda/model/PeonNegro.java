package es.ieslavereda.model;

import es.ieslavereda.model.TAD.ListaCoord;

public final class PeonNegro extends Peon {
    private ListaCoord coordenadasKills;

    public PeonNegro(Celda celda) {
        super(PieceType.BLACK_PEON, celda);
        coordenadasKills = new ListaCoord();
    }

   /* @Override
    public void moveTo(Coordenada c){
        super.moveTo(c);

        if (getCelda().getCoordenada().getRow()==8){
            new ReinaNegra(getCelda());
        }
    }*/

    @Override
    public void transform() {
        new ReinaNegra(getCelda());
        celda = null;
    }

   /* @Override
    public ListaCoord getNextMoves() {
        coordenadasMovimientos = new ListaCoord();
        coordenadasKills = new ListaCoord();
        Coordenada posicion = getCelda().getCoordenada();
        Coordenada c;
        Tablero t = getCelda().getTablero();

        //Up
        c = posicion.casillaDown();
        verificarCasilla(c, t, false);
        //DoubleUp
        if (!coordenadasMovimientos.isEmpty() && getCelda().getCoordenada().getRow() == 2) {
            c = posicion.casillaDown().casillaDown();
            verificarCasilla(c, t, false);
        }
        //Matar
        c = posicion.diagoUpLeft();
        verificarCasilla(c, t, true);
        c = posicion.diagoUpRight();
        verificarCasilla(c, t, true);
        return coordenadasMovimientos.addAll(coordenadasKills);
    }
*/
   @Override
   public void moveTo(Coordenada nuevaPosición){
       Movimientos.moveTo(this, nuevaPosición);
       if (celda.getCoordenada().getRow()==8)
           transform();
   }

    @Override
    public ListaCoord getNextKills() {
        coordenadasKills.clear();
        Coordenada posicion = getCelda().getCoordenada();
        Coordenada c;
        Tablero t = getCelda().getTablero();
        c = posicion.diagoDownLeft();
        verificarCasilla(c, t, true);
        c = posicion.diagoDownRight();
        verificarCasilla(c, t, true);
        return coordenadasKills;
    }

    public void verificarCasilla(Coordenada c, Tablero t, boolean matar) {
        if (t.getCelda(c) != null) {
            if (!matar) {
                if (t.getCelda(c).isEmpty()) {
                    coordenadasMovimientos.add(c);
                }
            } else {
                if (!t.getCelda(c).isEmpty() && t.getCelda(c).getPiece().getColor() != this.getColor()) {
                    coordenadasKills.add(c);
                }
            }
        }
    }


    @Override
    public ListaCoord getComplexMoves() {
        coordenadasMovimientos.clear();
        coordenadasKills.clear();
        Coordenada posicion = getCelda().getCoordenada();
        Coordenada c;
        Tablero t = getCelda().getTablero();

        //Up
        c = posicion.casillaDown();
        verificarComplexCasilla(c, t, this.celda);
        //DoubleUp
        if (!coordenadasMovimientos.isEmpty() && getCelda().getCoordenada().getRow() == 2) {
            c = posicion.casillaDown().casillaDown();
            verificarComplexCasilla(c, t, this.celda);
        }
        //Matar
        c = posicion.diagoDownLeft();
        verificarComplexMatar(c, t, getCelda());
        c = posicion.diagoDownRight();
        verificarComplexMatar(c, t, getCelda());

        return coordenadasMovimientos.addAll(coordenadasKills);
    }

    public void verificarComplexCasilla(Coordenada coordenada, Tablero t, Celda celda1) {
        if (t.getCelda(coordenada) != null) {
            if (t.getCelda(coordenada).isEmpty()) {
                Movimientos.moveSinMatar(this,coordenada);
                if (!t.estaJaqueColor(this.getColor())) {
                    coordenadasMovimientos.add(coordenada);
                }
                deshacerMove(celda1.getCoordenada(), coordenada, null);
            }
        }
    }


    public void verificarComplexMatar(Coordenada c, Tablero t, Celda celda1) {
        Pieza provisional;
        if (t.getCelda(c) != null)
        {
            if (!t.getCelda(c).isEmpty() && t.getCelda(c).getPiece().getColor() != this.getColor())
            {
                provisional = t.getCelda(c).getPiece();
                Movimientos.moveSinMatar(this, c);
                if (!t.estaJaqueColor(this.getColor()))
                {
                    coordenadasKills.add(c);
                }
                deshacerMove(celda1.getCoordenada(), c, provisional);
            }
        }
    }
}
