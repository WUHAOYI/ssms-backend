package com.ssms.company.dto;
import lombok.*;

import com.ssms.common.api.BaseResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericCompanyResponse extends BaseResponse {
    private CompanyDto company;
}
