package scores;

import javax.swing.JTextArea;

/**
 * Cette classe met en place les scores
 * @author Julien
 * 
 */
public class Scores extends JTextArea{
	int score = 0;
	String texte;
	private static final long serialVersionUID = 1L;
	 public Scores() {
			super();
			initialize();
		}

		private void initialize() {
			texte = Integer.toString(score);
	        setText(texte); 
	    }
		
		public void augmenteScore(int i)
		{
			score += i;	
			texte = Integer.toString(score);
			
			setText(texte); 
		}
}
