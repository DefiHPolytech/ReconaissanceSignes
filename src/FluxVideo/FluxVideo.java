package FluxVideo;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.*;

public class FluxVideo {


	private FrameGrabber fluxVideo = null;
	
	public FluxVideo() {
		
		try {
			initFluxVideo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de l'initialisation de la vidéo");
			e.printStackTrace();
		}
	}

	private void initFluxVideo() throws Exception {

		this.fluxVideo = new OpenCVFrameGrabber(0);

		if (this.fluxVideo == null)
			throw new Exception("Probleme d'initialisation de la webcam... ");
		this.fluxVideo.start();
	}

	public IplImage getImageFlux() throws Exception {
		return fluxVideo.grab();
	}


	public FrameGrabber getFluxVideo() {
		return this.fluxVideo;
	}

	
}