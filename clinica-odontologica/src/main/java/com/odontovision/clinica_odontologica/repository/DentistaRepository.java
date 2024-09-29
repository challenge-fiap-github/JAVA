package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link Dentista}.
 * <p>
 * Esta interface permite realizar operações CRUD (Create, Read, Update, Delete)
 * e consultas personalizadas na tabela de dentistas do banco de dados.
 * </p>
 * <p>
 * Ao estender {@link JpaRepository}, herda vários métodos padrão para manipulação de dados,
 * como salvar, deletar, encontrar por ID, entre outros.
 * </p>
 * <p>
 * Anotada com {@link Repository} para indicar ao Spring que esta interface é um componente
 * de repositório, permitindo a detecção automática e a injeção de dependência.
 * </p>
 */
@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

}
