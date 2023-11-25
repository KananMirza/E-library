package com.orient.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_phones")
@DynamicInsert
public class UserPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    String phone;
}
