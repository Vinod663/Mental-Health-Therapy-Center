package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapyDetailDto {
    private int therapistId;
    private String therapistName;
    private String programId;
    private String programName;
    private String note;
}
