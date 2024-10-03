package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório da entidade {@link Dentista}.
 * <p>
 * Esta interface permite realizar operações CRUD e consultas personalizadas na tabela de dentistas do banco de dados.
 * </p>
 */
@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

    /**
     * Consulta personalizada para buscar dentistas pelo nome.
     * <p>
     * Utiliza Spring JPA Query Methods.
     * </p>
     *
     * @param nome Nome do dentista.
     * @return Lista de dentistas cujo nome contenha o valor informado.
     */
    List<Dentista> findByNomeContaining(String nome);

    /**
     * Consulta personalizada usando JPQL para listar dentistas por número de licença.
     *
     * @param numeroLicenca Número de licença do dentista.
     * @return Lista de dentistas com o número de licença especificado.
     */
    @Query("SELECT d FROM Dentista d WHERE d.numeroLicenca = :numeroLicenca")
    List<Dentista> findDentistasByNumeroLicenca(String numeroLicenca);

    /**
     * Consulta para listar apenas dentistas ativos.
     * <p>
     * Esta consulta utiliza JPQL para retornar apenas os dentistas que estão ativos (soft delete).
     * </p>
     *
     * @return Lista de dentistas ativos.
     */
    @Query("SELECT d FROM Dentista d WHERE d.ativo = true")
    List<Dentista> findAllAtivos();
}
