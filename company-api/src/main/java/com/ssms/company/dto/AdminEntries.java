package com.ssms.company.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminEntries {
    private String companyId;
    @Builder.Default
    private List<DirectoryEntryDto> admins = new ArrayList<DirectoryEntryDto>();
}
