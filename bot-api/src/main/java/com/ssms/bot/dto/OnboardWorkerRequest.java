package com.ssms.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnboardWorkerRequest {
    @NotBlank
    private String companyId;
    @NotBlank
    private String userId;
}
