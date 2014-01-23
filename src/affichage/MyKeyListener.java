
package affichage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Benjamin
 * Cette classe v�rifie si une touche � �t� appuy�e. 
 * Elle sera enlev�e dans la version finale ou le listener sera associ� a la webcam.
 * Cette classe est appell�e par d�faut !
 */
public class MyKeyListener implements KeyListener {

    
    protected PanelActionManager actions;
    public MyKeyListener (PanelActionManager action){
        actions = action;
    }
    
    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    /**
     * @param arg0
     */
    public void keyTyped(KeyEvent arg0) {
        System.out.println(arg0.getKeyChar());
            String c = Character.toString(arg0.getKeyChar());
            actions.actionSurVoitureLettre(c,"stop");      
            
           
        }
    
    

    
    
}









