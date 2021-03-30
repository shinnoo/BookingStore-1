package com.ptit.demo.service.mapper;


import com.ptit.demo.domain.*;
import com.ptit.demo.service.dto.FullnameDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fullname} and its DTO {@link FullnameDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FullnameMapper extends EntityMapper<FullnameDTO, Fullname> {



    default Fullname fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fullname fullname = new Fullname();
        fullname.setId(id);
        return fullname;
    }
}
