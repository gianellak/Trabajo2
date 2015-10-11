

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ProcesoTexto {
	
	
	static HashMap<Character, BigDecimal> calculoEstadistica(HashMap<Character, Integer> mapaTexto, Double acum){
		
		
		//mostrarTotal(h);
		System.out.println("total: " + acum);
		return calculoFrecuencia(mapaTexto, acum);
		
		
	}
	
	static void mostrarTotal(HashMap<Character, Integer> h){
		
		Set<Entry<Character, Integer>> freq = h.entrySet();
	    
		Iterator<Entry<Character, Integer>> it = freq.iterator();
 
		
		while ( it.hasNext() ) {
			Entry<Character, Integer> item = it.next();
			//System.out.println ( item.getKey() + ": " + item.getValue() );
			
		}

	}
	
	
	static HashMap<Character, BigDecimal> calculoFrecuencia(HashMap<Character, Integer> mapaTexto, Double acum){
		
		Double total = new Double(acum);
		
		Set<Entry<Character, Integer>> freq = mapaTexto.entrySet();
	    
		Iterator<Entry<Character, Integer>> it = freq.iterator();
		
		HashMap<Character, BigDecimal> porc = new HashMap<Character, BigDecimal>();
		
		
		while ( it.hasNext() ) {
			
			Entry<Character, Integer> item = it.next();
			
			
			BigDecimal e = new BigDecimal((item.getValue()/(total))*100).setScale(3, RoundingMode.HALF_UP);;
			
			porc.put ( item.getKey(), e);
			
			
		}
		//mostrarTotalLetras(porc);
		
		return porc;

	}

	@SuppressWarnings("unused")
	private static void mostrarTotalLetras(HashMap<Character, BigDecimal> fre) {
		Set<Entry<Character, BigDecimal>> freq = fre.entrySet();
	    
		Iterator<Entry<Character, BigDecimal>> it = freq.iterator();
 
		
		while ( it.hasNext() ) {
			Entry<Character, BigDecimal> item = it.next();
			System.out.println ( item.getKey() + ": " + item.getValue() );
			
		}

		
	}

	

}
