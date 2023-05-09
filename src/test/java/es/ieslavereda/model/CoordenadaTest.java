package es.ieslavereda.model;

import es.ieslavereda.model.GAME.Coordenada;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CoordenadaTest {

    @BeforeAll
    static void initialize(){
        System.out.println("Ejecutado beforeAll");
    }

    @BeforeEach
    void before(){
        System.out.println("Ejecutando beforeEach");
    }

    @Test
    @DisplayName("Prueba del método arriba")
    void casillaUp() {
        Coordenada c = new Coordenada('B',  2);
        Coordenada c2 = new Coordenada('B',  1);
        assertEquals(c.casillaUp(), c2);

    }

    @Test
    @DisplayName("Prueba del método abajo")
    void casillaDown() {
        Coordenada c = new Coordenada('B',  1);
        Coordenada c2 = new Coordenada('B',  2);
        assertEquals(c.casillaDown(), c2);
    }

    @Test
    @DisplayName("Prueba del método izquierda")
    void casillaLeft() {
        Coordenada c = new Coordenada('B',  1);
        Coordenada c2 = new Coordenada('a',  1);
        assertEquals(c.casillaLeft(), c2);
    }

    @Test
    @DisplayName("Prueba del método derecha")
    void casillaRight() {
        Coordenada c = new Coordenada('B',  1);
        Coordenada c2 = new Coordenada('C',  1);
        assertEquals(c.casillaRight(), c2);
    }

    @Test
    @DisplayName("Prueba del método up diagonal derecha")
    void diagoUpRight() {
        Coordenada c = new Coordenada('B',  2);
        Coordenada c2 = new Coordenada('C',  1);
        assertEquals(c.diagoUpRight(), c2);
    }

    @Test
    @DisplayName("Prueba del método up diagonal izquierda")
    void diagoUpLeft() {
        Coordenada c = new Coordenada('B',  2);
        Coordenada c2 = new Coordenada('A',  1);
        assertEquals(c.diagoUpLeft(), c2);
    }

    @Test
    @DisplayName("Prueba del método down diagonal rigth")
    void diagoDownRight() {
        Coordenada c = new Coordenada('B',  2);
        Coordenada c2 = new Coordenada('C',  3);
        assertEquals(c.diagoDownRight(), c2);
    }

    @Test
    @DisplayName("Prueba del método down diagonal left")
    void diagoDownLeft() {
        Coordenada c = new Coordenada('B',  1);
        Coordenada c2 = new Coordenada('A',  2);
        assertEquals(c.diagoDownLeft(), c2);
        assertEquals(new Coordenada('E',5).diagoDownLeft(), new Coordenada('d', 6));
    }
}