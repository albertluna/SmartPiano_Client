package model;

import java.util.LinkedList;

@SuppressWarnings("javadoc")

/**
 * classe que conté tota la informació d'una cançó
 */
public class Canco {

    /**
     * @atribut: idCanco Identificador de la cançó
     * @atribut: nomCanco nom de la Cançó
     * @atribut: codiAutora codi de l'autora de la cançó
     * @atribut: isPrivate Booleà per saber si la cançó és privada
     * @atribut: numReproduccions Nombre de reproduccions de la cançó
     * @atribut: notes Llista de notes amb la seva duració
     */
    private int idCanco;
    private String nomCanco;
    private String codiAutora;
    private boolean isPrivate;
    private int numReproduccions;
    private LinkedList<Nota> notes;

    /**
     * construcotr de la classe
     * @param nomCanco nom de la cançó
     * @param codiAutora codi d'amistat de l'usuari que l'ha creat
     * @param isPrivate la cançó és privada o pública
     * @param notes llista de notes que conté la cançó
     */
    public Canco(String nomCanco, String codiAutora, boolean isPrivate, LinkedList<Nota> notes) {
        this.nomCanco = nomCanco;
        this.codiAutora = codiAutora;
        this.isPrivate = isPrivate;
        this.notes = notes;
    }

    /**
     * getter de les notes de la cançó
     * @return la llista de notes de la cançó
     */
    public LinkedList<Nota> getNotes() { return notes; }

    /**
     * getter del nom de la cançó
     * @return el nom de la cançó
     */
    public String getNomCanco() {
        return nomCanco;
    }

    /**
     * getter del codi de l'autora de la cançó
     * @return codi d'amistat de l'autor de la cançó
     */
    public String getCodiAutora() {
        return codiAutora;
    }

    /**
     * getter del booleà per a saber si la cançó és o no privada
     * @return si la cançó és privada o pública
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * getter del nombre de reproduccions de la cançó
     * @return nombre total de reproduccions de la cançó
     */
    public int getNumReproduccions() { return numReproduccions; }
}