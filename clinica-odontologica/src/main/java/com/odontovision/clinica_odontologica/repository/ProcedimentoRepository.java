package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositório da entidade {@link Procedimento}.
 * <p>
 * Permite realizar operações CRUD e consultas personalizadas na tabela de procedimentos.
 * </p>
 */
@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

    /**
     * Lista procedimentos com risco de fraude.
     * <p>
     * Busca todos os procedimentos que estão marcados como suspeitos de fraude (riscoFraude = true).
     * </p>
     *
     * @return Lista de procedimentos com risco de fraude.
     */
    @Query("SELECT p FROM Procedimento p WHERE p.riscoFraude = true")
    List<Procedimento> listarFraudes();

    /**
     * Consulta personalizada para listar procedimentos realizados antes de uma data específica.
     * <p>
     * Esta consulta busca todos os procedimentos realizados antes de uma data informada.
     * </p>
     *
     * @param dataProcedimento Data limite para busca.
     * @return Lista de procedimentos realizados antes da data especificada.
     */
    @Query("SELECT p FROM Procedimento p WHERE p.dataProcedimento < :dataProcedimento")
    List<Procedimento> findProcedimentosBeforeDate(LocalDate dataProcedimento);

    /**
     * Consulta personalizada para buscar procedimentos por tipo.
     * <p>
     * Retorna todos os procedimentos de um tipo específico, como "Limpeza" ou "Extração".
     * </p>
     *
     * @param tipoProcedimento Tipo do procedimento.
     * @return Lista de procedimentos de um tipo específico.
     */
    List<Procedimento> findByTipoProcedimento(String tipoProcedimento);

    /**
     * Consulta personalizada para buscar procedimentos com custo superior a um valor.
     * <p>
     * Retorna todos os procedimentos cujo custo seja superior ao valor especificado.
     * </p>
     *
     * @param custo Valor mínimo do custo do procedimento.
     * @return Lista de procedimentos com custo superior ao valor especificado.
     */
    List<Procedimento> findByCustoGreaterThan(Double custo);
}
