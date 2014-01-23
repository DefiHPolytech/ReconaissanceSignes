package affichage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



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
    public void keyTyped(KeyEvent arg0) {
        System.out.println(arg0.getKeyChar());
            String c = Character.toString(arg0.getKeyChar());
            actions.actionSurVoitureLettre(c,"stop");      
            
           
        }
    
    

    
    
}









