package com.threed_model_market.project.service.impl.Views;

import com.threed_model_market.project.dto.Views.VUserStatisticDto;
import com.threed_model_market.project.model.Views.VUserStatistic;
import com.threed_model_market.project.repository.Views.VUserStatisticRepository;
import com.threed_model_market.project.service.Views.VUserStatisticService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VUserStatisticServiceImpl implements VUserStatisticService {

    private final VUserStatisticRepository vUserStatisticRepository;

    public VUserStatisticServiceImpl(VUserStatisticRepository vUserStatisticRepository) {
        this.vUserStatisticRepository = vUserStatisticRepository;
    }

    @Override
    public VUserStatisticDto getUserStatisticById(Long id) {
        VUserStatistic statistic = vUserStatisticRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User statistic not found"));
        return convertToDto(statistic);
    }

    @Override
    public List<VUserStatisticDto> getAllUserStatistics() {
        List<VUserStatistic> statistics = vUserStatisticRepository.findAll();
        return statistics.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private VUserStatisticDto convertToDto(VUserStatistic statistic) {
        return new VUserStatisticDto(
                statistic.getId(),
                statistic.getUserId(),
                statistic.getUsername(),
                statistic.getEmail(),
                statistic.getTotalOrders(),
                statistic.getTotalSpent(),
                statistic.getTotalReviews()
        );
    }
}
