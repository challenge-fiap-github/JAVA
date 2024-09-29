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

    /**
     * Construtor com injeção de dependência.
     *
     * @param procedimentoRepository Repositório de procedimentos.
     * @param pacienteRepository     Repositório de pacientes.
     * @param dentistaRepository     Repositório de dentistas.
     */
    public ProcedimentoService(ProcedimentoRepository procedimentoRepository,
                               PacienteRepository pacienteRepository,
                               DentistaRepository dentistaRepository) {
        this.procedimentoRepository = procedimentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
    }

    /**
     * Salva um novo procedimento.
     *
     * @param procedimentoDTO Dados do procedimento a ser salvo.
     * @return {@link ProcedimentoDTO} do procedimento salvo.
     */
    public ProcedimentoDTO salvarProcedimento(ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = convertToEntity(procedimentoDTO);
        Procedimento salvo = procedimentoRepository.save(procedimento);
        return convertToDTO(salvo);
    }

    /**
     * Lista todos os procedimentos.
     *
     * @return Lista de {@link ProcedimentoDTO}.
     */
    public List<ProcedimentoDTO> listarTodos() {
        return procedimentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um procedimento por ID.
     *
     * @param id ID do procedimento.
     * @return {@link Optional} contendo o {@link ProcedimentoDTO}, se encontrado.
     */
    public Optional<ProcedimentoDTO> buscarPorId(Long id) {
        return procedimentoRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Atualiza um procedimento existente.
     *
     * @param id               ID do procedimento a ser atualizado.
     * @param procedimentoDTO Dados atualizados do procedimento.
     * @return {@link Optional} contendo o {@link ProcedimentoDTO} atualizado, se encontrado.
     */
    public Optional<ProcedimentoDTO> atualizarProcedimento(Long id, ProcedimentoDTO procedimentoDTO) {
        return procedimentoRepository.findById(id)
                .map(procedimento -> {
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

                    Procedimento atualizado = procedimentoRepository.save(procedimento);
                    return convertToDTO(atualizado);
                });
    }

    /**
     * Deleta um procedimento pelo ID.
     *
     * @param id ID do procedimento a ser deletado.
     */
    public void deletarProcedimento(Long id) {
        if (procedimentoRepository.existsById(id)) {
            procedimentoRepository.deleteById(id);
        } else {
            throw new ProcedimentoNotFoundException(id);
        }
    }

    /**
     * Lista procedimentos com suspeita de fraude.
     *
     * @return Lista de {@link ProcedimentoDTO} com risco de fraude.
     */
    public List<ProcedimentoDTO> listarFraudes() {
        return procedimentoRepository.listarFraudes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converte um {@link Procedimento} para um {@link ProcedimentoDTO}.
     *
     * @param procedimento Entidade a ser convertida.
     * @return Objeto {@link ProcedimentoDTO}.
     */
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

    /**
     * Converte um {@link ProcedimentoDTO} para uma entidade {@link Procedimento}.
     *
     * @param procedimentoDTO Objeto DTO a ser convertido.
     * @return Entidade {@link Procedimento}.
     */
    private Procedimento convertToEntity(ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = new Procedimento();
        procedimento.setId(procedimentoDTO.getId());
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

        return procedimento;
    }
}
