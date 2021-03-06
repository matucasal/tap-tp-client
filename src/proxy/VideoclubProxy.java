package proxy;

import businessLogic.Pelicula;
import lib.ReadJson;

import java.io.IOException;

import org.json.simple.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//clase autogenerada
public class VideoclubProxy {
	
	//llamando al run hago las llamadas al server
	OkHttpClient client = new OkHttpClient();
	
	String run(String url) throws IOException {
		Request request = new Request.Builder()
		.url(url)
		.build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	
	
	public void init(){
		
		//por si hay que usar proxy
		System.setProperty("proxySet", "true");
		System.setProperty("http.proxyHost", "test");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyUser", "mcasal");
		System.setProperty("http.proxyPassword", "test");
	}
	
	//metodo autogenerado
	public void listarPeliculas() {
		String response;
		String automaticUrl = "http://jsonplaceholder.typicode.com/posts/1";
		try {
			response = this.run(automaticUrl);
			System.out.println(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//metodo autogenerado
	public Pelicula obtenerPeliculaPorTitulo(String titulo) {
		//como se que devuelvo pelicula, este objeto se creara automaticamente
		Pelicula p = null;
		String response = "";
		//esta url se va a generar automaticamente por el nombre del metodo
		String automaticUrl = "http://jsonplaceholder.typicode.com/posts/1";
		try {
			//obtengo el json desde la url
			//response = this.run(automaticUrl);
			//clase propia lector de json
			ReadJson rj = new ReadJson();
			
			//primero valido el json y obtengo el objeto
			JSONObject jsonObject = rj.validarJson(response);
			
			//si jsonObject viene con algo quiere decir que se pudo usar el json, puede convertirlo al objeto generico
			if (jsonObject != null){
				//busco el objeto a traves del json con convertirJsonToObject
				//como parametro mando el response y el objeto pedido
				//como casteo mando el objeto pedido
				p = (Pelicula) rj.convertirJsonToGenericObject(jsonObject, (Object) new Pelicula());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//devuelvo el objecto vacio
		return p;
	}
	
}
