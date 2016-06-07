package proxy;

import businessLogic.Pelicula;

public class TestProxy {

	public static void main(String[] args)  {
		VideoclubProxy vcp = new VideoclubProxy();
		//vcp.init();
		//vcp.listarPeliculas();
		Pelicula p = vcp.obtenerPeliculaPorTitulo("hola");
		System.out.println(p.getTitulo());
	  }
  

  
}