package com.ssms.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ssms.company.dto.ShiftDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertChangedShiftRequest {
    @NotBlank
    private String userId;
    @NotNull
    private ShiftDto oldShift;
    @NotNull
    private ShiftDto newShift;
}
