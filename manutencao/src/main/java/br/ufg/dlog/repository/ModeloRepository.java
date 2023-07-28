package br.ufg.dlog.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Modelo;

@Transactional
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
	@Query(value = "select max(cod_mod) from t_modelo", nativeQuery = true)
	Long maximoCodigoModelo();

}
