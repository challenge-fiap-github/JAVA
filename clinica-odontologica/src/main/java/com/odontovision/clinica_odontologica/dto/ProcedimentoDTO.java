package com.odontovision.clinica_odontologica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Classe DTO (Data Transfer Object) para a entidade Procedimento.
 * <p>
 * Esta classe é usada para transferir dados entre as camadas da aplicação,
 * representando as informações de um procedimento realizado.
 * Utiliza Lombok e Bean Validation.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedimentoDTO {

    /**
     * Identificador único do procedimento.
     * Será gerado automaticamente na criação (POST).
     */
    private Long id;

    /**
     * Tipo do procedimento realizado.
     * <p>
     * Exemplo: "Limpeza", "Extração", "Canal", etc.
     * Este campo é obrigatório e deve ter entre 3 e 100 caracteres.
     * </p>
     */
    @NotBlank(message = "O tipo de procedimento não pode ser nulo ou vazio.")
    @Size(min = 3, max = 100, message = "O tipo de procedimento deve ter entre 3 e 100 caracteres.")
    private String tipoProcedimento;

    /**
     * Data em que o procedimento foi realizado.
     * <p>
     * Deve ser uma data no passado ou presente.
     * </p>
     */
    @NotNull(message = "A data do procedimento não pode ser nula.")
    @PastOrPresent(message = "A data do procedimento deve ser no passado ou presente.")
    private LocalDate dataProcedimento;

    /**
     * Custo do procedimento em moeda local.
     * <p>
     * Este campo é obrigatório e deve ser maior que zero.
     * </p>
     */
    @NotNull(message = "O custo do procedimento não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O custo deve ser maior que 0.")
    private Double custo;

    /**
     * Indica se o procedimento é suspeito de fraude.
     * <p>
     * Este campo é obrigatório.
     * </p>
     */
    @NotNull(message = "O campo de risco de fraude não pode ser nulo.")
    private Boolean riscoFraude;

    /**
     * Identificador do paciente associado ao procedimento.
     * <p>
     * Este campo é obrigatório.
     * </p>
     */
    @NotNull(message = "O ID do paciente não pode ser nulo.")
    private Long pacienteId;

    /**
     * Identificador do dentista que realizou o procedimento.
     * <p>
     * Este campo é obrigatório.
     * </p>
     */
    @NotNull(message = "O ID do dentista não pode ser nulo.")
    private Long dentistaId;
}
