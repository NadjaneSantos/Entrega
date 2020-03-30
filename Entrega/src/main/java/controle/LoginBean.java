package controle;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import DAO.PessoaDAO;
import DAO.PessoaDAOImpl;
import entidade.Pessoa;
import util.JpaUtil;

@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean {

	private String usuarioInpt;
	private String senhaInpt;
	private String usuarioAdm = "admin";
	private String senhaAdm = "admin";
	private static final String TELAPESQUISA = "PesquisarPessoa.xhtml";
	private PessoaDAO pessoaDAO;
	private String mensagem;

	public LoginBean() {

		this.pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());

	}

	public void entrar() throws IOException {

		if (usuarioInpt.equals(usuarioAdm) && senhaInpt.equals(senhaAdm)) {

			FacesContext.getCurrentInstance().getExternalContext().redirect(TELAPESQUISA);

		} else {

			Pessoa buscaUsuario = this.pessoaDAO.pesquisar(this.usuarioInpt);

			if (buscaUsuario != null) {
				if (buscaUsuario.getSenha().equals(this.senhaInpt)) {
					FacesContext.getCurrentInstance().getExternalContext().redirect(TELAPESQUISA);
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Senha Incorreta."));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inexistente.", "Contate o admin."));

			}
		}
	}

	public void limpar() {
		PrimeFaces.current().resetInputs("form:panel");
	}

	public String getUsuarioInpt() {
		return usuarioInpt;
	}

	public void setUsuarioInpt(String usuarioInpt) {
		this.usuarioInpt = usuarioInpt;
	}

	public String getSenhaInpt() {
		return senhaInpt;
	}

	public void setSenhaInpt(String senhaInpt) {
		this.senhaInpt = senhaInpt;
	}

	public String getUsuarioAdm() {
		return usuarioAdm;
	}

	public void setUsuarioAdm(String usuarioAdm) {
		this.usuarioAdm = usuarioAdm;
	}

	public String getSenhaAdm() {
		return senhaAdm;
	}

	public void setSenhaAdm(String senhaAdm) {
		this.senhaAdm = senhaAdm;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
