package controller;

import model.ProjectManager;
import network.ServerCommunication;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("javadoc")

/**
 * controlador de la finestrà gràfica del menú principal del programa
 */
public class MenuController implements ActionListener{
    /**
     * @atribut: vista_menu Vista del menú
     * @atribut: pm Gestor del model del projecte
     * @atribut: sc Comunicació amb el servidor
     */
    private Vista_Menu vista_menu;
    private ProjectManager pm;
    private ServerCommunication sc;

    /**
     * constructor de la classe del controlador del menú
     * @param vista_menu per controlar la finestra gràfica corresponent
     * @param pm conté l'usuari que està fent servir l'aplicació
     * @param sc per establir connexio amb el servidor
     */
    public MenuController(Vista_Menu vista_menu, ProjectManager pm, ServerCommunication sc) {
        this.vista_menu = vista_menu;
        this.pm = pm;
        this.sc = sc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "TOCAR_EL_PIANO":

                //Nova vista del piano
                Vista_Piano vistaPiano = new Vista_Piano();
                ControladorPianoMouse controladorMouse = new ControladorPianoMouse(vistaPiano, pm, sc);
                vistaPiano.assignarControladorMouse(controladorMouse);

                ControladorPianoTeclat controladorTeclat = new ControladorPianoTeclat(vistaPiano, controladorMouse);
                vistaPiano.assignarControladorTecles(controladorTeclat);
                vistaPiano.setVisible(true);
                vista_menu.setVisible(false);
                break;

            case "REPRODUIR_CANCO":

                //Nova vista per a reproduir una cançó
                Vista_Cancons vista_cancons = new Vista_Cancons();
                CanconsController cc = new CanconsController(sc, vista_cancons, pm);
                vista_cancons.registraControlador(cc);
                vista_cancons.setVisible(true);
                vista_menu.setVisible(false);

                break;

            case "AFEGIR_AMIGUES":

                //Nova vista per a afegir amistats
                Vista_AfegirAmics vista_afegirAmics = new Vista_AfegirAmics(pm.getUsuari().getCodiAmistat());
                AmicsController ac = new AmicsController(vista_afegirAmics, vista_menu, pm, sc);
                vista_afegirAmics.amicsController(ac);
                vista_afegirAmics.setVisible(true);
                vista_menu.setVisible(false);

                break;

            case "TANCAR_SESSIO":

                //Tanquem sessió i tornem a la pantalla d'inici de sessió o registre
                Vista_IniciarRegistrar_Sessio vista_iniciarRegistrar_sessio = new Vista_IniciarRegistrar_Sessio();
                vista_iniciarRegistrar_sessio.registerController(new LogController(vista_iniciarRegistrar_sessio, pm, sc));
                //Reiniciem el model del gestor del piano
                pm.setUsuari(null);
                LogController logController = new LogController(vista_iniciarRegistrar_sessio, pm, sc);
                vista_iniciarRegistrar_sessio.registerController(logController);
                vista_iniciarRegistrar_sessio.setVisible(true);
                vista_menu.setVisible(false);
                break;

            case "ELIMINAR_COMPTE":
                //Eliminem la conta i tornem a la pantalla de registre o inici de sessió
                sc.eliminaUsuari(pm.getUsuari());
                Vista_IniciarRegistrar_Sessio vista_iniciarRegistrar_sessio1 = new Vista_IniciarRegistrar_Sessio();
                LogController logController1 = new LogController(vista_iniciarRegistrar_sessio1, pm, sc);
                vista_iniciarRegistrar_sessio1.registerController(logController1);
                vista_iniciarRegistrar_sessio1.setVisible(true);
                vista_menu.setVisible(false);
                break;

        }
    }
}
