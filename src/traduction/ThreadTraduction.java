package traduction;

import java.util.ArrayList;
import java.util.List;

import Arbre.ImageToCvMat;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class ThreadTraduction implements Runnable {
	ImageToCvMat foret = null;
	IplImage imageMain = null;

	boolean continuer = true;

	char lettre = '\0';
	char lettrePrecedent = '\0';
		
	List <TraductionListener> listeners;
	
	public ThreadTraduction() {

		foret = new ImageToCvMat();
		foret.getTree().load("arbre", "arbre");
		
		listeners = new ArrayList<>();
	}

	public void run() {

		while (continuer) {

			if (this.imageMain != null) {

				lettre = foret.Tests(imageMain);
				
				notifyTraduction("" + lettre);
				
				// on libere la mémoire
				imageMain.deallocate();

				lettrePrecedent = lettre;
				lettre = '\0';
			}

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void setImage(IplImage image) {
		if (this.imageMain != null)
			imageMain = null;

		this.imageMain = image;

	}

	public void setContinuer(boolean bool) {
		this.continuer = bool;
	}
	
	public void addListener (TraductionListener listener) {
		listeners.add(listener);
	}
	
	public void notifyTraduction (String traduction) {
		for (TraductionListener listener : listeners)
			listener.receivedTraduction(traduction);
	}

}
