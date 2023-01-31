package es.ieslavereda.model.DobleEnlace;

import es.ieslavereda.model.Pieza;

public class ListaPiezaDobleEnlace {

    private int size;
    private Nodo cabeza;
    private Nodo cola;

    public ListaPiezaDobleEnlace(){
        size=0;
        cabeza=null;
        cola=null;
    }

    public boolean isEmpty(){
        return cabeza==null;
    }

    public void addHead(Pieza p){
        Nodo aux = new Nodo(p);
        if (cabeza==null){
            cabeza=aux;
            cola=aux;
        }else{
            aux.setNext(cabeza);
            cabeza.setPrevious(aux);
            cabeza=aux;
        }
        size++;
    }

    public void addTail(Pieza p){
        Nodo aux = new Nodo(p);
        if (cola==null){
            cola=aux;
            cabeza=aux;
        }else{
            aux.setPrevious(cola);
            cola.setNext(aux);
            cola=aux;
        }
        size++;
    }

    public Pieza removeHead(){
        if (isEmpty()) return null;
        Pieza p = cabeza.getInfo();
        if (size==1){
            cabeza=null;
            cola=null;
        }else {
            cabeza = cabeza.getNext();
            cabeza.setPrevious(null);
        }
        size--;
        return p;
    }

    public Pieza removeTail(){
        if (isEmpty()) return null;
        Pieza p = cola.getInfo();
        if (size==1){
            cabeza=null;
            cola=null;
        }else {
            cola = cola.getPrevious();
            cola.setNext(null);
        }
        size--;
        return p;
    }

    public int count(Pieza.PieceType pieceType){
        int contador = 0;
        Nodo aux = cabeza;
        while (aux!=null){
            if (aux.getInfo().getType()==pieceType)
                contador++;
            aux=aux.getNext();
        }
        return contador;
    }


    @Override
    public String toString(){
        String str = "size: " + size + ", values: ";
        Nodo aux = cabeza;
        while (aux!=null){
            str+=aux + " ";
            aux = aux.getNext();
        }
        return str;
    }

    public String toStringFromTail(){
        String str = "size: " + size + ", values: ";
        Nodo aux = cola;
        while (aux!=null){
            str+=aux + " ";
            aux = aux.getPrevious();
        }
        return str;
    }
}
