package com.billingapplication.service;

import com.billingapplication.model.Cart;
import com.billingapplication.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepository;

    // Create a new Cart
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get all Carts
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // Get a Cart by ID
    public List<Cart> getCartById(String id) {
        return cartRepository.findByUserid(id);
    }

    // Update a Cart
    public Cart updateCart(int id, Cart cartDetails) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.setCartQuantity(cartDetails.getCartQuantity());

        return cartRepository.save(cart);
    }

    // Delete a Cart
    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }
}
