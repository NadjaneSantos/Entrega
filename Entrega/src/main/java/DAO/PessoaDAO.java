package DAO;

import java.util.List;

import entidade.Pessoa;

public interface PessoaDAO {
	
	public boolean inserir(Pessoa pessoa);
	
	public void alterar(Pessoa pessoa);
	
	public void remover(Pessoa pessoa);

	public Pessoa pesquisar(String cpf);
	
	public List<Pessoa> listarTodos();


}
