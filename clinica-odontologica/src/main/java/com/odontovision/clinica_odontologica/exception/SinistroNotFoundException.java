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
}
