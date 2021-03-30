package com.ptit.demo.service.impl;

import com.ptit.demo.service.FullnameService;
import com.ptit.demo.domain.Fullname;
import com.ptit.demo.repository.FullnameRepository;
import com.ptit.demo.service.dto.FullnameDTO;
import com.ptit.demo.service.mapper.FullnameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Fullname}.
 */
@Service
@Transactional
public class FullnameServiceImpl implements FullnameService {

    private final Logger log = LoggerFactory.getLogger(FullnameServiceImpl.class);

    private final FullnameRepository fullnameRepository;

    private final FullnameMapper fullnameMapper;

    public FullnameServiceImpl(FullnameRepository fullnameRepository, FullnameMapper fullnameMapper) {
        this.fullnameRepository = fullnameRepository;
        this.fullnameMapper = fullnameMapper;
    }

    @Override
    public FullnameDTO save(FullnameDTO fullnameDTO) {
        log.debug("Request to save Fullname : {}", fullnameDTO);
        Fullname fullname = fullnameMapper.toEntity(fullnameDTO);
        fullname = fullnameRepository.save(fullname);
        return fullnameMapper.toDto(fullname);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FullnameDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fullnames");
        return fullnameRepository.findAll(pageable)
            .map(fullnameMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FullnameDTO> findOne(Long id) {
        log.debug("Request to get Fullname : {}", id);
        return fullnameRepository.findById(id)
            .map(fullnameMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fullname : {}", id);
        fullnameRepository.deleteById(id);
    }
}
