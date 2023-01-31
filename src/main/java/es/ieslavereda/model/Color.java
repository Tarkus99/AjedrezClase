package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

import javax.print.DocFlavor;

public enum Color {

    BLACK(Attribute.TEXT_COLOR(0, 0, 0)),
    WHITE(Attribute.TEXT_COLOR(255,255,255));

    private Attribute color;

    Color(Attribute color){
        this.color = color;
    }

    public Attribute getColor() {
        return color;
    }

    public Color next(){
           //return values()[(ordinal()+1)% values().length];
        if (this.equals(WHITE))
            return BLACK;
        return WHITE;
    }
}
