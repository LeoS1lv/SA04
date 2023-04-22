package br.com.SA04.sa04_spring.servico;

import java.util.List;
import br.com.SA04.sa04_spring.modelo.Ativos;

public interface ILivroServico {

	public Ativos salvarLivro(Ativos nomeLivro);

	public List<Ativos> buscarTodosLivros();

	public Ativos bucarLivroPorId(Long id);

	public void deletarLivroPorId(Long id);

	public void atualizarLivro(Ativos nomeLivro);

}