package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantContaVeiculos;

@Repository
public interface FantContaVeiculosRepository extends JpaRepository<FantContaVeiculos, String> {
	
	@Query(value = "select t_saida1.mes_s,count(t_saida1.cd_saida1)conta, sum(t_saida1.km_ro)km, t_veiculos.placa_atual \r\n"
			+ "from t_saida1, t_veiculos\r\n"
			+ "where t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "and t_saida1.data_s>=?1\r\n"
			+ "and t_saida1.data_s<=?2\r\n"
			+ "group by t_veiculos.placa_atual, t_saida1.mes_s\r\n"
			+ "order by t_saida1.mes_s, t_veiculos.placa_atual", nativeQuery = true)
	Collection<FantContaVeiculos> contaKmSaidaVeiculos(Date inicio, Date fim);

}
