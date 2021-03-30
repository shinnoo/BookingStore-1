package com.ptit.demo.web.rest;

import com.ptit.demo.service.FullnameService;
import com.ptit.demo.web.rest.errors.BadRequestAlertException;
import com.ptit.demo.service.dto.FullnameDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ptit.demo.domain.Fullname}.
 */
@RestController
@RequestMapping("/api")
public class FullnameResource {

    private final Logger log = LoggerFactory.getLogger(FullnameResource.class);

    private static final String ENTITY_NAME = "fullname";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FullnameService fullnameService;

    public FullnameResource(FullnameService fullnameService) {
        this.fullnameService = fullnameService;
    }

    /**
     * {@code POST  /fullnames} : Create a new fullname.
     *
     * @param fullnameDTO the fullnameDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fullnameDTO, or with status {@code 400 (Bad Request)} if the fullname has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fullnames")
    public ResponseEntity<FullnameDTO> createFullname(@RequestBody FullnameDTO fullnameDTO) throws URISyntaxException {
        log.debug("REST request to save Fullname : {}", fullnameDTO);
        if (fullnameDTO.getId() != null) {
            throw new BadRequestAlertException("A new fullname cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FullnameDTO result = fullnameService.save(fullnameDTO);
        return ResponseEntity.created(new URI("/api/fullnames/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fullnames} : Updates an existing fullname.
     *
     * @param fullnameDTO the fullnameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fullnameDTO,
     * or with status {@code 400 (Bad Request)} if the fullnameDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fullnameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fullnames")
    public ResponseEntity<FullnameDTO> updateFullname(@RequestBody FullnameDTO fullnameDTO) throws URISyntaxException {
        log.debug("REST request to update Fullname : {}", fullnameDTO);
        if (fullnameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FullnameDTO result = fullnameService.save(fullnameDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fullnameDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fullnames} : get all the fullnames.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fullnames in body.
     */
    @GetMapping("/fullnames")
    public ResponseEntity<List<FullnameDTO>> getAllFullnames(Pageable pageable) {
        log.debug("REST request to get a page of Fullnames");
        Page<FullnameDTO> page = fullnameService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fullnames/:id} : get the "id" fullname.
     *
     * @param id the id of the fullnameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fullnameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fullnames/{id}")
    public ResponseEntity<FullnameDTO> getFullname(@PathVariable Long id) {
        log.debug("REST request to get Fullname : {}", id);
        Optional<FullnameDTO> fullnameDTO = fullnameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fullnameDTO);
    }

    /**
     * {@code DELETE  /fullnames/:id} : delete the "id" fullname.
     *
     * @param id the id of the fullnameDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fullnames/{id}")
    public ResponseEntity<Void> deleteFullname(@PathVariable Long id) {
        log.debug("REST request to delete Fullname : {}", id);
        fullnameService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
