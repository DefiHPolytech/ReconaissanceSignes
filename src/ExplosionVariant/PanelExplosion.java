package ExplosionVariant;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import vehicules.Vehicule;
import affichage.Cadre;
import affichage.PanelActionManager;
import affichage.PanelGenerique;
import algorithmes.Aleatoire;
/**
 * Cette classe simule une explosion
 * @author Julien 
 *
 */
public class PanelExplosion extends PanelGenerique {
	
	private static final long serialVersionUID = 1L;
	private final double EPSILON = 0.01;
	private final double DIFFICULTEE = 8;
	
    public PanelExplosion(){
    	// On crée une grille de 1*10 col ou circuleront les voitures
        setLayout(new GridLayout(1,GRIDNB));
        instanciateArray();       
        generalStart();   
        pam=new PanelActionManager(this);        
        KeyListenerExplosion listener = new KeyListenerExplosion(pam);
        addKeyListener(listener);
        setFocusable(true);
        timer = new Timer(INTERVALLEVERIFS,this);        
        timer.start();   
        
    }
	
    /**
     * L'action a faire lorsque un véhicule est explosé / autres
     */
    @Override
	public void actionPerformed(ActionEvent e) {
	Cadre.s.diminueTemps();
	
	if(Cadre.s.getTempsRestant()<=0)
	{
		
		Cadre.s.setText("Le jeu est terminé");
		JOptionPane.showMessageDialog(null,"Vous avez fait le plus d'erreurs sur "+Cadre.e.getMaxErr()+ " avec "+ Cadre.e.getNbErr()+" erreurs");
		System.exit(0);
		
	}
	else
	{
			for (int i=0; i<trackList.size();i++){		
				// Si c'est un véhicule et nn un panel vide
				if(trackList.get(i) instanceof Vehicule)
				{
					Vehicule v = (Vehicule)trackList.get(i);
					//Si le véhicule sort on ajoute une erreur
					if(v.outOfWindow())
					{
					 Cadre.e.ajouteErreur(v.getLettre());
					}
					//Si il est sorti ou qu'il a explosé et qu'on a laissé le temps de voir l'image d'explosion
					if(v.outOfWindow() || (v.countDownZero() && !v.isRunning()))
					{
						//Alors il y a une prob. qu'on repop un véhicule sur la colonne correspondante
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
				// Il y a une probabilité faible qu'a chacke tick d'horloge un véhicule repop sur une colonne 
				else if(Math.random()<EPSILON*DIFFICULTEE)
				{
					pam.swapPanelIby(i,  Aleatoire.createRandomVehicle(NBCARS));
				}
	 }
     }
    }
    
    @Override
    public void receivedTraduction(String traduction)
    {
    	System.out.println(traduction);
        pam.actionSurVoitureLettre(traduction,"explose");  
    }
}
	

