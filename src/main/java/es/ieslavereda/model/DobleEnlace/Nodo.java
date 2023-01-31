package es.ieslavereda.model.DobleEnlace;

import es.ieslavereda.model.Pieza;

public class Nodo {

    private Pieza info;
    private Nodo next;
    private Nodo previous;

    public Nodo(Pieza p){
        info=p;
        next=null;
        previous=null;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public void setPrevious(Nodo previous) {
        this.previous = previous;
    }

    public Nodo getNext() {
        return next;
    }

    public Nodo getPrevious() {
        return previous;
    }

    public Pieza getInfo() {
        return info;
    }

    @Override
    public String toString(){
        return info.toString();
    }


}
