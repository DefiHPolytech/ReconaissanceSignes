package algorithmes;

import java.util.Random;

import vehicules.*;
 
/**
 * Cette classe permet de générer l'aléatoire ds le jeu
 * @author user
 *
 */
public class Aleatoire {
	
	/**
	 * Avoir une lettre aléatoire
	 * @return
	 */
	public static String randomLetter() {
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'A');
		return Character.toString(c);
	}

	/**
	 * Avoir un booléen aléatoire
	 * @return i
	 */
	public static boolean randomBoolean() {
		Random r = new Random();
		boolean i = r.nextBoolean();
		return i;
	}

	
	   /**
     * Avoir un booléen aléatoire (avec une probabilité de succés de i%)
     * @return
     */
    public static boolean randomBoolean(int i) {
        Random r = new Random();
        int c = r.nextInt(99);
        return c<i;
    }

    
    
	/**
	 * Avoir un véhicule aléatoire 
	 * @param nbCars
	 * @return v
	 */
	public static int RandomCar(int nbCars) {
		Random r = new Random();
		int i = r.nextInt(nbCars);
		return i;
	}

	public static Vehicule createRandomVehicle(int nbcars) {
		String lettre = Aleatoire.randomLetter();
		Vehicule v;
		switch (Aleatoire.RandomCar(nbcars)) {
		case 0:
			v = new VoitureRouge(lettre);
			break;
		case 1:
			v = new VoitureVerte(lettre);
			break;
		case 2:
			v = new VoitureBlanche(lettre);
			break;
		case 3:
			v = new VoitureJaune(lettre);
			break;
		case 4:
			v = new Camion(lettre);
			break;
		case 5:
			v = new GrosCamion(lettre);
			break;
		default:
			v = new GrosCamion(lettre);
			break;
		}
		
			
		return v;
	}
}
