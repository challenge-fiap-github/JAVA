package com.odontovision.clinica_odontologica.service;

import com.odontovision.clinica_odontologica.dto.PacienteDTO;
import com.odontovision.clinica_odontologica.model.Paciente;
import com.odontovision.clinica_odontologica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        paciente.setAtivo(true);  // Garante que o paciente seja ativo por padrão
        Paciente salvo = pacienteRepository.save(paciente);
        return convertToDTO(salvo);
    }

    /**
     * Lista todos os pacientes ativos.
     *
     * @return Lista de {@link PacienteDTO}.
     */
    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findByAtivoTrue().stream()
                .map(this::convertToDTO)
                .toList(); // Utilizando Stream.toList()
    }

    /**
     * Busca um paciente pelo ID.
     *
     * @param id ID do paciente.
     * @return {@link Optional} contendo o {@link PacienteDTO}, se encontrado.
     */
    public Optional<PacienteDTO> buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .filter(Paciente::getAtivo)  // Verifica se o paciente está ativo
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
                .filter(Paciente::getAtivo)  // Verifica se o paciente está ativo
                .map(paciente -> {
                    paciente.setNome(pacienteDTO.getNome());
                    paciente.setIdade(pacienteDTO.getIdade());
                    paciente.setPlanoOdontologico(pacienteDTO.getPlanoOdontologico());
                    Paciente atualizado = pacienteRepository.save(paciente);
                    return convertToDTO(atualizado);
                });
    }

    /**
     * Desativa um paciente (soft delete).
     *
     * @param id ID do paciente a ser desativado.
     */
    public void desativarPaciente(Long id) {
        pacienteRepository.findById(id)
                .ifPresent(paciente -> {
                    paciente.setAtivo(false);  // Soft delete: desativa o paciente
                    pacienteRepository.save(paciente);
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
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setIdade(pacienteDTO.getIdade());
        paciente.setPlanoOdontologico(pacienteDTO.getPlanoOdontologico());
        paciente.setAtivo(true);  // Define paciente como ativo por padrão
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
        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setIdade(paciente.getIdade());
        pacienteDTO.setPlanoOdontologico(paciente.getPlanoOdontologico());
        pacienteDTO.setAtivo(paciente.getAtivo());
        return pacienteDTO;
    }
}