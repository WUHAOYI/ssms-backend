package com.ssms.company.dto;

import lombok.*;
import com.ssms.common.api.BaseResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericJobResponse extends BaseResponse {
    private JobDto job;
}
