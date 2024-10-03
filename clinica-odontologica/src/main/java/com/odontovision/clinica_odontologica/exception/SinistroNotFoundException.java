package com.odontovision.clinica_odontologica.exception;

/**
 * Exceção personalizada que indica que um sinistro não foi encontrado.
 * <p>
 * Esta exceção é lançada quando uma operação de busca por um sinistro específico
 * não encontra nenhum registro correspondente ao ID fornecido.
 * </p>
 */
public class SinistroNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do sinistro não encontrado.
     *
     * @param id O ID do sinistro que não foi encontrado.
     */
    public SinistroNotFoundException(Long id) {
        super("Sinistro não encontrado com o ID: " + id);
    }

    /**
     * Construtor da exceção que aceita uma mensagem personalizada.
     * <p>
     * Esse construtor permite criar uma mensagem de erro personalizada para cenários específicos.
     * </p>
     *
     * @param message Mensagem personalizada descrevendo o erro.
     */
    public SinistroNotFoundException(String message) {
        super(message);
    }
}
