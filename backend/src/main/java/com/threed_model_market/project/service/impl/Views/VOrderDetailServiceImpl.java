package com.threed_model_market.project.service.impl.Views;

import com.threed_model_market.project.dto.Views.VOrderDetailDto;
import com.threed_model_market.project.exception_handler.exceptions.Order.OrderNotFoundException;
import com.threed_model_market.project.model.Views.VOrderDetail;
import com.threed_model_market.project.repository.Views.VOrderDetailRepository;
import com.threed_model_market.project.service.Views.VOrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VOrderDetailServiceImpl implements VOrderDetailService {

    private final VOrderDetailRepository vOrderDetailRepository;

    public VOrderDetailServiceImpl(VOrderDetailRepository vOrderDetailRepository) {
        this.vOrderDetailRepository = vOrderDetailRepository;
    }

    @Override
    public List<VOrderDetailDto> getAllOrderDetails() {
        List<VOrderDetail> vOrderDetails = vOrderDetailRepository.findAll();
        if (vOrderDetails.isEmpty()) {
            throw new OrderNotFoundException("No order details found");
        }
        return vOrderDetails.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<VOrderDetailDto> getOrderDetailsByOrderId(Long orderId) {
        List<VOrderDetail> orderDetails = vOrderDetailRepository.findByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            throw new OrderNotFoundException("No order details found for order ID: " + orderId);
        }
        return orderDetails.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<VOrderDetailDto> getOrderDetailsByUser(String username) {
        List<VOrderDetail> orderDetails = vOrderDetailRepository.findByUser(username);
        if (orderDetails.isEmpty()) {
            throw new OrderNotFoundException("No order details found for user: " + username);
        }
        return orderDetails.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<VOrderDetailDto> getOrderDetailsByStatus(String status) {
        List<VOrderDetail> orderDetails = vOrderDetailRepository.findByStatus(status);
        if (orderDetails.isEmpty()) {
            throw new OrderNotFoundException("No order details found for status: " + status);
        }
        return orderDetails.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private VOrderDetailDto mapToDto(VOrderDetail vOrderDetail) {
        VOrderDetailDto dto = new VOrderDetailDto();
        dto.setId(vOrderDetail.getId());
        dto.setOrderId(vOrderDetail.getOrderId());
        dto.setUser(vOrderDetail.getUser());
        dto.setOrderDate(vOrderDetail.getOrderDate());
        dto.setStatus(vOrderDetail.getStatus());
        dto.setModelName(vOrderDetail.getModelName());
        dto.setQuantity(vOrderDetail.getQuantity());
        dto.setLineTotal(vOrderDetail.getLineTotal());
        return dto;
    }
}
