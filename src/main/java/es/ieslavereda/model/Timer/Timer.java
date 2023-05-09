package es.ieslavereda.model.Timer;

import es.ieslavereda.model.GAME.Partida;
import javax.swing.*;
import java.io.Serializable;

public class Timer extends JFrame implements Serializable {
    private JLabel minutos;
    private JLabel segundos;
    private JPanel miPanel;
    private JLabel centesimas;
    private Hilo hilo;
    private int time;
    private Partida partida;
    Integer min;
    Integer seg;
    Integer centSeg;

    public Timer(int time, Partida p) {
        super();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(miPanel);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.pack();
        this.time = time;
        partida = p;
    }

    public void start() {
        hilo = new Hilo();
        hilo.start();
        setVisible(true);
    }

    public void stop() {
        hilo.parar();
        setVisible(false);
    }

    private class Hilo extends Thread implements Serializable{
        private boolean exit;
        public Hilo() {
            exit = false;
        }

        @Override
        public void run() {
            int a = (min == null) ? time : min;
            int b = (seg == null) ? 0 : seg;
            int c = (centSeg == null) ? 0 : centSeg;
            int i = a;
            while (i >= 0 && !exit) {
                minutos.setText("" + i);
                int j = b;
                while (j >= 0 && !exit) {
                    segundos.setText("" + j);
                    int k = c;
                    while (k >= 0 && !exit) {
                        seg = j;
                        min = i;
                        centSeg = k;
                        centesimas.setText((k < 10) ? 0 + "" + k : "" + k);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        k--;
                    }
                    c = 99;
                    j--;
                }
                b = 59;
                i--;
            }
            if (!exit)
                partida.finish();
        }

        public void parar() {
            exit = true;

        }
    }
}
