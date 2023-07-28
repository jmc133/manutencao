package br.ufg.dlog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantContaEspecie;

@Repository
public interface FantContaEspecieRepository extends JpaRepository<FantContaEspecie, String> {
	
	@Query(value = "select count(cd_saida1)conta, t_especie.especie\r\n"
			+ "from t_saida1, t_especie, t_veiculos\r\n"
			+ "where t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "and t_saida1.data_s>=?1\r\n"
			+ "and t_saida1.data_s<=?2\r\n"
			+ "group by t_especie.especie\r\n"
			+ "order by t_especie.especie", nativeQuery = true)
	List<FantContaEspecie> contaEspeciePeriodo(Date dataini, Date datafim);
	
	@Query(value = "select count(cd_saida1)cont from t_saida1\r\n"
			+ "where data_s>=?1 and data_s<=?2", nativeQuery = true)
	int contaTodaSaida(Date ini, Date fim);
	
	@Query(value = "select count(cd_saida1)*100/(select count(cd_saida1)from t_saida1\r\n"
			+ "			where t_saida1.data_s>=?1 and t_saida1.data_s<=?2)porcent\r\n"
			+ "			from t_saida1, t_especie, t_veiculos\r\n"
			+ "			where t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "			and t_saida1.data_s>=?3\r\n"
			+ "			and t_saida1.data_s<=?4\r\n"
			+ "			group by t_especie.especie\r\n"
			+ "			order by t_especie.especie", nativeQuery = true)
	int[] porcentagensdeEspecies(Date inisubquery, Date fimsubquery, Date ini, Date fim);
	@Query(value = "select t_especie.especie from t_saida1, t_especie, t_veiculos\r\n"
			+ "			where t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "			and t_saida1.data_s>=?1\r\n"
			+ "			and t_saida1.data_s<=?2\r\n"
			+ "			group by t_especie.especie\r\n"
			+ "			order by t_especie.especie", nativeQuery = true)
	String[] arrayEspecies( Date ini, Date fim);

}
