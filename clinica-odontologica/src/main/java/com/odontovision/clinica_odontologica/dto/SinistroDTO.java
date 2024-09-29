package com.odontovision.clinica_odontologica.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para a entidade Sinistro.
 * <p>
 * Esta classe é usada para transferir dados entre as camadas da aplicação,
 * representando as informações de um sinistro registrado.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SinistroDTO {

    /**
     * Identificador único do sinistro.
     */
    private Long id;

    /**
     * Data em que o sinistro ocorreu.
     * <p>
     * Este campo é obrigatório.
     * </p>
     */
    @NotNull(message = "A data do sinistro é obrigatória.")
    private LocalDate dataSinistro;

    /**
     * Identificador do procedimento relacionado ao sinistro.
     * <p>
     * Este campo é obrigatório.
     * </p>
     */
    @NotNull(message = "O ID do procedimento é obrigatório.")
    private Long procedimentoId;

    /**
     * Identificador do dentista relacionado ao sinistro.
     * <p>
     * Este campo é obrigatório.
     * </p>
     */
    @NotNull(message = "O ID do dentista é obrigatório.")
    private Long dentistaId;

    /**
     * Identificador do paciente relacionado ao sinistro.
     * <p>
     * Este campo é obrigatório.
     * </p>
     */
    @NotNull(message = "O ID do paciente é obrigatório.")
    private Long pacienteId;

    /**
     * Indica se o sinistro é suspeito de fraude.
     */
    private Boolean riscoFraude;
}
