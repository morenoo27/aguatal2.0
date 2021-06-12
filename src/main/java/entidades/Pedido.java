package entidades;

import java.io.Serializable;
import javax.persistence.*;

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
	@JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario", insertable = false, updatable = true)
	private Usuario usuario;

	// bi-directional many-to-one association to Dispensadora
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "codDispensadora", referencedColumnName = "codDispensadora", insertable = false, updatable = true)
	private Dispensadora dispensadora;

	public Pedido() {
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

	public Dispensadora getDispensadoras() {
		return this.dispensadora;
	}

	public void setDispensadoras(Dispensadora dispensadoraPedido) {
		this.dispensadora = dispensadoraPedido;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Pedido [codPedido=");
		builder.append(codPedido);
		
		builder.append(", dispensadoras=");
		builder.append(dispensadora.getCodDispensadora());
		
		builder.append(", usuario=");
		try {
			builder.append(usuario.getCodUsuario());
		} catch (NullPointerException e) {
			builder.append("");
		}
		
		builder.append("]");
		return builder.toString();
	}
	
}