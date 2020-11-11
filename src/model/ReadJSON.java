package model;

@SuppressWarnings("javadoc")

/**
 * classe en què es guarda tota la informació del fitxer json 'config.json'
 */
public class ReadJSON {

    /**
     * @atribut: ip Direcció ip de la connexió del servidor
     * @atribut: port Port de connexió del servidor
     */
    private String ip;
    private int port;

    /**
     * Getter de la ip del servidor
     * @return la ip del servidor
     */
    public String getIp() {
        return ip;
    }

    /**
     * Getter del port de connexió del servidor
     * @return el port del servidor
     */
    public int getPort() {
        return port;
    }
}
