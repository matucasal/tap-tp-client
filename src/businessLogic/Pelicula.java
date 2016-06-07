package businessLogic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "peliculas")
public class Pelicula {
	public final static String ID_FIELD_NAME = "id";
	public final static String TITULO_FIELD_NAME = "titulo";

	@DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
	public int id;
	
	@DatabaseField(columnName = TITULO_FIELD_NAME)
	public String titulo;
	
	public Pelicula () {
		//para ormlite
	}
	
	public Pelicula(String titulo) {
		super();
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}
	
	@Publicable
	public String getTitulo() {
		return titulo;
	}
	
	@Publicable
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String toString() {
		System.out.println(this.titulo);
		return this.titulo;
	}
}
