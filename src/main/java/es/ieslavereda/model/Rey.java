package es.ieslavereda.model;

import es.ieslavereda.model.TAD.ListaCoord;
import es.ieslavereda.model.TAD.ListaPieza;
import es.ieslavereda.model.TAD.NodoPieza;

public class Rey extends Pieza {
    private boolean movido;

    public Rey(Pieza.PieceType p, Celda c) {
        super(p, c);
        movido = false;
    }


    @Override
    public void moveTo(Coordenada nuevaPosición){
        if (!movido) {
            if (nuevaPosición.equals(getCelda().getCoordenada().casillaRight().casillaRight()))
                Movimientos.enrocarseCorto(this);
            else if (nuevaPosición.equals(getCelda().getCoordenada().casillaLeft().casillaLeft())) {
                Movimientos.enrocarseLargo(this);
            }
        }else {
            Movimientos.moveTo(this, nuevaPosición);
        }

        movido = true;
    }

    @Override
    public ListaCoord getNextKills() {
        coordenadasMovimientos = new ListaCoord();
        Coordenada posicion = getCelda().getCoordenada();
        Coordenada c;
        Tablero t = getCelda().getTablero();

        //Up
        c = posicion.casillaUp();
        verificarCasillaSinJaque(c, t);
        //Down
        c = posicion.casillaDown();
        verificarCasillaSinJaque(c, t);
        //Left
        c = posicion.casillaLeft();
        verificarCasillaSinJaque(c, t);
        //Right
        c = posicion.casillaRight();
        verificarCasillaSinJaque(c, t);
        //UpRigth
        c = posicion.diagoUpRight();
        verificarCasillaSinJaque(c, t);
        //UpLeft
        c = posicion.diagoUpLeft();
        verificarCasillaSinJaque(c, t);
        //DownRigth
        c = posicion.diagoDownRight();
        verificarCasillaSinJaque(c, t);
        //DownLeft
        c = posicion.diagoDownLeft();
        verificarCasillaSinJaque(c, t);

        return coordenadasMovimientos;
    }

    @Override
    public ListaCoord getComplexMoves() {
        coordenadasMovimientos.clear();
        Coordenada posicion = getCelda().getCoordenada();
        Coordenada c;
        Tablero t = getCelda().getTablero();

        //Up
        c = posicion.casillaUp();
        verificarCasillaConJaque(c, t);
        //Down
        c = posicion.casillaDown();
        verificarCasillaConJaque(c, t);
        //Left
        c = posicion.casillaLeft();
        verificarCasillaConJaque(c, t);
        //Right
        c = posicion.casillaRight();
        verificarCasillaConJaque(c, t);
        //UpRigth
        c = posicion.diagoUpRight();
        verificarCasillaConJaque(c, t);
        //UpLeft
        c = posicion.diagoUpLeft();
        verificarCasillaConJaque(c, t);
        //DownRigth
        c = posicion.diagoDownRight();
        verificarCasillaConJaque(c, t);
        //DownLeft
        c = posicion.diagoDownLeft();
        verificarCasillaConJaque(c, t);
        //

        if (!movido && !coordenadasMovimientos.isEmpty())
            coordenadasMovimientos = coordenadasMovimientos.addAll(añadirEnroque(t));

        return coordenadasMovimientos;
    }

    private ListaCoord añadirEnroque(Tablero t) {
        ListaCoord listaAux = new ListaCoord();
        Coordenada posicion = getCelda().getCoordenada();
        if (!estaEnJaque(posicion)) {

            if (t.getTorreCorta(getColor(), posicion)) {
                if (enroqueCorto()) {
                    listaAux.add(posicion.casillaRight().casillaRight());
                }
            }
            if (t.getTorreLarga(getColor(), posicion)) {
                if (enroqueLargo()) {
                    listaAux.add(posicion.casillaLeft().casillaLeft());
                }
            }
        }
        return listaAux;
    }

    public boolean enroqueCorto() {
        Tablero t = getCelda().getTablero();
        Coordenada posicion = getCelda().getCoordenada();
        Coordenada coordenada;
        coordenada = posicion.casillaRight();
        ListaPieza vectorPiezas = getCelda().getTablero().piezasEnemigas(getColor());
        ListaCoord allMovements = Tools.allMovements(vectorPiezas);
        int i = 0;
        while (i < 2) {
            if (!t.getCelda(coordenada).isEmpty() || allMovements.contiene(coordenada))
                return false;
            coordenada = coordenada.casillaRight();
            i++;
        }
        return true;
    }

    public boolean enroqueLargo() {
        Tablero t = getCelda().getTablero();
        Coordenada coordenada = getCelda().getCoordenada();
        coordenada = coordenada.casillaLeft();
        ListaPieza vectorPiezas = getCelda().getTablero().piezasEnemigas(getColor());
        ListaCoord allMovements = Tools.allMovements(vectorPiezas);
        int i = 0;
        while (i < 3) {
            if (!t.getCelda(coordenada).isEmpty() ||  allMovements.contiene(coordenada))
                return false;
            coordenada = coordenada.casillaLeft();
            i++;
        }
        return true;
    }

    private void verificarCasillaSinJaque(Coordenada coordenada, Tablero t) {

        if (t.getCelda(coordenada) != null) {
            if
            (t.getCelda(coordenada).isEmpty()
                    ||
                    (!t.getCelda(coordenada).isEmpty() && t.getCelda(coordenada).getPiece().getColor() != this.getColor())) {
                coordenadasMovimientos.add(coordenada);
            }
        }
    }

    private void verificarCasillaConJaque(Coordenada coordenada, Tablero t) {
        Coordenada origen = getCelda().getCoordenada();
        Pieza prov;

        if (t.getCelda(coordenada) != null) {
            if (!t.getCelda(coordenada).isEmpty()) {
                prov = t.getCelda(coordenada).getPiece();
                if (prov.getColor() != this.getColor()) {
                    Movimientos.moveSinMatar(this, coordenada);
                    if (!estaEnJaque(getCelda().getCoordenada()))
                        coordenadasMovimientos.add(coordenada);
                    deshacerMove(origen, coordenada, prov);
                }
            } else {
                Movimientos.moveSinMatar(this, coordenada);
                if (!estaEnJaque(getCelda().getCoordenada()))
                    coordenadasMovimientos.add(coordenada);
                deshacerMove(origen, coordenada, null);
            }
        }
    }

    public boolean estaEnJaque(Coordenada c) {

        ListaPieza vectorPiezas = getCelda().getTablero().piezasEnemigas(getColor());
        ListaCoord allMovements = Tools.allMovements(vectorPiezas);
        return allMovements.contiene(c);
    }

    public boolean mate(){
        return getCelda().getTablero().movesAliadosVacios(getColor()) && estaEnJaque(getCelda().getCoordenada());
    }

    public boolean tablas(Coordenada coordenada){
        return getCelda().getTablero().movesAliadosVacios(getColor()) && !estaEnJaque(coordenada);
    }
}
