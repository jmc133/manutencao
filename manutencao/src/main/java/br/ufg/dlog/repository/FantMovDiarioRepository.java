package br.ufg.dlog.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantMovDiario;

@Repository
public interface FantMovDiarioRepository extends JpaRepository<FantMovDiario, Long> {
	
	@Query(value = "select t_movimentodiario.codigo, t_requisicao.requisicao,orgao.sg_orgao,t_movimentodiario.destino,\r\n"
			+ "			t_movimentodiario.solicitante,t_funcionarios.pri_nome\r\n"
			+ "			from t_movimentodiario, t_requisicao,orgao,t_funcionarios\r\n"
			+ "			where t_movimentodiario.requisicao=t_requisicao.cod_req\r\n"
			+ "			and t_movimentodiario.unidade=orgao.cd_orgao\r\n"
			+ "			and t_movimentodiario.motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "			order by t_funcionarios.pri_nome", nativeQuery = true)
	Collection<FantMovDiario> verMovimentoDiario();

}
