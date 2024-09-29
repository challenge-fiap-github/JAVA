package com.odontovision.clinica_odontologica.service;

import com.odontovision.clinica_odontologica.dto.DentistaDTO;
import com.odontovision.clinica_odontologica.model.Dentista;
import com.odontovision.clinica_odontologica.repository.DentistaRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;

    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    /**
     * Salvar um novo dentista.
     *
     * @param dentistaDTO Dados do dentista.
     * @return Dentista salvo.
     */
    public DentistaDTO salvarDentista(@Valid DentistaDTO dentistaDTO) {
        Dentista dentista = convertToEntity(dentistaDTO);
        Dentista salvo = dentistaRepository.save(dentista);
        return convertToDTO(salvo);
    }

    /**
     * Listar todos os dentistas.
     *
     * @return Lista de DentistaDTO.
     */
    public List<DentistaDTO> listarTodos() {
        return dentistaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Buscar dentista por ID.
     *
     * @param id ID do dentista.
     * @return Optional contendo o DentistaDTO, se encontrado.
     */
    public Optional<DentistaDTO> buscarPorId(Long id) {
        return dentistaRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Atualizar um dentista existente.
     *
     * @param id           ID do dentista.
     * @param dentistaDTO Dados atualizados.
     * @return Optional contendo o DentistaDTO atualizado, se encontrado.
     */
    public Optional<DentistaDTO> atualizarDentista(Long id, @Valid DentistaDTO dentistaDTO) {
        return dentistaRepository.findById(id)
                .map(dentista -> {
                    dentista.setNome(dentistaDTO.getNome());
                    dentista.setNumeroLicenca(dentistaDTO.getNumeroLicenca());
                    Dentista atualizado = dentistaRepository.save(dentista);
                    return convertToDTO(atualizado);
                });
    }

    /**
     * Deletar um dentista por ID.
     *
     * @param id ID do dentista.
     */
    public void deletarDentista(Long id) {
        if (dentistaRepository.existsById(id)) {
            dentistaRepository.deleteById(id);
        } else {
            throw new com.odontovision.clinica_odontologica.exception.DentistaNotFoundException(id);
        }
    }

    /**
     * Converter entidade para DTO.
     *
     * @param dentista Entidade Dentista.
     * @return DentistaDTO.
     */
    private DentistaDTO convertToDTO(Dentista dentista) {
        return new DentistaDTO(dentista.getId(), dentista.getNome(), dentista.getNumeroLicenca());
    }

    /**
     * Converter DTO para entidade.
     *
     * @param dentistaDTO DentistaDTO.
     * @return Entidade Dentista.
     */
    private Dentista convertToEntity(DentistaDTO dentistaDTO) {
        Dentista dentista = new Dentista();
        dentista.setNome(dentistaDTO.getNome());
        dentista.setNumeroLicenca(dentistaDTO.getNumeroLicenca());
        return dentista;
    }
}
