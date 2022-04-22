package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CartService {


    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Cart createCart(final Long userId) {
        User user = userRepository.findById(userId).get();
        if(user.getCart() != null) {
            return user.getCart();
        }
        Cart cart = new Cart();
        cart.setUser(user);
        cart = cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
        return cart;
    }

    public List<Product> getProducts(final Long cartId) throws CartNotFoundException {
        List<Product> productsFromCarts = new ArrayList<>();
        productsFromCarts.addAll(cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new).getProducts());
        return productsFromCarts;
    }

    public void addProduct(final Long cartId, final Long productId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productId).get();
        List<Product> products = cart.getProducts();
        products.add(product);
        cartRepository.save(cart);
    }

    public boolean createOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setProducts(new ArrayList<>(cart.getProducts()));
        order = orderRepository.save(order);

        return order.getId() != null;
    }

    public boolean deleteProduct(final Long cartId, final Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Optional<Product> product = productRepository.findById(productId);

        if(!product.isPresent()) {
            throw new ProductNotFoundException("Task with given id doesn't exist");
        }

        boolean removed = cart.getProducts().remove(product.get());

        if(removed) {
            cartRepository.save(cart);
        }

        return removed;
    }


}