package controller;

import com.google.gson.Gson;
import model.ProjectManager;
import model.entitats.Missatge;
import network.ServerCommunication;
import view.Vista_AfegirAmics;
import view.Vista_Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  controlador de la finestra gràfica on es poden afegir amistats
 */
public class AmicsController implements ActionListener{
    private Vista_AfegirAmics vista_afegirAmics;
    private Vista_Menu vista_menu;
    private ProjectManager pm;
    private ServerCommunication sc;

    /**
     * Constructor del controlador de la vista d'afegir amigues
     * @param vista_afegirAmics per controlar la finestra grafica on s'afegeixen noves amistats
     * @param vista_menu per tornar al menu principal
     * @param pm variable que conté l'usuari que està utilitzant el programa
     * @param sc per establir una connexio amb el servidor
     */
    public AmicsController(Vista_AfegirAmics vista_afegirAmics, Vista_Menu vista_menu, ProjectManager pm, ServerCommunication sc){
        this.vista_afegirAmics = vista_afegirAmics;
        this.vista_menu = vista_menu;
        this.pm = pm;
        this.sc = sc;
    }

    /**
     * Mètode per a respondre a les accions que realitza l'usuari sobre la interfície gràfica
     * @param e Event
     */
    @Override
    public void actionPerformed (ActionEvent e){

        switch(e.getActionCommand()){
            case "MENU":
                //Tornem al menú
                vista_menu.setVisible(true);
                vista_afegirAmics.setVisible(false);
                break;
            case "AFEGIR":
                //Provem d'afegir la nova amistat
                String[] codis = new String[2];
                codis[0] = pm.getUsuari().getCodiAmistat();
                codis[1] = vista_afegirAmics.getCodiAmic();
                System.out.println(codis);
                sc.sendAmistat(codis);
                Missatge m = sc.registerAnswer();
                if(m.getTipus().equals("AMISTAT OK")) {
                    vista_afegirAmics.addOK();
                } else if (m.getTipus().equals("AMISTAT EXISTS")){
                    vista_afegirAmics.errorExists();
                } else if(m.getTipus().equals("AMISTAT KO")){
                    vista_afegirAmics.errorKO();
                }
                break;

        }
    }
}
