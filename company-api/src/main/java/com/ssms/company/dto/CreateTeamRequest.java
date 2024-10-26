package com.ssms.company.dto;

import com.ssms.common.validation.DayOfWeek;
import com.ssms.common.validation.Timezone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTeamRequest {
    @NotBlank
    private String companyId;
    @NotBlank
    private String name;
    @Timezone
    private String timezone;
    @DayOfWeek
    private String dayWeekStarts;
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
    @NotEmpty
    private String color;
}
