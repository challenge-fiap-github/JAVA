package com.odontovision.clinica_odontologica.service;

import com.odontovision.clinica_odontologica.dto.SinistroDTO;
import com.odontovision.clinica_odontologica.exception.SinistroNotFoundException;
import com.odontovision.clinica_odontologica.exception.ProcedimentoNotFoundException;
import com.odontovision.clinica_odontologica.exception.DentistaNotFoundException;
import com.odontovision.clinica_odontologica.exception.PacienteNotFoundException;
import com.odontovision.clinica_odontologica.model.Sinistro;
import com.odontovision.clinica_odontologica.model.Procedimento;
import com.odontovision.clinica_odontologica.model.Dentista;
import com.odontovision.clinica_odontologica.model.Paciente;
import com.odontovision.clinica_odontologica.repository.SinistroRepository;
import com.odontovision.clinica_odontologica.repository.ProcedimentoRepository;
import com.odontovision.clinica_odontologica.repository.DentistaRepository;
import com.odontovision.clinica_odontologica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócios relacionada a {@link Sinistro}.
 */
@Service
public class SinistroService {

    private final SinistroRepository sinistroRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final DentistaRepository dentistaRepository;
    private final PacienteRepository pacienteRepository;

    public SinistroService(SinistroRepository sinistroRepository,
                           ProcedimentoRepository procedimentoRepository,
                           DentistaRepository dentistaRepository,
                           PacienteRepository pacienteRepository) {
        this.sinistroRepository = sinistroRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.dentistaRepository = dentistaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<SinistroDTO> listarTodos() {
        return sinistroRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SinistroDTO salvarSinistro(SinistroDTO sinistroDTO) {
        Sinistro sinistro = convertToEntity(sinistroDTO);
        Sinistro salvo = sinistroRepository.save(sinistro);
        return convertToDTO(salvo);
    }

    public Optional<SinistroDTO> buscarPorId(Long id) {
        return sinistroRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<SinistroDTO> atualizarSinistro(Long id, SinistroDTO sinistroDTO) {
        return sinistroRepository.findById(id)
                .map(sinistro -> {
                    sinistro.setDataSinistro(sinistroDTO.getDataSinistro());
                    sinistro.setRiscoFraude(sinistroDTO.getRiscoFraude());
                    sinistro.setTipoSinistro(sinistroDTO.getTipoSinistro());
                    sinistro.setDescricao(sinistroDTO.getDescricao());

                    sinistro.setProcedimento(buscarProcedimento(sinistroDTO.getProcedimentoId()));
                    sinistro.setDentista(buscarDentista(sinistroDTO.getDentistaId()));
                    sinistro.setPaciente(buscarPaciente(sinistroDTO.getPacienteId()));

                    Sinistro atualizado = sinistroRepository.save(sinistro);
                    return convertToDTO(atualizado);
                });
    }

    public void deletarSinistro(Long id) {
        if (sinistroRepository.existsById(id)) {
            sinistroRepository.deleteById(id);
        } else {
            throw new SinistroNotFoundException(id);
        }
    }

    private SinistroDTO convertToDTO(Sinistro sinistro) {
        SinistroDTO dto = new SinistroDTO();
        dto.setId(sinistro.getId());
        dto.setTipoSinistro(sinistro.getTipoSinistro());
        dto.setDescricao(sinistro.getDescricao());
        dto.setDataSinistro(sinistro.getDataSinistro());
        dto.setProcedimentoId(sinistro.getProcedimento() != null ? sinistro.getProcedimento().getId() : null);
        dto.setDentistaId(sinistro.getDentista() != null ? sinistro.getDentista().getId() : null);
        dto.setPacienteId(sinistro.getPaciente() != null ? sinistro.getPaciente().getId() : null);
        dto.setRiscoFraude(sinistro.isRiscoFraude());
        return dto;
    }

    private Sinistro convertToEntity(SinistroDTO sinistroDTO) {
        Sinistro sinistro = new Sinistro();
        sinistro.setId(sinistroDTO.getId());
        sinistro.setTipoSinistro(sinistroDTO.getTipoSinistro());
        sinistro.setDescricao(sinistroDTO.getDescricao());
        sinistro.setDataSinistro(sinistroDTO.getDataSinistro());
        sinistro.setRiscoFraude(sinistroDTO.getRiscoFraude());

        sinistro.setProcedimento(buscarProcedimento(sinistroDTO.getProcedimentoId()));
        sinistro.setDentista(buscarDentista(sinistroDTO.getDentistaId()));
        sinistro.setPaciente(buscarPaciente(sinistroDTO.getPacienteId()));

        return sinistro;
    }

    private Procedimento buscarProcedimento(Long id) {
        return id != null ? procedimentoRepository.findById(id)
                .orElseThrow(() -> new ProcedimentoNotFoundException(id)) : null;
    }

    private Dentista buscarDentista(Long id) {
        return id != null ? dentistaRepository.findById(id)
                .orElseThrow(() -> new DentistaNotFoundException(id)) : null;
    }

    private Paciente buscarPaciente(Long id) {
        return id != null ? pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException(id)) : null;
    }
}
