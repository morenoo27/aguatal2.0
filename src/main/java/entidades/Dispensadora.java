package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * <p>
 * Clase que representa la tabla dispensadora en la base de datos aguatalv2
 * 
 * <p>
 * Esta entidad es un objeto que tiene un codigo y una descripcion del tamaño
 * del mismo objeto que puede estar en un pedido
 * 
 */
@Entity //es una entidad en la base de datos
@Table(name = "dispensadora")//nombre de la tabla a la que hace referencia
@NamedQuery(name = "Dispensadora.findAll", query = "SELECT d FROM Dispensadora d")//query que selecciona todas las instancias que haya en la base de datos
public class Dispensadora implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int codDispensadora;

	private String tamanio;

	// bi-directional many-to-one association to Pedido
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codDispensadora", referencedColumnName = "codDispensadora",insertable = false, updatable = false)
	private Pedido pedido;

	public Dispensadora() {
	}

	public int getCodDispensadora() {
		return this.codDispensadora;
	}

	public void setCodDispensadora(int codDispensadora) {
		this.codDispensadora = codDispensadora;
	}

	public String getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Dispensadora [codDispensadora=");
		builder.append(codDispensadora);
		
		builder.append(", tamanio=");
		builder.append(tamanio);
		
		builder.append(", pedido=");
		try {
			builder.append(pedido.getCodPedido());
		} catch (NullPointerException e) {
			builder.append("");
		}
		
		builder.append("]");
		return builder.toString();
	}
}