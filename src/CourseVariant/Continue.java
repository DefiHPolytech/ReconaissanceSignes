package CourseVariant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import traduction.Traducteur;
import Fenetre.PanneauVideo;
import affichage.Fenetre;
import affichage.Cadre;
import affichage.PanelGenerique;


/**
 * 
 * @author Benjamin
 *
 */
public class Continue extends JFrame implements ActionListener {

private JButton continu, quitGame, changeMod;
private Cadre c;
private PanelGenerique panel;
public Continue(PanelGenerique p) {
    this.setLayout(new FlowLayout());
    this.c= getCadre(p);
    this.panel=p;
    this.setTitle("Continue");
    continu = new JButton("continuer");
    quitGame = new JButton("quitter le Jeu");
    changeMod = new JButton("changer de mode de jeu");
    add(continu);
    add(quitGame);
    add(changeMod);
    continu.addActionListener(this);
    quitGame.addActionListener(this);
    changeMod.addActionListener(this);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setUndecorated(true);
    this.setBackground(Color.white);
    this.setSize(300,300);
    this.setVisible(true);

}

/**
 * 
 * @return Renvoi le cadre ou est stocké le panel mis en parametre
 * 
 */
public Cadre getCadre(JPanel j) {
    Component comp = j;
    while (!(comp instanceof JFrame)) {
        comp = comp.getParent();
    }
   return (Cadre) comp;
}

@Override
public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource() == continu) {
        Container pa = panel.getParent();
        dispose();
        pa.remove(panel);
        pa.add(new PanelCourse());
        pa.invalidate();
        pa.validate();
    }
    if (arg0.getSource() == quitGame) {
        dispose();
        c.dispose();
    }
    if (arg0.getSource() == changeMod) {
        dispose();
        c.dispose();
        c = new Cadre(Fenetre.menu,c.getTraducteur(),c.getPanneauVideo());
    }
}


}