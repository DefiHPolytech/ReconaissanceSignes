package apprentissage;


import Arbre.Recup;
import FluxVideo.FluxVideo;
import Reconaissance.ReconPeau;
import Teste.VerifierImage.Fenetre;

import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class threadMain implements Runnable {

	
	Fenetre fenetreCAM = null;
	Fenetre fenetreNoirBlanc = null;
	
	
	FluxVideo flux = null;
	
	IplImage imageCapture = null;
	
	ReconPeau objDetect = null;
	
	boolean run = true;
	
	public threadMain()
	{
		fenetreCAM = new Fenetre(Teste.VerifierImage.Main.LARGEUR, Teste.VerifierImage.Main.HAUTEUR);
		fenetreNoirBlanc  = new Fenetre(Teste.VerifierImage.Main.LARGEUR, Teste.VerifierImage.Main.HAUTEUR);
		
		fenetreNoirBlanc.setLocation(Teste.VerifierImage.Main.LARGEUR+20,0);
		
		flux = new FluxVideo();
		objDetect = new ReconPeau();
		
	}
	
	public void stop() {
		run = false;
	}
	
	public void run (){
		
		while(run)
		{
			
			try {
				 imageCapture =  Recup.redimImage(flux.getFluxVideo().grab(),Teste.VerifierImage.Main.LARGEUR,
								 Teste.VerifierImage.Main.HAUTEUR).clone();
				 
				 objDetect.DetectCouleur(flux.getImageFlux().clone());
				 
				 fenetreCAM.setImage(imageCapture.getBufferedImage());
				 fenetreCAM.repaint();
				 
				 fenetreNoirBlanc.setImage(objDetect.getImage().getBufferedImage());
				 fenetreNoirBlanc.repaint();
				 
				 Thread.sleep(10);

			} catch (Exception | InterruptedException e) {
				e.printStackTrace();
			}
		}
		fenetreCAM.dispose();
		fenetreNoirBlanc.dispose();
		this.stop();
	}
	
	
	public IplImage getCapture (){ return this.imageCapture;}

}
