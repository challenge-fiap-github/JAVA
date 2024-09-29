package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link Diagnostico}.
 * <p>
 * Esta interface permite realizar operações CRUD e consultas personalizadas na tabela de diagnósticos do banco de dados.
 * </p>
 */
@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long>, GenericRepository<Diagnostico, Long> {

}
