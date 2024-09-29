package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Procedimento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de teste para o repositório {@link ProcedimentoRepository}.
 * <p>
 * Esta classe contém testes automatizados para verificar o comportamento
 * correto do repositório de {@link Procedimento}, especialmente a consulta
 * personalizada {@link ProcedimentoRepository#listarFraudes()} que retorna
 * procedimentos com suspeita de fraude.
 * </p>
 * <p>
 * Os testes utilizam a anotação {@link DataJpaTest} para configurar o contexto
 * de persistência, e os métodos da classe são executados em um banco de dados
 * em memória configurado automaticamente pelo Spring.
 * </p>
 */
@DataJpaTest
public class ProcedimentoRepositoryTest {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    /**
     * Teste que verifica se o método {@link ProcedimentoRepository#listarFraudes()} retorna
     * corretamente os procedimentos que são suspeitos de fraude.
     * <p>
     * Este teste verifica que a lista de procedimentos fraudulentos não é nula e que contém
     * pelo menos um procedimento.
     * </p>
     */
    @Test
    public void shouldReturnFraudulentProcedures() {
        List<Procedimento> procedimentosFraude = procedimentoRepository.listarFraudes();
        assertThat(procedimentosFraude).isNotNull();
        assertThat(procedimentosFraude.size()).isGreaterThan(0);
    }
}
