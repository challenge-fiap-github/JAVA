package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link Paciente}.
 * <p>
 * Esta interface permite realizar operações CRUD e consultas personalizadas na tabela de pacientes.
 * </p>
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
