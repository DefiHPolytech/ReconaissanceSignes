package algorithmes;

import java.util.Random;

import vehicules.Camion;
import vehicules.GrosCamion;
import vehicules.Vehicule;
import vehicules.VoitureBlanche;
import vehicules.VoitureJaune;
import vehicules.VoitureRouge;
import vehicules.VoitureVerte;

public class Aleatoire {
	public static String randomLetter() {
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'A');
		return Character.toString(c);
	}

	public static boolean randomBoolean() {
		Random r = new Random();
		boolean i = r.nextBoolean();
		return i;
	}

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
