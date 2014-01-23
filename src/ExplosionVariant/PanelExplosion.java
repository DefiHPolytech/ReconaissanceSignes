package ExplosionVariant;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import vehicules.Vehicule;
import affichage.PanelActionManager;
import affichage.PanelGenerique;
import algorithmes.Aleatoire;

public class PanelExplosion extends PanelGenerique{
	
	private static final long serialVersionUID = 1L;
	private static final double EPSILON = 0.001;
	
    public PanelExplosion(){
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
	
    @Override
	public void actionPerformed(ActionEvent e) {
	        
	for (int i=0; i<carList.size();i++){
		 
		 if(carList.get(i) instanceof Vehicule)
	     {
			 Vehicule v = (Vehicule)carList.get(i);
	          if(v.outOfWindow() || (v.countDownZero() && !v.isRunning()))
	          {
	        	    
	        	    	pam.swapPanelIby(i,  Aleatoire.createRandomVehicle(NBCARS));
	        	    
	          }	          
	     } 
		 else if(Math.random()<EPSILON)
		 {
			 pam.swapPanelIby(i,  Aleatoire.createRandomVehicle(NBCARS));
		 }
	 }
     }
}
	

