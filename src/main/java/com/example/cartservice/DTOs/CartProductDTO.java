package com.example.cartservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductDTO {
    protected Long productId;
    protected Long quantity;
}
