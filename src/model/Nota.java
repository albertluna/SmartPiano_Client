package model;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.*;

@SuppressWarnings("javadoc")

/**
 * classe que guarda la informació de les notes d'una cançó
 */
public class Nota {

    /**
     * @atribut: nota Nom de la nota
     * @atribut: inici Instant d'inici de la nota
     * @atribut: fi Instant de fi de la nota
     */
    private String nota;
    private int inici;
    private int fi;

    /**
     * Constructor d'una nota
     * @param nota nom de la nota
     * @param inici instant que es comença a tocar
     */
    public Nota(String nota, int inici) {
        this.nota = nota;
        this.inici = inici;
    }

    /**
     * Setter del final d'una nota
     * @param inici milisegon que s'acaba de tocar la nota
     */
    public void setFi(LocalTime inici) { fi = (int)inici.until(LocalTime.now(), MILLIS); }

    /**
     * Getter d'una nota
     * @return valor de la nota
     */
    public String getNota() { return nota; }

    /**
     * Getter de l'inici d'una nota
     * @return milisegon en què s'ha començat a tocar la nota
     */
    public int getInici() { return inici; }

    /**
     * Getter del fi d'una nota
     * @return milisegon en què s'ha acabat de tocar la nota
     */
    public int getFi() { return fi; }

    /**
     * Mètode per a passar la nota a cadena amb el seu inici i fi
     * @return string de la nota
     */
    @Override
    public String toString() { return nota + " " + inici + " " + fi;}

}