package es.ieslavereda.model.TAD;

import es.ieslavereda.model.Coordenada;

public class NodoCoord {

    private Coordenada coordenada;
    private NodoCoord next;

    public NodoCoord(Coordenada c){
        coordenada= c;
        next=null;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public NodoCoord getNext() {
        return next;
    }

    public void setNext(NodoCoord next){
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NodoCoord){
            NodoCoord aux = (NodoCoord)obj;
            return aux.getCoordenada().equals(coordenada);
        }
        return false;
    }

    @Override
    public String toString(){
        return
                coordenada.toString() + ((next!=null)? " " + next.toString():" ");
    }
}
