package projetdysgraphie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Insets;
import static java.awt.SystemColor.window;
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
import javax.swing.JColorChooser;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class PagePremiere extends javax.swing.JFrame {

    private String version = "v.1.14"; // Version du projet.
    private final int nbFichiers; // Nombre de fichiers contenus dans le dossier Dataset.

    private Trace tModele; // Enregistrement de la Trace de la lettre modèle.
    private ArrayList<Point> listPoint = new ArrayList<Point>(); // Liste des points à tracer.
    private String nomFichier; // Nom du fichier enregistré dans le dossier Dataset.
    private Boolean voir = false;
    // Define constants for the various dimensions
//   public static final int CANVAS_WIDTH = 500;
//   public static final int CANVAS_HEIGHT = 300;
    public static Color LINE_COLOR = Color.BLUE; // Couleur d'écriture (bleu par défaut).
    private String sexe;
    private String niveau;

    // Lines drawn, consists of a List of PolyLine instances
    private ArrayList<Float> Pression = new ArrayList<Float>();
    // Lines drawn, consists of a List of PolyLine instances
    private List<PolyLine> lines = new ArrayList<PolyLine>();
    private Map<PolyLine, Color> Resultats = new HashMap<>();
    private List<Color> couleurs = new ArrayList<Color>();
    private PolyLine currentLine; // the current line (for capturing)

    Tableau fichier; // Création du fichier Excel xls.

    private long tempsDebut; // Temps de debut.

    /**
     * Permet de compter le nombre de fichiers enregistrés dans le dossier
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
     * Permet de donner le nom du fichier à enregistrer.
     *
     * @param nb est le nombre de fichiers dans le dossier Dataset.
     * @return le nom du fichier à retourner..
     */
    static final String nomFichier(int nb, String sexe, String niveau) {
        return ("fichier" + nb + "_" + sexe + "_" + niveau + "-Modele.xls");
    }

    /**
     * Creates new form NewJFrame
     *
     * @param t
     */
    public PagePremiere(Trace t) throws Exception {
        this.getContentPane().setBackground(Color.decode("#F69679"));
        this.nbFichiers = countFiles("./Dataset");
        initComponents();
        tempsDebut = System.currentTimeMillis();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        System.out.println(xSize + "   " + ySize);
        int gameHeight = (int) (Math.round(ySize * 0.70));
        int gameWidth = (int) (Math.round(xSize * 0.40));
        int gameHeight1 = (int) (Math.round(ySize * 0.08));
        int gameWidth2 = (int) (Math.round(xSize * 0.30));
        int gameHeight3 = (int) (Math.round(ySize * 0.60));
        int gameWidth3 = (int) (Math.round(xSize * 0.82));
        int gameHeight6 = (int) (Math.round(ySize * 0.80));
        int gameWidth6 = (int) (Math.round(xSize * 0.43));
        int gameHeight8 = (int) (Math.round(ySize * 0.75));
        int gameWidth8 = (int) (Math.round(xSize * 0.01));
        int gameHeight9 = (int) (Math.round(ySize * 0.62));
        int gameWidth9 = (int) (Math.round(xSize * 0.04));
        int gameHeight10 = (int) (Math.round(ySize * 0.67));
        int gameWidth10 = (int) (Math.round(xSize * 0.08));
        int gameHeight11 = (int) (Math.round(ySize * 0.72));
        int gameWidth11 = (int) (Math.round(xSize * 0.13));
        int gameHeight12 = (int) (Math.round(ySize * 0.15));
        int gameWidth12 = (int) (Math.round(xSize * 0.45));
        int gameHeight13 = (int) (Math.round(ySize * 0.30));
        int gameWidth13 = (int) (Math.round(xSize * 0.46));
        int gameHeight14 = (int) (Math.round(ySize * 0.40));
        int gameWidth14 = (int) (Math.round(xSize * 0.46));
        choice1.setLocation(gameWidth13, gameHeight13);
        choice2.setLocation(gameWidth14, gameHeight14);
        choice1.setSize(150, 150);
        choice2.setSize(150, 150);
        jLabel1.setLocation(gameWidth12, gameHeight12);
        courbeTrace.setSize(500, 450);
        jPanel8.setSize(150, 150);
        jPanel7.setSize(90, 90);
        jPanel6.setSize(50, 50);
        jPanel5.setSize(250, 250);
        jPanel3.setSize(130, 130);
        jPanel9.setSize(300, 300);
        jPanel6.setLocation(gameWidth9, gameHeight9);
        jPanel7.setLocation(gameWidth10, gameHeight10);
        jPanel8.setLocation(gameWidth11, gameHeight11);
        jPanel3.setLocation(gameWidth8, gameHeight8);
        courbeTrace.setLocation(gameWidth2, gameHeight1);
        jPanel5.setLocation(gameWidth3, gameHeight3);
        jButtonVoir.setLocation(gameWidth6, gameHeight6);
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jButtonVoir.setForeground(Color.WHITE);
        jButtonVoir.setBackground(Color.decode("#F04248"));
        String image = "/medias/ifp.png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));
        jPanel6.setOpaque(false);
        jPanel9.setOpaque(false);
        jPanel7.setOpaque(false);
        jPanel8.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel5.setOpaque(false);
        chargerImage("font-size2", jPanel3);
        chargerImage("elephant2-", jPanel5);
        chargerImage("main", jPanel6);
        chargerImage("horloge", jPanel7);
        chargerImage("tablette", jPanel8);
        chargerImage("child", jPanel9);
        choice1.add("Ton sexe");
        choice1.add("Garçon");
        choice1.add("Fille");
        choice2.add("Ton niveau scolaire");
        choice2.add("CP");
        choice2.add("CE1");
        choice2.add("CE2");
        choice2.add("CM1");
        choice2.add("CM2");
        choice2.add("6ème");
        choice2.add("5ème");
        choice2.add("4ème");
        choice2.add("3ème");
        courbeTrace.requestFocus();

    }

    public PagePremiere() throws Exception {
        this.getContentPane().setBackground(Color.decode("#F69679"));
        this.nbFichiers = countFiles("./Dataset");
        initComponents();
        tempsDebut = System.currentTimeMillis();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        System.out.println(xSize + "   " + ySize);
        courbeTrace.setSize(500, 450);
        int gameHeight = (int) (Math.round(ySize * 0.10));
        int gameWidth = (int) (Math.round(xSize * 0.10));
        int gameHeight1 = (int) (Math.round(ySize * 0.08));
        int gameWidth2 = (int) (Math.round(xSize * 0.30));
        int gameHeight3 = (int) (Math.round(ySize * 0.60));
        int gameWidth3 = (int) (Math.round(xSize * 0.82));
        int gameHeight6 = (int) (Math.round(ySize * 0.80));
        int gameWidth6 = (int) (Math.round(xSize * 0.43));
        int gameHeight8 = (int) (Math.round(ySize * 0.70));
        int gameWidth8 = (int) (Math.round(xSize * 0.01));
        int gameHeight9 = (int) (Math.round(ySize * 0.62));
        int gameWidth9 = (int) (Math.round(xSize * 0.04));
        int gameHeight10 = (int) (Math.round(ySize * 0.67));
        int gameWidth10 = (int) (Math.round(xSize * 0.08));
        int gameHeight11 = (int) (Math.round(ySize * 0.72));
        int gameWidth11 = (int) (Math.round(xSize * 0.13));
        int gameHeight12 = (int) (Math.round(ySize * 0.15));
        int gameWidth12 = (int) (Math.round(xSize * 0.45));
        int gameHeight13 = (int) (Math.round(ySize * 0.30));
        int gameWidth13 = (int) (Math.round(xSize * 0.46));
        int gameHeight14 = (int) (Math.round(ySize * 0.40));
        int gameWidth14 = (int) (Math.round(xSize * 0.46));
        choice1.setLocation(gameWidth13, gameHeight13);
        choice2.setLocation(gameWidth14, gameHeight14);
        choice1.setSize(150, 150);
        choice2.setSize(150, 150);
        jLabel1.setLocation(gameWidth12, gameHeight12);
        jPanel8.setSize(150, 150);
        jPanel7.setSize(90, 90);
        jPanel6.setSize(50, 50);
        jPanel5.setSize(250, 250);
        jPanel3.setSize(130, 130);
        jPanel9.setSize(300, 300);
        choice1.add("Ton sexe");
        choice1.add("Garçon");
        choice1.add("Fille");
        choice2.add("Ton niveau scolaire");
        choice2.add("CP");
        choice2.add("CE1");
        choice2.add("CE2");
        choice2.add("CM1");
        choice2.add("CM2");
        choice2.add("6ème");
        choice2.add("5ème");
        choice2.add("4ème");
        choice2.add("3ème");
        jPanel6.setLocation(gameWidth9, gameHeight9);
        jPanel7.setLocation(gameWidth10, gameHeight10);
        jPanel8.setLocation(gameWidth11, gameHeight11);
        jPanel3.setLocation(gameWidth8, gameHeight8);
        courbeTrace.setLocation(gameWidth2, gameHeight1);
        jPanel5.setLocation(gameWidth3, gameHeight3);
        jButtonVoir.setLocation(gameWidth6, gameHeight6);
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jButtonVoir.setForeground(Color.WHITE);
        jButtonVoir.setBackground(Color.decode("#F04248"));
        String image = "/medias/ifp.png";
        ImageIcon img = new ImageIcon(getClass().getResource(image));

        jPanel6.setOpaque(false);
        jPanel9.setOpaque(false);
        jPanel7.setOpaque(false);
        jPanel8.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel5.setOpaque(false);
        chargerImage("font-size2", jPanel3);
        chargerImage("elephant2-", jPanel5);
        chargerImage("main", jPanel6);
        chargerImage("horloge", jPanel7);
        chargerImage("tablette", jPanel8);
        chargerImage("child", jPanel9);
        courbeTrace.requestFocus();
        courbeTrace.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                courbeTrace.requestFocus();
            }
        });
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
     * Permet d'afficher les graphiques calulés à partir du tracé.
     */
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

        popupMenu1 = new java.awt.PopupMenu();
        popupMenu2 = new java.awt.PopupMenu();
        courbeTrace = new RoundCanvas();
        jLabel1 = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();
        choice2 = new java.awt.Choice();
        jPanel9 = new javax.swing.JPanel();
        jButtonVoir = new RoundButton("Voir");
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFichier = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 204));
        setForeground(java.awt.Color.pink);

        courbeTrace.setBackground(new java.awt.Color(255, 255, 255));
        courbeTrace.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247,239,149), 10, true));
        courbeTrace.setMinimumSize(new java.awt.Dimension(20, 300));

        jLabel1.setBackground(new java.awt.Color(51, 255, 204));
        jLabel1.setFont(new java.awt.Font("Bradley Hand ITC", 0, 48)); // NOI18N
        jLabel1.setText("Bienvenue!");

        jPanel9.setPreferredSize(new java.awt.Dimension(170, 170));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout courbeTraceLayout = new javax.swing.GroupLayout(courbeTrace);
        courbeTrace.setLayout(courbeTraceLayout);
        courbeTraceLayout.setHorizontalGroup(
            courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courbeTraceLayout.createSequentialGroup()
                .addGroup(courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(courbeTraceLayout.createSequentialGroup()
                        .addGroup(courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(courbeTraceLayout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabel1))
                            .addGroup(courbeTraceLayout.createSequentialGroup()
                                .addGap(191, 191, 191)
                                .addGroup(courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 172, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, courbeTraceLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        courbeTraceLayout.setVerticalGroup(
            courbeTraceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courbeTraceLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        jPanel8.setPreferredSize(new java.awt.Dimension(170, 170));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 126, Short.MAX_VALUE)
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
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(courbeTrace, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVoir, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(260, 260, 260))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(courbeTrace, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonVoir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(1475, 1475, 1475)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ouvre l'explorateur de fichier et permet de selectionner un fichier CSV à
     * Lire lors du clic sur le menu fichier.
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
     * Permet d'afficher le graphique de l'accélération.
     *
     * @param evt
     */
    private void jButtonVoirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoirActionPerformed
        if (choice1.getSelectedItem().equals("Ton sexe")) {
        } else if (choice2.getSelectedItem().equals("Ton niveau scolaire")) {
        } else {
            this.sexe = choice1.getSelectedItem();
            this.niveau = choice2.getSelectedItem();
            PagePremiereLettre p = null;
            try {
                p = new PagePremiereLettre(sexe, niveau);
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
        }
    }//GEN-LAST:event_jButtonVoirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private java.awt.Choice choice2;
    private javax.swing.JPanel courbeTrace;
    private javax.swing.JButton jButtonVoir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JMenu menuFichier;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    // End of variables declaration//GEN-END:variables
}
