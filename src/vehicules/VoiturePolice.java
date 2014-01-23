package vehicules;
/**
 * Cette classe représente une voiture de police
 * @author user
 *
 */
public class VoiturePolice extends Vehicule {
private static final long serialVersionUID = 1L;
private static final int vitesse = 80;
private static final String lien = "voitures/policeCar.png";
    public VoiturePolice(String lettre) {
        super(lettre);
        setImage(lien);
        setVitesse(vitesse);
    }
}
