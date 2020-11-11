package view;

import controller.ConfigController;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("javadoc")

/**
 * finestra gràfica que gestiona el mapejat del teclat físic amb el piano
 */
public class Vista_Config extends JFrame {

    /**
     * @atribut: jbGuarda Botó de guardar
     * @atribut: jNota Etiqueta amb la nota a mapejar
     * @atribut: jlNotesTecles Llista de tecles amb la seva nota corresponent
     */
    private JButton jbGuarda;
    private JLabel jNota;
    private JList<String> jlNotesTecles;

    /**
     * constructor de la vista de configuració
     */
    public Vista_Config(){

        setLayout(new BorderLayout());

        JPanel jpSuperior = new JPanel();
        JLabel jlTitol = new JLabel("Configuració");
        jlTitol.setHorizontalAlignment(SwingConstants.CENTER);
        jlTitol.setFont(new Font("Serif", Font.PLAIN, 60));
        jpSuperior.add(jlTitol);

        JPanel jpInferior = new JPanel();
        jbGuarda = new JButton("Guarda");
        jpInferior.add(jbGuarda);

        add(jpSuperior, BorderLayout.NORTH);
        add(jpInferior, BorderLayout.SOUTH);

        JPanel jpCentre = new JPanel(new GridLayout(1,2));
        jNota = new JLabel("Mapping");
        jNota.setHorizontalAlignment(SwingConstants.CENTER);
        jNota.setFont(new Font("serif", Font.PLAIN, 30));
        jlNotesTecles = new JList<>();
        JScrollPane jspNotes = new JScrollPane(jlNotesTecles);

        jpCentre.add(jNota);
        jpCentre.add(jspNotes);
        add(jpCentre, BorderLayout.CENTER);


        setSize(500,300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Mètode que crea la connexió controlador - finestra
     * @param cc controlador
     */
    public void registraControlador(ConfigController cc){

        jbGuarda.setActionCommand("GUARDA");
        jbGuarda.addActionListener(cc);

        jlNotesTecles.addKeyListener(cc);
    }

    /**
     * Mètode que actualitza la etiqueta amb la nota a mapejar
     * @param nota escriu per pantalla el nom de la nota que s'està actualitzant
     */
    public void setNota(String nota) {
        jNota.setText(nota);
        jNota.revalidate();
        this.repaint();
    }

    /**
     * Setter de la llista actualitzada del mapejat
     * @param data s'escriu per pantalla la correspondència total entre les tecles físiques i les del piano
     */
    public void setLlista(String[] data){
        jlNotesTecles.setListData(data);
        jlNotesTecles.revalidate();
        this.repaint();
    }

    /**
     * Mètode que crea una finestra auxiliar quan s'acaba de fer el mapejat
     */
    public void missatgeAcabat() {
        JOptionPane.showMessageDialog(this, "Configuració del teclejat finalitzat");
    }
}
