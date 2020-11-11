package controller;

import com.google.gson.Gson;
import model.ProjectManager;
import model.Usuari;
import model.entitats.Missatge;
import network.ServerCommunication;
import view.Vista_IniciarRegistrar_Sessio;
import view.Vista_Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("javadoc")

/**
 * controlador de la finestra gràfica on es pot registrar on iniciar sessió
 */
public class LogController implements ActionListener {

    /**
     * @atribut: view Vista d'iniciar sessió o registrar-se
     * @atribut: model Gestor del model del programa client de l'SmartPiano
     * @atribut: sCommunication Comunicació amb el servidor
     */
    private Vista_IniciarRegistrar_Sessio view;
    private ProjectManager model;
    private ServerCommunication sCommunication;

    /**
     * constructor de la classe controlador de l'inici de sessió o registre
     * @param view per controlar la finestra gràfica corresponent
     * @param model conté l'usuari que està fent servir l'aplicació
     * @param sCommunication per establir connexio amb el servidor
     */
    public LogController(Vista_IniciarRegistrar_Sessio view, ProjectManager model, ServerCommunication sCommunication) {
        this.view = view;
        this.model = model;
        this.sCommunication = sCommunication;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGIN":
                Gson gson = new Gson();
                Usuari u = new Usuari(view.getLogin(), view.getContrasenya());
                //Comprovem si el login és correcte
                sCommunication.sendLogin(u);
                Missatge m = sCommunication.registerAnswer();

                if(m.getTipus().equals("LOGIN OK")) {
                    //El login és correcte per tant iniciem sessió a l'usuari i li enviem la informació necessària
                    model.setUsuari(gson.fromJson(m.getJson(), Usuari.class));
                    Vista_Menu menu = new Vista_Menu();
                    MenuController mc = new MenuController(menu, model, sCommunication);
                    menu.menuController(mc);
                    menu.setJlBenvinguda(model.getUsuari().getNom());
                    menu.setVisible(true);
                    view.setVisible(false);

                } else if (m.getTipus().equals("LOGIN KO")){
                    //Error en el login
                    view.loginError();
                }
                break;

            case "REGISTER":
                //Comprovem que el registre compleixi els requisits necessaris
                if (view.getRegisterContrasenya().equals("")|| view.getRegisterEmail().equals("")||view.getRegisterNom().equals("")||view.getRegisterConfirmacio().equals("")){
                    view.jtreset();
                    view.emptyError();
                } else if (!view.getRegisterEmail().contains("@")){
                    view.jtreset();
                    view.emailError();
                } else if (!view.getRegisterContrasenya().equals(view.getRegisterConfirmacio())) {
                    view.contrassenyesError();
                } else if (!(view.getRegisterContrasenya().length() >= 8) ||!view.getRegisterContrasenya().matches("(?=.*[0-9]).*") || !view.getRegisterContrasenya().matches("(?=.*[A-Z]).*")|| !view.getRegisterContrasenya().matches("(?=.*[a-z]).*")){
                    view.caractersError();
                } else {
                    //Creem un nou usuari i comprovem que el seu nickname i correu no existeixin
                    Usuari user = new Usuari(view.getRegisterNom(), view.getRegisterEmail(), view.getRegisterContrasenya());
                    sCommunication.sendRegister(user);
                    Missatge m2 = sCommunication.registerAnswer();
                    if (m2.getTipus().equals("REGISTRE ERROR")) {
                        //Error al registre ja que algun dels camps únics ja existeixen
                        view.registerError();
                    } else {
                        //Usuari registrat correctament
                        view.registerOK();
                    }
                }
                break;
        }
    }
}
