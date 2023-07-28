package br.ufg.dlog.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Cidade;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
@Query(value = "select * from cidades order by nm_cidade", nativeQuery = true)
Collection<Cidade> cidadesOrdenadasPorNome();
	

}
