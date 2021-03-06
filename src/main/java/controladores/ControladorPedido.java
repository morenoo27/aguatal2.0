package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Pedido;

public class ControladorPedido {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aguatalv2");
	private EntityManager em;
	private Query consulta;

	/**
	 * Metodo que elimina un peido del sistema
	 * 
	 * @param pedido Pedido que queremos eliminar
	 */
	public void deletePed(Pedido pedido) {

		this.em = entityManagerFactory.createEntityManager();

		Pedido aux = null;

		this.em.getTransaction().begin();

		if (!this.em.contains(pedido)) {
			aux = this.em.merge(pedido);
		}

		this.em.remove(aux);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Elimina todos los pedidos existenbte en el sistema
	 * 
	 * @return cantidad de pedidos eliminados
	 */
	public int deleteAll() {

		EntityManager em2 = entityManagerFactory.createEntityManager();

		int numFilas = 0;

		List<Pedido> registros = findAll();

		for (Pedido aux : registros) {
			deletePed(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table pedido auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	/**
	 * Metodo que modifica un pedido de la base de datos
	 * 
	 * <p>
	 * Realmente este metodo va a ser poco realizado en una aplicacion real del
	 * sistema, pero puede estar disponible para el momneto en el que por algun
	 * casual haga falta modificar un pedido
	 * 
	 * @param ped Pedido modificado
	 */
	public void modifyPed(Pedido ped) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(ped);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Introduce un pedido en el sistema
	 * 
	 * @param pedido PEdido que queremos insertar en el sistema
	 */
	public void insertPed(Pedido pedido) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(pedido);

		this.em.getTransaction().commit();

		this.em.close();
	}

	/**
	 * Metodo que inserta una serie de pedidos que estan en una lista de estos
	 * objetos
	 * 
	 * <p>
	 * Realmente, lo que se realiza es un foreach en el que por cada pedido de la
	 * lista, se ejecuta el insertPed de objetos individuales
	 * 
	 * @param pedidos Lista de pedido que queremos insertar en el sistema
	 * @return cantidad de pedidos insertados en el sistema
	 */
	public int insertDiss(ArrayList<Pedido> pedidos) {

		int numFilas = 0;
		for (Pedido aux : pedidos) {

			insertPed(aux);
			numFilas++;
		}

		return numFilas;
	}

	/**
	 * Metodo que obtiene todos los pedidos del sistema
	 * 
	 * @return Lista con todos los pedidos
	 */
	public List<Pedido> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Pedido.findAll");

		@SuppressWarnings("unchecked")
		List<Pedido> listaTrabajadores = consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}

	/**
	 * Busca un pedido seguns su clave primaria
	 * 
	 * <p>
	 * En este caso la clave primaria es un numero unico e identificativo
	 * 
	 * @param pk numero identificativo
	 * @return Pedido que estamos buscando
	 */
	public Pedido findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Pedido aux = null;

			this.consulta = em.createNativeQuery("Select * from pedido where codPedido = ?", Pedido.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Pedido) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}
	}

	/**
	 * Metodo que busca todos los pedidos solicitados por un usuario en concreto
	 * 
	 * <p>
	 * Este metodo realiza una busque en el sistema en el que filtra que los pedudos
	 * tengan como clave foranea en el campo usuario, la clave primaria del usuario
	 * en el que centramos la busqueda
	 * 
	 * @param pkUser Numero identificativo del usuario
	 * @return Lista con todos los pedidos que tiene a su cargo el usuario
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Pedido> findByUser(int pkUser) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			ArrayList<Pedido> aux = new ArrayList<>();

			this.consulta = em.createNativeQuery("Select * from pedido where codUsuario = ?", Pedido.class);

			this.consulta.setParameter(1, pkUser);// intercambiar primera ? por pk

			aux = (ArrayList<Pedido>) consulta.getResultList().stream()
					.collect(Collectors.toCollection(ArrayList::new));

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}
	}
}