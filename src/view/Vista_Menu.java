package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings({"FieldCanBeLocal","javadoc"})

/**
 * finestra gràfica del menú principal
 */
public class Vista_Menu extends JFrame{

    /**
     * @atribut: jpFons Panel del fons
     * @atribut: jpButtons Panel per als botons
     * @atribut: jpTitol Panel per al titol de la finestra
     * @atribut: jpButtonDown Panel per als botons inferiors
     * @atribut: jpPiano Panel per al botó de reproduir
     * @atribut: jpReproduir Panel per al botó de reproduir
     * @atribut: jpAmics Panel per al botó d'afegir amigues
     * @atribut: jbPiano Botó de tocar el piano
     * @atribut: jbReproduir Botó de reproduir una cançó
     * @atribut: jbAmics Botó d'afegir amigues
     * @atribut: jbTancarSessio Botó de tancar sessió
     * @atribut: jbEliminarCompte Botó d'eliminar compte
     * @atribut: jlTitol Etiqueta del títol
     * @atribut: jlBenvinguda Label de benvinguda
     */
    private JPanel jpFons;
    private JPanel jpButtons;
    private JPanel jpTitol;
    private JPanel jpButtonDown;
    private JPanel jpPiano;
    private JPanel jpReproduir;
    private JPanel jpAmics;
    private JButton jbPiano;
    private JButton jbReproduir;
    private JButton jbAmics;
    private JButton jbTancarSessio;
    private JButton jbEliminarCompte;
    private JLabel jlTitol;
    private JLabel jlBenvinguda;

    /**
     * constructor de la vista del menú
     */
    public Vista_Menu(){

        jpFons = new JPanel(new BorderLayout());

        //Panell del Titol
        jpTitol = new JPanel(new GridLayout(2,1));
        jlTitol = new JLabel("SmartPiano");
        jlTitol.setHorizontalAlignment(SwingConstants.CENTER);
        jlBenvinguda = new JLabel("Benvingut/da ");
        jlBenvinguda.setHorizontalAlignment(SwingConstants.CENTER);
        jlBenvinguda.setFont(new Font("Serif", Font.PLAIN, 30));
        jlTitol.setFont(new Font("Serif", Font.PLAIN, 60));
        jpTitol.add(jlTitol);
        jpTitol.add(jlBenvinguda);

        //Panell de Botons
        jpButtons = new JPanel(new GridLayout(4,0));
        jpPiano = new JPanel();
        jpReproduir = new JPanel();
        jpAmics = new JPanel();

        //Tocar el piano
        jbPiano = new JButton("                        Tocar el piano                         ");
        jpPiano.add(jbPiano);
        jbReproduir = new JButton("                      Reproduir cançó                     ");
        jpReproduir.add(jbReproduir);
        jbAmics = new JButton("                         Afegir amistat                         ");
        jpAmics.add(jbAmics);

        jpButtonDown = new JPanel(new FlowLayout());
        jbTancarSessio = new JButton(" Tancar sessió");
        jbEliminarCompte = new JButton(" Eliminar compte");
        jpButtonDown.add(jbTancarSessio);
        jpButtonDown.add(jbEliminarCompte);

        jpButtons.add(jpPiano);
        jpButtons.add(jpReproduir);
        jpButtons.add(jpAmics);
        jpButtons.add(jpButtonDown);


        jpFons.add(jpTitol,BorderLayout.PAGE_START);
        jpFons.add(jpButtons,BorderLayout.CENTER);

        this.getContentPane().add(jpFons);
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    /**
     * Mètode que crea la connexió controlador - finestra
     * @param c controlador
     */
    public void menuController(MenuController c){

        jbPiano.addActionListener(c);
        jbPiano.setActionCommand("TOCAR_EL_PIANO");
        jbReproduir.addActionListener(c);
        jbReproduir.setActionCommand("REPRODUIR_CANCO");
        jbAmics.addActionListener(c);
        jbAmics.setActionCommand("AFEGIR_AMIGUES");
        jbTancarSessio.addActionListener(c);
        jbTancarSessio.setActionCommand("TANCAR_SESSIO");
        jbEliminarCompte.addActionListener(c);
        jbEliminarCompte.setActionCommand("ELIMINAR_COMPTE");
    }

    /**
     * Mètode que estbleix el nom de benvinguda al menu
     * @param username nom de l'usuari
     */
    public void setJlBenvinguda(String username){
        jlBenvinguda.setText("Benvingut/da " + username);
    }

}
