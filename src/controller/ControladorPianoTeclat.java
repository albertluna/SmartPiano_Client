package controller;

import model.Gravacio;
import model.MapejatTeclesPiano;
import view.Vista_Piano;

import javax.sound.midi.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *  controlador de la finestra gràfica del piano mitjançant el teclat
 */
public class ControladorPianoTeclat extends KeyAdapter{

    private Vista_Piano vista;
    private Synthesizer syn;
    private MidiChannel[] midChannel;
    private ControladorPianoMouse cpm;
    private HashMap<Integer, Boolean> keys;

    /**
     * constructor de la classe controlador del piano (teclat)
     * @param vista per controlar la finestra gràfica corresponent
     * @param cpm per tenir connexió amb el controlador del piano teclat
     */
    public ControladorPianoTeclat(Vista_Piano vista, ControladorPianoMouse cpm) {
        // Establim la "relació" C->V

        //Creem un hashmap per a saber si una nota ja està sonant
        keys = new HashMap<>();
        try {
            //Iniciem el sintetitzador midi
            syn = MidiSystem.getSynthesizer();
            syn.open();
            midChannel = syn.getChannels();

        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
        this.vista = vista;
        this.cpm = cpm;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keys.containsKey(keyCode)){
            if (keys.get(keyCode)){
                return;
            }
        }
        keys.put(keyCode, true);
        //Fem sonar la nota
        cpm.tocarNota(cpm.transformarTeclaANota(keyCode));

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keys.containsKey(keyCode)){
            if (keys.get(keyCode)){
                keys.put(keyCode, false);
            }

        }
        //Deixem de fer sonar la nota i en cas que s'estigui gravant, marquem el fi de la nota
        cpm.setFiNota(cpm.transformarTeclaANota(keyCode));
        cpm.offNota(cpm.transformarTeclaANota(keyCode));
    }
}
