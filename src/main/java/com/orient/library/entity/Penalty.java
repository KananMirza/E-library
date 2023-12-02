package com.orient.library.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "penalty")
@Data
@DynamicInsert
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    @JoinColumn(name = "lease_id", referencedColumnName = "id")
    Lease lease;
    @OneToOne
    @JoinColumn(name = "penalty_type_id", referencedColumnName = "id")
    PenaltyType penaltyType;
    Integer status;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
