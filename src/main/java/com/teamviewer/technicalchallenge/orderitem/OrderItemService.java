package com.teamviewer.technicalchallenge.orderitem;

import com.teamviewer.technicalchallenge.product.ExistingProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItemRepository.findAll();
    }

    public OrderItem getOrderItem(Long id) {
        return this.orderItemRepository.findById(id).orElseThrow(() -> new OrderItemNotFoundException(id));
    }

    public OrderItem createOrderItem(OrderItem newOrderItem) {
        boolean orderItemExists = this.orderItemRepository.existsById(newOrderItem.getId());
        if (!orderItemExists) {
            return this.orderItemRepository.save(newOrderItem);
        } else {
            throw new ExistingProductException(newOrderItem.getId());
        }
    }

    public OrderItem updateOrderItem(Long id, OrderItem newOrderItem) {
        return this.orderItemRepository.findById(id)
                .map(orderItem -> {
                    orderItem.setId(id);
                    orderItem.setQuantity(newOrderItem.getQuantity());
                    orderItem.setProduct(newOrderItem.getProduct());
                    orderItem.setOrder(newOrderItem.getOrder());
                    return this.orderItemRepository.save(orderItem);
                })
                .orElseThrow(() -> new OrderItemNotFoundException(id));

    }

    public void deleteOrderItem(Long id) {
        this.orderItemRepository.deleteById(id);
    }
}
