package view;

import controller.LogController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

@SuppressWarnings({"javadoc", "FieldCanBeLocal"})

/**
 * finestra gràfica que gestiona l'inici o registre d'un usuari
 */
public class Vista_IniciarRegistrar_Sessio extends JFrame{

    /**
     * @atribut: jpFons Panel del fons
     * @atribut: jpTitol Panel per al titol
     * @atribut: jpIniciarSessio Panel per a l inici de sessio
     * @atribut: jpRegistrar Panel per al registre
     * @atribut: jpLogin Panel per al login
     * @atribut: jpContrassenyaLogin Panel per a la contrasenya del login
     * @atribut: jpEmail Panel per a l'email
     * @atribut: jpNom Panel per al nickname
     * @atribut: jpContrassenyaRegist Panel per a la contrasenya de registre
     * @atribut: jpConfirmacio Panel per a la repeticio de la contrasenya
     * @atribut: jpButtonLogin Panel per al botó de login
     * @atribut: jpButtonRegist Panel per al botó de registre
     * @atribut: jbLogin Boto de login
     * @atribut: jbRegistrar Boto de registre
     * @atribut: jlTitol Etiqueta del titol
     * @atribut: jlLogin Label del login
     * @atribut: jlContrassenyaLogin Label de la contrasenya
     * @atribut: jlEmail Label del correu
     * @atribut: jlNom Label del nickname
     * @atribut: jlContrassenyaRegist Label de la contrasenya de registre
     * @atribut: jlConfirmacio Label de la confirmacio de la contrasenya
     * @atribut: jtLogin TextField del login
     * @atribut: jtContrassenyaLogin Text field de la contrasenya del login
     * @atribut: jtEmail Text field del correu del registre
     * @atribut: jtNom Text field del nom del registre
     * @atribut: jtContrassenyaRegist Text field de la contrasenya del registre
     * @atribut: jtConfirmacio Text field de la confirmació de la contrasenya
     */
    private JPanel jpFons;
    private JPanel jpTitol;
    private JPanel jpInciarSessio;
    private JPanel jpRegistrar;
    private JPanel jpLogin;
    private JPanel jpContrassenyaLogin;
    private JPanel jpEmail;
    private JPanel jpNom;
    private JPanel jpContrasenyaRegist;
    private JPanel jpConfirmacio;
    private JPanel jpButtonLogin;
    private JPanel jpButtonRegist;
    private JButton jbLogin;
    private JButton jbRegistrar;
    private JLabel jlTitol;
    private JLabel jlLogin;
    private JLabel jlContrassenyaLogin;
    private JLabel jlEmail;
    private JLabel jlNom;
    private JLabel jlContrassenyaRegist;
    private JLabel jlConfirmacio;
    private JTextField jtLogin;
    private JPasswordField jtContrassenyaLogin;
    private JTextField jtEmail;
    private JTextField jtNom;
    private JPasswordField jtContrassenyaRegist;
    private JPasswordField jtConfirmacio;

