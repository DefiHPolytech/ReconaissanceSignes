package affichage;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import scores.Scores;
import ExplosionVariant.PanelExplosion;


/**
 * @author Julien
 * Cette classe fait appara�tre un panel contenant les diff�rentes variantes
 */
public class Cadre extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static final int SIZEH =600;
	public static final int SIZEL =800;
	public static Scores s;
	public Cadre()
	{
		setLayout(new BorderLayout());
		/** La taille de la fen�tre */
	    setSize(SIZEL,SIZEH);
	    setLocationRelativeTo(null);
	    
	    //Savoir que faire lorsque on appuie sur la croix
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Traffic");
		
		PanelExplosion f = new PanelExplosion();
		//PanelCourse c = new PanelCourse();
		
		add(f, BorderLayout.CENTER);
		
		s= new Scores();
		add(s, BorderLayout.SOUTH);
		
		
	}
}
