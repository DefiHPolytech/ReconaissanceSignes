package affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;

import algorithmes.Aleatoire;
import vehicules.*;

public class PanelGenerique extends JPanel implements ActionListener{


	protected static final long serialVersionUID = 1L;
	protected static final int GRIDNB=10;
	protected static final int NBCARS=5;
	protected static final int VITESSEMIN=5;
	
	protected ArrayList<JPanel> carList = new ArrayList<JPanel>();
	protected PanelActionManager pam;
    
	protected Timer timer;
    public PanelGenerique(){
        
    }
    public  ArrayList<JPanel> getCarList(){
        return carList;
    }
    
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
    
    public void instanciatePanels(){
        Iterator<JPanel> it = carList.iterator();
        
        while (it.hasNext()) {
               JPanel s = it.next();
               add(s);
        }
    }
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
}