package vehicules;
/**
 * Cette classe représente un camion
 * @author user
 *
 */
public class Camion extends Vehicule{

private static final long serialVersionUID = 1L;
private static final int vitesse = 70;
private static final String lien = "voitures/camion.png";
    public Camion(String lettre) {
        super(lettre);
        setImage(lien);
        setVitesse(vitesse);
    }
}
