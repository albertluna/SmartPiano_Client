package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

@SuppressWarnings({"javadoc", "FieldCanBeLocal"})

/**
 * Finestra gràfica del piano
 */
public class Vista_Piano extends JFrame {

    /**
     * @atribut: screenSize Mides de la finestra
     * @atribut: jpTecles Panell per a les tecles
     * @atribut: jpnotes Matriu de panells per a la partitura
     * @atribut: jTeclesPiano Array de tecles del piano
     * @atribut: jpOpcionsPestanya Panell per a les opcions del piano
     * @atribut: jbGrabar Botó per a gravar
     * @atribut: jbOrganitzarTecles Botó per al mapejat de les tecles
     * @atribut: jbTornarMenu Botó per a tornar a la pantalla principal
     * @atribut: mapaTecles HashMap de les tecles amb el seu corresponent color
     * @atribut: GRAVAR Cadena constant "GRAVAR"
     */
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel jpTecles;
    private JPanel  jpartitura;
    private JPanel[][] jpnotes;
    private JButton[] jTeclesPiano = new JButton[24];
    private JPanel jpOpcionsPestanya;
    private JButton jbGrabar;
    private JButton jbOrganitzarTecles;
    private JButton jbTornarMenu;
    private HashMap <JButton, Color> mapaTecles;
    public static final String GRAVAR = "GRAVAR";


