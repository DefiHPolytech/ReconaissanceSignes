package affichage;
import java.awt.GridLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SignHelper extends JFrame {
    
    public static final String PATHLSF   = "data\\img\\lsf\\";
    public static final String ALPHABET  = "abcdefghijklmnopqrstuvwxyz";
    public static final String IMGFORMAT = ".jpg";
    
    public SignHelper() {
        super("Langue des signes");
        setLayout(new GridLayout(4, 6));
        
        for (int i = 0; i < ALPHABET.length(); ++i) {
            add(new JLabel(new ImageIcon(PATHLSF + ALPHABET.charAt(i) + IMGFORMAT)));
        }
        pack();
        setResizable(false);
    }
    
    public static void main (String[] args) {
        
        SignHelper helper = new SignHelper();
        
        helper.setVisible(true);
        
    }
}
