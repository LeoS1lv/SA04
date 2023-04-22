package br.com.SA04.sa04_spring.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.SA04.sa04_spring.excecao.LivroNaoEncontradoExcecao;
import br.com.SA04.sa04_spring.modelo.Ativos;
import br.com.SA04.sa04_spring.servico.ILivroServico;

@Controller
@RequestMapping("/livro")
public class LivrosControlador {

	@Autowired
	private ILivroServico service;

	@GetMapping("/")
	public String exibirPaginaInicial() {
		return "paginaInicio";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicao() {
		return "adicionarLivro";
	}

	@PostMapping("/salvar")
	public String salvarLivro(@ModelAttribute Ativos nomeLivro, Model modelo) {
		service.salvarLivro(nomeLivro);
		Long id = service.salvarLivro(nomeLivro).getId();
		String mensagem = "Livro com id: '" + id + "' salvo com sucesso!";
		modelo.addAttribute("message", mensagem);
		return "adicionarLivro";
	}

	@GetMapping("/listar")
	public String buscarTodosLivros(@RequestParam(value = "message", required = false) String mensagem, Model modelo) {
		List<Ativos> nomelivros = service.buscarTodosLivros();
		modelo.addAttribute("listagem", nomelivros);
		modelo.addAttribute("message", mensagem);
		return "listarLivros";
	}

	@GetMapping("/editar")
	public String exibirFormularioEdicao(Model modelo, RedirectAttributes atributos, @RequestParam Long id) {
		String pagina = null;
		try {
			Ativos nomeLivro = service.bucarLivroPorId(id);
			modelo.addAttribute("NomeLivro", nomeLivro);
			pagina = "editarLivro";
		} catch (LivroNaoEncontradoExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
			pagina = "redirect:listar";
		}
		return pagina;
	}

	@PostMapping("/atualizar")
	public String atualizarLivro(@ModelAttribute Ativos nomeLivro, RedirectAttributes atributos) {
		service.atualizarLivro(nomeLivro);
		Long id = nomeLivro.getId();
		atributos.addAttribute("message", "Livro com id: '" + id + "' atualizado com sucesso!");
		return "redirect:listar";
	}

	@GetMapping("/deletar")
	public String deletarLivro(@RequestParam Long id, RedirectAttributes atributos) {
		try {
			service.deletarLivroPorId(id);
			atributos.addAttribute("message", "Livro com id: '" + id + "' excluído com sucesso!");
		} catch (LivroNaoEncontradoExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
		}
		return "redirect:listar";
	}
}