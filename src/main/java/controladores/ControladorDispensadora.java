package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public void deleteDis(Dispensadora cli) {

		this.em = entityManagerFactory.createEntityManager();

		Dispensadora aux = null;

		this.em.getTransaction().begin();

		if (!this.em.contains(cli)) {
			aux = this.em.merge(cli);
		}

		this.em.remove(aux);

		this.em.getTransaction().commit();

		this.em.close();
	}

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

	public void modifyDIs(Dispensadora user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public void insertDis(Dispensadora user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public int insertDiss(ArrayList<Dispensadora> suscripciones) {

		int numFilas = 0;
		for (Dispensadora aux : suscripciones) {

			insertDis(aux);
			numFilas++;
		}

		return numFilas;
	}

	public List<Dispensadora> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Dispensadora.findAll");

		@SuppressWarnings("unchecked")
		List<Dispensadora> listaTrabajadores = consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}

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

	public static void main(String[] args) {

		ControladorDispensadora cd = new ControladorDispensadora();

		Dispensadora d = new Dispensadora();
		d.setTamanio("mediana");
		d.setPedido(null);

		cd.insertDis(d);
		
		ArrayList <Dispensadora> dispensadoras = cd.findAll().stream().collect(Collectors.toCollection(ArrayList::new));
		dispensadoras.forEach(System.out::println);
		
		System.out.println(cd.findByPK(1).toString());
	}
}
