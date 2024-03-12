package com.example.cartservice.Services;

import com.example.cartservice.Models.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Cart getSingleCart(Long id);
    List<Cart> getCartByDate(String startDate, String endDate);
    Cart addCart(Cart cart);
    List<Cart> getCartByUser(String userId);

    Cart updateCart(Long id,Cart cart);

    String deleteCart(Long id);

}
