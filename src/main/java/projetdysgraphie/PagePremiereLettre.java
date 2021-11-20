package projetdysgraphie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
//import jpen.PLevel;
//import jpen.PLevelEvent;
//import jpen.event.PenAdapter;
//import jpen.owner.multiAwt.AwtPenToolkit;

public class PagePremiereLettre extends javax.swing.JFrame {

    private String version = "v.1.146"; // Version du projet.
    private final int nbFichiers; // Nombre de fichiers contenus dans le dossier Dataset.

    private Trace tModele; // Enregistrement de la Trace de la lettre modÃ¨le.
    private ArrayList<Point> listPoint = new ArrayList<Point>(); // Liste des points Ã  tracer.
    private String nomFichier; // Nom du fichier enregistrÃ© dans le dossier Dataset.
    private Boolean voir = false;
    // Define constants for the various dimensions
//   public static final int CANVAS_WIDTH = 500;
//   public static final int CANVAS_HEIGHT = 300;
    public static Color LINE_COLOR = Color.BLUE; // Couleur d'Ã©criture (bleu par dÃ©faut).
    private String sexe;
    private String niveau;

    // Lines drawn, consists of a List of PolyLine instances
    private List<PolyLine> lines = new ArrayList<PolyLine>();
    private ArrayList<Float> Pression = new ArrayList<Float>();
    private Map<PolyLine, Color> Resultats = new HashMap<>();
    private List<Color> couleurs = new ArrayList<Color>();
    private PolyLine currentLine; // the current line (for capturing)

    Tableau fichier; // CrÃ©ation du fichier Excel xls.

