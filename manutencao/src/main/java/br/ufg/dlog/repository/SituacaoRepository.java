package br.ufg.dlog.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Situacao;

@Transactional
@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, String>{

}
