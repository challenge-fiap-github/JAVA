package com.odontovision.clinica_odontologica.exception;

/**
 * Exceção personalizada que indica que um diagnóstico não foi encontrado.
 * <p>
 * Esta exceção é lançada quando uma operação de busca por um diagnóstico específico
 * não encontra nenhum registro correspondente ao ID fornecido.
 * É utilizada para sinalizar ao chamador que o recurso solicitado não existe.
 * </p>
 */
public class DiagnosticoNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do diagnóstico não encontrado.
     *
     * @param id O ID do diagnóstico que não foi encontrado.
     */
    public DiagnosticoNotFoundException(Long id) {
        super("Diagnóstico não encontrado com o ID: " + id);
    }

    /**
     * Construtor que aceita uma mensagem de erro personalizada.
     * <p>
     * Este construtor permite passar uma mensagem personalizada para o erro,
     * oferecendo flexibilidade para diferentes cenários.
     * </p>
     *
     * @param message Mensagem personalizada descrevendo o erro.
     */
    public DiagnosticoNotFoundException(String message) {
        super(message);
    }
}
