package br.ufg.dlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.Sequencia;

@Repository
public interface SequenciaRepository extends JpaRepository<Sequencia,Long> {

}
