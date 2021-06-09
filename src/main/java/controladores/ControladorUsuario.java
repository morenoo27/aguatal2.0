package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Usuario;

public class ControladorUsuario {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aguatalv2");
	private EntityManager em;
	private Query consulta;

	/**
	 * Metodo que elimina una instancia de la tabla 'usuario'<br/>
	 * 
	 * <p>
	 * En este proceso, se abre el entityManager. Si el objeto no es gestionado por
	 * el contexto de persistencia, se carga en el y se guarda en un Objeto de tipo
	 * Usuario auxiliar previamente creaco e instanciado a null
	 * 
	 * <p>
	 * Ahora se puede borrar usando aux, porque es una entidad gestionada por la
	 * caché, se actualiza la base de datos y se cierra el entityManager
	 * 
	 * @param user Usuario que queremos eliminar
	 */
	public void deleteUser(Usuario user) {

		this.em = entityManagerFactory.createEntityManager();

		Usuario aux = null;

		this.em.getTransaction().begin();

		if (!this.em.contains(user)) {
			aux = this.em.merge(user);
		}

		this.em.remove(aux);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que elimina instancias de la tabla 'usuario'<br/>
	 * En este metodo se crea un nuevo EntityManager para no resetear el
	 * autoincremente de la tabla
	 * 
	 * <p>
	 * A partir de un bucle foreach, se eliminan cada uno de los registros
	 * 
	 * <p>
	 * Luego de eso, se abre la transaccion, creamos la query que resetea el
	 * auto_increment despues de vaciar la tabla completamente, realizamos un commit
	 * para guardar los cambios y se cierra el EntityManager
	 * 
	 * @return Numero de filas afectadas
	 */
	public int deleteAll() {

		EntityManager em2 = entityManagerFactory.createEntityManager();

		int numFilas = 0;

		List<Usuario> registros = findAll();

		for (Usuario aux : registros) {
			deleteUser(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table usuario auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	/**
	 * Metodo que realiza una actualizacion de los datos de un usuario que pasamos
	 * por parametro
	 * 
	 * <p>
	 * Para este proceso se trealiza una transaccion en la que se commitea la accion
	 * despues de usar el metodo <em>merge(Objeto)
	 * 
	 * <p>
	 * merge(Objeto)</em> - Si una entidad con el mismo identificador que user
	 * existe en el contexto de persistencia (cache), se actualizan sus atributos y
	 * se devuelve. Si el objeto user no existe en la base de datos, se comporta
	 * como persist() y la entidad gestionada es la devuelta por merge(), por lo que
	 * user es una entidad desconectada
	 * 
	 * @param user Usuario que queremos actualziar
	 */
	public void modifyuser(Usuario user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que inserta un Usuario en la tabla 'usuario'<br/>
	 * Se hace a partir del metodo persist(user)
	 * 
	 * <p>
	 * persist(user) - Guarda el objeto en el contexto de persistencia (caché
	 * intermedia) y la pone como entidad conectada
	 * 
	 * 
	 * @param user Usuario que queremos insertar
	 */
	public void insertUser(Usuario user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que inserta en la tabla usuario una lista de objetos Usuario medianto
	 * un forEach y el metodo insertUser(usuario user)
	 * 
	 * @param usuarios ArrayList de objetos Usuario
	 * @return numero de filas afectadas
	 */
	public int insertUser(ArrayList<Usuario> usuarios) {

		int numFilas = 0;
		for (Usuario aux : usuarios) {

			insertUser(aux);
			numFilas++;
		}

		return numFilas;
	}

	/**
	 * Metodo que almacena todas las instancias de la tabla 'usuario'
	 * 
	 * @return Lista con todos los usuarios
	 */
	public List<Usuario> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Usuario.findAll");

		@SuppressWarnings("unchecked")
		List<Usuario> listaTrabajadores = (List<Usuario>) consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}

	/**
	 * metodo que realiza una busqueda en la tabla 'usaurio' y devuelve esa
	 * instancia como un objeto de tipo Usuario
	 * 
	 * <p>
	 * Se realiza con un objeto Query a partir de una SQL nativa, que es :<br/>
	 * 'Select * from usuario where codUsuario = ?'
	 * 
	 * @param pk Primary key a buscar
	 * @return Objeto de tipo Usuario
	 */
	public Usuario findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			this.consulta = em.createNativeQuery("Select * from usuario where codUsuario = ?", Usuario.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			Usuario aux = (Usuario) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}

	}

	/**
	 * Metodo que busca en la tabla 'usaurio' aquellos que tengan ese nombre
	 * 
	 * <p>
	 * En este caso se va a utilizar una nativeQuery, que permite pasar código SQL
	 * directamente a la base de datos
	 * 
	 * 
	 * @param nombre Nombre a buscar en la tabla
	 * @return Lista de usuarios con ese nombre
	 */
	public List<Usuario> findByNombre(String nombre) {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNativeQuery("Select * from usuario where nombreUsuario=?", Usuario.class);

		this.consulta.setParameter(1, nombre);

		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuarios = consulta.getResultList();

		this.em.close();

		return listaUsuarios;
	}

	/**
	 * 
	 * @param usaurio
	 * @return
	 */
	public Usuario findByUsuario(String usaurio) {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNativeQuery("Select * from usuario where usuario=?", Usuario.class);

		this.consulta.setParameter(1, usaurio);

		Usuario usuarioABusacar = null;
		try {
			usuarioABusacar = (Usuario) consulta.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}

		this.em.close();

		return usuarioABusacar;
	}

	public static void main(String[] args) {

		ControladorUsuario cu = new ControladorUsuario();

		recogerDatosUsuario().forEach(System.out::println);

		recogerDatosUsuario().forEach(System.out::println);
	}

	private static ArrayList<Usuario> recogerDatosUsuario() {

		ControladorUsuario cu = new ControladorUsuario();

		return cu.findAll().stream().collect(Collectors.toCollection(ArrayList::new)); // conversion por stream a un
																						// arraylist
	}
}
