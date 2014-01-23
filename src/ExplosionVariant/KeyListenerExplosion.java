package ExplosionVariant;

import java.awt.event.KeyEvent;

import affichage.MyKeyListener;
import affichage.PanelActionManager;

public class KeyListenerExplosion extends MyKeyListener{
	
    public KeyListenerExplosion(PanelActionManager action) {
		super(action);
	}

	@Override
    public void keyTyped(KeyEvent arg0) {
        System.out.println(arg0.getKeyChar());
            String c = Character.toString(arg0.getKeyChar());
            actions.actionSurVoitureLettre(c,"explose");          
        }
}
