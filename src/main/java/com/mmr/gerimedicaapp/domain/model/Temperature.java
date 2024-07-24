package com.mmr.gerimedicaapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Temperature {
    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private String sortingPriority;
}
