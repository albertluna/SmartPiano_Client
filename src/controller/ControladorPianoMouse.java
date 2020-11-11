package controller;

import model.Gravacio;
import model.MapejatTeclesPiano;
import model.ProjectManager;
import network.ServerCommunication;
import view.*;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;

@SuppressWarnings("javadoc")

/**
 * controlador de la finestra gràfica del piano mitjançant els botons
 */
public class ControladorPianoMouse implements MouseListener {

    /**
     * @atribut: vista Vista del piano
     * @atribut: syn Sintetitzador
     * @atribut: midChannel Array de midi channels del sintatitzador
     * @atribut: instrument Array d'instruments del sintetitzador
     * @atribut: pm Gestor del model del programa
     * @atribut: sc Comunicació amb el servidor
     * @atribut: isGravant Booleà per a saber si està gravant una cançó
     * @atribut: novaCanco Gravació per a una nova cançó
     * @atribut: mtp Mapejat del teclat del piano
     */
    private Vista_Piano vista;
    private Synthesizer syn;
    private MidiChannel[] midChannel;
    private Instrument[] instrument;
    private ProjectManager pm;
    private ServerCommunication sc;
    private boolean isGravant;
    private Gravacio novaCanco;
    private MapejatTeclesPiano mtp;


    /**
     * constructor del controlador del piano (mouse)
     * @param vista per controlar la finestra gràfica corresponent
     * @param pm conté l'usuari que està fent servir l'aplicació
     * @param sc per establir connexio amb el servidor
     */
    public ControladorPianoMouse(Vista_Piano vista, ProjectManager pm, ServerCommunication sc) {
        // Establim la "relació" C->V
        try {
            syn = MidiSystem.getSynthesizer();
            syn.open();
            midChannel = syn.getChannels();

            //Guardem els instruments del sintetitzador
            instrument = syn.getDefaultSoundbank().getInstruments();
            syn.loadInstrument(instrument[0]);
            this.pm = pm;
            this.sc = sc;

        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
        this.vista = vista;
        isGravant = false;
        mtp = pm.getMtp();

    }

    /**
     * Setter del mapejat del teclat
     * @param mtp Mapejat del teclat
     */
    public void setMapejatTeclat(MapejatTeclesPiano mtp) {
        this.mtp = mtp;
    }

    /**
     * Mètode per a transformar una nota en forma d'enter segons la posició a l'array de tecles en una nota (String)
     * @param nota valor de la tecla que s'ha pulsat
     * @return el nom de la nota que correspon del mapejat
     */
    public String transformarTeclaANota(int nota) { return mtp.getNota(nota);}

    /**
     * Mètode en clickar el mouse
     * @param e Event del Mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Mètode mentre el mouse està clickat
     * @param e Mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        String ac=((JButton)e.getSource()).getText();
        switch (ac) {

            case "GRAVAR":
                this.isGravant = !isGravant;
                if (isGravant) {
                    //Es comenca la gravacio
                    novaCanco = new Gravacio(LocalTime.now());
                } else {
                    //S'acaba la gravacio de la canco
                    //Es crea la vista per preguntar el nom de la canco i s'envia la canco si vol l'usuari
                    Vista_EnregistrarCanco ec = new Vista_EnregistrarCanco(pm.getUsuari().getCodiAmistat());
                    EnregistrarCancoController ecc = new EnregistrarCancoController(sc, ec, pm, novaCanco);
                    ec.registerController(ecc);
                    ec.setVisible(true);
                }
                break;

            case "HOME":
                //Es torna al menu principal
                Vista_Menu menu = new Vista_Menu();
                MenuController mc = new MenuController(menu, pm, sc);
                menu.setJlBenvinguda(pm.getUsuari().getNom());
                menu.menuController(mc);
                menu.setVisible(true);
                vista.setVisible(false);
                break;

            case "CONFIG":
                //Obrim finestra de configuració
                Vista_Config vc = new Vista_Config();
                ConfigController cc = new ConfigController(vc, this, mtp, pm);
                vc.registraControlador(cc);
                vc.setLlista(mtp.toStrings());
                vc.setVisible(true);
                break;

            default:
                tocarNota(ac);
                break;

        }
    }

    /**
     * funció que afegeix una nota a la llista de nota de la gravació
     * @param nota nom de la nota
     */
    public void afegirNotaGravacio(String nota) {
        novaCanco.afegirNota(nota);
    }


    @Override
    /**
     * Mètode en deixar de clicar el mouse
     * @param e Event del mouse
     */
    public void mouseReleased(MouseEvent e) {
        //Funcio per determinar el milisegon final d'una nota quan s'esta gravant
        if(((JButton)e.getSource()).getText() != "CONFIG" && ((JButton)e.getSource()).getText() != "HOME" &&((JButton)e.getSource()).getText() != "GRAVAR") {
            setFiNota(((JButton) e.getSource()).getText());
            vista.resetColor(mtp.getPosicio(((JButton) e.getSource()).getText()));
            offNota(((JButton) e.getSource()).getText());
        }
    }


    /**
     * funció que guarda en quin milisegon es deixa de toca una nota
     * @param nota nom de la nota
     */
    public void setFiNota(String nota) {
        if (isGravant) {
            novaCanco.setFiNota(nota);
        }
    }

    /**
     * Mètode per a pintar una tecla quan sona una nota
     * @param nota Nota a pintar
     */
    public void pintaNota(String nota){ vista.pintaTecla(getBoto(nota));}

    /**
     * Mètode per a despintar una nota un cop deixa de sonar
     * @param nota Nota a despintar
     */
    public void despintaNota(String nota){
        vista.despintaTecla(getBoto(nota));
    }

    /**
     * Mètode per a despintar totes la partitura
     */
    public void resetPartitura(){
        vista.resetPartitura();
    }


    /**
     * Mètode per a actualitzar una nota en la partitura
     * @param i Posició de files de la nota
     * @param j Posició de columnes de la nota
     */
    public  void actualitzanota(int i, int j){
        vista.actualitzanota(i, j);
    }


    /**
     * Mètode en cas que el mouse es col·loca sobre les tecles del piano
     * @param e Event del mouse
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Mètode per a quan el mouse surt d'una zona determinada
     * @param e Event del mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * funció que reprodueix la nota que es passa com a variable
     *
     * @param nota valor de la nota que es vol fer sonar
     */
    public void tocarNota(String nota) {
        if (isGravant) {
            //Afegim la nota a la gravació
            afegirNotaGravacio(nota);
        }

        if (mtp.getPosicio(nota)!= -1) {
            vista.pintaTecla(mtp.getPosicio(nota));
            //Fem sonar la tecla
            switch (nota) {
                case "Do":
                    this.midChannel[0].noteOn(60, 1000);
                    break;
                case "Re":
                    this.midChannel[8].noteOn(62, 1000);

                    break;
                case "Mi":
                    this.midChannel[8].noteOn(64, 1000);
                    break;
                case "Fa":
                    this.midChannel[8].noteOn(65, 1000);

                    break;
                case "Sol":
                    this.midChannel[8].noteOn(67, 1000);

                    break;
                case "La":
                    this.midChannel[8].noteOn(69, 1000);

                    break;
                case "Si":
                    this.midChannel[8].noteOn(71, 1000);

                    break;
                case "Do+":
                    this.midChannel[8].noteOn(72, 1000);

                    break;
                case "Re+":
                    this.midChannel[8].noteOn(74, 1000);

                    break;
                case "Mi+":
                    this.midChannel[8].noteOn(76, 1000);

                    break;
                case "Fa+":
                    this.midChannel[8].noteOn(77, 1000);

                    break;
                case "Sol+":
                    this.midChannel[8].noteOn(79, 1000);

                    break;
                case "La+":
                    this.midChannel[8].noteOn(81, 1000);

                    break;
                case "Si+":
                    this.midChannel[8].noteOn(83, 1000);

                    break;

                case "Do#":
                    this.midChannel[8].noteOn(61, 1000);

                    break;
                case "Re#":
                    this.midChannel[8].noteOn(63, 1000);

                    break;
                case "Fa#":
                    this.midChannel[8].noteOn(66, 1000);

                    break;
                case "Sol#":
                    this.midChannel[8].noteOn(68, 1000);

                    break;
                case "La#":
                    this.midChannel[8].noteOn(70, 1000);

                    break;
                case "Do#+":
                    this.midChannel[8].noteOn(73, 1000);

                    break;
                case "Re#+":
                    this.midChannel[8].noteOn(75, 1000);

                    break;
                case "Fa#+":
                    this.midChannel[8].noteOn(78, 1000);

                    break;
                case "Sol#+":
                    this.midChannel[8].noteOn(80, 1000);

                    break;
                case "La#+":
                    this.midChannel[8].noteOn(82, 1000);
                    break;
            }
        }
    }

    /**
     * Mètode per a apagar el so d'una nota
     * @param nota Cadena amb la nota
     */
    public void offNota(String nota){
        switch(nota){
            case "Do"://A DO
                this.midChannel[8].noteOff(60);
                vista.resetColor(getBoto(nota));
                break;
            case "Re"://S RE
                this.midChannel[8].noteOff(62);
                vista.resetColor(getBoto(nota));
                break;

            case "Mi"://D MI
                this.midChannel[8].noteOff(64);
                vista.resetColor(getBoto(nota));
                break;

            case "Fa"://F FA
                this.midChannel[8].noteOff(65);
                vista.resetColor(getBoto(nota));
                break;

            case "Sol"://G SOL
                this.midChannel[8].noteOff(67);
                vista.resetColor(getBoto(nota));
                break;
            case "La"://H LA
                this.midChannel[8].noteOff(69);
                vista.resetColor(getBoto(nota));
                break;
            case "Si"://J SI
                this.midChannel[8].noteOff(71);
                vista.resetColor(getBoto(nota));
                break;
            case "Do+": //Z DO+
                this.midChannel[8].noteOff(72);
                vista.resetColor(getBoto(nota));
                break;
            case "Re+"://X RE+
                this.midChannel[8].noteOff(74);
                vista.resetColor(getBoto(nota));
                break;
            case "Mi+"://C MI+
                this.midChannel[8].noteOff(76);
                vista.resetColor(getBoto(nota));
                break;
            case "Fa+"://V FA+
                this.midChannel[8].noteOff(77);
                vista.resetColor(getBoto(nota));
                break;
            case "Sol+": //B SOL+
                this.midChannel[8].noteOff(79);
                vista.resetColor(getBoto(nota));
                break;
            case "La+": //N LA+
                this.midChannel[8].noteOff(81);
                vista.resetColor(getBoto(nota));
                break;
            case "Si+": //M SI+
                this.midChannel[8].noteOff(83);
                vista.resetColor(getBoto(nota));
                break;

            case "Do#"://Q DO#
                this.midChannel[8].noteOff(61);
                vista.resetColor(getBoto(nota));
                break;
            case "Re#"://W RE#
                this.midChannel[8].noteOff(63);
                vista.resetColor(getBoto(nota));
                break;
            case "Fa#"://E FA#
                this.midChannel[8].noteOff(66);
                vista.resetColor(getBoto(nota));
                break;
            case "Sol#"://R SOL#
                this.midChannel[8].noteOff(68);
                vista.resetColor(getBoto(nota));
                break;
            case "La#"://T LA#
                this.midChannel[8].noteOff(70);
                vista.resetColor(getBoto(nota));
                break;
            case "Do#+"://Y DO#+
                this.midChannel[8].noteOff(73);
                vista.resetColor(getBoto(nota));
                break;
            case "Re#+"://U RE#+
                this.midChannel[8].noteOff(75);
                vista.resetColor(getBoto(nota));
                break;
            case "Fa#+"://I FA#+
                this.midChannel[8].noteOff(78);
                vista.resetColor(getBoto(nota));
                break;
            case "Sol#+"://O SOL#+
                this.midChannel[8].noteOff(80);
                vista.resetColor(getBoto(nota));
                break;
            case "La#+"://P LA#+
                this.midChannel[8].noteOff(82);
                vista.resetColor(getBoto(nota));
                break;
            default:
                break;

        }

    }

    /**
     * Mètode per a trobar el botó clicat a partir del nom de la tecla del piano
     * @param nota
     * @return Enter corresponent al botó
     */
    public int getBoto(String nota){
        Object[][] botons = vista.getNomjTeclesPiano();
        for(Object[] s: botons){
            if (s[1].equals(nota)){
                return (Integer) s[0];
            }
        }
        return -1;
    }
}
