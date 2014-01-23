package vehicules;

import java.awt.*;   
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import affichage.Cadre;

public abstract class Vehicule extends JPanel implements ActionListener
{
	
		private static final long serialVersionUID = 1L;
		private Timer timer ;
	    private int x=25,y=Cadre.SIZEH,vitesse;
	    private String lettre;
	    private BufferedImage image;
	    private int dy=5;
	    
	    public Vehicule(String lettre)
	    {
	    	
	        this.lettre = lettre;
	    }
	    
	    public String getLettre(){
	        return lettre;
	    }

	    
	    public void actionPerformed(ActionEvent e)
	        {
	            y -= dy;
	            repaint();
	        }
	    

	    @Override
	    protected void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        paintCar(g);
	    }	    
	    
	    protected void paintCar (Graphics g){
	        g.drawImage(getImage(),x,y,null);
	        g.drawString(lettre,x+1/3,y+1/3);
	    }

		protected BufferedImage getImage() {
			return image;
		}

		protected void setImage(String image) {
			try{
				this.image = ImageIO.read(new File(image));
			}
			catch(Exception e)
			{
				System.out.println("Fichier"+image+" manquant ! ");
			}
		}

		protected int getVitesse() {
			return vitesse;
		}
		
		protected void setVitesse (int vitesse){
		    this.vitesse = vitesse;   
            timer = new Timer(vitesse, this);
		}
		
		public void destroyTimer() {
		    timer.stop();
		    timer=null;
		}
		
		public void go() {
			timer.start();	
		}
		
		public void stop(){
		    timer.stop();
		}
		

		public void slow(){
		    timer.setDelay(timer.getDelay()+50);
		}
		public void unSlow(){
		    timer.setDelay(timer.getDelay()-50);
		}
		
		public Timer getTimer(){
		    return timer;
		}
		
		public void explose()
		{
			setImage("voitures/explosion.png");
			repaint();
		}
		
		public boolean outOfWindow (){
		    return y<(-image.getHeight());
		}
		
		public void restart (){
		    timer.start();
		}
}
