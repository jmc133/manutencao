package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	@Query(value = "SELECT cd_pessoa_func, nm_pessoa_func, cargo_f, pri_nome, matricula_f,banco, agencia, conta, cpf,\r\n"
			+ "identidade, cnh, categoria, vencimento, senha, pis, endereco, salario, lotacao, ativo, cep, ddd,\r\n"
			+ "telefone, ddd1, celular, a_nivel, abastecer, bairro, e_mail, servidor_publico, cidade, uf\r\n"
			+ "	FROM public.t_funcionarios where nm_pessoa_func like %?1% ", nativeQuery = true)
	List<Pessoa>pesquisaPorNome(String nm_pessoa_func);
	@Query(value = "SELECT * FROM public.t_funcionarios where cd_pessoa_func=?1", nativeQuery = true)
	Pessoa pesquisaId(Long cd_pessoa_func);
	
	@Query(value = "select * from t_funcionarios where ativo='S' ORDER by nm_pessoa_func", nativeQuery = true)
	Collection<Pessoa> listaPessoasAtivas();
	
	@Modifying
	@Query(value = "update t_funcionarios set senha=?1 where cd_pessoa_func=?2", nativeQuery = true)
	void alteraSenhaNoPessoa(String senha, Long pessoa);
	
	@Query(value = "select MAX(cd_pessoa_func) from t_funcionarios", nativeQuery = true)
	Long maximoCodigoPessoa();
	
	@Query(value = "select * from t_funcionarios\n"
			+ "			where ativo like'%S%' \n"
			+ "			and t_funcionarios.cd_pessoa_func not in (select fk_motorista from t_saida1\n"
			+ "			where data_est>=current_date and data_r is null and data_est<=current_date+1)\n"
			+ "			and lotacao=1\n"
			+ "			and cargo_f like '%MOTO%'\n"
			+ "			order by nm_pessoa_func", nativeQuery = true)
	Collection<Pessoa> motoristasNaoEscalados();
	
	default Page<Pessoa> findPessoaByNamePage(String nome,Pageable pageable){
		Pessoa pessoa = new Pessoa();
		pessoa.setNm_pessoa_func(nome);
		//Estamos configurando para pesquisar por partes no banco de dados
		ExampleMatcher exemploMacher = ExampleMatcher.matchingAny()
				.withMatcher("nm_pessoa_func",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		//Uno o objeto com o valor e configuracao para consulta
		Example<Pessoa> exemplo = Example.of(pessoa, exemploMacher);
		Page<Pessoa> pessoas = findAll(exemplo, pageable);
		return pessoas;
	}

}
