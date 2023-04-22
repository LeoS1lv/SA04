package br.com.SA04.sa04_spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.SA04.sa04_spring.modelo.Ativos;

public interface LivroRepositorio extends JpaRepository<Ativos, Long> {

}