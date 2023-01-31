package es.ieslavereda.model.TAD;

import es.ieslavereda.model.Coordenada;

public class ListaCoord {

    private int size;
    private NodoCoord cabeza;
    private NodoCoord cola;

    public ListaCoord() {
        size = 0;
        cabeza = null;
        cola = null;
    }

    public ListaCoord(ListaCoord l) {
        addAll(l);
    }

    public void add(Coordenada coordenada) {

        NodoCoord nodo = new NodoCoord(coordenada);

        if (cabeza == null) {
            cabeza = nodo;
            cola = nodo;
        } else {
            nodo.setNext(cabeza);
            cabeza = nodo;
        }
        size++;
    }

    public NodoCoord getCabeza() {
        return cabeza;
    }

    public Coordenada getCoordenada(int pos) {
        if (pos < 0 || pos >= size) return null;

        NodoCoord aux = cabeza;
        while (aux.getNext() != null && pos > 0) {
            aux = aux.getNext();
            pos--;
        }
        return aux.getCoordenada();
    }

    public ListaCoord addAll(ListaCoord l) {
        //boolean modificado = false;
        NodoCoord aux = l.cabeza;
        while (aux != null) {
            if (!contiene(aux.getCoordenada())) {
                //modificado = true;
                add(aux.getCoordenada());
            }
            aux = aux.getNext();
        }
        //return modificado;
        return this;
    }

    public void clear() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    public boolean contiene(Coordenada coordenada) {

        boolean encontrado = false;
        NodoCoord nodAux = cabeza;

        while (nodAux != null && !encontrado) {
            encontrado = nodAux.getCoordenada().equals(coordenada);
            nodAux = nodAux.getNext();
        }
        return encontrado;
    }

    public Coordenada remove(int pos) {
        if (pos >= size || pos < 0 || isEmpty())
            return null;

        NodoCoord aux = cabeza;
        Coordenada value;

         if (pos == 0) {
            value = cabeza.getCoordenada();
            cabeza = aux.getNext();
            size--;
            return value;
        } else if (pos == (size - 1)) {
            while (pos > 1) {
                aux = aux.getNext();
                pos--;
            }
            value = aux.getNext().getCoordenada();
            aux.setNext(null);
            size--;
            return value;
        } else {
            while (pos > 1) {
                aux = aux.getNext();
                pos--;
            }
            value = aux.getNext().getCoordenada();
            aux.setNext(aux.getNext().getNext());
            size--;
            return value;
        }
    }

    public boolean isEmpty() {
        return cabeza == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListaCoord) {
            ListaCoord aux = (ListaCoord) obj;
            if (aux.size != size)
                return false;
            boolean encontrado = false;
            NodoCoord nodAux = cabeza;
            NodoCoord nodAux2 = aux.cabeza;
            while (nodAux != null && !encontrado) {
                if (!nodAux.equals(nodAux2))
                    encontrado = true;

                nodAux = nodAux.getNext();
                nodAux2 = nodAux2.getNext();
            }
            return !encontrado;
        }
        return false;
    }

    @Override
    public String toString() {
        return "{ size: " + size + ", Values: " + ((cabeza == null) ? "}" : cabeza);
    }

}
