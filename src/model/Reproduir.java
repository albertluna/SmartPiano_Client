package model;

import controller.ControladorPianoMouse;
import view.Vista_Piano;

import javax.swing.*;
import java.time.LocalTime;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.MILLIS;

@SuppressWarnings("javadoc")

/**
 * Classe que s'encarrega de reproduir la cançó seleccionada
 */
public class Reproduir extends Thread {

    /**
     * @atribut: cpm Controlador del piano pel mouse
     * @atribut: canco Cançó a reproduir
     * @atribut: map Hashmap que relaciona la nota amb un boolea per a saber si s'està reproduïnt
     * @atribut: mtp Mapejat de les tecles del piano
     * @atribut: so Integer per saber si ha de sonar o no la cançó
     */
    private ControladorPianoMouse cpm;
    private Canco canco;
    private HashMap <Nota, Boolean> map;
    private MapejatTeclesPiano mtp;
    private int so;

    /**
     * constructor de la classe reproduir
     * @param cpm controlador del piano
     * @param c Cançó a reproduir
     * @param mtp Mapejat de les tecles del piano
     * @param so Enter per a saber si s'ha de silenciar o no la cançó
     */
    public Reproduir(ControladorPianoMouse cpm, Canco c, MapejatTeclesPiano mtp, int so) {
        this.cpm = cpm;
        this.canco = c;
        this.map = new HashMap<>();
        this.mtp = mtp;
        this.so = so;
    }

    /**
     * Funció per a iniciar la reproducció de la cançó
     */
    public void reproduir() {
        start();
    }

    /**
     * Funcio que reprodueix la canco alhora que es mostra el piano
     */
    @Override
    public void run() {
        LocalTime inici = LocalTime.now();
        LocalTime ara = LocalTime.now();

        //Bucle per anar reproduint les notes mentre duri la canco
        while (inici.until(ara, MILLIS) <= canco.getNotes().get(canco.getNotes().size()-1).getFi()) {
            for (Nota n: canco.getNotes()) {
                ara = LocalTime.now();
                map.put(n, false);

                //Es toca la nota si esta en el temps que s'ha de tocar
                if ((inici.until(ara, MILLIS) - n.getInici() >= 0) && (n.getFi() - inici.until(ara, MILLIS) >= 0)) {
                    if(inici.until(ara, MILLIS) - n.getInici() == 0){
                        map.put(n, true);
                        if(so == 0) {
                            cpm.tocarNota(n.getNota());
                        } else{
                            cpm.pintaNota(n.getNota());
                        }
                    }
                    if(n.getFi() - inici.until(ara, MILLIS) <= 0){
                        map.put(n, false);
                        if(so == 0) {
                            cpm.offNota(n.getNota());
                        } else {
                            cpm.despintaNota(n.getNota());
                        }
                    }
                }

                //Comprovem la partitura que haurà de mostrar-se cada 200 milisegons (fins a 10 * 200 milisegons abans)
                if(inici.until(ara, MILLIS)%200 == 0) {
                    cpm.resetPartitura();
                }
                for (int i = 0; i < 10; i++) {
                    //Mirem fins a 2 segons abans per mostrar a la partitura la nota
                    if (inici.until(ara.plus((200 * i), MILLIS), MILLIS) - n.getInici() >= 0 && n.getFi() - inici.until(ara.plus((200*i), MILLIS), MILLIS) >= 0) { //ara -Inici <= 2000;
                        cpm.actualitzanota(9-i, mtp.getPosicioOrdre(n.getNota()));
                    }
                }

            }
        }
        cpm.resetPartitura();

    }

}