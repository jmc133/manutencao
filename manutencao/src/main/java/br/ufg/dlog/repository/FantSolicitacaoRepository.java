package br.ufg.dlog.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantSolicitacao;

@Repository
public interface FantSolicitacaoRepository extends JpaRepository<FantSolicitacao, Long> {
	
	@Query(value = "select t_solicitacao.id,t_solicitacao.aceite,t_solicitacao.data_ini,t_solicitacao.data_fim,t_solicitacao.destino,\r\n"
			+ "t_solicitacao.solicitante,orgao.sg_orgao\r\n"
			+ "from t_solicitacao,orgao\r\n"
			+ "where t_solicitacao.unidade=orgao.cd_orgao\r\n"
			+ "and t_solicitacao.data_ini>=current_date", nativeQuery = true)
	Collection<FantSolicitacao> solicitacoesPartindoDeHoje();
	
	@Query(value = "select t_solicitacao.id,t_solicitacao.aceite,t_solicitacao.data_ini,t_solicitacao.data_fim,t_solicitacao.destino,\r\n"
			+ "t_solicitacao.solicitante,orgao.sg_orgao\r\n"
			+ "from t_solicitacao,orgao\r\n"
			+ "where t_solicitacao.unidade=orgao.cd_orgao\r\n"
			+ "and t_solicitacao.data_ini>=current_date\r\n"
			+ "and t_solicitacao.aceite is null", nativeQuery = true)
	Collection<FantSolicitacao> solcitacaoSemAceite();

}
