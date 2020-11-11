package model;

import java.time.LocalTime;
import java.util.LinkedList;
import static java.time.temporal.ChronoUnit.*;

@SuppressWarnings("javadoc")

/**
 * classe que s'encarrega de gestionar la gravació d'una cançó
 */
public class Gravacio {

    /**
     * @atribut: inici Temps d'inici de la gravació
     * @atribut: notes Array de notes a reproduir
     * @atribut: aux Array de notes auxiliar
     */
    private LocalTime inici;
    private LinkedList<Nota> notes;
    private LinkedList<Nota> aux;

    /**
     * constructor de la classe gravació
     * @param inici indica quan es comença a gravar la cançó
     */
    public Gravacio(LocalTime inici) {
        this.inici = inici;
        notes = new LinkedList<>();
        aux = new LinkedList<>();
    }

    /**
     * @param event afegeix una nota a la llista de notes de la gravació
     */
    public void afegirNota(String event) {
        //Candicio per afegir una nota a la llista si el piano esta en mode gravacio
        if (!event.equals("GRAVAR")) aux.add(new Nota(event, (int)inici.until(LocalTime.now(), MILLIS)));
    }

    /**
     * Mètode per a marcar el final d'una nota a la gravació
     * @param event determina en quin milisegon de la gravacio s'acaba de tocar una nota
     */
    public void setFiNota(String event) {

        if (!event.equals("GRAVAR")) {

            for(Nota n: aux){
                n.setFi(inici);
                notes.add(n);
            }

            aux.clear();
        }
    }

    /**
     * Funcio que retorna el la canco creada en un string json
     * @param nomCanco nom de la cançó gravada
     * @param codiAutora codi d'amistat de l'autor/a
     * @param isPrivate cançó privada o pública
     * @return la cançó completada
     */
    public Canco fiGravacio(String nomCanco, String codiAutora, boolean isPrivate) {

        return new Canco(nomCanco, codiAutora, isPrivate, notes);

    }
}
