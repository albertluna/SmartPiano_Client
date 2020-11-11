package model;

@SuppressWarnings("javadoc")

/**
 * classe que s'encarregar de gestionar la correspondència entre les tecles i les notes del piano
 */
public class MapejatTeclesPiano {

    /**
     * @atribut: notes Array de notes en forma de cadena
     * @atribut: tecles Array d'enters corresponents a les tecles
     * @atribut: posicio Array d'enters corresponents a la posició de les tecles
     */
    private final String[] notes;
    private int[] tecles;
    private int[] posicio;

    /**
     * Constructor del mapejat de les tecles del piano per defecte
     */
    public MapejatTeclesPiano() {
        notes = new String[24];
        tecles = new int[24];
        posicio = new int[24];

        notes[0] = "Do";
        notes[1] = "Re";
        notes[2] = "Mi";
        notes[3] = "Fa";
        notes[4] = "Sol";
        notes[5] = "La";
        notes[6] = "Si";
        notes[7] = "Do+";
        notes[8] = "Re+";
        notes[9] = "Mi+";
        notes[10] = "Fa+";
        notes[11] = "Sol+";
        notes[12] = "La+";
        notes[13] = "Si+";
        notes[14] = "Do#";
        notes[15] = "Re#";
        notes[16] = "Fa#";
        notes[17] = "Sol#";
        notes[18] = "La#";
        notes[19] = "Do#+";
        notes[20] = "Re#+";
        notes[21] = "Fa#+";
        notes[22] = "Sol#+";
        notes[23] = "La#+";

        posicio[0] = 0;
        posicio[1] = 2;
        posicio[2] = 4;
        posicio[3] = 5;
        posicio[4] = 7;
        posicio[5] = 9;
        posicio[6] = 11;
        posicio[7] = 12;
        posicio[8] = 14;
        posicio[9] = 16;
        posicio[10] = 17;
        posicio[11] = 19;
        posicio[12] = 21;
        posicio[13] = 23;
        posicio[14] = 1;
        posicio[15] = 3;
        posicio[16] = 6;
        posicio[17] = 8;
        posicio[18] = 10;
        posicio[19] = 13;
        posicio[20] = 15;
        posicio[21] = 18;
        posicio[22] = 20;
        posicio[23] = 22;

        //Creem una configuracio inicial per tocar amb el teclat
        tecles[0] = 65; //Do - A
        tecles[1] = 83; //re - S
        tecles[2] = 68; //Mi - D
        tecles[3] = 70; //Fa - F
        tecles[4] = 71; //Sol - G
        tecles[5] = 72; //La - H
        tecles[6] = 74; //Si - J
        tecles[7] = 90; //Do+ - Z
        tecles[8] = 88; //Re+ - X
        tecles[9] = 67; //Mi+ - C
        tecles[10] = 86; //Fa+ - V
        tecles[11] = 66; //Sol+ - B
        tecles[12] = 78; //La+ - N
        tecles[13] = 77; //Si+ - M
        tecles[14] = 81; //Do# - Q
        tecles[15] = 87; //Re# - W
        tecles[16] = 69; //Fa# - E
        tecles[17] = 82; //Sol# - R
        tecles[18] = 84; //La# - T
        tecles[19] = 89; //DO#+ - Y
        tecles[20] = 85; //Re#+ - U
        tecles[21] = 73; //Fa#+ - I
        tecles[22] = 79; //Sol#+ - O
        tecles[23] = 80; //La#+ - P

    }

    /**
     * Getter de la nota segons el valor de la tecla
     * @param valorTecla valor de la tecla del teclat que s'ha clicat
     * @return el nom de la nota que li correspon
     */
    public String getNota(int valorTecla) {

        String nota = "";
        for (int i = 0; i < notes.length; i++) {
            if (valorTecla == tecles[i]) {
                nota = notes[i];
            }
        }
        return nota;
    }

    /**
     * Getter de la posició de la tecla segons la nota
     * @param nota
     * @return posició de la tecla
     */
    public int getPosicio(String nota){
        for(int n = 0; n < 24; n++){
            if (notes[n].equals(nota)){
                return n;
            }
        }
        return -1;
    }

    /**
     * Getter de la posició de la tecla segons l'ordre dels jbuttons de la vista
     * @param nota
     * @return posició ordenada de la tecla
     */
    public int getPosicioOrdre(String nota){
        for(int n = 0; n < 24; n++){
            if (notes[n].equals(nota)){
                return posicio[n];
            }
        }
        return -1;
    }

    /**
     * Getter de totes les notes
     * @return array de notes totals
     */
    public String[] getNotes() { return notes; }

    /**
     * Getter del nom d'una nota segons la seva posició
     * @param posicio index de la nota
     * @return el nom de la nota
     */
    public String getValorNota(int posicio) { return notes[posicio];}

    /**
     * Setter del nou valor de la tecla pel mapejat
     * @param index index de la nota
     * @param valorTecla valor de la tecla del teclat
     */
    public void setNovaTecla(int index, int valorTecla) {
        tecles[index] = valorTecla;
    }

    /**
     * Mètode per a crear una llista de cadenes amb la correspondència de les notes i les seves tecles corresponents del teclat
     * @return transforma la informació del mapejat a string
     */
    public String[] toStrings() {
        String[] data = new String[24];
        for (int i = 0; i < 24; i++) {
            data[i] = notes[i] + " - " + (char)tecles[i];
        }
        return data;
    }
}

