package businessLogic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usuarios")
public class Usuario {
	public final static String ID_FIELD_NAME = "id";
	public final static String NOMBRE_FIELD_NAME = "nombre";
		
	@DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
	public int id;

	@DatabaseField(columnName = NOMBRE_FIELD_NAME)
	public String nombre;

	Usuario() {
		//para ormlite
	}
	
	public Usuario(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	
	@Publicable
	public String getNombre() {
		return nombre;
	}

	@Publicable
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
