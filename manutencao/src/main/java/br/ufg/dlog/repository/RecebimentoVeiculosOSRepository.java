package br.ufg.dlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.RecebimentoVeiculosDeOS;

@Repository
public interface RecebimentoVeiculosOSRepository extends JpaRepository<RecebimentoVeiculosDeOS, Long> {
	
	@Query(value = "select max(idrecebimentos)id from recebimentoveiculosos", nativeQuery = true)
	Long verIdmaximo();
	
	@Query(value = "select * from recebimentoveiculosos where idrecebimentos=?1 ", nativeQuery = true)
	RecebimentoVeiculosDeOS selecionaPorId(Long id);
	
	@Query(value = "select * from recebimentoveiculosos where fkordemservico=?1 and fkpessoajuridica=?2", nativeQuery = true)
	RecebimentoVeiculosDeOS selecionaPorOSePJ(Long os, Long pj);

}
