package br.com.SA04.sa04_spring.servico.implementacao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.SA04.sa04_spring.excecao.LivroNaoEncontradoExcecao;
import br.com.SA04.sa04_spring.modelo.Ativos;
import br.com.SA04.sa04_spring.repositorio.LivroRepositorio;
import br.com.SA04.sa04_spring.servico.ILivroServico;
 
@Service
public class LivroServicoImpl implements ILivroServico {
 
	@Autowired
	private LivroRepositorio repositorio;
 
	@Override
	public Ativos salvarLivro(Ativos nomeLivro) {
		return repositorio.save(nomeLivro);
	}
 
	@Override
	public List<Ativos> buscarTodosLivros() {
		return repositorio.findAll();
	}
 
	@Override
	public Ativos bucarLivroPorId(Long id) {
		Optional<Ativos> opcional = repositorio.findById(id);
		if (opcional.isPresent()) {
			return opcional.get();
		} else {
			throw new LivroNaoEncontradoExcecao("Livro com id: " + id + " não encontrado.");
		}
	}
 
	@Override
	public void deletarLivroPorId(Long id) {
		repositorio.delete(bucarLivroPorId(id));
	}
 
	@Override
	public void atualizarLivro(Ativos invoice) {
		repositorio.save(invoice);
	}
}