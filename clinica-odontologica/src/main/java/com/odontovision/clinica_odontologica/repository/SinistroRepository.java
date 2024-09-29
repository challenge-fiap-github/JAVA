package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link Sinistro}.
 * <p>
 * Permite realizar operações CRUD na tabela de sinistros.
 * </p>
 */
@Repository
public interface SinistroRepository extends JpaRepository<Sinistro, Long> {

}
