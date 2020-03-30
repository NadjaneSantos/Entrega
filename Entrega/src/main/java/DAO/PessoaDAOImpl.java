package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Pessoa;

public class PessoaDAOImpl implements PessoaDAO {

	private EntityManager em;

	public PessoaDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean inserir(Pessoa pessoa) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(pessoa);
		et.commit();
		return true;
	}

	@Override
	public void alterar(Pessoa pessoa) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(pessoa);
		et.commit();

	}

	@Override
	public void remover(Pessoa pessoa) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(pessoa);
		et.commit();

	}

	@Override
	// Na instancia de pessoa, na minha conexão em vou procurar (find) o cpf dentro
	// da minha classe pessoa (Pessoa.class, cpf). Onde retorna a pessoa
	public Pessoa pesquisar(String cpf) {
		Pessoa pessoa = em.find(Pessoa.class, cpf);
		return pessoa;
	}

	@Override
	public List<Pessoa> listarTodos() {
		Query query = em.createQuery("from Pessoa p");
		@SuppressWarnings("unchecked")
		List<Pessoa> pessoas = query.getResultList();
		return pessoas;
	}

}
