package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.Escala;

@Repository
public interface EscalaRepository extends JpaRepository<Escala,Long>{
	@Query(value = "SELECT id, condutor, data_final, data_inicio, destino, diarias, unidade, usuario, veiculo, condutor2\r\n"
			+ "	FROM public.escala\r\n"
			+ "	WHERE data_inicio>=?", nativeQuery = true)
	Collection<Escala>  verificaEscalas(Date inicio);
	
	@Query(value = "SELECT * FROM public.escala ORDER by data_inicio", nativeQuery = true)
	Collection<Escala> mostraTodaEscala();

}
