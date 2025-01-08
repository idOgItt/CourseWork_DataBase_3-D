package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.PaymentDto;
import com.threed_model_market.project.exception_handler.exceptions.Order.OrderNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.Payment.PaymentNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.PaymentMethod.PaymentMethodNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.PaymentStatus.PaymentStatusNotFoundException;
import com.threed_model_market.project.model.Order;
import com.threed_model_market.project.model.Payment;
import com.threed_model_market.project.model.PaymentMethod;
import com.threed_model_market.project.model.PaymentStatus;
import com.threed_model_market.project.repository.OrderRepository;
import com.threed_model_market.project.repository.PaymentMethodRepository;
import com.threed_model_market.project.repository.PaymentRepository;
import com.threed_model_market.project.repository.PaymentStatusRepository;
import com.threed_model_market.project.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentStatusRepository paymentStatusRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository, PaymentMethodRepository paymentMethodRepository, PaymentStatusRepository paymentStatusRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public Payment createPayment(PaymentDto paymentDto) {
        Order order = orderRepository.findById(paymentDto.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + paymentDto.getOrderId()));

        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentDto.getPaymentMethodId())
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found"));

        PaymentStatus paymentStatus = paymentStatusRepository.findById(paymentDto.getPaymentStatusId())
                .orElseThrow(() -> new PaymentStatusNotFoundException("Payment status not found"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(paymentStatus);

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
    }

    @Override
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with ID: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
