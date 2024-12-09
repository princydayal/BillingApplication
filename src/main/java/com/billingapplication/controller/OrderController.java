//package com.billingapplication.controller;
//
//import com.billingapplication.model.Order;
//import com.billingapplication.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//
//    @PostMapping("/createOrder")
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//
//        if (order == null || order.getProducts() == null || order.getProducts().isEmpty()) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//
//        Order createdOrder = orderService.createOrder(order);
//        return ResponseEntity.ok(createdOrder);
//    }
//
//
//    @GetMapping("/{orderId}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
//        Order order = orderService.getOrderById(orderId);
//        return ResponseEntity.ok(order);
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.getAllOrders();
//        return ResponseEntity.ok(orders);
//    }
//
//
//    @PutMapping("/{orderId}")
//    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Order orderDetails) {
//        Order updatedOrder = orderService.updateOrder(orderId, orderDetails);
//        return ResponseEntity.ok(updatedOrder);
//    }
//
//
//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
//        orderService.deleteOrder(orderId);
//        return ResponseEntity.noContent().build();
//    }
//}
