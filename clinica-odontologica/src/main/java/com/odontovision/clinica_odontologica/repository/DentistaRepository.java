package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link Dentista}.
 * <p>
 * Esta interface permite realizar operações CRUD e consultas personalizadas na tabela de dentistas do banco de dados.
 * </p>
 */
@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long>, GenericRepository<Dentista, Long> {

}
