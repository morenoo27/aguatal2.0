package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Suscripcion;

public class ControladorSuscripcion {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aguatalv2");
	private EntityManager em;
	private Query consulta;

	/**
	 * metodo que elimina una suscripcion del sistema
	 * 
	 * @param sus Suscripocion que queremos eliminar
	 */
	public void deleteSus(Suscripcion sus) {

		this.em = entityManagerFactory.createEntityManager();

		Suscripcion aux = null;

		this.em.getTransaction().begin();

		if (!this.em.contains(sus)) {
			aux = this.em.merge(sus);
		}

		this.em.remove(aux);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que elimina todas las suscripciones que existen en el sistema
	 * 
	 * @return Numero de instancias eliminadas
	 */
	public int deleteAll() {

		EntityManager em2 = entityManagerFactory.createEntityManager();

		int numFilas = 0;

		List<Suscripcion> registros = findAll();

		for (Suscripcion aux : registros) {
			deleteSus(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table suscripcion auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	/**
	 * Metodo que realiza una actualizacion de una suscripcion ya existente en el
	 * sistema
	 * 
	 * @param sus Suscripcion a actualizar
	 */
	public void modifySus(Suscripcion sus) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(sus);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que inserta una nueva suscripcion en el sistema
	 * 
	 * @param sus Suscripcion que queremos inserta en el sistema
	 */
	public void insertSus(Suscripcion sus) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(sus);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que instancia una serie de nuevas suscripciones en el sistema
	 * 
	 * @param suscripciones Lista copn las nuevas suscripciones
	 * @return Numero de operaciones realizadas con exito
	 */
	public int insertSuss(ArrayList<Suscripcion> suscripciones) {

		int numFilas = 0;
		for (Suscripcion aux : suscripciones) {

			insertSus(aux);
			numFilas++;
		}

		return numFilas;
	}

	/**
	 * Metodo que obtiene todas las suscripciones que hay registreadas en el sistema
	 * 
	 * @return
	 */
	public List<Suscripcion> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Suscripcion.findAll");

		@SuppressWarnings("unchecked")
		List<Suscripcion> listaTrabajadores = consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}

	/**
	 * Busqueda de la suscripcion x
	 * 
	 * @param pk Primary key de la suscipcion
	 * @return SUscripcion que queremos buscar
	 */
	public Suscripcion findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Suscripcion aux = null;

			this.consulta = em.createNativeQuery("Select * from suscripcion where codSuscripcion = ?",
					Suscripcion.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Suscripcion) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}

	}

	/**
	 * Metodo que realiza una gusqueda de una suscripcion ligada a un usuario
	 * 
	 * @param pk Primary key del usuario que queremos su suscripcion
	 * @return Suscripcion de dicho usuario
	 */
	public Suscripcion findByUserPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Suscripcion aux = null;

			this.consulta = em.createNativeQuery("Select * from suscripcion where codUsuario = ?", Suscripcion.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Suscripcion) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}

	}
}