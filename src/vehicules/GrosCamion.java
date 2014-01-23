package vehicules;
/**
 * Cette classe représente un gros camion
 * @author user
 *
 */
public class GrosCamion extends Vehicule {

private static final long serialVersionUID = 1L;
private static final int vitesse = 80;
private static final String lien = "voitures/groscamion.png";
    public GrosCamion(String lettre) {
        super(lettre);
        setImage(lien);
        setVitesse(vitesse);
    }
    
}
