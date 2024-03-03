package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.order.Order;
import com.teamviewer.technicalchallenge.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderModelAssembler assembler) {
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Optional<Order> getOrder(Long id) {
        return this.orderRepository.findById(id);
    }

    public Order createOrder(Order newOrder) {
        return this.orderRepository.save(newOrder);
    }

    public Order updateOrder(Long id, Order newOrder) {
        Order order = this.orderRepository.findById(id).orElseGet(() -> newOrder);
        order.setId(id);
//        order.setName(newOrder.getName());
        order.setDescription(newOrder.getDescription());
        return this.orderRepository.save(newOrder);
    }

    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
    
}
