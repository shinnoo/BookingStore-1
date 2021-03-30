package com.ptit.demo.service.mapper;


import com.ptit.demo.domain.*;
import com.ptit.demo.service.dto.CartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CartMapper extends EntityMapper<CartDTO, Cart> {



    default Cart fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setId(id);
        return cart;
    }
}
