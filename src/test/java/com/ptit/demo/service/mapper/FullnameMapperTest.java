package com.ptit.demo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FullnameMapperTest {

    private FullnameMapper fullnameMapper;

    @BeforeEach
    public void setUp() {
        fullnameMapper = new FullnameMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fullnameMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fullnameMapper.fromId(null)).isNull();
    }
}
