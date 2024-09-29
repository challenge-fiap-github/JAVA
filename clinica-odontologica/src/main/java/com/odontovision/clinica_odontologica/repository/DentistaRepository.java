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
public interface DentistaRepository extends JpaRepository<Dentista, Long>, GenericRepository<Dentista, Long> {

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
}
