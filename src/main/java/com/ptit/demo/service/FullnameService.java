package com.ptit.demo.service;

import com.ptit.demo.service.dto.FullnameDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ptit.demo.domain.Fullname}.
 */
public interface FullnameService {

    /**
     * Save a fullname.
     *
     * @param fullnameDTO the entity to save.
     * @return the persisted entity.
     */
    FullnameDTO save(FullnameDTO fullnameDTO);

    /**
     * Get all the fullnames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FullnameDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fullname.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FullnameDTO> findOne(Long id);

    /**
     * Delete the "id" fullname.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
