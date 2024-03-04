package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.order.Order;
import com.teamviewer.technicalchallenge.order.Order;
import com.teamviewer.technicalchallenge.order.Order;
import com.teamviewer.technicalchallenge.order.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        return this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order createOrder(Order newOrder) {
        newOrder.setStatus(Status.IN_PROGRESS);
        return this.orderRepository.save(newOrder);
    }

    public Order updateOrder(Long id, Order newOrder) {
        return this.orderRepository.findById(id)
                .map(order -> {
                    order.setId(id);
                    order.setStatus(newOrder.getStatus());
                    order.setOrderItems(newOrder.getOrderItems());
                    return this.orderRepository.save(order);
                })
                .orElseThrow(() -> new OrderNotFoundException(id));

    }

    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
    
}
