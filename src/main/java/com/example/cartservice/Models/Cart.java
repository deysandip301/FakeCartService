package com.example.cartservice.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Cart {
    Long id;
    Long userId;
    String date;
    List<CartProduct> products;
}
