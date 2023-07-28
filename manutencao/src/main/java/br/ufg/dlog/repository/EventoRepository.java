package br.ufg.dlog.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Evento;

@Repository
@Transactional
public interface EventoRepository extends JpaRepository<Evento, Long> {
	@Query(value = "select * from t_evento where codigo=4", nativeQuery = true)
	Collection<Evento> selecionaManutencao();

}
