package affichage;



import javax.swing.JFrame;

import CourseVariant.PanelCourse;
import ExplosionVariant.PanelExplosion;

public class Cadre extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final int SIZEH =600;
	public static final int SIZEL =800;
	public Cadre()
	{
	    setSize(SIZEL,SIZEH);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Traffic");
		
		//PanelExplosion f = new PanelExplosion();
		PanelCourse c = new PanelCourse();
		add(c);
	}
}
