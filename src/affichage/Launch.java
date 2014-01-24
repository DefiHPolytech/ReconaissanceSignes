package affichage;

import traduction.Traducteur;
import traduction.TraductionAfficheur;
import Fenetre.PanneauVideo;

/**
 * @author Benjamin
 * La classe de lançement du jeu
 */
public class Launch {

	public static void main(String[] args) {
		
        Traducteur traducteur = new Traducteur(new PanneauVideo(), 1000);
        
        traducteur.addListener(new TraductionAfficheur());
        
        traducteur.init();

        
        Thread thTraducteur = new Thread(traducteur);
        
        Cadre c = new Cadre(Fenetre.menu,traducteur);
        c.setVisible(true);
        thTraducteur.start();
        
		

	}

}
