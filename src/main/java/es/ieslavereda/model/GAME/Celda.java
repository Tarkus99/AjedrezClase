package es.ieslavereda.model.GAME;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Clase Celda que se encarga de gestionar las celdas del tablero.
 * @author genguidanosn
 */
public class Celda implements Serializable {
    /**
     * Color que adquiere la celda en cada momento de la partida.
     */
    private ColorCelda color;
    /**
     * Color original de la celda, que solo podrá ser blanco o negro y será inmutable.
     */
    private ColorCelda colorOriginal;
    private Coordenada coordenada;
    /**
     * Indica la pieza que hay sobre la celda.
     */
    private Pieza pieza;
    private Tablero tablero;

    /**
     * Constructor de Celda.
     * @param coordenada la coordenada en el tablero correspondiente a esta celda.
     * @param tablero el tablero al cuál pertenece esta celda.
     */
    public Celda(Coordenada coordenada, Tablero tablero) {
        this.coordenada = coordenada;
        this.tablero = tablero;
        this.pieza = null;
        if (((coordenada.getCol() - 'A') + (coordenada.getRow() - 1)) % 2 == 0) {
            this.colorOriginal = ColorCelda.BLANCO;
        } else {
            this.colorOriginal = ColorCelda.NEGRO;
        }
        color = colorOriginal;

    }

    /**
     * Método modificador para cambiar la pieza que hay sobre una celda.
     * @param pieza nueva pieza. Puede ser null si queremos quitar una pieza de una celda.
     */
    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    /**
     * Devuelve la coordenada correspondiente a la celda.
     * @return coordenada.
     */
    public Coordenada getCoordenada() {
        return coordenada;
    }

    /**
     * Getter de la pieza que hay en la celda.
     * @return Pieza.
     */
    public Pieza getPiece() {
        return pieza;
    }

    /**
     * Getter del tablero al cuál pertenece la celda.
     * @return tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Getter del color actual de la celda, que puede variar según el contexto de la partida.
     * @return color.
     */
    public ColorCelda getColor(){
        return color;
    }

    /**
     * Método que me indica si la celda esta vacía o no, es decir, si hay alguna pieza en ella o no.
     * @return true: si hay alguna pieza.
     * @return false: si no hay ninguna pieza.
     */
    public boolean isEmpty() {
        return pieza == null;
    }

    /**
     * Método para cambiar el color de la celda cuando queremos resaltar los posibles movimientos de una pieza.
     * Si la celda contiene una pieza, se resaltará de rojo, indicando al jugador que puede comer una pieza enemiga.
     * En caso contrario se resaltará de verde, indicando al jugador que puede moverse a una celda vacía.
     */
    public void resaltar() {
        if (isEmpty()){
            if (colorOriginal==ColorCelda.BLANCO)
                color=ColorCelda.LIGHT_GREEN;
            else
                color=ColorCelda.DARK_GREEN;
        }else{
            if(colorOriginal==ColorCelda.BLANCO)
                color=ColorCelda.LIGHT_RED;
            else
                color=ColorCelda.DARK_RED;
        }
    }

    /**
     * Método para resaltar una celda de color azulado, que indica la celda de origen y destino
     * del movimiento de una pieza.
     */
    public void resaltarLastMovement(){
        if (colorOriginal==ColorCelda.BLANCO)
            color=ColorCelda.LIGHT_YELLOW;
        else
            color=ColorCelda.DARK_YELLOW;
    }

    /**
     * Método para resaltar la celda del rey de color azul, indicando qeu está en jaque.
     */
    public void resaltarJaque(){
        color=ColorCelda.JAQUE;
    }

    /**
     * Método para que el color actual de la celda sea igual al color original de la celda.
     */
    public void resetColor() {
        color=colorOriginal;
    }

    @Override
    public String toString(){
        if (isEmpty())
            return colorize("   ", color.attribute);
        else
            return colorize(" ", color.attribute) +
                pieza +
                colorize(" ", color.attribute);
    }

    /**
     * Enumerción de los diferentes colores que puede adquirir una celda en función del contexto en el que se encuentre.
     * Cada color tiene un atributo <b>rgb</b> que indica el color real.
     */
    public enum ColorCelda {
        BLANCO(Attribute.BACK_COLOR(229, 186, 115)),
        NEGRO(Attribute.BACK_COLOR(57, 81, 68)),
        LIGHT_YELLOW(Attribute.BACK_COLOR(139, 211, 169)),
        DARK_YELLOW(Attribute.BACK_COLOR(139, 211, 200)),
        LIGHT_GREEN(Attribute.BACK_COLOR(97, 249, 107)),
        DARK_GREEN(Attribute.BACK_COLOR(28,179,17)),
        LIGHT_RED(Attribute.BACK_COLOR(255, 0, 0 )),
        DARK_RED(Attribute.BACK_COLOR(206, 40, 40)),
        JAQUE(Attribute.BACK_COLOR(132, 210, 197));

        public Attribute attribute;

        /**
         * Constructor del color de una celda.
         * @param attribute color en formato rgb.
         */
        ColorCelda(Attribute attribute) {
            this.attribute = attribute;
        }
    }
}
