package vehicules;
/**
 * Cette classe représente une voiture jaune
 * @author user
 *
 */
public class VoitureJaune extends Vehicule {
private static final long serialVersionUID = 1L;
private static final int vitesse = 60;
private static final String lien = "voitures/jaune.png";
    public VoitureJaune(String lettre) {
        super(lettre);
        setImage(lien);
        setVitesse(vitesse);
    }
}
