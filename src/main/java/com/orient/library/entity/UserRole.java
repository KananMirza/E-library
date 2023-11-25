package com.orient.library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_roles")
@DynamicInsert
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @JsonProperty("created_at")
    LocalDateTime createdAt;
}
