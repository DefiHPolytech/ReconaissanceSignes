package vehicules;

public class VoitureRouge extends Vehicule{

	private static final long serialVersionUID = 1L;
	private static final int vitesse = 30;
	private static final String lien = "voitures/rouge.png";
	public VoitureRouge(String lettre) {
		super(lettre);
		setImage(lien);
		setVitesse(vitesse);
	}
	
}
