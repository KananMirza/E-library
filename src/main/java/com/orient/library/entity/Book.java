package com.orient.library.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
@DynamicInsert
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "seria_id")
    String seriaId;
    @Column(name = "shelf_id")
    Integer shelfId;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "penalty_amount")
    Float penaltyAmount;
    @Column(name = "year_publishing")
    Year yearPublishing;
    @Column(name = "count")
    Integer count;
    @Column(name = "status")
    Integer status;
    @Column(name = "is_deleted")
    Integer isDeleted;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "shelf_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Shelf shelf;

}
