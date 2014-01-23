package ExplosionVariant;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import vehicules.Vehicule;
import affichage.PanelActionManager;
import affichage.PanelGenerique;
import algorithmes.Aleatoire;
/**
 * Cette classe simule une explosion
 * @author Julien 
 *
 */
public class PanelExplosion extends PanelGenerique{
	
	private static final long serialVersionUID = 1L;
	private static final double EPSILON = 0.01;
	private static final double DIFFICULTEE = 1;
	
	
    public PanelExplosion(){
    	// On cr�e une grille de 1*10 col ou circuleront les voitures
        setLayout(new GridLayout(1,GRIDNB));
        instanciateArray();       
        generalStart();   
        pam=new PanelActionManager(this);        
        KeyListenerExplosion listener = new KeyListenerExplosion(pam);
        addKeyListener(listener);
        setFocusable(true);
        timer = new Timer(VITESSEMIN,this);        
        timer.start();
    }
	
    /**
     * L'action a faire lorsque un v�hicule est explos� / autres
     */
    @Override
	public void actionPerformed(ActionEvent e) {
	        
	for (int i=0; i<carList.size();i++){
		 // Si c'est un v�hicule et nn un panel vide
		 if(carList.get(i) instanceof Vehicule)
	     {
			 Vehicule v = (Vehicule)carList.get(i);
			 //Si il est sorti ou qu'il a explos� et qu'on a laiss� le temps de voir l'image d'explosion
	          if(v.outOfWindow() || (v.countDownZero() && !v.isRunning()))
	          {
	        	  //Alors il y a une prob. qu'on repop un v�hicule sur la colonne correspondante
	        	    	if(Aleatoire.randomBoolean())
	        	    	{
	        	    		pam.swapPanelIby(i,  Aleatoire.createRandomVehicle(NBCARS));
	        	    	}
	        	    	else
	        	    	{
	        	    		pam.replaceVehiculebyPanel(i);
	        	    	}	        	    
	          }	          
	     } 
		 // Il y a une probabilit� faible qu'a chacke tick d'horloge un v�hicule repop sur une colonne 
		 else if(Math.random()<EPSILON*DIFFICULTEE)
		 {
			 pam.swapPanelIby(i,  Aleatoire.createRandomVehicle(NBCARS));
		 }
	 }
     }
}
	

