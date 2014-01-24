package CourseVariant;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import traduction.TraductionListener;
import vehicules.*;
import affichage.Cadre;
import affichage.PanelActionManager;
import affichage.PanelGenerique;
import algorithmes.Aleatoire;

/**
 * Cette classe simule une course entre des policiers et des malfrats
 * 
 * @author Benjamin
 * 
 */
public class PanelCourse extends PanelGenerique implements ActionListener,
        TraductionListener {

private static final long serialVersionUID = 1L;

public PanelCourse() {
    setLayout(new GridLayout(1, 10));
    instanciateArray();
    generalStart();
    pam = new PanelActionManager(this);
    CourseKeyListener listener = new CourseKeyListener(pam);
    addKeyListener(listener);
    setFocusable(true);
    timer = new Timer(INTERVALLEVERIFS, this);
    timer.start();
}

/**
 * On red�finit l'instanciation de l'array avec des v�hicules de police qui
 * n'apparaissent pas dans l'autre variante
 */
@Override
public void instanciateArray() {
    Vehicule v = new VoiturePolice("");
    carList.add(v);
    add(v);
    int proba = 30;
    for (int i = 1; i < GRIDNB; i++) {
        if (Aleatoire.randomBoolean(proba)) {
            Vehicule w = Aleatoire.createRandomVehicle(NBCARS);
            carList.add(w);
            add(w);
            proba -= 10;
        } else {
            JPanel p = new JPanel();
            carList.add(p);
            add(p);
            proba += 5;
        }
    }
}

/**
 * On redefinit l'action a faire lorsque des v�hicules sortent de l'�cran/a un
 * tick d'horloge
 */
@Override
public void actionPerformed(ActionEvent e) {

    for (int i = 0; i < carList.size(); i++) {
        if (carList.get(i) instanceof Vehicule) {
            Vehicule v = (Vehicule) carList.get(i);
            if (v.isSlowed() && v.countDownZero()) {
                v.unSlow();
            }

            if (v.touchLine()) {

                if (v.equals(carList.get(0))) {
                    System.out.println("vous avez gagn�");
                    Cadre.s.setText("vous avez gagn�");
                    ;
                } else {
                    System.out.println("vous avez perdu");
                    Cadre.s.setText("vous avez perdu");
                    ;
                }
                generalStop();
                timer.stop();

            }
        }
    }
}

@Override
public void receivedTraduction(String traduction) {

    pam.actionSurVoitureLettre(traduction, "slow");

}

}
