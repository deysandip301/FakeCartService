package com.example.cartservice.DTOs;

import com.example.cartservice.Models.CartProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {
    Long id;
    Long userId;
    String date;
    List<CartProductDTO> products;
}
