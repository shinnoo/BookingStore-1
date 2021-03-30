package com.ptit.demo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ptit.demo.web.rest.TestUtil;

public class FullnameDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FullnameDTO.class);
        FullnameDTO fullnameDTO1 = new FullnameDTO();
        fullnameDTO1.setId(1L);
        FullnameDTO fullnameDTO2 = new FullnameDTO();
        assertThat(fullnameDTO1).isNotEqualTo(fullnameDTO2);
        fullnameDTO2.setId(fullnameDTO1.getId());
        assertThat(fullnameDTO1).isEqualTo(fullnameDTO2);
        fullnameDTO2.setId(2L);
        assertThat(fullnameDTO1).isNotEqualTo(fullnameDTO2);
        fullnameDTO1.setId(null);
        assertThat(fullnameDTO1).isNotEqualTo(fullnameDTO2);
    }
}
