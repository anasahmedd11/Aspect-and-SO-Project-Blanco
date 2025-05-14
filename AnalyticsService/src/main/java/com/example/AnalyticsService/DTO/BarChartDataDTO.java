package com.example.AnalyticsService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BarChartDataDTO {
    private List<String> xValues;
    private List<Double> yValues;
}
