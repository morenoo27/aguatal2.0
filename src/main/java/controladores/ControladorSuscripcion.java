package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Suscripcion;
import entidades.Usuario;


public class ControladorSuscripcion {

	// Factoria para obtener objetos EntityManager
		private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aguatalv2");
		private EntityManager em;
		private Query consulta;

		public void deleteSus(Suscripcion cli) {

			this.em = entityManagerFactory.createEntityManager();

			Suscripcion aux = null;

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

		public void modifySus(Suscripcion user) {

			this.em = entityManagerFactory.createEntityManager();

			this.em.getTransaction().begin();

			this.em.merge(user);

			this.em.getTransaction().commit();

			this.em.close();
		}

		public void insertSus(Suscripcion user) {

			this.em = entityManagerFactory.createEntityManager();

			this.em.getTransaction().begin();

			this.em.persist(user);

			this.em.getTransaction().commit();

			this.em.close();
		}

		public int insertSuss(ArrayList<Suscripcion> suscripciones) {

			int numFilas = 0;
			for (Suscripcion aux : suscripciones) {

				insertSus(aux);
				numFilas++;
			}

			return numFilas;
		}

		public List<Suscripcion> findAll() {

			this.em = entityManagerFactory.createEntityManager();

			this.consulta = em.createNamedQuery("Suscripcion.findAll");

			@SuppressWarnings("unchecked")
			List<Suscripcion> listaTrabajadores = consulta.getResultList();

			this.em.close();

			return listaTrabajadores;
		}

		public Suscripcion findByPK(int pk) {

			try {
				this.em = entityManagerFactory.createEntityManager();

				Suscripcion aux = null;

				this.consulta = em.createNativeQuery("Select * from suscripcion where codSuscripcion = ?", Suscripcion.class);

				this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

				aux = (Suscripcion) consulta.getSingleResult();

				this.em.close();

				return aux;
			} catch (NoResultException ex) {
				System.out.println("No se encuentra el dato que se queire buscar");
				return null;
			}

		}

		public static void main(String[] args) {
			
			ControladorSuscripcion cs = new ControladorSuscripcion();
			ControladorUsuario cu = new ControladorUsuario();
			
			ArrayList<Suscripcion> suscripciones = cs.findAll().stream().collect(Collectors.toCollection(ArrayList::new));
			
			suscripciones.forEach(System.out::println);
			
//			Suscripcion nuevaSus = new Suscripcion();
//			nuevaSus.setPrecioMensual(5.99);
//			
//			cs.insertSus(nuevaSus);
//			
//			Usuario vico = new Usuario();
//			vico.setUsuario("vico");
//			vico.setPass("1234");
//			vico.setCodUsuario(2);
//			vico.setApellidosUsuario("Super Star");
//			vico.setNombreUsuario("Vico");
//			vico.setEmail("vico@apruebame.com");
//			vico.setDireccion("Mar Alboran");
//			
//			cu.insertUser(vico);
			
			
			
			
			Usuario vico = new Usuario();
			vico.setUsuario("vico");
			vico.setPass("1234");
			vico.setApellidosUsuario("Super Star");
			vico.setNombreUsuario("Vico");
			vico.setEmail("vico@apruebame.com");
			vico.setDireccion("Mar Alboran");
			
			
			cu.insertUser(vico);
			
			Suscripcion sus = new Suscripcion();
			sus.setPrecioMensual(5.99);
			
			cs.insertSus(sus);
			
			cu.findAll().stream().collect(Collectors.toCollection(ArrayList::new)).forEach(System.out::println);
			
			cs.findAll().stream().collect(Collectors.toCollection(ArrayList::new)).forEach(System.out::println);
			
		}
}
