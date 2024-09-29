package com.odontovision.clinica_odontologica.exception;

/**
 * Exceção personalizada que indica que um dentista não foi encontrado.
 * <p>
 * Esta exceção é lançada quando uma operação de busca por um dentista específico
 * não encontra nenhum registro correspondente ao ID fornecido.
 * É utilizada para sinalizar ao chamador que o recurso solicitado não existe.
 * </p>
 */
public class DentistaNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do dentista não encontrado.
     *
     * @param id O ID do dentista que não foi encontrado.
     */
    public DentistaNotFoundException(Long id) {
        super("Dentista não encontrado com o ID: " + id);
    }
}
