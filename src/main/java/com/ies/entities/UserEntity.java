package com.ies.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fullName;
    private String email;
    private String pazzword;
    private String phonNo;
    private char gender;
    private LocalDate dob;
    private String ssn;
    private String activeSw;
private String activeStatus;
    private String roleName;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private Integer createdBy;
    private Integer updatedBy;
    @OneToOne
    private PlansEntitiy plansEntitiy;
}
