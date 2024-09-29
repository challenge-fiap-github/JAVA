package com.odontovision.clinica_odontologica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe DTO para transporte de dados de Paciente entre as camadas da aplicação.
 * Utiliza Bean Validation para validação dos dados.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    private Long id;

    @NotNull(message = "O nome não pode ser nulo.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @Min(value = 0, message = "A idade deve ser maior ou igual a 0.")
    private int idade;

    @NotNull(message = "O plano odontológico não pode ser nulo.")
    @Size(min = 3, max = 50, message = "O plano odontológico deve ter entre 3 e 50 caracteres.")
    private String plpackage com.odontovision.clinica_odontologica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

    /**
     * Data Transfer Object (DTO) para a entidade Paciente.
     * <p>
     * Esta classe é usada para transferir dados entre as camadas da aplicação,
     * representando as informações de um paciente.
     * </p>
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
        @NotNull(message = "O nome não pode ser nulo.")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
        private String nome;

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
         * Este campo é obrigatório e deve conter entre 3 e 50 caracteres.
         * </p>
         */
        @NotNull(message = "O plano odontológico não pode ser nulo.")
        @Size(min = 3, max = 50, message = "O plano odontológico deve ter entre 3 e 50 caracteres.")
        private String planoOdontologico;
    }
    anoOdontologico;
}
