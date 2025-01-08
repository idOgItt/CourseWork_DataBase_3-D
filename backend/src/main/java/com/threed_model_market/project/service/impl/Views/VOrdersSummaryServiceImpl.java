package com.threed_model_market.project.service.impl.Views;

import com.threed_model_market.project.exception_handler.exceptions.Order.OrderNotFoundException;
import com.threed_model_market.project.model.Views.VOrdersSummary;
import com.threed_model_market.project.dto.Views.VOrdersSummaryDto;
import com.threed_model_market.project.repository.Views.VOrdersSummaryRepository;
import com.threed_model_market.project.service.Views.VOrdersSummaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VOrdersSummaryServiceImpl implements VOrdersSummaryService {

    private final VOrdersSummaryRepository vOrdersSummaryRepository;

    public VOrdersSummaryServiceImpl(VOrdersSummaryRepository vOrdersSummaryRepository) {
        this.vOrdersSummaryRepository = vOrdersSummaryRepository;
    }

    @Override
    public List<VOrdersSummaryDto> getAllOrdersSummary() {
        List<VOrdersSummary> ordersSummary = vOrdersSummaryRepository.findAll();
        return ordersSummary.stream()
                .map(order -> new VOrdersSummaryDto(
                        order.getId(),
                        order.getOrderId(),
                        order.getUser(),
                        order.getStatus(),
                        order.getTotalAmount(),
                        order.getDiscountCode(),
                        order.getDiscountDetails(),
                        order.getOrderDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public VOrdersSummaryDto getOrderSummaryByOrderId(Integer orderId) {
        VOrdersSummary orderSummary = vOrdersSummaryRepository.findByOrderId(orderId);
        if (orderSummary == null) {
            throw new OrderNotFoundException("Order summary not found for Order ID: " + orderId);
        }

        return new VOrdersSummaryDto(
                orderSummary.getId(),
                orderSummary.getOrderId(),
                orderSummary.getUser(),
                orderSummary.getStatus(),
                orderSummary.getTotalAmount(),
                orderSummary.getDiscountCode(),
                orderSummary.getDiscountDetails(),
                orderSummary.getOrderDate()
        );
    }
}
