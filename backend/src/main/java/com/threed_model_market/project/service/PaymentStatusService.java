package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.PaymentStatusDto;
import com.threed_model_market.project.model.PaymentStatus;

import java.util.List;

public interface PaymentStatusService {
    PaymentStatus createPaymentStatus(PaymentStatusDto paymentStatusDto);

    List<PaymentStatus> getAllPaymentStatuses();

    PaymentStatus getPaymentStatusById(Long id);

    void removePaymentStatus(Long id);
}
