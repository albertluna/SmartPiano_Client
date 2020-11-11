package controller;

import model.Gravacio;
import model.ProjectManager;
import model.entitats.Missatge;
import network.ServerCommunication;
import view.Vista_EnregistrarCanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import com.google.gson.*;

@SuppressWarnings("javadoc")

/**
 * controlador de la finestra que guarda la cançó creada i l'envia al servidor
 */
public class EnregistrarCancoController implements ActionListener {

    /**
     * @atribut: ec Vista de guardar una cançó
     * @atribut: Gestor del model del programa client de l'SmartPiano
     * @atribut: gravacio Gravacio de la cançó
     * @atribut: sc Comunicació amb el servidor
     */
    private Vista_EnregistrarCanco ec;
    private ProjectManager pm;
    private Gravacio gravacio;
    private ServerCommunication sc;

    /**
     * constructor de la classe per al controlador de la vista de guardar una gravació
     * @param sc per establir una connexio amb el servidor
     * @param ec per controlar la finestra gràfica corresponent
     * @param pm conté l'usuari que està fent servir l'aplicació
     * @param gravacio conté la cançó creada
     */
    public EnregistrarCancoController(ServerCommunication sc, Vista_EnregistrarCanco ec, ProjectManager pm, Gravacio gravacio) {
        this.ec = ec;
        this.pm = pm;
        this.gravacio = gravacio;
        this.sc = sc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ENREGISTRAR":
                if(ec.getText().contains("'")){
                    ec.showErrorNom();
                } else {
                    //Es guarda a la canco en gravacio tota la informacio extreta de la finestra enregistrar canco
                    try {

                        //S'envia la canco que s'acaba de registrar al servidor
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        sc.connect();
                        sc.send(new Missatge("NOVA_CANCO",
                                gson.toJson(gravacio.fiGravacio(ec.getText(), pm.getUsuari().getCodiAmistat(), ec.cancoEsPrivada()))));
                        Missatge resposta = sc.registerAnswer();
                        if (resposta.getTipus().equals("CANCO_OK")) {
                            ec.registreOK();
                        } else {
                            ec.registreKO();
                        }
                        ec.setVisible(false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "CANCELLAR":
                //No es guarda la cançó
                ec.setVisible(false);
                break;
        }
    }
}
