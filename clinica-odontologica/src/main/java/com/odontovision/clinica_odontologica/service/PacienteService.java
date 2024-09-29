package com.odontovision.clinica_odontologica.service;

import com.odontovision.clinica_odontologica.dto.PacienteDTO;
import com.odontovision.clinica_odontologica.exception.PacienteNotFoundException;
import com.odontovision.clinica_odontologica.model.Paciente;
import com.odontovision.clinica_odontologica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócios relacionada a {@link Paciente}.
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    /**
     * Construtor com injeção de dependência.
     *
     * @param pacienteRepository Repositório de pacientes.
     */
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Salva um novo paciente.
     *
     * @param pacienteDTO Dados do paciente a ser salvo.
     * @return {@link PacienteDTO} do paciente salvo.
     */
    public PacienteDTO salvarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = convertToEntity(pacienteDTO);
        Paciente salvo = pacienteRepository.save(paciente);
        return convertToDTO(salvo);
    }

    /**
     * Lista todos os pacientes.
     *
     * @return Lista de {@link PacienteDTO}.
     */
    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um paciente pelo ID.
     *
     * @param id ID do paciente.
     * @return {@link Optional} contendo o {@link PacienteDTO}, se encontrado.
     */
    public Optional<PacienteDTO> buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Atualiza um paciente existente.
     *
     * @param id          ID do paciente a ser atualizado.
     * @param pacienteDTO Dados atualizados do paciente.
     * @return {@link Optional} contendo o {@link PacienteDTO} atualizado, se encontrado.
     */
    public Optional<PacienteDTO> atualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNome(pacienteDTO.getNome());
                    paciente.setIdade(pacienteDTO.getIdade());
                    paciente.setPlanoOdontologico(pacienteDTO.getPlanoOdontologico());
                    Paciente atualizado = pacienteRepository.save(paciente);
                    return convertToDTO(atualizado);
                });
    }

    /**
     * Deleta um paciente pelo ID.
     *
     * @param id ID do paciente a ser deletado.
     * @return {@link Optional} vazio se o paciente foi deletado, ou vazio se não encontrado.
     */
    public Optional<Void> deletarPaciente(Long id) {
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    pacienteRepository.delete(paciente);
                    return null;
                });
    }

    /**
     * Converte um {@link PacienteDTO} para uma entidade {@link Paciente}.
     *
     * @param pacienteDTO Objeto DTO a ser convertido.
     * @return Entidade {@link Paciente}.
     */
    private Paciente convertToEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setNome(pacienteDTO.getNome());
        paciente.setIdade(pacienteDTO.getIdade());
        paciente.setPlanoOdontologico(pacienteDTO.getPlanoOdontologico());
        return paciente;
    }

    /**
     * Converte uma entidade {@link Paciente} para um {@link PacienteDTO}.
     *
     * @param paciente Entidade a ser convertida.
     * @return Objeto {@link PacienteDTO}.
     */
    private PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setIdade(paciente.getIdade());
        pacienteDTO.setPlanoOdontologico(paciente.getPlanoOdontologico());
        return pacienteDTO;
    }
}
