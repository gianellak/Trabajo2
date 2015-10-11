import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class Programa {


	private static HashMap<Character, Integer> mapaTexto = new HashMap<Character, Integer>();
	private static HashMap<String, HashMap<Character, BigDecimal>> mapaLenguaje;
	private static HashMap<Character,BigDecimal> mapaFreq;
	private static HashMap<String, Integer> mapaResultado;

	public static void main(String[] args) throws IOException {

		
		//FileReader file = new FileReader("C:\\Users\\Yo\\Desktop\\donQuijote.txt");
	
		//FileReader file = new FileReader("C:\\Users\\Yo\\Desktop\\Shakespeare.txt");
		//FileReader file = new FileReader("C:\\Users\\Yo\\Desktop\\bovary.txt");
		FileReader file = new FileReader("C:\\Users\\Yo\\Desktop\\Movimientos.txt");
		FileReader tabla = new FileReader ("C:\\Users\\Yo\\Desktop\\letras.txt");

		
        BufferedReader br = new BufferedReader(file);
        BufferedReader tb =new BufferedReader(tabla);
        
        mapaLenguaje = LeerTabla.leer(tb);
        
        LeerTabla.mostrarIdioma(mapaLenguaje);
        
        tb.close();
        
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
        DB db = mongoClient.getDB("test");
        
        DBCollection coll = db.getCollection("texto");
        
        coll.drop();
     
        String line = br.readLine();
        
        BasicDBObject document ;
		
		while (line != null ) {    
       

            	document= new BasicDBObject();

            	if (line.trim().isEmpty() != true){
            		
            		document.put("linea", line);
            	//	System.out.println(document);
            		coll.insert(document);
            		
            	
            	}
            	
            

            line = br.readLine();   
		}
		
		

		String map = "function(){" +
				"var cont = {};" +
				"for (var i = this.linea.length -1 ; i >= 0; i--) {" +
				"var letra = this.linea[i].toLowerCase();" +
				"if (isNumber(cont[letra])){" +
				"cont[letra]++; }" +
				" else {cont[letra]=1; } }" +
				"for(var k in cont){if (k !== ' '){emit(k,cont[k]); }}};";
		
		String reduce = "function(key, values){return Array.sum(values);}; ";
		
		MapReduceCommand cmd = new MapReduceCommand(coll, map, reduce,
                null, MapReduceCommand.OutputType.INLINE, null);


		MapReduceOutput out = coll.mapReduce(cmd);
		
		
				
		Double acum = (double) 0;
		
		for (DBObject o : out.results()) {
			
			char letra = o.get("_id").toString().charAt(0); 
			
			Double d = (Double) o.get("value");
			
			acum = acum + d;
			
			Integer valor = d.intValue();
			
			mapaTexto.put( letra , valor );
			
			
		}
		
		mapaFreq = ProcesoTexto.calculoEstadistica(mapaTexto, acum);
		
		
		mapaResultado = Comparador.comparoIdiomas(mapaLenguaje, mapaFreq);
		
		Comparador.mostrarResultados(mapaResultado);
	}

}

