

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Comparador{
	

	private static BigDecimal error;
	


	static HashMap<String, Integer> comparoIdiomas(HashMap<String, HashMap<Character, BigDecimal>> mapaIdioma,
						final HashMap<Character, BigDecimal> mapaFreq) {
					
					final HashMap <String , Integer> mapaResultado = new HashMap<String, Integer>();
					
					Set<Entry<String, HashMap<Character, BigDecimal>>> freq = mapaIdioma.entrySet();
					
					Iterator<Entry<String, HashMap<Character, BigDecimal>>> it = freq.iterator();
					
					ExecutorService exec = Executors.newFixedThreadPool(5);
					
					while ( it.hasNext() ) {
					
						final Entry<String, HashMap<Character, BigDecimal>> item = it.next();

						exec.execute(new Runnable() {
			    		
							public void run() {
								//System.out.println("Running" + Thread.currentThread() + "idioma: " + item.getKey());
								for (final Character key : item.getValue().keySet()) {
				    	
								//	System.out.println("Letra-idioma: " + key + "-" + item.getKey());
				    			if (mapaFreq.containsKey(key)) {
							    	error = new BigDecimal(0.3);
							    	BigDecimal resta = item.getValue().get(key).subtract(mapaFreq.get(key));
							    	resta = resta.abs();
							        
							    	if ( (error).compareTo(resta) > 0){
							    		//System.out.println("Error:  " + error + " Resta : " + resta);
							    		//System.out.println("Es menor en= " + key);
							        	if ( mapaResultado.containsKey ( item.getKey() ) ) {
							        		//System.out.println(item.getValue().get(key) + " - " + mapaFreq.get(key) + " = " + resta);
							        		mapaResultado.put ( item.getKey(), mapaResultado.get(item.getKey()) +1 );
						    			} else {
						    				mapaResultado.put ( item.getKey(), (int) 1 );
						    				//System.out.println(item.getValue().get(key) + " - " + mapaFreq.get(key) + " = " + resta);
						    			}
							        }
				    			}
				    		}
								
							}
				    	});
				    }
				    
				    exec.shutdown();
				    
				    try {
				       boolean b = exec.awaitTermination(50, TimeUnit.SECONDS);
				    
				     System.out.println("All: " + b);
				     
				     
				    } catch (InterruptedException e) {
				       e.printStackTrace();
				    }
					
					
				    return mapaResultado;
			}
			
					
				

	
	

	public static void mostrarResultados(HashMap<String, Integer> mapaResultado){
		
		Set<Entry<String, Integer>> freq = mapaResultado.entrySet();
		
		Iterator<Entry<String, Integer>> it = freq.iterator();
		
		String idiomaMax = null;
		int maximo = 0 ;
		
		while ( it.hasNext() ) {
			Entry<String, Integer> item = it.next();
			//System.out.println ( item.getKey() + ": " + item.getValue() );
			
			if(item.getValue()>maximo){
			maximo = item.getValue();
			idiomaMax = item.getKey();
			}
			
		}
		
		System.out.println("El idioma del texto es " + idiomaMax);
	}
	




	
}
