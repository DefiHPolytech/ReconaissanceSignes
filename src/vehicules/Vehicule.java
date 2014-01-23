package vehicules;

import java.awt.*;   
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import affichage.Cadre;

/**
 * Cette classe représente un véhicule qui se déplace sur une colonne
 * @author Benjamin-Julien
 *
 */
public abstract class Vehicule extends JPanel implements ActionListener
{
	
		private static final long serialVersionUID = 1L;
		private Timer timer ;
	    private int x=25,y=Cadre.SIZEH,vitesse;
	    private String lettre;
	    private BufferedImage image;
	    private int dy=5;
	    private int countdown=-1;

	    
	    public Vehicule(String lettre)
	    {
	    	
	        this.lettre = lettre;
	    }
	    
	    /**
	     * Le compte a rebours est terminé
	     * @return
	     */
	    public boolean countDownZero (){
	        return countdown<=0;
	    }
	    
	    /**
	     * Savoir quelle lettre est portée par le véhicule
	     * @return
	     */
	    public String getLettre(){
	        return lettre;
	    }

	    
	    public void actionPerformed(ActionEvent e)
	        {
	            y -= dy;
	            repaint();
	            if (countdown>0){
	            	countdown-=5;
	            
	                System.out.println(countdown);}
	        }
	    

	    @Override
	    protected void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        paintCar(g);
	    }	    
	    
	    /**
	     * Dessine la voiture
	     * @param g
	     */
	    protected void paintCar (Graphics g){
	        g.drawImage(getImage(),x,y,null);
	        g.drawString(lettre,x+1/3,y+1/3);
	    }
	    
	    /**
	     * Savoir l'image
	     * @return
	     */
		protected BufferedImage getImage() {
			return image;
		}
	
		/**
		 * Changer l'image pour une image d'explosion par exemple
		 * @param image
		 */
		protected void setImage(String image) {
			try{
				this.image = ImageIO.read(new File(image));
			}
			catch(Exception e)
			{
				System.out.println("Fichier"+image+" manquant ! ");
			}
		}
		
		/**
		 * Avoir la vitesse actuelle
		 * @return
		 */
		protected int getVitesse() {
			return vitesse;
		}
		
		/**
		 * Changer la vitesse avec le timer 
		 * @param vitesse
		 */
		protected void setVitesse (int vitesse){
		    this.vitesse = vitesse;   
            timer = new Timer(vitesse, this);
		}
		
		/**
		 * Supprimer le timer
		 */
		public void destroyTimer() {
		    timer.stop();
		    timer=null;
		}
		
		/**
		 * Démarrer la voiture
		 */
		public void go() {
			timer.start();	
		}
		
		/**
		 * La voiture est elle en mvt ?
		 * @return
		 */
		public boolean isRunning(){
			return dy!=0;
		}
		
		/**
		 * Stopper la voiture
		 */
		public void stop(){
		    dy=0;
		}
		
		/**
		 * Mettre en place un compte a rebours (par ex utile lorsque on fait exploser une voiture, pour afficher l'image d'expl.)
		 * @param delay
		 */
		public void setCountDown(int delay){
		    countdown=delay;
		}
		
		/**
		 * Ralentit le véhicule
		 */
		public void slow(){
		    timer.setDelay(timer.getDelay()+50);
		}
		
		/**
		 * Accélère le véhicule
		 */
		public void unSlow(){
		    timer.setDelay(timer.getDelay()-50);
		}
		
		/**
		 * Avoir le timer
		 * @return
		 */
		public Timer getTimer(){
		    return timer;
		}
		
		/**
		 * Exploser la voiture
		 */
		public void explose()
		{
			setImage("voitures/explosion.png");
			repaint();
			Cadre.s.augmenteScore(10);
		}
		
		
		/**
		 * Si la voiture sort de la fenêtre
		 * @return
		 */
		public boolean outOfWindow (){
		    return y<(-image.getHeight());
		}
		
		public void restart (){
		    timer.start();
		}
}
