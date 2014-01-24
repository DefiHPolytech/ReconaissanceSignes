package affichage;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.googlecode.javacv.cpp.videoInputLib.videoInput;

public class WebcamSelection extends JPanel {
    
    public WebcamSelection() {
        
        JButton boutonValider;
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        ButtonGroup grp = new ButtonGroup();
                
        for (int i = 0; i < videoInput.listDevices(); ++i) {
            JRadioButton button = new JRadioButton(videoInput.getDeviceName(i));
            grp.add(button);
            add(button);
            System.out.println(videoInput.getDeviceName(i));
        }
        boutonValider = new JButton("Valider");
        
        add(boutonValider);
    }
    
    public static void main (String args[]) {
        WebcamSelection selection = new WebcamSelection();
        
        JFrame fenetre = new JFrame("Selection de webcam");
        fenetre.add(selection);
        fenetre.pack();
        
        fenetre.setVisible(true);
    }
}
