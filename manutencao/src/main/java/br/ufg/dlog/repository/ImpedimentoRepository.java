package br.ufg.dlog.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Impedimento;

@Transactional
@Repository
public interface ImpedimentoRepository extends JpaRepository<Impedimento, Integer> {

}
