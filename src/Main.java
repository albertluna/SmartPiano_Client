/**
 * El client de l'SmartPiano és el programa amb el què interactua l'usuari. Aquest el permetrà iniciar sessió,
 * registrar-se, tocar el piano, gravar cançons, reproduir-les, afegir amistats, eliminar la conta o bé tancar sessió.
 *
 * @author: Grup C7 (Albert Ribas, Marcos Abrines, Aleix Ramon, Albert Luna i Anna Noguer)
 * @since: 18 Març 2019
 * @version: 1.0
 */

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import controller.*;
import model.ProjectManager;
import model.ReadJSON;
import network.ServerCommunication;
import view.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * MAIN del client del programa SmartPiano
 */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                Gson gsonDB = new Gson();
                //Llegim el fitxer de configuració
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader("fitxers/config.json"));
                    ReadJSON rj = gsonDB.fromJson(reader, ReadJSON.class);

                    //Creem el model de l'Smart Piano
                    ProjectManager model = new ProjectManager();
                    Vista_IniciarRegistrar_Sessio vista_iniciarRegistrar_sessio = new Vista_IniciarRegistrar_Sessio();
                    ServerCommunication sCommunication = new ServerCommunication(rj.getIp(), rj.getPort());

                    //Creem la comunicació amb el servidor i intentem connectar-nos-hi
                    try{
                        sCommunication.connect();
                        //Mostrem la pantalla de registre o inici de sessió
                        LogController logController = new LogController(vista_iniciarRegistrar_sessio, model, sCommunication);
                        vista_iniciarRegistrar_sessio.registerController(logController);
                        vista_iniciarRegistrar_sessio.setVisible(true);

                    } catch (IOException e){
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
