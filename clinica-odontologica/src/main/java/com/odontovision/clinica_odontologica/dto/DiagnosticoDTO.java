package com.odontovision.clinica_odontologica.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para a entidade Diagnóstico.
 * <p>
 * Esta classe é usada para transferir dados entre as camadas da aplicação,
 * representando as informações de um diagnóstico realizado em um paciente.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticoDTO {

    /**
     * Identificador único do diagnóstico.
     * <p>
     * Este campo é opcional, pois será gerado automaticamente na criação de um novo diagnóstico.
     * </p>
     */
    private Long id;

    /**
     * Tipo do diagnóstico realizado.
     * <p>
     * Exemplos: "Cárie", "Gengivite", "Periodontite", etc.
     * Este campo é obrigatório e não pode estar em branco.
     * </p>
     */
    @NotBlank(message = "O tipo de diagnóstico é obrigatório.")
    private String tipoDiagnostico;

    /**
     * Data em que o diagnóstico foi realizado.
     * <p>
     * Este campo é obrigatório e não pode ser nulo.
     * </p>
     */
    @NotNull(message = "A data do diagnóstico é obrigatória.")
    private LocalDate dataDiagnostico;

    /**
     * Descrição detalhada do diagnóstico.
     * <p>
     * Este campo deve conter informações adicionais sobre o diagnóstico,
     * como observações do dentista ou detalhes específicos do tratamento.
     * Este campo é obrigatório e não pode estar em branco.
     * </p>
     */
    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    /**
     * Identificador do paciente associado ao diagnóstico.
     * <p>
     * Relaciona o diagnóstico a um paciente específico.
     * </p>
     */
    @NotNull(message = "O paciente associado ao diagnóstico é obrigatório.")
    private Long pacienteId;
}
