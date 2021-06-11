package entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codUsuario;

	private String apellidosUsuario;

	private String direccion;

	private String email;

	private String nombreUsuario;

	private String pass;

	private String usuario;

	// bi-directional many-to-one association to Pedido
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario", insertable = false, updatable = true)
	private List<Pedido> pedidos;

	// bi-directional one-to-one association to Suscripcion
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario", insertable = false, updatable = false)
	private Suscripcion suscripcion;

	public Usuario() {
	}

	public int getCodUsuario() {
		return this.codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getApellidosUsuario() {
		return this.apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setUsuario(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setUsuario(null);

		return pedido;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Usuario [codUsuario=");
		builder.append(codUsuario);
		
		builder.append(", usuario=");
		builder.append(usuario);
		
		builder.append(", pass=");
		builder.append(pass);
		
		builder.append(", nombreUsuario=");
		builder.append(nombreUsuario);

		builder.append(", apellidosUsuario=");
		builder.append(apellidosUsuario);

		builder.append(", direccion=");
		builder.append(direccion);

		builder.append(", email=");
		builder.append(email);

		builder.append(", pedidos=");
		builder.append(pedidos());

		builder.append(", suscripcion=");
		try {
			builder.append(suscripcion.getCodSuscripcion());
		} catch (NullPointerException e) {
			builder.append("");
		}
		builder.append("]");
		return builder.toString();
	}

	private Object pedidos() {
		
		if (!this.pedidos.isEmpty()) {
			String txt = "";
			for (Pedido pedido : pedidos) {
				
				txt += pedido.getCodPedido() + ", ";
			}
			
			return txt;
		}
		
		return ", ";
	}

}