package CourseVariant;

import java.awt.event.KeyEvent;

import affichage.PanelActionManager;
/**
 * C'est l'�couteur pour la course
 * @author Benjamin
 *
 */
public class CourseKeyListener extends affichage.MyKeyListener{

    public CourseKeyListener(PanelActionManager action) {
        super(action);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * On change l'action a faire lors de l'appui d'une touche (ovveride)
     */
    @Override
    public void keyTyped(KeyEvent arg0) {
        System.out.println(arg0.getKeyChar());
            String c = Character.toString(arg0.getKeyChar());
            actions.actionSurVoitureLettre(c,"slow");      
             
           
        }
}
