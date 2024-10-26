package com.ssms.company.dto;

import com.ssms.common.api.BaseResponse;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericTeamResponse extends BaseResponse {
    private TeamDto team;
}
