package view;

import controller.EnregistrarCancoController;
import model.ProjectManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("javadoc")

/**
 * finestra que gestiona un cop s'ha gravat una nova cançó
 */
public class Vista_EnregistrarCanco extends JFrame {


    /**
     * @atribut: jtNomCanco Camp de text amb el nom de la cançó
     * @atribut: jlNomCanco Eiqueta de nom de la cançó
     * @atribut: isPrivate CheckBox per a escollir si la cançó serà privada o pública
     * @atribut: codiUsuari Codi de l'usuari que guarda la cançó
     * @atribut: jbEnregistrar Botó per a guardar la cançó
     * @atribut: jbCancela Botó per a cancelar el registre de la cançó
     * @atribut: jpButons Panel per als botons de guardar i cancelar
     * @atribut: jpNomCanco Panel per al nom de la cançó
     * @atribut: jpFons Panel de fons
     */
    private JTextField jtNomCanco;
    private JLabel jlNomCanco;
    private JCheckBox isPrivate;
    private JLabel codiUsuari;
    private JButton jbEnregistrar;
    private JButton jbCancellar;
    private JPanel jpButons;
    private JPanel jpNomCanco;
    private JPanel jpFons;

    /**
     * constructor de la vista d'enregistrar una cançó
     * @param codiAmistat codi d'amistat del client
     */
    public Vista_EnregistrarCanco(String codiAmistat) {

        //Panell de tota la finestra
        jpFons = new JPanel(new GridLayout(4, 0));

        //Informacio inicial
        codiUsuari = new JLabel(codiAmistat + ": configura la canco que has creat.");

        //Per saber si vol la canco privada o publica
        isPrivate = new JCheckBox("Canco privada");
        isPrivate.setSelected(false);

        //Per introduir el nom que vol per la canco
        jlNomCanco = new JLabel("Nom canco: ");
        jtNomCanco = new JTextField();
        jtNomCanco.setColumns(20);
        jpNomCanco = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpNomCanco.add(jlNomCanco);
        jpNomCanco.add(jtNomCanco);

        //Butons que indiquen si vol que es guardi la canco o no
        jpButons = new JPanel(new GridLayout(0,2));

        jbEnregistrar = new JButton("Enregistrar canco");
        jbCancellar = new JButton("Cancel·lar pujada");

        jpButons.add(jbEnregistrar);
        jpButons.add(jbCancellar);

        //Panell general de la pagina
        jpFons.add(codiUsuari);
        jpFons.add(isPrivate);
        jpFons.add(jpNomCanco);
        jpFons.add(jpButons);

        this.getContentPane().add(jpFons);
        setSize(400,175);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    /**
     * Mètode que crea la connexió controlador - finestra
     * @param c controlador de la finestra
     */
    public void registerController(EnregistrarCancoController c){
        jbEnregistrar.setActionCommand("ENREGISTRAR");
        jbCancellar.setActionCommand("CANCELLAR");

        jbEnregistrar.addActionListener(c);
        jbCancellar.addActionListener(c);
    }

    /**
     * Mètode per a obtenir el nom de la cançó assignat per l'usuari
     * @return nom de la cançó que s'ha creat
     */
    public String getText() { return jtNomCanco.getText(); }

    /**
     * Mètode per a saber si la cançó és o no privada
     * @return si la nova cançó és privada o pública
     */
    public boolean cancoEsPrivada() { return isPrivate.isSelected(); }

    /**
     * Mètode que crea una finestra auxiliar si s'ha guardat la cançó correctament
     */
    public void registreOK(){
        JOptionPane.showMessageDialog(this, "Cançó registrada correctament.");
    }

    /**
     * Mètode que crea una finestra auxiliar si no s'ha guardat la cançó correctament
     */
    public void registreKO(){
        JOptionPane.showMessageDialog(this, "Error en registrar la cançó.");
    }

    /**
     * Mètode que crea una finestra auxiliar si el nom de la cançó no compleix els requisits
     */
    public void showErrorNom(){JOptionPane.showMessageDialog(this, "El nom no pot contenir el caràcter '");}


}
