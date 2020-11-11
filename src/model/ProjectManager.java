package model;

@SuppressWarnings("javadoc")

/**
 * Classe que gestiona el model del programa
 */
public class ProjectManager {

    /**
     * @atribut: Usuari Usuari que ha iniciat sessio al programa
     * @atribut: mtp Mapejat de les tecles del piano
     */
    private Usuari Usuari;
    private MapejatTeclesPiano mtp;

    /**
     * constructor de la classe del gestor del model del programa
     */
    public ProjectManager(){
        this.Usuari = new Usuari();
        this.mtp = new MapejatTeclesPiano();
    }

    /**
     * Setter del mapejat del programa
     * @param mtp Mapejat de les tecles del piano
     */
    public void setMtp(MapejatTeclesPiano mtp){
        this.mtp = mtp;
    }

    /**
     * Getter del mapejat de les tecles
     * @return Mapejat de les tecles del piano
     */
    public MapejatTeclesPiano getMtp() {
        return mtp;
    }

    /**
     * Mètode per a trobar l'usuari actiu
     * @return usuari que està fent servir l'aplicació
     */
    public model.Usuari getUsuari() {
        return Usuari;
    }

    /**
     * Mètode per a assignar l'usuari
     * @param u setter de l'usuari que fa servir l'aplicació
     */
    public void setUsuari(Usuari u){
        Usuari = u;
    }
}
