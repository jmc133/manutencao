package br.ufg.dlog.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Cor;

@Transactional
@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {
	@Query(value = "SELECT max(cod_cor) FROM public.t_cor", nativeQuery = true)
	Long maximoCodigoCor();

}
