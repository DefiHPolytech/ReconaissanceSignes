package affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
//Timer adapt� a la gestion �venementielle
import javax.swing.Timer;

import algorithmes.Aleatoire;
import traduction.TraductionListener;
import vehicules.*;

/**
 * Cette classe affiche un panel g�n�rique dont les m�thodes sont utiles pour les 2 variantes
 * @author Julien
 *
 */
public class PanelGenerique extends JPanel implements ActionListener,TraductionListener{


	protected static final long serialVersionUID = 1L;
	protected static final int GRIDNB=10;
	protected static final int NBCARS=5;
	// Une seconde entre chaque v�rification du timer ppal
	protected static final int INTERVALLEVERIFS=1000;	
	protected ArrayList<JPanel> carList = new ArrayList<JPanel>();
	protected PanelActionManager pam;    
	
	//Pour executer une action a intervalle r�guliers
	protected Timer timer;
	
    
    public  ArrayList<JPanel> getCarList(){
        return carList;
    }
    
    /**
     * Pour remplir l'array avec les v�hicules initiaux
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
     * Faire d�marrer tous les v�hicules
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
     * Arr�ter tous les v�hicules
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
     * L'action par d�faut appliqu�e aux v�hicules sortants(remplacement sans al�atoire)
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