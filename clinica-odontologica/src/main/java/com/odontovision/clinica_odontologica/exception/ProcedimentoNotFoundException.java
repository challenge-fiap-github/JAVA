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

    /**
     * Construtor da exceção que aceita uma mensagem personalizada.
     * <p>
     * Este construtor permite criar uma mensagem de erro personalizada para
     * diferentes cenários de exceção.
     * </p>
     *
     * @param message Mensagem personalizada descrevendo o erro.
     */
    public ProcedimentoNotFoundException(String message) {
        super(message);
    }
}
