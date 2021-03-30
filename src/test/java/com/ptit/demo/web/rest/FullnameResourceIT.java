package com.ptit.demo.web.rest;

import com.ptit.demo.BookStoreApp;
import com.ptit.demo.domain.Fullname;
import com.ptit.demo.repository.FullnameRepository;
import com.ptit.demo.service.FullnameService;
import com.ptit.demo.service.dto.FullnameDTO;
import com.ptit.demo.service.mapper.FullnameMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FullnameResource} REST controller.
 */
@SpringBootTest(classes = BookStoreApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FullnameResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    @Autowired
    private FullnameRepository fullnameRepository;

    @Autowired
    private FullnameMapper fullnameMapper;

    @Autowired
    private FullnameService fullnameService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFullnameMockMvc;

    private Fullname fullname;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fullname createEntity(EntityManager em) {
        Fullname fullname = new Fullname()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME);
        return fullname;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fullname createUpdatedEntity(EntityManager em) {
        Fullname fullname = new Fullname()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME);
        return fullname;
    }

    @BeforeEach
    public void initTest() {
        fullname = createEntity(em);
    }

    @Test
    @Transactional
    public void createFullname() throws Exception {
        int databaseSizeBeforeCreate = fullnameRepository.findAll().size();
        // Create the Fullname
        FullnameDTO fullnameDTO = fullnameMapper.toDto(fullname);
        restFullnameMockMvc.perform(post("/api/fullnames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fullnameDTO)))
            .andExpect(status().isCreated());

        // Validate the Fullname in the database
        List<Fullname> fullnameList = fullnameRepository.findAll();
        assertThat(fullnameList).hasSize(databaseSizeBeforeCreate + 1);
        Fullname testFullname = fullnameList.get(fullnameList.size() - 1);
        assertThat(testFullname.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testFullname.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
    }

    @Test
    @Transactional
    public void createFullnameWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fullnameRepository.findAll().size();

        // Create the Fullname with an existing ID
        fullname.setId(1L);
        FullnameDTO fullnameDTO = fullnameMapper.toDto(fullname);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFullnameMockMvc.perform(post("/api/fullnames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fullnameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fullname in the database
        List<Fullname> fullnameList = fullnameRepository.findAll();
        assertThat(fullnameList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFullnames() throws Exception {
        // Initialize the database
        fullnameRepository.saveAndFlush(fullname);

        // Get all the fullnameList
        restFullnameMockMvc.perform(get("/api/fullnames?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fullname.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)));
    }
    
    @Test
    @Transactional
    public void getFullname() throws Exception {
        // Initialize the database
        fullnameRepository.saveAndFlush(fullname);

        // Get the fullname
        restFullnameMockMvc.perform(get("/api/fullnames/{id}", fullname.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fullname.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingFullname() throws Exception {
        // Get the fullname
        restFullnameMockMvc.perform(get("/api/fullnames/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFullname() throws Exception {
        // Initialize the database
        fullnameRepository.saveAndFlush(fullname);

        int databaseSizeBeforeUpdate = fullnameRepository.findAll().size();

        // Update the fullname
        Fullname updatedFullname = fullnameRepository.findById(fullname.getId()).get();
        // Disconnect from session so that the updates on updatedFullname are not directly saved in db
        em.detach(updatedFullname);
        updatedFullname
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME);
        FullnameDTO fullnameDTO = fullnameMapper.toDto(updatedFullname);

        restFullnameMockMvc.perform(put("/api/fullnames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fullnameDTO)))
            .andExpect(status().isOk());

        // Validate the Fullname in the database
        List<Fullname> fullnameList = fullnameRepository.findAll();
        assertThat(fullnameList).hasSize(databaseSizeBeforeUpdate);
        Fullname testFullname = fullnameList.get(fullnameList.size() - 1);
        assertThat(testFullname.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testFullname.getLastName()).isEqualTo(UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFullname() throws Exception {
        int databaseSizeBeforeUpdate = fullnameRepository.findAll().size();

        // Create the Fullname
        FullnameDTO fullnameDTO = fullnameMapper.toDto(fullname);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFullnameMockMvc.perform(put("/api/fullnames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fullnameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fullname in the database
        List<Fullname> fullnameList = fullnameRepository.findAll();
        assertThat(fullnameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFullname() throws Exception {
        // Initialize the database
        fullnameRepository.saveAndFlush(fullname);

        int databaseSizeBeforeDelete = fullnameRepository.findAll().size();

        // Delete the fullname
        restFullnameMockMvc.perform(delete("/api/fullnames/{id}", fullname.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fullname> fullnameList = fullnameRepository.findAll();
        assertThat(fullnameList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
