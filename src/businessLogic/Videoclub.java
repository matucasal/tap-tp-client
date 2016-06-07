package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class Videoclub {
	static String databaseUrl = "jdbc:sqlite:video.db";
	static ConnectionSource connectionSource;
	static Dao<Usuario, String> usuariosDao;
	static Dao<Pelicula, String> peliculasDao;
	static Dao<Prestamo, String> prestamosDao;
	
	public static List<Usuario> usuarios;
	public static List<Pelicula> peliculas;
	public static List<Prestamo> prestamos;
	
	public static void main(String[] args) throws Exception {
        // create a connection source to our database
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        
        usuariosDao = DaoManager.createDao(connectionSource, Usuario.class);
        peliculasDao = DaoManager.createDao(connectionSource, Pelicula.class);
        prestamosDao = DaoManager.createDao(connectionSource, Prestamo.class);
        
		//TableUtils.createTable(connectionSource, Usuario.class);
		//TableUtils.createTable(connectionSource, Pelicula.class);
       // TableUtils.createTable(connectionSource, Prestamo.class);
		
        //popularDb();
        //listarPeliculas();
        //listarUsuarios();
        //crearPrestamos();
        listarPrestamos();

        connectionSource.close();
	}
	
	public static void crearPrestamos() {
		Usuario usuario = obtenerUsuarioPorNombre("JORGE");
		Pelicula pelicula = obtenerPeliculaPorTitulo("TITANIC");
		Prestamo prestamo = new Prestamo(usuario, pelicula);
		
		try {
			prestamosDao.create(prestamo);
			System.out.println("Prestamo creado.");
		} catch (SQLException e) {
			System.out.println("Error al crear prestamo.");
		}
	}
	
	public static void popularDb() {
		crearPelicula("TERMINATOR");
		crearPelicula("CAZAFANTASMAS");
		crearPelicula("TITANIC");
		crearPelicula("STAR WARS");
		crearPelicula("ROBOCOP");
		
		crearUsuario("JORGE");
		crearUsuario("LUIS");
		crearUsuario("MARIO");
		crearUsuario("MIRTA");
		crearUsuario("SUSANA");
	}
	
	@Publicable
	public static Pelicula obtenerPeliculaPorTitulo(String titulo) {
		QueryBuilder<Pelicula, String> queryBuilder = peliculasDao.queryBuilder();
		try {
			queryBuilder.where().eq(Pelicula.TITULO_FIELD_NAME, titulo);
			List<Pelicula> peliculas = queryBuilder.query();
			return peliculas.get(0);
		} catch (SQLException e) {
			System.out.println("Error al obtener pelicula.");
			return null;
		}
	}
	
	@Publicable
	public static Usuario obtenerUsuarioPorNombre(String nombre) {
		QueryBuilder<Usuario, String> queryBuilder = usuariosDao.queryBuilder();
		try {
			queryBuilder.where().eq(Usuario.NOMBRE_FIELD_NAME, nombre);
			List<Usuario> usuarios = queryBuilder.query();
			return usuarios.get(0);
		} catch (SQLException e) {
			System.out.println("Error al obtener usuario.");
			return null;
		}
	}
	
	@Publicable
	public static void listarPeliculas() {
		List<Pelicula> peliculas;
		
		try {
			peliculas = peliculasDao.queryForAll();
			
			System.out.println("PELICULAS:");
			System.out.println("----------");
			
	        for (Pelicula p : peliculas) {
	        	System.out.println(p.getTitulo());
	        }
		} catch (SQLException e) {
			System.out.println("Error al listar las peliculas.");
		}
	}
	
	@Publicable
	public static void listarUsuarios()  {
		List<Usuario> usuarios;
		
		try {
			usuarios = usuariosDao.queryForAll();
			
			System.out.println("USUARIOS:");
			System.out.println("---------");
			
	        for (Usuario u : usuarios) {
	        	System.out.println(u.getNombre());
	        }
		} catch (SQLException e) {
			System.out.println("Error al listar los usuarios.");
		}
	}
	
	@Publicable
	public static void listarPrestamos()  {
		List<Prestamo> prestamos;
		
		try {
			prestamos = prestamosDao.queryForAll();
			
			System.out.println("PRESTAMOS:");
			System.out.println("----------");
			
	        for (Prestamo p : prestamos) {
	        	usuariosDao.refresh(p.getUsuario());
	        	peliculasDao.refresh(p.getPelicula());
	        	System.out.println("El usuario " + p.getUsuario().getNombre() + " alquilo la pelicula " + p.getPelicula().getTitulo() + ".");
	        }
		} catch (SQLException e) {
			System.out.println("Error al listar los prestamos.");
		}
	}
	
	@Publicable
	public static void crearPelicula(String titulo) {
		Pelicula p = new Pelicula(titulo);
		
		try {
			peliculasDao.create(p);
			System.out.println("Pelicula " + p.getTitulo() + " creada.");
		} catch (SQLException e) {
			System.out.println("Error al crear pelicula.");
		}
	}
	
	@Publicable
	public static void crearUsuario(String nombre) {
		Usuario u = new Usuario(nombre);
		
		try {
			usuariosDao.create(u);
			System.out.println("Usuario " + u.getNombre() + " creado.");
		} catch (SQLException e) {
			System.out.println("Error al crear usuario.");
		}
	}
	
	@Publicable
	public static void eliminarPelicula(Pelicula p) {
		DeleteBuilder<Pelicula, String> deleteBuilder = peliculasDao.deleteBuilder();
		
		try {
			deleteBuilder.where().eq(Pelicula.ID_FIELD_NAME, p.getId());
			deleteBuilder.delete();
		} catch (SQLException e) {
			System.out.println("Error al eliminar pelicula.");
		}
	}
	
	@Publicable
	public static void eliminarUsuario(Usuario u) {
		DeleteBuilder<Usuario, String> deleteBuilder = usuariosDao.deleteBuilder();
		
		try {
			deleteBuilder.where().eq(Usuario.ID_FIELD_NAME, u.getId());
			deleteBuilder.delete();
		} catch (SQLException e) {
			System.out.println("Error al eliminar usuario.");
		}
	}
}