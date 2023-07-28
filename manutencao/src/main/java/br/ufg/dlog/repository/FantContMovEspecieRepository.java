package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantContMovEspecie;

@Repository
public interface FantContMovEspecieRepository extends JpaRepository<FantContMovEspecie, String>{
	
	@Query(value = "select t_saida1.mes_s,count(t_saida1.cd_saida1)conta, sum(t_saida1.km_ro)km, t_especie.especie\r\n"
			+ "from t_saida1, t_veiculos, t_especie\r\n"
			+ "where t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "and t_saida1.data_s>=?1\r\n"
			+ "and t_saida1.data_s<=?2\r\n"
			+ "group by t_especie.especie, t_saida1.mes_s\r\n"
			+ "order by t_saida1.mes_s, t_especie.especie", nativeQuery = true)
	Collection<FantContMovEspecie> contaMovEspecie(Date ini, Date fim);

}
