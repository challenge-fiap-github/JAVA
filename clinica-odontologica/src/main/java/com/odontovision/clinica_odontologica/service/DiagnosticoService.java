package com.odontovision.clinica_odontologica.service;

import com.odontovision.clinica_odontologica.dto.DiagnosticoDTO;
import com.odontovision.clinica_odontologica.exception.DiagnosticoNotFoundException;
import com.odontovision.clinica_odontologica.exception.PacienteNotFoundException;
import com.odontovision.clinica_odontologica.model.Diagnostico;
import com.odontovision.clinica_odontologica.model.Paciente;
import com.odontovision.clinica_odontologica.repository.DiagnosticoRepository;
import com.odontovision.clinica_odontologica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;
    private final PacienteRepository pacienteRepository;

    public DiagnosticoService(DiagnosticoRepository diagnosticoRepository, PacienteRepository pacienteRepository) {
        this.diagnosticoRepository = diagnosticoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Listar todos os diagnósticos.
     *
     * @return Lista de DiagnosticoDTO.
     */
    public List<DiagnosticoDTO> listarTodos() {
        return diagnosticoRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();  // Usando toList() diretamente
    }

    /**
     * Salvar um novo diagnóstico.
     *
     * @param diagnosticoDTO Dados do diagnóstico a ser salvo.
     * @return DiagnosticoDTO salvo.
     */
    public DiagnosticoDTO salvarDiagnostico(DiagnosticoDTO diagnosticoDTO) {
        // Usar o método convertToEntity para criar a entidade Diagnostico
        Diagnostico diagnostico = convertToEntity(diagnosticoDTO);

        // Salvar o diagnóstico no banco de dados
        Diagnostico salvo = diagnosticoRepository.save(diagnostico);

        // Retornar o DTO com os dados do diagnóstico salvo
        return convertToDTO(salvo);
    }

    /**
     * Buscar um diagnóstico por ID.
     *
     * @param id ID do diagnóstico.
     * @return Optional contendo o DiagnosticoDTO, se encontrado.
     */
    public Optional<DiagnosticoDTO> buscarPorId(Long id) {
        return diagnosticoRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Atualizar um diagnóstico existente.
     *
     * @param id              ID do diagnóstico a ser atualizado.
     * @param diagnosticoDTO Dados atualizados do diagnóstico.
     * @return Optional contendo o DiagnosticoDTO atualizado, se encontrado.
     */
    public Optional<DiagnosticoDTO> atualizarDiagnostico(Long id, DiagnosticoDTO diagnosticoDTO) {
        return diagnosticoRepository.findById(id)
                .map(diagnostico -> {
                    diagnostico.setTipoDiagnostico(diagnosticoDTO.getTipoDiagnostico());
                    diagnostico.setDataDiagnostico(diagnosticoDTO.getDataDiagnostico());
                    diagnostico.setDescricao(diagnosticoDTO.getDescricao());

                    if (diagnosticoDTO.getPacienteId() != null) {
                        Paciente paciente = buscarPacientePorId(diagnosticoDTO.getPacienteId());
                        diagnostico.setPaciente(paciente);
                    }

                    Diagnostico atualizado = diagnosticoRepository.save(diagnostico);
                    return convertToDTO(atualizado);
                });
    }

    /**
     * Deletar um diagnóstico por ID.
     *
     * @param id ID do diagnóstico a ser deletado.
     */
    public void deletarDiagnostico(Long id) {
        if (diagnosticoRepository.existsById(id)) {
            diagnosticoRepository.deleteById(id);
        } else {
            throw new DiagnosticoNotFoundException(id);
        }
    }

    /**
     * Converter Diagnostico para DiagnosticoDTO.
     *
     * @param diagnostico Entidade Diagnostico.
     * @return DiagnosticoDTO.
     */
    private DiagnosticoDTO convertToDTO(Diagnostico diagnostico) {
        return new DiagnosticoDTO(
                diagnostico.getId(),
                diagnostico.getTipoDiagnostico(),
                diagnostico.getDataDiagnostico(),
                diagnostico.getDescricao(),
                diagnostico.getPaciente() != null ? diagnostico.getPaciente().getId() : null
        );
    }

    /**
     * Converter DiagnosticoDTO para Diagnostico.
     *
     * @param diagnosticoDTO DiagnosticoDTO.
     * @return Entidade Diagnostico.
     */
    private Diagnostico convertToEntity(DiagnosticoDTO diagnosticoDTO) {
        // Buscar o paciente pelo ID fornecido no DTO
        Paciente paciente = pacienteRepository.findById(diagnosticoDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException(diagnosticoDTO.getPacienteId()));

        // Criar uma nova entidade Diagnostico e associar os dados
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(diagnosticoDTO.getId());
        diagnostico.setTipoDiagnostico(diagnosticoDTO.getTipoDiagnostico());
        diagnostico.setDataDiagnostico(diagnosticoDTO.getDataDiagnostico());
        diagnostico.setDescricao(diagnosticoDTO.getDescricao());
        diagnostico.setPaciente(paciente);  // Associar o paciente ao diagnóstico

        return diagnostico;
    }

    /**
     * Método auxiliar para buscar o Paciente por ID.
     *
     * @param pacienteId ID do paciente.
     * @return Paciente encontrado.
     * @throws PacienteNotFoundException Se o paciente não for encontrado.
     */
    private Paciente buscarPacientePorId(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException(pacienteId));
    }
}