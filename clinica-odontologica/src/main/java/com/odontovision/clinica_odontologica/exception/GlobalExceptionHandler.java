package com.odontovision.clinica_odontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controlador global de exceções para a aplicação.
 * <p>
 * Captura e trata exceções lançadas em todo o aplicativo, fornecendo respostas HTTP apropriadas.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções do tipo {@link DentistaNotFoundException}.
     *
     * @param ex A exceção capturada.
     * @return {@link ResponseEntity} com mensagem de erro e status HTTP.
     */
    @ExceptionHandler(DentistaNotFoundException.class)
    public ResponseEntity<String> handleDentistaNotFoundException(DentistaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Trata exceções do tipo {@link DiagnosticoNotFoundException}.
     *
     * @param ex A exceção capturada.
     * @return {@link ResponseEntity} com mensagem de erro e status HTTP.
     */
    @ExceptionHandler(DiagnosticoNotFoundException.class)
    public ResponseEntity<String> handleDiagnosticoNotFoundException(DiagnosticoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Trata exceções do tipo {@link PacienteNotFoundException}.
     *
     * @param ex A exceção capturada.
     * @return {@link ResponseEntity} com mensagem de erro e status HTTP.
     */
    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<String> handlePacienteNotFoundException(PacienteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Trata exceções do tipo {@link ProcedimentoNotFoundException}.
     *
     * @param ex A exceção capturada.
     * @return {@link ResponseEntity} com mensagem de erro e status HTTP.
     */
    @ExceptionHandler(ProcedimentoNotFoundException.class)
    public ResponseEntity<String> handleProcedimentoNotFoundException(ProcedimentoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Trata exceções do tipo {@link SinistroNotFoundException}.
     *
     * @param ex A exceção capturada.
     * @return {@link ResponseEntity} com mensagem de erro e status HTTP.
     */
    @ExceptionHandler(SinistroNotFoundException.class)
    public ResponseEntity<String> handleSinistroNotFoundException(SinistroNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
