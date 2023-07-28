package br.ufg.dlog.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Lotacao;

@Transactional
@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Long>{
	@Query(value = "SELECT MAX(cd_lotacao) FROM public.t_lotacao", nativeQuery = true)
	Long maximoCodigoLotacao();

}
