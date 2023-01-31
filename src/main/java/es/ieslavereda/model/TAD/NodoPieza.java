package es.ieslavereda.model.TAD;

import es.ieslavereda.model.Pieza;

public class NodoPieza {

    private Pieza pieza;
    private NodoPieza next;

    public NodoPieza(Pieza p){
        pieza=p;
        next=null;
    }

    public Pieza getPieza(){
        return pieza;
    }

    public NodoPieza getNext() {
        return next;
    }

    public void setNext(NodoPieza next) {
        this.next = next;
    }

    @Override
    public String toString(){
        return pieza.soloForma() + ((next!=null)?","+next:"}");
    }
}
