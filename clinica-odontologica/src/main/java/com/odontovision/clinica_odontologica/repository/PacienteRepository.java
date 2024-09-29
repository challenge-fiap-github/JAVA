package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório da entidade {@link Paciente}.
 * <p>
 * Esta interface permite realizar operações CRUD e consultas personalizadas na tabela de pacientes.
 * </p>
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, GenericRepository<Paciente, Long> {

    /**
     * Consulta personalizada para buscar pacientes pelo nome.
     * <p>
     * Utiliza Spring JPA Query Methods.
     * </p>
     *
     * @param nome Nome do paciente ou parte do nome.
     * @return Lista de pacientes cujo nome contém o valor especificado.
     */
    List<Paciente> findByNomeContaining(String nome);

    /**
     * Consulta personalizada usando JPQL para listar pacientes com idade maior que uma idade específica.
     *
     * @param idade Idade mínima dos pacientes.
     * @return Lista de pacientes cuja idade é maior que o valor informado.
     */
    @Query("SELECT p FROM Paciente p WHERE p.idade > :idade")
    List<Paciente> findPacientesOlderThan(Integer idade);

    /**
     * Consulta personalizada para buscar pacientes por plano odontológico.
     * <p>
     * Utiliza Spring JPA Query Methods.
     * </p>
     *
     * @param planoOdontologico Nome do plano odontológico.
     * @return Lista de pacientes que possuem o plano odontológico especificado.
     */
    List<Paciente> findByPlanoOdontologico(String planoOdontologico);
}
