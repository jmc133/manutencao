package br.ufg.dlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.Sexo;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, String> {

}
