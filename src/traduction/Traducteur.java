package traduction;

import java.awt.image.BufferedImage;

import Fenetre.PanneauImageNoirEtBlanc;
import Fenetre.PanneauVideo;
import Reconaissance.ReconCouleurPeau;
import Reconaissance.ReconPeau;
import Reconaissance.ReconVisage;

import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * Classe qui reprend le "main" des IUT. On lance la caméra, reconnait le visage,
 * puis la main, la couleur de peau puis on essaie de déchiffrer ce que l'on recoit de la
 * caméra.
 * @param panneau video
 * @param traductionInterval (en millisecondes)
 */
public class Traducteur implements Runnable {

    private PanneauVideo pan;
    private PanneauImageNoirEtBlanc panNoirEtBlanc;

    // Objets de Reconnaissance
    private ReconVisage detectVisage;
    private ReconCouleurPeau couleurPeau;
    private ReconPeau detectePeau;

    //images
    private BufferedImage imageTete;
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

    /**
     * Methode qui essaie de masquer le visage après l'avoir détecté aupréalable.
     * Afin de n'avoir plus que la main sur l'image.
     * Malheureusement elle ne marche pas très bien. 
     */
    private void appliMasque() {
        imageIplPara = detectVisage.getImage().clone();

        // appli du masque
        try {
            ReconCouleurPeau.masquerObjet(imageIplPara.getBufferedImage(),
                    detectVisage.getRectangle());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'application du masque !");
        }
    }

    /**
     * Phase d'initialisation
     * On reconnait la tête, couleur de peau. Cela permet d'aider le logiciel à reconnaitre
     * plus facilement la main. En fonction de cela on fixe la configuration
     * qui va permettre de générer l'image en noir et blanc
     */
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
                    	
                        // Preparation calcul de la couleur moyenne
                        couleurPeau.setImage(imageTete);

                        // on calcule la couleur moyenne de la tête
                        calculCouleurMoyenne();

                        appliMasque();

                        // on recup la position de la main
                        detectMain();
                    }
                }
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

    /**
     * On supprime les objets que l'on n'utilise plus
     */
    private void libererMemoire() {
        try {

            imageTete = null;
            imageIplPara = null;

            detectVisage.free();
            detectVisage = null;

        } catch (Exception e) {
        	System.out.println("Erreur lors de la tentative de libération de mémoire");
        }
    }

    /**
     * Seconde phase.
     * Transformation de l'image de la main en noir et blanc, puis traduction
     * à l'aide d'un arbre de vérité
     */
    private void secondePhase() {
        System.out.println("Seconde Phase... ");
        
        while (continuer) {
            while (!pause) {

                if (pan.getImageIpl() != null && pan.getImage() != null) {
                	
                    // on actualise l'image noir et blanc
                    panNoirEtBlanc.setImage(pan.getImageIpl());

                    // on suit la main
                    detectePeau.suivreMain(pan.getImageIpl(), pan.getRectMain());

                    // on modifie les caracterisitiques du rectangle
                    rectMain = pan.getRectMain();
                    rectMain.x(detectePeau.x() - rectMain.width() / 2);
                    rectMain.y(detectePeau.y() - rectMain.height() / 2);

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
        if (!ready)
            init();        
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
