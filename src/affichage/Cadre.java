package affichage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import scores.Erreurs;
import scores.Scores;
import traduction.Traducteur;
import CourseVariant.PanelCourse;
import ExplosionVariant.PanelExplosion;
import Fenetre.PanneauVideo;

/**
 * @author Julien Cette classe fait apparaître un panel contenant les
 *         différentes variantes
 */
public class Cadre extends JFrame implements ActionListener {

private static final long serialVersionUID = 1L;
public static final int SIZEH = 600;
public static final int SIZEL = 1024;
public static Scores s;
public static Erreurs e;
private JButton explosion, course,options;
private Traducteur traducteur;
private PanneauVideo pV;
public Cadre(Fenetre menu, Traducteur t,PanneauVideo pV) {
    traducteur = t;
    setSize(SIZEL, SIZEH);
    setLocationRelativeTo(null);
    this.pV=pV;
    // Savoir que faire lorsque on appuie sur la croix
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Traffic");

    if (menu == Fenetre.menu) {

        /** La taille de la fenêtre */
        
        setLayout(new FlowLayout());
        
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
        e = new Erreurs();
        add(s, BorderLayout.SOUTH);
        traducteur.addListener(c);
        add(pV,BorderLayout.WEST);
    }
    if (menu == Fenetre.modeCourse) {
        
        setLayout(new BorderLayout());
        
       pV.setSize(100, 100);
       add(pV,BorderLayout.WEST);
        
        PanelCourse c = new PanelCourse();

        c.setSize(1024, 748);
        add(c,BorderLayout.CENTER);
        

        s= new Scores();
        add(s,BorderLayout.SOUTH);
        traducteur.addListener(c);
        
    }
    setVisible(true);
}

@Override
public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource() == explosion) {
        dispose();
        Cadre c = new Cadre (Fenetre.modeExplosion,traducteur,pV);
    }

    if (arg0.getSource() == course) {
        dispose();
        Cadre c = new Cadre (Fenetre.modeCourse,traducteur,pV);
    }
    if (arg0.getSource() == options) {
     
    }

}
}
