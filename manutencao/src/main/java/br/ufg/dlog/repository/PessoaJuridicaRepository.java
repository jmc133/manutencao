package br.ufg.dlog.repository;


import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.pessoa_juridica;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<pessoa_juridica, Long> {
	@Query(value = "select * from pessoa_juridica where tipo_manutencao=2 and ativo='S'",nativeQuery = true)
	List<pessoa_juridica> fornecedorDePecas();
	
	@Query(value = "select * from pessoa_juridica where tipo_manutencao=1 and ativo='S'",nativeQuery = true)
	List<pessoa_juridica> fornecedorDeServicos();
	
	@Query(value = "select * from pessoa_juridica where tipo_manutencao=3 and ativo='S'",nativeQuery = true)
	List<pessoa_juridica> fornecedorDePecasServicos();
	
	@Query(value= "select max(cd_pessoa_jur) from pessoa_juridica", nativeQuery = true)
	Long maximoIdPessoaJuridica();
	
	@Query(value= "select * from pessoa_juridica where cd_pessoa_jur=?1", nativeQuery = true)
	pessoa_juridica selecionaPessoaJuridicaPorId(Long id);

}
