package ExplosionVariant;

import java.awt.event.KeyEvent;

import affichage.MyKeyListener;
import affichage.PanelActionManager;

/**
 * Cette classe est le listener pour la variante explosion
 * @author user
 *
 */
public class KeyListenerExplosion extends MyKeyListener{
	
    public KeyListenerExplosion(PanelActionManager action) {
		super(action);
	}
    /** 
     * Ce qu'on fait lorsque une touche est tapée
     */
	@Override
    public void keyTyped(KeyEvent arg0) {
        System.out.println(arg0.getKeyChar());
            String c = Character.toString(arg0.getKeyChar());
            actions.actionSurVoitureLettre(c,"explose");          
        }
}
