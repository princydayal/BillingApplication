package com.billingapplication.service;

import com.billingapplication.model.Order;
import com.billingapplication.model.Product;
import com.billingapplication.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // Create an order
    @Transactional
    public Order createOrder(Order order) {
        // Check if user email is registered
        if (!userService.isEmailRegistered(order.getUserEmail())) {
            throw new RuntimeException("Email not registered: " + order.getUserEmail());
        }
        // Generate a unique 4 to 6 digit order number
        String orderNumber = generateOrderNumber();
        order.setOrderNumber(orderNumber);

         //Validate if products exist and are valid
//        Set<Product> products = order.getProducts();
//        for (Product product : products) {
//            productService.validateProduct(product.getProductid());
//        }

        // Save the order to the repository
        return orderRepository.save(order);
    }

    // Generate a 4 to 6 digit order number based on UUID
    private String generateOrderNumber() {
        // Extract a portion of UUID, convert to digits, and limit to 6 digits
        return UUID.randomUUID().toString().replaceAll("[^\\d]", "").substring(0, 6);
    }

    // Update an order
    @Transactional
    public Order updateOrder(Long orderId, Order orderDetails) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + orderId + " does not exist."));

        if (!userService.isEmailRegistered(orderDetails.getUserEmail())) {
            throw new IllegalArgumentException("User with email " + orderDetails.getUserEmail() + " does not exist.");
        }

        // Update order details
        existingOrder.setTotalAmount(orderDetails.getTotalAmount());
        existingOrder.setProducts(orderDetails.getProducts());

        // Save the updated order
        return orderRepository.save(existingOrder);
    }

    // Delete an order
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist.");
        }

        orderRepository.deleteById(orderId);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + orderId + " does not exist."));
    }
}
