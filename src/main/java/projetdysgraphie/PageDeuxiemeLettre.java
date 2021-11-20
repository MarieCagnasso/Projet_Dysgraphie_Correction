package projetdysgraphie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedInputStream;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
//import jpen.PLevel;
//import jpen.PLevelEvent;
//import jpen.event.PenAdapter;
//import jpen.owner.multiAwt.AwtPenToolkit;
import static projetdysgraphie.PagePremiereLettre.countFiles;
import static projetdysgraphie.PagePremiereLettre.nomFichier;

public class PageDeuxiemeLettre extends javax.swing.JFrame {

    private Boolean voir = false;
    private String version = "v.1.146"; // Version du projet.
    private final int nbFichiers; // Nombre de fichiers contenus dans le dossier Dataset.
    private ArrayList<Float> Pression = new ArrayList<Float>();
    private Trace modele; // Enregistrement de la Trace de la lettre modÃ¨le.
    private Trace essai; // Enregistrement de la Trace de l'essai Ã  comparer.
    private ArrayList<Point> listPoint = new ArrayList<Point>(); // Liste des points Ã  tracer.
    private String nomFichier; // Nom du fichier enregistrÃ© dans le dossier Dataset.
    private String sexe;
    private String niveau;
    // Define constants for the various dimensions
//   public static final int CANVAS_WIDTH = 500;
//   public static final int CANVAS_HEIGHT = 300;
    public static final Color LINE_COLOR = Color.BLUE;

    // Lines drawn, consists of a List of PolyLine instances
    private List<PolyLine> lines = new ArrayList<PolyLine>();
    private Map<PolyLine, Color> Resultats = new HashMap<>();
    private PolyLine currentLine;  // the current line (for capturing)

    Tableau fichier; // CrÃ©ation du fichier Excel xls.

    private long tempsDebut; // Temps de debut.
    private int nbEssai;

    /**
     * Permet de compter le nombre de fichiers enregistrÃ©s dans le dossier
     * Dataset.
     *
     * @param parent est le chemin du dossier dans lequel compter les fichiers.
     * @return nombre e fichiers (int).
     */
    static final int countFiles(String parent) throws Exception {
        File file = new File(parent);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        return file.list().length;
    }

    /**
     * Permet de donner le nom du fichier Ã  enregistrer.
     *
     * @param nb est le nombre de fichiers dans le dossier Dataset.
     * @return le nom du fichier Ã  retourner..
     */
     static final String nomFichier(int nb, String sexe, String niveau) {
        return ("fichier" + nb +"_"+sexe+"_"+niveau+"-Modele.xls");
    }

    /**
     * Creates new form NewJFrame
     *
     * @param tModele
     */
    public PageDeuxiemeLettre(Trace tModele, int nbE, String sexe, String niveau) throws Exception {
        this.getContentPane().setBackground(Color.decode("#F69679"));
        this.nbFichiers = countFiles("C:/ProjetDysgraphie-master-" + version + "/Dataset");
        modele = tModele;
        initComponents();
        tempsDebut = System.currentTimeMillis();
        Paint();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());

