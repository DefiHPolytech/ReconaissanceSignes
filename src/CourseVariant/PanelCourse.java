package CourseVariant;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import vehicules.*;
import affichage.MyKeyListener;
import affichage.PanelActionManager;
import affichage.PanelGenerique;
import algorithmes.Aleatoire;

public class PanelCourse extends PanelGenerique implements ActionListener {

private static final long serialVersionUID = 1L;

public PanelCourse() {
    setLayout(new GridLayout(1, 10));
    instanciateArray();
    generalStart();
    pam = new PanelActionManager(this);
    CourseKeyListener listener = new CourseKeyListener(pam);
    addKeyListener(listener);
    setFocusable(true);
    timer = new Timer(30, this);
    timer.start();
}

@Override
public void instanciateArray() {
    Vehicule v = new VoiturePolice("");
    carList.add(v);
    add(v);
    for (int i = 1; i < GRIDNB; i++) {
        if (Aleatoire.randomBoolean()) {
            Vehicule w = Aleatoire.createRandomVehicle(NBCARS);
            carList.add(w);
            add(w);
        } else {
            JPanel p = new JPanel();
            carList.add(p);
            add(p);
        }
    }
}

@Override
public void actionPerformed(ActionEvent e) {
    
    for (int i = 0; i < carList.size(); i++) {
        if (carList.get(i) instanceof Vehicule) {
            Vehicule v = (Vehicule) carList.get(i);

            
            if (v.outOfWindow()) {
                
                if(v.equals(carList.get(0))){
                    System.out.println("vous avez gagné");
                    
                }
                else{
                    System.out.println("vous avez perdu");
                    
                }
                generalStop();
                timer.stop();
            }
        }
    }
}

}
