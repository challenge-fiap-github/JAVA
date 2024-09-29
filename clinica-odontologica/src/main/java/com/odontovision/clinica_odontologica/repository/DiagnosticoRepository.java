package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositório da entidade {@link Diagnostico}.
 * <p>
 * Esta interface permite realizar operações CRUD e consultas personalizadas na tabela de diagnósticos do banco de dados.
 * </p>
 */
@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long>, GenericRepository<Diagnostico, Long> {

    /**
     * Consulta personalizada para buscar diagnósticos pelo tipo.
     * <p>
     * Utiliza Spring JPA Query Methods.
     * </p>
     *
     * @param tipoDiagnostico Tipo do diagnóstico (ex: "Cárie", "Gengivite").
     * @return Lista de diagnósticos com o tipo especificado.
     */
    List<Diagnostico> findByTipoDiagnosticoContaining(String tipoDiagnostico);

    /**
     * Consulta personalizada usando JPQL para listar diagnósticos realizados após uma data específica.
     *
     * @param dataDiagnostico Data de realização do diagnóstico.
     * @return Lista de diagnósticos realizados após a data informada.
     */
    @Query("SELECT d FROM Diagnostico d WHERE d.dataDiagnostico > :dataDiagnostico")
    List<Diagnostico> findDiagnosticosAfterDate(LocalDate dataDiagnostico);

}