        int gameHeight = (int) (Math.round(ySize * 0.50));
        int gameWidth = (int) (Math.round(xSize * 0.80));
        int gameHeight11 = (int) (Math.round(ySize * 0.12));
        int gameWidth11 = (int) (Math.round(xSize * 0.12));
        int gameHeight1 = (int) (Math.round(ySize * 0.10));
        int gameWidth2 = (int) (Math.round(xSize * 0.10));
        int gameHeight0 = (int) (Math.round(ySize * 0.69));
        int gameWidth0 = (int) (Math.round(xSize * 0.04));
        int gameHeight4 = (int) (Math.round(ySize * 0.61));
        int gameWidth4 = (int) (Math.round(xSize * 0.04));
        int gameHeight5 = (int) (Math.round(ySize * 0.67));
        int gameWidth5 = (int) (Math.round(xSize * 0.04));
        int gameHeight3 = (int) (Math.round(ySize * 0.42));
        int gameWidth3 = (int) (Math.round(xSize * 0.78));
        int gameHeight6 = (int) (Math.round(ySize * 0.62));
        int gameWidth6 = (int) (Math.round(xSize * 0.40));
        int gameHeight7 = (int) (Math.round(ySize * 0.59));
        int gameWidth7 = (int) (Math.round(xSize * 0.79));
        int gameHeight8 = (int) (Math.round(ySize * 0.75));
        int gameWidth8 = (int) (Math.round(xSize * 0.01));
        int gameHeight9 = (int) (Math.round(ySize * 0.58));
        int gameWidth9 = (int) (Math.round(xSize * 0.60));
        int gameHeight10 = (int) (Math.round(ySize * 0.15));
        int gameWidth10 = (int) (Math.round(xSize * 0.90));
        int gameHeight12 = (int) (Math.round(ySize * 0.81));
        int gameWidth12 = (int) (Math.round(xSize * 0.85));
        int gameHeight13 = (int) (Math.round(ySize * 0.61));
        int gameWidth13 = (int) (Math.round(xSize * 0.50));
        int gameHeight14 = (int) (Math.round(ySize * 0.61));
        int gameWidth14 = (int) (Math.round(xSize * 0.70));
        courbeTrace.setSize(gameWidth, gameHeight);
        jPanel2.setSize(290, 290);
        jPanel1.setSize(250, 250);
        panelImage.setSize(130, 130);
        jPanel2.setLocation(gameWidth9, gameHeight9);
        panelImage.setLocation(gameWidth8, gameHeight8);
        courbeTrace.setLocation(gameWidth2, gameHeight1);
        courbeAccel.setLocation(gameWidth0, gameHeight0);
        jPanel1.setLocation(gameWidth3, gameHeight3);
        jLabel3.setLocation(gameWidth4, gameHeight4);
        jSeparator1.setLocation(gameWidth5, gameHeight5);
        jButtonVoir.setLocation(gameWidth6, gameHeight6);
        jButtonNon.setLocation(gameWidth7, gameHeight7);
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jButton1.setLocation(gameWidth11, gameHeight11);
        jButtonVoir.setForeground(Color.WHITE);
        jButtonVoir.setBackground(Color.decode("#F04248"));
        jButtonNon.setForeground(Color.decode("#F7EF95"));
        jButtonNon.setBorderPainted(false);
        jButtonNon.setContentAreaFilled(false);
        jButtonNon.setFocusPainted(false);
        jButtonNon.setOpaque(false);
        String image = "/medias/Recommencer.png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));
        jButtonNon.setIcon(img);
        jButtonNon.setVisible(false);
        jButton2.setLocation(gameWidth12, gameHeight12);
        jPanel2.setVisible(false);
        panelImage1.setLocation(gameWidth13, gameHeight13);
        panelImage2.setLocation(gameWidth14, gameHeight14);
        jButton2.setVisible(false);
        jPanel2.setOpaque(false);
        panelImage.setOpaque(false);
        jPanel1.setOpaque(false);
        courbeAccel.setVisible(false);
        jLabel3.setVisible(false);
        jSeparator1.setVisible(false);
        panelImage1.setSize(290, 290);
        panelImage2.setSize(290, 290);
        chargerImage("font-size2", panelImage);
        chargerImage("bravo", jPanel2);
        chargerImage("elephant2-", jPanel1);
        String image2 = "/medias/arrow.png";
        chargerImage("mince", panelImage1);
        chargerImage("mince", panelImage2);
        ImageIcon img2 = new ImageIcon(getClass().getResource(image2));
        jButton2.setIcon(img2);
        jButton2.setForeground(Color.decode("#F7D28D"));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(false);
        panelImage1.setVisible(false);
        panelImage2.setVisible(false);
        panelImage1.setOpaque(false);
        panelImage2.setOpaque(false);
        this.sexe = sexe;
        this.niveau = niveau;
    }

    public PageDeuxiemeLettre(String sexe, String niveau) throws Exception {
        this.getContentPane().setBackground(Color.decode("#F69679"));
        this.nbFichiers = countFiles("C:/ProjetDysgraphie-master-" + version + "/Dataset");
        initComponents();
        tempsDebut = System.currentTimeMillis();
        Paint();
        this.sexe = sexe;
        this.niveau = niveau;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        System.out.println(xSize + "   " + ySize);
        int gameHeight = (int) (Math.round(ySize * 0.50));
        int gameWidth = (int) (Math.round(xSize * 0.80));
        int gameHeight11 = (int) (Math.round(ySize * 0.12));
        int gameWidth11 = (int) (Math.round(xSize * 0.12));
        int gameHeight1 = (int) (Math.round(ySize * 0.10));
        int gameWidth2 = (int) (Math.round(xSize * 0.10));
        int gameHeight0 = (int) (Math.round(ySize * 0.69));
        int gameWidth0 = (int) (Math.round(xSize * 0.04));
        int gameHeight4 = (int) (Math.round(ySize * 0.61));
        int gameWidth4 = (int) (Math.round(xSize * 0.04));
        int gameHeight5 = (int) (Math.round(ySize * 0.67));
        int gameWidth5 = (int) (Math.round(xSize * 0.04));
        int gameHeight3 = (int) (Math.round(ySize * 0.42));
        int gameWidth3 = (int) (Math.round(xSize * 0.78));
        int gameHeight6 = (int) (Math.round(ySize * 0.62));
        int gameWidth6 = (int) (Math.round(xSize * 0.40));
        int gameHeight7 = (int) (Math.round(ySize * 0.59));
        int gameWidth7 = (int) (Math.round(xSize * 0.79));
        int gameHeight8 = (int) (Math.round(ySize * 0.75));
        int gameWidth8 = (int) (Math.round(xSize * 0.01));
        int gameHeight9 = (int) (Math.round(ySize * 0.58));
        int gameWidth9 = (int) (Math.round(xSize * 0.60));
        int gameHeight10 = (int) (Math.round(ySize * 0.15));
        int gameWidth10 = (int) (Math.round(xSize * 0.90));
        int gameHeight12 = (int) (Math.round(ySize * 0.85));
        int gameWidth12 = (int) (Math.round(xSize * 0.85));
        int gameHeight13 = (int) (Math.round(ySize * 0.61));
        int gameWidth13 = (int) (Math.round(xSize * 0.60));
        int gameHeight14 = (int) (Math.round(ySize * 0.61));
        int gameWidth14 = (int) (Math.round(xSize * 0.70));
        System.out.println(gameHeight + "   " + ySize);
        jButton2.setLocation(gameWidth12, gameHeight12);
        courbeTrace.setSize(gameWidth, gameHeight);
        jPanel2.setSize(290, 290);
        jPanel1.setSize(250, 250);
        panelImage.setSize(130, 130);
        panelImage1.setSize(290, 290);
        panelImage2.setSize(290, 290);
        panelImage1.setLocation(gameWidth13, gameHeight13);
        panelImage2.setLocation(gameWidth14, gameHeight14);
        jPanel2.setLocation(gameWidth9, gameHeight9);
        panelImage.setLocation(gameWidth8, gameHeight8);
        courbeTrace.setLocation(gameWidth2, gameHeight1);
        courbeAccel.setLocation(gameWidth0, gameHeight0);
        jPanel1.setLocation(gameWidth3, gameHeight3);
        jLabel3.setLocation(gameWidth4, gameHeight4);
        jSeparator1.setLocation(gameWidth5, gameHeight5);
        jButtonVoir.setLocation(gameWidth6, gameHeight6);
        jButtonNon.setLocation(gameWidth7, gameHeight7);
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jButton1.setLocation(gameWidth11, gameHeight11);
        jButtonVoir.setForeground(Color.WHITE);
        jButtonVoir.setBackground(Color.decode("#F04248"));
        jButtonNon.setForeground(Color.decode("#F7EF95"));
        jButtonNon.setBorderPainted(false);
        jButtonNon.setContentAreaFilled(false);
        jButtonNon.setFocusPainted(false);
        jButtonNon.setOpaque(false);
        String image = "/medias/Recommencer.png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));
        jButtonNon.setIcon(img);
        jButtonNon.setVisible(false);
        jPanel2.setVisible(false);
        panelImage1.setVisible(false);
        panelImage2.setVisible(false);
        jPanel2.setOpaque(false);
        panelImage.setOpaque(false);
        panelImage1.setOpaque(false);
        panelImage2.setOpaque(false);
        jPanel1.setOpaque(false);
        courbeAccel.setVisible(false);
        jLabel3.setVisible(false);
        jSeparator1.setVisible(false);
        jButton2.setVisible(false);
        chargerImage("font-size2", panelImage);
        chargerImage("bravo", jPanel2);
        chargerImage("elephant2-", jPanel1);
        chargerImage("mince", panelImage1);
        chargerImage("mince", panelImage2);

        String image2 = "/medias/arrow.png";
        ImageIcon img2 = new ImageIcon(getClass().getResource(image2));
        jButton2.setIcon(img2);
        jButton2.setForeground(Color.decode("#F7D28D"));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(false);
    }

    /**
     * Permet de crÃ©er une zone de dessin dans laquelle un tracÃ© peut Ãªtre fait.
     */
    public void Paint() {
        JPanel ct = new DrawCanvas();
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(false);
        String image = "/medias/Gomme.png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));
        jButton1.setIcon(img);
        jButton1.setSize(35, 35);
        courbeTrace.removeAll();

        ct.setSize(1192, 429);
        ct.setBackground(Color.WHITE);
        ct.addMouseListener(new MouseAdapter() {
            @Override
            /**
             * Permet de tracer au clic de souris / stylo sur tablette.
             *
             * @param evt est le clic.
             */
            public void mousePressed(MouseEvent evt) {
                if (voir.equals(false)) {

                    Random rand = new Random();

// Java 'Color' class takes 3 floats, from 0 to 1.
                    float r = rand.nextFloat();
                    float g = rand.nextFloat();
                    float b = rand.nextFloat();

                    Color c = new Color(r, g, b);
                    currentLine = new PolyLine();
                    Resultats.put(currentLine, c);
                    lines.add(currentLine);
                    currentLine.addPoint(evt.getX(), evt.getY());
                }

            }
        });
        ct.addMouseMotionListener(new MouseMotionAdapter() {
            /**
             * Permet tracer quand la souris est enfoncÃ©e / le stylo appuie sur
             * la tablette.
             *
             * @param evt est le clic.
             */
            
            @Override
            public void mouseDragged(MouseEvent evt) {
                if (voir.equals(false)) {
                    currentLine.addPoint(evt.getX(), evt.getY());
                    repaint();  // invoke paintComponent()
                    long time = System.currentTimeMillis() - tempsDebut;
                    listPoint.add(new Point(evt.getX(), evt.getY(), evt.getID(), listPoint.size(), (int) time));
                }
            }
        });
//        AwtPenToolkit.addPenListener(ct, new PenAdapter(){
//                         @Override
//			                public void penLevelEvent(PLevelEvent ev){
//                                         float pressure=ev.pen.getLevelValue(PLevel.Type.PRESSURE);
//                                         Pression.add(pressure);
//                                        }
//                                        });
        courbeTrace.setLayout(new BoxLayout(courbeTrace, BoxLayout.Y_AXIS));
        courbeTrace.add(ct);
        ct.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Define inner class DrawCanvas, which is a JPanel used for custom drawing
    private class DrawCanvas extends JPanel {

        @Override
        protected void paintComponent(Graphics g) { // called back via repaint()
            super.paintComponent(g);
            g.setColor(LINE_COLOR);
            for (PolyLine line : lines) {
                line.draw(g);
            }
        }
    }

    /**
     * Permet d'afficher les graphiques calulÃ©s Ã  partir du tracÃ©.
     */
    public void afficherGraphs() {
        JPanel ca = new Courbe(essai.getPointsAcceleration());
        courbeAccel.removeAll();
        ca.setSize(courbeAccel.getSize());
        courbeAccel.add(ca);

//        JPanel c = new Courbe(essai);
//        courbeTrace.removeAll();
//        c.setSize(courbeTrace.getSize());
//        courbeTrace.add(c);
        courbeAccel.setVisible(true);
        courbeTrace.setVisible(true);
        revalidate();
        repaint();
    }

    /**
     * Permet d'enregistrer un fichier.
     */
    public void saveFile() throws Exception {
        try {
//            System.out.println("nb fichiers = " + nbFichiers);
            this.fichier = new Tableau(nomFichier(nbFichiers, sexe, niveau), "sheet1", listPoint);
        } catch (Exception ex) {
            Logger.getLogger(PagePremiereLettre.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomFichier = ("C:/ProjetDysgraphie-master-" + version + "/Dataset/" + nomFichier(nbFichiers, sexe, niveau));
    }

//    /**
//     * Charge le Trace "essai"
//     */
//    public void chargerFichier() {
//        JFileChooser f = new JFileChooser();
//        int result = f.showOpenDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File fichier = f.getSelectedFile();
//            try {
//                essai = new Trace(fichier);
//                afficherGraphs();
//                chargerImage(modele.estSimilaireAuModele(essai));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(this, "fichier : " + fichier.getAbsolutePath() + " introuvable");
//            }
//        }
//    }
    /**
     * Charge et affiche l'image selon l'exactitude de l'Ã©criture
     *
     * @param bon bon=true si elles sont assez similaires, false sinon
     */
    public void chargerImage(boolean bon, int g) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        String image;
        if (bon) {
            int gameHeight9 = (int) (Math.round(ySize * 0.65));
            int gameWidth9 = (int) (Math.round(xSize * 0.60));
            courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(39, 237, 142), 10, true));
            chargerImage("bravo", jPanel2);
            jPanel2.setLocation(gameWidth9, gameHeight9);
            jPanel2.setVisible(true);

        } else {
            switch (g) {
                case 1:
                    System.out.println("g=" + g);
                    int gameHeight9 = (int) (Math.round(ySize * 0.61));
                    int gameWidth9 = (int) (Math.round(xSize * 0.60));
                    courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 66, 72), 10, true));
                    chargerImage("mince", jPanel2);
                    jPanel2.setLocation(gameWidth9, gameHeight9);
                    jPanel2.setVisible(true);
                    break;
                case 2:
                    System.out.println("g=" + g);
                    int gameHeight10 = (int) (Math.round(ySize * 0.61));
                    int gameWidth10 = (int) (Math.round(xSize * 0.63));
                    courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 66, 72), 10, true));
                    chargerImage("mince", jPanel2);
                    jPanel2.setLocation(gameWidth10, gameHeight10);
                    jPanel2.setVisible(true);
                    int gameHeight11 = (int) (Math.round(ySize * 0.61));
                    int gameWidth11 = (int) (Math.round(xSize * 0.57));
                    courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 66, 72), 10, true));
                    chargerImage("mince", jPanel2);
                    panelImage1.setLocation(gameWidth11, gameHeight11);
                    panelImage1.setVisible(true);
                    break;
                default:
                    System.out.println("g=" + g);
                    int gameHeight12 = (int) (Math.round(ySize * 0.61));
                    int gameWidth12 = (int) (Math.round(xSize * 0.60));
                    courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 66, 72), 10, true));
                    chargerImage("mince", jPanel2);
                    jPanel2.setLocation(gameWidth12, gameHeight12);
                    courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 66, 72), 10, true));
                    panelImage1.setVisible(true);
                    panelImage2.setVisible(true);
                    jPanel2.setVisible(true);
                    break;
            }

        }

        this.revalidate();
        this.repaint();
    }

    public void chargerImage(String animal, JPanel panel) {
        String image;
        image = "/medias/" + animal + ".png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));
        JLabel label = new JLabel();
        label.setIcon(img);
        label.setSize(panel.getSize());
        panel.removeAll();
        panel.add(label);
        this.revalidate();
        this.repaint();
    }

    private void playSound(String expression) {
        {
            String son;
            son = "/medias/" + expression + ".wav";
            try {
                InputStream audioSrc = getClass().getResourceAsStream(son);
                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        courbeTrace = new RoundCanvas();
        courbeAccel = new javax.swing.JPanel();
        panelImage = new javax.swing.JPanel();
        jButtonNon = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButtonVoir = new RoundButton("Valider");
        jButton2 = new javax.swing.JButton();
        panelImage1 = new javax.swing.JPanel();
        panelImage2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFichier = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        jLabel1.setText("Entraîne-toi !");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setText("L'accélération de ton mouvement");

        courbeTrace.setBackground(new java.awt.Color(255, 255, 255));
        courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247,239,149), 10, true));

        javax.swing.GroupLayout courbeTraceLayout = new javax.swing.GroupLayout(courbeTrace);
        courbeTrace.setLayout(courbeTraceLayout);
        courbeTraceLayout.setHorizontalGroup(
            courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1193, Short.MAX_VALUE)
        );
        courbeTraceLayout.setVerticalGroup(
            courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout courbeAccelLayout = new javax.swing.GroupLayout(courbeAccel);
        courbeAccel.setLayout(courbeAccelLayout);
        courbeAccelLayout.setHorizontalGroup(
            courbeAccelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        courbeAccelLayout.setVerticalGroup(
            courbeAccelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        jButtonNon.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jButtonNon.setForeground(new java.awt.Color(0, 153, 153));
        jButtonNon.setText("Recommencer");
        jButtonNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNonActionPerformed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(208, 182));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(61, 64));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonVoir.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jButtonVoir.setForeground(new java.awt.Color(0, 153, 153));
        jButtonVoir.setText("Valider");
        jButtonVoir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoirActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton2.setText("Continuer");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        menuFichier.setText("File");
        menuFichier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuFichierMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuFichier);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(171, 171, 171)
                .addComponent(jButton1)
                .addGap(47, 47, 47)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                    .addComponent(jSeparator1))
                                .addGap(226, 226, 226)
                                .addComponent(jButtonVoir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(courbeAccel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNon, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(courbeTrace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(courbeTrace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(courbeAccel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 79, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonVoir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelImage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonNon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Permet de charger un nouvel essai lors du clic sur le menu "fichier".
     *
     * @param evt
     */
    private void menuFichierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFichierMouseClicked
//        chargerFichier();
    }//GEN-LAST:event_menuFichierMouseClicked

    /**
     * Lance la PageDeuxiemeLettre encore.
     *
     * @param evt
     */
    private void jButtonNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNonActionPerformed
        PagePremiereLettre p = null;
        try {
            p = new PagePremiereLettre( sexe, niveau);
        } catch (Exception ex) {
            Logger.getLogger(PageDeuxiemeLettre.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonNonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        lines.clear();
        Resultats.clear();
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Permet d'afficher le graphique de l'accÃ©lÃ©ration.
     *
     * @param evt
     */
    private void jButtonVoirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoirActionPerformed
        jButtonVoir.setVisible(false);
        jButton2.setVisible(true);
        jPanel1.setVisible(false);
        jButton1.setVisible(false);
        panelImage.setVisible(false);
        jSeparator1.setVisible(true);
        courbeAccel.setVisible(true);
        jLabel3.setVisible(true);
        jButtonNon.setVisible(true);
        voir = true;
        double margeErreur = 0.25;
        try {
            saveFile();
            essai = new Trace(nomFichier);
            afficherGraphs();
            switch (nbEssai) {
                case 0:
                    margeErreur = 0.25;
                    break;
                case 1:
                    margeErreur = 0.20;
                    break;
                case 2:
                    margeErreur = 0.15;
                    break;
                case 3:
                    margeErreur = 0.10;
                    break;
                case 4:
                    margeErreur = 0.10;
                    break;
                default:
                    margeErreur = 0.25;
                    break;
            }
            System.out.println("nbEssai = " + nbEssai + " et marge Erreur = " + margeErreur);
            int g = modele.Evaluation(essai, margeErreur);
            chargerImage(modele.estSimilaireAuModele(essai, margeErreur), g);
            if (modele.estSimilaireAuModele(essai, margeErreur)) {
                playSound("gagner");
            } else {
                playSound("ohmince");
            }
        } catch (Exception ex) {
            System.out.println("ProblÃ¨me Page 2");
            Logger.getLogger(PagePremiereLettre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonVoirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PageDeuxiemeLettre p = null;
        nbEssai++;
        System.out.println("nb E = " + nbEssai);
        try {
            System.out.println("nb fichiers = " + nbFichiers);
            this.fichier = new Tableau(nomFichier(nbFichiers, sexe, niveau), "sheet1", listPoint);
                p = new PageDeuxiemeLettre(modele, nbEssai, sexe, niveau);
                p.setVisible(true);
            
        } catch (Exception ex) {
            Logger.getLogger(PagePremiereLettre.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        Font myFont = new Font("Comic Sans MS", Font.ITALIC, 18);

        jButton2.setFont(myFont);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited

        Font myFont = new Font("Comic Sans MS", Font.PLAIN, 18);

        jButton2.setFont(myFont);
    }//GEN-LAST:event_jButton2MouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel courbeAccel;
    private javax.swing.JPanel courbeTrace;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonNon;
    private javax.swing.JButton jButtonVoir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenu menuFichier;
    private javax.swing.JPanel panelImage;
    private javax.swing.JPanel panelImage1;
    private javax.swing.JPanel panelImage2;
    // End of variables declaration//GEN-END:variables
}
