package com.ies.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "IES_ELIGIBALITY_CRITERIA")
public class EligCritiria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer traceId;
 private String planStatus;
 private Double benfiAmt;
}
