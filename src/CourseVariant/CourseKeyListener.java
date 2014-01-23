package CourseVariant;

import java.awt.event.KeyEvent;

import affichage.PanelActionManager;

public class CourseKeyListener extends affichage.MyKeyListener{

    public CourseKeyListener(PanelActionManager action) {
        super(action);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        System.out.println(arg0.getKeyChar());
            String c = Character.toString(arg0.getKeyChar());
            actions.actionSurVoitureLettre(c,"slow");      
             
           
        }
}
