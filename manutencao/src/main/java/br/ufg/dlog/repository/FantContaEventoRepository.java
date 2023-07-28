package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantContaEvento;

@Repository
public interface FantContaEventoRepository extends JpaRepository<FantContaEvento, String>{
	
	@Query(value = "select t_saida1.mes_s,count(t_saida1.cd_saida1)conta, sum(t_saida1.km_ro)km, t_evento.evento\r\n"
			+ "from t_saida1, t_evento\r\n"
			+ "where t_saida1.evento_s=t_evento.codigo\r\n"
			+ "and t_saida1.data_s>=?1\r\n"
			+ "and t_saida1.data_s<=?2\r\n"
			+ "group by t_evento.evento, t_saida1.mes_s\r\n"
			+ "order by t_saida1.mes_s, t_evento.evento", nativeQuery = true)
   Collection<FantContaEvento> contaKmMovEvento(Date inicio, Date fim);
}
