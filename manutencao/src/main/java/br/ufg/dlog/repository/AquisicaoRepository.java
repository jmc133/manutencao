package br.ufg.dlog.repository;
 





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Aquisicao;

@Repository
@Transactional
public interface AquisicaoRepository extends JpaRepository<Aquisicao, Long> {
	@Query(value = "SELECT max(cod_aqui) FROM public.t_aquisicao", nativeQuery = true)
	Long maximoCodigoAquisicao();
	
	

}
