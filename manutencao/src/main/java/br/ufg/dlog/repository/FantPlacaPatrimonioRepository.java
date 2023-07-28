package br.ufg.dlog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantPlacaPatrimonio;

@Repository
public interface FantPlacaPatrimonioRepository extends JpaRepository<FantPlacaPatrimonio, String> {
	
	@Query(value = "select placa_atual,patrimonio from t_veiculos\r\n"
			+ "where especie=?3 and situacao='1' and impedimento=0 and lotacao_v=1\r\n"
			+ "and patrimonio not in (select veiculo_a from t_agenda, t_data_agenda where t_agenda.cod_a=t_data_agenda.cod_data and t_data_agenda.dataagenda BETWEEN ?1 and ?2)\r\n"
			+ "order by  placa_atual desc", nativeQuery = true)
	List<FantPlacaPatrimonio> listaPlacaPatrimonio(Date ini, Date fim,Integer especie);

}
