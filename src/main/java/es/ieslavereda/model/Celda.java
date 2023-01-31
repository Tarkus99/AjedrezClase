package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Celda {

    private ColorCelda color;
    private ColorCelda colorOriginal;
    private Coordenada coordenada;
    private Pieza pieza;
    private Tablero tablero;

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

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public Pieza getPiece() {
        return pieza;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ColorCelda getColorOriginal(){
        return colorOriginal;
    }

    public ColorCelda getColor(){
        return color;
    }

    public Attribute getAttribute(){
        return colorOriginal.attribute;
    }

    public boolean isEmpty() {
        return pieza == null;
    }

    public void resaltar() {
        if (isEmpty()){
            if (colorOriginal==ColorCelda.BLANCO)
                color=ColorCelda.LIGHT_YELLOW;
            else
                color=ColorCelda.DARK_YELLOW;
        }else{
            if(colorOriginal==ColorCelda.BLANCO)
                color=ColorCelda.LIGHT_RED;
            else
                color=ColorCelda.DARK_RED;
        }
    }

    public void resaltarJaque(){
        color=ColorCelda.JAQUE;
    }

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

    public enum ColorCelda {
        BLANCO(Attribute.BACK_COLOR(229, 186, 115)),
        NEGRO(Attribute.BACK_COLOR(57, 81, 68)),
        LIGHT_YELLOW(Attribute.BACK_COLOR(255, 239, 130)),
        DARK_YELLOW(Attribute.BACK_COLOR(239, 211, 69)),
        LIGHT_RED(Attribute.BACK_COLOR(255, 0, 0 )),
        DARK_RED(Attribute.BACK_COLOR(206, 40, 40)),
        JAQUE(Attribute.BACK_COLOR(132, 210, 197));

        public Attribute attribute;

        ColorCelda(Attribute attribute) {
            this.attribute = attribute;
        }
    }
}
