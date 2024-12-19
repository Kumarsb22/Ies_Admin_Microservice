package com.ies.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "plans_details")
public class PlansEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;
    private String planName;
    private LocalDateTime planStartDate;
    private LocalDateTime planEndDate;
    private String activeSw;
    @Column(insertable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(insertable = false)
    @UpdateTimestamp
    private  LocalDateTime updatedDate;
    private Integer createdBy;
    private Integer updatedBy;
}
