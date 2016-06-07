package proxy;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {

	//metodo que va a leer el json y convertilo en objeto segun el objeto que venga como parametro
	public Object convertirJsonToObject (String json,Object objectoParametro){
		
		//instancio el parser
		JSONParser parser = new JSONParser();
		//obtengo la clase que tengo que usar segun la que viene por parametro
		Class<? extends Object> clase = objectoParametro.getClass();
		
		//declaro el objeto que voy a devolver
		Object objetoParametroReturn;
		
		try {
			
			//le instancio un objeto a la clase
			objetoParametroReturn = clase.newInstance();
		
			Object obj;
			//si no viene nada como parametro json, uso este archivito por defecto
			if (json.isEmpty()){
				 obj = parser.parse(new FileReader(
	                    "/C:/Users/mcasal/Desktop/test.html"));
			//si viene algo, lo utilizo como json
			}else {
				obj = parser.parse(json); 
			}
			 JSONObject jsonObject = (JSONObject) obj;
            
			Iterator it = ((HashMap) obj).entrySet().iterator();
			//recorro el json con un iterator
            while (it.hasNext()) {
            	//si hay un siguiente cargo en el objeto par
            	Map.Entry pair = (Map.Entry)it.next();
                
                //si lo que hay en el objeto es string, lo uso como variable del objeto automatico
                if (pair.getValue() instanceof String) {
                	//le agrego al objeto el valor de la variable 
                	clase.getDeclaredField(pair.getKey().toString()).set(objetoParametroReturn , pair.getValue().toString());
                }
                //si lo que hay en el objeto no es un string, va a ser una lista, ver que hacer
                else{
                }
                //impresion x defecto del objeto json con clave valor
                //System.out.println(pair.getKey().toString() + " = " + pair.getValue().toString());
                // avoids a ConcurrentModificationException (ni idea que es esto, pero estaba asi en el ejemplo, lo dejo
                it.remove(); 
            }
 
            /*
            //asi funcionaba el ejemplo
            //String title = (String) jsonObject.get("title");
            //String body = (String) jsonObject.get("body");
            //JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            //System.out.println("Name: " + title);
            //System.out.println("Author: " + body);
            
            System.out.println("\nCompany List:");
            Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            */
            
            //devuelvo el objeto automatico
            return objetoParametroReturn;
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
		
	}
}
