package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Dispensadora;

public class ControladorDispensadora {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aguatalv2");
	private EntityManager em;
	private Query consulta;

	/**
	 * Metodo que elimina una dispensadora del sistema
	 * 
	 * @param dis Objeto dDispensadora que queremos eliminar
	 */
	public void deleteDis(Dispensadora dis) {

		this.em = entityManagerFactory.createEntityManager();

		Dispensadora aux = null;

		this.em.getTransaction().begin();

		if (!this.em.contains(dis)) {
			aux = this.em.merge(dis);
		}

		this.em.remove(aux);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metdo que elimina toda la tabal 'dispensadora' del sistema
	 * 
	 * @return numero de dispensadoras eliminadas
	 */
	public int deleteAll() {

		EntityManager em2 = entityManagerFactory.createEntityManager();

		int numFilas = 0;

		List<Dispensadora> registros = findAll();

		for (Dispensadora aux : registros) {
			deleteDis(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table dispensadora auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	/**
	 * Metodo para modificar una dispensadora creada en el sistema
	 * 
	 * @param dis Objeto dispensadora que queremos modificar (ya existenmte en la
	 *            base de ddatos)
	 */
	public void modifyDIs(Dispensadora dis) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(dis);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que inserta una dispensadiora nueva en al base de datos
	 * 
	 * @param dis Dispensadora que queremos insertar
	 */
	public void insertDis(Dispensadora dis) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(dis);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo para inserta una cantidad de dispensadoras(metidas en una lista)
	 * <p>
	 * Este metodo realmente ejecuta el metodo insertar dispensadora individual pero
	 * en cada dispensadora de la lista
	 * 
	 * @param suscripciones Lista de dispensadora a insertar
	 * @return numero de dispensadoras introducidas
	 */
	public int insertDiss(ArrayList<Dispensadora> suscripciones) {

		int numFilas = 0;
		for (Dispensadora aux : suscripciones) {

			insertDis(aux);
			numFilas++;
		}

		return numFilas;
	}

	/**
	 * metodo que devuelve todas las dispensadoraas metidas en el sistema
	 * 
	 * @return Lista con todas las dipensadoras
	 */
	public List<Dispensadora> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Dispensadora.findAll");

		@SuppressWarnings("unchecked")
		List<Dispensadora> listaTrabajadores = consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}

	/**
	 * Metodo que buisca una dispensadora en concreto. "La dispensadora con pk x"
	 * 
	 * @param pk Primary key de la dispensadora
	 * @return Objeto dispensadora que estamos buscando
	 */
	public Dispensadora findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Dispensadora aux = null;

			this.consulta = em.createNativeQuery("Select * from dispensadora where codDispensadora = ?",
					Dispensadora.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Dispensadora) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}
	}
}
