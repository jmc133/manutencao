package br.ufg.dlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.Tipo_manutencao;

@Repository
public interface Tipo_manutencaoRespository extends JpaRepository<Tipo_manutencao, Long> {

}
