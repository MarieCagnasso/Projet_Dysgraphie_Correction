package projetdysgraphie;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Trace {

    private List<Point> myPoints;

    /**
     * créé un objet tracé correspondant au CSV source
     *
     * @param source
     * @throws IOException
     */
//    public Trace(File source) throws IOException{
//        Scanner sc = new Scanner(source);
//        Lecteur l;
//        if(!sc.next().equals("Identification")){
//            l = new Lecteur(source);
//        }
//        else{
//           sc.nextLine();
//           sc.nextLine();
//           sc.nextLine();
//           sc.nextLine();
//           String input ="";
//           while(sc.hasNextLine()){
//               input+="\n"+sc.nextLine();
//           }
//           StringReader sourceStr = new StringReader(input);
//           l = new Lecteur(sourceStr);
//        }
//        myPoints = l.lire();
//        Double averageInterval = getAverageInter();
//        for(Point p:myPoints){
//            p.setInter((int) Math.round(averageInterval));
//        }
//    }
    public Trace(String name) throws IOException {
        Lecteur l = new Lecteur(name);
        myPoints = l.lire(name);
        Double averageInterval = getAverageInter();
        for (Point p : myPoints) {
            p.setInter((int) Math.round(averageInterval));
        }
    }

    public Trace(List<Point> mP) {
        myPoints = mP;
    }

    /**
     *
     * @return points contenus dans le tracé
     */
    public List<Point> getPoint() {
        return myPoints;
    }

    /**
     * Calcule l'acceleration en tout point du trace, sauf le premier et le
     * dernier. Se sert de la moyenne des intervalles du trace au lieu de celle
     * réelle pour éviter les faux pics d'acceleration.
     *
     * @return liste des valeurs d'acceleration du trace
     */
    public List<Double> getAcceleration() {
        List<Double> res = new ArrayList();
        Double averageInterval = getAverageInter();
        for (int i = 1; i < myPoints.size() - 1; i++) {
            Point pAv = myPoints.get(i - 1);
            Point pMi = myPoints.get(i);
            Point pAp = myPoints.get(i + 1);
            Double acc = (pAv.vitesseEntre(pMi) - pMi.vitesseEntre(pAp)) / ((double) 2 * averageInterval);
            res.add(acc);
        }
        return res;
    }

    /**
     *
     * @return la moyenne d'intervalles de temps entre les points
     */
    public double getAverageInter() {
        double res = 0;
        for (Point p : myPoints) {
            res += p.getInterval();
        }
        res = res / myPoints.size();
        return res;
    }

    /**
     *
     * @return une liste de points affichages de valeur x=num et y=acceleration
     */
    public List<PointAffichage> getPointsAcceleration() {
        ArrayList<PointAffichage> res = new ArrayList();
        List<Double> accel = getAcceleration();
        Double min = accel.get(0);
        for (int i = 0; i < accel.size(); i++) {
            double y = accel.get(i);
            PointAffichage p = new PointAffichage(i, y);
            res.add(p);
            if (y < min) {
                min = y;
            }
        }
        if (min < 0) {
            for (PointAffichage p : res) {
                p.setY(p.getY() - min);
            }
        }
        return res;
    }

    /**
     * Fait la moyenne de toutes les valeurs remises en positif d'une liste
     *
     * @param l
     * @return
     */
    public double moyennePositive(List<Double> l) {
        double res = 0;
        for (double d : l) {
            res += Math.abs(d);
        }
        res = res / l.size();
        return res;
    }

    /**
     *
     * @return le nombre de pic d'acceleration supérieurs à 1/2*moyenne
     * d'acceleration positive du tracé
     */
    public int getNbPic() {
        int res = 0;
        int signe = 0;
        List<Double> accel = getAcceleration();
        double average = moyennePositive(accel);
        if (accel.get(0) - accel.get(1) < 0) {
            signe = 1;
        } else {
            signe = -1;
        }
        for (int i = 0; i < accel.size() - 1; i++) {
            double signeAct = accel.get(i) - accel.get(i + 1);
            if (((signe < 0 && signeAct > 0)
                    || (signe > 0 && signeAct < 0))
                    && Math.abs(signeAct) > average * 0.5) {

                signe = -signe;
                res++;
            }
        }
        return res;
    }

    /**
     * Verifie l'essai est semblable au modèle avec une marge d'erreur de 20%
     * (compare le nombre de pics)
     *
     * @param essai tracé à verifier
     * @return
     */
    public boolean estSimilaireAuModelePrems(Trace essai, double margeErreur) {
        int nbPicModele = getNbPic();
        int nbPicEssai = essai.getNbPic();
//        double margeErreur = 0.20;
        boolean res = Math.abs(nbPicModele - nbPicEssai) <= nbPicModele * (0.8 - margeErreur);
        return res;
    }

    public boolean estSimilaireAuModeleAussi(Trace essai, double margeErreur) {
        HashMap<Double, Double> distanceOld = new HashMap<Double, Double>();
        HashMap<Double, Double> distancenew = new HashMap<Double, Double>();
        ArrayList<Double> distanceXi = new ArrayList<Double>();
        ArrayList<Double> distanceYi = new ArrayList<Double>();
        ArrayList<Double> distanceX = new ArrayList<Double>();
        ArrayList<Double> distanceY = new ArrayList<Double>();
        int bonPoints = 0;
        List<Point> pics = this.getPoint();
        List<Point> picse = essai.getPoint();
        int j = 1;
        int k = 1;
        for (int i = 0; i < pics.size() - 1; i++) {
            double x = pics.get(i).getX();
            double x0 = pics.get(j).getX();
            double y = pics.get(i).getY();
            double y0 = pics.get(j).getY();
            double distance1 = x0 - x;
            double distance3 = y0 - y;
            distanceOld.put(distance1, distance3);
            j += 1;

        }
        for (int p = 0; p < picse.size() - 1; p++) {

            double x1 = picse.get(p).getX();
            double x2 = picse.get(k).getX();
            double y1 = picse.get(p).getY();
            double y2 = picse.get(k).getY();
            double distance2 = x2 - x1;
            double distance4 = y2 - y1;
            distancenew.put(distance2, distance4);
            k += 1;
        }
        for (Map.Entry<Double, Double> entry : distanceOld.entrySet()) {
            for (Map.Entry<Double, Double> entry1 : distancenew.entrySet()) {
                Double x = entry.getKey();
                Double x1 = entry1.getKey();
                Double y = entry.getValue();
                Double y1 = entry1.getValue();
                if (x.equals(x1) || x.equals(x1 + 1) || x.equals(x1 - 1) || x.equals(x1 + 2) || x.equals(x1 - 2) || x.equals(x1 + 3) || x.equals(x1 - 3) || x.equals(x1 + 4) || x.equals(x1 - 4)){// || x.equals(x1 + 5) || x.equals(x1 - 5)) {
                    if (y.equals(y1) || y.equals(y1 + 1) || x.equals(y1 - 1) || y.equals(y1 + 2) || x.equals(y1 - 2) || y.equals(y1 + 3) || x.equals(y1 - 3) || y.equals(y1 + 4) || x.equals(y1 - 4)){//|| y.equals(y1 + 5) || x.equals(y1 - 5)) {
                        bonPoints += 1;
                    }
                }
            }
        }

        int size = pics.size();
        int siz = picse.size();
        boolean res = false;
        if (size <= siz) {
            if(size - bonPoints>0){
            res = Math.abs(size - bonPoints) <= size * (0.8 - margeErreur);
            }
        } else {
             if(siz - bonPoints>0){
            res = Math.abs(siz - bonPoints) <= siz * (0.8 - margeErreur);
             }
        }
        return res;
    }

    public boolean estSimilaireAuModele(Trace essai, double margeErreur) {
        boolean res1 = this.estSimilaireAuModelePrems(essai, margeErreur);
        boolean res2 = this.estSimilaireAuModeleAussi(essai, margeErreur);
        boolean res = false;
        if (res1) {
            if (res2) {
                res = true;
            }
        }
        return res;
    }

    public int Evaluation(Trace essai, double margeErreur) {
        HashMap<Double, Double> distanceOld = new HashMap<Double, Double>();
        HashMap<Double, Double> distancenew = new HashMap<Double, Double>();
        ArrayList<Double> distanceXi = new ArrayList<Double>();
        ArrayList<Double> distanceYi = new ArrayList<Double>();
        ArrayList<Double> distanceX = new ArrayList<Double>();
        ArrayList<Double> distanceY = new ArrayList<Double>();
        int bonPoints = 0;
        List<Point> pics = this.getPoint();
        List<Point> picse = essai.getPoint();
        int j = 1;
        int k = 1;
        for (int i = 0; i < pics.size() - 1; i++) {
            double x = pics.get(i).getX();
            double x0 = pics.get(j).getX();
            double y = pics.get(i).getY();
            double y0 = pics.get(j).getY();
            double distance1 = x0 - x;
            double distance3 = y0 - y;
            distanceOld.put(distance1, distance3);
            j += 1;

        }
        for (int p = 0; p < picse.size() - 1; p++) {

            double x1 = picse.get(p).getX();
            double x2 = picse.get(k).getX();
            double y1 = picse.get(p).getY();
            double y2 = picse.get(k).getY();
            double distance2 = x2 - x1;
            double distance4 = y2 - y1;
            distancenew.put(distance2, distance4);
            k += 1;
        }
        for (Map.Entry<Double, Double> entry : distanceOld.entrySet()) {
            for (Map.Entry<Double, Double> entry1 : distancenew.entrySet()) {
                Double x = entry.getKey();
                Double x1 = entry1.getKey();
                Double y = entry.getValue();
                Double y1 = entry1.getValue();
                if (x.equals(x1) || x.equals(x1 + 1) || x.equals(x1 - 1) || x.equals(x1 + 2) || x.equals(x1 - 2) || x.equals(x1 + 3) || x.equals(x1 - 3) || x.equals(x1 + 4) || x.equals(x1 - 4)){//|| x.equals(x1 + 5) || x.equals(x1 - 5)) {
                    if (y.equals(y1) || y.equals(y1 + 1) || x.equals(y1 - 1) || y.equals(y1 + 2) || x.equals(y1 - 2) || y.equals(y1 + 3) || x.equals(y1 - 3) || y.equals(y1 + 4) || x.equals(y1 - 4)){// || y.equals(y1 + 5) || x.equals(y1 - 5)) {
                        bonPoints += 1;
                    }
                }
            }
        }
        
        int nbchip = 0;
        int size = pics.size();
        int siz = picse.size();
        System.out.println("bonpoints="+bonPoints);
        System.out.println("size="+size);
        System.out.println("siz="+siz);
        boolean res = false;
        if (size <= siz) {
             if(size - bonPoints>0){
            res = Math.abs(size - bonPoints) <= size * (0.8 - margeErreur);
            }
            if(res){
                System.out.println("je suis laaaaaaaaaaaaaa");
                nbchip=1;
            int l = (int) (size * (0.8 - margeErreur));
            int f = size - bonPoints;
            int g=(size-l)/3;
             System.out.println("l="+l);
            System.out.println("f="+f);
            System.out.println("g="+g);
            if (f<=g && f>l) {
                nbchip=1;
            }
            else if (f<=(g*2)) {
                nbchip=2;
            }
             else {
                nbchip=3;
            }
            }
        } else {
             if(siz - bonPoints>0){
            res = Math.abs(siz - bonPoints) <= siz * (0.8 - margeErreur);
             }
            if (res){
                System.out.println("je suis laaaaaaaaaaaaaa");
                nbchip=1;
            }else{
             int l = (int) (siz * (0.8 - margeErreur));
            int f = siz - bonPoints;
            int g=(siz-l)/3;
            System.out.println("l="+l);
            System.out.println("f="+f);
            System.out.println("g="+g);
            if (f<=g) {
                nbchip=1;
            }
            else if (f<=(g*2)) {
                nbchip=2;
            }
             else  {
                nbchip=3;
            }
        }
        }
System.out.println("nbchip="+nbchip);
        return nbchip;
    }
}
