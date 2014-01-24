package scores;

import javax.swing.JTextArea;

/**
 * Cette classe met en place les scores et des textes informatifs
 * 
 * @author Julien
 * 
 */
public class Scores extends JTextArea {
private int score = 0;
private int tempsrestant;
private static final long serialVersionUID = 1L;

public Scores() {
    super();
}

public int getScore(){
    return score;
}


/**
 * Initialiser le temps
 */
public void initializeTemps(int temps) {
    tempsrestant = temps;
}

/**
 * Pour augmenter le score 
 * @param i
 */
public void augmenteScore(int i) {
    score += i;
    setText("Score : "+Integer.toString(score)+" Temps restant : "+tempsrestant);
}

public void diminueTemps() {
    tempsrestant--;
    String min  = Integer.toString(tempsrestant/60);
    String secs  = Integer.toString(tempsrestant%60);
    setText("Score : "+Integer.toString(score)+" Temps restant : "+min+":"+secs);
}

public int getTempsRestant()
{
	return tempsrestant;
}

/**
 * Pour mettre un texte a la place du score
 * @param i
 */
public void setScore(int i) {
    score = i;
    setText(Integer.toString(score));
}
}
