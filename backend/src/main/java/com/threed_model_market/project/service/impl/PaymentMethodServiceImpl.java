package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.PaymentMethodDto;
import com.threed_model_market.project.exception_handler.exceptions.PaymentMethod.PaymentMethodNotFoundException;
import com.threed_model_market.project.model.PaymentMethod;
import com.threed_model_market.project.repository.PaymentMethodRepository;
import com.threed_model_market.project.service.PaymentMethodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethodDto paymentMethodDto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(paymentMethodDto.getName());
        paymentMethod.setDescription(paymentMethodDto.getDescription());

        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new PaymentMethodNotFoundException("PaymentMethod not found with ID: " + id));
    }

    @Override
    public void removePaymentMethod(Long id) {
        PaymentMethod paymentMethod = getPaymentMethodById(id);
        paymentMethodRepository.delete(paymentMethod);
    }
}
