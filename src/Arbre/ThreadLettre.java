package Arbre;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import Fenetre.FenetrePrincipale;
import Fenetre.PanneauLettre;


public class ThreadLettre implements Runnable {

	FenetrePrincipale fenetreLettre  = null;
	PanneauLettre pan = null;
	
	ImageToCvMat foret = null;
	
	IplImage imageMain = null;
	BufferedImage imageLettre = null;
	
	private static final String PATHLETTRES  = "data/img/lettres/";
	private static final String EXTLETTRES   = ".gif";
	private static final String PATHIMGERROR = "data/img/lettres/erreur.jpg";
	
	boolean continuer = true;
	
	char lettre = '\0';
	
	
	char lettrePrecedent = '\0';
	
	public ThreadLettre (){
		
		pan = new PanneauLettre();
		fenetreLettre = new FenetrePrincipale(FenetrePrincipale.LARGEUR*2, 0, this.pan);
		
		foret = new ImageToCvMat();
		foret.getTree().load("arbre", "arbre");
	}
	
	public void run() {
		
		while(continuer)
		{
			
			if(this.imageMain != null)
			{
				
				lettre = foret.Tests(imageMain);
				
				
				try {
					
					if(lettre != lettrePrecedent && lettre != '\0') {
						imageLettre = ImageIO.read(new File(PATHLETTRES + lettre + EXTLETTRES));
						System.out.println("Lettre reconnue: " + lettre);
						lettrePrecedent = lettre;
					}
					else
						imageLettre = ImageIO.read(new File(PATHIMGERROR));
					
				} catch (IOException e) {
					System.out.println("Erreur chargement de l'image " + lettre + " dans ThreadImage...");
				}
				
				
				
				
				if(imageLettre != null)
				{
					this.pan.setImage(imageLettre);
					
					try {
						Son.SonLettre.jouerSon(lettre);
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("Fichier Audio non charge : "+ lettre);
					}
				}
				
				
				//on libere la mémoire
				imageMain.deallocate();
				imageMain = null;
				
				imageLettre = null;
							
				lettrePrecedent = lettre;
				lettre = '\0';
			}
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		fenetreLettre.dispose();
		
	}
	
	public void setImage (IplImage image)
	{
		if(this.imageMain != null)
			 imageMain = null;
		
		this.imageMain = image;
		
	}
	
	public void setContinuer (boolean bool){ this.continuer = bool;}

}
