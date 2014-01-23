package affichage;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import vehicules.Vehicule;

public class PanelActionManager {

PanelGenerique pC;

/**
 * @author benjamin
 * @param pC
 */
public PanelActionManager(PanelGenerique pC) {
    this.pC = pC;
}

/**
 * Renvoie l'indice dans la Liste des Panels (Vehicules) des vehicules ayant la
 * lettre c
 * 
 * @param c
 *            charactere considéré
 * @return
 */
public ArrayList<Integer> getCarsWithLetter(String c){
    ArrayList<Integer> indices= new ArrayList<Integer>();
    ArrayList<JPanel> carList = pC.getCarList();
    System.out.println("String c: "+c);

    for (int i=0; i<carList.size();i++){
        if (carList.get(i) instanceof Vehicule){
            Vehicule v = (Vehicule)carList.get(i);
            System.out.println("lettre vehicule"+v.getLettre());
            if (v.getLettre().toLowerCase().equals(c)){
                indices.add(i);
                System.out.println(i);
            }
        }
    }
    return indices;
    
}

/**
 * Cette méthode ralentit/accèlère/fait exploser une voiture
 * Le code est similaire pour ces trois méthodes ce pourquoi on a factorisé le code
 * @param c
 * @param action
 */
public void actionSurVoitureLettre(String c, String action) {
    ArrayList<Integer> indices = getCarsWithLetter(c);
    Iterator<Integer> it = indices.iterator();
    while (it.hasNext()) {
        int i = it.next();
        Vehicule v = (Vehicule) pC.getCarList().get(i);
        switch(action)
        {
        case "explose":
            v.stop();
        	v.explose();
        	v.setCountDown(100);
        case "start":
        	v.go();   
        case "stop":
        	v.stop();
        case "slow":
            v.slow();
            v.setCountDown(100);
        }       
        pC.getCarList().set(i,v);
    }
	
}

/**
 * Cette méthode détruit un véhicule
 * @param jPanel
 */
public void detruire(JPanel jPanel) {
    if (jPanel instanceof Vehicule){
        Vehicule vehicule = (Vehicule) jPanel;
        vehicule.destroyTimer();
    }
    jPanel = null;
}

/**
 * Cette méthode remplace un Panel ou Vehicule par un nouveau véhicule
 * @param i
 * @param v
 */
public void swapPanelIby(int i, Vehicule v) {
    ArrayList<JPanel> carList = pC.getCarList();
    detruire(carList.get(i));
    pC.remove(carList.get(i));
    carList.set(i, v);
    pC.add(carList.get(i), i);
    
    Vehicule vehicule = (Vehicule) carList.get(i);
    vehicule.go();
    pC.invalidate();
    pC.validate();
}

/**
 * Cette méthode remplace un véhicule par un panel vide
 * @param i
 */
public void replaceVehiculebyPanel(int i)
{
	ArrayList<JPanel> carList = pC.getCarList();
    detruire(carList.get(i));
    pC.remove(carList.get(i));
    carList.set(i, new JPanel());
    pC.add(carList.get(i), i);
    pC.invalidate();
    pC.validate();
}

}
