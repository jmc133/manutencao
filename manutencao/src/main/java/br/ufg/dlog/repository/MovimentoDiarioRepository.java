package br.ufg.dlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.MovimentoDiaDia;

@Repository
public interface MovimentoDiarioRepository extends JpaRepository<MovimentoDiaDia, Long> {
	
	@Query(value = "select max(codigo)cod from t_movimentodiario", nativeQuery = true)
	Long maximoIdMovimentoDiario();

}
