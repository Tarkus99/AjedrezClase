package es.ieslavereda.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    Color w, b;

    @BeforeEach
    void initialize(){
        w = Color.WHITE;
        b = Color.BLACK;
    }

    @Test
    void next() {
        assertEquals(w.next(), Color.BLACK);
        assertEquals(b.next(), Color.WHITE);
        assertEquals(b.next().next().next(), Color.WHITE);
        assertEquals(w.next().next(), Color.WHITE);
    }
}