    /**
     * constructor de la finestra d'inici de sessió i registre
     */
    public Vista_IniciarRegistrar_Sessio() {

        jpFons = new JPanel(new BorderLayout());

        //Panell del Titol
        jpTitol = new JPanel();
        jlTitol = new JLabel("SmartPiano");
        jlTitol.setFont(new Font("Serif", Font.PLAIN, 60));
        jpTitol.add(jlTitol);

        //Panell de IniciarSessio
        jpInciarSessio = new JPanel(new GridLayout(3,0));

        jpLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlLogin = new JLabel("Email/Nom:    ");
        jlLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        jtLogin = new JTextField();
        jtLogin.setColumns(19);
        jpLogin.add(jlLogin);
        jpLogin.add(jtLogin);

        jpContrassenyaLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlContrassenyaLogin = new JLabel("Contrasenya: ");
        jlContrassenyaLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        jtContrassenyaLogin = new JPasswordField();
        jtContrassenyaLogin.setColumns(19);
        jpContrassenyaLogin.add(jlContrassenyaLogin);
        jpContrassenyaLogin.add(jtContrassenyaLogin);

        jbLogin = new JButton(" Envia");
        jpButtonLogin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpButtonLogin.add(jbLogin);

        jpInciarSessio.add(jpLogin);
        jpInciarSessio.add(jpContrassenyaLogin);
        jpInciarSessio.add(jpButtonLogin);
        jpInciarSessio.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 0), "Iniciar Sessió", TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.PLAIN, 20))));

        jpRegistrar = new JPanel(new GridLayout(5,0));

        jpEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlEmail = new JLabel("Email:             ");
        jlEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        jtEmail = new JTextField();
        jtEmail.setColumns(15);
        jpEmail.add(jlEmail);
        jpEmail.add(jtEmail);

        jpNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlNom = new JLabel("Nom:               ");
        jlNom.setFont(new Font("Arial", Font.PLAIN, 14));
        jtNom = new JTextField();
        jtNom.setColumns(15);
        jpNom.add(jlNom);
        jpNom.add(jtNom);

        jpContrasenyaRegist = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlContrassenyaRegist = new JLabel("Contrasenya:   ");
        jlContrassenyaRegist.setFont(new Font("Arial", Font.PLAIN, 14));
        jtContrassenyaRegist = new JPasswordField();
        jtContrassenyaRegist.setColumns(15);
        jpContrasenyaRegist.add(jlContrassenyaRegist);
        jpContrasenyaRegist.add(jtContrassenyaRegist);

        jpConfirmacio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlConfirmacio = new JLabel("Confirmacio:    ");
        jlConfirmacio.setFont(new Font("Arial", Font.PLAIN, 14));
        jtConfirmacio = new JPasswordField();
        jtConfirmacio.setColumns(15);
        jpConfirmacio.add(jlConfirmacio);
        jpConfirmacio.add(jtConfirmacio);

        jbRegistrar = new JButton(" Envia");
        jpButtonRegist = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpButtonRegist.add(jbRegistrar);

        jpRegistrar.add(jpEmail);
        jpRegistrar.add(jpNom);
        jpRegistrar.add(jpContrasenyaRegist);
        jpRegistrar.add(jpConfirmacio);
        jpRegistrar.add(jpButtonRegist);
        jpRegistrar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 0), "Registrar-se", TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.PLAIN, 20))));


        jpFons.add(jpTitol,BorderLayout.PAGE_START);
        jpFons.add(jpInciarSessio,BorderLayout.LINE_START);
        jpFons.add(jpRegistrar,BorderLayout.LINE_END);

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
    public void registerController(LogController c){
        jbLogin.setActionCommand("LOGIN");
        jbLogin.addActionListener(c);
        jbRegistrar.addActionListener(c);
        jbRegistrar.setActionCommand("REGISTER");
    }

    /**
     * Getter del login de l'usuari
     * @return login de l'usuari
     */
    public String getLogin(){
        return jtLogin.getText();
    }

    /**
     * Getter de la contrasenya de l'usuari
     * @return contrasenya de l'usuari
     */
    public String getContrasenya(){
        String pass = String.valueOf(jtContrassenyaLogin.getPassword());
        return  pass;
    }

    /**
     * Getter de l'email de registre
     * @return correu en el registre
     */
    public String getRegisterEmail(){
        return jtEmail.getText();
    }

    /**
     * Getter del nickname de registre
     * @return nom en el registre
     */
    public String getRegisterNom(){
        return jtNom.getText();
    }

    /**
     * Getter de la contrasenya de registre
     * @return contrasenya en el registre
     */
    public String getRegisterContrasenya(){
        String pass = String.valueOf(jtContrassenyaRegist.getPassword());
        return  pass;
    }

    /**
     * Mètode per a guardar la confirmació de la contrasenya
     * @return confirmació de la contrasenya en el registre
     */
    public String getRegisterConfirmacio(){
        String pass = String.valueOf(jtConfirmacio.getPassword());
        return  pass;
    }

    /**
     * Mètode que torna a posar els camps en blanc
     */
    public void jtreset() {
            jtLogin.setText("");
            jtContrassenyaLogin.setText("");
            jtNom.setText("");
            jtEmail.setText("");
            jtContrassenyaRegist.setText("");
            jtConfirmacio.setText("");
    }

    /**
     * Mètode que crea una finestra auxiliar si no està ben construït el correu
     */
    public void emailError(){
        JOptionPane.showMessageDialog(this, "ERROR: El correu ha de tenir el caràcter '@'.");
    }

    /**
     * Mètode que crea una finestra auxiliar si la contrasenya té menys de 9 caràcters
     */
    public void passwordTooShort(){
        JOptionPane.showMessageDialog(this, "ERROR: La contrassenya ha de contenir un mínim de 9 caràcters i incloure nombres, majúscules i minúscules.");
    }

    /**
     * Mètode que crea una finestra auxiliar si s'ha registrat correctament
     */
    public void registerOK(){
        JOptionPane.showMessageDialog(this, "T'has registrat correctament!");
        jtreset();
    }

    /**
     * Mètode que crea una finestra auxiliar si s'introdueix un correu o login ja existents en el sistema
     */
    public void registerError(){
        JOptionPane.showMessageDialog(this, "ERROR: Aquest login o correu ja existeixen a la nostra base de dades.");
        jtreset();
    }

    /**
     * Mètode que crea una finestra auxiliar si les contrasenyes no coincideixen
     */
    public void contrassenyesError(){
        JOptionPane.showMessageDialog(this, "ERROR: Les contrasenyes no coincideixen.");
    }

    /**
     * Mètode que crea una finestra auxiliar si hi ha un error en iniciar sessió
     */
    public void loginError(){
        JOptionPane.showMessageDialog(this, "ERROR: Usuari o contrasenya incorrectes.");
        jtreset();
    }

    /**
     * Mètode que crea una finestra auxiliar si la contrasenya del registre no és correcte
     */
    public void caractersError(){
        JOptionPane.showMessageDialog(this, "ERROR: La contrasenya ha de tenir un mínim de 8 caràcters i contenir com a mínim una majúscula, una minúscula i un dígit.");
    }

    /**
     * Mètode que crea una finestra auxiliar si un camp no s'ha omplert
     */
    public void emptyError(){
        JOptionPane.showMessageDialog(this, "ERROR: Tots els camps són obligatoris.");
    }

}
