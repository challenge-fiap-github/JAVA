package com.odontovision.clinica_odontologica.repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório genérico que define operações CRUD básicas.
 *
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo do identificador da entidade.
 */
public interface GenericRepository<T, ID> {

    /**
     * Salva a entidade fornecida.
     *
     * @param entity a entidade a ser salva.
     * @return a entidade salva.
     */
    T save(T entity);

    /**
     * Busca a entidade pelo ID.
     *
     * @param id o identificador da entidade.
     * @return um Optional contendo a entidade, caso encontrada.
     */
    Optional<T> findById(ID id);

    /**
     * Retorna uma lista de todas as entidades.
     *
     * @return uma lista de todas as entidades.
     */
    List<T> findAll();

    /**
     * Exclui a entidade pelo ID.
     *
     * @param id o identificador da entidade a ser excluída.
     */
    void deleteById(ID id);
}
