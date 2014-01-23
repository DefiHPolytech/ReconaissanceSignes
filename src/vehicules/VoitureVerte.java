package vehicules;

/**
 * Cette classe représente une voiture verte
 * @author user
 *
 */
public class VoitureVerte extends Vehicule{
	private static final long serialVersionUID = 1L;
	private static final int vitesse = 35; 
	private static final String lien = "voitures/verte.png";
	public VoitureVerte(String lettre) {
		super(lettre);
		setImage(lien);
		setVitesse(vitesse);
	}	
}