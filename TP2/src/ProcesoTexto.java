

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ProcesoTexto {
	
	
	static HashMap<Character, BigDecimal> calculoEstadistica(HashMap<Character, Integer> mapaTexto, Double acum){
		
		

		System.out.println("total: " + acum);
		return calculoFrecuencia(mapaTexto, acum);
		
		
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
		
		
		return porc;

		
	}

	

}
