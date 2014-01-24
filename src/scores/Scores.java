package scores;

import javax.swing.JTextArea;

/**
 * Cette classe met en place les scores
 * 
 * @author Julien
 * 
 */
public class Scores extends JTextArea {
private int score = 0;
private String texte;
private static final long serialVersionUID = 1L;

public Scores() {
    super();
    initialize();
}

public int getScore(){
    return score;
}

public String getTexte(){
    return texte;
}

/**
 * Initialiser le score
 */
private void initialize() {
    texte = Integer.toString(score);
    setText("Score : "+texte);
}

/**
 * Pour augmenter le score 
 * @param i
 */
public void augmenteScore(int i) {
    score += i;
    texte = Integer.toString(score);

    setText("Score : "+texte);
}

/**
 * Pour mettre un texte a la place du score
 * @param i
 */
public void setScore(int i) {
    score = i;
    texte = Integer.toString(score);

    setText(texte);
}
}
