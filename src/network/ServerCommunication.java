package network;

import com.google.gson.Gson;
import model.Canco;
import model.entitats.Missatge;
import model.Usuari;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@SuppressWarnings("javadoc")

/**
 * Classe que s'encarrega de la gestió de la comunicació amb el servidor
 */
public class ServerCommunication {

    /**
     * @atribut: ip IP de la connexió del servidor
     * @atribut: port Port per a la connexió al servidor
     * @atribut: oos ObjectOutputStream per a enviar dades
     * @atribut: ois ObjectInputStream per a rebre dades
     * @atribut: socket Socket de connexió servidor-client
     * @atribut: gson
     * @atribut: cancons Array de cancons rebudes
     * @atribut: nicknames Array de nicknames rebuts
     * @atribut: canco Cançó enviada per a reproduir
     */
    private final String ip;
    private final int port;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    private Gson gson;
    private Canco[] cancons;
    private String[] nicknames;
    private Canco canco;

    /**
     * constructor de la classe de connexió amb el servidor
     * @param ip ip del servidor
     * @param port port del servidor
     */
    public ServerCommunication(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    /**
     * Mètode que obre la connexió amb el servidor
     * @throws IOException
     */
    public void connect() throws IOException {
        socket = new Socket(ip, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        gson = new Gson();
    }

    /**
     * Mètode que tanca la connexió amb el servidor
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socket.close();
    }

    /**
     * Mètode que envia l'usuari al servidor quan inicia sessió
     * @param u usuari
     */
    public void sendLogin(Usuari u) {
        try {
            Missatge m = new Missatge("LOGIN", gson.toJson(u));
            // enviem cadena amb login
            //SI EL LOGIN ES CORRECTE AFEGIM CONNEXIÓ
            oos.writeObject(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode que envia l'usuari al servidor quan es registra
     * @param u usuari
     */
    public void sendRegister(Usuari u){
        try{
            //enviem cadena amb info registre
            Missatge m = new Missatge("REGISTRE", gson.toJson(u));
            oos.writeObject(m);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que indica al servidor una nova amistat
     * @param codis els dos codis d'amistat que s'acaben de fer amics
     */
    public void sendAmistat (String[] codis){
        try{
            //enviem cadena amb els dos codis d'amistat
            Missatge m = new Missatge("AMISTAT", gson.toJson(codis));
            oos.writeObject(m);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que rep una resposta del servidor
     * @return missatge que s'ha rebut del servidor
     */
    public Missatge registerAnswer(){
        try {
            return (Missatge) ois.readObject();
        } catch (IOException|ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mètode que envia un missatge al servidor
     * @param m missatge que s'envia al servidor
     * @throws IOException
     */
    public void send(Missatge m) throws IOException {
        oos.writeObject(m);
    }

    /**
     * Mètode que envia al servidor un usuari que es vol enviar al sistema
     * @param u usuari que es vol eliminar del sistema
     */
    public void eliminaUsuari(Usuari u){
        try{
            oos.writeObject(new Missatge("ELIMINA", gson.toJson(u)));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que tanca la connexió amb el servidor
     */
    public void desconnectar(){
        try{
            disconnect();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * llegeix el missatge que s'ha rebut del servidor i es fan les gestions pertinents
     */
    public void llegirMissatge() {
        try {
            Gson gson = new Gson();

            Missatge message = (Missatge) ois.readObject();
            switch (message.getTipus()) {
                case "LLISTA_CANCONS":
                    cancons = gson.fromJson(message.getJson(), Canco[].class);
                    int i = 0;
                    nicknames = new String[cancons.length];
                    for(Canco c: cancons){
                        send(new Missatge("NOM_AUTORA", gson.toJson(c.getCodiAutora())));
                        nicknames[i] = gson.fromJson(registerAnswer().getJson(), String.class);
                        i++;
                    }
                    break;
                case "REPRODUIR_CANCO":
                    canco = gson.fromJson(message.getJson(), Canco.class);
                    break;
            }
        } catch (IOException|ClassNotFoundException e) {

        }
    }

    /**
     * getter  de la llista de cançons rebuda
     * @return array de cançons
     */
    public Canco[] getCancons() { return cancons; }

    /**
     * getter de la llista de nicknames rebuts
     * @return array de codis d'amistat
     */
    public String[] getNicknames(){
        return nicknames;
    }

    /**
     * getter de la cançó enviada
     * @return canço determinada
     */
    public Canco getCanco() { return canco; }
}