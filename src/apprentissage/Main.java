package apprentissage;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Arbre.ImageToCvMat;
import Fenetre.FenetrePrincipale;
import Fenetre.PanneauLettre;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Main {
	
	private static final String PATHLETTRES = "data/img/lettres_signes/";
	public static final String ALPHABET    = "abcdefghijklmnopqrstuvwxyz";
	
	private static final String PATHLSF = "data/img/lsf/";

public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
				
		int nbPhotoLettre = 0;
		boolean recommence = false;
				
		threadMain thFenetre = new threadMain();
		PanneauLettre panneauLettre = new PanneauLettre();		
		FenetrePrincipale fp = new FenetrePrincipale(300, 0, panneauLettre);
		fp.setResizable(false);
		Thread thread = new Thread(thFenetre);
		thread.start();
		
		IplImage image;
		
		do
		{
			recommence = false;
			System.out.println("Combiens de photos voulez vous pour chaque lettre ? ");
			
			try
			{
				nbPhotoLettre = scan.nextInt();
			}catch(Exception e){
				recommence = true;
				scan.nextLine();
			}
			
		} while (recommence);
		
		scan.nextLine();
		File folder = new File(PATHLETTRES);
		folder.mkdir();
		
		for (int i = 0; i < ALPHABET.length(); ++i) {
			try {
				panneauLettre.setImage(ImageIO.read(new File(PATHLSF + ALPHABET.charAt(i) + ".jpg")));
				panneauLettre.setSize(120, 90);
				fp.setSize(120, 120);
			} catch (Exception e) {
				e.printStackTrace();
			}
				System.out.println("Capture de la lettre " + ALPHABET.charAt(i));
			System.out.println("Appuyer sur une touche quand vous êtes près... ");
			scan.nextLine();
			System.out.println("Demarage de la capture...");
			
			for(int iPhoto = 0; iPhoto < nbPhotoLettre; ++iPhoto)
			{
				System.out.println(iPhoto);
				image = thFenetre.getCapture();
				
				String nomPhoto = PATHLETTRES + ALPHABET.charAt(i) + iPhoto + ".jpg";
				
				cvSaveImage(nomPhoto, image);
								
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Lettre " + ALPHABET.charAt(i) + " terminée");
		}
		
		int nbPhoto = nbPhotoLettre * ALPHABET.length();
		System.out.println("Creation arbre pour " + nbPhoto + " images");
		
		ImageToCvMat arbre = new ImageToCvMat(nbPhoto);
		arbre.Apprentissage(PATHLETTRES);
		
		//Nettoyage des images
		for (File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
		
		System.out.println("Aprentissage terminé");
		
		scan.close();
		thFenetre.stop();
	}
}
