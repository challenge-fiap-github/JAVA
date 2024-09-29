package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Dentista;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de teste para o repositório {@link DentistaRepository}.
 * <p>
 * Este teste é responsável por verificar a funcionalidade básica de recuperação
 * de dentistas do banco de dados.
 * </p>
 */
@DataJpaTest
public class DentistaRepositoryTest {

    @Autowired
    private DentistaRepository dentistaRepository;

    /**
     * Teste que verifica se todos os dentistas são retornados corretamente.
     * <p>
     * Este método realiza a seguinte validação:
     * <ul>
     *     <li>Verifica se a lista de dentistas retornada não é nula.</li>
     *     <li>Verifica se a quantidade de dentistas retornados é maior que zero.</li>
     * </ul>
     */
    @Test
    public void shouldReturnAllDentists() {
        List<Dentista> dentistas = dentistaRepository.findAll();

        // Verifica se a lista de dentistas não é nula
        assertThat(dentistas).isNotNull();

        // Verifica se a lista de dentistas contém pelo menos um dentista
        assertThat(dentistas.size()).isGreaterThan(0);
    }
}
