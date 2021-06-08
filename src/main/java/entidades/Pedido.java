package entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la tabla pedido de la base de datos aguatalv2 que
 * contiene la informacion sobre un pedido que realiza un cliente/usuario
 * solicitando una dispensadora
 * 
 */
@Entity
@Table(name = "pedido")
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codPedido;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUsuario", insertable = false, updatable = false)
	private Usuario usuario;

	// bi-directional many-to-one association to Dispensadora
	@OneToMany(mappedBy = "pedido")
	private List<Dispensadora> dispensadoras;

	public Pedido() {
		this.dispensadoras = new ArrayList<>();
	}

	public int getCodPedido() {
		return this.codPedido;
	}

	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Dispensadora> getDispensadoras() {
		return this.dispensadoras;
	}

	public void setDispensadoras(List<Dispensadora> dispensadoras) {
		this.dispensadoras = dispensadoras;
	}

	public Dispensadora addDispensadora(Dispensadora dispensadora) {
		getDispensadoras().add(dispensadora);
		dispensadora.setPedido(this);

		return dispensadora;
	}

	public Dispensadora removeDispensadora(Dispensadora dispensadora) {
		getDispensadoras().remove(dispensadora);
		dispensadora.setPedido(null);

		return dispensadora;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Pedido [codPedido=");
		builder.append(codPedido);
		
		builder.append(", dispensadoras=");
		builder.append(obtenerDis());
		
		builder.append(", usuario=");
		try {
			builder.append(usuario.getCodUsuario());
		} catch (NullPointerException e) {
			builder.append("");
		}
		
		builder.append("]");
		return builder.toString();
	}

	private Object obtenerDis() {

		if (!this.dispensadoras.isEmpty()) {
			String texto = "";
			
			for (Dispensadora dispensadora : dispensadoras) {
				
				
				texto += dispensadora.toString() + ", ";
			}
			
			return texto;
		}
		
		return ", ";
	}

	
}