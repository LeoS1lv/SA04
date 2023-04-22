package br.com.SA04.sa04_spring.excecao;

public class LivroNaoEncontradoExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivroNaoEncontradoExcecao() {
		super();
	}

	public LivroNaoEncontradoExcecao(String mensagemPersonalizada) {
		super(mensagemPersonalizada);
	}
}