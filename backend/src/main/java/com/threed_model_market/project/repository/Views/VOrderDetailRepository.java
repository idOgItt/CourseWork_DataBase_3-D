package com.threed_model_market.project.repository.Views;

import com.threed_model_market.project.model.Views.VOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VOrderDetailRepository extends JpaRepository<VOrderDetail, Long> {
    List<VOrderDetail> findByOrderId(Long orderId);
    List<VOrderDetail> findByUser(String username);
    List<VOrderDetail> findByStatus(String status);
}
