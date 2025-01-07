package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.PaymentMethodDto;
import com.threed_model_market.project.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(PaymentMethodDto paymentMethodDto);

    List<PaymentMethod> getAllPaymentMethods();

    PaymentMethod getPaymentMethodById(Long id);

    void removePaymentMethod(Long id);
}
