package com.odontovision.clinica_odontologica;

import com.odontovision.clinica_odontologica.model.Diagnostico;
import com.odontovision.clinica_odontologica.repository.DiagnosticoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de teste para o repositório {@link DiagnosticoRepository}.
 * <p>
 * Esta classe contém testes automatizados para verificar o comportamento
 * correto do repositório de {@link Diagnostico}, especialmente o método {@link DiagnosticoRepository#findAll()}
 * que retorna todos os diagnósticos registrados no banco de dados.
 * </p>
 * <p>
 * Os testes utilizam a anotação {@link DataJpaTest} para configurar o contexto
 * de persistência, e os métodos da classe são executados em um banco de dados
 * em memória configurado automaticamente pelo Spring.
 * </p>
 */
@DataJpaTest
public class DiagnosticoRepositoryTest {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    /**
     * Teste que verifica se o método {@link DiagnosticoRepository#findAll()} retorna
     * corretamente todos os registros de diagnósticos.
     * <p>
     * Este teste verifica que a lista de diagnósticos não é nula e contém pelo menos
     * um registro.
     * </p>
     */
    @Test
    public void shouldReturnAllDiagnosticos() {
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
        assertThat(diagnosticos).isNotNull();
        assertThat(diagnosticos.size()).isGreaterThan(0);
    }
}
