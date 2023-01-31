package es.ieslavereda.model;

import es.ieslavereda.model.TAD.ListaCoord;

import static com.diogonunes.jcolor.Ansi.colorize;

public abstract class Pieza {

    protected Celda celda;
    private PieceType forma;

    protected ListaCoord coordenadasMovimientos;

    public Pieza(PieceType f, Celda celda) {
        this.forma = f;
        this.celda = celda;
        coordenadasMovimientos = new ListaCoord();
        putPieceInPlace();
    }

    public Celda getCelda() {
        return celda;
    }

    public Color getColor() {
        return forma.color;
    }

    public PieceType getType() {
        return forma;
    }

    public void putPieceInPlace() {
        celda.setPieza(this);
    }

    public String decirNombre() {
        if (forma == PieceType.BLACK_REINA || forma == PieceType.BLACK_TORRE
                || forma == PieceType.WHITE_REINA || forma == PieceType.WHITE_TORRE) {
            return "La " + this.forma.nombre;
        } else {
            return "El " + this.forma.nombre;
        }
    }

    public String soloForma() {
        return colorize(forma.toString(), forma.color.getColor());
    }

    public abstract ListaCoord getNextKills();

    public abstract ListaCoord getComplexMoves();
    public abstract void moveTo(Coordenada nuevaPosicion);

    public void deshacerMove(Coordenada origen, Coordenada provisional, Pieza laQueEstaba) {
        Tablero t = getCelda().getTablero();

        if (laQueEstaba == null) {
            t.getCelda(provisional).setPieza(null);
            celda = t.getCelda(origen);
            t.getCelda(origen).setPieza(this);
        } else {
            t.getCelda(provisional).setPieza(laQueEstaba);
            t.getCelda(provisional).getPiece().celda = t.getCelda(provisional);
            this.celda = t.getCelda(origen);
            t.getCelda(origen).setPieza(this);
        }
    }
    @Override
    public String toString() {
        return colorize(forma.toString(), forma.color.getColor(), celda.getColor().attribute);
    }


    public enum PieceType {

        BLACK_PEON('\u265D', Color.BLACK, "peón"),
        BLACK_ALFIL('\u265D', Color.BLACK, "alfil"),
        BLACK_CABALLO('\u265E', Color.BLACK, "caballo"),
        BLACK_TORRE('\u265C', Color.BLACK, "torre"),
        BLACK_REINA('\u265B', Color.BLACK, "reina"),
        BLACK_REY('\u265A', Color.BLACK, "rey"),
        WHITE_PEON('\u265D', Color.WHITE, "peón"),
        WHITE_ALFIL('\u265D', Color.WHITE, "alfil"),
        WHITE_CABALLO('\u265E', Color.WHITE, "caballo"),
        WHITE_TORRE('\u265C', Color.WHITE, "torre"),
        WHITE_REINA('\u265B', Color.WHITE, "reina"),
        WHITE_REY('\u265A', Color.WHITE, "rey");

        char forma;
        Color color;
        String nombre;

        PieceType(char shape, Color c, String nombre) {
            forma = shape;
            color = c;
            this.nombre = nombre;
        }

//        @Override
//        public boolean equals(Object obj){
//            if (obj instanceof PieceType){
//                PieceType p = (PieceType) obj;
//                return forma==p.forma && color==p.color;
//            }
//            return false;
//        }

        @Override
        public String toString() {
            return String.valueOf(forma);
        }

    }
}
