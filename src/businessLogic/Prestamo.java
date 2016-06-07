package businessLogic;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.j256.ormlite.field.DatabaseField;


public class Prestamo {
	public final static String USUARIO_ID_FIELD_NAME = "pelicula_id";
	public final static String PELICULA_ID_FIELD_NAME = "usuario_id";
	
	@DatabaseField(generatedId = true)
	public int id;
	
	@DatabaseField(foreign = true, columnName = USUARIO_ID_FIELD_NAME)
	public Usuario usuario;
	
	@DatabaseField(foreign = true, columnName = PELICULA_ID_FIELD_NAME)
	public Pelicula pelicula;
	
	@DatabaseField
	public Date fechaAlquiler;
	
	@DatabaseField
	public Date fechaVencimiento;
	
	Prestamo() {
		//para ormlite
	}
	
	public Prestamo(Usuario usuario, Pelicula pelicula) {
		super();
		this.usuario = usuario;
		this.pelicula = pelicula;
		
		Date fechaAlquiler = new Date();
		Date fechaVencimiento = new Date(fechaAlquiler.getTime() + TimeUnit.DAYS.toMillis(1));
		
		this.fechaAlquiler = fechaAlquiler;
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getId() {
		return id;
	}
	
	@Publicable
	public Usuario getUsuario() {
		return usuario;
	}
	
	@Publicable
	public Pelicula getPelicula() {
		return pelicula;
	}
	
	@Publicable
	public Date getFechaAlquiler() {
		return fechaAlquiler;
	}
	
	@Publicable
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
}
