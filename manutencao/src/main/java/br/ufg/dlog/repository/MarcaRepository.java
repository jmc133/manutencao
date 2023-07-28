package br.ufg.dlog.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Marca;

@Transactional
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
	@Query(value = "select max(cod_marca) from t_marca", nativeQuery = true)
	Long maximoCodigoMarca();

}
