package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import DAO.PessoaDAO;
import DAO.PessoaDAOImpl;
import entidade.Endereco;
import entidade.Pessoa;
import util.JpaUtil;

/**
 * @author Nadjane Para o CRUD de pessoa vamos ter os dados de pessoa e
 *         endereço. Para pesquisar pessoa (listar todas as pessoas) vamos
 *         utilizar a chave primaria de pessoa que retornará uma lista de
 *         pessoas. Logo, precisa dos atributos pessoa, endereco, cpf pesquisado
 *         e lista de pessoas. Recuperar os dados do banco vamos utilizar a
 *         interface PessoaDAO. Link das páginas para redirecionamento.
 */

@ManagedBean(name = "PessoaBean")
@SessionScoped
public class PessoaBean {

	private Pessoa pessoa;
	private Endereco endereco;
	private String cpfSelecionado;
	private List<Pessoa> listaPessoas;
	private PessoaDAO pessoaDAO;
	private static final String TELACRUD = "CrudPessoa.xhtml";
	private static final String TELAPESQUISA = "PesquisarPessoa.xhtml";

	public PessoaBean() {

		// Instanciar os objetos, construtor PessoaBean
		this.pessoa = new Pessoa();
		// Pessoa passui lista de Telefone que serão criados no momento da criação da
		// pessoa
		this.pessoa.setEnderecos(new ArrayList<Endereco>());
		this.endereco = new Endereco();
		this.listaPessoas = new ArrayList<Pessoa>();

		// Instanciar a interface PessoaDao que vai receber a classe PessoaDAOImpl com a
		// conexão com o banco de dados atraves do JPA

		this.pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());

		// Para recuperar a lista de todos as pessoas
		this.listaPessoas = this.pessoaDAO.listarTodos();
		System.out.println(this.listaPessoas);
	}

	public void pesquisar() {
		// Recupera a lista de todas as pessoas
		this.listaPessoas = this.pessoaDAO.listarTodos();
	}

	public void salvar() throws IOException {

		if (this.pessoaDAO.inserir(this.pessoa)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sucesso !!!"));
			abrirPerquisarPessoa();

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir !!!"));
		}

	}

	public void addEndereco() {

		
			Endereco novoEndereco = new Endereco();

			novoEndereco.setRua(this.endereco.getRua());
			novoEndereco.setNumero(this.endereco.getNumero());
			novoEndereco.setComplemento(this.endereco.getComplemento());
			novoEndereco.setPessoa(this.pessoa);

			this.pessoa.getEnderecos().add(novoEndereco);
			this.endereco = new Endereco();

			}

	
	public void abrirPerquisarPessoa() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(TELAPESQUISA);

	}

	public void editar() throws IOException {
		Pessoa pessoaEdicao = this.pessoaDAO.pesquisar(cpfSelecionado);
		this.pessoa = pessoaEdicao;
		abrirCRUDPessoa();
	}

	public void abrirCRUDPessoa() throws IOException {
		this.limpar();
		FacesContext.getCurrentInstance().getExternalContext().redirect(TELACRUD);

	}

	public String remover() {
		Pessoa pessoaRemocao = this.pessoaDAO.pesquisar(cpfSelecionado);
		this.pessoaDAO.remover(pessoaRemocao);
		this.listaPessoas = this.pessoaDAO.listarTodos();
		return "";

	}

	public void limpar() {
		
		this.listaPessoas = new ArrayList<Pessoa>();
		this.pessoa = new Pessoa();
		this.pessoa.setEnderecos(new ArrayList<Endereco>());
		this.endereco = new Endereco();
		
	}


	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpfSelecionado() {
		return cpfSelecionado;
	}

	public void setCpfSelecionado(String cpfSelecionado) {
		this.cpfSelecionado = cpfSelecionado;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

}
