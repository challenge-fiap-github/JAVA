package com.odontovision.clinica_odontologica.exception;

/**
 * Exceção personalizada que indica que um paciente não foi encontrado.
 * <p>
 * Esta exceção é lançada quando uma operação de busca por um paciente específico
 * não encontra nenhum registro correspondente ao ID fornecido.
 * </p>
 */
public class PacienteNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do paciente não encontrado.
     *
     * @param id O ID do paciente que não foi encontrado.
     */
    public PacienteNotFoundException(Long id) {
        super("Paciente não encontrado com o ID: " + id);
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
    public PacienteNotFoundException(String message) {
        super(message);
    }
}
