package FluxVideo;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.*;

public class FluxVideo {

        private int webcamNumber;
	private FrameGrabber fluxVideo = null;
	
	private static final int DEFAULTWEBCAM = 0;
	
	public FluxVideo() {
	    this(DEFAULTWEBCAM);
	}
	
	public FluxVideo(int webcamNumber) {
		this.webcamNumber = webcamNumber;
		try {
			initFluxVideo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de l'initialisation de la vidéo");
			e.printStackTrace();
		}
	}

	private void initFluxVideo() throws Exception {

		this.fluxVideo = new OpenCVFrameGrabber(webcamNumber);

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