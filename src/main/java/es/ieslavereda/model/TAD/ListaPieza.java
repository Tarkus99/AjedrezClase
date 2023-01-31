package es.ieslavereda.model.TAD;

import es.ieslavereda.model.Coordenada;
import es.ieslavereda.model.Pieza;

public class ListaPieza {

    private int size;
    private NodoPieza cabeza;
    private NodoPieza cola;

    public ListaPieza() {
        size=0;
        cabeza=null;
        cola=null;
    }

    public void add(Pieza p) {
        NodoPieza aux = new NodoPieza(p);
        if (cabeza == null) {
            cabeza = aux;
            cola = aux;
        } else {
            aux.setNext(cabeza);
            cabeza = aux;
        }
        size++;
    }

    public NodoPieza getCabeza(){
        return cabeza;
    }

    public ListaPieza addAll(ListaPieza l){
        NodoPieza aux = l.cabeza;
        while (aux!=null){
            add(aux.getPieza());
            aux = aux.getNext();
        }
        return this;
    }

    public Pieza remove(int pos) {
        if (pos >= size || pos < 0 || isEmpty())
            return null;

        NodoPieza aux = cabeza;
        Pieza value;

        if (pos == 0) {
            value = cabeza.getPieza();
            cabeza = aux.getNext();
            size--;
            return value;
        } else if (pos == (size - 1)) {
            while (pos > 1) {
                aux = aux.getNext();
                pos--;
            }
            value = aux.getNext().getPieza();
            aux.setNext(null);
            size--;
            return value;
        } else {
            while (pos > 1) {
                aux = aux.getNext();
                pos--;
            }
            value = aux.getNext().getPieza();
            aux.setNext(aux.getNext().getNext());
            size--;
            return value;
        }
    }

    public boolean isEmpty() {
        return cabeza == null;
    }

    @Override
    public String toString() {
        return "{ size: " + size + ", Values: " + ((cabeza == null) ? "}" : cabeza);
    }
    public int getSize(){
        return size;
    }
}
