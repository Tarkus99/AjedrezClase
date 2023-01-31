package es.ieslavereda.model.DobleEnlace;

import es.ieslavereda.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ListaPiezaDobleEnlaceTest {

    private static Tablero t;

    @BeforeAll
    public static void init(){
        t = new Tablero(null);
    }

//    @Test
//    void addHead() {
//        ListaPiezaDobleEnlace lista = new ListaPiezaDobleEnlace();
//        lista.addTail(new ReinaNegra(t.getCelda(new Coordenada('a', 2))));
//        lista.addTail(new ReinaBlanca(t.getCelda(new Coordenada('c', 4))));
//        lista.addTail(new TorreNegra(t.getCelda(new Coordenada('h', 4))));
//
//        assertDoesNotThrow(() -> {
//            System.out.println(lista);
//            System.out.println(lista.toStringFromTail());
//        });
//    }

//    @Test
//    int count(ReinaNegra r){
//        ListaPiezaDobleEnlace lista = new ListaPiezaDobleEnlace();
//        lista.addHead(new ReinaNegra(t.getCelda(new Coordenada('a', 2))));
//        lista.addHead(new ReinaBlanca(t.getCelda(new Coordenada('c', 4))));
//        lista.addHead(new TorreNegra(t.getCelda(new Coordenada('h', 4))));
//
//        assertEquals(1, count(r));
//        return 0;
//    }


    @Test
    void count(){
        ListaPiezaDobleEnlace lista = new ListaPiezaDobleEnlace();
        lista.addTail(new ReinaNegra(t.getCelda(new Coordenada('a', 2))));
        lista.addTail(new ReinaBlanca(t.getCelda(new Coordenada('c', 4))));
        lista.addTail(new TorreNegra(t.getCelda(new Coordenada('h', 4))));

       assertAll(new Executable() {
           @Override
           public void execute() throws Throwable {
               assertEquals(1, lista.count(Pieza.PieceType.BLACK_REINA));
               assertEquals(1, lista.count(Pieza.PieceType.WHITE_REINA));
               assertEquals(0, lista.count(Pieza.PieceType.BLACK_PEON));
           }
       });
    }
}