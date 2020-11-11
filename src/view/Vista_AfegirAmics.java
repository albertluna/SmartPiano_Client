package view;

//import controller.AmicsController;

import controller.AmicsController;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("javadoc")

/**
 * finestra gràfica en què es poden afegir amistats
 */
public class Vista_AfegirAmics extends JFrame{

    /**
     * @atribut: jpFons Panell del fons
     * @atribut: jpTitol Panell per al titol
     * @atribut: jpCodi Panell per al codi d'amistat
     * @atribut: jpBuscarAmic Panell per al text field de buscar amiga
     * @atribut: jpText Panell per al text
     * @atribut: jpMenu Panell per al boto de tornar
     * @atribut: jbBuscarAmic Boto de buscar amiga
     * @atribut: jbMenu Botó per a tornar al menú
     * @atribut: jlTitol Etiqueta amb el títol
     * @atribut: jlCodi Etiqueta amb el codi d'amistat de l'usuari
     * @atribut: jlBuscarAmic Etiqueta de buscar amiga
     * @atribut: jtBuscarAmic Camp de text per a introduir el codi de l'amiga
     */
    private JPanel jpFons;
    private JPanel jpTitol;
    private JPanel jpCodi;
    private JPanel jpBuscarAmic;
    private JPanel jpText;
    private JPanel jpMenu;
    private JButton jbBuscarAmic;
    private JButton jbMenu;
    private JLabel jlTitol;
    private JLabel jlCodi;
    private JLabel jlBuscarAmic;
    private JTextField jtBuscarAmic;

    /**
     * constructor
     * @param codi codi d'amistat de l'usuari
     */
    public Vista_AfegirAmics(String codi){

        jpFons = new JPanel(new BorderLayout());

        jpTitol = new JPanel();
        jlTitol = new JLabel("Afegir Amistats");
        jlTitol.setFont(new Font("Arial", Font.PLAIN, 60));
        jpTitol.add(jlTitol);

        jpCodi = new JPanel(new FlowLayout());
        jlCodi = new JLabel("Codi d'usuari: " + codi);
        jpCodi.add(jlCodi);

        jpBuscarAmic = new JPanel(new FlowLayout());
        jlBuscarAmic = new JLabel("Afegir amistat: ");
        jtBuscarAmic = new JTextField();
        jtBuscarAmic.setColumns(30);
        jbBuscarAmic = new JButton("Afegeix");
        jpBuscarAmic.add(jlBuscarAmic);
        jpBuscarAmic.add(jtBuscarAmic);
        jpBuscarAmic.add(jbBuscarAmic);

        jpText = new JPanel(new GridLayout(2,0));
        jpText.add(jpCodi);
        jpText.add(jpBuscarAmic);

        jpMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jbMenu = new JButton("Menu");
        jpMenu.add(jbMenu);

        //Afegim els panells
        jpFons.add(jpTitol, BorderLayout.PAGE_START);
        jpFons.add(jpText,BorderLayout.CENTER);
        jpFons.add(jpMenu, BorderLayout.PAGE_END);

        this.getContentPane().add(jpFons);
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * es crea la connexió controlador - finestra
     * @param c controlador de la finestra
     */
    public void amicsController (AmicsController c){
        jbBuscarAmic.setActionCommand("AFEGIR");
        jbMenu.setActionCommand("MENU");
        jbMenu.addActionListener(c);
        jbBuscarAmic.addActionListener(c);
    }

    /**
     * Getter del codi d'amistat a buscar
     * @return el codi d'amistat que s'ha escrit
     */
    public String getCodiAmic(){
        return jtBuscarAmic.getText();
    }

    /**
     * Mètode que crea una finestra auxiliar si l'amistat ja exisitia
     */
    public void errorExists(){
        JOptionPane.showMessageDialog(this, "ERROR: Aquest codi ja forma part de les teves amistats!");
    }

    /**
     * Mètode que crea una finestra auxiliar si s'introdueix un codi que no és en el sistema
     */
    public void errorKO(){
        JOptionPane.showMessageDialog(this, "ERROR: No s'ha trobat aquest codi d'amistat.");
    }

    /**
     * Mètode que crea una finestra auxiliar si s'ha fet l'amistat correctament
     */
    public void addOK(){
        JOptionPane.showMessageDialog(this, "Amistat afegida correctament!");
    }
}
