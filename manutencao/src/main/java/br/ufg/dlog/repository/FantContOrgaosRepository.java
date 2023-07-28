package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantContOrgaos;

@Repository
public interface FantContOrgaosRepository extends JpaRepository<FantContOrgaos, String> {
	
	@Query(value = "select t_saida1.mes_s,count(t_saida1.cd_saida1)conta, sum(t_saida1.km_ro)km, orgao.sg_orgao\r\n"
			+ "from t_saida1, orgao\r\n"
			+ "where t_saida1.fk_uni=orgao.cd_orgao\r\n"
			+ "and t_saida1.data_s>=?1\r\n"
			+ "and t_saida1.data_s<=?2\r\n"
			+ "group by orgao.sg_orgao, t_saida1.mes_s\r\n"
			+ "order by t_saida1.mes_s, orgao.sg_orgao", nativeQuery = true)
  Collection<FantContOrgaos> contaMovOrgaos(Date ini, Date fim);
}
