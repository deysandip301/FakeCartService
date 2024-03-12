package com.example.cartservice.Services;

import com.example.cartservice.DTOs.CartDTO;
import com.example.cartservice.DTOs.CartProductDTO;
import com.example.cartservice.Models.Cart;
import com.example.cartservice.Models.CartProduct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCartService implements CartService{

    private final String API_URL = "https://fakestoreapi.com/carts";
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<Cart> getAllCarts() {
        // method to get all the cart items
        try {
            CartDTO[] cartDTOS = restTemplate.getForObject(API_URL,CartDTO[].class);
            if(cartDTOS != null) {
                return mapToCarts(List.of(cartDTOS));
            }
        }
        catch (RestClientException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // making list of cart from the fetched cartDTOs
    private List<Cart> mapToCarts(List<CartDTO> cartDTOS) {
        List<Cart> cartConvertedList = new ArrayList<>();
        for(CartDTO fakeStoreCart : cartDTOS) {
            if(fakeStoreCart != null) {
                cartConvertedList.add(mapToCart(fakeStoreCart));
            }
        }
        return cartConvertedList;
    }

    // making a single cart from the given cartDTO

    private Cart mapToCart(CartDTO fakeStoreDTO) {
        Cart newCart = new Cart();
        newCart.setId(fakeStoreDTO.getId());
        newCart.setUserId(fakeStoreDTO.getUserId());
        newCart.setDate(fakeStoreDTO.getDate());
        newCart.setProducts(toListOfCartProducts(fakeStoreDTO.getProducts()));
        return newCart;
    }

    // making the list of cartProducts from the fetched products list on cart
    private List<CartProduct> toListOfCartProducts(List<CartProductDTO> products) {
        List<CartProduct> listOfCartProducts = new ArrayList<>();
        for(CartProductDTO fakeStoreCartProduct : products) {
            if(fakeStoreCartProduct != null) {
                CartProduct cartProduct = new CartProduct();
                cartProduct.setProductId(fakeStoreCartProduct.getProductId());
                cartProduct.setQuantity(fakeStoreCartProduct.getQuantity());
                listOfCartProducts.add(cartProduct);
            }
        }
        return listOfCartProducts;
    }

    @Override
    public Cart getSingleCart(Long id) {
        // method to get only one cart item by id
        try{
            CartDTO cartDTO = restTemplate.getForObject(API_URL +"/" + id,CartDTO.class);
            if(cartDTO != null) {
                return mapToCart(cartDTO);
            }
        }
        catch (RestClientException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Cart> getCartByDate(String startDate,String endDate){
        // method to get cart items by start and end date
        try {
            CartDTO[] cartDTOS = restTemplate.getForObject(API_URL + "?startdate=" + startDate +"&enddate="+endDate,CartDTO[].class);
            if(cartDTOS != null) {
                return mapToCarts(List.of(cartDTOS));
            }
        }
        catch (RestClientException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Cart addCart(Cart cart) {
        try {
            CartDTO cartDTO = restTemplate.postForObject(API_URL,mapToCartDTO(cart),CartDTO.class);
            if(cartDTO != null) {
                return mapToCart(cartDTO);
            }
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CartDTO mapToCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
//        System.out.println(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setDate(cart.getDate());
        cartDTO.setProducts(mapToCartProductDTO(cart.getProducts()));
        return cartDTO;
    }

    private List<CartProductDTO> mapToCartProductDTO(List<CartProduct> cartProducts) {
        List<CartProductDTO> listOfCartProductDTOs = new ArrayList<>();
        for(CartProduct products : cartProducts) {
            if(products != null) {
                CartProductDTO cartProductDTO = new CartProductDTO();
                cartProductDTO.setProductId(products.getProductId());
                cartProductDTO.setQuantity(products.getQuantity());
                listOfCartProductDTOs.add(cartProductDTO);
            }
        }
        return listOfCartProductDTOs;
    }

    @Override
    public List<Cart> getCartByUser(String userId) {
        try {
            CartDTO[] cartDTOS = restTemplate.getForObject(API_URL + "/user/" + userId,CartDTO[].class);
            if(cartDTOS != null) {
                return mapToCarts(List.of(cartDTOS));
            }
        }
        catch (RestClientException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        try {
            CartDTO cartDTO = restTemplate.postForObject(API_URL,mapToCartDTO(cart),CartDTO.class);
            if(cartDTO != null) {
                return mapToCart(cartDTO);
            }
        }
        catch (RestClientException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteCart(Long cartId) {
        try {
            restTemplate.delete(API_URL+"/" + cartId);
            return "The cart with cartId " + cartId + " has been deleted successfully.";
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
        return "Unable to delete the cart with cartId " + cartId;
    }
}
