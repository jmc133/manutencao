package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.Id;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import br.ufg.dlog.classes.Veiculos;

@Transactional
@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos,String>{
	
	
	@Query(value = "SELECT patrimonio, placa_atual 	FROM public.t_veiculos", nativeQuery=true)
	List<Veiculos> buscaPlacaPtrimonio();
	
	@Query(value = "SELECT * FROM public.t_veiculos where situacao like'%1%' order by placa_atual", nativeQuery = true)
	List<Veiculos> findVeiculosAtivos();
	
    @Query(value = "select * from t_veiculos where placa_atual like 'P%' and situacao='1'", nativeQuery = true)
    List<Veiculos> buscaTesteManutencao();

	@Query(value = "SELECT * FROM public.t_veiculos where patrimonio=?1 order by placa_atual", nativeQuery = true)
	Veiculos pesquisaPorPatrimonio(String patrimonio);
	
	@Query(value = "select * from t_veiculos where patrimomio like '?1%' and placa_atual like'?2%'", nativeQuery = true)
	Veiculos pesquisaExistenciaVeiculo(String patrimonio, String placa);
	
	@Query(value = "select * from t_veiculos where impedimento=0 and situacao like'%1%'  order by placa_atual", nativeQuery = true)
	Collection<Veiculos> pesquisaVeiculoLivres();
	
	@Query(value = "select * from t_veiculos where situacao='1'", nativeQuery = true)
    List<Veiculos> veiculosAtivos();
}
