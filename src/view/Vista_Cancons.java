package view;

import controller.CanconsController;
import model.Canco;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("javadoc")

/**
 * Finestra gràfica de la llista de cançons que es poden reproduir per a que l'usuari esculli
 */
public class Vista_Cancons extends JFrame{

    /**
     * @atribut: jpTitol Panel per al titol de la pàgina
     * @atribut: jpFons Panel per al fons de la finestra
     * @atribut: jpButton Panel per al botó d'acceptar
     * @atribut: jlLlistaCancons Llista de cançons disponibles
     * @atribut jlTitol Etriqueta per al titol de la pagina
     * @atribut: jbaccepta Botó d'acceptar
     * @atribut: jbcancela Botó de cancelar
     */
    private JPanel jpTitol;
    private JPanel jpFons;
    private JPanel jpButton;
    private JList<String> jlLlistaCancons;
    private JScrollPane jsCancons;
    private JLabel jlTitol;
    private JButton jbaccepta;
    private JButton jbcancela;

    /**
     * constructor de la vista de cançons
     */
    public Vista_Cancons(){

        jlLlistaCancons = new JList();

        jpFons = new JPanel( new GridLayout(3,0));

        jpTitol = new JPanel();
        jlTitol = new JLabel("Llistat de cançons");
        jlTitol.setFont(new Font("Arial", Font.PLAIN, 60));
        jpTitol.add(jlTitol);

        jsCancons = new JScrollPane(jlLlistaCancons);

        jpButton = new JPanel(new FlowLayout());
        jbaccepta = new JButton("Acceptar");
        jbcancela = new JButton("Cancelar");
        jpButton.add(jbaccepta);
        jpButton.add(jbcancela);

        jpFons.add(jpTitol);
        jpFons.add(jsCancons);
        jpFons.add(jpButton);

        this.getContentPane().add(jpFons);
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    /**
     * Mètode que crea la connexió controlador - finestra
     * @param cc controlador
     */
    public void registraControlador(CanconsController cc){

        jbaccepta.setActionCommand("ACCEPTA");
        jbcancela.setActionCommand("CANCELA");
        jbaccepta.addActionListener(cc);
        jbcancela.addActionListener(cc);

    }

    /**
     * Mètode que estableix la llista de cançons disponibles
     * @param cancons llista de cançons que l'usuari hi té accés
     * @param nicknames nicknames dels usuaris que han fet la cançó
     */
    public void setLlistaCancons(Canco[] cancons, String[] nicknames) {

        String[] data = new String[cancons.length];
        for (int i = 0; i < cancons.length; i++) {
            data[i] = "Autor/a: " + nicknames[i] + "  -  " + "Cançó: " + cancons[i].getNomCanco() +
            "   -   reproduccions: " + cancons[i].getNumReproduccions();
        }
        jlLlistaCancons.setListData(data);

    }

    /**
     * Getter de la selecció de cançó de la llista
     * @return el nombre de la cançó que s'ha escollit per a ser reproduïda
     */
    public int getSeleccioCanco() {
        return jlLlistaCancons.getSelectedIndex();
    }

    /**
     * Mètode que crea una finestra auxiliar si no es selecciona una cançó
     */
    public void errorSeleccio(){
        JOptionPane.showMessageDialog(this, "Selecciona una cançó");
    }

}