    private long tempsDebut; // Temps de debut.

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
        return ("fichier" + nb + "_" + sexe + "_" + niveau + "-Modele.xls");
    }

    /**
     * Creates new form NewJFrame
     *
     * @param t
     */
    public PagePremiereLettre(Trace t, String sexe, String niveau) throws Exception {
        this.getContentPane().setBackground(Color.decode("#F69679"));
        this.nbFichiers = countFiles("./Dataset");
        tModele = t;
        initComponents();
        this.sexe = sexe;
        this.niveau = niveau;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());

        int gameHeight = (int) (Math.round(ySize * 0.50));
        int gameWidth = (int) (Math.round(xSize * 0.80));
        int gameHeight11 = (int) (Math.round(ySize * 0.12));
        int gameWidth11 = (int) (Math.round(xSize * 0.12));
        int gameHeight1 = (int) (Math.round(ySize * 0.50));
        int gameWidth2 = (int) (Math.round(xSize * 0.50));
        int gameHeight0 = (int) (Math.round(ySize * 0.69));
        int gameWidth0 = (int) (Math.round(xSize * 0.04));
        int gameHeight3 = (int) (Math.round(ySize * 0.42));
        int gameWidth3 = (int) (Math.round(xSize * 0.78));
        int gameHeight4 = (int) (Math.round(ySize * 0.63));
        int gameWidth4 = (int) (Math.round(xSize * 0.04));
        int gameHeight5 = (int) (Math.round(ySize * 0.67));
        int gameWidth5 = (int) (Math.round(xSize * 0.04));
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
        int gameHeight12 = (int) (Math.round(ySize * 0.70));
        int gameWidth12 = (int) (Math.round(xSize * 0.80));
        jPanel7.setSize(130, 130);
        jPanel6.setSize(290, 290);
        jPanel3.setSize(130, 130);
        jPanel5.setSize(250, 250);
        jButton2.setLocation(gameWidth12, gameHeight12);
        jPanel6.setLocation(gameWidth9, gameHeight9);
        jPanel7.setLocation(gameWidth10, gameHeight10);
        jButton1.setLocation(gameWidth11, gameHeight11);
        courbeTrace.setSize(new Dimension(gameWidth, gameHeight));
        courbeTrace.setLocation(gameWidth2, gameHeight1);
        courbeAccel.setLocation(gameWidth0, gameHeight0);
        jPanel5.setLocation(gameWidth3, gameHeight3);
        jPanel3.setLocation(gameWidth8, gameHeight8);
        jLabel3.setLocation(gameWidth4, gameHeight4);
        jSeparator1.setLocation(gameWidth5, gameHeight5);
        jButtonVoir.setLocation(gameWidth6, gameHeight6);
        jButtonNon.setLocation(gameWidth7, gameHeight7);
        jButton2.setVisible(false);
        JLabel label = new JLabel("");
        label.setHorizontalAlignment(JLabel.CENTER);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        label.setBorder(line);
        tempsDebut = System.currentTimeMillis();
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jButtonVoir.setBorder(line);
        jButtonVoir.setForeground(Color.WHITE);
        jButtonVoir.setBackground(Color.decode("#F04248"));
        jButtonNon.setForeground(Color.decode("#F7EF95"));
        jButtonNon.setBorderPainted(false);
        jButtonNon.setContentAreaFilled(false);
        jButtonNon.setFocusPainted(false);
        jButtonNon.setOpaque(false);
        String image = "/medias/Recommencer.png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));
        courbeAccel.setVisible(false);
        jLabel3.setVisible(false);
        jSeparator1.setVisible(false);
        jButtonNon.setIcon(img);
        jButtonNon.setVisible(false);
        jPanel6.setOpaque(false);
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        jPanel7.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel5.setOpaque(false);
        chargerImage("font-size2", jPanel3);
        chargerImage("elephant2-", jPanel5);
        chargerImage("singe", jPanel6);
        chargerImage("bear", jPanel7);
        String image2 = "/medias/arrow.png";
        ImageIcon img2 = new ImageIcon(getClass().getResource(image2));
        jButton2.setIcon(img2);
        jButton2.setForeground(Color.decode("#F7D28D"));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(false);
 
    }

    public PagePremiereLettre(String sexe, String niveau) throws Exception {
        this.getContentPane().setBackground(Color.decode("#F69679"));
        this.nbFichiers = countFiles("./Dataset");
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
        int gameHeight4 = (int) (Math.round(ySize * 0.63));
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
        int gameHeight12 = (int) (Math.round(ySize * 0.80));
        int gameWidth12 = (int) (Math.round(xSize * 0.85));
        System.out.println(gameHeight + "   " + ySize);
        jButton2.setVisible(false);
        courbeTrace.setSize(gameWidth, gameHeight);
        jButton2.setLocation(gameWidth12, gameHeight12);
        jPanel7.setSize(130, 130);
        jPanel6.setSize(290, 290);
        jPanel5.setSize(250, 250);
        jPanel3.setSize(130, 130);
        jPanel6.setLocation(gameWidth9, gameHeight9);
        jPanel7.setLocation(gameWidth10, gameHeight10);
        jPanel3.setLocation(gameWidth8, gameHeight8);
        courbeTrace.setLocation(gameWidth2, gameHeight1);
        courbeAccel.setLocation(gameWidth0, gameHeight0);
        jPanel5.setLocation(gameWidth3, gameHeight3);
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
        String image2 = "/medias/arrow.png";
        ImageIcon img2 = new ImageIcon(getClass().getResource(image2));
        jButton2.setIcon(img2);
        jButton2.setHorizontalTextPosition(SwingConstants.LEFT);
        jButton2.setForeground(Color.decode("#F7D28D"));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setOpaque(false);
        jButtonNon.setVisible(false);
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        jPanel6.setOpaque(false);
        jPanel7.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel5.setOpaque(false);
        courbeAccel.setVisible(false);
        jLabel3.setVisible(false);
        jSeparator1.setVisible(false);
        chargerImage("font-size2", jPanel3);
        chargerImage("elephant2-", jPanel5);
        chargerImage("singe", jPanel6);
        chargerImage("bear", jPanel7);

    }

    public void Paint() {
        JPanel ct = new DrawCanvas();
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(false);
        String image = "/medias/Gomme.png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));
        jButton1.setIcon(img);
        jButton1.setSize(40, 40);
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
//                System.out.println("X =" + evt.getX() + " Y =" + evt.getY() + " id =" + listPoint.size() ) ;
//                System.out.println(System.currentTimeMillis()-tempsDebut);
                    long time = System.currentTimeMillis() - tempsDebut;
                    listPoint.add(new Point(evt.getX(), evt.getY(), listPoint.size(), 0, (int) time));
                    if (listPoint.size() > 1) {
                        listPoint.get(listPoint.size() - 1).setInter(listPoint.get(listPoint.size() - 1).IntervalleEntre(listPoint.get(listPoint.size() - 2)));
                    } else {
                        listPoint.get(listPoint.size() - 1).setInter(listPoint.get(listPoint.size() - 1).getTime());
                    }
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
            if (voir.equals(false)) {// called back via repaint()
                super.paintComponent(g);
                g.setColor(LINE_COLOR);

                for (PolyLine line : lines) {
                    line.draw(g);
                }
            } else {
                super.paintComponent(g);

                for (Map.Entry<PolyLine, Color> entry : Resultats.entrySet()) {
                    Color couleur = entry.getValue();
                    g.setColor(couleur);
                    PolyLine line = entry.getKey();
                    line.draw(g);
                }
            }
        }
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
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * Permet d'afficher les graphiques calulÃ©s Ã  partir du tracÃ©.
     */
    public void afficherGraphs() {
        JPanel ca = new Courbe(tModele.getPointsAcceleration());
        courbeAccel.removeAll();
        ca.setSize(courbeAccel.getSize());
        courbeAccel.add(ca);

//        JPanel c = new Courbe(tModele);
//        courbeTrace.removeAll();
//        c.setSize(courbeTrace.getSize());
//        courbeTrace.add(c);
        courbeAccel.setVisible(true);
        courbeTrace.setVisible(true);
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
        nomFichier = ("./Dataset/" + nomFichier(nbFichiers, sexe, niveau));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonNon = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        courbeAccel = new javax.swing.JPanel();
        courbeTrace = new RoundCanvas();
        jButtonVoir = new RoundButton("Voir");
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFichier = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 204));
        setForeground(java.awt.Color.pink);

        jButtonNon.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jButtonNon.setForeground(new java.awt.Color(0, 153, 153));
        jButtonNon.setText("Recommencer");
        jButtonNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNonActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(51, 255, 204));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        jLabel1.setText("Ecris ta lettre modèle");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setText("L'accélération de ton mouvement");

        javax.swing.GroupLayout courbeAccelLayout = new javax.swing.GroupLayout(courbeAccel);
        courbeAccel.setLayout(courbeAccelLayout);
        courbeAccelLayout.setHorizontalGroup(
            courbeAccelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );
        courbeAccelLayout.setVerticalGroup(
            courbeAccelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        courbeTrace.setBackground(new java.awt.Color(255, 255, 255));
        courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247,239,149), 10, true));
        courbeTrace.setMinimumSize(new java.awt.Dimension(20, 300));

        javax.swing.GroupLayout courbeTraceLayout = new javax.swing.GroupLayout(courbeTrace);
        courbeTrace.setLayout(courbeTraceLayout);
        courbeTraceLayout.setHorizontalGroup(
            courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        courbeTraceLayout.setVerticalGroup(
            courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        jButtonVoir.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jButtonVoir.setForeground(new java.awt.Color(0, 153, 153));
        jButtonVoir.setText("Valider");
        jButtonVoir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoirActionPerformed(evt);
            }
        });

        jPanel3.setPreferredSize(new java.awt.Dimension(70, 90));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 132, Short.MAX_VALUE)
        );

        jPanel6.setPreferredSize(new java.awt.Dimension(170, 170));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jPanel7.setPreferredSize(new java.awt.Dimension(170, 170));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 126, Short.MAX_VALUE)
        );

        jPanel5.setPreferredSize(new java.awt.Dimension(170, 170));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton2.setText("Suivant");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(courbeAccel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel3))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonVoir, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonNon, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2))
                        .addGap(79, 79, 79)
                        .addComponent(jButton1)
                        .addGap(68, 68, 68)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(courbeTrace, javax.swing.GroupLayout.PREFERRED_SIZE, 1203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)))
                .addComponent(courbeTrace, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(291, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButtonVoir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(courbeAccel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Lance la PagePremiereLettre lettre lors du clic sur le jButtonNon.
     *
     * @param evt
     */
    private void jButtonNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNonActionPerformed
        // TODO add your handling code here:
        PagePremiereLettre p = null;
        try {
            p = new PagePremiereLettre(sexe, niveau);
        } catch (Exception ex) {
            Logger.getLogger(PagePremiereLettre.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonNonActionPerformed

    /**
     * Ouvre l'explorateur de fichier et permet de selectionner un fichier CSV
     * Ã  Lire lors du clic sur le menu fichier.
     *
     * @param evt
     */
    private void menuFichierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFichierMouseClicked
        JFileChooser f = new JFileChooser();
        int result = f.showOpenDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File fichier = f.getSelectedFile();
//            try {
//                tModele = new Trace(fichier);
//                afficherGraphs();
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(this, "fichier : " + fichier.getAbsolutePath() + " introuvable");
//            }
//        }
    }//GEN-LAST:event_menuFichierMouseClicked

    /**
     * Permet d'afficher le graphique de l'accÃ©lÃ©ration.
     *
     * @param evt
     */
    private void jButtonVoirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoirActionPerformed
        jButton2.setVisible(true);
        jPanel6.setVisible(true);
        jPanel7.setVisible(true);
        jButtonVoir.setVisible(false);
        jPanel5.setVisible(false);
        jButton1.setVisible(false);
        jPanel3.setVisible(false);
        courbeAccel.setVisible(true);
        jLabel3.setVisible(true);
        jSeparator1.setVisible(true);
        jButtonNon.setVisible(true);
        try {
            saveFile();
            tModele = new Trace(nomFichier);
            voir = true;
            afficherGraphs();
        } catch (Exception ex) {
            System.out.println("ProblÃ¨me Page 1");
            Logger.getLogger(PagePremiereLettre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonVoirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        lines.clear();
        Resultats.clear();
        repaint();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PageDeuxiemeLettre p = null;
        try {
            p = new PageDeuxiemeLettre(tModele, 0, sexe, niveau);
        } catch (Exception ex) {
            Logger.getLogger(PagePremiereLettre.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setVisible(true);
        try {
            saveFile();
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenu menuFichier;
    // End of variables declaration//GEN-END:variables
}
