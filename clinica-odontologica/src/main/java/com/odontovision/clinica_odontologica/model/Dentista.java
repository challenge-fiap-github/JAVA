package com.odontovision.clinica_odontologica.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

/**
 * Entidade que representa um Dentista na clínica odontológica.
 * <p>
 * Esta classe mapeia a tabela "dentista" no banco de dados e contém
 * as informações dos dentistas que atuam na clínica. Cada dentista
 * pode estar associado a múltiplos procedimentos realizados.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dentista {

    /**
     * Identificador único do dentista.
     * <p>
     * Geração automática do ID utilizando a estratégia de auto incremento.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do dentista.
     * <p>
     * O nome não pode estar em branco e deve conter entre 3 e 100 caracteres.
     * </p>
     */
    @NotBlank(message = "O nome do dentista é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome do dentista deve ter entre 3 e 100 caracteres.")
    private String nome;

    /**
     * Número de licença profissional do dentista (CRO).
     * <p>
     * O número de licença deve ser único e seguir um padrão de formato.
     * Exemplo de formato: "CRO12345".
     * </p>
     */
    @NotBlank(message = "O número da licença é obrigatório.")
    @Pattern(regexp = "^CRO\\d{5}$", message = "O número de licença deve seguir o formato 'CRO12345'.")
    @Column(unique = true)
    private String numeroLicenca;

    /**
     * Lista de procedimentos associados ao dentista.
     * <p>
     * Indica todos os procedimentos realizados pelo dentista na clínica.
     * O relacionamento é unidirecional e mapeado pela propriedade "dentista"
     * na entidade {@link Procedimento}.
     * </p>
     */
    @OneToMany(mappedBy = "dentista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Procedimento> procedimentos;

    /**
     * Status ativo ou inativo do dentista (soft delete).
     * <p>
     * O campo ativo define se o dentista está ativo ou inativo no sistema.
     * A remoção do dentista do sistema não apaga fisicamente os dados,
     * mas o marca como inativo para preservar o histórico.
     * </p>
     */
    @Column(nullable = false)
    private boolean ativo = true;

    /**
     * Método para desativar o dentista.
     * <p>
     * Marca o dentista como inativo no sistema, preservando seu histórico.
     * </p>
     */
    public void desativar() {
        this.ativo = false;
    }

    /**
     * Método para reativar o dentista.
     * <p>
     * Marca o dentista como ativo novamente, caso seja necessário reativar seu perfil.
     * </p>
     */
    public void reativar() {
        this.ativo = true;
    }
}
