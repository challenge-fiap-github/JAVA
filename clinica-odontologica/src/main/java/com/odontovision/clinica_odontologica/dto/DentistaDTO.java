package com.odontovision.clinica_odontologica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) para a entidade Dentista.
 * <p>
 * Esta classe é usada para transferir dados entre as camadas da aplicação,
 * representando as informações de um dentista.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DentistaDTO {

    /**
     * Identificador único do dentista.
     */
    private Long id;

    /**
     * Nome completo do dentista.
     * <p>
     * Este campo é obrigatório e deve conter entre 3 e 50 caracteres.
     * </p>
     */
    @NotBlank(message = "O nome do dentista é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    /**
     * Número de licença profissional do dentista.
     * <p>
     * Este campo é obrigatório e não pode estar em branco.
     * </p>
     */
    @NotBlank(message = "O número da licença é obrigatório.")
    private String numeroLicenca;
}
