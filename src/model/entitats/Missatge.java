package model.entitats;

import java.io.Serializable;

@SuppressWarnings("javadoc")

/**
 * classe que s'usarà sempre com a paquet de dades que s'envia al servidor
 */
public class Missatge implements Serializable {

    /**
     * @atribut: tipus String amb el tipus de missatge
     * @atribut: json String amb la informació del missatge
     */
    private String tipus;
    private String json;

    /**
     * Constructor de la classe missatge
     * @param tipus comanda de la informació que s'envia
     * @param json missatge transformat en json
     */
    public Missatge(String tipus, String json){
        this.tipus = tipus;
        this.json = json;
    }

    /**
     * Getter del json del missatge
     * @return les dades enviades
     */
    public String getJson() {
        return json;
    }

    /**
     * Getter del tipus de missatge
     * @return la comanda de l'enviament
     */
    public String getTipus() {
        return tipus;
    }

}
