package com.mmr.gerimedicaapp.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Temperature {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Integer id;
    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private String sortingPriority;
}
