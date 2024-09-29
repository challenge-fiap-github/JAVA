package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositório da entidade {@link Procedimento}.
 * <p>
 * Permite realizar operações CRUD e consultas personalizadas na tabela de procedimentos.
 * </p>
 */
@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>, GenericRepository<Procedimento, Long> {

    /**
     * Lista procedimentos com risco de fraude.
     *
     * @return Lista de procedimentos com risco de fraude.
     */
    @Query("SELECT p FROM Procedimento p WHERE p.riscoFraude = true")
    List<Procedimento> listarFraudes();
}
