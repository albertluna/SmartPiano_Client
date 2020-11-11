package model;

@SuppressWarnings("javadoc")

/**
 * Classe que guarda la informació del client
 */
public class Usuari {

    /**
     * @atribut: Nom Nickname de l'usuari
     * @atribut: Correu Email de l'usuari
     * @atribut: Contrasenya Contrasenya de l'usuari
     * @atribut: CodiAmistat Codi d'Amistat de l'usuari
     */
    private String Nom;
    private String Correu;
    private String Contrasenya;
    private String CodiAmistat;

    /**
     * constructor sense paràmetres d'un usuari
     */
    public Usuari(){

    }

    /**
     * construcotor d'un usuari per al registre
     * @param nom nom de l'usuari
     * @param correu correu de l'usuari
     * @param contrasenya constrasenya del compte de l'usuari
     */
    public Usuari(String nom, String correu, String contrasenya){
        this.Nom = nom;
        this.Correu = correu;
        this.Contrasenya = contrasenya;
    }

    /**
     * Constructor d'un usuari per a l'inici de sessió
     * @param cad correu
     * @param contrassenya constrasenya del compte de l'usuari
     */
    public Usuari(String cad, String contrassenya){
        Boolean isCorreu = false;
        //Comprovem si la cadena és un correu o el nickname
        for (int i = 0; i < cad.length(); i++){
            if (cad.charAt(i) == '@'){
                isCorreu = true;
            }
        }
        if(isCorreu){
            this.Correu = cad;
        } else{
            this.Nom = cad;
        }
        this.Contrasenya = contrassenya;
    }

    /**
     * getter del nickname de l'usuari
     * @return nom de l'usuari
     */
    public String getNom() {
        return Nom;
    }

    /**
     * setter del nickname de l'usuari
     * @param nom nom de l'usuari
     */
    public void setNom(String nom) {
        Nom = nom;
    }

    /**
     * Getter del codi d'amistat de l'usuari
     * @return codi d'amistat de l'usuari
     */
    public String getCodiAmistat() {
        return CodiAmistat;
    }
}
