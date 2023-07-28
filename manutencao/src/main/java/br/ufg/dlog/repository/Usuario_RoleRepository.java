package br.ufg.dlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.usuarios_role;

@Repository
public interface Usuario_RoleRepository extends JpaRepository<usuarios_role, Long> {

}
