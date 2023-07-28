package br.ufg.dlog.repository;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;






import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Movimento;


import br.ufg.dlog.relatorios.RelMovMesEspecie;
import br.ufg.dlog.relatorios.RelMovMesMotorista;
import br.ufg.dlog.relatorios.RelMovMesOrgaos;
import br.ufg.dlog.relatorios.RelMovMesRequisicao;
import br.ufg.dlog.relatorios.RelMovMesVeiculo;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
	/*public interface IMovimento{
	Long getCont();
	int getmes_s();
	}*/
	@Query(value = "select max(cd_saida1)maxid from t_saida1", nativeQuery = true)
	Long maximoIdMovimento();
	
	@Query(value = "select * from t_saida1 where data_s is null and data_est>=(select now()-interval'4 hours') and data_est<=(select now()+interval'18 hours')and fk_motorista=?1", nativeQuery = true)
	List<Movimento> verificaSaida(Long mot);
	@Query(value = "select count(cd_saida1) from t_saida1 where t_saida1.data_est>=(select now()-interval'24 hours') and data_est<=(select now()+interval'24 hours') and data_s is null and v_s is  null  and fk_motorista=?1", nativeQuery = true)
	Long contaSaida(Long motorista);
	@Query(value = "select min(cd_saida1) from t_saida1 where t_saida1.data_est>=(select now()-interval'24 hours') and data_est<=(select now()+interval'24 hours') and data_s is null and v_s is  null  and fk_motorista=?1", nativeQuery = true)
	Long menorIdsaida(Long mot);
	
	@Query(value = "select * from t_saida1 where cd_saida1=?1" , nativeQuery = true)
	Movimento selecionaMovimentoPorId(Long id);
	
	@Query(value = "select max(km_r) from t_saida1 where fk_veiculos like ?1", nativeQuery = true)
	Double maximoKm(String veiculo);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE t_saida1 set data_s=now(), v_s='V',dias=(select date_part('DAY',CURRENT_TIMESTAMP)),\r\n"
			+ "mes_s=(select date_part('MONTH',CURRENT_TIMESTAMP)),ano_s=(select date_part('YEAR',CURRENT_TIMESTAMP)),\r\n"
			+ "km_s=?1\r\n"
			+ "where v_s is null\r\n"
			+ "and cd_saida1=?2", nativeQuery = true)
	void updateIniciaMov( Double km,Long cd_saida);
	
	@Query(value = "select * from t_saida1 where data_s is not null and v_s is not null and v_r is null and data_r is null and fk_motorista=?1", nativeQuery = true)
	Movimento verificaRetorno(Long mot);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE t_saida1 set data_r=now(), v_r='V',km_r=?1, km_ro=?2\r\n"
			+ "			where v_r is null\r\n"
			+ "			and cd_saida1=?3", nativeQuery = true)
	void updateFinalizaMov(Double km, Double kmro, Long cd_saida);
	
	@Query(value = "select count(cd_saida1)cont from t_saida1 where data_s>=?1 and data_s<=?2", nativeQuery = true)
	Long relatorioGeralPorPeriodo(Date inicial, Date Final);  
	
	@Query(value = "select count(cd_saida1)cont from t_saida1, t_veiculos\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			group by mes_s,t_veiculos.placa_atual\r\n"
			+ "			order by mes_s,t_veiculos.placa_atual", nativeQuery = true)
	Long[] relatorioMesVeiculoCont (Date incial, Date Final);
	
	@Query(value = "select mes_s mes from t_saida1, t_veiculos\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			group by mes_s,t_veiculos.placa_atual\r\n"
			+ "			order by mes_s,t_veiculos.placa_atual", nativeQuery = true)
	Long[] relatorioMesVeiculoMes (Date incial, Date Final);
	
	@Query(value = "select t_veiculos.placa_atual from t_saida1, t_veiculos\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			group by mes_s,t_veiculos.placa_atual\r\n"
			+ "			order by mes_s,t_veiculos.placa_atual", nativeQuery = true)
	String[] relatorioMesVeiculoPlaca (Date incial, Date Final);
	
	@Query(value = "select sum(t_saida1.km_ro) from t_saida1, t_veiculos\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			group by mes_s,t_veiculos.placa_atual\r\n"
			+ "			order by mes_s,t_veiculos.placa_atual", nativeQuery = true)
	Double[] relatorioMesVeiculoKm (Date incial, Date Final);
	
	@Query(value = "select count(DISTINCT t_saida1.fk_veiculos)cont from t_saida1\r\n"
			+ "			where data_s>=?1 and data_s<=?2", nativeQuery = true)
	Long contaVeiculosPorPeriodo (Date incial, Date Final);
	
	@Query(value = "select count(cd_saida1)cont from t_saida1, t_veiculos,t_especie\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "			group by mes_s,t_especie.especie\r\n"
			+ "			order by mes_s,t_especie.especie", nativeQuery = true)
	Long[] relatorioMesEspecieCont(Date incial, Date Final);
	
	@Query(value = "select count(distinct t_veiculos.especie)cont from t_saida1, t_veiculos,t_especie\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio", nativeQuery = true)
	Long contaEspeciePorPeriodo(Date incial, Date Final);
	
	@Query(value = "select mes_s mes from t_saida1, t_veiculos,t_especie\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "			group by mes_s,t_especie.especie\r\n"
			+ "			order by mes_s,t_especie.especie", nativeQuery = true)
	Long[] relatorioMesEspecieMes(Date incial, Date Final);
	
	@Query(value = "select t_especie.especie from t_saida1, t_veiculos,t_especie\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "			group by mes_s,t_especie.especie\r\n"
			+ "			order by mes_s,t_especie.especie", nativeQuery = true)
	String[] relatorioMesEspecieEspecie(Date incial, Date Final);
	
	@Query(value = "select sum(t_saida1.km_ro) from t_saida1, t_veiculos,t_especie\r\n"
			+ "			where data_s>=?1 and data_s<=?2\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "			group by mes_s,t_especie.especie\r\n"
			+ "			order by mes_s,t_especie.especie", nativeQuery = true)
	Double[] relatorioMesEspecieKm(Date incial, Date Final);
	
	@Query(value = "select mes_s from t_saida1 where data_s>=?1 and data_s<=?2  group by ano_s, mes_s order by ano_s, mes_s", nativeQuery = true)
    Long[]  mostraMesPorPeriodo(Date inicio, Date fim);
	
	@Query(value = "select count(cd_saida1) as cont from t_saida1 where data_s>=?1 and data_s<=?2  group by ano_s, mes_s order by ano_s, mes_s ", nativeQuery = true)
	Long[] contaMovimentoPorPeriodo(Date inicio, Date fim);
	

	
	@Query(value = "select count(cd_saida1)cont, mes_s mes, t_funcionarios.nm_pessoa_func from t_saida1, t_funcionarios\r\n"
			+ "where data_s>=?1 and data_s<=?2\r\n"
			+ "and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "group by mes_s,t_funcionarios.nm_pessoa_func\r\n"
			+ "order by mes_s,t_funcionarios.nm_pessoa_func", nativeQuery = true)
	List<RelMovMesMotorista> relatorioMesMotoristas(Date incial, Date Final);
	
	@Query(value = "select count(cd_saida1)cont from t_saida1, t_requisicao\r\n"
			+ "		where data_s>=?1  and data_s<=?2\r\n"
			+ "		and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "		group by mes_s,t_requisicao.requisicao\r\n"
			+ "		order by mes_s,t_requisicao.requisicao ", nativeQuery = true)
	List<Long> relatorioMesRequisicaoCont(Date incial, Date Final);
	
	@Query(value = "select  mes_s mes from t_saida1, t_requisicao\r\n"
			+ "		where data_s>=?1  and data_s<=?2\r\n"
			+ "		and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "		group by mes_s,t_requisicao.requisicao\r\n"
			+ "		order by mes_s,t_requisicao.requisicao  ", nativeQuery = true)
	List<Long> relatorioMesRequisicaoMes(Date incial, Date Final);
	
	@Query(value = "select   t_requisicao.requisicao from t_saida1, t_requisicao\r\n"
			+ "		where data_s>=?1  and data_s<=?2\r\n"
			+ "		and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "		group by mes_s,t_requisicao.requisicao\r\n"
			+ "		order by mes_s,t_requisicao.requisicao  ", nativeQuery = true)
	List<String> relatorioMesRequisicaoReq(Date incial, Date Final);
	
    @Query(value = "select cast(count(cd_saida1)as varchar(5))as cont, cast(mes_s as varchar(2))as mes from t_saida1 where data_s>=?1 and data_s<=?2  group by ano_s, mes_s order by ano_s, mes_s", nativeQuery = true)
    List<String> pesquisaConvertendoStringMesCont(Date inicio, Date fim);

    @Query(value = "select cast(mes_s as varchar)mes  from t_saida1, orgao\r\n"
    		+ "						where t_saida1.data_s>=?1 and t_saida1.data_s<=?2 and t_saida1.fk_uni=orgao.cd_orgao\r\n"
    		+ "						group by mes_s, orgao.sg_orgao\r\n"
    		+ "						order by mes_s, orgao.sg_orgao ", nativeQuery = true)
    ArrayList<String> pesquisaMovOrgaosMes(Date inicio, Date fim);
    
    @Query(value = "select sg_orgao from t_saida1, orgao where t_saida1.data_s>=?1 and t_saida1.data_s<=?2 and t_saida1.fk_uni=orgao.cd_orgao\r\n"
    		+ "						group by mes_s, orgao.sg_orgao\r\n"
    		+ "						order by mes_s, orgao.sg_orgao ", nativeQuery = true)
    ArrayList<String> pesquisaMovOrgaosOrgaos(Date inicio, Date fim); 
    
    @Query(value = "select count(DISTINCT fk_uni) cont from t_saida1\r\n"
    		+ "			where t_saida1.data_s>=?1 and t_saida1.data_s<=?2", nativeQuery = true)
    Long contaUnidadesAtendidas(Date ini, Date fim);
    
    @Query(value = "select cast(count(t_saida1.cd_saida1)as varchar) cont from t_saida1, orgao\r\n"
    		+ "			where t_saida1.data_s>=?1 and t_saida1.data_s<=?2 and t_saida1.fk_uni=orgao.cd_orgao\r\n"
    		+ "			group by mes_s, orgao.sg_orgao\r\n"
    		+ "			order by mes_s, orgao.sg_orgao ", nativeQuery = true)
    ArrayList<String> pesquisaMovOrgaosCont(Date inicio, Date fim); 
    
    @Query(value = "select count(t_saida1.cd_saida1)cont from t_saida1,t_evento\r\n"
    		+ "where (t_saida1.evento_s>0 or t_saida1.evento_s is not null)\r\n"
    		+ "and t_saida1.evento_s=t_evento.codigo\r\n"
    		+ "and t_saida1.data_s>=?1\r\n"
    		+ "and t_saida1.data_s<=?2\r\n"
    		+ "group by t_evento.evento,t_saida1.mes_s\r\n"
    		+ "order by t_saida1.mes_s,t_evento.evento", nativeQuery = true)
    Long[] contaEventos(Date ini, Date fim);
    
    @Query(value = "select t_evento.evento from t_saida1,t_evento\r\n"
    		+ "where (t_saida1.evento_s>0 or t_saida1.evento_s is not null)\r\n"
    		+ "and t_saida1.evento_s=t_evento.codigo\r\n"
    		+ "and t_saida1.data_s>=?1\r\n"
    		+ "and t_saida1.data_s<=?2\r\n"
    		+ "group by t_evento.evento,t_saida1.mes_s\r\n"
    		+ "order by t_saida1.mes_s,t_evento.evento", nativeQuery = true)
    ArrayList<String> listaEventos(Date ini, Date fim);
    
    @Query(value = "select t_saida1.mes_s from t_saida1,t_evento\r\n"
    		+ "where (t_saida1.evento_s>0 or t_saida1.evento_s is not null)\r\n"
    		+ "and t_saida1.evento_s=t_evento.codigo\r\n"
    		+ "and t_saida1.data_s>=?1\r\n"
    		+ "and t_saida1.data_s<=?2\r\n"
    		+ "group by t_evento.evento,t_saida1.mes_s\r\n"
    		+ "order by t_saida1.mes_s,t_evento.evento", nativeQuery = true)
    Long[] listaMesesEventos(Date ini, Date fim);
    
    @Query(value = "select count(distinct t_saida1.evento_s) from t_saida1\r\n"
    		+ "    		where (t_saida1.evento_s>0 or t_saida1.evento_s is not null)\r\n"
    		+ "    		and t_saida1.data_s>='01/01/2022'\r\n"
    		+ "    		and t_saida1.data_s<='31/03/2022'", nativeQuery = true)
    Long QtdEventosNoPeriodo(Date ini, Date fim);
	
	
	@Query(value = "select max(cd_saida1)\r\n"
			+ "from t_saida1 where data_est is not null and data_s is not null and data_r is not null", nativeQuery = true)
	Long MaximoIdMovConcluido();
	@Query(value = "SELECT * from t_saida1 where t_saida1.cd_saida1=?1", nativeQuery = true)
	Collection<Movimento> movimentoPorId(Long id);

}
