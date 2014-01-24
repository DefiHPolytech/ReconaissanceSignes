package affichage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import scores.Scores;
import traduction.Traducteur;
import CourseVariant.PanelCourse;
import ExplosionVariant.PanelExplosion;

/**
 * @author Julien Cette classe fait apparaître un panel contenant les
 *         différentes variantes
 */
public class Cadre extends JFrame implements ActionListener {

private static final long serialVersionUID = 1L;
public static final int SIZEH = 600;
public static final int SIZEL = 800;
public static Scores s;
private PanelGenerique pG;
private JButton explosion, course,options;
private Traducteur traducteur;
public Cadre(Fenetre menu, Traducteur t) {
    traducteur = t;
    setSize(SIZEL, SIZEH);
    setLocationRelativeTo(null);

    // Savoir que faire lorsque on appuie sur la croix
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Traffic");

    if (menu == menu) {

        setLayout(new FlowLayout());
        /** La taille de la fenêtre */

        explosion = new JButton("Mode Explosion");
        course = new JButton("Mode Course");
        options = new JButton("Options");
        add(explosion);
        add(course);
        add(options);
        explosion.addActionListener(this);
        course.addActionListener(this);
        options.addActionListener(this);
    }
    if (menu == Fenetre.modeExplosion) {
        setLayout(new BorderLayout());
        
        PanelExplosion c = new PanelExplosion();

        add(c, BorderLayout.CENTER);

        s= new Scores();
        add(s, BorderLayout.SOUTH);
    }
    if (menu == Fenetre.modeCourse) {
        setLayout(new BorderLayout());
        
        PanelCourse c = new PanelCourse();

        add(c, BorderLayout.CENTER);

        s= new Scores();
        add(s, BorderLayout.SOUTH);
    }
    setVisible(true);
}

@Override
public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource() == explosion) {
        dispose();
        Cadre c = new Cadre (Fenetre.modeExplosion,traducteur);
        traducteur.addListener(c.pG);
    }

    if (arg0.getSource() == course) {
        dispose();
        Cadre c = new Cadre (Fenetre.modeCourse,traducteur);
        traducteur.addListener(c.pG);
    }
    if (arg0.getSource() == course) {
     
    }

}
}
