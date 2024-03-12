package com.example.cartservice.Controllers;

import com.example.cartservice.Models.Cart;
import com.example.cartservice.Services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public Cart getSingleCart(@PathVariable("id") Long id) {
        return cartService.getSingleCart(id);
    }

    @GetMapping("/date")
    public List<Cart> getCartByDate(@RequestParam("startdate") String startDate, @RequestParam("enddate") String endDate){
        return cartService.getCartByDate(startDate,endDate);
    }

    @PostMapping("")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUser(@PathVariable("userId") String userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/{cartId}")
    public Cart updateCart(@PathVariable("cartId")Long id, @RequestBody Cart cart) {
        return cartService.updateCart(id,cart);
    }

    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable("id")Long id) {
        return cartService.deleteCart(id);
    }
}
