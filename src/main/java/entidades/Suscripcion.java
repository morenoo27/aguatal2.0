package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * <p>
 * Clase representativa de la tabla suscripcion en la base de datos aguatalv2
 * 
 * <p>
 * Esta clase tiene su codigo unico e identificativo y un precio mensual ademas
 * de estar relacionado con un usuario del sistema
 * 
 */
@Entity
@Table(name = "suscripcion")
@NamedQuery(name = "Suscripcion.findAll", query = "SELECT s FROM Suscripcion s")
public class Suscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codSuscripcion;

	private double precioMensual;

	// bi-directional one-to-one association to Usuario
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario")
//	@OneToOne( cascade =  { CascadeType.PERSIST } )
//	@JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario")
	private Usuario usuario;

	public Suscripcion() {
	}

	public int getCodSuscripcion() {
		return this.codSuscripcion;
	}

	public void setCodSuscripcion(int codSuscripcion) {
		this.codSuscripcion = codSuscripcion;
	}

	public double getPrecioMensual() {
		return this.precioMensual;
	}

	public void setPrecioMensual(double precioMensual) {
		this.precioMensual = precioMensual;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Suscripcion [codSuscripcion=");
		builder.append(codSuscripcion);

		builder.append("precioMensual=");
		builder.append(precioMensual);

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