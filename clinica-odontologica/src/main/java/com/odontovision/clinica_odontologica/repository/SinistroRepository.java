package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositório da entidade {@link Sinistro}.
 * <p>
 * Permite realizar operações CRUD e consultas personalizadas na tabela de sinistros.
 * </p>
 */
@Repository
public interface SinistroRepository extends JpaRepository<Sinistro, Long> {

    /**
     * Consulta personalizada para listar sinistros que ocorreram antes de uma data específica.
     *
     * @param dataSinistro Data limite para busca.
     * @return Lista de sinistros ocorridos antes da data especificada.
     */
    @Query("SELECT s FROM Sinistro s WHERE s.dataSinistro < :dataSinistro")
    List<Sinistro> findSinistrosBeforeDate(LocalDate dataSinistro);

    /**
     * Consulta personalizada para listar sinistros suspeitos de fraude.
     *
     * @return Lista de sinistros suspeitos de fraude.
     */
    @Query("SELECT s FROM Sinistro s WHERE s.riscoFraude = true")
    List<Sinistro> listarSinistrosComFraude();

    /**
     * Consulta personalizada para listar sinistros por ID do dentista.
     *
     * @param dentistaId ID do dentista.
     * @return Lista de sinistros associados ao dentista especificado.
     */
    List<Sinistro> findByDentistaId(Long dentistaId);

    /**
     * Consulta personalizada para listar sinistros por ID do paciente.
     *
     * @param pacienteId ID do paciente.
     * @return Lista de sinistros associados ao paciente especificado.
     */
    List<Sinistro> findByPacienteId(Long pacienteId);
}
