package br.ufg.dlog.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufg.dlog.classes.FantMovimentoDataString;

public interface FantMovimentoDataStringRepository extends JpaRepository<FantMovimentoDataString, Long> {
	
	@Query(value = "select cd_saida1,data_est,to_char(data_s, 'dd/MM/yyyy HH24:mm')data_s,to_char(data_r,'dd/MM/yyyy HH24:mm')data_r,evento,km_s,km_r,km_ro,placa_atual,requisicao,nm_pessoa_func,\r\n"
			+ "s_destino,sg_orgao,s_solicitante,s_executado\r\n"
			+ "from t_saida1, orgao , t_veiculos,t_requisicao,t_funcionarios,t_evento\r\n"
			+ "where t_saida1.fk_uni=orgao.cd_orgao and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "and t_saida1.fk_req=t_requisicao.cod_req and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "and t_saida1.data_s>=?1 and t_saida1.data_s<=?2\r\n"
			+ "and t_saida1.evento_s=t_evento.codigo and orgao.sg_orgao=?3\r\n"
			+ "order by t_saida1.data_s", nativeQuery = true)
	List<FantMovimentoDataString> relacaoPorUnidades(Date ini, Date fim, String sg_orgao);
	
	@Query(value = "select cd_saida1,data_est,to_char(data_s, 'dd/MM/yyyy HH24:mm')data_s,to_char(data_r,'dd/MM/yyyy HH24:mm')data_r,evento,km_s,km_r,km_ro,placa_atual,requisicao,nm_pessoa_func,\r\n"
			+ "			s_destino,sg_orgao,s_solicitante,s_executado\r\n"
			+ "			from t_saida1, orgao , t_veiculos,t_requisicao,t_funcionarios,t_evento\r\n"
			+ "			where t_saida1.fk_uni=orgao.cd_orgao and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_saida1.fk_req=t_requisicao.cod_req and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "			and t_saida1.data_s>=?1 and t_saida1.data_s<=?2\r\n"
			+ "			and t_saida1.evento_s=t_evento.codigo \r\n"
			+ "			and t_requisicao.requisicao=?3\r\n"
			+ "			order by t_saida1.data_s", nativeQuery = true)
	List<FantMovimentoDataString> relacaoPorRequisicoes(Date ini, Date fim, String requisicao);
	
	@Query(value = "select cd_saida1,data_est,to_char(data_s, 'dd/MM/yyyy HH24:mm')data_s,to_char(data_r,'dd/MM/yyyy HH24:mm')data_r,evento,km_s,km_r,km_ro,placa_atual,requisicao,nm_pessoa_func,\r\n"
			+ "			s_destino,sg_orgao,s_solicitante,s_executado\r\n"
			+ "			from t_saida1, orgao , t_veiculos,t_requisicao,t_funcionarios,t_evento,t_especie\r\n"
			+ "			where t_saida1.fk_uni=orgao.cd_orgao and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			and t_saida1.fk_req=t_requisicao.cod_req and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "			and t_saida1.data_s>=?1 and t_saida1.data_s<=?2 and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "			and t_saida1.evento_s=t_evento.codigo and t_especie.especie like ?3\r\n"
			+ "			order by t_saida1.data_s", nativeQuery = true)
	List<FantMovimentoDataString> relacaoPorEspecies(Date ini, Date fim, String especie);
	
	@Query(value = "select cd_saida1,data_est,to_char(data_s, 'dd/MM/yyyy HH24:mm')data_s,to_char(data_r,'dd/MM/yyyy HH24:mm')data_r,evento,km_s,km_r,km_ro,placa_atual,requisicao,nm_pessoa_func,\r\n"
			+ "		s_destino,sg_orgao,s_solicitante,s_executado\r\n"
			+ "		from t_saida1, orgao , t_veiculos,t_requisicao,t_funcionarios,t_evento,t_especie\r\n"
			+ "		where t_saida1.fk_uni=orgao.cd_orgao and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "		and t_saida1.fk_req=t_requisicao.cod_req and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "		and t_saida1.data_s>=?1 and t_saida1.data_s<=?2 and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "		and t_saida1.evento_s=t_evento.codigo and t_veiculos.placa_atual=?3\r\n"
			+ "		order by t_saida1.data_s", nativeQuery = true)
	List<FantMovimentoDataString> relacaoPorPlacas(Date ini, Date fim, String placa);
	
	@Query(value = "select cd_saida1,data_est,to_char(data_s, 'dd/MM/yyyy HH24:mm')data_s,to_char(data_r,'dd/MM/yyyy HH24:mm')data_r,evento,km_s,km_r,km_ro,placa_atual,requisicao,nm_pessoa_func,\r\n"
			+ "		s_destino,sg_orgao,s_solicitante,s_executado\r\n"
			+ "		from t_saida1, orgao , t_veiculos,t_requisicao,t_funcionarios,t_evento,t_especie\r\n"
			+ "		where t_saida1.fk_uni=orgao.cd_orgao and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "		and t_saida1.fk_req=t_requisicao.cod_req and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "		and t_saida1.data_s>=?1 and t_saida1.data_s<=?2 and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "		and t_saida1.evento_s=t_evento.codigo and t_evento.evento=?3\r\n"
			+ "		order by t_saida1.data_s", nativeQuery = true)
	List<FantMovimentoDataString> relacaoPorEventos(Date ini, Date fim, String evento);
	
	@Query(value = "select cd_saida1,data_est,to_char(data_s, 'dd/MM/yyyy HH24:mm')data_s,to_char(data_r,'dd/MM/yyyy HH24:mm')data_r,evento,km_s,km_r,km_ro,placa_atual,requisicao,nm_pessoa_func,\r\n"
			+ "		s_destino,sg_orgao,s_solicitante,s_executado\r\n"
			+ "		from t_saida1, orgao , t_veiculos,t_requisicao,t_funcionarios,t_evento,t_especie\r\n"
			+ "		where t_saida1.fk_uni=orgao.cd_orgao and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "		and t_saida1.fk_req=t_requisicao.cod_req and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "		and t_saida1.data_s>=?1 and t_saida1.data_s<=?2 and t_veiculos.especie=t_especie.cd_especie\r\n"
			+ "		and t_saida1.evento_s=t_evento.codigo and t_funcionarios.cargo_f=?3\r\n"
			+ "		order by t_saida1.data_s", nativeQuery = true)
	List<FantMovimentoDataString> relacaoPorCargos(Date ini, Date fim, String cargo);

}
