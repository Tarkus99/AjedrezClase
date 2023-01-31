package es.ieslavereda.model;

public class Coordenada {

    private char col;
    private int row;

    public Coordenada(char letra, int num){
        this.col = Character.toUpperCase(letra);
        this.row = num;
    }

    public char getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Coordenada casillaUp(){
        return new Coordenada(col,(row-1));
    }
    public Coordenada casillaDown(){
        return new Coordenada(col, row+1);
    }
    public Coordenada casillaLeft(){
        return new Coordenada((char)(col-1), row);
    }
    public Coordenada casillaRight(){
        return new Coordenada((char)(col+1), row);
    }
    public Coordenada diagoUpRight(){
        return casillaUp().casillaRight();
    }
    public Coordenada diagoUpLeft(){
        return casillaUp().casillaLeft();
    }
    public Coordenada diagoDownRight(){
        return casillaDown().casillaRight();
    }
    public Coordenada diagoDownLeft(){
        return casillaDown().casillaLeft();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordenada){
            Coordenada c = (Coordenada) obj;
            return col==c.col && row == c.row;
        }
        return false;
    }

    @Override
    public String toString(){
        return "(" + this.col + "," + this.row + ")";
    }
}
