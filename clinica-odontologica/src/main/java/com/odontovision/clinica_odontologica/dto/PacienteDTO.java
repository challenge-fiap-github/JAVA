package com.odontovision.clinica_odontologica.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Vou usar futuramente em campos de validação
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Classe DTO para transporte de dados de Paciente entre as camadas da aplicação.
 * Utiliza Bean Validation para validação dos dados.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    /**
     * Identificador único do paciente.
     */
    private Long id;

    /**
     * Nome completo do paciente.
     * <p>
     * Este campo é obrigatório e deve conter entre 2 e 100 caracteres.
     * </p>
     */
    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    /**
     * CPF do paciente.
     * <p>
     * Este campo é obrigatório e deve ser único, além de seguir o formato de CPF (###.###.###-##).
     * </p>
     */
    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato ###.###.###-##")
    private String cpf;

    /**
     * Idade do paciente.
     * <p>
     * Deve ser maior ou igual a 0.
     * </p>
     */
    @Min(value = 0, message = "A idade deve ser maior ou igual a 0.")
    private int idade;

    /**
     * Plano odontológico do paciente.
     * <p>
     * Este campo é opcional e deve conter entre 3 e 50 caracteres, se fornecido.
     * </p>
     */
    @Size(min = 3, max = 50, message = "O plano odontológico deve ter entre 3 e 50 caracteres.")
    private String planoOdontologico;

    /**
     * Status ativo do paciente.
     * <p>
     * Indica se o paciente está ativo ou inativo no sistema (soft delete).
     * </p>
     */
    private Boolean ativo;
}
