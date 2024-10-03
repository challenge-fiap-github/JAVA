package com.odontovision.clinica_odontologica.service;

import com.odontovision.clinica_odontologica.dto.ProcedimentoDTO;
import com.odontovision.clinica_odontologica.exception.ProcedimentoNotFoundException;
import com.odontovision.clinica_odontologica.exception.PacienteNotFoundException;
import com.odontovision.clinica_odontologica.exception.DentistaNotFoundException;
import com.odontovision.clinica_odontologica.model.Procedimento;
import com.odontovision.clinica_odontologica.model.Paciente;
import com.odontovision.clinica_odontologica.model.Dentista;
import com.odontovision.clinica_odontologica.repository.ProcedimentoRepository;
import com.odontovision.clinica_odontologica.repository.PacienteRepository;
import com.odontovision.clinica_odontologica.repository.DentistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócios relacionada a {@link Procedimento}.
 */
@Service
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;

    public ProcedimentoService(ProcedimentoRepository procedimentoRepository,
                               PacienteRepository pacienteRepository,
                               DentistaRepository dentistaRepository) {
        this.procedimentoRepository = procedimentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
    }

    public ProcedimentoDTO salvarProcedimento(ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = convertToEntity(procedimentoDTO);
        Procedimento salvo = procedimentoRepository.save(procedimento);
        return convertToDTO(salvo);
    }

    public List<ProcedimentoDTO> listarTodos() {
        return procedimentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProcedimentoDTO> buscarPorId(Long id) {
        return procedimentoRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<ProcedimentoDTO> atualizarProcedimento(Long id, ProcedimentoDTO procedimentoDTO) {
        return procedimentoRepository.findById(id)
                .map(procedimento -> {
                    atualizarProcedimentoComDTO(procedimento, procedimentoDTO);
                    Procedimento atualizado = procedimentoRepository.save(procedimento);
                    return convertToDTO(atualizado);
                });
    }

    public void deletarProcedimento(Long id) {
        if (procedimentoRepository.existsById(id)) {
            procedimentoRepository.deleteById(id);
        } else {
            throw new ProcedimentoNotFoundException(id);
        }
    }

    public boolean existeProcedimento(Long id) {
        return procedimentoRepository.existsById(id);
    }

    public List<ProcedimentoDTO> listarFraudes() {
        return procedimentoRepository.listarFraudes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProcedimentoDTO convertToDTO(Procedimento procedimento) {
        ProcedimentoDTO dto = new ProcedimentoDTO();
        dto.setId(procedimento.getId());
        dto.setTipoProcedimento(procedimento.getTipoProcedimento());
        dto.setDataProcedimento(procedimento.getDataProcedimento());
        dto.setCusto(procedimento.getCusto());
        dto.setRiscoFraude(procedimento.isRiscoFraude());
        dto.setPacienteId(procedimento.getPaciente() != null ? procedimento.getPaciente().getId() : null);
        dto.setDentistaId(procedimento.getDentista() != null ? procedimento.getDentista().getId() : null);
        return dto;
    }

    private Procedimento convertToEntity(ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = new Procedimento();
        atualizarProcedimentoComDTO(procedimento, procedimentoDTO);
        return procedimento;
    }

    // Novo método auxiliar para atualizar o Procedimento com os dados do DTO
    private void atualizarProcedimentoComDTO(Procedimento procedimento, ProcedimentoDTO procedimentoDTO) {
        procedimento.setTipoProcedimento(procedimentoDTO.getTipoProcedimento());
        procedimento.setDataProcedimento(procedimentoDTO.getDataProcedimento());
        procedimento.setCusto(procedimentoDTO.getCusto());
        procedimento.setRiscoFraude(procedimentoDTO.getRiscoFraude());

        if (procedimentoDTO.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(procedimentoDTO.getPacienteId())
                    .orElseThrow(() -> new PacienteNotFoundException(procedimentoDTO.getPacienteId()));
            procedimento.setPaciente(paciente);
        }

        if (procedimentoDTO.getDentistaId() != null) {
            Dentista dentista = dentistaRepository.findById(procedimentoDTO.getDentistaId())
                    .orElseThrow(() -> new DentistaNotFoundException(procedimentoDTO.getDentistaId()));
            procedimento.setDentista(dentista);
        }
    }
}
