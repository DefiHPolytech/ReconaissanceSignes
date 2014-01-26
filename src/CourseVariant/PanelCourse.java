package CourseVariant;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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
 * On redéfinit l'instanciation de l'array avec des véhicules de police qui
 * n'apparaissent pas dans l'autre variante
 */
@Override
public void instanciateArray() {
    Vehicule v = new VoiturePolice("");
    v.setDy(1);
    trackList.add(v);
    add(v);
    int proba = 50;
    for (int i = 1; i < GRIDNB; i++) {
        if (Aleatoire.randomBoolean(proba)) {
            Vehicule w = Aleatoire.createRandomVehicle(NBCARS);
            w.setDy(1);
            trackList.add(w);
            add(w);
            proba -= 10;
        } else {
            JPanel p = new JPanel();
            trackList.add(p);
            add(p);
            proba += 10;
        }
    }
}

/**
 * On redefinit l'action a faire lorsque des véhicules sortent de l'écran/a un
 * tick d'horloge
 */
@Override
public void actionPerformed(ActionEvent e) {

    for (int i = 0; i < trackList.size(); i++) {
        if (trackList.get(i) instanceof Vehicule) {
            Vehicule v = (Vehicule) trackList.get(i);
            if (v.isSlowed() && v.countDownZero()) {
                v.unSlow();
            }

            if (v.touchLine()) {

                if (v.equals(trackList.get(0))) {
                    System.out.println("vous avez gagné");
                    Cadre.s.setText("vous avez gagné");
                    ;
                } else {
                    System.out.println("vous avez perdu");
                    Cadre.s.setText("vous avez perdu travaillez la lettre "
                            + v.getLettre());
                    ;
                }
                generalStop();
                timer.stop();
                Continue c = new Continue(this);

            }
        }
    }
}



@Override
public void receivedTraduction(String traduction) {
    pam.actionSurVoitureLettre(traduction, "slow");
}

}