    /**
     * constructor de la finestra gràfica del piano
     */
    public Vista_Piano() {

        mapaTecles = new HashMap<>();

        //Tecles Blanques
        jpTecles = new JPanel();

        jTeclesPiano[0] = new JButton("Do");
        jTeclesPiano[1] = new JButton("Re");
        jTeclesPiano[2]= new JButton("Mi");
        jTeclesPiano[3]= new JButton("Fa");
        jTeclesPiano[4]= new JButton("Sol");
        jTeclesPiano[5]= new JButton("La");
        jTeclesPiano[6]= new JButton("Si");
        jTeclesPiano[7]= new JButton("Do+");
        jTeclesPiano[8]= new JButton("Re+");
        jTeclesPiano[9]= new JButton("Mi+");
        jTeclesPiano[10]= new JButton("Fa+");
        jTeclesPiano[11]= new JButton("Sol+");
        jTeclesPiano[12]= new JButton("La+");
        jTeclesPiano[13]= new JButton("Si+");


        jTeclesPiano[0].setBackground(Color.WHITE);
        jTeclesPiano[1].setBackground(Color.WHITE);
        jTeclesPiano[2].setBackground(Color.WHITE);
        jTeclesPiano[3].setBackground(Color.WHITE);
        jTeclesPiano[4].setBackground(Color.WHITE);
        jTeclesPiano[5].setBackground(Color.WHITE);
        jTeclesPiano[6].setBackground(Color.WHITE);
        jTeclesPiano[7].setBackground(Color.WHITE);
        jTeclesPiano[8].setBackground(Color.WHITE);
        jTeclesPiano[9].setBackground(Color.WHITE);
        jTeclesPiano[10].setBackground(Color.WHITE);
        jTeclesPiano[11].setBackground(Color.WHITE);
        jTeclesPiano[12].setBackground(Color.WHITE);
        jTeclesPiano[13].setBackground(Color.WHITE);

        //tecles negres
        jTeclesPiano[14] = new JButton("Do#");
        jTeclesPiano[14].setForeground(Color.WHITE);
        jTeclesPiano[15]= new JButton("Re#");
        jTeclesPiano[15].setForeground(Color.WHITE);
        jTeclesPiano[16]= new JButton("Fa#");
        jTeclesPiano[16].setForeground(Color.WHITE);
        jTeclesPiano[17]= new JButton("Sol#");
        jTeclesPiano[17].setForeground(Color.WHITE);
        jTeclesPiano[18]= new JButton("La#");
        jTeclesPiano[18].setForeground(Color.WHITE);
        jTeclesPiano[19]= new JButton("Do#+");
        jTeclesPiano[19].setForeground(Color.WHITE);
        jTeclesPiano[20]= new JButton("Re#+");
        jTeclesPiano[20].setForeground(Color.WHITE);
        jTeclesPiano[21]= new JButton("Fa#+");
        jTeclesPiano[21].setForeground(Color.WHITE);
        jTeclesPiano[22]= new JButton("Sol#+");
        jTeclesPiano[22].setForeground(Color.WHITE);
        jTeclesPiano[23]= new JButton("La#+");
        jTeclesPiano[23].setForeground(Color.WHITE);

        jTeclesPiano[14].setBackground(Color.BLACK);
        jTeclesPiano[15].setBackground(Color.BLACK);
        jTeclesPiano[16].setBackground(Color.BLACK);
        jTeclesPiano[17].setBackground(Color.BLACK);
        jTeclesPiano[18].setBackground(Color.BLACK);
        jTeclesPiano[19].setBackground(Color.BLACK);
        jTeclesPiano[20].setBackground(Color.BLACK);
        jTeclesPiano[21].setBackground(Color.BLACK);
        jTeclesPiano[22].setBackground(Color.BLACK);
        jTeclesPiano[23].setBackground(Color.BLACK);

        jpTecles.setLayout(new GridLayout(0,24,0,10));
        jpTecles.setPreferredSize(new Dimension(screenSize.width,400));
        jpTecles.add(jTeclesPiano[0]);
        jpTecles.add(jTeclesPiano[14]);
        jpTecles.add(jTeclesPiano[1]);
        jpTecles.add(jTeclesPiano[15]);
        jpTecles.add(jTeclesPiano[2]);
        jpTecles.add(jTeclesPiano[3]);
        jpTecles.add(jTeclesPiano[16]);
        jpTecles.add(jTeclesPiano[4]);
        jpTecles.add(jTeclesPiano[17]);
        jpTecles.add(jTeclesPiano[5]);
        jpTecles.add(jTeclesPiano[18]);
        jpTecles.add(jTeclesPiano[6]);
        jpTecles.add(jTeclesPiano[7]);
        jpTecles.add(jTeclesPiano[19]);
        jpTecles.add(jTeclesPiano[8]);
        jpTecles.add(jTeclesPiano[20]);
        jpTecles.add(jTeclesPiano[9]);
        jpTecles.add(jTeclesPiano[10]);
        jpTecles.add(jTeclesPiano[21]);
        jpTecles.add(jTeclesPiano[11]);
        jpTecles.add(jTeclesPiano[22]);
        jpTecles.add(jTeclesPiano[12]);
        jpTecles.add(jTeclesPiano[23]);
        jpTecles.add(jTeclesPiano[13]);

        getContentPane().add(jpTecles, BorderLayout.CENTER);

        //opcions menu
        jpOpcionsPestanya = new JPanel();

        jbOrganitzarTecles = new JButton("CONFIG");
        jbOrganitzarTecles.setIcon(new ImageIcon("Imatges/config.png"));


        jbTornarMenu = new JButton("HOME");
        jbTornarMenu.setIcon(new ImageIcon("Imatges/home.png"));

        jbGrabar = new JButton(GRAVAR);
        jbGrabar.setIcon(new ImageIcon("Imatges/grabar.png"));


        jpOpcionsPestanya.setLayout(new GridLayout(1,3,600,20));
        jpOpcionsPestanya.add(jbTornarMenu);
        jpOpcionsPestanya.add(jbOrganitzarTecles);
        jpOpcionsPestanya.add(jbGrabar);


        getContentPane().add(jpOpcionsPestanya, BorderLayout.PAGE_END);

        jpartitura = new JPanel( new GridLayout(10,24));
        jpnotes = new JPanel[10][24];
        for(int r = 0; r < 10 ; r++){
            for(int f = 0; f < 24; f++){
                jpnotes[r][f] = new JPanel();
                jpnotes[r][f].setPreferredSize(new Dimension(1,50));
                jpnotes[r][f].setBackground(Color.BLACK);
                jpartitura.add(jpnotes[r][f]);
            }
        }
        getContentPane().add(jpartitura, BorderLayout.NORTH);



        // Donem una mida, un títol i centrem la finestra a la pantalla
        // setSize(1700,1500);
        setSize(screenSize.width,screenSize.height -40);
        setTitle("SmartPiano");
        setLocationRelativeTo(null);
        // Indiquem que quan es faci clic a la "X" de la finestra
        // el programa finalitzi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(JButton jb: jTeclesPiano){
            mapaTecles.put(jb, jb.getBackground());
        }

    }


    /**
     * es crea la connexió controlador - finestra mitjançant el ratolí
     * @param cBotons controlador
     */
    public void assignarControladorMouse(MouseListener cBotons) {

        //Assignem la comanda i listener per a cada tecla
        for (int i = 0; i < jTeclesPiano.length; i++) {
            jTeclesPiano[i].setActionCommand(String.valueOf(i));
            jTeclesPiano[i].addMouseListener(cBotons);
        }

        jbGrabar.setActionCommand(String.valueOf(jTeclesPiano.length+1));
        jbTornarMenu.setActionCommand(String.valueOf(jTeclesPiano.length+2));
        jbOrganitzarTecles.setActionCommand(String.valueOf(jTeclesPiano.length+3));
        jbGrabar.addMouseListener(cBotons);
        jbTornarMenu.addMouseListener(cBotons);
        jbOrganitzarTecles.addMouseListener(cBotons);

    }

    /**
     * es crea la connexió controlador - finestra mitjançant el teclat
     * @param cBotons controlador
     */
    public void assignarControladorTecles(KeyListener cBotons){

        for(int i =0; i<jTeclesPiano.length;i++){

            jTeclesPiano[i].setActionCommand(String.valueOf(i));
            jTeclesPiano[i].addKeyListener(cBotons);
        }

        jbGrabar.setActionCommand(String.valueOf(jTeclesPiano.length+1));
        jbTornarMenu.setActionCommand(String.valueOf(jTeclesPiano.length+2));
        jbOrganitzarTecles.setActionCommand(String.valueOf(jTeclesPiano.length+3));
        jbGrabar.addKeyListener(cBotons);
        jbTornarMenu.addKeyListener(cBotons);
        jbOrganitzarTecles.addKeyListener(cBotons);
    }

    /**
     * es canvia el color d'una tecla quan es toca
     * @param i index de la teclat
     */
    public void resetColor(int i){

        jTeclesPiano[i].doClick();
        jTeclesPiano[i].setBackground(mapaTecles.get(jTeclesPiano[i]));

    }

    /**
     * Funció per a pintar la tecla quan l'usuari la prem
     * @param i Tecla  a pintar
     */
    public void pintaTecla(int i){
        jTeclesPiano[i].setBackground(Color.PINK);
    }

    /**
     * Mètode per a despintar una tecla
     * @param i Index de la tecla a despintar
     */
    public void despintaTecla(int i){
        jTeclesPiano[i].setBackground(mapaTecles.get(jTeclesPiano[i]));
    }

    /**
     * Mètode per a trobar el nom de les tecles el piano i la seva posició
     * @return Matriu d'objectes amb el nom i posició corresponent a cada tecla
     */
    public Object[][] getNomjTeclesPiano(){
        Object[][] noms = new Object[jTeclesPiano.length][2];
        int i = 0;
        for(JButton jb: jTeclesPiano){
            noms[i][0] = i;
            noms[i][1] = jb.getText();
            i++;
        }
        return noms;
    }


    /**
     * Mètode per a pintar de negre tota la partitura
     */
    public void resetPartitura(){
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 24; j++) {
                jpnotes[i][j].setBackground(Color.BLACK);
            }
        }
    }

    /**
     * Mètode per a actualitzar una nota de la partitura
     * @param i Posició de les files a la matriu de la partitura
     * @param j Posició de les columnes a la matriu de la partitura
     */
    public void actualitzanota(int i, int j){
        jpnotes[i][j].setBackground(Color.PINK);
    }

    /**
     * Mètode per a mostrar panell de diàleg per a activar o no el so
     * @return Resposta
     */
    public int preguntaSo(){
        return JOptionPane.showConfirmDialog(this,"Vols activar el so de la cançó?", "Activar so", JOptionPane.YES_NO_OPTION);
    }

}
