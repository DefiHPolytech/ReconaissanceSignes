package vehicules;

public class VoitureBlanche extends Vehicule {
private static final long serialVersionUID = 1L;
private static final int vitesse = 50;
private static final String lien = "voitures/blanc.png";
    public VoitureBlanche(String lettre) {
        super(lettre);
        setImage(lien);
        setVitesse(vitesse);
    }
}
