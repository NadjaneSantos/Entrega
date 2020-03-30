package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="endereco")
public class Endereco {

	@Id
	@Column(name="id")
	@GeneratedValue (generator= "S_endereco")
	@SequenceGenerator(name="S_endereco", sequenceName = "S_endereco", allocationSize = 1)
	private long id;
	@Column(name="numero")
	private int numero;
	@Column(name="rua")
	private String rua;
	@Column(name="complemento")
	private String complemento;
	@ManyToOne
	@JoinColumn(name="cpf_pessoa", referencedColumnName = "cpf", nullable = false)
	private Pessoa pessoa;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
