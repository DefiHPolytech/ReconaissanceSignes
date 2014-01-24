package traduction;

import java.awt.image.BufferedImage;

import Fenetre.PanneauImageNoirEtBlanc;
import Fenetre.PanneauVideo;
import Reconaissance.ReconCouleurPeau;
import Reconaissance.ReconPeau;
import Reconaissance.ReconVisage;

import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Traducteur implements Runnable {

    private PanneauVideo pan;
    private PanneauImageNoirEtBlanc panNoirEtBlanc;

    // Objet Reconnaissance
    private ReconVisage detectVisage;
    private ReconCouleurPeau couleurPeau;
    private ReconPeau detectePeau;

    private BufferedImage imageTete;
    private BufferedImage imageParallele;
    private IplImage imageIplPara;

    private ThreadTraduction thTraduction;
    
    private boolean continuer;
    private boolean pause;
    private boolean ready;
    
    private CvRect rectMain = null;
    
    private int traductionInterval;
    // couleur Moyenne
    private float[] HSV;

    public Traducteur(PanneauVideo video, int traductionInterval) {
        continuer = true;
        pause = false;
        ready = false;
        
        this.traductionInterval = traductionInterval;
        
        pan = video;
        panNoirEtBlanc = new PanneauImageNoirEtBlanc();

        // Objet Reconnaissance
        detectVisage = new ReconVisage();
        couleurPeau = new ReconCouleurPeau();
        detectePeau = new ReconPeau();
        
        thTraduction = new ThreadTraduction();
    }

    private void detectMain() {
        detectePeau.recupMain(imageIplPara);
        pan.setPosRectangleMain(
                detectePeau.x() - pan.getRectMain().width() / 2,
                detectePeau.y() - pan.getRectMain().height() / 2);
    }

    private void calculCouleurMoyenne() {
        HSV = ReconCouleurPeau.RGBtoHSV(couleurPeau.getCouleurMoyen()[0],
                couleurPeau.getCouleurMoyen()[1],
                couleurPeau.getCouleurMoyen()[2]);
        ReconPeau.setIntervalleH((int) (HSV[0] * 160));

    }

    private void appliMasque() {
        // on essaye mtn de masquer le visage
        // pour recuperer la main
        // on travail sur une image parallele
        imageIplPara = detectVisage.getImage().clone();
        // l'image du debut du traitement
        imageParallele = imageIplPara.getBufferedImage();

        // appli du masque
        try {
            ReconCouleurPeau.masquerObjet(imageParallele,
                    detectVisage.getRectangle());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'application du masque");
            System.out.println(e);
        }
    }

    public void init() {
        System.out.println("Première Phase... ");

        int compteur = 20;

        while (compteur > 0) {
            if (pan.getImageIpl() != null) {

                // fenetre noir et blanc
                panNoirEtBlanc.setImage(pan.getImageIpl());

                // on recup l'image du flux
                detectVisage.setImage(panNoirEtBlanc.getImage());

                // on detecte le visage
                detectVisage.detecterLesVisages();

                // si on detecte le visage
                if (detectVisage.getRectangle() != null) {

                    // on dessine sur le flux un ovale autour de la tête
                    pan.setRectangleVisage(detectVisage.getRectangle());

                    // on recupere l'image de la tête
                    imageTete = detectVisage.recupObjetBuffer();

                    if (imageTete != null) {
                        // Preparation calcul couleur moyenne
                        couleurPeau.setImage(imageTete);

                        // on calcule la couleur moyenne de la tête
                        calculCouleurMoyenne();

                        appliMasque();

                        // on recup la position de la main
                        detectMain();

                        // on libere la mémoire
                        imageParallele = null;
                        imageIplPara = null;
                        imageTete = null;

                    }

                    detectVisage.setRectangle(null);

                }

                detectVisage.setImage(null);

            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(compteur);
            compteur--;
        }
        
        // on libere la mémoire pour la seconde phase
        libererMemoire();
        
        Thread th = new Thread(thTraduction);

        th.start();

        ready = true;
    }

    private void libererMemoire() {
        try {

            imageTete = null;
            imageParallele = null;
            imageIplPara = null;

            detectVisage.free();
            detectVisage = null;

        } catch (Exception e) {
        }
    }

    private void secondePhase() {
        System.out.println("Seconde Phase... ");
        
        while (continuer) {
            while (!pause) {

                if (pan.getImageIpl() != null && pan.getImage() != null) {
                    // on actualise l'image noir et blanc
                    panNoirEtBlanc.setImage(pan.getImageIpl());

                    // on suit la main
                    detectePeau
                            .suivreMain(pan.getImageIpl(), pan.getRectMain());

                    // on modifie les caracterisitiques du rectangle
                    rectMain = pan.getRectMain();
                    rectMain.x(detectePeau.x() - rectMain.width() / 2);
                    rectMain.y(detectePeau.y() - rectMain.height() / 2);

                    // detectePeau.DetectCouleur(pan.getImageIpl());
                    // rectMain =
                    // ReconPeau.adaptRectangleObjet(detectePeau.getImage(),
                    // rectMain);

                    // on modifie la position du rectangle sur l'affichage
                    if (rectMain != null)
                        pan.setRectangleMain(rectMain);

                    // on teste l'arbre
                    thTraduction
                            .setImage(IplImage.createFrom(ReconVisage
                                    .recupObjetBuffer(pan.getImage(),
                                            pan.getRectMain())));

                }

                if (!continuer)
                    break;

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            // si on a appuye sur "arret" on attend
            try {
                Thread.sleep(traductionInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        ready = false;

    }
    
    public void addListener (TraductionListener listener) {        
        thTraduction.addListener(listener);
    }

    public void stop() {
        thTraduction.setContinuer(false);
    }

    public void lancerTraductions() {
        secondePhase();
    }
    
    public void setPause (boolean bool) {
        pause = bool;
    }
    
    public void setContinue (boolean bool) {
        continuer = bool;
    }
    
    public void setTraductionInterval (int interval) {
        traductionInterval = interval;
    }
    
    public boolean isReady() {
        return ready;
    }
    
    public void run() {
        lancerTraductions();
    }

    public static void main(String[] args) {
        Traducteur traducteur = new Traducteur(new PanneauVideo(), 1000);
        
        traducteur.addListener(new TraductionAfficheur());
        
        traducteur.init();
        
        Thread thTraducteur = new Thread(traducteur);
        thTraducteur.start();
        
    }

}
