package com.orient.library.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lease")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "lease_and_status", joinColumns = @JoinColumn(name = "lease_id"), inverseJoinColumns = @JoinColumn(name = "lease_status_id"))
    List<LeaseStatus> statuses;
    @Column(name = "from_date")
    LocalDateTime fromDate;
    @Column(name = "to_date")
    LocalDateTime toDate;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
