package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.PaymentDto;
import com.threed_model_market.project.exception_handler.exceptions.Order.OrderNotFoundException;
import com.threed_model_market.project.model.Order;
import com.threed_model_market.project.model.Payment;
import com.threed_model_market.project.repository.OrderRepository;
import com.threed_model_market.project.repository.PaymentRepository;
import com.threed_model_market.project.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment createPayment(PaymentDto paymentDto) {

        Order order = orderRepository.findById(paymentDto.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + paymentDto.getOrderId()));

        orderRepository.refresh(order);

        if (order.getTotalAmount() == null || order.getTotalAmount().compareTo(paymentDto.getAmount()) != 0) {
            throw new IllegalArgumentException("Payment amount does not match the total order amount.");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
