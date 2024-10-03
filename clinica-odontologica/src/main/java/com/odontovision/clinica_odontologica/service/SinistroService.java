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

    /**
     * Construtor com injeção de dependência.
     *
     * @param sinistroRepository     Repositório de sinistros.
     * @param procedimentoRepository Repositório de procedimentos.
     * @param dentistaRepository     Repositório de dentistas.
     * @param pacienteRepository     Repositório de pacientes.
     */
    public SinistroService(SinistroRepository sinistroRepository,
                           ProcedimentoRepository procedimentoRepository,
                           DentistaRepository dentistaRepository,
                           PacienteRepository pacienteRepository) {
        this.sinistroRepository = sinistroRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.dentistaRepository = dentistaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Lista todos os sinistros.
     *
     * @return Lista de {@link SinistroDTO}.
     */
    public List<SinistroDTO> listarTodos() {
        return sinistroRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Salva um novo sinistro.
     *
     * @param sinistroDTO Dados do sinistro a ser salvo.
     * @return {@link SinistroDTO} do sinistro salvo.
     */
    public SinistroDTO salvarSinistro(SinistroDTO sinistroDTO) {
        Sinistro sinistro = convertToEntity(sinistroDTO);
        Sinistro salvo = sinistroRepository.save(sinistro);
        return convertToDTO(salvo);
    }

    /**
     * Busca um sinistro por ID.
     *
     * @param id ID do sinistro.
     * @return {@link Optional} contendo o {@link SinistroDTO}, se encontrado.
     */
    public Optional<SinistroDTO> buscarPorId(Long id) {
        return sinistroRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Atualiza um sinistro existente.
     *
     * @param id          ID do sinistro a ser atualizado.
     * @param sinistroDTO Dados atualizados do sinistro.
     * @return {@link Optional} contendo o {@link SinistroDTO} atualizado, se encontrado.
     */
    public Optional<SinistroDTO> atualizarSinistro(Long id, SinistroDTO sinistroDTO) {
        return sinistroRepository.findById(id)
                .map(sinistro -> {
                    sinistro.setDataSinistro(sinistroDTO.getDataSinistro());
                    sinistro.setRiscoFraude(sinistroDTO.getRiscoFraude());

                    if (sinistroDTO.getProcedimentoId() != null) {
                        Procedimento procedimento = procedimentoRepository.findById(sinistroDTO.getProcedimentoId())
                                .orElseThrow(() -> new ProcedimentoNotFoundException(sinistroDTO.getProcedimentoId()));
                        sinistro.setProcedimento(procedimento);
                    }

                    if (sinistroDTO.getDentistaId() != null) {
                        Dentista dentista = dentistaRepository.findById(sinistroDTO.getDentistaId())
                                .orElseThrow(() -> new DentistaNotFoundException(sinistroDTO.getDentistaId()));
                        sinistro.setDentista(dentista);
                    }

                    if (sinistroDTO.getPacienteId() != null) {
                        Paciente paciente = pacienteRepository.findById(sinistroDTO.getPacienteId())
                                .orElseThrow(() -> new PacienteNotFoundException(sinistroDTO.getPacienteId()));
                        sinistro.setPaciente(paciente);
                    }

                    Sinistro atualizado = sinistroRepository.save(sinistro);
                    return convertToDTO(atualizado);
                });
    }

    /**
     * Deleta um sinistro pelo ID.
     *
     * @param id ID do sinistro a ser deletado.
     */
    public void deletarSinistro(Long id) {
        if (sinistroRepository.existsById(id)) {
            sinistroRepository.deleteById(id);
        } else {
            throw new SinistroNotFoundException(id);
        }
    }

    /**
     * Converte um {@link Sinistro} para um {@link SinistroDTO}.
     *
     * @param sinistro Entidade a ser convertida.
     * @return Objeto {@link SinistroDTO}.
     */
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

    /**
     * Converte um {@link SinistroDTO} para uma entidade {@link Sinistro}.
     *
     * @param sinistroDTO Objeto DTO a ser convertido.
     * @return Entidade {@link Sinistro}.
     */
    private Sinistro convertToEntity(SinistroDTO sinistroDTO) {
        Sinistro sinistro = new Sinistro();
        sinistro.setId(sinistroDTO.getId());
        sinistro.setTipoSinistro(sinistroDTO.getTipoSinistro());
        sinistro.setDescricao(sinistroDTO.getDescricao());
        sinistro.setDataSinistro(sinistroDTO.getDataSinistro());
        sinistro.setRiscoFraude(sinistroDTO.getRiscoFraude());

        if (sinistroDTO.getProcedimentoId() != null) {
            Procedimento procedimento = procedimentoRepository.findById(sinistroDTO.getProcedimentoId())
                    .orElseThrow(() -> new ProcedimentoNotFoundException(sinistroDTO.getProcedimentoId()));
            sinistro.setProcedimento(procedimento);
        }

        if (sinistroDTO.getDentistaId() != null) {
            Dentista dentista = dentistaRepository.findById(sinistroDTO.getDentistaId())
                    .orElseThrow(() -> new DentistaNotFoundException(sinistroDTO.getDentistaId()));
            sinistro.setDentista(dentista);
        }

        if (sinistroDTO.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(sinistroDTO.getPacienteId())
                    .orElseThrow(() -> new PacienteNotFoundException(sinistroDTO.getPacienteId()));
            sinistro.setPaciente(paciente);
        }

        return sinistro;
    }
}
