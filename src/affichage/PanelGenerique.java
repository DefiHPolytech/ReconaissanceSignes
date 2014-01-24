package affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
//Timer adapté a la gestion évenementielle
import javax.swing.Timer;

import algorithmes.Aleatoire;
import traduction.TraductionListener;
import vehicules.*;

/**
 * Cette classe affiche un panel générique dont les méthodes sont utiles pour les 2 variantes
 * @author Julien
 *
 */
public class PanelGenerique extends JPanel implements ActionListener,TraductionListener{


	protected static final long serialVersionUID = 1L;
	protected static final int GRIDNB=10;
	protected static final int NBCARS=5;
	// Une seconde entre chaque vérification du timer ppal
	protected static final int INTERVALLEVERIFS=1000;	
	protected ArrayList<JPanel> carList = new ArrayList<JPanel>();
	protected PanelActionManager pam;    
	
	//Pour executer une action a intervalle réguliers
	protected Timer timer;
	
    
    public  ArrayList<JPanel> getCarList(){
        return carList;
    }
    
    /**
     * Pour remplir l'array avec les véhicules initiaux
     */
    public void instanciateArray (){
		for(int i = 0; i<GRIDNB; i++)
		if(Aleatoire.randomBoolean())
		{
			Vehicule v = Aleatoire.createRandomVehicle(NBCARS);
			carList.add(v);
			add(v);
		}
		else
		{
			JPanel p=new JPanel();
			carList.add(p);
			add(p);
		}
    }
    
  
    /**
     * Faire démarrer tous les véhicules
     */
    public void generalStart(){
        Iterator<JPanel> it = carList.iterator();
        
        while (it.hasNext()) {
               JPanel s = it.next();
               if(s instanceof Vehicule)
               {
            	   Vehicule t = (Vehicule)s;
            	   t.go();
               }
        }
    }
    
    /**
     * Arrêter tous les véhicules
     */
    public void generalStop(){
        Iterator<JPanel> it = carList.iterator();
        
        while (it.hasNext()) {
               JPanel s = it.next();
               if(s instanceof Vehicule)
               {
                   Vehicule t = (Vehicule)s;
                   t.stop();
               }
        }
    }
    
    /**
     * L'action par défaut appliquée aux véhicules sortants(remplacement sans aléatoire)
     * NB : Le timer remonte une action de type actionevent a chaque "top" d'horloge 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (int i=0; i<carList.size();i++){
               if(carList.get(i) instanceof Vehicule)
               {
            	   Vehicule v = (Vehicule)carList.get(i);
            	   if(v.outOfWindow())
            	   {
            		   System.out.println("vehicule sorti");
            		   pam.swapPanelIby(i, Aleatoire.createRandomVehicle(5));
            	   }
               }        
        }
    }

    @Override
    public void receivedTraduction(String traduction) {
        // TODO Auto-generated method stub
        
    }
}