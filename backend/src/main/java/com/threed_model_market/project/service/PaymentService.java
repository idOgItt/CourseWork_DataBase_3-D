package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.PaymentDto;
import com.threed_model_market.project.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDto paymentDto);

    List<Payment> getPaymentsByOrderId(Long orderId);

    void deletePayment(Long id);
}
