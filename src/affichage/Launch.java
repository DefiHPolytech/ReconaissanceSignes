package affichage;

import traduction.Traducteur;
import traduction.TraductionAfficheur;
import CourseVariant.CourseKeyListener;
import Fenetre.PanneauVideo;

/**
 * @author Benjamin
 * La classe de lançement du jeu
 */
public class Launch {

	public static void main(String[] args) {
		PanneauVideo pV= new PanneauVideo();
        Traducteur traducteur = new Traducteur(pV, 1000);
        
        traducteur.addListener(new TraductionAfficheur());
        traducteur.init();

        
        Thread thTraducteur = new Thread(traducteur);
        
        Cadre c = new Cadre(Fenetre.menu,traducteur,pV);
        
        c.setVisible(true);
        thTraducteur.start();
        
		

	}

}
