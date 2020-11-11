package controller;

import model.ProjectManager;
import model.Reproduir;
import model.entitats.Missatge;
import network.ServerCommunication;
import view.Vista_Cancons;
import view.Vista_Menu;
import view.Vista_Piano;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import com.google.gson.Gson;

@SuppressWarnings("javadoc")

/**
 * controlador de la finestra gràfica on es veuen totes les cançons que pot accedir l'usuari i quina vol reproduir
 */
public class CanconsController implements ActionListener {


    /**
     * @atribut: view Vista de cançons
     * @atribut: pm Gestor del model del programa
     * @atribut: sc Comunicació amb el servidor
     */
    private Vista_Cancons view;
    private ProjectManager pm;
    private ServerCommunication sc;

    /**
     * constructor de la classe controlador de la vista cançons
     * @param sc per establir connexio amb el servidor
     * @param view per controlar la finestra gràfica corresponent
     * @param pm conté l'usuari que està fent servir l'aplicació
     */
    public CanconsController(ServerCommunication sc, Vista_Cancons view, ProjectManager pm){
        this.view = view;
        this.pm = pm;
        this.sc = sc;
        try {
            //demanem la llista de cançons disponibles per a l'usuari
            sc.send(new Missatge("LLISTA_CANCONS", (new Gson()).toJson(pm.getUsuari().getCodiAmistat())));
            sc.llegirMissatge();
            view.setLlistaCancons(sc.getCancons(), sc.getNicknames());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode per a quan l'usuari realitza una acció sobre la vista
     * @param e Event realitzat
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ACCEPTA":
                //Ha d'haver connexio amb el servidor
                //Enviament al servidor de la canco seleccionada i retorn del fitxer amb tota la info per reproduir-se
                try {
                    if(view.getSeleccioCanco() == -1){
                        view.errorSeleccio();
                    } else {
                        sc.send(new Missatge("REPRODUIR_CANCO", (new Gson()).toJson(sc.getCancons()[view.getSeleccioCanco()])));
                        sc.llegirMissatge();
                        //Creem una nova vista del piano per a reproduir la cançó
                        Vista_Piano vistaPiano = new Vista_Piano();
                        ControladorPianoMouse controladorMouse = new ControladorPianoMouse(vistaPiano, pm, sc);
                        controladorMouse.setMapejatTeclat(pm.getMtp());
                        ControladorPianoTeclat cpt = new ControladorPianoTeclat(vistaPiano, controladorMouse);
                        vistaPiano.assignarControladorTecles(cpt);
                        vistaPiano.assignarControladorMouse(controladorMouse);
                        vistaPiano.setVisible(true);
                        view.setVisible(false);
                        //Demanem a l'usuari si vol o no activar el so
                        int so = vistaPiano.preguntaSo();
                        //Reproduïm la cançó
                        Reproduir reproduir = new Reproduir(controladorMouse, sc.getCanco(), pm.getMtp(), so);
                        reproduir.reproduir();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case "CANCELA":
                //Tornem al menú
                Vista_Menu vm = new Vista_Menu();
                vm.menuController(new MenuController(vm, pm, sc));
                vm.setJlBenvinguda(pm.getUsuari().getNom());
                vm.setVisible(true);
                view.setVisible(false);
                break;

        }
    }
}
