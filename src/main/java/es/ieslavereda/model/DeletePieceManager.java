package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.model.DobleEnlace.ListaPiezaDobleEnlace;

import static com.diogonunes.jcolor.Ansi.RESET;
import static com.diogonunes.jcolor.Ansi.colorize;

public class DeletePieceManager implements IDeletePieceManager{


    private ListaPiezaDobleEnlace listaMuertes;

    public DeletePieceManager(){
        listaMuertes = new ListaPiezaDobleEnlace();
    }


    @Override
    public void addPiece(Pieza p) {
        listaMuertes.addHead(p);
    }

    @Override
    public int count(Pieza.PieceType pieceType) {
       return listaMuertes.count(pieceType);
    }

    @Override
    public Pieza getLast() {
        return listaMuertes.removeHead();
    }

    @Override
    public String toString(){
        String str = "";
        Pieza.PieceType p;
        Attribute a = Attribute.BACK_COLOR(57, 81, 68);
        Attribute b = Attribute.BACK_COLOR(164, 126, 59);
        for (int i = 0; i < Pieza.PieceType.values().length; i++){
            p = Pieza.PieceType.values()[i];
            str+=colorize(" ", a)
                    + colorize(String.valueOf(p), p.color.getColor(), a)
                    + colorize(" ", a);
        }
        str+="\n";

        for (int i = 0; i < Pieza.PieceType.values().length; i++){
            str+=colorize(" ", b) +
                    colorize(String.valueOf(count(Pieza.PieceType.values()[i])), b) +
                    colorize(" ", b);
        }
        str+="\n";
        return str;
    }

}
