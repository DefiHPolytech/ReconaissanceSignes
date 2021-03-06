package Main;

import java.awt.image.BufferedImage;

import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import Arbre.ThreadLettre;
import Fenetre.FenetrePrincipale;
import Fenetre.PanneauImageNoirEtBlanc;
import Fenetre.PanneauVideo;
import Reconaissance.ReconCouleurPeau;
import Reconaissance.ReconPeau;
import Reconaissance.ReconVisage;

public class Main {

	/**
	 * @param args
	 */

	public static void main(final String[] args) {

		PanneauVideo pan = new PanneauVideo();
		PanneauImageNoirEtBlanc panNoirEtBlanc = new PanneauImageNoirEtBlanc();

		FenetrePrincipale fenetreNoirEtBlanc = new FenetrePrincipale(
				FenetrePrincipale.LARGEUR, 0, panNoirEtBlanc);
		FenetrePrincipale fenetre = new FenetrePrincipale(0, 0, pan);

		// Objet Reconnaissance
		ReconVisage detectVisage = new ReconVisage();
		ReconCouleurPeau couleurPeau = new ReconCouleurPeau();
		ReconPeau detectePeau = new ReconPeau();

		BufferedImage imageTete = null;
		BufferedImage imageParallele = null;
		IplImage imageIplPara = null;
		// couleur Moyenne
		float[] HSV;

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

					// on dessine sur le flux un ovale autour de la t�te
					pan.setRectangleVisage(detectVisage.getRectangle());

					// on recupere l'image de la t�te
					imageTete = detectVisage.recupObjetBuffer();

					if (imageTete != null) {
						// la methode cacule aussi la couleurMoyenne
						couleurPeau.setImage(imageTete);

						// on calcule la couleur moyenne de la t�te
						HSV = ReconCouleurPeau.RGBtoHSV(
								couleurPeau.getCouleurMoyen()[0],
								couleurPeau.getCouleurMoyen()[1],
								couleurPeau.getCouleurMoyen()[2]);

						ReconPeau.setIntervalleH((int) (HSV[0] * 160));

						// on essaye mtn de masquer le visage
						// pour recuperer la main
						// on travail sur une image parallele
						imageIplPara = detectVisage.getImage().clone();
						// l'image du debut du traitement
						imageParallele = imageIplPara.getBufferedImage(); 

						// appli du masque
						try {
							ReconCouleurPeau.masquerObjet(imageIplPara.getBufferedImage(),
									detectVisage.getRectangle());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out
									.println("Erreur lors de l'application du masque");
							System.out.println(e);
						}

						// on recup la position de la main
						detectePeau.recupMain(imageIplPara);
						pan.setPosRectangleMain(detectePeau.x()
								- pan.getRectMain().width() / 2,
								detectePeau.y() - pan.getRectMain().height()
										/ 2);

						// on libere la m�moire
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(compteur);
			compteur--;
		}

		System.out.println("Seconde Phase... ");

		// on libere la m�moire pour la seconde phase
		try {

			imageTete = null;
			imageParallele = null;
			imageIplPara = null;

			detectVisage.free();
			detectVisage = null;

		} catch (Exception e) {
			System.out.println("Erreur lors de la lib�ration de la m�moire");
		}

		ThreadLettre thLettre = new ThreadLettre();
		Thread th = new Thread(thLettre);

		th.start();

		CvRect rectMain = null;

		while (!fenetre.appliEstQuitter()) {
			while (!fenetre.appliEstArrete()) {

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
					thLettre.setImage(IplImage.createFrom(ReconVisage
							.recupObjetBuffer(pan.getImage(), pan.getRectMain())));

				}

				if (fenetre.appliEstQuitter())
					break;

				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// si on a appuye sur "arret" on attend
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fenetreNoirEtBlanc.dispose();
		thLettre.setContinuer(false);

	}

}
