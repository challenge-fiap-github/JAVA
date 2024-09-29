package com.odontovision.clinica_odontologica.exception;

/**
 * Exceção personalizada que indica que um procedimento não foi encontrado.
 * <p>
 * Esta exceção é lançada quando uma operação de busca por um procedimento específico
 * não encontra nenhum registro correspondente ao ID fornecido.
 * </p>
 */
public class ProcedimentoNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do procedimento não encontrado.
     *
     * @param id O ID do procedimento que não foi encontrado.
     */
    public ProcedimentoNotFoundException(Long id) {
        super("Procedimento não encontrado com o ID: " + id);
    }
}
