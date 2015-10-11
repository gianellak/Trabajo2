

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class LeerTabla {
	
	
	

	private static String abc;
	
	static HashMap<String, HashMap<Character, BigDecimal>> procesoLinea(String line) throws IOException {
		
		
		
		HashMap<String, HashMap<Character, BigDecimal> > mapaIdioma = new HashMap<String, HashMap<Character, BigDecimal>>();
		
		
			
					
				String[] parts = line.split("-");
					
				mapaIdioma.put(parts[0] , cargarAbecedario(parts));
					
				//mostrarAbecedario(mapaLetraI);
				
				
				
				
				
				return mapaIdioma;
				
	    		
    }

			
	

	static void mostrarIdioma(
			HashMap<String, HashMap<Character, BigDecimal>> mapaIdioma) {
		Set<Entry<String, HashMap<Character, BigDecimal>>> freq = mapaIdioma.entrySet();
	    
		Iterator<Entry<String, HashMap<Character, BigDecimal>>> it = freq.iterator();
 
		
		while ( it.hasNext() ) {
			Entry<String, HashMap<Character, BigDecimal>> item = it.next();
			System.out.println ( item.getKey() + ": " + item.getValue() );
			
		}
		
	}

	

	@SuppressWarnings("unused")
	private static void mostrarAbecedario(HashMap<Character, BigDecimal> mapaLetraI) {
		Set<Entry<Character, BigDecimal>> freq = mapaLetraI.entrySet();
	    
		Iterator<Entry<Character, BigDecimal>> it = freq.iterator();
 
		
		while ( it.hasNext() ) {
			Entry<Character, BigDecimal> item = it.next();
			 System.out.println ( item.getKey() + ": " + item.getValue() );
			
		}
		
		
	}

	private static HashMap<Character, BigDecimal> cargarAbecedario(
			
			String[] parts) {
		
		HashMap<Character, BigDecimal> mapaLetraI = new HashMap<Character, BigDecimal>();
		
					
				setAbc("abcdefghijklmnopqrstuvwxyzáéíóú");
    	
				
			
				for(int i=0; i<abc.length(); i++){
		    		
					BigDecimal e = new BigDecimal(parts[i+1]);

						mapaLetraI.put( abc.charAt(i), e );
						
		    			
		    				
		    			}
		    	
				
				return mapaLetraI;
		
	}

	


	public static String getAbc() {
		return abc;
	}

	public static void setAbc(String abc) {
		LeerTabla.abc = abc;
	}




	public static HashMap<String, HashMap<Character, BigDecimal>> leer(
			BufferedReader tb) throws IOException {
	 
		HashMap<String, HashMap<Character, BigDecimal> > mapaTabla = new HashMap<String, HashMap<Character, BigDecimal>>();
		
		String line = tb.readLine();
		
		
		
		while ((line) != null) {
			int i = 0;
			
		    for(i=0; i<line.length(); i++){
		    

			line = line.toLowerCase();
	
			String[] parts = line.split("-");
			
			mapaTabla.put(parts[0] , cargarAbecedario(parts));
				
			
		    } 
		    line = tb.readLine();
	   
		}
		
		return mapaTabla;


	}

	

}
