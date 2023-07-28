package br.ufg.dlog.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.FatasmaVeiculo;

@Repository
@Transactional
public interface FatasmaVeiculoRepository extends JpaRepository<FatasmaVeiculo, String> {

	@Query(value = "SELECT patrimonio, placa_atual, t_tipo.tipo	FROM public.t_veiculos, t_tipo\n"
			+ "where situacao like'%1%' and t_veiculos.tipo=t_tipo.cod_tipo and t_veiculos.impedimento=0\n"
			+ "order by t_veiculos.placa_atual", nativeQuery=true)
	List<FatasmaVeiculo> buscaPlacaPtrimonio();
}
