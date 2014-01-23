package traduction;

public class TraductionAfficheur implements TraductionListener {
	public void receivedTraduction(String traduction) {
		System.out.println("Traduction recue " + traduction);
	}
}
