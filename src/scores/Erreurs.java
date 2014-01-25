package scores;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
/**
 * Savoir les erreurs qu'on a faites lors du jeu
 * @author Julien
 *
 */
public class Erreurs {
	private HashMap<String,Integer> h = new HashMap<String,Integer>();
	private int max;
	public void ajouteErreur(String s)
	{
		
		if(h.get(s)!= null)
		{		
			h.put(s, (h.get(s))+1);
		}		
		else
		{		
			h.put(s, 1);
		}	
			
	}
	
	public String getMaxErr()
	{
		
		max = Collections.max(h.values());
		for(int i = 'A';i<'Z';i++)
		{
			char c = (char)i;
			
			if(h.get(Character.toString(c))!= null)
			{
			if(h.get(Character.toString(c))==max)
			{
				return Character.toString(c);
			}
			}
			
		}
		return null;
		
		
	}
	
	public String getNbErr()
	{
		
		return Integer.toString(max);
		
		
	}
	
}
