package controller;

import model.MapejatTeclesPiano;
import model.ProjectManager;
import view.Vista_Config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *  controlador de la finestra gràfica que fa el mapejat del teclat amb el piano
 */
public class ConfigController extends KeyAdapter implements ActionListener {

    private Vista_Config vc;
    private ControladorPianoMouse cpm;
    private MapejatTeclesPiano mtp;
    private int index;
    private ProjectManager pm;

    /**
     * constructor de la classe controlador de la configuració del teclat
     * @param vc per controlar la finestra gràfica corresponent
     * @param cpm per retornar a la finestra del piano
     * @param mtp model on es guarda la correspondència entre les tecles i les notes del piano
     */
    public ConfigController(Vista_Config vc, ControladorPianoMouse cpm, MapejatTeclesPiano mtp, ProjectManager pm){
        this.vc = vc;
        this.cpm = cpm;
        this.mtp = mtp;
        this.pm = pm;
        index = 0;
        ferMappejat();
    }

    /**
     * funció que avança en la creació del mapejat i s'atura quan arriba al final
     */
    public void ferMappejat() {
        if (index == mtp.getNotes().length) {
            vc.missatgeAcabat();
        } else {
            vc.setNota(mtp.getValorNota(index));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            //Guardem el nou mapejat
            case "GUARDA":
                cpm.setMapejatTeclat(mtp);
                pm.setMtp(mtp);
                vc.setVisible(false);
                break;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        mtp.setNovaTecla(index, e.getKeyCode());
        vc.setLlista(mtp.toStrings());
        index++;
        ferMappejat();
    }
}
