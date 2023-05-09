package es.ieslavereda.model.GAME;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Clase abstracta que gestiona el comportamiento común de todas las piezas del ajedrez.
 * @author genguidanosn
 */
public abstract class Pieza implements Serializable {

    /**
     * Celda sobre la que está una pieza.
     */
    protected Celda celda;
    /**
     * Forma que adquiere la pieza.
     */
    private PieceType forma;
    /**
     * Conjunto de coordenadas a los que puede moverse una pieza.
     */
    protected Set<Coordenada> coordenadasMovimientos;

    /**
     * Constructor de Pieza.
     * @param f forma que tendrá la pieza.
     * @param celda celda sobre la cuál se posará la pieza.
     */
    public Pieza(PieceType f, Celda celda) {
        this.forma = f;
        this.celda = celda;
        coordenadasMovimientos = new HashSet<>();
        putPieceInPlace();
    }

    /**
     * Getter de la celda donde se encuentra la pieza.
     * @return Celda
     */
    public Celda getCelda() {
        return celda;
    }

    /**
     * Getter del valor en puntos que tiene una pieza.
     * @return número natural.
     */
    public int getValor(){
        return forma.valor;
    }

    /**
     * Getter del color de la forma de la pieza.
     * @return Color.
     */
    public Color getColor() {
        return forma.color;
    }

    /**
     * Getter del tipo (o forma) de la pieza.
     * @return la forma de la pieza,
     */
    public PieceType getType() {
        return forma;
    }

    /**
     * Método que asigna la pieza actual a la celda. Sólo es usada al inicio de la partida cuando se colocan todas
     * las piezas. Este método permite que la pieza sea visible sobre su celda.
     */
    public void putPieceInPlace() {
        celda.setPieza(this);
    }

    /**
     * Metodo que devuelve el texto descriptivo de la pieza con su artículo femenino o masculino.
     * @return cadena de texto.
     */
    public String decirNombre() {
        if (forma == PieceType.BLACK_REINA || forma == PieceType.BLACK_TORRE
                || forma == PieceType.WHITE_REINA || forma == PieceType.WHITE_TORRE) {
            return "La " + this.forma.nombre;
        } else {
            return "El " + this.forma.nombre;
        }
    }

    /**
     * Metodo abstracto que obtiene las coordenadas donde se puede mover una pieza, sin restricciones.
     * @return conjunto de coordenadas sin repetición.
     */
    public abstract Set<Coordenada> getNextKills();

    /**
     * Método abstracto para obtener las coordenadas donde se puede mover una pieza siempre que no deje en jaque
     * a su rey.
     * @return conjunto de coordenadas sin repetición.
     */
    public abstract Set<Coordenada> getComplexMoves();

    /**
     * Método que mueve la pieza a una nueva posición.
     * @param nuevaPosicion nueva coordenada dónde mover la pieza.
     */
    public abstract void moveTo(Coordenada nuevaPosicion);

    /**
     * Método para deshacer un movimiento. útil para verificar el método complexMoves()
     * @param origen coordenada donde estaba la pieza antes de moverse.
     * @param provisional coordenada donde se ha movido la pieza.
     * @param laQueEstaba pieza que estaba en la coordenada provisional.
     */
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

    /**
     * Lista de las diferentes formas que puede adquirir una pieza.
     */
    public enum PieceType {

        BLACK_PEON('\u265F', Color.BLACK, "peón",1),
        BLACK_ALFIL('\u265D', Color.BLACK, "alfil",3),
        BLACK_CABALLO('\u265E', Color.BLACK, "caballo",3),
        BLACK_TORRE('\u265C', Color.BLACK, "torre",5),
        BLACK_REINA('\u265B', Color.BLACK, "reina",9),
        BLACK_REY('\u265A', Color.BLACK, "rey",0),
        WHITE_PEON('\u265F', Color.WHITE, "peón",1),
        WHITE_ALFIL('\u265D', Color.WHITE, "alfil",3),
        WHITE_CABALLO('\u265E', Color.WHITE, "caballo",3),
        WHITE_TORRE('\u265C', Color.WHITE, "torre",5),
        WHITE_REINA('\u265B', Color.WHITE, "reina",9),
        WHITE_REY('\u265A', Color.WHITE, "rey",0);

        //Atributos
        /**
         * Carácter de la tabla UNICODE que representa visualmente la forma.
         */
        char forma;
        /**
         * Color de la forma
         */
        Color color;
        /**
         * Nombre descriptivo de la forma
         */
        String nombre;
        /**
         * Valor en puntos de la pieza según el reglamento del ajedrez.
         */
        int valor;

        /**
         * Constructor de PieceType
         * @param shape carácter UNICODE representativo de la forma.
         * @param c color en el cual se imprimirá por pantalla la forma.
         * @param nombre texto descriptivo de la forma.
         * @param valor puntos que vale cada pieza.
         */
        PieceType(char shape, Color c, String nombre, int valor) {
            forma = shape;
            color = c;
            this.nombre = nombre;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return String.valueOf(forma);
        }
    }
}
