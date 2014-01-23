package algorithmes;

import javazoom.jl.player.advanced.*;

import java.io.*;


/**
 * Cette classe permet de créer des sons
 * @author benjamin
 *
 */
public class Sons {
		/**
		 * Créer un son en mémoire(constructeur)
		 * @param path
		 * @throws Exception
		 */
        public Sons(String path) throws Exception {
                @SuppressWarnings("resource")
				InputStream in = (InputStream)new BufferedInputStream(new FileInputStream(new File(path)));
                player = new AdvancedPlayer(in);
        }

       
        /**
         * Démarrer la lecture
         * @throws Exception
         */
        public void play() throws Exception {
                if (player != null) {
                        isPlaying = true;
                        player.play();
                }
        }
       
        /**
         * Démarrer la lecture pdt un temps fixe
         * @param begin
         * @param end
         * @throws Exception
         */
        public void play(int begin,int end) throws Exception {
                if (player != null) {
                        isPlaying = true;
                        player.play(begin,end);
                }
        }
       
        /**
         * Stopper la lecture
         * @throws Exception
         */
        public void stop() throws Exception {
                if (player != null) {
                        isPlaying = false;
                        player.stop();                        
                }
        }
       
        /**
         * Savoir si la musique est jouée
         * @return
         */
        public boolean isPlaying() {
                return isPlaying;
        }

        private boolean isPlaying = false;
        private AdvancedPlayer player = null;
} 
