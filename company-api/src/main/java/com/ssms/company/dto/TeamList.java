package com.ssms.company.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamList {
    @Builder.Default
    private List<TeamDto> teams = new ArrayList<TeamDto>();
}
