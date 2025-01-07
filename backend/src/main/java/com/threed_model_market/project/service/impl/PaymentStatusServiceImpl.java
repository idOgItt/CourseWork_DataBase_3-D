package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.PaymentStatusDto;
import com.threed_model_market.project.exception_handler.exceptions.PaymentStatus.PaymentStatusNotFoundException;
import com.threed_model_market.project.model.PaymentStatus;
import com.threed_model_market.project.repository.PaymentStatusRepository;
import com.threed_model_market.project.service.PaymentStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;

    public PaymentStatusServiceImpl(PaymentStatusRepository paymentStatusRepository) {
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public PaymentStatus createPaymentStatus(PaymentStatusDto paymentStatusDto) {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setName(paymentStatusDto.getName());
        paymentStatus.setDescription(paymentStatusDto.getDescription());

        return paymentStatusRepository.save(paymentStatus);
    }

    @Override
    public List<PaymentStatus> getAllPaymentStatuses() {
        return paymentStatusRepository.findAll();
    }

    @Override
    public PaymentStatus getPaymentStatusById(Long id) {
        return paymentStatusRepository.findById(id)
                .orElseThrow(() -> new PaymentStatusNotFoundException("PaymentStatus not found with ID: " + id));
    }

    @Override
    public void removePaymentStatus(Long id) {
        PaymentStatus paymentStatus = getPaymentStatusById(id);
        paymentStatusRepository.delete(paymentStatus);
    }
}
