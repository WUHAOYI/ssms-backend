package com.ssms.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthGraphResponse {
    private Map<String, Integer> peopleScheduledPerWeek;
    private Integer peopleOnShift;
}
